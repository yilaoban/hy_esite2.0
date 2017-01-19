package com.huiyee.interact.checkin.mgr;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.mgr.imp.AbstractMgr;
import com.huiyee.interact.checkin.dao.ICheckinDao;
import com.huiyee.interact.checkin.dao.ICheckinRecordDao;
import com.huiyee.interact.checkin.dto.CheckinDto;
import com.huiyee.interact.checkin.dto.IDto;
import com.huiyee.interact.checkin.dto.Pager;
import com.huiyee.interact.checkin.model.CheckinRecord;

public class CheckinRecordMgr extends AbstractMgr implements ICheckinRecordMgr
{
	private ICheckinDao checkinDao;
	private ICheckinRecordDao checkinRecordDao;

	public void setCheckinDao(ICheckinDao checkinDao)
	{
		this.checkinDao = checkinDao;
	}

	public void setCheckinRecordDao(ICheckinRecordDao checkinRecordDao)
	{
		this.checkinRecordDao = checkinRecordDao;
	}

	@Override
	public void addCheckIn(long wbuid, long ownerwbuid, int addnum, int daynum, long pageid, String ip, String terminal, String source,int utype)
	{
		checkinRecordDao.addCheckIn(wbuid, ownerwbuid, addnum, daynum, pageid, ip, terminal, source,utype);
		//this.updateBalance(ownerwbuid, wbuid, addnum, "签到",utype);
	}

	@Override
	public CheckinRecord findLastCheckIn(long wbuid, long ownerwbuid,int utype)
	{
		return checkinRecordDao.findLastCheckIn(wbuid, ownerwbuid,utype);
	}

	@Override
	public IDto findCheckinData(long ownerwbuid,int utype, int pageid)
	{
		CheckinDto dto = new CheckinDto();
		int total = checkinRecordDao.findCheckinDataTotal(ownerwbuid,utype);
		dto.setPager(new Pager(pageid, total, IInteractConstants.CHECK_DATA_LIMIT));
		if (total > 0)
		{
			dto.setRecord(checkinRecordDao.findCheckinData(ownerwbuid,utype, (pageid - 1) * IInteractConstants.CHECK_DATA_LIMIT, IInteractConstants.CHECK_DATA_LIMIT));
		}
		return dto;
	}

	@Override
	public CheckinRecord findLastCheckInByHyuid(long hyuid)
	{
		return checkinRecordDao.findLastCheckInByHyuid(hyuid);
	}

	@Override
	public void addCheckIn(long hyuid, int addnum, int daynum, String ip, String terminal, String source)
	{
		checkinRecordDao.addCheckIn(hyuid, addnum, daynum, ip, terminal, source);
		this.updateBalance(hyuid, addnum, "签到成功", "NEW", "CHK", daynum);
	}
}
