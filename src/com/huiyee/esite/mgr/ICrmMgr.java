package com.huiyee.esite.mgr;

import com.huiyee.weixin.model.WxMediaNews;

public interface ICrmMgr {

	public String findCrmKey(long id);

	public long findMpidByAccout(String accout);

	public WxMediaNews findNews(long id);

}
