package com.huiyee.esite.mgr.imp;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.huiyee.esite.dao.IPageCacheDao;
import com.huiyee.esite.dao.IPageDao;
import com.huiyee.esite.mgr.IPageCacheManager;
import com.huiyee.esite.model.PageCache;

public class PageCacheManagerImpl implements IPageCacheManager {
	private IPageDao pageDao;
	private IPageCacheDao pageCacheDao;

	public void setPageCacheDao(IPageCacheDao pageCacheDao) {
		this.pageCacheDao = pageCacheDao;
	}

	public int saveMap(long pageId, Map<String, Object> map) {
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(map);
			pageDao.updateOnline(pageId);
			return pageCacheDao.saveMap(pageId, byteArrayOutputStream.toByteArray());
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return 0;
	}
	public int savePagesMap( Map<Long,Map<String, Object>> m) {
	    int res=0;
	    Iterator<Entry<Long, Map<String, Object>>> iter = m.entrySet().iterator();
	    while (iter.hasNext()) {
	        Map.Entry<Long, Map<String, Object>> entry = iter.next();
	        long pageId=entry.getKey();
	        Map<String, Object> map=entry.getValue();
	        res=saveMap(pageId, map);
        }
	    return res;
	}
	public Map<String, Object> getMap(long pageid) {
		return pageCacheDao.getMap(pageid);
	}

	@Override
	public int savePageHtml(long pageid,String sTotalString) {
		return pageCacheDao.savePageHtml(pageid,sTotalString);
	}

	@Override
	public PageCache findPageCacheByPageid(long pageid) {
		return pageCacheDao.findPageCacheByPageid(pageid);
	}

	public void setPageDao(IPageDao pageDao) {
		this.pageDao = pageDao;
	}
	
	
}
