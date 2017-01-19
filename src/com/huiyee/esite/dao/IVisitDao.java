package com.huiyee.esite.dao;

import java.util.Date;
import java.util.List;

import com.huiyee.esite.dto.VisitNum;
import com.huiyee.esite.dto.VisitUserDto;
import com.huiyee.esite.model.AreaAnalysis;
import com.huiyee.esite.model.CustomVisitReport;
import com.huiyee.esite.model.VisitLog;
import com.huiyee.esite.model.VisitReportDetail;
import com.huiyee.esite.model.VisitUser;

public interface IVisitDao
{
	public void insertVisitLogAnonymous(long siteid,long pageid, String ip,String agent,String term,String key);

	public void insertVisitLog(long siteid,long pageid, VisitUser vu, String ip,String agent,String term,String key);

	public List<VisitLog> findVistLogBySiteGidAndtype(long siteid, String type, int page);

	public int findTotalVistLogBySiteidAndtype(long siteid, String type);
	
	public List<VisitLog> findVistLogBySiteAndtype(long siteid, String type, int page);
	
	public int findTotalVistLogBySiteAndtype(long siteid, String type);

	public List<VisitLog> findVistLogExport(long sitegroupid, String type);
	
	public int findVisitNumBySiteGroup(long sgid,Date date);
	
	public int findNoLoginVisitNumBySiteGroup(long sgid,Date date);
	
	public int findNoLogVisitBySiteGroupGender(long sgid,String gender) ;
	
	public int findVisitBySiteGroupGender(long sgid,String gender);
	
	//得到访问的总页数用于分页
	public int findVisitAreaReportTotal(long sgid);
	
	public List<AreaAnalysis> findVisitArea(long sgid,int start,int size);
	
	public List<VisitReportDetail> findVisitReportDetail(long sgid,String wbuid,int start,int size);
	
	public int findVisitReportDetailTotal(long sgid,String wbuid);
	
	//查询得到访问情况，生成饼图
	public List<AreaAnalysis> findVisitAreaReport(long sgid);
	
	//查询访问总数
	public int sumVisitNum(long sgid);
	
	//查询结果总数
	public int findVisitReportTotal(long sgid);
	
	/**
	 * 统计出查看详情访问的次数
	 * @param sgid
	 * @param wbuid
	 * @return
	 */
	public int countVisitNum(long sgid,String wbuid);
	
	//表单查询
	public List<CustomVisitReport> searchVisitReport(long sgid,VisitUserDto visitdto,int start,int size);
	
	public int searchVisitReportTotal(long sgid,VisitUserDto visitdto);
	
	//public List<CustomVisitReport> findReportByForm();
	public void insertVisitAnalyse(long sgid,int visitTotalNum,int visitNum,int hdnum,int addVisitNum,String date);
	
	public int findVisitNumBeforeDate(long sgid,String date);
	
	public void insertHourAnalyse(long sgid,int hour,int visitnum,int hdnum);
	
	public int findVisitNumByHour(long sgid,int hour);
	
	public void insertAreaAnalyse(long sgid,long aid,String area,int vnum,int hnum,Date lasttime);
	
	public List<VisitLog> findVisitLogListAreaBySgid(long sgid);
	
	public int updateVisitLogAreaStatus(long id);
	
	public List<VisitLog> findVisitLogNotProcess(int size);
	
	public int updateVisitLogFansprocessstatus(long id,String type);
	
	public int updateVisitLogHourStatus(long id) ;
	
	public List<VisitLog> findNoProcessHourStatusVisitLogList(int size);
	
	public void insertHourAnalyse(long sgid,String hour,int vnum,int hnum);
	
	public List<VisitLog> findNoProcessAreaStatusVisitLogList(int size);
	
	public List<VisitLog> findNoProcessGenderStatusVisitLogList(int size);
	
	public int updateVisitLogGenderStatus(long id);
	
    public void insertGenderAnalyse(long sgid,int vnum,int hnum,String gender);
    
    public int findVisitRecordListTotalByNickName(long sgid, String nickname);
    
    public List<CustomVisitReport> findVisitRecordListByNickName(long sgid,
            String nickname, int start, int size);
    public List<CustomVisitReport> findVisitLogUnknow(long sgid,int start,int size);
    
    public int findVisitLogTotalNum(long sgid);
    
    public int findLoginVisitLogTotalNum(long sgid);
    
    public int findTodayDddNum(long sgid);
    
    public int findHdTotalNum(long sgid);
    
    public int findJoinNum(long sgid);
    
    public int findJoinSucNum(long sgid);
    
    public List<AreaAnalysis> findVisitAreaOwner(long owner,int start,int size);
    
    public int findVisitTerminalReportTotal(long sgid);
    
    public int findVisitPerNumBySgid(long sgid);
    
    public int findVisitNumBySgid(long sgid);
    
	public List<VisitNum> findHdNumListBySgid(long sgid);
	
	public List<VisitNum> findVisitNumListByOwner(long ownerid) ;
	
	public List<VisitNum> findVisitNumListBySgid(long sgid);
	
	public List<VisitNum> findHdNumListByOwner(long ownerid);
	
	public int findVisitPerNumByNum(long sgid,int num) ;
	
	public int findVisitPerNumByNumOwner(long owner,int num);
	
	public int findVisitNumTotalOwner(long owner);
	
	public int findNoLoginVisitTotalBySiteGroup(long sgid);
}
