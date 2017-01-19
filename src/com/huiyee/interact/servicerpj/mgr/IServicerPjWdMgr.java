package com.huiyee.interact.servicerpj.mgr;

import com.huiyee.esite.dto.IDto;
import com.huiyee.interact.servicerpj.model.ServicerPjWd;

public interface IServicerPjWdMgr {
	
	public IDto findServicerPjwdList(long owner,int pageId);
	
	public int savepjwd(long owner,String name);
	
	public ServicerPjWd findPjWdById(long id);
	
	public int updatePjWdById(String name,long id);
	
	public int delPjWdById(long id);
}
