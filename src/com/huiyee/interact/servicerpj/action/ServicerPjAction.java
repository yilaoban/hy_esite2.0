
package com.huiyee.interact.servicerpj.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.constants.IPrivilegeConstants;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.Sitegroup;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.service.Permission;
import com.huiyee.interact.servicerpj.dto.ServicerPjDto;
import com.huiyee.interact.servicerpj.mgr.IServicerMgr;
import com.huiyee.interact.servicerpj.mgr.IServicerPjMgr;
import com.huiyee.interact.servicerpj.mgr.IServicerPjPageMgr;
import com.huiyee.interact.servicerpj.mgr.IServicerPjWdMgr;
import com.huiyee.interact.servicerpj.model.ServicerPj;
import com.huiyee.interact.servicerpj.model.ServicerPjPage;
import com.huiyee.interact.servicerpj.model.ServicerPjWd;
import com.huiyee.interact.template.mgr.IWxTemplateMgr;
import com.huiyee.interact.template.model.WxTemplate;
import com.huiyee.interact.yuyue.model.YuYueServicer;
import com.opensymphony.xwork2.ActionContext;

import net.sf.json.JSONObject;

/**
 * 服务者评价
 * 
 * @author ldw
 * 
 */
public class ServicerPjAction extends AbstractAction {

	private static final long serialVersionUID = -5156101853651948239L;
	private IServicerMgr servicerMgr;
	private IServicerPjMgr servicerPjMgr;
	private IServicerPjPageMgr servicerPjPageMgr;
	private IServicerPjWdMgr servicerPjWdMgr;
	private YuYueServicer yuYueServicer;
	private ServicerPjWd servicerPjWd;
	private IWxTemplateMgr wxTemplateMgr;

	private int pageId = 1;
	private ServicerPjDto dto;
	private long caid;
	private long serid;
	private int moveUp;
	private int top;
	private WxTemplate wt_f;
	private WxTemplate wt_r;
	private ServicerPj pj;
	private List<Sitegroup> sgList;
	private long sgid;
	private int source;
	private long pjwdid;
	private String name;

	public void setServicerPjWdMgr(IServicerPjWdMgr servicerPjWdMgr) {
		this.servicerPjWdMgr = servicerPjWdMgr;
	}

