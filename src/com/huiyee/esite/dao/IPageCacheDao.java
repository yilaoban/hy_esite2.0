package com.huiyee.esite.dao;

import java.util.Map;

import com.huiyee.esite.model.PageCache;

public interface IPageCacheDao {

	public int saveMap(long pageId, byte[] bs);

	public Map<String, Object> getMap(long pageid);

	public int savePageHtml(long pageid,String sTotalString);
	
	public PageCache findPageCacheByPageid(long pageid);
	
}
