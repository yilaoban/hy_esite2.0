package com.huiyee.weixin.mgr;

import java.util.List;

import com.huiyee.weixin.model.WxApp;

public interface IWxAppMgr {

	public List<WxApp> getWxapps();

	public WxApp getWxapp(String appid);
	
	public WxApp getWxappById(long id);
}
