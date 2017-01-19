package com.huiyee.esite.dao;

import java.util.List;

import com.huiyee.esite.model.FansAnalyse;
import com.huiyee.esite.model.FansAnalyseRecord;
import com.huiyee.esite.model.FansLevelAnalyse;

public interface IFansAnalyseDao {

	public int findVisitFansTotal(long sitegroupid,String type);
	
	public int findHdFansTotal(long sitegroupid,String type);
	
	public int findVisitTotal(long sitegroupid);
	
	public List<FansAnalyseRecord> findFansAnalyselist(long sitegroupid,String type, int start,int size);
	
	public int saveFansAnalyse(long sitegroupid, long wbuid, String nickname,
			String type, String followed,int level);
	
	public int saveDanymicLevelFansAnalyse(long sitegroupid, int level,int dnum);
	
	public int saveVistiLevelFansAnalyse(long sitegroupid, int level,int vnum);
	
	public int saveDanymicUnFansAnalyse(long sitegroupid, int dunfansnum);
	
	public int saveDanymicFansAnalyse(long sitegroupid, int dfansnum) ;
	
	public int saveVistiUnFansAnalyse(long sitegroupid, int vunfansnum);
	
	public FansAnalyse findFansAnalyse(long sitegroupid);
	
	public int saveVistiFansAnalyse(long sitegroupid, int vfansnum);
	
	public FansAnalyseRecord findFansAnalyseRecord(long sitegroupid,long wbuid);
	
	public List<FansLevelAnalyse> findFansLevelAnalyseList(long sitegroupid);
	
	public int findLevelVnumTotal(long sitegroupid);
	
	public int findLevelDnumTotal(long sitegroupid);
	
	public List<FansLevelAnalyse> findFansLevelAnalyseListByOwner(long ownerid);
	
	public int updateVisitFansAnalyse(long sitegroupid,String type) ;
	
	public int updateDanymicFansAnalyse(long sitegroupid,String type);
	
	public int updateDDanymicLevelAnalyse(long sitegroupid, int level);

	public int updateADanymicLevelAnalyse(long sitegroupid, int level);

	public int updateDVisitLevelAnalyse(long sitegroupid, int level);

	public int updateAVisitLevelAnalyse(long sitegroupid, int level);
	
}
