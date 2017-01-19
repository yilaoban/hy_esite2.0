package com.huiyee.esite.mgr.imp;

import java.util.List;

import com.huiyee.esite.dao.IDynamicActionRecordDao;
import com.huiyee.esite.dao.ISinaShareDao;
import com.huiyee.esite.dao.ISinaShareRecordDao;
import com.huiyee.esite.mgr.ISinaShareRecordManager;
import com.huiyee.esite.model.SinaChecklistRecord;
import com.huiyee.esite.model.SinaShareRecord;
import com.huiyee.tfmodel.HyWbSrc;

public class SinaShareRecordManagerImpl implements ISinaShareRecordManager {
	private ISinaShareRecordDao sinaShareRecordDao;
	private ISinaShareDao sinaShareDao;
	private IDynamicActionRecordDao dynamicActionRecordDao;

	public void setSinaShareRecordDao(ISinaShareRecordDao sinaShareRecordDao) {
		this.sinaShareRecordDao = sinaShareRecordDao;
	}

	@Override
	public List<SinaShareRecord> findRecordByFid(long fid, int page) {
		return sinaShareRecordDao.findRecordByFid(fid, page);
	}

	@Override
	public void updateRecord(long id, HyWbSrc status) {
		sinaShareRecordDao.updateRecord(id, status);
	}

	@Override
	public long saveSinaShareRecord(long shareid, HyWbSrc status,String bimg,String mimg,String simg, String terminal,long pageid,String source,String ip) {
		long result = sinaShareRecordDao.saveSinaShareRecord(shareid, status, bimg, mimg, simg, terminal,ip,source);
		if(result > 0){
			sinaShareDao.updateShareCount(shareid);	
//			dynamicActionRecordDao.addDynamicActionRecord(pageid, Long.parseLong(status.getUser().getId()), 113, result, source, ip, terminal);
		}
		return result;
	}

	public void setSinaShareDao(ISinaShareDao sinaShareDao) {
		this.sinaShareDao = sinaShareDao;
	}

	@Override
	public List<SinaChecklistRecord> findRecordByShareid(long shareid, long fid) {
		return sinaShareRecordDao.findRecordByShareid(shareid, fid);
	}

	public void setDynamicActionRecordDao(
			IDynamicActionRecordDao dynamicActionRecordDao) {
		this.dynamicActionRecordDao = dynamicActionRecordDao;
	}
}
