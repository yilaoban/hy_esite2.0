
package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.ContentNew;
import com.huiyee.interact.cb.model.CbActivityMatter;
import com.huiyee.weixin.model.WxPageShow;

public class PageShowMaterialDto implements IDto
{

	private Pager pager;
	private List<WxPageShow> wxPageShowList;
	private List<ContentCategory> cc;
	private List<ContentNew> news;
	private List<CbActivityMatter> matterList;

	
	public List<CbActivityMatter> getMatterList()
	{
		return matterList;
	}

	
	public void setMatterList(List<CbActivityMatter> matterList)
	{
		this.matterList = matterList;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	public List<WxPageShow> getWxPageShowList()
	{
		return wxPageShowList;
	}

	public void setWxPageShowList(List<WxPageShow> wxPageShowList)
	{
		this.wxPageShowList = wxPageShowList;
	}

	public List<ContentCategory> getCc()
	{
		return cc;
	}

	public void setCc(List<ContentCategory> cc)
	{
		this.cc = cc;
	}

	public List<ContentNew> getNews()
	{
		return news;
	}

	public void setNews(List<ContentNew> news)
	{
		this.news = news;
	}

}
