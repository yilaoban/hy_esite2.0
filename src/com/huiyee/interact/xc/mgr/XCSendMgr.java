package com.huiyee.interact.xc.mgr;

import com.huiyee.interact.xc.dao.IXcSendRecordDao;
import com.huiyee.interact.xc.model.XcSendRecord;

public class XCSendMgr implements IXCSendMgr
{
	private IXcSendRecordDao xcSendRecordDao;

	public void setXcSendRecordDao(IXcSendRecordDao xcSendRecordDao)
	{
		this.xcSendRecordDao = xcSendRecordDao;
	}

	@Override
	public XcSendRecord getSdRecord(long xcid, long uid, int utype)
	{
		return xcSendRecordDao.getSdRecord(xcid, uid, utype);
	}
}
