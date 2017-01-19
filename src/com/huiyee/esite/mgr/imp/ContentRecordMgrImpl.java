package com.huiyee.esite.mgr.imp;

import java.util.List;

import com.huiyee.esite.dao.IContentRecordDao;
import com.huiyee.esite.mgr.IContentRecordMgr;
import com.huiyee.esite.model.ContentRecord;
import com.huiyee.esite.model.VisitUser;


public class ContentRecordMgrImpl implements IContentRecordMgr
{
	private IContentRecordDao contentRecordDao;

	public void setContentRecordDao(IContentRecordDao contentRecordDao)
	{
		this.contentRecordDao = contentRecordDao;
	}

	@Override
	public int saveContentRecord(VisitUser vu, ContentRecord contentRecord, String ip, String terminal)
	{
		long uid = vu.getUid();
		int utype = vu.getCd();
		contentRecord.setUid(uid);
		contentRecord.setUtype(utype);
		return contentRecordDao.saveContentRecord(vu, contentRecord, ip, terminal);
	}

	@Override
	public int findTotalContentReport(String type,int utype,long uid,String title)
	{
		return contentRecordDao.findTotalContentReport(type,utype,uid,title);
	}

	@Override
	public List<ContentRecord> findContentRecordList(String type,int utype,long uid,String title,int start, int size)
	{
		return contentRecordDao.findContentRecordList(type,utype,uid,title,start,size);
	}

	@Override
	public ContentRecord findContentRecordById(long id)
	{
		return contentRecordDao.findContentRecordById(id);
	}

	@Override
	public int updateMicroRecord(long id, ContentRecord cr)
	{
		return contentRecordDao.updateMicroRecord(id,cr);
	}
	
	
}
