
package com.huiyee.interact.ad.mgr.impl;

import java.util.List;
import java.util.Random;

import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.ad.dao.IAdWayDao;
import com.huiyee.interact.ad.dao.IAdWayggDao;
import com.huiyee.interact.ad.mgr.IAdWagGGMgr;
import com.huiyee.interact.ad.model.Ad;
import com.huiyee.interact.ad.model.AdWay;
import com.huiyee.interact.ad.model.Adgg;

public class AdWagGGMgrImpl implements IAdWagGGMgr
{

	private IAdWayggDao adWayggDao;
	private IAdWayDao adWayDao;

	public void setAdWayggDao(IAdWayggDao adWayggDao)
	{
		this.adWayggDao = adWayggDao;
	}

	public void setAdWayDao(IAdWayDao adWayDao)
	{
		this.adWayDao = adWayDao;
	}

	@Override
	public AdWay findAdggByWayid(VisitUser vu)
	{

		if (vu != null)
		{
			long wayid = Long.parseLong(vu.getKv());
			AdWay way = adWayDao.findWayByid(wayid);
			if (way != null)
			{
				List<Adgg> adggList = adWayggDao.findAdggByWayid(wayid);
				Random r = new Random();
				int index = 0;
				if (adggList != null && adggList.size() > 0)
				{
					int size = adggList.size();
					index = r.nextInt(size);
					Adgg ad = adggList.get(index);
					way.setGg(ad);
				}

				if (way.getFsurl() != null && vu.getWxUser() != null && vu.getWxUser().getSubscribe() == 1)
				{
					way.setUrl(way.getFsurl());
				}
			}
			return way;
		}
		return null;

	}

}
