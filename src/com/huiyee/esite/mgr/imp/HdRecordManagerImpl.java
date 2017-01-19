package com.huiyee.esite.mgr.imp;

import java.util.List;

import com.huiyee.esite.dao.IHdRecordDao;
import com.huiyee.esite.mgr.IHdRecordManager;
import com.huiyee.esite.model.AppointmentRecord;
import com.huiyee.esite.model.HdModel;
import com.huiyee.esite.model.HdRecord;
import com.huiyee.esite.model.ZanDetail;

public class HdRecordManagerImpl implements IHdRecordManager{
	
	private IHdRecordDao hdRecordDao;

	public void setHdRecordDao(IHdRecordDao hdRecordDao) {
		this.hdRecordDao = hdRecordDao;
	}

	@Override
	public List<HdRecord> findHdRecordBySitegroupid(long sitegroupid) {
		return hdRecordDao.findHdRecordBySitegroupid(sitegroupid);
	}

	@Override
	public List<Long> findHdTypeBySitegroupid(long sitegroupid) {
		return hdRecordDao.findHdTypeBySitegroupid(sitegroupid);
	}

	@Override
	public List<HdRecord> findHdRecordNoProcess(int size) {
		return hdRecordDao.findHdRecordNoProcess(size);
	}

	@Override
	public int updateHdReocrdFansStatus(long id,String type) {
		return hdRecordDao.updateHdReocrdFansStatus(id, type);
	}

	@Override
	public List<HdModel> findHdModelListBySgid(long sgid)
	{
		return hdRecordDao.findHdModelListBySgid(sgid);
	}

	@Override
	public int findHdReportNumTotal(long sgid)
	{
		return hdRecordDao.findHdReportNumTotal(sgid);
	}

	@Override
	public List<ZanDetail> findZanDetail(long productid, int start, int size)
	{
		return hdRecordDao.findZanDetail(productid, start, size);
	}

	@Override
	public int findZanDetailTotal(long productid)
	{
		return hdRecordDao.findZanDetailTotal(productid);
	}
	
	@Override
	public AppointmentRecord findAptRecord(long recordid)
	{
		return hdRecordDao.findAptRecord(recordid);
	}

	@Override
	public AppointmentRecord findAptRecord(long aptid, long wbuid, int type)
	{
		return hdRecordDao.findAptRecord(aptid, wbuid, type);
	}
}
