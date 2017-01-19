package com.huiyee.esite.mgr;

import java.util.List;

import com.huiyee.esite.model.FansAnalyse;
import com.huiyee.esite.model.FansAnalyseRecord;
import com.huiyee.esite.model.FansLevelAnalyse;

public interface IFansAnalyseManager {
	
	public int findVisitFansTotal(long sitegroupid,String type);
	
	public int findHdFansTotal(long sitegroupid,String type);
	
	public int findVisitTotal(long sitegroupid);
	
	public List<FansAnalyseRecord> findFansAnalyse(long sitegroupid,String type,int start,int size);
	
	public int saveHdFansAnalyse(long rid,long sitegroupid,long wbuid,String nickname,String type,String followed,int level);
	
	public int saveVisitFansAnalyse(long rid,long sitegroupid,long wbuid,String nickname,String type,String followed,int level);
	
	public int updateHdRecordError(long rid);
	
	public int updateVisitReocordError(long rid);
	
	public FansAnalyse findFansAnalyse(long sitegroupid);
	
    public List<FansLevelAnalyse> findFansLevelAnalyseList(long sitegroupid);
    
    public int findLevelVnumTotal(long sitegroupid);
    
    public int findLevelDnumTotal(long sitegroupid);
    
    public List<FansLevelAnalyse> findFansLevelAnalyseListByOwner(long ownerid);
    
    public int updateUserInfoStatus(long wbuid);;

}
