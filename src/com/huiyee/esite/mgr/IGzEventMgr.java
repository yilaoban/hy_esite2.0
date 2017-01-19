
package com.huiyee.esite.mgr;

import com.huiyee.esite.model.WxGzEvent;
import com.huiyee.interact.xc.model.KeywordMsg;

public interface IGzEventMgr
{

	/**
	 * 
	 * @param failurl
	 *            Ê§°ÜÌø×ªµÄÁ´½Ó
	 * @param keyMsg
	 * @param enid
	 * @param type
	 *            X-xc;L-lottery;C-wei checkin
	 * @return
	 */
	public long updateMsg(long id, String failurl, KeywordMsg keyMsg, long owner);

	public WxGzEvent findGzEvent(long id);
	
	public int addSysKeywords(String openid, long msgid, long recordid,int type);
}
