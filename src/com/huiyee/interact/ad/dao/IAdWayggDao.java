package com.huiyee.interact.ad.dao;

import java.util.List;

import com.huiyee.interact.ad.model.AdWaygg;
import com.huiyee.interact.ad.model.Adgg;


public interface IAdWayggDao
{
	public List<Adgg> findAdggByWayid(long wayid);

	public List<AdWaygg> findAdWayggByWayid(long wayid);

	public void updateWayggClean(long wayid);

	public int saveWaygg(long wayid, String[] ggid);
}
