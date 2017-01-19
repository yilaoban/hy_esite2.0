package com.huiyee.interact.cs.service;

import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.cs.dto.RsCsDto;

public interface ICsDrawService
{
	public RsCsDto fuidDraw(VisitUser vu ,long csid,String jcon,String ip,String terminal);
}
