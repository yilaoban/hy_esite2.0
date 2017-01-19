package com.huiyee.interact.ad.dao;

import java.util.List;

import com.huiyee.interact.ad.model.AdWay;


public interface IAdWayDao
{

	public long saveAdWay(long mediaid, long wdid, long qwdid, long pageid, String url,String fsurl, long owner,String type,int num);
	
	public int updateAdWay(long wayid,long mediaid, long wdid, long qwdid, long pageid, String url,String fsurl, long owner,String type,int num);
	
	public int updateMediaWay(long wayid, long wdid, long qwdid, long pageid, String url,String fsurl, long owner);

	public int findWayTotal(long owner, String qwd, String media, String wd);

	public List<AdWay> findWayList(long owner, String qwd, String media, String wd, int i, int interactAdLimit);

	public AdWay findWayByid(long wayid);

}
