package com.huiyee.interact.xc.dao;

import java.util.List;

import com.huiyee.esite.model.SinaUser;
import com.huiyee.esite.model.WxUser;
import com.huiyee.interact.xc.model.XcCommentRecord;

public interface InteractCommentDao
{

	public int saveComment(XcCommentRecord xcRecord);
	
	public List<XcCommentRecord> findCommentRecordList(long xcid,long pageid,long start,int size);
	
	public int commentRecordTotal(long commentid);
	
	public int findUserCommentNum(long uid,int utype,long xcid);
	
	public List<XcCommentRecord> findCommentRecord(long xcid, long start,int size);
	
	public List<XcCommentRecord> findCommentRecord1(long xcid, long start,int size);
	
	public WxUser findWxUser(long wxuid);
	
	public SinaUser findSinaUser(long wbuid);
	
	public int findCommenterTotal(long xcid);
	
	public int findCommenterTotal1(long xcid);

	public List<Long> findCommentUser(int size);

	public XcCommentRecord findLastComment(Long wxuid);
	
}
