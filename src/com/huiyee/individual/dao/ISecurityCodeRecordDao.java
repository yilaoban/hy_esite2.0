package com.huiyee.individual.dao;

import java.util.List;

import com.huiyee.individual.model.SecurityCodeRecord;

public interface ISecurityCodeRecordDao
{

	public int updateSecurityCodeRecordStatus(long id,String status);
	
	public int updateSecurityCodeRecordPayed(long id,int payed);
	
	public int saveUser(long pageid,long entityid,int type,String code1,String code2,String code3,String code4,String phone,String address);
	
	public SecurityCodeRecord findbyUser(long entityid,int type);
	
	public int updateUser(String code1,String code2,String code3,String code4,long entityid,String phone,String address);
	
	public int updateUser(String code1,String code2,long entityid,int num,SecurityCodeRecord scr,String phone,String address);
	
	public List<SecurityCodeRecord> findInfo(String edt,String cmp,String fld,String starttime,String endtime,String submitstarttime,String submitendtime);
}
