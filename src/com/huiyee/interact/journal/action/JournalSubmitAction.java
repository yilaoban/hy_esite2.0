package com.huiyee.interact.journal.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.interact.journal.mgr.IJournalMgr;
import com.huiyee.interact.journal.util.HttpRequestDeviceUtils;
import com.huiyee.esite.util.ClientUserIp;

public class JournalSubmitAction  extends InteractModelAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2052231174893023885L;
	private long wbuid;
	private long pageid;
	private long contentid;
	private long cid;
	private String sharepic;
	private String sharecontent;
	private String source;
	
	private IJournalMgr journalMgr;
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		String ip = ClientUserIp.getIpAddr(request);
		String userAgent = request.getHeader("user-agent");
		int result = 0;
		try {
			result = journalMgr.saveJournalRecrod(pageid, wbuid, contentid, sharecontent, sharepic, ip, HttpRequestDeviceUtils.getMediaDevice(userAgent), source,cid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);			
		out.flush();
		out.close();
		return null;
	}

	public void setWbuid(long wbuid) {
		this.wbuid = wbuid;
	}

	public void setPageid(long pageid) {
		this.pageid = pageid;
	}

	public void setContentid(long contentid) {
		this.contentid = contentid;
	}

	public void setSharepic(String sharepic) {
		this.sharepic = sharepic;
	}

	public void setSharecontent(String sharecontent) {
		this.sharecontent = sharecontent;
	}

	public void setJournalMgr(IJournalMgr journalMgr) {
		this.journalMgr = journalMgr;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
