package com.huiyee.interact.setting.mgr;

import com.huiyee.interact.setting.model.HyUserPrint;

public interface IHyUserPrintMgr {

	public HyUserPrint findPrint(long owner);

	public int addPrint(HyUserPrint print);

	public int updatePrint(HyUserPrint print);

}
