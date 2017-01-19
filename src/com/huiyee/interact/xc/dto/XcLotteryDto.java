package com.huiyee.interact.xc.dto;

import java.util.List;
import java.util.Map;

import com.huiyee.esite.model.Site;
import com.huiyee.interact.auction.model.Pager;
import com.huiyee.interact.cs.dto.IDto;
import com.huiyee.interact.xc.model.Xc;
import com.huiyee.interact.xc.model.XcCheckinRecord;
import com.huiyee.interact.xc.model.XcCjRecord;
import com.huiyee.interact.xc.model.XcCommentRecord;
import com.huiyee.interact.xc.model.XcInviteRecord;
import com.huiyee.interact.xc.model.XcSite;

public class XcLotteryDto implements IDto {
	
	private List<Xc> xcList;
	private Pager pager;
	private List<XcInviteRecord> inviteRecordList;
	private List<XcCheckinRecord> checkinRecordList;
	private List<XcCommentRecord> commentRecordList;
	private List<XcCjRecord> cjRecordList;
	private List<Site> siteList;
	private Map<Long, Long> relationMap;//与申请record的对应关系
	
	
	public Map<Long, Long> getRelationMap()
	{
		return relationMap;
	}

	
	public void setRelationMap(Map<Long, Long> relationMap)
	{
		this.relationMap = relationMap;
	}

	public List<Site> getSiteList()
	{
		return siteList;
	}

	public void setSiteList(List<Site> siteList)
	{
		this.siteList = siteList;
	}

	public List<XcCheckinRecord> getCheckinRecordList()
	{
		return checkinRecordList;
	}

	public void setCheckinRecordList(List<XcCheckinRecord> checkinRecordList)
	{
		this.checkinRecordList = checkinRecordList;
	}

	public List<XcCommentRecord> getCommentRecordList()
	{
		return commentRecordList;
	}

	public void setCommentRecordList(List<XcCommentRecord> commentRecordList)
	{
		this.commentRecordList = commentRecordList;
	}

	public List<XcCjRecord> getCjRecordList()
	{
		return cjRecordList;
	}

	public void setCjRecordList(List<XcCjRecord> cjRecordList)
	{
		this.cjRecordList = cjRecordList;
	}

	public List<XcInviteRecord> getInviteRecordList()
	{
		return inviteRecordList;
	}

	public void setInviteRecordList(List<XcInviteRecord> inviteRecordList)
	{
		this.inviteRecordList = inviteRecordList;
	}

	public List<Xc> getXcList()
	{
		return xcList;
	}

	public void setXcList(List<Xc> xcList)
	{
		this.xcList = xcList;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}
	
	
}
