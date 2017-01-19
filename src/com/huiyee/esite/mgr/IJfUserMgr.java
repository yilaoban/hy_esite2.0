package com.huiyee.esite.mgr;

import java.util.Date;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.UserBalance;


public interface IJfUserMgr
{
	public IDto findUserBalance(long owner,long mpid,int pageId,long hyuid,String nickname,Date startTime,Date endTime,int type);
	
	public UserBalance findUserBalanceByHyUid(long hyuid);
}
