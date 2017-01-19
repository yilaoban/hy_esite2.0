package com.huiyee.interact.setting.dao;

import com.huiyee.interact.setting.model.UWay;


public interface IHyUserWayDao
{

	public UWay findWay(long chyuid);

	public void addway(long chyuid,long owner);
	
	public void updateway(long id,int rmb,int jf,long shyuid);
	
	public UWay findWayById(long id,long endtime,int status);
	
	public UWay findUWayById(long id);
}
