
package com.huiyee.interact.cb.dto;

import java.util.List;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.model.CbActivity;
import com.huiyee.interact.cb.model.CbActivityMatter;
import com.huiyee.interact.cb.model.CbHbRecord;
import com.huiyee.interact.cb.model.CbImpel;
import com.huiyee.interact.cb.model.CbSender;
import com.huiyee.interact.cb.model.InteractCb;
import com.huiyee.interact.cb.model.SenderImpel;
import com.huiyee.interact.cb.model.SenderShare;

public class CbDataDto implements IDto
{

	private List<CbSender> list;
	private List<CbActivity> activity;
	private CbActivity current;
	private Pager pager;
	private CbSender sender;
	private CbImpel impel;// ¼¤Àø
	private List<CbImpel> implList;
	private List<SenderImpel> senderImpel;
	private List<SenderShare> shareList;
	private List<CbActivityMatter> matters;
	private List<CbHbRecord> hbRecord;
	private int hbtotal;
	private int hbused;
	private InteractCb cb;

	public List<CbHbRecord> getHbRecord()
	{
		return hbRecord;
	}

	public void setHbRecord(List<CbHbRecord> hbRecord)
	{
		this.hbRecord = hbRecord;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	public List<CbSender> getList()
	{
		return list;
	}

	public void setList(List<CbSender> list)
	{
		this.list = list;
	}

	public CbSender getSender()
	{
		return sender;
	}

	public void setSender(CbSender sender)
	{
		this.sender = sender;
	}

	public CbImpel getImpel()
	{
		return impel;
	}

	public void setImpel(CbImpel impel)
	{
		this.impel = impel;
	}

	public List<CbImpel> getImplList()
	{
		return implList;
	}

	public void setImplList(List<CbImpel> implList)
	{
		this.implList = implList;
	}

	public List<SenderImpel> getSenderImpel()
	{
		return senderImpel;
	}

	public void setSenderImpel(List<SenderImpel> senderImpel)
	{
		this.senderImpel = senderImpel;
	}

	public List<SenderShare> getShareList()
	{
		return shareList;
	}

	public void setShareList(List<SenderShare> shareList)
	{
		this.shareList = shareList;
	}

	public List<CbActivity> getActivity()
	{
		return activity;
	}

	public void setActivity(List<CbActivity> activity)
	{
		this.activity = activity;
	}

	public CbActivity getCurrent()
	{
		return current;
	}

	public void setCurrent(CbActivity current)
	{
		this.current = current;
	}

	public int getHbtotal()
	{
		return hbtotal;
	}

	public void setHbtotal(int hbtotal)
	{
		this.hbtotal = hbtotal;
	}

	public int getHbused()
	{
		return hbused;
	}

	public void setHbused(int hbused)
	{
		this.hbused = hbused;
	}

	public List<CbActivityMatter> getMatters()
	{
		return matters;
	}

	public void setMatters(List<CbActivityMatter> matters)
	{
		this.matters = matters;
	}

	public InteractCb getCb()
	{
		return cb;
	}

	public void setCb(InteractCb cb)
	{
		this.cb = cb;
	}

}
