
package com.huiyee.esite.mgr.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dao.IActivityDao;
import com.huiyee.esite.dao.IContentCategoryDao;
import com.huiyee.esite.mgr.IActivityManager;
import com.huiyee.esite.model.Activity;
import com.huiyee.esite.model.ActivityLog;
import com.huiyee.esite.model.ActivityReportAnalyse;
import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.Feature;
import com.huiyee.esite.model.Module;
import com.huiyee.esite.model.Source;
import com.huiyee.esite.model.Terminal;
import com.huiyee.esite.model.UserInfo;
import com.huiyee.esite.model.VisitLog;
import com.huiyee.interact.cb.dao.IActivityMatterDao;
import com.huiyee.weixin.model.WxPageShow;

public class ActivityManagerImpl implements IActivityManager
{

	private IActivityDao activityDao;
	private IContentCategoryDao categoryDao;
	private IActivityMatterDao activityMatterDao;

	
	public void setActivityMatterDao(IActivityMatterDao activityMatterDao)
	{
		this.activityMatterDao = activityMatterDao;
	}

	public void setCategoryDao(IContentCategoryDao categoryDao)
	{
		this.categoryDao = categoryDao;
	}

	public void setActivityDao(IActivityDao activityDao)
	{
		this.activityDao = activityDao;
	}

	@Override
	public int findActivityCounts(long id)
	{
		return activityDao.findActivityAll(id);
	}

	@Override
	public long saveActivityAndModule(String name, List<Long> modules, long siteid, long ownerid)
	{
		long activityid = activityDao.saveActivity(name, siteid, ownerid);
		for (Long l : modules)
		{
			activityDao.savaActivityModule(activityid, l);
		}
		return activityid;
	}

	@Override
	public List<Activity> findActivityList(long siteid)
	{
		return activityDao.findActivityName(siteid);
	}

	@Override
	public List<Activity> findActivityId(long siteid, String name)
	{
		return activityDao.findActivityId(name, siteid);
	}

	@Override
	public List<Module> findModuleList(long activityid)
	{
		return activityDao.findModuleList(activityid);
	}

	@Override
	public List<Feature> findActivityFeature(long activityid)
	{
		return activityDao.findActivityFeature(activityid);
	}

	@Override
	public String delteActivity(long activityid)
	{
		int rs = activityDao.deleteActivity(activityid);
		if (rs == 1)
		{
			return "Y";
		}
		return "N";
	}

	@Override
	public Activity findActivityNameById(long activityid)
	{
		return activityDao.findActivityNameById(activityid);
	}

	@Override
	public String updateActivity(long activityid, ArrayList<Long> moduleList, String activityname)
	{
		activityDao.updateActivity(activityname, activityid);
		activityDao.deleteActivityModule(activityid);
		for (Long l : moduleList)
		{
			activityDao.savaActivityModule(activityid, l);
		}
		return "Y";
	}

	@Override
	public List<Long> findModuleIdByActivityid(long activityid)
	{
		return activityDao.findModuleIdByActivityid(activityid);
	}

	@Override
	public List<Activity> findActivityId(long siteid)
	{
		return activityDao.findActivityId(siteid);
	}

	@Override
	public VisitLog findUnkonwnUserCount(long activityid)
	{
		return activityDao.findUnkonwnUserCount(activityid);
	}

	@Override
	public VisitLog findUserCount(long activityid)
	{
		return activityDao.findUserCount(activityid);
	}

	@Override
	public List<Activity> findActivityListBysitegrouid(long activityid)
	{
		return activityDao.findActivityListBysitegrouid(activityid);
	}

	@Override
	public List<Terminal> findVisitCountByTerminal(long activityid)
	{
		return activityDao.findVisitCountByTerminal(activityid);
	}

	@Override
	public List<Terminal> findAnonVisitCountByTerminal(long activityid)
	{
		return activityDao.findAnonVisitCountByTerminal(activityid);
	}

	@Override
	public int findAnonVisitCountBySource(String name, long activityid)
	{
		return activityDao.findAnonVisitCountBySource(name, activityid);
	}

	@Override
	public List<Source> findVistitCountBySource(long activityid)
	{
		return activityDao.findVistitCountBySource(activityid);
	}

	@Override
	public int findAnonParticipateCountBySource(String name, long activityid)
	{
		return activityDao.findAnonParticipateCountBySource(name, activityid);
	}

	@Override
	public int findAnonParticipateCountByTerminal(String name, long activityid)
	{
		return activityDao.findAnonParticipateCountByTerminal(name, activityid);
	}

	@Override
	public List<Source> findParticipateCountBySource(long activityid)
	{
		return activityDao.findParticipateCountBySource(activityid);
	}

