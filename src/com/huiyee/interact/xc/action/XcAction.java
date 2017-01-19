package com.huiyee.interact.xc.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.model.WxUser;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.esite.util.HttpRequestDeviceUtils;
import com.huiyee.interact.xc.model.XcRecord;
import com.huiyee.interact.xc.service.IXcService;
import com.opensymphony.xwork2.ActionContext;

public class XcAction extends InteractModelAction {
	private static final long serialVersionUID = 1L;
	private static Map<Long, String> xcids = new HashMap<Long, String>();

	private IXcService xcService;
	private long xcid;

	@Override
	public String execute() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();

		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = ClientUserIp.getIpAddr(request);

		String userAgent = request.getHeader("user-agent");
		String device = HttpRequestDeviceUtils.getMediaDevice(userAgent);

		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		// 0-活动未开始，-1-不在活动时间，-2-活动已结束，-3-用户为null，-4-用户id为0，-5-用户不符合活动要求
		int result = xcService.process(xcid, vu, ip, device);
		out.print(new Gson().toJson(result));

		out.flush();
		out.close();
		return null;
	}

	public String start() throws Exception {
		synchronized (getXcid(xcid)) {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/json;charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();

			// -1-没有此活动，0-活动未开始，1-活动开始，2-活动已结束
			int result = xcService.start(xcid);
			out.print(new Gson().toJson(result));

			out.flush();
			out.close();
		}
		return null;
	}

	public String getResult() throws Exception {
		synchronized (getXcid(xcid)) {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/json;charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();

			List<XcRecord> records = xcService.getResult(xcid);
			List<WxUser> results = xcService.getWxUser(records);
			Gson gson = new Gson();
			out.print(gson.toJson(results));

			out.flush();
			out.close();

			xcService.saveRecords(xcid);
			xcService.removeCache(xcid);
		}
		return null;
	}

	private static String getXcid(long xcid) {
		String str = xcids.get(xcid);
		if (str == null) {
			str = new String("");
			xcids.put(xcid, str);
		}
		return str;
	}

	public IXcService getXcService() {
		return xcService;
	}

	public void setXcService(IXcService xcService) {
		this.xcService = xcService;
	}

	public long getXcid() {
		return xcid;
	}

	public void setXcid(long xcid) {
		this.xcid = xcid;
	}

}
