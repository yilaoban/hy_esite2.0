package com.huiyee.esite.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.esite.util.Oauth;
import com.huiyee.interact.spread.util.HttpRequestDeviceUtils;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

public class ShowPageAction2 extends AbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2525788214888472099L;
	private long appid = 1;// 申请的app在本地数据库的id
	private String source = "sna";
	private String signed_request;
	private long cid;// 商家wbuid
	private long wbuid;// 访客wbuid
	private long hyuid;// 访客uid
	private long pageid;
	private int cd = 0;
	private String refurl;
	private static Map<String, String> wbCahe = new HashMap<String, String>();
	private String ua;

	@Override
	public String execute() throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String agent = request.getHeader("User-Agent");
		String userAgent = HttpRequestDeviceUtils.getMediaDevice(agent);
		if (agent==null||agent.contains("Commons-HttpClient"))
		{
			// 新浪虫子;不需要记录
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			out.print(-1);
			out.flush();
			out.close();
			return null;
		}
		Oauth o = new Oauth();
		if (signed_request != null)
		{
			String secrect = pageCompose.findAppSecrectByAppid(appid);
			o.parseSignedRequest(signed_request, secrect == null ? "" : secrect);
			cid = Long.parseLong(o.ouid);
		}

		pageid = this.pageCompose.findPageIdForPageShow(cid, appid);
		if (pageid == 0)
		{
			switch (new Long(appid).intValue())
			{
			case 4:
				if ("C".equals(userAgent))
					return "appPage";
				else
					return "appPagePhone";
			default:
				if ("C".equals(userAgent))
					return "forward";
				else
					return "forwardPhone";

			}
		}
		try
		{
			wbuid = Long.parseLong(o.user_id);// 访问用户的wbuid
		}
		catch (RuntimeException e)
		{
			wbuid = 0;
		}
		try
		{
			if (wbuid > 0)
			{
					Page pa = pageCompose.findPageByid(pageid);
					hyuid = pageCompose.updateUidByViewer(wbuid, pa.getSiteid(), o.access_token, Long.parseLong(o.expires) * 1000, o.user);// 记录信息，
			}
//			else
//			{
//				if(u!=null)
//				ActionContext.getContext().getSession().remove("visitUser");
//			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		refurl = HyConfig.getPageyuming(this.getOwner()) + "/user/show/" + pageid + "/" + source +".html";
		VisitUser vu = new VisitUser();
		vu.setOname(this.getOname());
//		vu.setHyuid(hyuid);
		vu.setWbuid(wbuid);
		vu.setCid(cid);
		vu.setCd(0);
		ActionContext.getContext().getSession().put("visitUser", vu);
		return Action.SUCCESS;
	}

	public String weixin() throws Exception
	{
		pageid = 66;
		return SUCCESS;
	}

	public long getCid()
	{
		return cid;
	}

	public void setAppid(long appid)
	{
		this.appid = appid;
	}

	public static Map<String, String> getWbCahe()
	{
		return wbCahe;
	}

	public String getSigned_request()
	{
		return signed_request;
	}

	public void setSigned_request(String signed_request)
	{
		this.signed_request = signed_request;
	}

	public long getWbuid()
	{
		return wbuid;
	}

	public long getHyuid()
	{
		return hyuid;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setCid(long cid)
	{
		this.cid = cid;
	}

	public long getAppid()
	{
		return appid;
	}

	public String getUa()
	{
		return ua;
	}

	public void setUa(String ua)
	{
		this.ua = ua;
	}

	public int getCd()
	{
		return cd;
	}

	public String getSource()
	{
		return source;
	}

	public String getRefurl()
	{
		return refurl;
	}

	public void setRefurl(String refurl)
	{
		this.refurl = refurl;
	}

}
