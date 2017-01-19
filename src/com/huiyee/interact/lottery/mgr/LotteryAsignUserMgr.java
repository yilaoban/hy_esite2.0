
package com.huiyee.interact.lottery.mgr;

import com.huiyee.interact.lottery.dao.ILotteryAsignUserDao;

public class LotteryAsignUserMgr implements ILotteryAsignUserMgr
{

	private ILotteryAsignUserDao lotteryAsignUserDao;

	public void setLotteryAsignUserDao(ILotteryAsignUserDao lotteryAsignUserDao)
	{
		this.lotteryAsignUserDao = lotteryAsignUserDao;
	}

	@Override
	public int findXcCheckIn(long xcid, long uid, int utype)
	{
		return lotteryAsignUserDao.findXcCheckIn(xcid, uid, utype);
	}

}
