package com.huiyee.interact.renqi.mgr;

import com.huiyee.interact.renqi.dto.IDto;
import com.huiyee.interact.renqi.dto.RqDto;
import com.huiyee.interact.renqi.model.RenQi;

public interface IRenQiMgr
{
	public RenQi findRenQiById(long id);

	public IDto findOwnerRenqi(long ownerid, int pageId);

	public long saveRenQi(RenQi rq, long ownerid);

	public IDto addPre(long ownerid);

	public int updateRenQi(RenQi rq, long id, long ownerid);

	public IDto editPre(long ownerid, long id);

	public int updateStatus(long id, String string, long id2);

}