package com.huiyee.interact.renqi.action;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.interact.renqi.dto.RenQiDataDto;
import com.huiyee.interact.renqi.mgr.IRenQiDataMgr;

public class RenQiDataAction extends InteractModelAction
{
	private int pageId = 1;
	private int utype = 0;
	private long rqid;
	private long fuid;
	private RenQiDataDto dto;
	private IRenQiDataMgr renQiDataMgr;

	public String execute() throws Exception
	{
		dto = (RenQiDataDto) renQiDataMgr.findList(rqid, pageId, utype);
		return SUCCESS;
	}

	public String detail() throws Exception
	{
		dto = (RenQiDataDto) renQiDataMgr.findDetail(rqid, fuid,pageId);
		return SUCCESS;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public int getUtype()
	{
		return utype;
	}

	public void setUtype(int utype)
	{
		this.utype = utype;
	}

	public RenQiDataDto getDto()
	{
		return dto;
	}

	public void setDto(RenQiDataDto dto)
	{
		this.dto = dto;
	}

	public long getRqid()
	{
		return rqid;
	}

	public void setRqid(long rqid)
	{
		this.rqid = rqid;
	}

	public void setRenQiDataMgr(IRenQiDataMgr renQiDataMgr)
	{
		this.renQiDataMgr = renQiDataMgr;
	}

	public long getFuid()
	{
		return fuid;
	}

	public void setFuid(long fuid)
	{
		this.fuid = fuid;
	}
}
