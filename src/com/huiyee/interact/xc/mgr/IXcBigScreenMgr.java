package com.huiyee.interact.xc.mgr;

import com.huiyee.interact.xc.dto.IDto;

public interface IXcBigScreenMgr
{
	public IDto findXcBigScreenList(long xcid);
	
	public IDto findPageList(long siteid);
	
	public IDto findSiteList(long ownerid);
	
	public long saveXcBigScreen(long xcid,String name,long pageid,String imgurl);
	
	public int delXcBigScreen(long id);
	
	public int updateImgurlByDpmid(long id,String imgurl);
	
}
