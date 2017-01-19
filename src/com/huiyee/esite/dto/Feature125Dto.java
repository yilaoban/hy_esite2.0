package com.huiyee.esite.dto;

import java.io.Serializable;
import java.util.List;

import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.Redirect;
import com.huiyee.interact.lottery.model.Lottery;

public class Feature125Dto implements IDto , Serializable{
	private static final long serialVersionUID = 6282879071900989379L;
	
	private List<Lottery> lotteryList;
	private long lotteryid;
	private long fid;
	private Redirect redirect;
	private long relationid;
	
	private String type;
	private long pageid;
	
	private String url;
	private String urlShow;
	private String urlPre;
	private String furl;
	private String furlShow;
	private List<Page> pageList;
	private long toPageid;
	
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public long getPageid()
	{
		return pageid;
	}
	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}
	public long getRelationid()
	{
		return relationid;
	}
	public void setRelationid(long relationid)
	{
		this.relationid = relationid;
	}
	public List<Lottery> getLotteryList()
	{
		return lotteryList;
	}
	public void setLotteryList(List<Lottery> lotteryList)
	{
		this.lotteryList = lotteryList;
	}
	public long getLotteryid()
	{
		return lotteryid;
	}
	public void setLotteryid(long lotteryid)
	{
		this.lotteryid = lotteryid;
	}
	public long getFid()
	{
		return fid;
	}
	public void setFid(long fid)
	{
		this.fid = fid;
	}
	
	public String getUrl()
	{
		return url;
	}
	
	public void setUrl(String url)
	{
		this.url = url;
	}
	
	public String getUrlShow()
	{
		return urlShow;
	}
	
	public void setUrlShow(String urlShow)
	{
		this.urlShow = urlShow;
	}
	
	public String getUrlPre()
	{
		return urlPre;
	}
	
	public void setUrlPre(String urlPre)
	{
		this.urlPre = urlPre;
	}
	
	public List<Page> getPageList()
	{
		return pageList;
	}
	
	public void setPageList(List<Page> pageList)
	{
		this.pageList = pageList;
	}
	
	public long getToPageid()
	{
		return toPageid;
	}
	
	public void setToPageid(long toPageid)
	{
		this.toPageid = toPageid;
	}
	
	public Redirect getRedirect()
	{
		return redirect;
	}
	
	public void setRedirect(Redirect redirect)
	{
		this.redirect = redirect;
	}
	
	public String getFurl()
	{
		return furl;
	}
	
	public void setFurl(String furl)
	{
		this.furl = furl;
	}
	
	public String getFurlShow()
	{
		return furlShow;
	}
	
	public void setFurlShow(String furlShow)
	{
		this.furlShow = furlShow;
	}
	
	
	
}
