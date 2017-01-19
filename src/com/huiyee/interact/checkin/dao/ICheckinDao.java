package com.huiyee.interact.checkin.dao;

import java.util.List;

import com.huiyee.interact.checkin.model.Checkin;
import com.huiyee.interact.checkin.model.CheckinBalance;
import com.huiyee.interact.checkin.model.CheckinRecord;

public interface ICheckinDao
{
	public Checkin findCheckRule(long ownerwbuid,int utype);

	public List<Checkin> findCheckRuleList(long ownerid,int start,int size);
	
	public int findCheckRuleTotal(long ownerid);
	
	public List<Checkin> findWxCheckRuleList(long ownerid,int start,int size);
	
	public int findWxCheckRuleTotal(long ownerid);

	public int updateCheckin(Checkin in);

	public int delCheckin(long ownerwbuid,int utype);
	
	public CheckinBalance findBalance(long ownerwbuid, long wbuid, int utype);
	
	public int findCheckinBalanceCount(long ownerwbuid, long wbuid, int utype);
	
	public List<CheckinRecord> findCheckinBalance(long ownerwbuid, long wbuid, int utype);
	
	public int findCheckinRecordListTotal(long owner);
	
	public List<CheckinRecord> findCheckinRecordList(long owner,int start,int size);
	
	public Checkin findCheckRuleByOwner(long owner);
}
