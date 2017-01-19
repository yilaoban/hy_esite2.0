package com.huiyee.interact.exam.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.HttpRequestDeviceUtils;
import com.huiyee.interact.exam.dto.PingceResultDto;
import com.opensymphony.xwork2.ActionContext;

public class ExamResultShowAction extends AbstractAction{

	private static final long serialVersionUID = 3772715263942673122L;
	private PingceResultDto dto;
	private long relationid;
	private long rid;
	private long pcid;
	private String userAgent;
	private int cd;// 用来识别当前环境的;-1-默认环境cookie,0-微博,1-微信,2-用户名密码
	private Page page;
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String userA = request.getHeader("user-agent");
		userAgent = HttpRequestDeviceUtils.getMediaDevice(userA);
		VisitUser visit = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if(visit != null){
			cd = visit.getCd();
		}
		dto = (PingceResultDto)pageCompose.findExamResult(relationid,rid);
		page = dto.getPage(); 
		if(dto.getTc() != null){
			pcid = dto.getTc().getId();
			return SUCCESS;
		}
		return "error";
	}
	
	public long getRelationid()
	{
		return relationid;
	}
	public void setRelationid(long relationid)
	{
		this.relationid = relationid;
	}
	public long getPcid()
	{
		return pcid;
	}
	public void setPcid(long pcid)
	{
		this.pcid = pcid;
	}
	public String getUserAgent()
	{
		return userAgent;
	}
	public void setUserAgent(String userAgent)
	{
		this.userAgent = userAgent;
	}
	public int getCd()
	{
		return cd;
	}
	public void setCd(int cd)
	{
		this.cd = cd;
	}
	public Page getPage()
	{
		return page;
	}
	public void setPage(Page page)
	{
		this.page = page;
	}
	
	public long getRid()
	{
		return rid;
	}
	
	public void setRid(long rid)
	{
		this.rid = rid;
	}
	public PingceResultDto getDto()
	{
		return dto;
	}
}
