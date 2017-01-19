package com.huiyee.interact.spread.mgr;

import com.huiyee.interact.spread.dto.IDto;

public interface ISpreadRecordMgr
{

	public IDto findSpreadrecordList(int pageid, long spreadid, String begintime, String endtime, long type);
}
