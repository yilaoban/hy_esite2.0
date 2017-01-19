package com.huiyee.esite.dao;

import java.util.List;

import com.huiyee.esite.model.AppointmentRecord;
import com.huiyee.esite.model.HdModel;
import com.huiyee.esite.model.HdRecord;
import com.huiyee.esite.model.ZanDetail;

public interface IHdRecordDao {

	public List<HdRecord> findHdRecordBySitegroupid(long sitegroupid);
	
	public List<Long> findHdTypeBySitegroupid(long sitegroupid);
	
	public List<HdRecord> findHdRecordNoProcess(int size);
	
	public int updateHdReocrdFansStatus(long id,String type);
	
	public List<HdModel> findHdModelListBySgid(long sgid);
	
	public int findHdReportNumTotal(long sgid);
	
	public int updateUserInfoStatus(long wbuid);
	
	public List<ZanDetail> findZanDetail(long productid,int start,int size);
	
	public int findZanDetailTotal(long productid);

	public AppointmentRecord findAptRecord(long recordid);
	
	public AppointmentRecord findAptRecord(long aptid, long wbuid, int type);
}
