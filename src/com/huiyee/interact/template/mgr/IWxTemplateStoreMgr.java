package com.huiyee.interact.template.mgr;

import java.util.List;

import com.huiyee.interact.template.model.WxTemplateStore;

public interface IWxTemplateStoreMgr {

	public int getStoreCount();

	public List<WxTemplateStore> getStoreList(int start, int rows);

	public WxTemplateStore getStore(long id);

}
