package com.huiyee.esite.mgr;

import java.util.List;

import com.huiyee.esite.model.WeiXinPage;
import com.huiyee.interact.cb.model.CbActivityMatter;
import com.huiyee.weixin.model.WxPageShow;


public interface IPageShowMaterialManager
{
	public int findTotalWxPageShowByAid(long cbid,long aid);
	
	public List<CbActivityMatter> findWxPageShowListByAid(long cbid,long aid,int start,int size);
	
	
	
	public int findTotalWxPageShowByOwnerid(long ownerid,String title);
	
	public List<WxPageShow> findPageShowMaterialByOwnerid(long ownerid,String title,int start,int size);
	
	public int updatePageShowActioned(long id, String actioned);
	
	public int saveCbActivityMatter(long cbid,long aid,long pageid,long id,long ownerid,String stype);
	
	public int updateInteractCbActivityMatterById(long amid);

	public int saveNewsMatter(long cbid, long aid, long newsid, long ownerid);
}
