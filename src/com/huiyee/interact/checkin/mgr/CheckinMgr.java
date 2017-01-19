package com.huiyee.interact.checkin.mgr;

import java.util.List;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.interact.checkin.dao.ICheckinDao;
import com.huiyee.interact.checkin.dto.CheckinDto;
import com.huiyee.interact.checkin.dto.IDto;
import com.huiyee.interact.checkin.dto.Pager;
import com.huiyee.interact.checkin.model.Checkin;
import com.huiyee.interact.checkin.model.CheckinBalance;
import com.huiyee.interact.checkin.model.CheckinRecord;

public class CheckinMgr implements ICheckinMgr
{
	private ICheckinDao checkinDao;

	public void setCheckinDao(ICheckinDao checkinDao)
	{
		this.checkinDao = checkinDao;
	}

	@Override
	public Checkin findCheckRule(long ownerwbuid,int utype)
	{
		return checkinDao.findCheckRule(ownerwbuid,utype);
	}

	@Override
	public IDto findCheckRuleList(long ownerid,int pageid)
	{
		CheckinDto dto = new CheckinDto();
		int total = checkinDao.findCheckRuleTotal(ownerid);
		dto.setPager(new Pager(pageid, total, IInteractConstants.CHECK_LIMIT));
		if(total > 0){
			dto.setList(checkinDao.findCheckRuleList(ownerid,(pageid - 1) * IInteractConstants.CHECK_LIMIT, IInteractConstants.CHECK_LIMIT));
		}
		return dto;
	}
	
	@Override
	public IDto findWxCheckRuleList(long ownerid,int pageid)
	{
		CheckinDto dto = new CheckinDto();
		int total = checkinDao.findWxCheckRuleTotal(ownerid);
		dto.setPager(new Pager(pageid, total, IInteractConstants.CHECK_LIMIT));
		if(total > 0){
			dto.setList(checkinDao.findWxCheckRuleList(ownerid,(pageid - 1) * IInteractConstants.CHECK_LIMIT, IInteractConstants.CHECK_LIMIT));
		}
		return dto;
	}

	@Override
	public int updateCheckin(Checkin in)
	{
		return checkinDao.updateCheckin(in);
	}

	@Override
	public int delCheckin(long ownerwbuid ,int utype)
	{
		return checkinDao.delCheckin(ownerwbuid,utype);
	}

	@Override
	public CheckinBalance findBalance(long ownerwbuid, long wbuid, int utype)
	{
		return checkinDao.findBalance(ownerwbuid,wbuid,utype);
	}

	@Override
	public int findCheckinBalanceCount(long ownerwbuid, long wbuid, int utype)
	{
		return checkinDao.findCheckinBalanceCount(ownerwbuid,wbuid,utype);
	}

	@Override
	public List<CheckinRecord> findCheckinBalance(long ownerwbuid, long wbuid, int utype)
	{
		return checkinDao.findCheckinBalance(ownerwbuid,wbuid,utype);
	}

	@Override
	public IDto findCheckinRecordList(int pageId,long owner)
	{
		CheckinDto dto = new CheckinDto();
		int total = checkinDao.findCheckinRecordListTotal(owner);
		if(total > 0){
			List<CheckinRecord> list = checkinDao.findCheckinRecordList(owner,(pageId - 1) * IInteractConstants.CHECK_LIMIT, IInteractConstants.CHECK_LIMIT);
			dto.setCheckinRecordList(list);
		}
		dto.setPager(new Pager(pageId, total, IInteractConstants.CHECK_LIMIT));
		return dto;
	}

	@Override
	public Checkin findCheckRuleByOwner(long owner)
	{
		return checkinDao.findCheckRuleByOwner(owner);
	}

}
