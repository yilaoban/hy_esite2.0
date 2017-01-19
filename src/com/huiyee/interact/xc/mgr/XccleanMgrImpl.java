
package com.huiyee.interact.xc.mgr;

import java.util.List;

import com.google.gson.Gson;
import com.huiyee.esite.model.Account;
import com.huiyee.interact.xc.dao.ISigninDao;
import com.huiyee.interact.xc.dao.IXcDao;
import com.huiyee.interact.xc.dao.IXcLotteryDao;
import com.huiyee.interact.xc.dao.IXcSendRecordDao;
import com.huiyee.interact.xc.dao.IXcfeatureDao;
import com.huiyee.interact.xc.model.HdEntity;
import com.huiyee.interact.xc.model.LotteryConfig;
import com.huiyee.interact.xc.model.Xc;

public class XccleanMgrImpl implements IXccleanMgr
{

	private IXcLotteryDao xcLotteryDao;
	private IXcSendRecordDao xcSendRecordDao;
	private IXcfeatureDao xcfeatureDao;

	@Override
	public int updateXcclean(Account account, long xcid, String inviteclean)
	{
		// es_interact_xc lotteryconfig 更新"started":0,"startnum":1
		Xc xc = xcLotteryDao.findXcById(xcid);
		if(xc==null){
			return 0;
		}
		LotteryConfig lconfig = xc.getLotteryconfig();
		lconfig.setStartnum(1);
		String lotteryconfig = new Gson().toJson(lconfig);
		xcLotteryDao.updateXcLottery(xcid, lotteryconfig);
		// 重置邀请记录,删除打开记录,删除签到记录,删除抽奖记录
		if("Y".equalsIgnoreCase(inviteclean)){
			xcSendRecordDao.updateXcClean(xcid);
		}else{
			xcSendRecordDao.updateXcCleanWithOutInvite(xcid);
		}
		// 删除互动
		List<HdEntity> list = xcfeatureDao.findXcEntitys(xcid);
		for (HdEntity hdEntity : list)
		{
			int featureid = hdEntity.getFeatureid();
			if (featureid == 10003 || featureid == 10004)
			{
				// 抽奖数据重置
				long lid = hdEntity.getEntityid();
				xcfeatureDao.updatehdEnd(lid, account.getOwner().getId());
				xcfeatureDao.updateLotteryClean(lid);

			} else if (featureid == 10002)
			{
				// 投票数据重置
				long lid = hdEntity.getEntityid();
				xcfeatureDao.updateVoteClean(lid);

			}

		}
		return 1;
	}

	public void setXcLotteryDao(IXcLotteryDao xcLotteryDao)
	{
		this.xcLotteryDao = xcLotteryDao;
	}

	public void setXcSendRecordDao(IXcSendRecordDao xcSendRecordDao)
	{
		this.xcSendRecordDao = xcSendRecordDao;
	}

	public void setXcfeatureDao(IXcfeatureDao xcfeatureDao)
	{
		this.xcfeatureDao = xcfeatureDao;
	}
}
