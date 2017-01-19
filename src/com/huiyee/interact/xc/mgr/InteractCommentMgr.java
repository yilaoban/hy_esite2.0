package com.huiyee.interact.xc.mgr;

import java.util.List;

import com.huiyee.esite.model.WxUser;
import com.huiyee.interact.xc.model.XcCommentRecord;

public interface InteractCommentMgr
{

	public List<XcCommentRecord> findCommentRecordList(long xcid,long pageid,long start,int size);
	
	public int saveComment(XcCommentRecord xcRecord);
	
	public int findUserCommentNum(long uid,int utype,long xcid);
	
	public List<XcCommentRecord> findCommentRecord(long xcid,long start,int size);
	
	public List<XcCommentRecord> findCommentRecord1(long xcid, long start,int size);
	
	public int findCommenterTotal(long xcid);
	
	public int findCommenterTotal1(long xcid);

	public List<XcCommentRecord> findCommentWinner(int size);
}
