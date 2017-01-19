package com.huiyee.esite.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.huiyee.esite.model.SinaApp;
import com.huiyee.esite.model.SinaToken;
import com.huiyee.tfmodel.HyWbUser;

public interface ISinaTokenDao
{
	public long findPageIdForPageShow(long cid, long appid);

	public List<SinaApp> findSinaApp();

	public SinaApp findSinaAppById(Long appid);

	public int addSinaToken(long siteid, long appid,long ownerid);

	public long findAppidBySiteid(long siteid);

	public SinaToken findTokenBySiteid(long siteid);
	
	public int updateCancelSinaTokenCid(long appid,long cid);

	public void updateSinaToken(HyWbUser user, long appid, String token, Date tokenendtime, long siteid);

	public SinaToken findLastNewToken();
	
	public Map<Long, Long> findSiteWbuid();
	
	public SinaToken findRandomSinaAppToken();

	public List<SinaToken> findCidByOwner(long owner,int start,int size);
	
	public int findCidTotal(long owner);
	
	public String findAppSecrectByAppid(long appid);
	
	public int updatePublishSinaToken(long pageid,long siteid, long appid,long cid,long ownerid,String token,Date tokenendtime,String nickname);
	
	public int updatePageSubSina(long id,String subsina);
	
	public SinaToken findSinaTokenByAppidAndCid(long appid,long cid);
	
	public SinaToken findSinaTokenCidByPageid(long pageid,long appid);
	
	public int findSinaTokenIsExit(long pageid);
	
	public SinaToken findSinaToken(long pageid);
	
	public int updateSinaTokenByPageid(long pageid);
	
	public int updateSinaTokenByAppicCid(long appid,long cid,long pageid);
	
	public int updateAppidCidByPageid(long appid,long cid,long pageid);
	
}
