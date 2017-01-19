package com.huiyee.interact.renqi.mgr;

import com.huiyee.interact.renqi.dao.IRenQiRecordDao;
import com.huiyee.interact.renqi.model.FUidJf;

public class RenQiRecordMgr implements IRenQiRecordMgr
{
	private IRenQiRecordDao renQiRecordDao;

	@Override
	public void addRqDuidDraw(long rqid, long duid, long fuid, int utype, int addjf, String ip, String terminal, String source)
	{
		renQiRecordDao.addRqDuidDraw(rqid, duid, fuid, utype, addjf, ip, terminal, source);
		renQiRecordDao.updateRqFuidJfTotal(rqid, fuid, utype, addjf);
	}

	public void setRenQiRecordDao(IRenQiRecordDao renQiRecordDao)
	{
		this.renQiRecordDao = renQiRecordDao;
	}

	@Override
	public FUidJf findFuidJf(long rqid, long fuid, int utype)
	{
		return renQiRecordDao.findFuidJf(rqid, fuid, utype);
	}

	@Override
	public void updateJf2Lottery(long rqid, long fuid, int utype, int cnum, long lid)
	{
		renQiRecordDao.updateRqFuidJfUsed(rqid, fuid, utype, cnum);
		renQiRecordDao.addLotteryUserTotal(lid, fuid, utype, 1);
	}

	@Override
	public int findDuidDraw(long rqid, long duid, long fuid, int utype)
	{
		return renQiRecordDao.findDuidDraw(rqid, duid, fuid, utype);
	}

	@Override
	public void addFuidShare(long rqid, long fuid, int utype, String content, String ip, String terminal, String source)
	{
		renQiRecordDao.addFuidShare(rqid, fuid, utype, content, ip, terminal, source);
		renQiRecordDao.addRqFuidJf(rqid, fuid, utype);
	}

}
