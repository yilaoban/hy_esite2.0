
package com.huiyee.esite.mgr.imp;

import com.huiyee.esite.dao.IGzEventDao;
import com.huiyee.esite.mgr.IGzEventMgr;
import com.huiyee.esite.model.WxGzEvent;
import com.huiyee.interact.xc.model.KeywordMsg;

public class GzEventImpl implements IGzEventMgr
{

	private IGzEventDao gzEventDao;

	public void setGzEventDao(IGzEventDao gzEventDao)
	{
		this.gzEventDao = gzEventDao;
	}

	@Override
	public long updateMsg(long id, String furl, KeywordMsg msg, long owner)
	{
		long msgid = 0;
		if (id == 0)
		{
			msgid = gzEventDao.addKeywordsMsg(msg);
			id = gzEventDao.addGzEvent(furl, msgid, owner);
		} else
		{
			if (msg.getId() == 0)
			{
				msgid = gzEventDao.addKeywordsMsg(msg);
			} else
			{
				gzEventDao.updateKeywordsMsg(msg);
				msgid = msg.getId();
			}
			gzEventDao.updateGzEvent(id, furl, msgid, owner);
		}

		return id;
	}

	@Override
	public WxGzEvent findGzEvent(long id)
	{
		return gzEventDao.findGzEvent(id);
	}

	@Override
	public int addSysKeywords(String openid, long msgid, long recordid,int type)
	{
		return gzEventDao.addSysKeywords(openid, msgid, recordid,type);
	}
}
