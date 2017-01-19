
package com.huiyee.esite.model;

import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;
import com.huiyee.interact.bbs.model.BBSUser;

public class VisitUser implements Serializable
{

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private long cid;// 当前访问的官微的id
	private String oname;// owner 的会员名
	private long wbuid;// 微博uid
	private long wxuid;// 微信openid
	private String source;// 访问来源
	private long pageid;// 到访的pageid每次加载都会重新set
	private String oldSource;// 上次访问的source
	private long oldPageid;// 上次访问的pageid
	private WxUser wxUser;
	private BBSUser bbsUser;
	private int cd = -1;// 用来识别当前环境的;-1-默认环境cookie,0-微博,1-微信,2-用户名密码
	private int uif;// 用来识别是不是需要获取微信用户信息;0-不用授权,1-代表授权
	private HyUser hyUser;
	private CookieUser cookieUser;
	private String skey = "";// ct-产品目录,cp-图片目录，cv-视频目录，cn-新闻目录，n-新闻详情.(比如ct-hy-123,除了新闻)
	private String svalue = "0";// skey的值
	private String kv = "";

	public String getKv()
	{
		return kv;
	}

	public void setKv(String kv)
	{
		if(kv==null)
		{
			this.kv ="kv";
			this.skey=kv;
			this.svalue=null;
			return;
		}
		if (kv.contains("-hy-"))
		{
			String[] strs = kv.split("-hy-");
			this.skey = strs[0];
			this.svalue = strs[strs.length-1];
		} else if (kv.startsWith("n-"))
		{
			String[] strs = kv.split("-");
			this.skey = strs[0];
			this.svalue = strs[strs.length-1];
		}
		this.kv = kv;
	}

	public String getOname()
	{
		return oname;
	}

	public void setOname(String oname)
	{
		this.oname = oname;
	}

	public String getSkey()
	{
		return skey;
	}

	public void setSkey(String skey)
	{
		this.skey = skey;
	}

	public String getSvalue()
	{
		return svalue;
	}

	public void setSvalue(String svalue)
	{
		this.svalue = svalue;
	}

	public long getHyUserId()
	{
		if (hyUser != null)
		{
			return hyUser.getId();
		}
		return 0;
	}

	public long getUid()
	{
		if (cd == -1)
		{
			if (cookieUser != null)
			{
				return cookieUser.getId();
			}
		} else if (cd == 0)
		{
			return wbuid;
		} else if (cd == 1)
		{
			return wxuid;
		} else if (cd == 2)
		{
			if (hyUser != null)
			{
				return hyUser.getId();
			}
		}
		return 0;
	}

	public long getWbuid()
	{
		return wbuid;
	}

	public void setWbuid(long wbuid)
	{
		this.wbuid = wbuid;
	}

	public long getWxuid()
	{
		return wxuid;
	}

	public void setWxuid(long wxuid)
	{
		this.wxuid = wxuid;
	}

	public String getSource()
	{
		if(StringUtils.isBlank(source)){
			return "pcn";
		}
		return source;
	}

	public void setSource(String source, long pageid)
	{
		// 只有source被主动换了之后old才置换，pageid只要点击就会换，所以pageid不需要置换old。
		if (StringUtils.isNotBlank(source))
		{
			if (this.source != null && !this.source.equals(source))
			{
				this.oldSource = this.source;
				if (pageid > 0)
				{
					this.oldPageid = this.pageid;
				}
			} 
			if (this.source == null || source.contains("-p-") || source.startsWith("t-"))
			{
				this.source = source;
			}
		}
		if (pageid > 0)
		{
			this.pageid = pageid;
		}
	}

	public long getCid()
	{
		return cid;
	}

	public void setCid(long cid)
	{
		this.cid = cid;
	}

	public long getPageid()
	{
		return pageid;
	}

	public WxUser getWxUser()
	{
		return wxUser;
	}

	public void setWxUser(WxUser wxUser)
	{
		this.wxUser = wxUser;
	}

	public int getCd()
	{
		return cd;
	}

	public void setCd(int cd)
	{
		this.cd = cd;
	}

	public int getUif()
	{
		return uif;
	}

	public void setUif(int uif)
	{
		this.uif = uif;
	}

	public BBSUser getBbsUser()
	{
		return bbsUser;
	}

	public void setBbsUser(BBSUser bbsUser)
	{
		this.bbsUser = bbsUser;
	}

	public HyUser getHyUser()
	{
		return hyUser;
	}

	public void setHyUser(HyUser hyUser)
	{
		this.hyUser = hyUser;
	}

	public CookieUser getCookieUser()
	{
		return cookieUser;
	}

	public void setCookieUser(CookieUser cookieUser)
	{
		this.cookieUser = cookieUser;
	}

	public String getOldSource()
	{
		return oldSource;
	}

	public void setOldSource(String oldSource)
	{
		this.oldSource = oldSource;
	}

	public long getOldPageid()
	{
		return oldPageid;
	}

	public void setOldPageid(long oldPageid)
	{
		this.oldPageid = oldPageid;
	}

}
