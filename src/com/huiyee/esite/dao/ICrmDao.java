package com.huiyee.esite.dao;

import com.huiyee.weixin.model.WxMediaNews;

public interface ICrmDao {

	public String findCrmKey(long id);

	public long findMpidByAccout(String accout);

	public WxMediaNews findNews(long id);

}
