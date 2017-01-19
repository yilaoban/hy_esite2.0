package com.huiyee.esite.mgr;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.huiyee.esite.model.SinaApp;
import com.huiyee.esite.model.SinaToken;
import com.huiyee.tfmodel.HyWbUser;

public interface ISinaTokenMgr
{
	public long findPageIdForPageShow(long cid, long appid);

	public List<SinaApp> findSinaApp();

	public SinaApp findSinaAppById(Long appid);

	public void updateSinaToken(HyWbUser user, long appid, String token, Date tokenendtime, long siteid);

	public long findAppidBySiteid(long siteid);

	public SinaToken findTokenBySiteid(long siteid);

	public SinaToken findLastNewToken();
	
	public SinaToken findRandomSinaAppToken();
	
	public Map<Long,Long> findSiteWbuid();
	
	public String findAppSecrectByAppid(long appid);
	
	public void updateSinaTokenPublish(long pageid,long siteid, long appid,long cid,long ownerid,String token,Date tokenendtime,String nickname);
	
	public SinaToken findSinaTokenCidByPageid(long pageid,long appid);
	
	public SinaToken findSinaTokenByAppidAndCid(long appid,long cid);
	
	public int findSinaTokenIsExit(long pageid);
	
	public SinaToken findSinaToken(long pageid);
	
	public int updateSinaTokenByPageid(long pageid);
	
	public int updateSinaTokenByAppicCid(long appid,long cid,long pageid);
	
	public int updateAppidCidByPageid(long appid,long cid,long pageid);
	
	public int updatePageSubSina(long id,String subsina);
}
