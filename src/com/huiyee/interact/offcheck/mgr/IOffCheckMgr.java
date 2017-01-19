
package com.huiyee.interact.offcheck.mgr;

import net.sf.json.JSONObject;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.offcheck.dto.CheckRs;
import com.huiyee.interact.offcheck.dto.OffCheckDto;
import com.huiyee.interact.offcheck.model.OffCheck;
import com.huiyee.interact.offcheck.model.OffCheckSource;

public interface IOffCheckMgr
{

	public OffCheck findStoreCrmByOwner(long id);

	public long saveStoreCrm(Account account);

	public IDto findOffCheckSource(Account account, int pageId);

	public long saveOffCheckSource(Account account, OffCheckSource ofcSource);

	public OffCheckSource findOffCheckSourceById(long sourceid, long id);

	public long updateOffCheckSource(Account account, OffCheckSource ofcSource);

	public long delSource(long sourceid, long id);

	public IDto findRecordList(Account account, int pageId, JSONObject sift);

	public IDto findRecordList(Account account, long sourceid, long wxuid, int pageId);
	
	public CheckRs updateChecking(long sourceid,VisitUser user,String ip,long owner);
	
	public CheckRs updateDtChecking(long id,VisitUser user,String ip,long owner);

	public IDto findOffCheckLogs(Account account, int pageId, JSONObject sift);

}
