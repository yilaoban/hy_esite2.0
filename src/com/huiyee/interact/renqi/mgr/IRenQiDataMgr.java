package com.huiyee.interact.renqi.mgr;

import com.huiyee.interact.renqi.dto.IDto;
import com.huiyee.interact.renqi.dto.RenQiDataDto;

public interface IRenQiDataMgr
{

	public IDto findList(long rqid, int pageId, int utype);

	public IDto findDetail(long rqid, long fuid, int pageId);

}
