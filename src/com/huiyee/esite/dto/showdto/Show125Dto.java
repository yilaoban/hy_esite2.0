package com.huiyee.esite.dto.showdto;

import java.io.Serializable;

import com.huiyee.esite.dto.IDto;
import com.huiyee.interact.lottery.model.Lottery;

public class Show125Dto implements IDto, Serializable{

	private static final long serialVersionUID = 1596996323085473763L;
	
	private long fid;
	private Lottery lottery;
	public long getFid()
	{
		return fid;
	}
	public void setFid(long fid)
	{
		this.fid = fid;
	}
	public Lottery getLottery()
	{
		return lottery;
	}
	public void setLottery(Lottery lottery)
	{
		this.lottery = lottery;
	}
	
	
}
