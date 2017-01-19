package com.huiyee.interact.cb.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.interact.cb.dto.CbRsDto;
import com.huiyee.interact.cb.dto.HbRecordDto;
import com.huiyee.interact.cb.mgr.IHbRecordMgr;
import com.huiyee.interact.cb.model.CbSender;
import com.opensymphony.xwork2.ActionContext;

public class HbRecordAction extends AbstractCbUserAction
{
	private static final long serialVersionUID = 2934258499501497789L;
	
	private HbRecordDto dto;
	private IHbRecordMgr hbRecordMgr;
	private int pageId = 1;
	private int num;//提现的金额
	
	public void setNum(int num)
	{
		this.num = num;
	}

	public void setHbRecordMgr(IHbRecordMgr hbRecordMgr)
	{
		this.hbRecordMgr = hbRecordMgr;
	}
	
	public String tixian() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setHeader("cache-control", "no-cache");
		response.setContentType("application/json; charset=utf-8");
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		String ip = ClientUserIp.getIpAddr(request);
		String userA = request.getHeader("user-agent");
		//CbSender cs = (CbSender) vu.getAppUsers().get("cb");
		CbRsDto rs=new CbRsDto();
//		if(cs != null)
//		{
//			CbSender cbs=hbRecordMgr.findHbSender(cs.getId());
//			if(cbs.getHbtotal()>cbs.getHbused())
//			{
//				hbRecordMgr.saveHbRecord(cs.getId(), num, ip, proAgent(userA), this.getOwner(), vu.getWxUser().getOpenid(), vu.getWxUser().getMpid());
//				rs.setStatus(1);
//			}else
//			{
//				rs.setRs("提现金额不足!");
//				rs.setStatus(-3);
//			}
//		}
		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		pw.print(gson.toJson(rs));
		pw.flush();
		pw.close();
		return null;
	}
	

	public String findHbRecordList() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("cache-control", "no-cache");
		response.setContentType("application/json; charset=utf-8");
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if(vu != null){
			//CbSender cs = (CbSender) vu.getAppUsers().get("cb");
//			if(cs != null){
//				dto = hbRecordMgr.findHbRecordList(cs.getId(),pageId);
//				dto.setStatus(1);
//			}
		}
		if(dto == null){
			dto = new HbRecordDto();
		}
		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		pw.print(gson.toJson(dto));
		pw.flush();
		pw.close();
		return null;
	}
	
	private String proAgent(String uagent)
	{
		String ag = "phone";
		if (uagent.contains("windows"))
		{
			ag = "pc";
		} else if (uagent.contains("iphone"))
		{
			ag = "iphone";
		} else if (uagent.contains("ipad"))
		{
			ag = "iphone";
		} else if (uagent.contains("ipod"))
		{
			ag = "iphone";
		} else if (uagent.contains("linux"))
		{
			ag = "android";
			String[] strs = uagent.split("build");
			if (strs.length > 1)
			{
				String[] strs2 = strs[0].split(";");
				ag = strs2[strs2.length - 1].trim();
			}
		}
		return ag;
	}
	
}
