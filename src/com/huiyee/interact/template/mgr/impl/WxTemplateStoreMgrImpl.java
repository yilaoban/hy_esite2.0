package com.huiyee.interact.template.mgr.impl;

import java.util.List;

import com.huiyee.interact.template.dao.IWxTemplateStoreDao;
import com.huiyee.interact.template.mgr.IWxTemplateStoreMgr;
import com.huiyee.interact.template.model.WxTemplateStore;

public class WxTemplateStoreMgrImpl implements IWxTemplateStoreMgr {

	private IWxTemplateStoreDao wxTemplateStoreDao;

	public void setWxTemplateStoreDao(IWxTemplateStoreDao wxTemplateStoreDao) {
		this.wxTemplateStoreDao = wxTemplateStoreDao;
	}

	@Override
	public int getStoreCount() {
		return wxTemplateStoreDao.getStoreCount();
	}

	@Override
	public List<WxTemplateStore> getStoreList(int start, int rows) {
		return wxTemplateStoreDao.getStoreList(start, rows);
	}

	@Override
	public WxTemplateStore getStore(long id) {
		return wxTemplateStoreDao.getStore(id);
	}

}
