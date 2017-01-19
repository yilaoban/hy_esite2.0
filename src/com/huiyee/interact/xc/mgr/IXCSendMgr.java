package com.huiyee.interact.xc.mgr;

import com.huiyee.interact.xc.model.XcSendRecord;

public interface IXCSendMgr
{
	public XcSendRecord getSdRecord(long xcid, long uid, int utype);
}
