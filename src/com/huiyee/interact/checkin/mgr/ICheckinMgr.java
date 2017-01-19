package com.huiyee.interact.checkin.mgr;

import java.util.List;

import com.huiyee.interact.checkin.dto.IDto;
import com.huiyee.interact.checkin.model.Checkin;
import com.huiyee.interact.checkin.model.CheckinBalance;
import com.huiyee.interact.checkin.model.CheckinRecord;

public interface ICheckinMgr
{
	public Checkin findCheckRule(long ownerwbuid,int utype);

	public IDto findCheckRuleList(long ownerid,int pageid);//
	
	public IDto findWxCheckRuleList(long ownerid,int pageid);//

	public int updateCheckin(Checkin in);//

	public int delCheckin(long ownerwbuid,int utype);//
	
	public CheckinBalance findBalance(long ownerwbuid,long wbuid,int utype);
	
	public int findCheckinBalanceCount(long ownerwbuid,long wbuid,int utype);
	
	public List<CheckinRecord> findCheckinBalance(long ownerwbuid,long wbuid,int utype);
	
	public IDto findCheckinRecordList(int pageId,long owner);
	
	public Checkin findCheckRuleByOwner(long owner);
}
