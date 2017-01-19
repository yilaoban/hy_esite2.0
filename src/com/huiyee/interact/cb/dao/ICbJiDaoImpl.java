
package com.huiyee.interact.cb.dao;

import java.util.List;
import java.util.Map;

import com.huiyee.interact.cb.model.CbImpel;
import com.huiyee.interact.cb.model.SenderImpel;

public interface ICbJiDaoImpl
{

	public int findJiliTotal(long cbaid, String name, String starttime, String endtime, String status);

	public List<SenderImpel> findSenderImpel(long cbid, String name, String starttime, String endtime, int start, int size, String status);

	public int findSenderJiTotal(long sender, String starttime, String endtime);

	public List<CbImpel> findSenderImpelDetail(long senderid, String starttime, String endtime, int i, int cbImpelLimit);

	public SenderImpel findJiRecordById(int impelId);

	public void updatejISub(int impelId, int subPrice);
}
