package com.huiyee.esite.mgr;


import java.util.Date;
import java.util.List;

import com.huiyee.esite.dto.DanymicActionRecord;
import com.huiyee.esite.dto.DanymicAton;
import com.huiyee.esite.dto.DanymicUserDetail;
import com.huiyee.esite.dto.DanymicUserRecord;
import com.huiyee.esite.dto.DanymicUserSiftDto;
import com.huiyee.esite.dto.ReportArea;
import com.huiyee.esite.dto.ReportHdNum;
import com.huiyee.esite.dto.ReportSource;
import com.huiyee.esite.dto.ReportTerminalAnalyse;
import com.huiyee.esite.dto.VisitNum;
import com.huiyee.esite.dto.VisitUserDto;
import com.huiyee.esite.model.AreaAnalysis;
import com.huiyee.esite.model.CustomVisitReport;
import com.huiyee.esite.model.HourAnalyse;
import com.huiyee.esite.model.ReportGenderAnalyse;
import com.huiyee.esite.model.VisitAnalyse;
import com.huiyee.esite.model.VisitLog;
import com.huiyee.esite.model.VisitReportDetail;
public interface IVisitReportManager {
    public int findVisitNumBySiteGroup(long sgid,Date date);
    
    public int findDynamicNumBySiteGroup(long sgid,Date date);
    
    public int findDynamicNumBySiteGroupPhone(long sgid) ;
    
    public int findDynamicNumBySiteGroupPC(long sgid) ;
    
    public int findDynamicNumBySiteGroupPad(long sgid) ;
    
    public List<ReportArea> findDymamicAreaBySiteGroup(long sgid);
    
    public String findAreaByIp(String ip);

    public List<AreaAnalysis> showDynamicAreaBySiteGroupId(long sgid,int start,int size);
    
    //查询得到访问情况，生成饼图
    public List<AreaAnalysis> findVisitAreaReport(long sgid);
    
    public List<AreaAnalysis> findDynamicAreaReport(long sgid);
    
    //查詢得到訪問表
    public List<AreaAnalysis> showVisiterBySiteGroupId(long sgid,int start,int size);
     
    public List<VisitReportDetail> findVisitReportDetail(long sgid,String wbuid,int start,int size);
    
    public int findVisitReportDetailTotal(long sgid,String wbuid);
    
    public int sumVisitNum(long sgid);
    
    public int sumHdNum(long sgid);

    public List<DanymicUserRecord> findDanymicRecordListByNickName(long sgid,String nickname,int start, int size);
    
    public int findDanymicRecordListTotalByNickName(long sgid,String nickname);
    
    public int findNoLoginVisitNumBySiteGroup(long sgid,Date date);

    public List<ReportSource> findDymamicSourceBySiteGroup(long sgid);
    
    public int findDynamicTotalBySiteGroup(long sgid);
    
    public int findDynamicNumBySiteGroupGender(long sgid,String gender) ;

    
    public int findNoLogVisitBySiteGroupGender(long sgid,String gender) ;
    
    public int findVisitBySiteGroupGender(long sgid,String gender);
    
    public List<DanymicUserRecord> findDanymicRecordList(long sgid,DanymicUserSiftDto siftDto,int start, int size);
    
    public int findDanymicRecordListTotal(long sgid,DanymicUserSiftDto siftDto);

    //查询结果总数
	public int findVisitReportTotal(long sgid);
	
	//得到互动的总记录数用于分页
	public int findDynamicAreaReportTotal(long sgid);
	
	//得到访问的总记录数用于分页
	public int findVisitAreaReportTotal(long sgid);
	
    //public List<CustomVisitReport> findVisitReport(long sgid,int start,int size);
    
    public List<CustomVisitReport> searchVisitReport(long sgid,VisitUserDto visitdto,int start,int size);
    

    public int searchVisitReportTotal(long sgid,VisitUserDto visitdto);
    
    //public String findReportAreaByIp(String ip);


    public int findDanymicRecordDetailTotal(long sgid,String wbuid,DanymicUserSiftDto siftDto);
    
    public List<DanymicUserDetail> findDanymicRecordDetailList(long sgid,String wbuid,DanymicUserSiftDto siftDto,int start, int size);
    
    public int countVisitNum(long sgid,String wbuid);
    
    public List<VisitAnalyse> findVisitAnalyseByDate(long sgid);
    
    public void insertVisitAnalyse(long sgid,int visitTotalNum,int visitNum,int hdnum,int addVisitNum,String date);
    
