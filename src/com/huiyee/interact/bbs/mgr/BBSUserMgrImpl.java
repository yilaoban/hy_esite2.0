package com.huiyee.interact.bbs.mgr;

import java.util.Date;

import com.huiyee.esite.model.Page;
import com.huiyee.interact.bbs.dao.IBBSUserDao;
import com.huiyee.interact.bbs.model.BBSUser;
import com.huiyee.interact.bbs.model.BBSUserActiveLevel;
import com.huiyee.interact.bbs.model.BBSUserOnline;

public class BBSUserMgrImpl implements IBBSUserMgr {

	private IBBSUserDao bbsUserDao;
	
	public void setBbsUserDao(IBBSUserDao bbsUserDao)
	{
		this.bbsUserDao = bbsUserDao;
	}
	
	@Override
	public int updateBBSUserbyId(long id)
	{
		return bbsUserDao.updateBBSUserbyId(id);
	}

	@Override
	public long saveBBSLoginLog(long userid, String ip)
	{
		return bbsUserDao.saveBBSLoginLog(userid,ip);
	}

	@Override
	public BBSUserActiveLevel findBBSUseActiveLevel(long userid)
	{
		return bbsUserDao.findBBSUseActiveLevel(userid);
	}

	@Override
	public BBSUserOnline findBBSUserOnlineById(long userid)
	{
		return bbsUserDao.findBBSUserOnlineById(userid);
	}

	@Override
	public int updateBBSUserLevlebyId(long id)
	{
		return bbsUserDao.updateBBSUserLevlebyId(id);
	}

	@Override
	public Page findJspnameByForumid(long forumid) {
		return bbsUserDao.findJspnameByForumid(forumid);
	}

	@Override
	public Page findRegJspnameByForumid(long forumid) {
		
		return bbsUserDao.findRegJspnameByForumid(forumid);

	}

	@Override
	public int updateOperateNum(String column, long id) {
		return bbsUserDao.updateOperateNum(column, id);
	}

	@Override
	public BBSUser findBBSUserByHyuid(long hyuid)
	{
		return bbsUserDao.findBBSUserByHyuid(hyuid);
	}

	@Override
	public BBSUser saveBBSUser(long hyuid, long owner,String ip)
	{
		BBSUser bs = new BBSUser();
		bs.setHyuserid(hyuid);
		bs.setOwner(owner);
		bs.setCreatetime(new Date());
		long bsid = bbsUserDao.saveBBSUser(bs, ip);
		bs.setId(bsid);
		bbsUserDao.saveBBSUserOnline(bsid);
		bbsUserDao.updateBBSUserbyId(bsid);
		long logid = bbsUserDao.saveBBSLoginLog(bsid, ip);
		bs.setLogid(logid);
		bs.setLogintime(System.currentTimeMillis());
		return bs;
	}
	
}
