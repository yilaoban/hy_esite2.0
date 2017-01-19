package com.huiyee.interact.cs.mgr;

import com.huiyee.interact.cs.dto.IDto;
import com.huiyee.interact.cs.model.ChuanSan;

public interface ICsMgr
{

	public ChuanSan findCsById(long id, long ownerid);

	public long saveChuanSan(ChuanSan cs, long ownerid, long omid);

	public int updateCs(ChuanSan rq, long id, long ownerid);

	public IDto findList(long ownerid, int pageId, long omid);

	public int updateCsJcontent(long id, long ownerid, String jsonStr);

	public int deleteContent(long id, long jid, long owner);

}
