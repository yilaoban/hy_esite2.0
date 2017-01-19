package com.huiyee.interact.ad.dto;

import java.util.List;

import com.huiyee.esite.dto.Pager;
import com.huiyee.interact.ad.model.AdMedia;
import com.huiyee.interact.ad.model.AdWay;


public class AdMediaDto implements IDto
{
	public Pager pager;
	public List<AdMedia> adMediaList; 
	public List<AdWay> adWayList;
	public AdMedia media;
	
	public AdMedia getMedia()
	{
		return media;
	}
	
	public void setMedia(AdMedia media)
	{
		this.media = media;
	}


	public List<AdWay> getAdWayList()
	{
		return adWayList;
	}

	
	public void setAdWayList(List<AdWay> adWayList)
	{
		this.adWayList = adWayList;
	}

	public Pager getPager()
	{
		return pager;
	}
	
	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	
	public List<AdMedia> getAdMediaList()
	{
		return adMediaList;
	}

	
	public void setAdMediaList(List<AdMedia> adMediaList)
	{
		this.adMediaList = adMediaList;
	}
	
}
