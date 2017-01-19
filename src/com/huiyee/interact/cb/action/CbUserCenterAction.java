package com.huiyee.interact.cb.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.mgr.IWeiXinMgr;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.appointment.model.AppointmentDataModel;
import com.huiyee.interact.cb.dto.HbRecordDto;
import com.huiyee.interact.cb.mgr.ICbUserCenterMgr;
import com.huiyee.interact.cb.model.CbSender;
import com.huiyee.weixin.model.WxMp;
import com.opensymphony.xwork2.ActionContext;

/**
 * 个人中心
 * @author Administrator
 *
 */
public class CbUserCenterAction extends AbstractCbUserAction{

	private static final long serialVersionUID = -7601810876205506576L;
	private ICbUserCenterMgr cbUserCenterMgr;
	private IWeiXinMgr weiXinMgr;
	private CbSender cbSender;
	private HbRecordDto dto;
	private long pageid;
	private String source;
	private AppointmentDataModel aptRecord; 
	private int rmb;
	private long fatherid;
	
	public void setWeiXinMgr(IWeiXinMgr weiXinMgr) {
		this.weiXinMgr = weiXinMgr;
	}

	public void setCbUserCenterMgr(ICbUserCenterMgr cbUserCenterMgr) {
		this.cbUserCenterMgr = cbUserCenterMgr;
	}
	
	@Override
	public String execute() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if(vu != null){
			dto = cbUserCenterMgr.findCbSenderByHyuid(vu.getHyUserId(),this.getOwner());
		}
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 提现
	 */
	public String cbtixian() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		int res = 0;
		if(vu != null){
			WxMp mp = weiXinMgr.updateFindWxMp(this.getOwner(), 0);
			res = cbUserCenterMgr.saveCbHongbaoSend(this.getOwner(),vu.getHyUserId(),vu.getWxUser().getOpenid(),mp.getId(),rmb);
		}
		out.print(gson.toJson(res));
		out.flush();
		out.close();
		return null;
	}
	
	
	/**
	 * 提现明细
	 * @return
	 * @throws Exception
	 */
	public String tixianRecord() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if(vu != null){
			 dto = cbUserCenterMgr.findHbCbSendByHyuid(vu.getHyUserId());
		}
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 我的伙伴
	 * @return
	 */
	public String mySender() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if(vu != null){
			dto = cbUserCenterMgr.findMySender(vu.getHyUserId(),pageid,this.getOwner());
		}
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}
	
	
	/**
	 * 实时排行
	 * @return
	 */
	public String senderSequence() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if(vu != null){
			dto = cbUserCenterMgr.findSenderlist(this.getOwner());
		}
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}
	
	
	public String myHuobi() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if(vu != null){
			dto = cbUserCenterMgr.findMyHuobi(vu.getHyUserId(),this.getOwner());
		}
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}
	
	public String inviteBuddy()throws Exception{
		// for ashare function
		VisitUser vu = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
		if (vu != null) {
			vu.setSource(source, pageid);
		}
		return SUCCESS;
	}
	
	public CbSender getCbSender() {
		return cbSender;
	}

	public void setCbSender(CbSender cbSender) {
		this.cbSender = cbSender;
	}

	public HbRecordDto getDto() {
		return dto;
	}

	public void setDto(HbRecordDto dto) {
		this.dto = dto;
	}

	public long getPageid() {
		return pageid;
	}

	public void setPageid(long pageid) {
		this.pageid = pageid;
	}

	public AppointmentDataModel getAptRecord() {
		return aptRecord;
	}

	public void setAptRecord(AppointmentDataModel aptRecord) {
		this.aptRecord = aptRecord;
	}

	public int getRmb() {
		return rmb;
	}

	public void setRmb(int rmb) {
		this.rmb = rmb;
	}

	public long getFatherid() {
		return fatherid;
	}

	public void setFatherid(long fatherid) {
		this.fatherid = fatherid;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
