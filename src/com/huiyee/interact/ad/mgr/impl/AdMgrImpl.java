
package com.huiyee.interact.ad.mgr.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.AreaProvince;
import com.huiyee.esite.model.Page;
import com.huiyee.interact.ad.dao.IAdDao;
import com.huiyee.interact.ad.dao.IAdMediaDao;
import com.huiyee.interact.ad.dao.IAdWayDao;
import com.huiyee.interact.ad.dao.IAdWayggDao;
import com.huiyee.interact.ad.dao.IAdggDao;
import com.huiyee.interact.ad.dao.IAdwdDao;
import com.huiyee.interact.ad.dao.IAreaProvinceDao;
import com.huiyee.interact.ad.dto.AdDto;
import com.huiyee.interact.ad.mgr.IAdMgr;
import com.huiyee.interact.ad.model.Ad;
import com.huiyee.interact.ad.model.AdMedia;
import com.huiyee.interact.ad.model.AdWay;
import com.huiyee.interact.ad.model.AdWaygg;
import com.huiyee.interact.ad.model.Adgg;
import com.huiyee.interact.ad.model.Adwd;

public class AdMgrImpl implements IAdMgr
{

	private IAdDao adDao;
	private IAdggDao adggDao;
	private IAdMediaDao adMediaDao;
	private IAdWayDao adWayDao;
	private IAdWayggDao adWayggDao;
	private IAdwdDao adwdDao;
	private IAreaProvinceDao areaProvinceDao;

	@Override
	public Ad findAdByOwner(Account account)
	{
		return adDao.findAdByOwner(account.getOwner().getId());
	}

	@Override
	public int saveAd(Account account)
	{
		return adDao.saveAd(account.getOwner().getId());
	}

	@Override
	public IDto findQrListInfo(Account account, String qwd, String media, String wd, int pageId)
	{
		long owner = account.getOwner().getId();
		AdDto dto = new AdDto();
		int total = adWayDao.findWayTotal(owner, qwd, media, wd);
		if (total > 0)
		{
			List<AdWay> list = adWayDao.findWayList(owner, qwd, media, wd, (pageId - 1) * IPageConstants.INTERACT_AD_LIMIT, IPageConstants.INTERACT_AD_LIMIT);
			dto.setPager(new Pager(pageId, total, IPageConstants.INTERACT_AD_LIMIT));
			dto.setList(list);

			List<AdMedia> adMediaList = adMediaDao.findAdMediaListByOwner(owner);
			if (adMediaList.size() > 0)
			{
				dto.setAdMediaList(adMediaList);
			}

		}
		return dto;
	}

	@Override
	public List<AreaProvince> findAreaProvince(Account account, String area)
	{
		return areaProvinceDao.findList(area);
	}

	@Override
	public List<Adwd> findWdList(Account account, String wd, String type)
	{
		return adwdDao.findListByOwner(account.getOwner().getId(), wd, type);
	}

	@Override
	public List<AdMedia> findMediaList(Account account, String media)
	{
		return adMediaDao.findMediaList(account.getOwner().getId(), media);
	}

	@Override
	public long saveAdWay(Account account, String media, String qwd, String wd, long gid, String url, String fsurl, String type, int num) throws Exception
	{
		long owner = account.getOwner().getId();
		long mediaid = 0;
		long qwdid = 0;
		long wdid = 0;
		if (StringUtils.isEmpty(media))
		{
			Exception e = new RuntimeException("-1");
			throw e;
		} else
		{
			mediaid = getMediaId(owner, media);
		}

		if (StringUtils.isEmpty(qwd))
		{
			Exception e = new RuntimeException("-2");
			throw e;
		} else
		{
			qwdid = getWdId(owner, qwd, Adwd.WD_QHO);
		}

		if (StringUtils.isEmpty(wd))
		{
			Exception e = new RuntimeException("-3");
			throw e;
		} else
		{
			wdid = getWdId(owner, wd, Adwd.WD_BBN);
		}
		Page p = adDao.findPageByApptypeAndGroupId(gid, Page.APPTYPE_ADD);
		if (p == null)
		{
			Exception e = new RuntimeException("-4");
			throw e;
		}

		return adWayDao.saveAdWay(mediaid, wdid, qwdid, p.getId(), url, fsurl, owner, type, num);
	}

