package com.huiyee.esite.mgr.imp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.huiyee.esite.dao.ISinaTokenDao;
import com.huiyee.esite.mgr.ISinaTokenMgr;
import com.huiyee.esite.model.SinaApp;
import com.huiyee.esite.model.SinaToken;
import com.huiyee.tfmodel.HyWbUser;

public class SinaTokenMgrImpl implements ISinaTokenMgr
{
	private ISinaTokenDao sinaTokenDao;

	public void setSinaTokenDao(ISinaTokenDao sinaTokenDao)
	{
		this.sinaTokenDao = sinaTokenDao;
	}

	@Override
	public long findPageIdForPageShow(long cid, long appid)
	{
		return sinaTokenDao.findPageIdForPageShow(cid, appid);
	}

	@Override
	public List<SinaApp> findSinaApp()
	{
		return sinaTokenDao.findSinaApp();
	}

	@Override
	public SinaApp findSinaAppById(Long appid)
	{
		return sinaTokenDao.findSinaAppById(appid);
	}

	@Override
	public void updateSinaToken(HyWbUser user, long appid, String token, Date tokenendtime, long siteid)
	{
		sinaTokenDao.updateCancelSinaTokenCid(appid, Long.parseLong(user.getId()));
		sinaTokenDao.updateSinaToken(user, appid, token, tokenendtime, siteid);
	}
	
	@Override
	public void updateSinaTokenPublish(long pageid,long siteid, long appid,long cid,long ownerid,String token,Date tokenendtime,String nickname)
	{
		SinaToken sinaToken=sinaTokenDao.findSinaTokenByAppidAndCid(appid, cid);
		sinaTokenDao.updatePublishSinaToken(pageid, siteid, appid, cid, ownerid,token, tokenendtime, nickname);
		sinaTokenDao.updatePageSubSina(pageid, "Y");
		if(sinaToken!=null){
			sinaTokenDao.updatePageSubSina(sinaToken.getPageid(), "N");
		} 
	}

	@Override
	public SinaToken findLastNewToken()
	{
		return sinaTokenDao.findLastNewToken();
	}

	@Override
	public long findAppidBySiteid(long siteid)
	{
		return sinaTokenDao.findAppidBySiteid(siteid);
	}

	@Override
	public SinaToken findTokenBySiteid(long siteid)
	{
		return sinaTokenDao.findTokenBySiteid(siteid);
	}

	@Override
	public Map<Long, Long> findSiteWbuid() {
		return sinaTokenDao.findSiteWbuid();
	}

	@Override
	public SinaToken findRandomSinaAppToken() {
		return sinaTokenDao.findRandomSinaAppToken();
	}

	@Override
	public String findAppSecrectByAppid(long appid) {
		return sinaTokenDao.findAppSecrectByAppid(appid);
	}

	@Override
	public SinaToken findSinaTokenCidByPageid(long pageid,long appid)
	{
		return sinaTokenDao.findSinaTokenCidByPageid(pageid,appid);
	}

	@Override
	public int findSinaTokenIsExit(long pageid)
	{
		return sinaTokenDao.findSinaTokenIsExit(pageid);
	}

	@Override
	public SinaToken findSinaToken(long pageid)
	{
		return sinaTokenDao.findSinaToken(pageid);
	}

	@Override
	public int updateSinaTokenByAppicCid(long appid, long cid, long pageid)
	{
		return sinaTokenDao.updateSinaTokenByAppicCid(appid, cid, pageid);
	}

	@Override
	public int updateSinaTokenByPageid(long pageid)
	{
		return sinaTokenDao.updateSinaTokenByPageid(pageid);
	}

	@Override
	public SinaToken findSinaTokenByAppidAndCid(long appid, long cid)
	{
		return sinaTokenDao.findSinaTokenByAppidAndCid(appid, cid);
	}

	@Override
	public int updateAppidCidByPageid(long appid, long cid, long pageid)
	{
		return sinaTokenDao.updateAppidCidByPageid(appid, cid, pageid);
	}

	@Override
	public int updatePageSubSina(long id, String subsina)
	{
		return sinaTokenDao.updatePageSubSina(id, subsina);
	}
}
