package com.huiyee.esite.mgr.imp;

import com.huiyee.esite.dao.IDynamicActionRecordDao;
import com.huiyee.esite.dao.ISinaForwardDao;
import com.huiyee.esite.mgr.ISinaForwardManager;

public class SinaForwardManagerImpl implements ISinaForwardManager {

	private ISinaForwardDao sinaForwardDao;
	private IDynamicActionRecordDao dynamicActionRecordDao;

	public void setSinaForwardDao(ISinaForwardDao sinaForwardDao) {
		this.sinaForwardDao = sinaForwardDao;
	}

	@Override
	public long saveSinaForward(long wbuid, long wbid, long mid, long shareid,
			String content, String terminal, long pageid, String source,
			String ip) {
		long result = sinaForwardDao.saveSinaForwardDao(shareid, wbuid, wbid, mid, content, terminal,ip,source);
		if(result > 0){
			sinaForwardDao.updateShareCount(shareid);	
//			dynamicActionRecordDao.addDynamicActionRecord(pageid, wbuid, 117, result, source, ip, terminal);
		}
		return result;
	}

	public void setDynamicActionRecordDao(
			IDynamicActionRecordDao dynamicActionRecordDao) {
		this.dynamicActionRecordDao = dynamicActionRecordDao;
	}
}
