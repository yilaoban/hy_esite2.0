package com.huiyee.weixin.mgr.impl;

import com.huiyee.weixin.dao.IWxMpDao;
import com.huiyee.weixin.mgr.IWxMpMgr;
import com.huiyee.weixin.model.WxMp;

public class WxMpMgrImpl implements IWxMpMgr {

	private IWxMpDao wxMpDao;

	public void setWxMpDao(IWxMpDao wxMpDao) {
		this.wxMpDao = wxMpDao;
	}

	@Override
	public long saveWxMp(String appid) {
		return wxMpDao.saveWxMp(appid);
	}

	@Override
	public int saveWxMp(WxMp mp) {
		return wxMpDao.saveWxMp(mp);
	}

}
