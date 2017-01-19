package com.huiyee.esite.dao;

public interface IOwnerBalanceRecordDao
{
	public void addRecord( long hyuid, int balance, String desc,String type,String stype,long enid);
	
	public long addRmbRecord( long hyuid, int balance, String desc,String type,String stype,long enid);
}
