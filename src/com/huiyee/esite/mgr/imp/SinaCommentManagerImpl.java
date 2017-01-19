package com.huiyee.esite.mgr.imp;

import com.huiyee.esite.dao.ISinaCommentDao;
import com.huiyee.esite.mgr.ISinaCommentManager;

public class SinaCommentManagerImpl extends AbstractMgr implements ISinaCommentManager{
	private ISinaCommentDao sinaCommentDao;
	
	public void setSinaCommentDao(ISinaCommentDao sinaCommentDao)
	{
		this.sinaCommentDao = sinaCommentDao;
	}

	@Override
	public long saveSinaComment(long wbuid, long fatherwbid, long wbid, long shareid,
			String content, String terminal, long pageid, String source,
			String ip) {
		long result = sinaCommentDao.saveSinaCommentDao(shareid, wbuid, fatherwbid, wbid, content, terminal,ip,source);
		if(result > 0){
			sinaCommentDao.updateShareCount(shareid);	
//			dynamicActionRecordDao.addDynamicActionRecord(pageid, wbuid, 120, result, source, ip, terminal);
		}
		return result;
	}
}