	@Override
	public List<Terminal> findParticipateCountByTerminal(long activityid)
	{
		return activityDao.findParticipateCountByTerminal(activityid);
	}

	@Override
	public int findAnonParticipateSuccessCountBySource(String name, long activityid)
	{
		return activityDao.findAnonParticipateSuccessCountBySource(name, activityid);
	}

	@Override
	public int findAnonParticipateSuccessCountByTerminal(String name, long activityid)
	{
		return activityDao.findAnonParticipateSuccessCountByTerminal(name, activityid);
	}

	@Override
	public List<Source> findParticipateSuccessCountBySource(long activityid)
	{
		return activityDao.findParticipateSuccessCountBySource(activityid);
	}

	@Override
	public List<Terminal> findParticipateSuccessCountByTerminal(long activityid)
	{
		return activityDao.findParticipateSuccessCountByTerminal(activityid);
	}

	@Override
	public int findParticipateCount(long activityid)
	{
		return activityDao.findParticipateCount(activityid);
	}

	@Override
	public int findParticipateSuccessCount(long activityid)
	{
		return activityDao.findParticipateSuccessCount(activityid);
	}

	@Override
	public List<VisitLog> findAnonVisitData(long activityid, int start, int pageSize)
	{
		return activityDao.findAnonVisitData(activityid, start, pageSize);
	}

	@Override
	public List<VisitLog> findnonAnonVisitData(long activityid, int start, int pageSize)
	{
		return activityDao.findnonAnonVisitData(activityid, start, pageSize);
	}

	@Override
	public List<VisitLog> findnonAnonVisitDataDetails(long activityid, String visitTime1, String visitTime2, String terminal, String source, int page, int pageSize)
	{
		return activityDao.findnonAnonVisitDataDetails(activityid, visitTime1, visitTime2, terminal, source, page, pageSize);
	}

	@Override
	public List<Terminal> findTerminalNames(long activityid)
	{
		return activityDao.findTerminalNames(activityid);
	}

	@Override
	public List<Source> findSourceNames(long activityid)
	{
		return activityDao.findSourceNames(activityid);
	}

	@Override
	public List<VisitLog> findnonAnonVisitDataDetails(long activityid, String nickname, int start, int pageSize)
	{
		return activityDao.findnonAnonVisitDataDetails(activityid, nickname, start, pageSize);
	}

	@Override
	public int findnonAnonVisitCount(long activityid)
	{
		return activityDao.findnonAnonVisitCount(activityid);
	}

	@Override
	public int findnonAnonVisitDataDetailsCount(long activityid, String visitTime1, String visitTime2, String terminal, String source)
	{
		return activityDao.findnonAnonVisitDataDetailsCount(activityid, visitTime1, visitTime2, terminal, source);
	}

	@Override
	public int findnonAnonVisitDataDetailsCount(long activityid, String nickname)
	{
		return activityDao.findnonAnonVisitDataDetailsCount(activityid, nickname);
	}

	@Override
	public int findAnonVisitDataCount(long activityid)
	{
		return activityDao.findAnonVisitDataCount(activityid);
	}

	@Override
	public int findAnonVisitCountByTerminal(String name, long activityid)
	{
		return activityDao.findAnonVisitCountByTerminal(name, activityid);
	}

	@Override
	public List<ActivityLog> findActivityParticipateDetails(long activityid, String name, int start, int pageSize)
	{
		return activityDao.findActivityParticipateDetails(activityid, name, start, pageSize);
	}

	@Override
	public UserInfo findNickNames(long siteid, long wbuid)
	{
		return activityDao.findNickNames(siteid, wbuid);
	}

	@Override
	public Activity findActivityByActivityid(long activityid)
	{
		return activityDao.findActivityByActivityid(activityid);
	}

	@Override
	public int findActivityParticipateDetailsCount(String terminalName, long activityid, String visitTime1, String visitTime2, String source)
	{
		return activityDao.findActivityParticipateDetailsCount(terminalName, activityid, visitTime1, visitTime2, source);
	}

	@Override
	public List<ActivityLog> findActivityParticipateDetails(long activityid, String terminalName, String visitTime1, String visitTime2, String source, int start, int pageSize)
	{
		return activityDao.findActivityParticipateDetails(activityid, terminalName, visitTime1, visitTime2, source, start, pageSize);
	}

	@Override
	public List<Source> findActivityParticipateSourceNames(String terminalName, long activityid)
	{
		return activityDao.findActivityParticipateSourceNames(terminalName, activityid);
	}

	@Override
	public List<UserInfo> findWbuid(long siteid, String nickname)
	{
		return activityDao.findWbuid(siteid, nickname);
	}

	@Override
	public int findActivityParticipateDetailsByName(long activityid, String terminalName, long wbuid)
	{
		return activityDao.findActivityParticipateDetailsByName(activityid, terminalName, wbuid);
	}

