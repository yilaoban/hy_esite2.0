package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.esite.model.InteractSpread;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.esite.model.SpreadOption;
import com.huiyee.interact.spread.model.SpreadModel;

public interface IInteractSpreadDao
{

	public long savePageId(long pageid);

	public InteractSpread findInteractSpread(long fid);

	public List<InteractSpread> findInteractSpreadList(long id);

	public int updateSpreadid(long spreadid, long fid);
	
	public SpreadModel findSpreadModelByFId(long fid);
	
	public List<SpreadOption> findOptionsBySpreadid(long spreadid);
	
	public PageBlockRelation findPageBlockRelationByRelationid(long relationid);
	
	public int updatePageBlockRelationByRelationid(long relationid,String json);
	
}
