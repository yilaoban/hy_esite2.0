
package com.huiyee.esite.action;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.model.WeiXinPage;
import com.huiyee.esite.model.WxUser;
import com.huiyee.esite.util.CacheUtil;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.esite.util.WeixinOauth;
import com.opensymphony.xwork2.ActionContext;

public class WXOAuthAction extends AbstractAction
{

	private static final long serialVersionUID = 1L;

	private String code;
	private String state;
	private String appid;
	private String source = "wxn";
	private long pageid;
	private int cd = 1;
	private String refurl;// 用来失败后,重新返回授权的地址
	private String kv;


	@Override
	public String execute() throws Exception
	{
		if (state == null)
		{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/json;charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			out.print("网络连接失败,请重新进入!");
			out.flush();
			out.close();
			return null;
		}
		if (code == null)
		{
			System.out.println("code为：null");
			refurl = HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl() + "/user/wxshow/" + pageid + "/" + source + ".html";
			if(StringUtils.isNotBlank(kv))
			{
		    refurl = HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl() + "/user/wxshow/" + pageid + "/" + source +"/" +kv+".html";
			}
			return SUCCESS;
		}
		String rs=SUCCESS;
		try
		{
			pageid = Long.valueOf(state);
		} catch (Exception e)
		{
			rs = "success4jsp";
		}
		WeiXinPage wx = (WeiXinPage) ActionContext.getContext().getSession().get("wxp");
		if (wx == null)
		{
			wx = this.pageCompose.findWXPageByPageid(pageid,this.getOwner());
		}
		if (wx == null||wx.getWxMp()==null||this.getOwner()==0)
		{
			System.out.println("返回按钮！关闭浏览窗口！");
			return "errclose";
		}
		WxUser wu = WeixinOauth.wxOauth(wx.getWxMp(), code);
		if (wu != null && wu.getOpenid() != null)
		{
			boolean bl = false;
			if (wu.getNickname() == null)
			{
				bl = true;
			}
			wu.setMpid(wx.getWxMp().getId());
			WxUser wuu = pageCompose.addWXUser(wu);
			wuu.setAppnickname(wx.getWxMp().getNick_name());// 用来抽奖提示关注哪个公众号才行
			VisitUser vu = new VisitUser();
			vu.setOname(this.getOname());
			vu.setWxuid(wuu.getId());
			vu.setWxUser(wuu);
			vu.setSource(source, pageid);
			vu.setKv(kv);
			vu.setCd(1);
			HyUser bb = pageCompose.saveHyUserByWxuid(vu.getWxuid(), this.getOwner());
			vu.setHyUser(bb);
			long iu = System.currentTimeMillis();
			if (wuu.getUpdatetime() != null)
			{
				iu = System.currentTimeMillis() - wuu.getUpdatetime().getTime();
			}
			if (wx.getInfoed().toUpperCase().equals("Y") && bl && iu > wx.getUpdateseconds() * 1000)
			{
				CacheUtil.wxmpso.remove(this.getOwner());
				CacheUtil.wxmps.remove(wx.getWxMp().getId());
				vu.setUif(1);// 需要重新授权得信息
				refurl = HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl() + "/user/wxshow/" + pageid + "/" + source + ".html";
				if(StringUtils.isNotBlank(kv))
				{
			    refurl = HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl() + "/user/wxshow/" + pageid + "/" + source +"/" +kv+".html";
				}
				rs=SUCCESS;
			} else
			{
				refurl = HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl() + "/user/show/" + pageid + "/" + source + ".html";
				if(StringUtils.isNotBlank(kv))
				{
					refurl = HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl() + "/user/show/" + pageid + "/" + source +"/" +kv+".html";
				}
			}

			ServletActionContext.getRequest().getSession().setAttribute("visitUser", vu);

		} else
		{
			System.out.println(new Date()+"==="+ServletActionContext.getRequest().getRequestURL()+ServletActionContext.getRequest().getQueryString());
			System.out.println("第三方应用过期时间："+new Date(wx.getWxMp().getThird_expires_in())+"授权过期时间："+new Date(wx.getWxMp().getAu_expires_in())+"==清除缓存==="+wx.getWxMp().getOwner()+"=="+this.getOwner()+"=="+code+"==="+wx.getWxMp().getAu_access_token());
			CacheUtil.wxmpso.remove(wx.getWxMp().getOwner());
			CacheUtil.wxmps.remove(wx.getWxMp().getId());
			refurl = HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl() + "/user/wxshow/" + pageid + "/" + source + ".html";
			if(StringUtils.isNotBlank(kv))
			{
				refurl = HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl() + "/user/wxshow/" + pageid + "/" + source +"/" +kv+".html";
			}
			rs=SUCCESS;
		}
		return rs;
	}

	
	public String getKv()
	{
		return kv;
	}
	
	public void setKv(String kv)
	{
		this.kv = kv;
	}
	
	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getAppid()
	{
		return appid;
	}

	public void setAppid(String appid)
	{
		this.appid = appid;
	}

	public String getSource()
	{
		return source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public int getCd()
	{
		return cd;
	}

	public void setCd(int cd)
	{
		this.cd = cd;
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
