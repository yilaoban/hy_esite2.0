package com.huiyee.interact.xc.dto;

import java.util.List;
import java.util.Map;

import com.huiyee.esite.dto.Pager;
import com.huiyee.interact.xc.model.XcSendRecord;

public class XcSendRecordDto implements IDto
{
	private Pager pager;
	private List<XcSendRecord> list;
	private Map<Long, Long> relationMap;//与申请record的对应关系

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	public List<XcSendRecord> getList()
	{
		return list;
	}

	public void setList(List<XcSendRecord> list)
	{
		this.list = list;
	}

	
	public Map<Long, Long> getRelationMap()
	{
		return relationMap;
	}

	
	public void setRelationMap(Map<Long, Long> relationMap)
	{
		this.relationMap = relationMap;
	}
}
