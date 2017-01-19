package com.huiyee.esite.mgr.imp;

import java.util.Date;
import java.util.List;

import com.huiyee.esite.dao.IUserDao;
import com.huiyee.esite.mgr.IUserMgr;
import com.huiyee.esite.model.SinaUser;
import com.huiyee.esite.model.UserInfo;

public class UserMgrImpl implements IUserMgr
{
	private IUserDao userDao;

	public void setUserDao(IUserDao userDao)
	{
		this.userDao = userDao;
	}

	@Override
	public long findUidByViewer(long viewer, long siteid)
	{
		return userDao.findUidByViewer(viewer, siteid);
	}

	@Override
	public long updateUidByViewer(long viewer, long siteid)
	{
		return userDao.updateUidByViewer(viewer, siteid);
	}

	@Override
	public List<SinaUser> findNoNickname()
	{
		return userDao.findNoNickname();
	}
	
	@Override
	public void updateNicknameByid(long wbuid, String screenName,int fansnum,int weibonum,String gender)
	{
		userDao.updateNicknameByid(wbuid,screenName, fansnum, weibonum,gender);
		//userDao.updateUserinfo(id, screenName);
	}

    @Override
    public UserInfo findUserInfo(long siteid, String wbuid) {
        return userDao.findUserInfo(siteid, wbuid);
    }

	@Override
	public int saveSiteUser(long viewer, long siteid, String token,
			long endtsecond,String info) {
		userDao.saveUserWbuid(viewer, info);
		return userDao.saveSiteUser(viewer, siteid, token, new Date(new Date().getTime()+endtsecond));
	}
}
