package com.huiyee.interact.servicerpj.mgr;

import com.huiyee.interact.servicerpj.model.ServicerPjPage;

public interface IServicerPjPageMgr {

	public ServicerPjPage findServicerPjPage(long owner, int type);

}
