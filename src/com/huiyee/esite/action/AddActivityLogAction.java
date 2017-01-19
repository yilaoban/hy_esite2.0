package com.huiyee.esite.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.esite.util.HttpRequestDeviceUtils;

public class AddActivityLogAction extends AbstractAction{
	private static final long serialVersionUID = -2454847734374563740L;
	private long activityid;
	private Long uid ;
	private String source;
	private String type;
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String userAgent = request.getHeader("user-agent");
		if(uid == null){
			System.out.println("shareSub : uid is null");
			return null;
		}
        String ip = ClientUserIp.getIpAddr(request);
        long wbuid  = pageCompose.getWbuidByUserInfo(uid);
		long result = pageCompose.composeActivityLog(activityid,wbuid,type,ip,HttpRequestDeviceUtils.getMediaDevice(userAgent),source);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}

	public long getActivityid() {
		return activityid;
	}

	public void setActivityid(long activityid) {
		this.activityid = activityid;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}
