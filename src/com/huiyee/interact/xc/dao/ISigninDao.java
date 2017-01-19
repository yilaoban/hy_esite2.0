package com.huiyee.interact.xc.dao;

import java.util.List;

import com.huiyee.esite.model.SinaUser;
import com.huiyee.esite.model.WxUser;
import com.huiyee.interact.xc.model.XcCheckinRecord;
import com.huiyee.interact.xc.model.XcSendRecord;



public interface ISigninDao 
{
	public int findUser(long xcid,long uid,int utype);
	
	public int saveUser(long uid,int type,long pageid,String source,long xcid,String ip,String terminal, int status);
	
	public int updateInvite(long uid,int type,long xcid);
	
	public int updateSd(long uid,int type,long xcid);
	
	public int findisInvite(long xcid,long uid,int type);
	
	public List<XcCheckinRecord> findXcCheckinRecord(long xcid,long start,int size);
	
	public WxUser findWxUser(long wxuid);
	
	public SinaUser findSinaUser(long wbuid);
	
	public int findSignerTotal(long xcid);
	
	public XcSendRecord findsdRecord(long uid,int utype,long xcid);

	public long findCheckinRecordId(long xcid, long uid, int type);
}
