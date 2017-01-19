package com.huiyee.interact.xc.mgr;

import com.huiyee.interact.xc.dao.IZhuanpanDao;


public class ZhuanpanMgr implements IZhuanpanMgr
{
	private IZhuanpanDao zhuanpanDao;

	public void setZhuanpanDao(IZhuanpanDao zhuanpanDao)
	{
		this.zhuanpanDao = zhuanpanDao;
	}

}
