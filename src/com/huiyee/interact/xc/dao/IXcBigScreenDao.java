package com.huiyee.interact.xc.dao;

import java.util.List;

import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.Site;
import com.huiyee.interact.xc.model.XcBigScreen;

public interface IXcBigScreenDao
{
	public List<XcBigScreen> findXcBigScreenList(long xcid);
	
	public List<Page> findPageList(long siteid);
	
	public long saveXcBigScreen(long xcid, String name, long pageid,String imgurl);
	
	public List<Site> findSiteList(long ownerid);
	
	public int delXcBigScreen(long id);
	
	public int updateImgurlByDpmid(long id, String imgurl);

	public long findSiteIdByXc(long xcid);
}
