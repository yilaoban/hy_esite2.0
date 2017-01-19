package com.huiyee.esite.dao;

public interface ISinaForwardDao {
	
	public long saveSinaForwardDao(long shareid,long wbuid,long wbid,long mid,String content,String terminal,final String ip,final String source);
	
	public int updateShareCount(long shareid);
}
