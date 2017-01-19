package com.huiyee.esite.mgr.imp;

import java.util.Date;

import com.huiyee.esite.dao.IDynamicActionRecordDao;
import com.huiyee.esite.dao.ISinaUserDao;
import com.huiyee.esite.mgr.ISinaUserManager;
import com.huiyee.esite.model.UserInfo;
import com.huiyee.tfmodel.HyWbUser;

public class SinaUserManagerImpl implements ISinaUserManager {

	private ISinaUserDao sinaUserDao;
	private IDynamicActionRecordDao dynamicActionRecordDao;

	public void setSinaUserDao(ISinaUserDao sinaUserDao) {
		this.sinaUserDao = sinaUserDao;
	}

	@Override
	public void saveSinaUserOauth(HyWbUser user, long appid, String token,
			Date tokenendtime, long siteid,String ip,String terminal,long pageid,String source) {
		sinaUserDao.saveSinaUser(user);//±£´æÓÃ»§
		long entityid = sinaUserDao.findUserApp(Long.parseLong(user.getId()), siteid, appid);
		if( entityid > 0){
			entityid = sinaUserDao.updateSinaUserApp(entityid, token, tokenendtime,ip,terminal);
		}else{
			entityid = sinaUserDao.saveSinaUserApp(Long.parseLong(user.getId()), appid, token, tokenendtime,ip,terminal,siteid);
		}
		try {
			dynamicActionRecordDao.addDynamicActionRecord(pageid, Long.parseLong(user.getId()), 111, entityid, source, ip, terminal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String findTokenByWbuid(long wbuid,long pageid) {
		return sinaUserDao.findTokenByWbuid(wbuid, pageid);
	}

	@Override
	public String findTokenByUidAndSiteid(long uid, long siteid) {
		return sinaUserDao.findTokenByUidAndSiteid(uid, siteid);
	}

	@Override
	public long findWbuidByUid(long uid) {
		return sinaUserDao.findWbuidByUid(uid);
	}

	public void setDynamicActionRecordDao(
			IDynamicActionRecordDao dynamicActionRecordDao) {
		this.dynamicActionRecordDao = dynamicActionRecordDao;
	}

	@Override
	public UserInfo findUserByUid(long uid)
	{
		return sinaUserDao.findUserByUid(uid);
	}
}
