package com.huiyee.esite.mgr;

public interface ISinaCommentManager {

	public long saveSinaComment(long wbuid,long fatherwbid,long wbid,long shareid,String content,String terminal,long pageid,String source,String ip);
}
