
package com.huiyee.esite.action;

import java.net.URLEncoder;
import org.apache.commons.lang3.StringUtils;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.model.WeiXinPage;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.weixin.model.WxMp;
import com.opensymphony.xwork2.ActionContext;

public class WXShowPageAction extends AbstractAction
{

	private static final long serialVersionUID = 1L;
	private long id;// wxshowid
	private String refurl;
	private String source;
	private String from;
	private String isappinstalled;
	private String kv = "";
	private long pageid;
	private String jsp;//跳转的jsp名称

	@Override
	public String execute() throws Exception
	{
		String refurlh = HyConfig.getPageyuming1() + this.getOnameUrl() + "/user/wxoauth/" + source + ".html";
		if (StringUtils.isNotBlank(kv))
		{
			refurlh = HyConfig.getPageyuming1() + this.getOnameUrl() + "/user/wxoauth/" + source + "/" + kv + ".html";
		}
		WeiXinPage wx = null;
		String sta=pageid+"";
		if (pageid > 0)
		{
			wx = this.pageCompose.findWXPageByPageid(pageid, this.getOwner());
		}else if(StringUtils.isNotBlank(jsp))
		{
			wx = this.pageCompose.findWXPageByPageid(0, this.getOwner());
			if(jsp.startsWith("ui"))
			{
				wx.setInfoed("Y");	
			}
			if(jsp.startsWith("uu"))
			{
				return "success4jsp";
			}
			sta=jsp;
		}
		else if(id>0)
		{
			wx = this.pageCompose.findWXPage(id,this.getOwner());
			pageid = wx.getPageid();
		}
		if (wx == null || wx.getWxMp() == null||this.getOwner()==0)
		{
			System.out.println("第一次，返回按钮！关闭浏览窗口！");
			return "errclose";
		}
		ActionContext.getContext().getSession().put("wxp", wx);
		String ot = "snsapi_base";
		VisitUser vus = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if (vus != null )
		{
			if (vus.getWxUser() != null&&vus.getUif() == 1)
			{
				vus.setUif(0);
				ot = "snsapi_userinfo";
			}
             if (vus.getCd()==2)//用户名密码登录不跳转微信授权
			{
				if (pageid > 0)
				{
					vus.setSource(source, pageid);
					vus.setKv(kv);
					refurl = HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl() + "/user/show/" + pageid + "/" + source + ".html";
					if (StringUtils.isNotBlank(kv))
					{
						refurl = HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl() + "/user/show/" + pageid + "/" + source + "/" + kv + ".html";
					}
					return SUCCESS;
				} else
				{
					//System.out.println("pageid为0，默认访问jsp名称，jsp没有只则会报错！");
					return "success4jsp";// 直接访问传过来的jsp
				}
			}

		}
		WxMp mp = wx.getWxMp();
		if (mp.getVerify_type_info() == -1 || mp.getService_type_info() != 2)
		{
			if (vus != null)
			{
				vus.setSource(source, wx.getPageid());
				vus.setKv(kv);
			}
			refurl = HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl() + "/user/show/" + pageid + "/" + source + ".html";
			if (StringUtils.isNotBlank(kv))
			{
				refurl = HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl() + "/user/show/" + pageid + "/" + source + "/" + kv + ".html";
			}
			return SUCCESS;
		}
		if (mp.getAppsecret() == null)
		{
			refurl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + mp.getAppid() + "&redirect_uri=" + URLEncoder.encode(refurlh, "utf-8")
					+ "&response_type=code&scope=" + ot + "&state=" + sta + "&component_appid=" + mp.getThird_appid() + "#wechat_redirect";
		} else
		{
			refurl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + mp.getAppid() + "&redirect_uri=" + URLEncoder.encode(refurlh, "utf-8")
					+ "&response_type=code&scope=" + ot + "&state=" + sta + "#wechat_redirect";

		}
		return SUCCESS;
	}

	
	public String getJsp()
	{
		return jsp;
	}
	
	public void setJsp(String jsp)
	{
		this.jsp = jsp;
	}
	
	public String getSource()
	{
		return source;
	}
	
	public String getKv()
	{
		return kv;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getRefurl()
	{
		return refurl;
	}

	public void setKv(String kv)
	{
		this.kv = kv;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public String getFrom()
	{
		return from;
	}

	public void setFrom(String from)
	{
		this.from = from;
	}

	public String getIsappinstalled()
	{
		return isappinstalled;
	}

	public void setIsappinstalled(String isappinstalled)
	{
		this.isappinstalled = isappinstalled;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

}
