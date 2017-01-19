package com.huiyee.esite.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.mgr.IContentRecordMgr;
import com.huiyee.esite.model.ContentRecord;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.HttpRequestDeviceUtils;
import com.huiyee.esite.util.ClientUserIp;
import com.opensymphony.xwork2.ActionContext;


public class ContentRecordAction extends AbstractAction
{
	private static final long serialVersionUID = 2793114255080604295L;
	private IContentRecordMgr contentRecordMgr;
	private ContentRecord contentRecord;
	
	public void setContentRecordMgr(IContentRecordMgr contentRecordMgr)
	{
		this.contentRecordMgr = contentRecordMgr;
	}


	@Override
	public String execute() throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String userAgent = request.getHeader("user-agent");
		String ip = ClientUserIp.getIpAddr(request);
		String result = "N";
		VisitUser visit = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		int rs = contentRecordMgr.saveContentRecord(visit,contentRecord,ip, HttpRequestDeviceUtils.getMediaDevice(userAgent));
		if(rs > 0){
			result = "Y";
		}
		out.print(result);
		out.flush();
		out.close();
		return null;
	}

	
	public ContentRecord getContentRecord()
	{
		return contentRecord;
	}

	
	public void setContentRecord(ContentRecord contentRecord)
	{
		this.contentRecord = contentRecord;
	}
	
	
}
