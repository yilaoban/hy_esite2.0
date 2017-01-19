package com.huiyee.interact.cs.action;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.interact.cs.dto.CsDataDto;
import com.huiyee.interact.cs.mgr.CsRecordMgr;
import com.huiyee.interact.cs.mgr.ICsRecordMgr;

public class CsDataAction extends InteractModelAction
{
	private long csid;
	private int pageId = 1;
	private CsDataDto dto;
	private ICsRecordMgr csRecordMgr;
	private int type = 0;

	@Override
	public String execute() throws Exception
	{
		dto = (CsDataDto) csRecordMgr.findCsRecord(csid, type, pageId);
		return SUCCESS;
	}

	public long getCsid()
	{
		return csid;
	}

	public void setCsid(long csid)
	{
		this.csid = csid;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public CsDataDto getDto()
	{
		return dto;
	}

	public void setDto(CsDataDto dto)
	{
		this.dto = dto;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public void setCsRecordMgr(ICsRecordMgr csRecordMgr)
	{
		this.csRecordMgr = csRecordMgr;
	}
}
