package com.huiyee.interact.cb.dao;

import java.util.List;

import com.huiyee.interact.cb.model.CbSender;
import com.huiyee.interact.cb.model.SenderImpel;


public interface ICbSenderDao
{

	public List<CbSender> findSenderRecord(long cbid, int sendType, long owner, int start, int size);

	public int findSenderRecordTotal(long cbid, int sendType, long owner);
	
	public void updateCbSenderUsed(long id,int status);

	public CbSender findRecordById(long cbid, long rid);

	public int updateCbSenderSub(CbSender sender, String status, String reason, long id);

	public CbSender findSenderByUId(long cbid, long wxuid);
	
	public int findSender(long hyuid);

	public CbSender findRecordById(long sender);

	public List<CbSender> findSenderByTime(long cbid, String endtime, int start, int size);

	public void addHbtotal(SenderImpel si, int subPrice);

	/**
	 * 所有销售员total
	 * @param cbid
	 * @param name
	 * @return
	 */
	public int findSenderTotal(long owner, String name);

	/**
	 * 所有销售员
	 * @param cbid
	 * @param name
	 * @param start
	 * @param size
	 * @return
	 */
	public List<CbSender> findSenderByCbid(long owner, String name, int start, int size);

	/**
	 * 删除合伙人
	 * @param owner
	 * @param uid
	 */
	public void delSender(long owner, long uid);
	
	public CbSender findRecordByUid(long cbid, long uid);

}
