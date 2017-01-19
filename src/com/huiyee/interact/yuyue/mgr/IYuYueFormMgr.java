package com.huiyee.interact.yuyue.mgr;

import com.huiyee.interact.yuyue.dto.IDto;


public interface IYuYueFormMgr
{
	public IDto findAptRecordByWxuid(long wxuid,long owner);
	
	public IDto findYuYueCatalog(long owner,String type);
	
	public IDto findYuYueServicerListBycatid(long catid,long owner,int pageId,int size);
	
	public IDto findYuYueServiceListBycatid(long catid,long owner,int pageId,int size);
	
	public IDto findYuYueCatalogById(long catid,long owner );
	
	public IDto findYuYueServicerById(long serid,long owner,long catid);
	
	public IDto findYuYueServiceById(long catid,long owner,long serviceid);
	
	public IDto findServicerListByService(long catid,long owner,long serviceid);
	
	public IDto findServiceListBySerid(long catid,long serid);
	
}
