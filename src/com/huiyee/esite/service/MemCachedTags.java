package com.huiyee.esite.service;

import java.util.HashMap;
import java.util.Map;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.util.ToolUtils;
public class MemCachedTags {
    /**
     * 获取缓存随机数
     * @param session
     * @param key
     * @return
     */
	public static Integer getCacheTag(Map<String, Object> session, String key) {
		Map<String, Integer> cacheTags = (Map<String, Integer>) session
				.get(IPageConstants.CACHE_TAGS);
		if (cacheTags == null) {
			cacheTags = new HashMap<String, Integer>();
		}
		if (cacheTags.get(key) == null) {
			cacheTags.put(key, ToolUtils.createRadomNum());
			session.put(IPageConstants.CACHE_TAGS, cacheTags);
		}
		return cacheTags.get(key);
	}
	/**
	 * 更新缓存随机数
	 * @param session
	 * @param key
	 */
	public static void updateCacheTag(Map<String, Object> session, String key) {
		Map<String, Integer> cacheTags = (Map<String, Integer>) session
				.get(IPageConstants.CACHE_TAGS);
		if (cacheTags == null) {
			cacheTags = new HashMap<String, Integer>();
			cacheTags.put(key, ToolUtils.createRadomNum());
			session.put(IPageConstants.CACHE_TAGS, cacheTags);
		} else {
			Integer tag = cacheTags.get(key);
			Integer newkey = ToolUtils.createRadomNum();
			while (tag == newkey) {
				newkey = ToolUtils.createRadomNum();
			}
			cacheTags.put(key, newkey);
		}

	}
}
