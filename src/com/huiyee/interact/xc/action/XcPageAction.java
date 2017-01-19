package com.huiyee.interact.xc.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.esite.util.HyConfig;

public class XcPageAction extends InteractModelAction {
	private static final long serialVersionUID = 1L;
	private static Map<Long, Long> pageMap = new HashMap<Long, Long>();

	private long xcid;
	private long pageid;

	public String pageid() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");

		PrintWriter out;
		try {
			out = response.getWriter();
			Long pageid = pageMap.get(xcid);
			if (pageid == null) {
				out.print(new Gson().toJson(-2));
			} else {
				out.print(new Gson().toJson(pageid));
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public String cachePageid() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");

		HttpServletRequest request = ServletActionContext.getRequest();
		PrintWriter out;
		try {
			out = response.getWriter();
			String ip = ClientUserIp.getIpAddr(request);
			if (HyConfig.getAllowClearIp().contains(ip)) {
				pageMap.put(xcid, pageid);
				out.print(new Gson().toJson(1));
			} else {
				out.print(new Gson().toJson(-1));
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public long getXcid() {
		return xcid;
	}

	public void setXcid(long xcid) {
		this.xcid = xcid;
	}

	public void setPageid(long pageid) {
		this.pageid = pageid;
	}

	public long getPageid() {
		return pageid;
	}

}
