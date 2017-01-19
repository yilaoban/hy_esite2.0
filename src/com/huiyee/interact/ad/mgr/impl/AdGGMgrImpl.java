package com.huiyee.interact.ad.mgr.impl;

import java.util.List;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.Pager;
import com.huiyee.interact.ad.dao.IAdggDao;
import com.huiyee.interact.ad.dto.AdGGDto;
import com.huiyee.interact.ad.dto.IDto;
import com.huiyee.interact.ad.mgr.IAdGGMgr;
import com.huiyee.interact.ad.model.Adgg;


public class AdGGMgrImpl implements IAdGGMgr
{
	private IAdggDao adGGDao;
	
	public void setAdGGDao(IAdggDao adGGDao)
	{
		this.adGGDao = adGGDao;
	}

	@Override
	public IDto findAdGGListByOwner(long owner, int pageId)
	{
		AdGGDto dto = new AdGGDto();
		int total = adGGDao.findTotalAdGGByOwner(owner);
		int size = 10;
		if(total > 0){
			List<Adgg> adGGList = adGGDao.findAdGGListByOwner(owner,(pageId - 1) * size,size);
			if(adGGList.size() > 0){
				dto.setAdGGList(adGGList);
			}
		}
		dto.setPager(new Pager(pageId, total, size));
		return dto;
	}

	@Override
	public int saveGG(Adgg adgg)
	{
		return adGGDao.saveGG(adgg);
	}

	@Override
	public Adgg findadGGById(long ggid)
	{
		return adGGDao.findadGGById(ggid);
	}

	@Override
	public int updateGG(Adgg adgg)
	{
		return adGGDao.updateGG(adgg);
	}

	@Override
	public int delGGById(long ggid, long owner)
	{
		return adGGDao.delGGById(ggid,owner);
	}

}