	@Override
	public IDto findAdWayList(Account account, long wayid, int pageId)
	{
		long owner = account.getOwner().getId();
		AdDto dto = new AdDto();
		List<AdWaygg> list = adWayggDao.findAdWayggByWayid(wayid);
		dto.setWgList(list);
		List<Adgg> gg = adggDao.findAdGGListByOwnerOrderByStart(owner, (pageId - 1) * IInteractConstants.INTERACT_AD_WAY_GG, IInteractConstants.INTERACT_AD_WAY_GG);
		int total = adggDao.findTotalAdGGByOwner(owner);
		dto.setPager(new Pager(pageId, total, IInteractConstants.INTERACT_AD_WAY_GG));
		dto.setGgList(gg);
		return dto;
	}

	@Override
	public int saveWaygg(Account account, long wayid, String ggids)
	{

		adWayggDao.updateWayggClean(wayid);
		if (StringUtils.isNotEmpty(ggids))
		{
			String[] ggid = ggids.split(",");
			return adWayggDao.saveWaygg(wayid, ggid);
		}
		return 1;
	}

	private long getWdId(long owner, String name, String type)
	{
		Adwd wd = adwdDao.findWdByName(owner, name, type);
		if (wd != null)
		{
			return wd.getId();
		} else
		{
			return adwdDao.saveWd(name, owner, type);
		}
	}

	private long getMediaId(long owner, String media)
	{
		AdMedia adm = adMediaDao.findMediaByName(media, owner);
		if (adm != null)
		{
			return adm.getId();
		} else
		{
			return adMediaDao.saveMedia(media, owner);
		}
	}

	public void setAdDao(IAdDao adDao)
	{
		this.adDao = adDao;
	}

	public void setAdggDao(IAdggDao adggDao)
	{
		this.adggDao = adggDao;
	}

	public void setAdMediaDao(IAdMediaDao adMediaDao)
	{
		this.adMediaDao = adMediaDao;
	}

	public void setAdWayDao(IAdWayDao adWayDao)
	{
		this.adWayDao = adWayDao;
	}

	public void setAdWayggDao(IAdWayggDao adWayggDao)
	{
		this.adWayggDao = adWayggDao;
	}

	public void setAdwdDao(IAdwdDao adwdDao)
	{
		this.adwdDao = adwdDao;
	}

	public void setAreaProvinceDao(IAreaProvinceDao areaProvinceDao)
	{
		this.areaProvinceDao = areaProvinceDao;
	}

	@Override
	public int updateAdWay(Account account, long wayid, String media, String qwd, String wd, long gid, String url, String fsurl, String type, int num) throws Exception
	{
		long owner = account.getOwner().getId();
		long mediaid = getMediaId(owner, media);
		long qwdid = getWdId(owner, qwd, Adwd.WD_QHO);
		long wdid = getWdId(owner, wd, Adwd.WD_BBN);
		Page p = adDao.findPageByApptypeAndGroupId(gid, Page.APPTYPE_ADD);
		if (p == null)
		{
			Exception e = new RuntimeException("-4");
			throw e;
		}

		return adWayDao.updateAdWay(wayid, mediaid, wdid, qwdid, p.getId(), url, fsurl, owner, type, num);
	}

	@Override
	public int updateMediaWay(Account account, long wayid, String qwd, String wd, long gid, String url, String fsurl) throws Exception
	{
		long owner = account.getOwner().getId();
		long qwdid = getWdId(owner, qwd, Adwd.WD_QHO);
		long wdid = getWdId(owner, wd, Adwd.WD_BBN);
		Page p = adDao.findPageByApptypeAndGroupId(gid, Page.APPTYPE_ADD);
		if (p == null)
		{
			Exception e = new RuntimeException("-4");
			throw e;
		}

		return adWayDao.updateMediaWay(wayid, wdid, qwdid, p.getId(), url, fsurl, owner);
	}

}
