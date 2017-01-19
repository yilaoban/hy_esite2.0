package com.huiyee.interact.xc.mgr;

import java.util.List;

import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.xc.model.XcCheckinRecord;

public interface ISigninMgr
{
	public int findUser(long xcid,long uid,int type);
	
	public int saveUser(long uid,int type,long pageid,String source,long xcid,String ip,String terminal);
	
	public int updateInvite(long uid,int type,long xcid);
	
	public int updateSd(long uid,int type,long xcid);
	
	public int findisInvite(long xcid,long uid,int type);
	
	public List<XcCheckinRecord> findXcCheckinRecord(long xcid,long start,int size);
	
	public int findSignerTotal(long xcid);

	public int saveUserFail(long uid, int type, long pageid, VisitUser vu, long xcid, String ip, String terminal, int status,long msgid);
	
}