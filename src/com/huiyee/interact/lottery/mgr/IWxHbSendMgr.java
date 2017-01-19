package com.huiyee.interact.lottery.mgr;

import com.huiyee.interact.lottery.dto.IDto;

public interface IWxHbSendMgr
{
	public IDto findWxHbSendList(long lpid,Integer errcode,int pageId);
	
	public int addToResend(long rid);
	
	public int updateSent(long id, String sent);
}
