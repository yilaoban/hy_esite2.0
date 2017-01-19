package com.huiyee.interact.setting.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.mgr.IHyUserMgr;
import com.huiyee.esite.mgr.IWeiKaQuanMgr;
import com.huiyee.esite.mgr.IWeiXinMgr;
import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.model.OrderGoods;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.model.WxUser;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.esite.util.ToolUtils;
import com.huiyee.interact.setting.dto.CashierDto;
import com.huiyee.interact.setting.mgr.IHyUserPrintMgr;
import com.huiyee.interact.setting.mgr.IHyUserWayMgr;
import com.huiyee.interact.setting.model.HyUserPrint;
import com.huiyee.interact.setting.model.UWay;
import com.opensymphony.xwork2.ActionContext;

public class HyCashierAction extends AbstractAction {
	private static final long serialVersionUID = -1786427310069020338L;
	private IHyUserMgr hyUserMgr;
	private IWeiXinMgr weiXinMgr;
	private IHyUserWayMgr hyUserWayMgr;
	private IHyUserPrintMgr hyUserPrintMgr;
	private VelocityEngine velocityEngine;
	private IWeiKaQuanMgr wkqMgr;

	private CashierDto dto;
	private String username;
	private String password;
	private long id;
	private long endtime;
	private int status;
	private String hydesc;

	public void setHyUserWayMgr(IHyUserWayMgr hyUserWayMgr) {
		this.hyUserWayMgr = hyUserWayMgr;
	}

	public void setHyUserMgr(IHyUserMgr hyUserMgr) {
		this.hyUserMgr = hyUserMgr;
	}

	public void setWeiXinMgr(IWeiXinMgr weiXinMgr) {
		this.weiXinMgr = weiXinMgr;
	}

	public void setHyUserPrintMgr(IHyUserPrintMgr hyUserPrintMgr) {
		this.hyUserPrintMgr = hyUserPrintMgr;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public void setWkqMgr(IWeiKaQuanMgr wkqMgr) {
		this.wkqMgr = wkqMgr;
	}

	public String login() throws Exception {
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if (vu != null && vu.getHyUser() != null) {
			return "goscan";
		} else {
			return SUCCESS;
		}
	}

	public String dologin() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int result = 0;// 0 µÇÂ¼Ê§°Ü 1µÇÂ¼³É¹¦
		if (username == null || password == null) {
			return null;
		}
		HyUser hu = this.hyUserMgr.login(username, ToolUtils.getMD5Str(password), this.getOwner());
		if (hu != null) {
			boolean b = hyUserWayMgr.findHyUserIdentity(this.getOwner(), "t1", hu.getId());
			if (b) {
				result = 1;
				VisitUser vux = new VisitUser();
				vux.setOname(this.getOname());
				vux.setHyUser(hu);
				vux.setCd(2);
				if (hu.getWxuid() > 0) {
					WxUser wxUser = weiXinMgr.getWxUserById(hu.getWxuid());
					vux.setWxuid(wxUser.getId());
					vux.setWxUser(wxUser);
				}
				ActionContext.getContext().getSession().put("visitUser", vux);
			} else {
				result = -1;
			}
		}
		out.print(result);
		out.flush();
		out.close();
		return null;
	}

	public String scan() throws Exception {
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if (vu == null) {
			return "fail";
		}
		return SUCCESS;
	}

	public void findUserWay() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		dto = hyUserWayMgr.findWay(id, endtime, 0);
		Gson gson = new Gson();
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
	}

	public String showUserWay() throws Exception {
		dto = hyUserWayMgr.findWay(id, endtime, 1);

		HyUserPrint print = hyUserPrintMgr.findPrint(this.getOwner());
		if (print != null) {
			HyUser user = dto.getHyUser();
			UWay uw = dto.getUw();

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("logo", print.getLogo());
			if (user != null) {
				map.put("No", "No." + user.getId());
			}
			map.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Map<String, Object> x = new HashMap<String, Object>();
			x.put("name", hydesc);
			if (uw != null) {
				x.put("price", toYuan(uw.getRmb()));
			}
			list.add(x);
			map.put("list", list);
			map.put("url", print.getUrl());

			String vm = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/WEB-INF/velocity/print/" + print.getVm() + ".vm", "UTF-8", map);
			dto.setVm(vm);
		}
		return SUCCESS;
	}

	public String showOrderGoods() throws Exception {
		dto = (CashierDto) wkqMgr.findPayOrderGoods(id);

		HyUserPrint print = hyUserPrintMgr.findPrint(this.getOwner());
		if (print != null) {
			OrderGoods og = dto.getOg();

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("logo", print.getLogo());
			if (og != null && og.getPayApt() != null) {
				map.put("No", "No." + og.getPayApt().getHyuid());
			}
			map.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Map<String, Object> x = new HashMap<String, Object>();
			if (og != null) {
				x.put("name", og.getProductname());
				x.put("price", toYuan(og.getPrice()));
			}
			list.add(x);
			map.put("list", list);
			map.put("url", print.getUrl());

			String vm = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/WEB-INF/velocity/print/" + print.getVm() + ".vm", "UTF-8", map);
			dto.setVm(vm);
		}
		return SUCCESS;
	}

	public String exit() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("text/html; charset=utf-8");
		if (!HyConfig.isRun()) {
			ActionContext.getContext().getSession().remove("visitUser");
		}
		PrintWriter pw = response.getWriter();
		pw.print("Y");
		pw.flush();
		pw.close();
		return null;
	}

	public String userBill() throws Exception {
		return SUCCESS;
	}

	private static String toYuan(int feng) {
		BigDecimal bd_feng = new BigDecimal(feng);
		BigDecimal divisor = new BigDecimal(100);
		BigDecimal bd_yuan = bd_feng.divide(divisor, 2, BigDecimal.ROUND_HALF_UP);
		return bd_yuan.toString();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getEndtime() {
		return endtime;
	}

	public void setEndtime(long endtime) {
		this.endtime = endtime;
	}

	public CashierDto getDto() {
		return dto;
	}

	public void setDto(CashierDto dto) {
		this.dto = dto;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getHydesc() {
		return hydesc;
	}

	public void setHydesc(String hydesc) {
		this.hydesc = hydesc;
	}

}
