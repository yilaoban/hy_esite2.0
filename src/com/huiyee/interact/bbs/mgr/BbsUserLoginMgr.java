package com.huiyee.interact.bbs.mgr;

import com.huiyee.esite.dto.BbsUserLoginTime;
import com.huiyee.interact.bbs.dao.IBbsUserLoginDao;

public class BbsUserLoginMgr implements IBbsUserLoginMgr
{
private IBbsUserLoginDao bbsUserLoginDao;

public void setBbsUserLoginDao(IBbsUserLoginDao bbsUserLoginDao)
{
	this.bbsUserLoginDao = bbsUserLoginDao;
}

@Override
public void updateLoginTime(BbsUserLoginTime bbsUserLoginTime)
{
//	bbsUserLoginDao.updateLoginTime(bbsUserLoginTime);
	bbsUserLoginDao.updateBBSLoginLog(bbsUserLoginTime.getLogid());
	
}
}
