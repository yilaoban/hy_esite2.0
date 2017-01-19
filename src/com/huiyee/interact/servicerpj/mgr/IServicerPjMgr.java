
package com.huiyee.interact.servicerpj.mgr;

import java.util.List;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.servicerpj.model.ServicerPj;
import com.huiyee.interact.servicerpj.model.ServicerPjWd;
import com.huiyee.interact.yuyue.model.YuYueServicer;

public interface IServicerPjMgr {

	public IDto findServicerList(Account account, int pageId);

	public long saveYuYueServicer(YuYueServicer yuYueServicer, long owner);

	public int updateServicerByCaid(Account account, long caid);

	public int delServicer(Account account, long serid);

	public int updateServicerOidx(Account account, long serid, int moveUp);

	public int updateServicerTop(Account account, long serid, int top);

	public YuYueServicer findServicerById(Account account, long serid);

	public int updateServicer(YuYueServicer yuYueServicer, long id);

	public IDto findServicerPjList(Account account, long serid, int pageId);

	////////////////////////////////////////////////////////

	public int findServicerPjTotal(long owner, long srid);

	public List<ServicerPj> findServicerPjList(long owner, long srid, int start, int rows);

	public int addServicerPjInvite(ServicerPj pj);

	public ServicerPj findServicerPj(int type, long enid);

	public int addServicerPj(VisitUser vu, ServicerPj pj);

	public int updateDzcontent(VisitUser vu, ServicerPj pj);

	public int updateServicerPage(Account account, long sgid, int source);
	
	public List<ServicerPjWd> findServicerPjWdList(long owner);

}
