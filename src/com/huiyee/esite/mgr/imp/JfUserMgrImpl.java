package com.huiyee.esite.mgr.imp;

import java.util.Date;
import java.util.List;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.dao.IHyUserDao;
import com.huiyee.esite.dao.IJfUserDao;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.JfUserDto;
import com.huiyee.esite.mgr.IJfUserMgr;
import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.model.UserBalance;
import com.huiyee.interact.checkin.dto.Pager;


public class JfUserMgrImpl implements IJfUserMgr
{
	private IJfUserDao jfUserDao;
	private IHyUserDao hyUserDao;
	
	
	public void setHyUserDao(IHyUserDao hyUserDao)
	{
		this.hyUserDao = hyUserDao;
	}

	public void setJfUserDao(IJfUserDao jfUserDao)
	{
		this.jfUserDao = jfUserDao;
	}

	@Override
	public IDto findUserBalance(long owner,long mpid,int pageId,long hyuid,String nickname,Date startTime,Date endTime,int type)
	{
		JfUserDto dto = new JfUserDto();
		int total = jfUserDao.findUserBalanceTotal(owner,mpid,hyuid,nickname,startTime,endTime,type);
		if(total > 0){
			int start = (pageId-1)*IInteractConstants.JF_LIMIT;
			List<UserBalance> list = jfUserDao.findUserBalance(owner,mpid,hyuid,nickname,startTime,endTime,type,start,IInteractConstants.JF_LIMIT);
			for (UserBalance userBalance : list)
			{
				HyUser user=hyUserDao.findHyUserById(userBalance.getHyuid());
				userBalance.setHyuser(user);
			}
			dto.setUserBalanceList(list);
		}
		Pager pager = new Pager(pageId, total, IInteractConstants.JF_LIMIT);
		dto.setPager(pager);
		return dto;
	}

	@Override
	public UserBalance findUserBalanceByHyUid(long hyuid)
	{
		return jfUserDao.findUserBalanceByHyUid(hyuid);
	}
	
}
