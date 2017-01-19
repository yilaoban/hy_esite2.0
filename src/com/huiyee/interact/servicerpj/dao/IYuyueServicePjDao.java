package com.huiyee.interact.servicerpj.dao;

import java.util.List;

import com.huiyee.interact.servicerpj.model.ServicerPj;
import com.huiyee.interact.servicerpj.model.ServicerPjWd;

public interface IYuyueServicePjDao {

	public int findServicerPjTotal(long owner, long srid);

	public List<ServicerPj> findServicerPjList(long owner, long srid, int start, int rows);

	public List<ServicerPjWd> findServicerPjWdList(long owner);

	public List<ServicerPjWd> findServicerPjWdListByPjid(long pjid);

	public ServicerPj findServicerPj(int type, long enid);

	public long addServicerPj(ServicerPj pj);

	public int[] addServicerPjWdl(long pjid, List<ServicerPjWd> wdList);

	public int[] updateServicerPt(long serid, List<ServicerPjWd> wdList);

	public int updateDzcontent(long id, String dzcontent);

}
