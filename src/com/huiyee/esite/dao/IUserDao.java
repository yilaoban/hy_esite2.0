package com.huiyee.esite.dao;

import java.util.Date;
import java.util.List;

import com.huiyee.esite.model.SinaUser;
import com.huiyee.esite.model.UserInfo;

public interface IUserDao
{
	public long findUidByViewer(long viewer, long siteid);

	public long updateUidByViewer(long viewer, long siteid);

	public List<SinaUser> findNoNickname();

	public void updateNicknameByid(long wbuid, String screenName,int fansnum,int weibonum,String gender);
	
	public UserInfo findUserInfo(long siteid,String wbuid);
	
	public int saveSiteUser(long viewer, long siteid, String token,Date tokenendtime); 
	
	public int saveUserWbuid(long wbuid,String info);
	
	public int updateUserinfo(long id,String nickname);
}
