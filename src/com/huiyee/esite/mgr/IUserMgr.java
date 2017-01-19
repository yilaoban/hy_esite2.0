package com.huiyee.esite.mgr;

import java.util.List;

import com.huiyee.esite.model.SinaUser;
import com.huiyee.esite.model.UserInfo;

public interface IUserMgr
{
	public long findUidByViewer(long viewer, long siteid);

	public long updateUidByViewer(long viewer, long siteid);

	public List<SinaUser> findNoNickname();

	public void updateNicknameByid(long wbuid, String screenName, int fansnum, int weibonum,String gender);
	
	public UserInfo findUserInfo(long siteid,String wbuid);
	
	public int saveSiteUser(long viewer,long siteid,String token,long endtsecond,String info);
	
}
