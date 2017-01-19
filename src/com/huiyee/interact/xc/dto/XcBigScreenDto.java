package com.huiyee.interact.xc.dto;

import java.util.List;

import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.Site;
import com.huiyee.interact.xc.model.LotteryConfig;
import com.huiyee.interact.xc.model.XcBigScreen;

public class XcBigScreenDto implements IDto {
	private List<XcBigScreen> xcBigScreenList;
	private Page page;
	private List<Page> pageList;
	private List<Site> siteList;
	private int size;
	private long pageid;
	private LotteryConfig lconfig;
	private long xcid;
	private long siteid;
	
	public long getSiteid()
	{
		return siteid;
	}

	
	public void setSiteid(long siteid)
	{
		this.siteid = siteid;
	}

	public long getXcid()
	{
		return xcid;
	}

	public void setXcid(long xcid)
	{
		this.xcid = xcid;
	}

	public LotteryConfig getLconfig()
	{
		return lconfig;
	}

	public void setLconfig(LotteryConfig lconfig)
	{
		this.lconfig = lconfig;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}

	public List<Site> getSiteList()
	{
		return siteList;
	}

	public void setSiteList(List<Site> siteList)
	{
		this.siteList = siteList;
	}

	public List<Page> getPageList()
	{
		return pageList;
	}

	public void setPageList(List<Page> pageList)
	{
		this.pageList = pageList;
	}

	public List<XcBigScreen> getXcBigScreenList()
	{
		return xcBigScreenList;
	}

	public void setXcBigScreenList(List<XcBigScreen> xcBigScreenList)
	{
		this.xcBigScreenList = xcBigScreenList;
	}

	public Page getPage()
	{
		return page;
	}

	public void setPage(Page page)
	{
		this.page = page;
	}
	
	
}
