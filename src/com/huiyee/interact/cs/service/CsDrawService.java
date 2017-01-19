package com.huiyee.interact.cs.service;

import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.cs.dto.RsCsDto;
import com.huiyee.interact.cs.mgr.ICsRecordMgr;

public class CsDrawService implements ICsDrawService
{
	private ICsRecordMgr csRecordMgr;

	@Override
	public RsCsDto fuidDraw(VisitUser vu, long csid, String jcon, String ip, String terminal)
	{
		RsCsDto rs = new RsCsDto();
		if (vu == null)
		{
			rs.setStatus(-1);
			rs.setHydesc("visituser²»´æÔÚ");
			return rs;
		}
		long uid = vu.getUid();
		int utype = vu.getCd();
	
		long rid = csRecordMgr.addCsFuidDraw(csid, uid, utype, jcon, ip, terminal, vu.getSource());
		rs.setStatus(1);
		rs.setRid(rid);
		return rs;
	}

	public void setCsRecordMgr(ICsRecordMgr csRecordMgr)
	{
		this.csRecordMgr = csRecordMgr;
	}

}
