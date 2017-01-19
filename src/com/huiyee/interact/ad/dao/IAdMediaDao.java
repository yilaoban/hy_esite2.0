package com.huiyee.interact.ad.dao;

import java.util.List;

import com.huiyee.interact.ad.model.AdMedia;
import com.huiyee.interact.ad.model.AdWay;


public interface IAdMediaDao
{
	public int findTotalAdMedia(long owner);
	
	public List<AdMedia> findAdMediaListByOwner(long owner,int start,int size);
	
	public List<AdMedia> findAdMediaListByOwner(long owner);
	
	public int savedMedia(AdMedia media);
	
	public AdMedia findAdMediaById(long mid);
	
	public int updateMedia(AdMedia media);
	
	public int delMediaById(long mid, long owner);
	
	public int findTotalMediaWay(long mid,String qwd, String wd);
	
	public List<AdWay> findMediaWayList(long mid,String qwd, String wd,int start,int size);
	
	public List<AdMedia> findMediaList(long owner, String media);

	public AdMedia findMediaByName(String media, long owner);

	public long saveMedia(String media, long owner);
	
	public int delWayById(long wayid, long owner);
	
	public AdWay findAdWayById(long wayid, long owner);
	
	public long findgroupid(long pageid);
	
	public String findgroupnameBygroupId(long groupid);
}
