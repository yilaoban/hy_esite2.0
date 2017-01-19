package project.caidan.dao;

import java.util.List;

import project.caidan.model.CdRmbRecord;


public interface ICdRmbRecordDao
{
	public List<CdRmbRecord> findList(long wxuid,int start,int size);
	
	public int findRmbRecordByWxuid(long wxuid);
}
