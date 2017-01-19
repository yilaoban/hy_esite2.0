package com.huiyee.esite.mgr;

import java.util.Map;

import com.huiyee.esite.model.PageCache;

public interface IPageCacheManager {
	public int saveMap(long pageId, Map<String, Object> map);
	
	public Map<String, Object> getMap(long pageid);
	
	public int savePagesMap( Map<Long,Map<String, Object>> m);
	
	public int savePageHtml(long pageid,String sTotalString);
	
	public PageCache findPageCacheByPageid(long pageid);
}
