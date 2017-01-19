package com.huiyee.esite.mgr;

import java.util.List;

import com.huiyee.esite.model.WxGroup;


public interface IMoveToGroupMgr
{
	public int saveWxGroupJob(long mpid,String name,String jobrule,String type);
	
	public List<WxGroup> findWxGroupList(long mpid);
	
	public long saveCrmWxGroup(long mpid, String name);
}
