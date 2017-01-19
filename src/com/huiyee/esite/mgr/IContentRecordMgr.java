package com.huiyee.esite.mgr;

import java.util.List;

import com.huiyee.esite.model.ContentRecord;
import com.huiyee.esite.model.VisitUser;


public interface IContentRecordMgr
{
	public int saveContentRecord(VisitUser visit,ContentRecord contentRecord,String ip,String terminal);
	
	public int findTotalContentReport(String type,int utype,long uid,String title);
	
	public List<ContentRecord> findContentRecordList(String type,int utype,long uid,String title,int start,int size);
	
	public ContentRecord findContentRecordById(long id);
	
	public int updateMicroRecord(long id,ContentRecord cr);

}
