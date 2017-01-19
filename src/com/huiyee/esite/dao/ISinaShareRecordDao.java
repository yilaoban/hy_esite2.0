package com.huiyee.esite.dao;

import java.util.List;

import com.huiyee.esite.model.SinaChecklistRecord;
import com.huiyee.esite.model.SinaShareRecord;
import com.huiyee.tfmodel.HyWbSrc;

public interface ISinaShareRecordDao {
	
	public List<SinaShareRecord> findRecordByFid(long fid, int page);
	
	public List<SinaChecklistRecord> findRecordByShareid(long shareid,long fid);

	public void updateRecord(long id, HyWbSrc status);

	public long saveSinaShareRecord(long shareid, HyWbSrc status,String bimg,String mimg,String simg, String terminal,final String ip,final String source);

	public List<SinaChecklistRecord> findExport(long sitegroupid, long ownerid);
	
}
