package com.huiyee.interact.journal.action;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.interact.journal.dto.PeriodicalRecordDto;
import com.huiyee.interact.journal.mgr.IPeriodicalRecordMgr;

public class PeriodicalRecordAction extends InteractModelAction{
	
	private IPeriodicalRecordMgr periodicalRecordMgr;
	private int pageid=1;
	private long jid;
	private String title;
	private int count;
	private String lastsharetime;
	private PeriodicalRecordDto dto;
	private long wbuid;
	private long contentid;
	private String nickname;
	private String createtime;
	private String ip;
	private String terminal;  //жу╤к
	private String source;
	private String begintime;
	private String endtime;
	private long mid = 10001;
	
	public String execute() throws Exception
	{
		dto=(PeriodicalRecordDto) periodicalRecordMgr.findJournalContentByJ(jid, pageid);
		return SUCCESS;
	}
	
	public String findJournalSR() throws Exception
	{
		dto=(PeriodicalRecordDto) periodicalRecordMgr.findJournalSR(contentid, pageid);
		return SUCCESS;
	}
	
	public String searchJnickname() throws Exception
	{
		dto=(PeriodicalRecordDto) periodicalRecordMgr.searchnickname(nickname,contentid, pageid);
		return SUCCESS;
	}
	
	public String searchtimeorts() throws Exception
	{
		dto=(PeriodicalRecordDto) periodicalRecordMgr.searchtimeorts(begintime,endtime,terminal,source,contentid, pageid);
		return SUCCESS;
	}
	

	public void setPeriodicalRecordMgr(IPeriodicalRecordMgr periodicalRecordMgr)
	{
		this.periodicalRecordMgr = periodicalRecordMgr;
	}

	public int getPageid()
	{
		return pageid;
	}

	public void setPageid(int pageid)
	{
		this.pageid = pageid;
	}

	public long getJid()
	{
		return jid;
	}

	public void setJid(long jid)
	{
		this.jid = jid;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public String getLastsharetime()
	{
		return lastsharetime;
	}

	public void setLastsharetime(String lastsharetime)
	{
		this.lastsharetime = lastsharetime;
	}

	public PeriodicalRecordDto getDto()
	{
		return dto;
	}

	public void setDto(PeriodicalRecordDto dto)
	{
		this.dto = dto;
	}

	public long getWbuid()
	{
		return wbuid;
	}

	public void setWbuid(long wbuid)
	{
		this.wbuid = wbuid;
	}

	public long getContentid()
	{
		return contentid;
	}

	public void setContentid(long contentid)
	{
		this.contentid = contentid;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public String getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(String createtime)
	{
		this.createtime = createtime;
	}

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public String getTerminal()
	{
		return terminal;
	}

	public void setTerminal(String terminal)
	{
		this.terminal = terminal;
	}

	public String getSource()
	{
		return source;
	}

	public void setSource(String source)
	{
		this.source = source;
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

	public long getMid()
	{
		return mid;
	}

	public void setMid(long mid)
	{
		this.mid = mid;
	}

	
	
	
}
