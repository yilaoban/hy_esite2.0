package com.huiyee.weixin.mgr;

import com.huiyee.weixin.model.WxMp;

public interface IWxMpMgr {

	public long saveWxMp(String appid);

	public int saveWxMp(WxMp mp);

}