	@Override
	public List<ActivityLog> findActivityParticipateDetailsByName(long activityid, String terminalName, long wbuid, int start, int pageSize)
	{
		return activityDao.findActivityParticipateDetailsByName(activityid, terminalName, wbuid, start, pageSize);
	}

	@Override
	public List<ActivityLog> findActivityParticipateSuccessDetails(long activityid, String name, int start, int pageSize)
	{
		return activityDao.findActivityParticipateSuccessDetails(activityid, name, start, pageSize);
	}

	@Override
	public int findActivityParticipateSuccessDetailsCount(String terminalName, long activityid, String visitTime1, String visitTime2, String source)
	{
		return activityDao.findActivityParticipateSuccessDetailsCount(terminalName, activityid, visitTime1, visitTime2, source);
	}

	@Override
	public List<ActivityLog> findActivityParticipateSuccessDetails(long activityid, String terminalName, String visitTime1, String visitTime2, String source, int start,
			int pageSize)
	{
		return activityDao.findActivityParticipateSuccessDetails(activityid, terminalName, visitTime1, visitTime2, source, start, pageSize);
	}

	@Override
	public List<Source> findActivityParticipateSuccessSourceNames(String terminalName, long activityid)
	{
		return activityDao.findActivityParticipateSuccessSourceNames(terminalName, activityid);
	}

	@Override
	public int findActivityParticipateSuccessDetailsByName(long activityid, String terminalName, long wbuid)
	{
		return activityDao.findActivityParticipateSuccessDetailsByName(activityid, terminalName, wbuid);
	}

	@Override
	public List<ActivityLog> findActivityParticipateSuccessDetailsByName(long activityid, String terminalName, long wbuid, int start, int pageSize)
	{
		return activityDao.findActivityParticipateSuccessDetailsByName(activityid, terminalName, wbuid, start, pageSize);
	}

	@Override
	public int findParticipateFailCountByTerminal(String name, long activityid)
	{
		return activityDao.findParticipateFailCountByTerminal(name, activityid);
	}

	@Override
	public List<ActivityLog> findActivityParticipateFailDetails(long activityid, String name, int start, int pageSize)
	{
		return activityDao.findActivityParticipateFailDetails(activityid, name, start, pageSize);
	}

	@Override
	public List<Source> findActivityParticipateFailSourceNames(String terminalName, long activityid)
	{
		return activityDao.findActivityParticipateFailSourceNames(terminalName, activityid);
	}

	@Override
	public int findActivityParticipateFailDetailsCount(String terminalName, long activityid, String visitTime1, String visitTime2, String source)
	{
		return activityDao.findActivityParticipateFailDetailsCount(terminalName, activityid, visitTime1, visitTime2, source);
	}

	@Override
	public List<ActivityLog> findActivityParticipateFailDetails(long activityid, String terminalName, String visitTime1, String visitTime2, String source, int start, int pageSize)
	{
		return activityDao.findActivityParticipateFailDetails(activityid, terminalName, visitTime1, visitTime2, source, start, pageSize);
	}

	@Override
	public int findActivityParticipateFailDetailsByName(long activityid, String terminalName, long wbuid)
	{
		return activityDao.findActivityParticipateFailDetailsByName(activityid, terminalName, wbuid);
	}

	@Override
	public List<ActivityLog> findActivityParticipateFailDetailsByName(long activityid, String terminalName, long wbuid, int start, int pageSize)
	{
		return activityDao.findActivityParticipateFailDetailsByName(activityid, terminalName, wbuid, start, pageSize);
	}

	@Override
	public List<Terminal> findParticipateFailCountByTerminal(long activityid)
	{
		return activityDao.findParticipateFailCountByTerminal(activityid);
	}

	@Override
	public List<Source> findParticipateFailCountBySource(long activityid)
	{
		return activityDao.findParticipateFailCountBySource(activityid);
	}

	@Override
	public UserInfo findwbuidByUid(long uid)
	{
		return activityDao.findwbuidByUid(uid);
	}

	@Override
	public long saveActivityLog(long activityid, long wbuid, String type, String ip, String terminal, String source)
	{
		return activityDao.saveActivityLog(activityid, wbuid, type, ip, terminal, source);
	}

	@Override
	public List<ContentCategory> findContentCategory(long ownerid)
	{
		/**
		 * 所有pageid不为0的新闻目录
		 */
		List<ContentCategory> list = categoryDao.findCcHasPageId(ownerid);
		return list;
	}
	
	@Override
	public WxPageShow findWxPageShowById(long wxshareid,long aid)
	{
		return activityMatterDao.findWxPageShowById(wxshareid,aid);
	}
}
