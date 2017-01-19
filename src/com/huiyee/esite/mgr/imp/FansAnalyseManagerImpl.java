package com.huiyee.esite.mgr.imp;

import java.util.List;

import com.huiyee.esite.dao.IFansAnalyseDao;
import com.huiyee.esite.dao.IHdRecordDao;
import com.huiyee.esite.dao.IVisitDao;
import com.huiyee.esite.mgr.IFansAnalyseManager;
import com.huiyee.esite.model.FansAnalyse;
import com.huiyee.esite.model.FansAnalyseRecord;
import com.huiyee.esite.model.FansLevelAnalyse;

public class FansAnalyseManagerImpl implements IFansAnalyseManager{
	
	private IFansAnalyseDao fansAnalyseDao;
	private IHdRecordDao hdRecordDao;
	private IVisitDao visitDao;

	public void setFansAnalyseDao(IFansAnalyseDao fansAnalyseDao) {
		this.fansAnalyseDao = fansAnalyseDao;
	}

	@Override
	public int findHdFansTotal(long sitegroupid,String type) {
		return fansAnalyseDao.findHdFansTotal(sitegroupid,type);
	}

	@Override
	public int findVisitFansTotal(long sitegroupid,String type) {
		return fansAnalyseDao.findVisitFansTotal(sitegroupid,type);
	}

	@Override
	public int findVisitTotal(long sitegroupid) {
		return fansAnalyseDao.findVisitTotal(sitegroupid);
	}

	@Override
	public List<FansAnalyseRecord> findFansAnalyse(long sitegroupid,String type, int start,
			int size) {
		return fansAnalyseDao.findFansAnalyselist(sitegroupid, type, start, size);
	}

	@Override
	public int saveHdFansAnalyse(long rid,long sitegroupid, long wbuid, String nickname,
			String type, String followed,int level) {
	    FansAnalyseRecord analyseRecord=fansAnalyseDao.findFansAnalyseRecord(sitegroupid, wbuid);
	    fansAnalyseDao.saveFansAnalyse(sitegroupid, wbuid, nickname, type, followed,level);
		if(analyseRecord==null){
		    if("Y".equals(type)){
		        fansAnalyseDao.saveDanymicFansAnalyse(sitegroupid, 1);
		    }else{
		        fansAnalyseDao.saveDanymicUnFansAnalyse(sitegroupid, 1);
		    }
		    fansAnalyseDao.saveDanymicLevelFansAnalyse(sitegroupid, level, 1);
		}
		else
		{
			if (!type.equals(analyseRecord.getFollowed()))
			{
				fansAnalyseDao.updateDanymicFansAnalyse(sitegroupid, type);
			}
			if(level!=analyseRecord.getLevel()){
				fansAnalyseDao.updateADanymicLevelAnalyse(sitegroupid, level);
				fansAnalyseDao.updateDDanymicLevelAnalyse(sitegroupid, analyseRecord.getLevel());
			}

		}
		return hdRecordDao.updateHdReocrdFansStatus(rid, "C");
	}
	
	@Override
	public int saveVisitFansAnalyse(long rid,long sitegroupid, long wbuid, String nickname,
			String type, String followed,int level) {
	    FansAnalyseRecord analyseRecord=fansAnalyseDao.findFansAnalyseRecord(sitegroupid, wbuid);
		fansAnalyseDao.saveFansAnalyse(sitegroupid, wbuid, nickname, type, followed,level);
        if(analyseRecord==null){
            if("Y".equals(type)){
                fansAnalyseDao.saveVistiFansAnalyse(sitegroupid, 1);
            }else{
                fansAnalyseDao.saveVistiUnFansAnalyse(sitegroupid, 1);
            }
            fansAnalyseDao.saveVistiLevelFansAnalyse(sitegroupid, level, 1);
        }else
		{
			if (!type.equals(analyseRecord.getFollowed()))
			{
				fansAnalyseDao.updateVisitFansAnalyse(sitegroupid, type);
			}
			if(level!=analyseRecord.getLevel()){
				fansAnalyseDao.updateAVisitLevelAnalyse(sitegroupid, level);
				fansAnalyseDao.updateDVisitLevelAnalyse(sitegroupid, analyseRecord.getLevel());
			}

		}
		return visitDao.updateVisitLogFansprocessstatus(rid, "C");
	}

	public void setHdRecordDao(IHdRecordDao hdRecordDao) {
		this.hdRecordDao = hdRecordDao;
	}

	public void setVisitDao(IVisitDao visitDao) {
		this.visitDao = visitDao;
	}

	@Override
	public int updateHdRecordError(long rid) {
		return hdRecordDao.updateHdReocrdFansStatus(rid, "E");
	}

	@Override
	public int updateVisitReocordError(long rid) {
		return visitDao.updateVisitLogFansprocessstatus(rid, "E");
	}

    @Override
    public FansAnalyse findFansAnalyse(long sitegroupid) {
        return fansAnalyseDao.findFansAnalyse(sitegroupid);
    }

    @Override
    public List<FansLevelAnalyse> findFansLevelAnalyseList(long sitegroupid) {
        return fansAnalyseDao.findFansLevelAnalyseList(sitegroupid);
    }

    @Override
    public int findLevelDnumTotal(long sitegroupid) {
        return fansAnalyseDao.findLevelDnumTotal(sitegroupid);
    }

    @Override
    public int findLevelVnumTotal(long sitegroupid) {
        return fansAnalyseDao.findLevelVnumTotal(sitegroupid);
    }

	@Override
	public List<FansLevelAnalyse> findFansLevelAnalyseListByOwner(long ownerid)
	{
		return fansAnalyseDao.findFansLevelAnalyseListByOwner(ownerid);
	}

	@Override
	public int updateUserInfoStatus(long wbuid)
	{
		return hdRecordDao.updateUserInfoStatus(wbuid);
	}

}
