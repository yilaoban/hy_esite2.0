package com.huiyee.interact.setting.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.constants.IPrivilegeConstants;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.model.WxUser;
import com.huiyee.esite.service.Permission;
import com.huiyee.interact.setting.dto.SettingDto;
import com.huiyee.interact.setting.mgr.IHyUserDzMgr;
import com.huiyee.interact.setting.model.HyUserDz;

import net.sf.json.JSONObject;

public class SettingDzAction extends AbstractAction {
	private static final long serialVersionUID = 6328825920431173450L;

	private IHyUserDzMgr hyUserDzMgr;

	private int pageId = 1;
	private SettingDto dto;
	private HyUserDz dz;
	private HyUser user;

	@Override
	public int getLightType() {
		return 4;
	}

	@Permission(module = IPrivilegeConstants.MODULE_APP_微积分, privilege = IPrivilegeConstants.PERMISSION_R)
	public String execute() throws Exception {
		String type = null;
		if (dz != null) {
			type = dz.getType();
		}

		long owner = this.getOwner();
		int rows = 10;
		int start = (pageId - 1) * rows;
		int total = hyUserDzMgr.findDzCount(owner, type);
		List<HyUserDz> dzList = hyUserDzMgr.findDzList(owner, type, start, rows);

		dto = new SettingDto();
		dto.setDzList(dzList);
		dto.setPager(new Pager(pageId, total, rows));
		return SUCCESS;
	}

	public String dzqx() throws Exception {
		VisitUser vu = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
		if (vu == null || dz == null) {
			return null;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			long owner = this.getOwner();
			int res = hyUserDzMgr.findDzByHyuid(owner, "t7", vu.getHyUserId());
			if (res == 0) {
				out.print("{\"errcode\":-2,\"errmsg\":\"您没有权限查看此页面\"}");
				return null;
			}
			HyUserDz hud = hyUserDzMgr.findDzByHyuid(owner, dz.getHyuid());
			JSONObject jo = new JSONObject();
			jo.put("errcode", 0);
			jo.put("errmsg", "ok");
			jo.put("dz", hud);
			out.print(jo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public String addDz() throws Exception {
		VisitUser vu = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
		if (vu == null) {
			return null;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			long owner = this.getOwner();
			WxUser wu = vu.getWxUser();
			HyUser hu = vu.getHyUser();
			if (wu != null) {
				int count = hyUserDzMgr.findDzByWxuid(owner, null, wu.getId());
				if (count > 0) {
					out.print("{\"errcode\":-2,\"errmsg\":\"您已经添加过该用户了\"}");
					return null;
				}
			} else if (hu != null) {
				int count = hyUserDzMgr.findDzByHyuid(owner, null, hu.getId());
				if (count > 0) {
					out.print("{\"errcode\":-2,\"errmsg\":\"您已经添加过该用户了\"}");
					return null;
				}
			}

			HyUserDz dz = new HyUserDz();
			dz.setOwner(owner);
			if (hu != null) {
				dz.setHyuid(hu.getId());
			}
			if (wu != null) {
				dz.setWxuid(wu.getId());
				dz.setOpenid(wu.getOpenid());
				dz.setNickname(wu.getNickname());
				dz.setSex(wu.getSex());
				dz.setHeadimgurl(wu.getHeadimgurl());
			}
			int res = hyUserDzMgr.addDz(dz, vu);
			if (res > 0) {
				out.print("{\"errcode\":0,\"errmsg\":\"ok\"}");
			} else {
				out.print("{\"errcode\":-1,\"errmsg\":\"DB save failed\"}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public String editDz() throws Exception {
		if (dz == null) {
			return null;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			int res = hyUserDzMgr.updateDz(dz.getId(), dz.getType(), dz.getType_val());
			out.print(res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public String delDz() throws Exception {
		if (dz == null) {
			return null;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			int res = hyUserDzMgr.delDz(dz.getId());
			out.print(res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public String user() throws Exception {
		if (user == null) {
			return null;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			HyUser u = hyUserDzMgr.findUser(user.getId());
			out.print(new Gson().toJson(u));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public String editUser() throws Exception {
		if (user == null) {
			return null;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			int res = hyUserDzMgr.updateUser(user);
			out.print(res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public void setHyUserDzMgr(IHyUserDzMgr hyUserDzMgr) {
		this.hyUserDzMgr = hyUserDzMgr;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public SettingDto getDto() {
		return dto;
	}

	public void setDto(SettingDto dto) {
		this.dto = dto;
	}

	public HyUserDz getDz() {
		return dz;
	}

	public void setDz(HyUserDz dz) {
		this.dz = dz;
	}

	public HyUser getUser() {
		return user;
	}

	public void setUser(HyUser user) {
		this.user = user;
	}

}
