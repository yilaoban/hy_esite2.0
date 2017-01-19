package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.esite.model.Module;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.interact.research.model.ResearchModel;
import com.huiyee.interact.research.model.ResearchQuestionModel;
import com.huiyee.interact.research.model.ResearchQuestionOption;


public interface IHd124Dao{
	
	public long saveFeatureInteractResearch(final long pageid);
	
	public List<ResearchModel> findInteractResearchByOwner(long ownerid);
	
	public Module findResearchidByFid(long fid);

	public int updateFeatureIneractResearch(long researchid,long fid);
	
	public ResearchModel findFeatureInteractResearchById(long fid);
	
	public PageBlockRelation findPageBlockRelationByRelationid(long relationid);
	 
	 public int updatePageBlockRelationByRelationid(long relationid,String json);
	 
	 public List<ResearchQuestionModel> findQuestionsByResearchid(long researchid);
	 
	 public List<ResearchQuestionOption> findOptionsByQuestionid(long questionid);
}
