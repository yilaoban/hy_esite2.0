package com.huiyee.esite.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.dto.UserOauthDto;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.esite.util.HttpRequestDeviceUtils;


public class UserOauthAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4244769537187402603L;
	private String code;
	private String refUrl;
	private String state;
	private String type="S";

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String execute() throws Exception {
		String[] strs = state.split(",");
		long entityid = Long.valueOf(strs[0]);
		long appid = Long.valueOf(strs[1]);
		String oldurl = strs[2];
		long shareid = Long.valueOf(strs[3]);
		long pageid = Long.parseLong(strs[4]);
		String source = strs[5];
		if("null".equals(source)){
		    source=null;
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		String userAgent = request.getHeader("user-agent");
		String ip = ClientUserIp.getIpAddr(request); 
		UserOauthDto dto = (UserOauthDto) pageCompose.userOauthByCode(appid, code, entityid, ip ,HttpRequestDeviceUtils.getMediaDevice(userAgent),shareid,pageid,source);
		refUrl = dto.getRefUrl();
//		if (!oldurl.trim().equals("null")) {
//			//³É¹¦ÊÚÈ¨
//			refUrl = oldurl;
//		}
		System.out.println("new oldurl:"+refUrl);
		if(code != null){
			request.getSession().setAttribute("type", type);
		}
		return SUCCESS;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRefUrl() {
		return refUrl;
	}
}
