package com.huiyee.interact.bbs.dao;

import com.huiyee.esite.dto.BbsUserLoginTime;

public interface IBbsUserLoginDao
{
	public void updateLoginTime(BbsUserLoginTime bbsUserLoginTime);
	
	public void updateBBSLoginLog(long logid);
}
