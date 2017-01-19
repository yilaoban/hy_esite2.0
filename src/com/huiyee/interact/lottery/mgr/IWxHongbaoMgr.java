package com.huiyee.interact.lottery.mgr;

import java.util.List;

import com.huiyee.esite.model.CrmWxHongbaoCheck;
import com.huiyee.esite.model.WxHbCheckLog;

public interface IWxHongbaoMgr{
	
	public int saveHongbaoCheck(long lid,int total,String reason,long account,int type,String accountname,String ip,long ckid,long mpid);
	
	public CrmWxHongbaoCheck findCrmWxHongbaoCheckByLid(long lid,int type);
	
	
	/**
	 * 不带有reason。
	 * @param lid
	 * @return
	 */
	public CrmWxHongbaoCheck findWxHongbaoCheckByLid(long lid);
	
	public void saveWxHongbaoLotterySend(long lid,long lpid,long lurid,String openid,long mpid);
	
	public List<WxHbCheckLog> findWxHbCheckLogListBylid(long lid, int start, int rows);
	/**
	 * 保存红包口令任务
	 * @param openid
	 * @param keywords
	 * @param hbgz
	 * @param lpid
	 * @param rid
	 */
	public void saveSysKeywords(long lid,long mpid,String openid,String keywords,int hbgz,long lpid,long rid);
}
