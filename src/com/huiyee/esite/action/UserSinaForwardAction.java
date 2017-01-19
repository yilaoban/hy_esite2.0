package com.huiyee.esite.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.esite.util.HttpRequestDeviceUtils;


public class UserSinaForwardAction extends AbstractAction {

	/**
	 * Î¢²©×ª·¢
	 */
	private static final long serialVersionUID = 8633701991025525627L;
	private long shareid;
	private long pageid;
	private String content;
	private Long uid ;
	private String source;
	private long wbid;
	private String gz;
	private String gzwbuid;
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String userAgent = request.getHeader("user-agent");
		if(uid == null){
			System.out.println("shareSub : uid is null");
			return null;
		}
		String ip = ClientUserIp.getIpAddr(request); 
		int result = pageCompose.composeSinaForward(wbid,uid,pageid, shareid, content, HttpRequestDeviceUtils.getMediaDevice(userAgent),source,ip,gz,gzwbuid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}

	public long getPageid() {
		return pageid;
	}

	public void setPageid(long pageid) {
		this.pageid = pageid;
	}

	public long getShareid() {
		return shareid;
	}

	public void setShareid(long shareid) {
		this.shareid = shareid;
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

	public long getWbid() {
		return wbid;
	}

	public void setWbid(long wbid) {
		this.wbid = wbid;
	}

	public String getGz() {
		return gz;
	}

	public void setGz(String gz) {
		this.gz = gz;
	}

	public void setGzwbuid(String gzwbuid) {
		this.gzwbuid = gzwbuid;
	}
	
}
