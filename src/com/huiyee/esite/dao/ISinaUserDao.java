package com.huiyee.esite.dao;

import java.util.Date;

import com.huiyee.esite.model.SinaUser;
import com.huiyee.esite.model.UserInfo;
import com.huiyee.tfmodel.HyWbUser;

public interface ISinaUserDao {

	public int saveSinaUser(HyWbUser user);
	
	public long saveSinaUserApp(long wbuid,long appid,String token,Date tokenendtime,String ip,String terminal,long siteid);
	
	public long updateSinaUserApp(long id,String token,Date tokenendtime, String ip, String terminal);
	
	public long findWbuidByUid(long uid);
	
	public String findTokenByWbuid(long wbuid,long pageid);
	
	public String findTokenByUidAndSiteid(long uid, long siteid);
	
	public long findUserApp(long wbuid,long siteid,long appid);
	
	public UserInfo findUserByUid(long uid);

	public String findNickNameById(long wbuid);

	public SinaUser findSinaUserByid(long wbuid);
}
