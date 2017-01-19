package com.huiyee.interact.ad.mgr;

import com.huiyee.interact.ad.dto.IDto;
import com.huiyee.interact.ad.model.Adgg;


public interface IAdGGMgr
{
	public IDto findAdGGListByOwner(long owner, int pageId);
	
	public int saveGG(Adgg adgg);
	
	public Adgg findadGGById(long ggid);
	
	public int updateGG(Adgg adgg);
	
	public int delGGById(long ggid,long owner);
}
