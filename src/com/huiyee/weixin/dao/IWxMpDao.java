package com.huiyee.weixin.dao;

import com.huiyee.weixin.model.WxMp;

public interface IWxMpDao {

	public long saveWxMp(String appid);

	public int saveWxMp(WxMp mp);

}
