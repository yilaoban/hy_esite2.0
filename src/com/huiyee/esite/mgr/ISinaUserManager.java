package com.huiyee.esite.mgr;

import java.util.Date;

import com.huiyee.esite.model.UserInfo;
import com.huiyee.tfmodel.HyWbUser;

public interface ISinaUserManager {
	
	public void saveSinaUserOauth(HyWbUser user, long appid, String token, Date tokenendtime, long siteid,String ip,String terminal,long pageid,String source);
	
	public long findWbuidByUid(long uid);
	
	public String findTokenByWbuid(long wbuid,long pageid);
	
	public String findTokenByUidAndSiteid(long uid,long siteid);
	
	public UserInfo findUserByUid(long uid);

}
