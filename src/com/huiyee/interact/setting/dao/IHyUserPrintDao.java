package com.huiyee.interact.setting.dao;

import com.huiyee.interact.setting.model.HyUserPrint;

public interface IHyUserPrintDao {

	public HyUserPrint findPrint(long owner);

	public int addPrint(HyUserPrint print);

	public int updatePrint(HyUserPrint print);

}
