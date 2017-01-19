package com.huiyee.esite.dao;

public interface ISinaCommentDao {
	
	public long saveSinaCommentDao(long shareid,long wbuid,long fatherwbid,long wbid,String content,String terminal,String ip,final String source);
	
	public int updateShareCount(long shareid);
	
}
