package com.huiyee.interact.servicerpj.dao;

import java.util.List;

import com.huiyee.interact.servicerpj.model.ServicerPjWd;

public interface IServicerPjWdDao {
	
	public int findTotalServicerPjwd(long owner);
	
	public List<ServicerPjWd> findServicerPjwdList(long owner,int start,int size);
	
	public int savepjwd(long owner, String name);
	
	public ServicerPjWd findPjWdById(long id);
	
	public int updatePjWdById(String name, long id);
	
	public int delPjWdById(long id);
}
