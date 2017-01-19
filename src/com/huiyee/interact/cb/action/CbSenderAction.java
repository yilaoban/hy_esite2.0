package com.huiyee.interact.cb.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.appointment.model.AppointmentDataModel;
import com.huiyee.interact.cb.mgr.ICbDataMgr;
import com.huiyee.interact.cb.mgr.ICbUserCenterMgr;
import com.opensymphony.xwork2.ActionContext;

public class CbSenderAction extends AbstractAction{
	private static final long serialVersionUID = 7426933069788055418L;
	private ICbUserCenterMgr cbUserCenterMgr;
	private ICbDataMgr cbDataMgr;
	private AppointmentDataModel aptRecord; 
	private long fatherid;
	
	public void setCbDataMgr(ICbDataMgr cbDataMgr) {
		this.cbDataMgr = cbDataMgr;
	}

	public void setCbUserCenterMgr(ICbUserCenterMgr cbUserCenterMgr) {
		this.cbUserCenterMgr = cbUserCenterMgr;
	}

	public void findSender() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		int res = 0;
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if(vu != null){
			 res = cbDataMgr.findSender(vu.getHyUserId());
		}
		out.print(gson.toJson(res));
		out.flush();
		out.close();
	}
	
	public String cbSenderAptRecord() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		int res = 0;
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if(vu != null){
			res = cbUserCenterMgr.saveCbSenderAndRecord(vu.getHyUserId(),this.getOwner(),vu.getWxUser().getId(),aptRecord,fatherid);
		}
		out.print(gson.toJson(res));
		out.flush();
		out.close();
		return null;
	}

	public AppointmentDataModel getAptRecord() {
		return aptRecord;
	}

	public void setAptRecord(AppointmentDataModel aptRecord) {
		this.aptRecord = aptRecord;
	}

	public long getFatherid() {
		return fatherid;
	}

	public void setFatherid(long fatherid) {
		this.fatherid = fatherid;
	}
	
	
}
