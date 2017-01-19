package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.esite.model.Module;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.interact.exam.model.ExamModel;
import com.huiyee.interact.exam.model.ExamQuestionModel;
import com.huiyee.interact.exam.model.ExamQuestionOption;


public interface IHd153Dao{
	
	public long saveFeatureInteractExam(final long pageid);
	
	public List<ExamModel> findInteractExamByOwner(long ownerid);
	
	public Module findExamidByFid(long fid);

	public int updateFeatureIneractExam(long examid,long fid);
	
	public ExamModel findFeatureInteractExamById(long fid);
	
	public PageBlockRelation findPageBlockRelationByRelationid(long relationid);
	 
	 public int updatePageBlockRelationByRelationid(long relationid,String json);
	 
	 public List<ExamQuestionModel> findQuestionsByExamid(long examid);
	 
	 public List<ExamQuestionOption> findOptionsByQuestionid(long questionid);
}
