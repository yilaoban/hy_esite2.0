package com.huiyee.interact.lottery.dao;

import java.util.List;

import com.huiyee.esite.model.CrmWxHongbaoCheck;
import com.huiyee.esite.model.WxHbCheckLog;


public interface IWxHongbaoDao {
	
	public long saveCrmWxHongbaoCheck(int type,long lid,long mpid);
	
	public int updateCrmWxHongbaoCheck(long ckid,int type,long lid,long mpid);
	
	public int saveCrmWxHongbaoCheckLog(int total,long ckid,long account,String accountname,String reason,String ip);
	
	public CrmWxHongbaoCheck findCrmWxHongbaoCheckByLid(long lid,int type);
	
	/**
	 * 不带有reason。
	 * @param lid
	 * @return
	 */
	public CrmWxHongbaoCheck findWxHongbaoCheckByLid(long lid);
	
	public void saveWxHongbaoLotterySend(long lid,long lpid,long lurid,String openid,long mpid);
	
	public List<WxHbCheckLog> findWxHbCheckLogListBylid(long lid, int start, int rows);
	
	public void saveSysKeywords(long lid,long mpid,String openid,String keywords,int hbgz,long lpid,long rid);

	public CrmWxHongbaoCheck findCheckByTypeAndEnid(long enid, int type);

	/**
	 *  合伙人激励确认
	 * @param cbaid
	 * @param type
	 * @param used 
	 */
	public void updateCbActivityUsed(long enid, int type, int used);
}
