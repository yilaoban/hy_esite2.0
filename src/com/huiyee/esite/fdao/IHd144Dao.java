package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.esite.model.RenQiRecord;
import com.huiyee.interact.renqi.model.RenQi;

public interface IHd144Dao 
{
	public long saveFeatureInteract(final long pageid);
	
	public int findRqidByFid(long fid);
	
	public List<RenQi> findRenQiByOwner(long ownerid);
	
	public int updateFeatureIneractRq(long rqid,long fid);
	
	public RenQi findRenQiByFId(long fid);
	
	public List<RenQiRecord> findRenQiRecord(long rqid,long duid,int type,int size);
	
	public int findIsSendByVisit(long rqid,long fuid);
	
	public int findIsJoinByVisit(long rqid,long fuid,long duid);
	
	public int findTotalByFuid(long rqid,long fuid);
}
