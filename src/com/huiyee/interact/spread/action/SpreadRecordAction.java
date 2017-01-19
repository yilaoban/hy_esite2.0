package com.huiyee.interact.spread.action;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.interact.spread.dto.SpreadRecordDto;
import com.huiyee.interact.spread.mgr.ISpreadRecordMgr;

public class SpreadRecordAction extends InteractModelAction
{
	private static final long serialVersionUID = 3913889817791350944L;
	private ISpreadRecordMgr spreadRecordManager;
	private SpreadRecordDto dto;
	private int pageId = 1;
	private String nickname;
	private String pic;
	private String content;
	private String begintime;
	private String endtime;
	private long spreadid;
	private long mid = 10010;
	private long type=0;

	@Override
	public String execute() throws Exception
	{
		dto =  (SpreadRecordDto) spreadRecordManager.findSpreadrecordList(pageId,spreadid,begintime,endtime,type);
		return SUCCESS;
	}
	
	public void setSpreadRecordManager(ISpreadRecordMgr spreadRecordManager)
	{
		this.spreadRecordManager = spreadRecordManager;
	}

	public SpreadRecordDto getDto()
	{
		return dto;
	}

	public void setDto(SpreadRecordDto dto)
	{
		this.dto = dto;
	}

	

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public String getPic()
	{
		return pic;
	}

	public void setPic(String pic)
	{
		this.pic = pic;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getBegintime()
	{
		return begintime;
	}

	public void setBegintime(String begintime)
	{
		this.begintime = begintime;
	}

	public String getEndtime()
	{
		return endtime;
	}

	public void setEndtime(String endtime)
	{
		this.endtime = endtime;
	}

	public long getSpreadid()
	{
		return spreadid;
	}

	public void setSpreadid(long spreadid)
	{
		this.spreadid = spreadid;
	}

	public long getMid()
	{
		return mid;
	}

	public void setMid(long mid)
	{
		this.mid = mid;
	}

	public long getType()
	{
		return type;
	}

	public void setType(long type)
	{
		this.type = type;
	}
	
	
	
}
