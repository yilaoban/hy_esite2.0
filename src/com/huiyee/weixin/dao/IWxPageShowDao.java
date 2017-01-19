package com.huiyee.weixin.dao;

import java.util.List;

import com.huiyee.esite.model.Page;
import com.huiyee.weixin.model.WxPageShow;

public interface IWxPageShowDao {
	
	public WxPageShow getWxPageShow(long pageid);
	
	public WxPageShow getWxPageShowBySite(long pageid);

	public WxPageShow getWxPageShowByPP(long pageid);
	
	public WxPageShow getWxPageShowByPS(long pageid);

	public WxPageShow saveWxPageShow(WxPageShow wps);

	public List<WxPageShow> findWxPageListByGroupid(long sitegroupid);
	
	public List<WxPageShow> findWxPageListBySiteid(long siteid);
	
	public int saveWxpp(long wxpid,long pageid);
	
	public WxPageShow findWxPageShowById(long id);
	
	public List<Long> findWxPPById(long id);
	
	public int updateWxPageShow(WxPageShow wps);
	
	public int deleteWxppByWxpid(long wxpid);
	
	public List<Page> findPublishPageBySiteid(long siteid);
	
	public int saveWxPs(long wxpid,long siteid);
	
	public int deleteWxpp(long wxpid);
	
	public int updateSitegroupCMP(long sitegroupid);

	public int deleteWxps(long shareid);

	public int deleteWps(long shareid);
	
}
