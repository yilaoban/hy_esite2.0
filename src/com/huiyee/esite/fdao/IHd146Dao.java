package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.esite.model.CsFuidDraw;
import com.huiyee.interact.xc.model.Xc;

public interface IHd146Dao {
	
	public long saveXc(long pageid);
	
	public long findXcidByFid(long fid);
	
	public List<Xc> findXcList(long owner);
	
	public int updateXcid(long xcid,long fid);
	
	public Xc findXcById(long xcid);
}
