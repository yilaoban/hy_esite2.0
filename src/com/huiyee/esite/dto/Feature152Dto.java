
package com.huiyee.esite.dto;

import java.io.Serializable;
import java.util.List;

import com.huiyee.esite.model.BBSForum;
import com.huiyee.interact.cb.model.InteractCb;

public class Feature152Dto implements IDto, Serializable
{

	private static final long serialVersionUID = 3776477610824949130L;

	private long fid;
	private InteractCb cb;
	private List<InteractCb> list;
	private long cbid;

	public long getFid()
	{
		return fid;
	}

	public void setFid(long fid)
	{
		this.fid = fid;
	}

	public InteractCb getCb()
	{
		return cb;
	}

	public void setCb(InteractCb cb)
	{
		this.cb = cb;
	}

	public List<InteractCb> getList()
	{
		return list;
	}

	public void setList(List<InteractCb> list)
	{
		this.list = list;
	}

	public long getCbid()
	{
		return cbid;
	}

	public void setCbid(long cbid)
	{
		this.cbid = cbid;
	}

}
