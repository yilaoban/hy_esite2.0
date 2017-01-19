package com.huiyee.interact.renqi.service;

import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.renqi.dto.RsRqDto;
import com.huiyee.interact.renqi.model.RenQi;

public interface IRenQiDrawService
{
	public RsRqDto bidOneRenQi(VisitUser vu ,RenQi rq,String ip,String terminal,String source);
	
	public void fuidShare(VisitUser vu , long rqid,String content,String ip,String terminal,String source);
}
