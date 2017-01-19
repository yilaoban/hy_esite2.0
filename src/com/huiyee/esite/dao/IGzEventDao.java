
package com.huiyee.esite.dao;

import com.huiyee.esite.model.WxGzEvent;
import com.huiyee.interact.xc.model.KeywordMsg;

public interface IGzEventDao
{

	public KeywordMsg findMsgById(long id);

	public long addKeywordsMsg(KeywordMsg msg);

	public int updateKeywordsMsg(KeywordMsg msg);

	public int addSysKeywords(String openid, long msgid, long recordid,int type);

	public WxGzEvent findGzEvent(long id);

	public long addGzEvent(String furl, long msgid, long owner);

	public int updateGzEvent(long id, String furl, long msgid, long owner);

}
