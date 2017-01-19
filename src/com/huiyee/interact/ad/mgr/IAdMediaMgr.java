package com.huiyee.interact.ad.mgr;

import java.util.List;

import com.huiyee.esite.model.AreaProvince;
import com.huiyee.interact.ad.dto.IDto;
import com.huiyee.interact.ad.model.AdMedia;
import com.huiyee.interact.ad.model.AdWay;


public interface IAdMediaMgr
{
	public IDto findAdMediaList(long owner,int pageId);
	
	public int savedMedia(AdMedia media,String area) throws Exception;
	
	public AdMedia findAdMediaById(long mid);
	
	public int updateMedia(AdMedia media,String area) throws Exception;
	
	public int delMediaById(long mid,long owner);
	
	public IDto findMediaWayList(long mid, String qwd,String wd,int pageId);
	
	public int delWayById(long wayid,long owner);
	
	public AdWay findAdWayById(long wayid,long owner);
	
	public String findgroupnameByPageId(long pageid);
	
	public List<AdMedia> findAdMediaListByOwner(long owner);

	public List<AreaProvince> findAreaProvince();
}
