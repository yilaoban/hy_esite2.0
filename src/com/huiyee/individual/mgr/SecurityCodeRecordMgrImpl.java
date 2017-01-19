package com.huiyee.individual.mgr;


import com.huiyee.individual.dao.ISecurityCodeRecordDao;
import com.huiyee.individual.dto.IDto;
import com.huiyee.individual.dto.SecurityCodeRecordDto;
import com.huiyee.individual.model.SecurityCodeRecord;

public class SecurityCodeRecordMgrImpl implements ISecurityCodeRecordMgr
{
	private ISecurityCodeRecordDao securitycoderecordDao;

	@Override
	public int updateSecurityCodeRecordPayed(long id, int payed)
	{
		return securitycoderecordDao.updateSecurityCodeRecordPayed(id, payed);
	}

	@Override
	public int updateSecurityCodeRecordStatus(long id, String status)
	{
		return securitycoderecordDao.updateSecurityCodeRecordStatus(id, status);
	}

	public ISecurityCodeRecordDao getSecuritycoderecordDao()
	{
		return securitycoderecordDao;
	}

	public void setSecuritycoderecordDao(ISecurityCodeRecordDao securitycoderecordDao)
	{
		this.securitycoderecordDao = securitycoderecordDao;
	}

	@Override
	public int saveUser(long pageid, long entityid, int type, String code1, String code2, String code3, String code4, String phone, String address)
	{
		return securitycoderecordDao.saveUser(pageid,entityid,type,code1,code2,code3,code4,phone,address);
	}

	@Override
	public SecurityCodeRecord findbyUser(long entityid,int type)
	{
		return securitycoderecordDao.findbyUser(entityid,type);
	}

	@Override
	public int updateUser(String code1, String code2, String code3, String code4,long entityid,String phone,String address)
	{
		return securitycoderecordDao.updateUser(code1,code2,code3,code4,entityid,phone,address);
	}

	@Override
	public int updateUser(String code1, String code2,long entityid,int num,SecurityCodeRecord scr,String phone,String address)
	{
		return securitycoderecordDao.updateUser(code1,code2,entityid,num,scr,phone,address);
	}

	@Override
	public IDto findInfo(String edt,String cmp,String fld,String starttime,String endtime,String submitstarttime,String submitendtime)
	{
		SecurityCodeRecordDto dto=new SecurityCodeRecordDto();
		dto.setList(securitycoderecordDao.findInfo(edt,cmp,fld, starttime, endtime,submitstarttime,submitendtime));
		return dto;
	}


}
