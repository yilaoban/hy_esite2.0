package com.huiyee.weixin.mgr.impl;

import java.util.List;

import com.huiyee.weixin.dao.IWxAppDao;
import com.huiyee.weixin.mgr.IWxAppMgr;
import com.huiyee.weixin.model.WxApp;

public class WxAppMgrImpl implements IWxAppMgr {

	private IWxAppDao wxAppDao;

	public void setWxAppDao(IWxAppDao wxAppDao) {
		this.wxAppDao = wxAppDao;
	}

	@Override
	public WxApp getWxapp(String appid) {
		return wxAppDao.getWxapp(appid);
	}

	@Override
	public List<WxApp> getWxapps() {
		return wxAppDao.getWxapps();
	}

	@Override
	public WxApp getWxappById(long id)
	{
		return wxAppDao.getWxappById(id);
	}

}
