package com.huiyee.weixin.mgr;

import java.util.List;

import com.huiyee.esite.model.Page;
import com.huiyee.weixin.model.WxPageShow;

public interface IWxPageShowMgr {

	public WxPageShow getWxPageShow(long pageid);

	public WxPageShow saveWxPageShow(WxPageShow wps,List<Long> pids);

	public List<WxPageShow> findWxPageListByGroupid(long sitegroupid);
	
	public List<WxPageShow> findWxPageListBySiteid(long siteid);
	
	public WxPageShow findWxPageShowById(long id);
	
	public List<Long> findWxPPById(long id);
	
	public int updateWxPageShow(WxPageShow wps,List<Long> pids,long siteid);
	
	public List<Page> findPublishPageBySiteid(long siteid);

	public int deleteWxPageShow(long shareid, String stype, long owner);

}