    public VisitAnalyse findVisitAnalyse(long sgid,String date);
    
    public int findVisitNumBeforeDate(long sgid,String date);
    
    public int findDynamicNumByHour(long sgid, int hour);
    
    public int findVisitNumByHour(long sgid,int hour);
    
    public HourAnalyse findHourAnalyse(long sgid,int hour);
    
    public List<HourAnalyse> findHourAnalyseBySgid(long sgid) ;
    
    public List<DanymicAton> findAtionBySgid(long sgid);
    
    public List<DanymicUserRecord> findDymamicListAreaBySgid(long sgid);
    
    public ReportArea findReportAreaByIp(String ip);
    
    public void updateAreaAnalyse(long id,long sgid,long aid,String area,int vnum,int hnum,Date lasttime);
    
    public List<VisitLog> findVisitLogListAreaBySgid(long sgid);
    
    public List<ReportArea> findVisitAreaBySiteGroup(long sgid);
    
    public List<DanymicActionRecord> findNoProcessHourStatusDanymicRecordList(int size);
    
    public List<VisitLog> findNoProcessHourStatusVisitLogList(int size);
    
    public void insertHourAnalyse(long id,long sgid,String hour,int vnum,int hnum);
    
    public List<VisitLog> findNoProcessAreaStatusVisitLogList(int size);
    
    public List<DanymicActionRecord> findNoProcessAreaStatusDanymicRecordList(int size);
    
    public List<VisitLog> findNoProcessGenderStatusVisitLogList(int size);
    
    public List<DanymicActionRecord> findNoProcessGenderStatusDanymicRecordList(int size) ;
    
    public void updateGenderAnalyse(long id,long sgid, int vnum, int hnum,String gender);
    
    public ReportGenderAnalyse findReportGenderAnalyseByGsid(long sgid) ;
    
    public List<CustomVisitReport> findVisitRecordListByNickName(long sgid, String nickname, int start, int size);
    
    public int findVisitRecordListTotalByNickName(long sgid, String nickname) ;
    
    public List<CustomVisitReport> findVisitLogUnknow(long sgid,int start,int size);
    
    public int findVisitLogTotalNum(long sgid);
    
    public int findLoginVisitLogTotalNum(long sgid);
    
    public int findTodayDddNum(long sgid);
    
    public int findHdTotalNum(long sgid);
    
    public int findJoinNum(long sgid);
    
    public int findJoinSucNum(long sgid);
    
    public List<AreaAnalysis> findVisitAreaOwner(long owner,int start,int size);
    
    public List<ReportTerminalAnalyse> findReportTerminalAnalyseByGsid(long sgid);
    
    public int findVisitTerminalReportTotal(long sgid);
    
    public int findDynamicTerminalReportTotal(long sgid);
    
    public int findDAreaReportTotal(long sgid);
    
    public int findDymamicHdNumTotalBySiteGroup(long sgid);
    
    public List<ReportHdNum> findDymamicHdNumListBySiteGroup(long sgid,int start,int size) ;
    
    public List<ReportHdNum> findDymamicHdNumBySiteGroup(long sgid);
    
    public int findDymamicHdNumTotal(long sgid);
    
    public int findHdPerNumByNum(long sgid,int num);
    
    public List<AreaAnalysis> findHdAreaOwner(long owner, int start, int size);
    
    public int findVisitPerNumBySgid(long sgid);
    
    public int findVisitNumBySgid(long sgid);
    
    public List<VisitNum> findHdNumListBySgid(long sgid);
	
	public List<VisitNum> findVisitNumListByOwner(long ownerid) ;
	
	public List<VisitNum> findVisitNumListBySgid(long sgid);
	
	public List<VisitNum> findHdNumListByOwner(long ownerid);
	
	public int findVisitPerNumByNum(long sgid,int num) ;
	
	public int findVisitPerNumByNumOwner(long owner,int num);
	
	public int findHdPerNumByNumOwner(long owner,int num);
	
	public int findVisitNumTotalOwner(long owner);
	
	public int findHdNumTotalOwner(long owner);
	
	public List<ReportTerminalAnalyse> findReportTerminalAnalyseByOwner(long ownerid);
	
    public List<ReportArea> findDymamicAreaBySiteOwner(long owner);
    
    public int findDAreaReportTotalByOwner(long owner);
    
    public int findDynamicTerminalReportTotalByOwner(long owner);
    
    public int findNoLoginVisitTotalBySiteGroup(long sgid);

}