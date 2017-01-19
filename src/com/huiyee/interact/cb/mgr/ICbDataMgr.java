
package com.huiyee.interact.cb.mgr;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.Account;
import com.huiyee.interact.cb.model.CbSender;
import com.huiyee.interact.cb.model.HbConfig;

public interface ICbDataMgr
{

	public IDto findSenderRecordList(long cbid, int sendType, long id, int pageid);
	
	public CbSender findSenderByUId(long owner, long wxuid);
	
	public int findSender(long hyuid);

	public IDto findSenderRecordList(long cbid, long rid, long id);

	public int updateCbSenderSub(long cbid, long rid, String status, String reason, Account account);

	/**
	 * 激励记录
	 * 
	 * @param account
	 * @param cbid
	 * @param name
	 * @param pageId
	 * @param endtime
	 * @param starttime
	 * @param status 
	 * @return
	 */
	public IDto findJiliRecord(Account account, long cbid, int pageId, String name, String starttime, String endtime, String status);

	/**
	 * 单个sender的激励记录
	 * 
	 * @param account
	 * @param cbid
	 * @param sender
	 * @param pageId
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public IDto findSenderDetail(Account account, long cbid, long sender, int pageId, String starttime, String endtime);

	public IDto findSenderEffect(long owner, long cbaid, int pageId, String starttime, String endtime, String order);

	public IDto findJiliList(long cbaid, Account account, String name, String starttime, String endtime, int pageId, String status);

	public int findCbExist(Account account);

	public int addInteractCb(Account account);

	public IDto findCbActivity(Account account, int pageId);

	public int updateJiliSub(int impelId, int subPrice);

	public HbConfig findHbConfig(Account account);

	public int updateHbConfig(Account account, HbConfig hbc);

	public IDto findFundsRecord(Account account, long sender, String name, String starttime, String endtime, int pageId);

	public IDto findCbSender(Account account, String name, int pageId);
	
	public int updateCbSendCheck(long id,int status);

}
