package com.huiyee.weixin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.dto.VisitLogDto;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.CacheUtil;
import com.huiyee.esite.util.ClientUserIp;
import com.opensymphony.xwork2.ActionContext;

public class WeixinEventLogAction extends AbstractAction {

	/**
	 * 记录分享事件和外部事件的日志
	 */
	private static final long serialVersionUID = 1L;

/**
 * 分享事件
 * @return
 * @throws Exception
 */
	
	public String ashare() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest httpRequest = ServletActionContext.getRequest();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser vux = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if(vux == null || vux.getWxUser() == null){
			out.print(-1);
			out.flush();
			out.close();
			return null;
		}
		try
		{
			String ip = ClientUserIp.getIpAddr(httpRequest);
			String uagent = httpRequest.getHeader("user-agent");
			VisitLogDto vl = new VisitLogDto();
			vl.setCreatetime(System.currentTimeMillis());
			vl.setIp(ip);
			vl.setUagent(uagent);
			vl.setUrl(httpRequest.getServletPath() + "?" + httpRequest.getQueryString());
			vl.setCookie(vux.getCookieUser().getCookie());
			vl.setMtype(vux.getCd());
			vl.setPageid(vux.getPageid());
			vl.setSource(vux.getSource());
			vl.setAdesc("分享");
			vl.setAtype("s");
			vl.setAoid(aoid);//分享快照的id
			vl.setOpenid(vux.getWxUser().getOpenid());
			vl.setWxuid(vux.getWxUser().getId());
			vl.setGz(vux.getWxUser().getSubscribe());
			if(vux.getHyUser()!=null)
			{
				vl.setHyuid(vux.getHyUser().getId());
			}
			CacheUtil.addVisitLog(vl);
		} catch (Exception e)
		{
			e.printStackTrace();
			out.print(-1);
			out.flush();
			out.close();
			return null;
		}
		out.print(1);
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 外部事件
	 * source格式为：
     * wxn-p-123-s-11-hy-v-124-c-12
     * v-访问的页面
     * c-点击者
	 * @return
	 * @throws Exception
	 */
	
	private String source;
	private String adesc;
	private long aoid;
	private String ip;
	
	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public void setAdesc(String adesc)
	{
		this.adesc = adesc;
	}
	
	public void setAoid(long aoid)
	{
		this.aoid = aoid;
	}

	public String asub() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try
		{
			VisitLogDto vl = new VisitLogDto();
			vl.setCreatetime(System.currentTimeMillis());
			vl.setIp(ip);
			vl.setMtype(2);
			vl.setSource(source);
			vl.setAoid(aoid);
			vl.setWbuid(this.getOwner());
			vl.setAdesc(adesc);
			vl.setAtype("w");
			CacheUtil.addVisitLog(vl);
		} catch (Exception e)
		{
			e.printStackTrace();
			out.print(-1);
			out.flush();
			out.close();
			return null;
		}
		out.print(1);
		out.flush();
		out.close();
		return null;
	}
	
}
