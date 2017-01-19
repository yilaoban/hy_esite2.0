package com.huiyee.esite.dto.showdto;

import java.io.Serializable;

import com.huiyee.esite.dto.IDto;
import com.huiyee.interact.research.model.ResearchModel;

public class Show124Dto implements IDto, Serializable{

	private static final long serialVersionUID = 3753168535074282209L;
	
	private long fid;
	private ResearchModel researchModel;
	
	public long getFid()
	{
		return fid;
	}
	public void setFid(long fid)
	{
		this.fid = fid;
	}
	public ResearchModel getResearchModel()
	{
		return researchModel;
	}
	public void setResearchModel(ResearchModel researchModel)
	{
		this.researchModel = researchModel;
	}
	
	
	
}
