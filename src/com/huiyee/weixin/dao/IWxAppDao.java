package com.huiyee.weixin.dao;

import java.util.List;

import com.huiyee.weixin.model.WxApp;

public interface IWxAppDao {

	public WxApp getWxapp(String appid);
	
	public WxApp getWxappById(long id);

	public List<WxApp> getWxapps();

}
