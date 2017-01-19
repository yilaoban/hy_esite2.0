package com.huiyee.esite.mgr;

import java.util.Map;

public interface IAppManager {
	
	public Object findAppObjectByAppid(long ownerid, long appid);

	public long findAppUsedPageid(long owner, int appid);

	public int updateUsedSiteGroup(long owner, int appid, Map<String, Long> sql);
}
