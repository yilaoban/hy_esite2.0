package com.huiyee.interact.setting.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.interact.offcheck.dto.CheckRs;
import com.huiyee.interact.setting.dto.CashierRs;
import com.huiyee.interact.setting.mgr.IHyUserWayMgr;
import com.huiyee.interact.setting.mgr.IHyUserXfDescMgr;
import com.huiyee.interact.setting.model.HyUserXfDesc;
import com.huiyee.interact.setting.model.UWay;
import com.opensymphony.xwork2.ActionContext;

/**
 * 收银员action
 * @author Administrator
 *
 */
public class CashierAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private IHyUserWayMgr hyUserWayMgr;
	private IHyUserXfDescMgr hyUserXfDescMgr;
	private int rmb;
	private String key;
	private String hydesc;
	private long uwayid;
	private long xfid;//消费备注id
	private long levelid;
	public void setHyUserXfDescMgr(IHyUserXfDescMgr hyUserXfDescMgr) {
		this.hyUserXfDescMgr = hyUserXfDescMgr;
	}


	public void setHydesc(String hydesc)
	{
		this.hydesc = hydesc;
	}


	public void setRmb(int rmb)
	{
		this.rmb = rmb;
	}

	
	public void setKey(String key)
	{
		this.key = key;
	}

	public void setHyUserWayMgr(IHyUserWayMgr hyUserWayMgr)
	{
		this.hyUserWayMgr = hyUserWayMgr;
	}

	/**
	 * 获取动态支付二维码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String uway() throws Exception {
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		if (HyConfig.isRun()) {
			CheckRs rs = new CheckRs();
			rs.setStatus(-10004);
			rs.setHydesc("请用前台地址！");
			Gson gson = new Gson();
			out.print(gson.toJson(rs));
			out.flush();
			out.close();
			return null;
		}
		String	url = HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl();
		CashierRs rs = hyUserWayMgr.updateUserWay(vu, this.getOwner(),url);
		Gson gson = new Gson();
		out.print(gson.toJson(rs));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 扫描枪或者微信端扫描
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sm() throws Exception {
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		if (HyConfig.isRun()) {
			CheckRs rs = new CheckRs();
			rs.setStatus(-10004);
			rs.setHydesc("请用前台地址！");
			Gson gson = new Gson();
			out.print(gson.toJson(rs));
			out.flush();
			out.close();
			return null;
		}
		HyUserXfDesc xfdesc =  hyUserXfDescMgr.findXfdescByXfid(xfid);
		if(xfdesc != null){
			hydesc = xfdesc.getName();
		}
		String	url = HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl();
		String strs[]=key.split("-");
		CashierRs rs = hyUserWayMgr.updateWayStatus(vu, this.getOwner(), url, rmb, Long.valueOf(strs[0]), Long.valueOf(strs[1]),hydesc,xfid);
		Gson gson = new Gson();
		out.print(gson.toJson(rs));
		out.flush();
		out.close();
		return null;
	}
	
	
	public String checkuwayused() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		UWay uWay = hyUserWayMgr.findUWayById(uwayid);
		if(uWay != null){
			out.print(uWay.getStatus());
		}else{
			out.print(0);
		}
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 展示折扣价
	 * @throws Exception
	 */
	public void showzkrmb() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		rmb = hyUserXfDescMgr.findzkrmbByXfidAndLevelid(this.getOwner(), xfid,levelid,rmb);
		out.print(gson.toJson(rmb));
		out.flush();
		out.close();
	}
	
	public long getUwayid() {
		return uwayid;
	}


	public void setUwayid(long uwayid) {
		this.uwayid = uwayid;
	}


	public long getXfid() {
		return xfid;
	}


	public void setXfid(long xfid) {
		this.xfid = xfid;
	}


	public long getLevelid() {
		return levelid;
	}


	public void setLevelid(long levelid) {
		this.levelid = levelid;
	}

}
