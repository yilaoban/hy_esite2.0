package com.huiyee.interact.lottery.dao;

import java.util.List;

import com.huiyee.interact.lottery.model.WxHbLotterySend;


public interface IWxHbSendDao
{
	public int findWxHbSendTotal(long lpid,Integer errcode);
	
	public List<WxHbLotterySend> findWxHbSendList(long lpid,Integer errcode,int start, int rows );
	
	public int addToResend(long rid);
	
	public int updateSent(long id, String sent);
}
