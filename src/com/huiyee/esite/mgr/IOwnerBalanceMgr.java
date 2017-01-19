package com.huiyee.esite.mgr;

import com.huiyee.esite.dto.IDto;

public interface IOwnerBalanceMgr {
	public IDto findPayTypeInfo(long hyuid,long id,String type,long owner);
	
	public int updateBanlance(long hyuid,long owner,long id,String ip,String type,String url);
}
