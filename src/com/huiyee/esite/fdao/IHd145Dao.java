package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.esite.model.CsFuidDraw;
import com.huiyee.interact.cs.model.Cs;

public interface IHd145Dao {
	
	public long saveFeatureInteract(final long pageid);

	public int findCsidByFid(long fid);
	
	public List<Cs> findCsByOwner(long ownerid);
	
	public int updateFeatureIneractCs(long csid,long fid);
	
	public Cs findCsByFid(long fid);
	
	public CsFuidDraw findCsFuidDrawByid(long id);
}
