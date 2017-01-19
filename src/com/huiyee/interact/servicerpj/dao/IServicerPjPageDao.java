package com.huiyee.interact.servicerpj.dao;

import com.huiyee.interact.servicerpj.model.ServicerPjPage;

public interface IServicerPjPageDao {

	public ServicerPjPage findServicerPjPage(long owner, int type);

}