	/**
	 * 服务者列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@Permission(module = IPrivilegeConstants.MODULE_APP_服务评价, privilege = IPrivilegeConstants.PERMISSION_R)
	public String ServicerIndex() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (ServicerPjDto) servicerPjMgr.findServicerList(account, pageId);
		setLightType(1);
		return SUCCESS;
	}

	/**
	 * 服务者评价列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@Permission(module = IPrivilegeConstants.MODULE_APP_服务评价, privilege = IPrivilegeConstants.PERMISSION_R)
	public String pjIndex() throws Exception {
		setLightType(2);
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (ServicerPjDto) servicerPjMgr.findServicerPjList(account, serid, pageId);
		return SUCCESS;
	}

	/**
	 * 添加服务者页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@Permission(module = IPrivilegeConstants.MODULE_APP_服务评价, privilege = IPrivilegeConstants.PERMISSION_R)
	public String addServicer() throws Exception {
		setLightType(1);
		return SUCCESS;
	}

	/**
	 * 添加服务者提交
	 * 
	 * @return
	 * @throws Exception
	 */
	@Permission(module = IPrivilegeConstants.MODULE_APP_服务评价, privilege = IPrivilegeConstants.PERMISSION_R_and_X)
	public String addServicerSub() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		servicerPjMgr.saveYuYueServicer(yuYueServicer, account.getOwner().getId());
		return SUCCESS;
	}

	/**
	 * 编辑服务者
	 * 
	 * @return
	 * @throws Exception
	 */
	@Permission(module = IPrivilegeConstants.MODULE_APP_服务评价, privilege = IPrivilegeConstants.PERMISSION_R_and_X)
	public String editServicer() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		yuYueServicer = servicerPjMgr.findServicerById(account, serid);
		return SUCCESS;
	}

	/**
	 * 编辑服务者提交
	 * 
	 * @return
	 * @throws Exception
	 */
	@Permission(module = IPrivilegeConstants.MODULE_APP_服务评价, privilege = IPrivilegeConstants.PERMISSION_R_and_X)
	public String editServicerSub() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		servicerPjMgr.updateServicer(yuYueServicer, account.getOwner().getId());
		return SUCCESS;
	}

	/**
	 * 导入服务者
	 * 
	 * @return
	 * @throws Exception
	 */
	@Permission(module = IPrivilegeConstants.MODULE_APP_服务评价, privilege = IPrivilegeConstants.PERMISSION_R_and_X)
	public String loadServicer() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("json/application;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int rs = servicerPjMgr.updateServicerByCaid(account, caid);
		out.print(rs);
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 删除服务者
	 * 
	 * @return
	 * @throws Exception
	 */
	@Permission(module = IPrivilegeConstants.MODULE_APP_服务评价, privilege = IPrivilegeConstants.PERMISSION_R_and_X)
	public String delServicer() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("json/application;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int rs = servicerPjMgr.delServicer(account, serid);
		out.print(rs);
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 服务者上移下移
	 * 
	 * @return
	 * @throws Exception
	 */
	@Permission(module = IPrivilegeConstants.MODULE_APP_服务评价, privilege = IPrivilegeConstants.PERMISSION_R_and_X)
	public String servicerMove() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("json/application;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int rs = servicerPjMgr.updateServicerOidx(account, serid, moveUp);
		out.print(rs);
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 服务者置顶/取消置顶
	 * 
	 * @return
	 * @throws Exception
	 */
	@Permission(module = IPrivilegeConstants.MODULE_APP_服务评价, privilege = IPrivilegeConstants.PERMISSION_R_and_X)
	public String servicerTop() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("json/application;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int rs = servicerPjMgr.updateServicerTop(account, serid, top);
		out.print(rs);
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 模板消息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Permission(module = IPrivilegeConstants.MODULE_APP_服务评价, privilege = IPrivilegeConstants.PERMISSION_R_and_X)
	public String template() throws Exception {
		long owner = this.getOwner();
		// 服务评价通知
		String type = "FWP";
		long store_id = 3;
		wt_f = wxTemplateMgr.getTemplate(owner, type);
		if (wt_f == null) {
			wt_f = new WxTemplate();
			wt_f.setType(type);
			wt_f.setStore_id(store_id);
		}

		// 店主回复通知
		type = "FWR";
		store_id = 3;
		wt_r = wxTemplateMgr.getTemplate(owner, type);
		if (wt_r == null) {
			wt_r = new WxTemplate();
			wt_r.setType(type);
			wt_r.setStore_id(store_id);
		}

		setLightType(4);
		return SUCCESS;
	}

	/**
	 * 服务配置
	 * 
	 * @return
	 * @throws Exception
	 */
	@Permission(module = IPrivilegeConstants.MODULE_APP_服务评价, privilege = IPrivilegeConstants.PERMISSION_R_and_X)
	public String config() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		sgList = pageCompose.findGroupTofs(account, IPageConstants.SITE_GROUP_H);
		setLightType(5);
		return SUCCESS;
	}

	/**
	 * 服务配置
	 * 
	 * @return
	 * @throws Exception
	 */
	@Permission(module = IPrivilegeConstants.MODULE_APP_服务评价, privilege = IPrivilegeConstants.PERMISSION_R_and_X)
	public String configSub() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("json/application;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int rs = servicerPjMgr.updateServicerPage(account, sgid, source);
		out.print(rs);
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 手动邀请用户评价
	 * 
	 * @return
	 * @throws Exception
	 */
	public String pjInvite() throws Exception {
		if (pj == null) {
			return null;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("json/application;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		try {
			pj.setOwner(this.getOwner());
			pj.setOname(this.getOname());
			pj.setType(0);
			pj.setEnid(System.currentTimeMillis());
			int res = servicerPjMgr.addServicerPjInvite(pj);
			if (res > 0) {
				json.put("errcode", 0);
				json.put("errmsg", "ok");
			} else if (res == 0) {
				json.put("errcode", -9);
				json.put("errmsg", "模板消息未设置");
			} else if (res == -1) {
				json.put("errcode", -1);
				json.put("errmsg", "未知用户");
			} else if (res == -2) {
				json.put("errcode", -2);
				json.put("errmsg", "未知二维码");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.print(json);
			out.flush();
			out.close();
		}
		return null;
	}

	/**
	 * 用户点击服务评价消息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String pjHub() throws Exception {
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if (vu == null) {
			return null;
		}
		JSONObject json = new JSONObject();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("json/application;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			String kv = vu.getKv();
			if (kv == null || !kv.matches("^\\d+-\\d+-\\d+$")) {
				json.put("errcode", -1);
				json.put("errmsg", "kv is invalid");
				return null;
			}
			String[] kvs = kv.split("-");
			int type = Integer.valueOf(kvs[0]);
			long enid = Long.valueOf(kvs[2]);
			long owner = this.getOwner();

			ServicerPjPage pjp = servicerPjPageMgr.findServicerPjPage(owner, type);
			if (pjp == null) {
				json.put("errcode", -2);
				json.put("errmsg", "没有可用的应用站点");
				return null;
			}

			ServicerPj pj = servicerPjMgr.findServicerPj(type, enid);
			long pageid = 0;
			if (pj != null) {
				pageid = pjp.getPjxid();
			} else {
				pageid = pjp.getPjtid();
			}
			if (pageid == 0) {
				json.put("errcode", -3);
				json.put("errmsg", "缺少功能页面");
				return null;
			}

			json.put("errcode", 0);
			json.put("errmsg", "ok");
			json.put("pageid", pageid);
			json.put("kv", kv);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.print(json);
			out.flush();
			out.close();
		}
		return null;
	}

	/**
	 * 服务评价提交
	 * 
	 * @return
	 * @throws Exception
	 */
	public String pjAdd() throws Exception {
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if (vu == null || pj == null) {
			return null;
		}
		JSONObject json = new JSONObject();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("json/application;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			String kv = vu.getKv();
			if (kv == null || !kv.matches("^\\d+-\\d+-\\d+$")) {
				json.put("errcode", -1);
				json.put("errmsg", "kv is invalid");
				return null;
			}

			String[] kvs = kv.split("-");
			int type = Integer.valueOf(kvs[0]);
			long sourceid = Long.valueOf(kvs[1]);
			long enid = Long.valueOf(kvs[2]);

			ServicerPj sp = servicerPjMgr.findServicerPj(type, enid);
			if (sp != null) {
				json.put("errcode", -2);
				json.put("errmsg", "您已经提交过评价了");
				return null;
			}

			pj.setOwner(this.getOwner());
			pj.setOname(this.getOname());
			pj.setWxuid(vu.getWxuid());
			pj.setType(type);
			pj.setSourceid(sourceid);
			pj.setEnid(enid);
			int res = servicerPjMgr.addServicerPj(vu, pj);
			if (res == -1) {
				json.put("errcode", -1);
				json.put("errmsg", "缺少功能页面");
			} else if (res == -2) {
				json.put("errcode", -2);
				json.put("errmsg", "pj save failed");
			} else {
				json.put("errcode", 0);
				json.put("errmsg", "ok");
				json.put("jf", res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.print(json);
			out.flush();
			out.close();
		}
		return null;
	}

	/**
	 * 服务评价详情页
	 * 
	 * @return
	 * @throws Exception
	 */
	public String pjDetail() throws Exception {
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if (vu == null) {
			return null;
		}
		JSONObject json = new JSONObject();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("json/application;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			String kv = vu.getKv();
			if (kv == null || !kv.matches("^\\d+-\\d+-\\d+$")) {
				json.put("errcode", -1);
				json.put("errmsg", "kv is invalid");
				return null;
			}
			String[] kvs = kv.split("-");
			int type = Integer.valueOf(kvs[0]);
			long enid = Long.valueOf(kvs[2]);

			ServicerPj pj = servicerPjMgr.findServicerPj(type, enid);
			ServicerPjPage pjp = servicerPjPageMgr.findServicerPjPage(this.getOwner(), type);
			json.put("errcode", 0);
			json.put("errmsg", "ok");
			json.put("pj", pj);
			json.put("pjp", pjp);
			json.put("kv", kv);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.print(json);
			out.flush();
			out.close();
		}
		return null;
	}

	/**
	 * 商家回复评价
	 * 
	 * @return
	 * @throws Exception
	 */
	public String pjReply() throws Exception {
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if (vu == null || pj == null) {
			return null;
		}
		JSONObject json = new JSONObject();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("json/application;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			String kv = vu.getKv();
			if (kv == null || !kv.matches("^\\d+-\\d+-\\d+$")) {
				json.put("errcode", -1);
				json.put("errmsg", "kv is invalid");
				return null;
			}
			String[] kvs = kv.split("-");
			int type = Integer.valueOf(kvs[0]);
			long sourceid = Long.valueOf(kvs[1]);
			long enid = Long.valueOf(kvs[2]);

			pj.setOwner(this.getOwner());
			pj.setOname(this.getOname());
			pj.setType(type);
			pj.setSourceid(sourceid);
			pj.setEnid(enid);
			int res = servicerPjMgr.updateDzcontent(vu, pj);
			if (res == -1) {
				json.put("errcode", -1);
				json.put("errmsg", "不是店主身份");
			} else if (res == -2) {
				json.put("errcode", -2);
				json.put("errmsg", "缺少功能页面");
			} else if (res == 0) {
				json.put("errcode", -3);
				json.put("errmsg", "save failed");
			} else {
				json.put("errcode", 0);
				json.put("errmsg", "ok");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.print(json);
			out.flush();
			out.close();
		}
		return null;

	}

	/**
	 * 服务者列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String pjServicers() throws Exception {
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if (vu == null) {
			return null;
		}
		JSONObject json = new JSONObject();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("json/application;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			String kv = vu.getKv();
			if (kv == null || !kv.matches("^\\d+-\\d+-\\d+$")) {
				json.put("errcode", -1);
				json.put("errmsg", "kv is invalid");
				return null;
			}
			String[] kvs = kv.split("-");
			int type = Integer.valueOf(kvs[0]);

			long owner = this.getOwner();
			int rows = 1000;
			int start = (pageId - 1) * rows;
			int total = servicerMgr.findServicerTotal(owner);
			List<YuYueServicer> list = servicerMgr.findServicerList(owner, start, rows);
			List<ServicerPjWd> wdList = servicerPjMgr.findServicerPjWdList(owner);
			ServicerPjPage pjp = servicerPjPageMgr.findServicerPjPage(owner, type);
			json.put("errcode", 0);
			json.put("errmsg", "ok");
			json.put("pageId", pageId);
			json.put("total", total);
			json.put("list", list);
			json.put("wdList", wdList);
			json.put("pjp", pjp);
			json.put("kv", kv);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.print(json);
			out.flush();
			out.close();
		}
		return null;
	}

	/**
	 * 评论列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String pjContents() throws Exception {
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if (vu == null) {
			return null;
		}
		JSONObject json = new JSONObject();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("json/application;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			String kv = vu.getKv();
			if (kv == null || !kv.matches("^\\d+-\\d+-\\d+(-\\d+)?$")) {
				json.put("errcode", -1);
				json.put("errmsg", "kv is invalid");
				return null;
			}
			String[] kvs = kv.split("-");
			int type = Integer.valueOf(kvs[0]);
			long srid = 0;
			if (kvs.length == 4) {
				srid = Long.valueOf(kvs[3]);
				kv = kvs[0] + "-" + kvs[1] + "-" + kvs[2];
			}

			long owner = this.getOwner();
			int rows = 10;
			int start = (pageId - 1) * rows;
			int total = servicerPjMgr.findServicerPjTotal(owner, srid);
			List<ServicerPj> list = servicerPjMgr.findServicerPjList(owner, srid, start, rows);
			ServicerPjPage pjp = servicerPjPageMgr.findServicerPjPage(owner, type);
			json.put("errcode", 0);
			json.put("errmsg", "ok");
			json.put("pageId", pageId);
			json.put("total", total);
			json.put("list", list);
			json.put("pjp", pjp);
			json.put("kv", kv);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.print(json);
			out.flush();
			out.close();
		}
		return null;
	}

	/**
	 * 评价维度列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@Permission(module = IPrivilegeConstants.MODULE_APP_服务评价, privilege = IPrivilegeConstants.PERMISSION_R)
	public String pjwd() throws Exception {
		setLightType(6);
		dto = (ServicerPjDto) servicerPjWdMgr.findServicerPjwdList(this.getOwner(), pageId);
		return SUCCESS;
	}

	public String savepjwd() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int res = servicerPjWdMgr.savepjwd(this.getOwner(), name);
		Gson gson = new Gson();
		out.print(gson.toJson(res));
		out.flush();
		out.close();
		return null;
	}

	public String findPjWdById() throws Exception {
		setLightType(6);
		servicerPjWd = servicerPjWdMgr.findPjWdById(pjwdid);
		return SUCCESS;
	}

	public String updatePjWdById() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int res = servicerPjWdMgr.updatePjWdById(name, pjwdid);
		Gson gson = new Gson();
		out.print(gson.toJson(res));
		out.flush();
		out.close();
		return null;
	}

	public String delPjWdById() throws Exception {
		setLightType(6);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int res = servicerPjWdMgr.delPjWdById(pjwdid);
		Gson gson = new Gson();
		out.print(gson.toJson(res));
		out.flush();
		out.close();
		return null;
	}

	public void setServicerMgr(IServicerMgr servicerMgr) {
		this.servicerMgr = servicerMgr;
	}

	public void setServicerPjMgr(IServicerPjMgr servicerPjMgr) {
		this.servicerPjMgr = servicerPjMgr;
	}

	public void setServicerPjPageMgr(IServicerPjPageMgr servicerPjPageMgr) {
		this.servicerPjPageMgr = servicerPjPageMgr;
	}

	public YuYueServicer getYuYueServicer() {
		return yuYueServicer;
	}

	public void setYuYueServicer(YuYueServicer yuYueServicer) {
		this.yuYueServicer = yuYueServicer;
	}

	public void setWxTemplateMgr(IWxTemplateMgr wxTemplateMgr) {
		this.wxTemplateMgr = wxTemplateMgr;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public ServicerPjDto getDto() {
		return dto;
	}

	public void setDto(ServicerPjDto dto) {
		this.dto = dto;
	}

	public long getCaid() {
		return caid;
	}

	public void setCaid(long caid) {
		this.caid = caid;
	}

	public long getSerid() {
		return serid;
	}

	public void setSerid(long serid) {
		this.serid = serid;
	}

	public int getMoveUp() {
		return moveUp;
	}

	public void setMoveUp(int moveUp) {
		this.moveUp = moveUp;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public WxTemplate getWt_f() {
		return wt_f;
	}

	public void setWt_f(WxTemplate wt_f) {
		this.wt_f = wt_f;
	}

	public WxTemplate getWt_r() {
		return wt_r;
	}

	public void setWt_r(WxTemplate wt_r) {
		this.wt_r = wt_r;
	}

	public ServicerPj getPj() {
		return pj;
	}

	public void setPj(ServicerPj pj) {
		this.pj = pj;
	}

	public List<Sitegroup> getSgList() {
		return sgList;
	}

	public void setSgList(List<Sitegroup> sgList) {
		this.sgList = sgList;
	}

	public long getSgid() {
		return sgid;
	}

	public void setSgid(long sgid) {
		this.sgid = sgid;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPjwdid() {
		return pjwdid;
	}

	public void setPjwdid(long pjwdid) {
		this.pjwdid = pjwdid;
	}

	public ServicerPjWd getServicerPjWd() {
		return servicerPjWd;
	}

	public void setServicerPjWd(ServicerPjWd servicerPjWd) {
		this.servicerPjWd = servicerPjWd;
	}

}
