package com.huiyee.interact.ad.mgr.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.model.AreaProvince;
import com.huiyee.interact.ad.dao.IAdMediaDao;
import com.huiyee.interact.ad.dao.IAreaProvinceDao;
import com.huiyee.interact.ad.dto.AdMediaDto;
import com.huiyee.interact.ad.dto.IDto;
import com.huiyee.interact.ad.mgr.IAdMediaMgr;
import com.huiyee.interact.ad.model.AdMedia;
import com.huiyee.interact.ad.model.AdWay;


public class AdMediaMgrImpl implements IAdMediaMgr
{
	private IAdMediaDao adMediaDao;
	private IAreaProvinceDao areaProvinceDao;
	
	public void setAreaProvinceDao(IAreaProvinceDao areaProvinceDao)
	{
		this.areaProvinceDao = areaProvinceDao;
	}

	public void setAdMediaDao(IAdMediaDao adMediaDao)
	{
		this.adMediaDao = adMediaDao;
	}

	@Override
	public IDto findAdMediaList(long owner,int pageId)
	{
		AdMediaDto dto = new AdMediaDto();
		int total = adMediaDao.findTotalAdMedia(owner);
		int size = 10;
		if(total > 0){
			List<AdMedia> adMediaList = adMediaDao.findAdMediaListByOwner(owner,(pageId - 1) * size,size);
			if(adMediaList.size() > 0){
				dto.setAdMediaList(adMediaList);
			}
		}
		dto.setPager(new Pager(pageId, total, size));
		return dto;
	}

	@Override
	public int savedMedia(AdMedia media,String area) throws Exception
	{
		long areaid = 0;
		if (StringUtils.isEmpty(area))
		{
			Exception e = new RuntimeException("-1");
			throw e;
		} else
		{
			media.setArea(area);
		}
		return adMediaDao.savedMedia(media);
	}

	@Override
	public AdMedia findAdMediaById(long mid)
	{
		return adMediaDao.findAdMediaById(mid);
	}

	@Override
	public int updateMedia(AdMedia media,String area) throws Exception
	{
		long areaid = 0;
		if (StringUtils.isEmpty(area))
		{
			Exception e = new RuntimeException("-1");
			throw e;
		} else
		{
			media.setArea(area);
		}
		return adMediaDao.updateMedia(media);
	}

	@Override
	public int delMediaById(long mid, long owner)
	{
		return adMediaDao.delMediaById(mid,owner);
	}

	@Override
	public IDto findMediaWayList(long mid, String qwd, String wd, int pageId)
	{
		AdMediaDto dto = new AdMediaDto();
		int total = adMediaDao.findTotalMediaWay(mid,qwd, wd);
		int size = 10;
		if(total > 0){
			List<AdWay> adWayList = adMediaDao.findMediaWayList(mid,qwd, wd,(pageId - 1) * size,size);
			if(adWayList.size() > 0){
				dto.setAdWayList(adWayList);
			}
		}
		AdMedia media = findAdMediaById(mid);
		dto.setPager(new Pager(pageId, total, size));
		dto.setMedia(media);
		return dto;
	}

	@Override
	public int delWayById(long wayid, long owner)
	{
		return adMediaDao.delWayById(wayid,owner);
	}

	@Override
	public AdWay findAdWayById(long wayid, long owner)
	{
		return adMediaDao.findAdWayById(wayid,owner);
	}

	@Override
	public String findgroupnameByPageId(long pageid)
	{
		long groupid= adMediaDao.findgroupid(pageid);
		return adMediaDao.findgroupnameBygroupId(groupid);
	}

	@Override
	public List<AdMedia> findAdMediaListByOwner(long owner)
	{
		return adMediaDao.findAdMediaListByOwner(owner);
	}
	@Override
	public List<AreaProvince> findAreaProvince()
	{
		return areaProvinceDao.findList();
	}
	
}
