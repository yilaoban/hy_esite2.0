package com.huiyee.esite.dao;

import com.huiyee.esite.dto.DanymicActionRecord;
import com.huiyee.esite.dto.DanymicAton;
import com.huiyee.esite.dto.DanymicUserDetail;
import com.huiyee.esite.dto.DanymicUserRecord;
import com.huiyee.esite.dto.DanymicUserSiftDto;
import com.huiyee.esite.dto.ReportArea;
import com.huiyee.esite.dto.ReportHdNum;
import com.huiyee.esite.dto.ReportSource;
import com.huiyee.esite.dto.ReportTerminalAnalyse;
import java.util.Date;
import java.util.List;
import com.huiyee.esite.model.AreaAnalysis;
import com.huiyee.esite.model.HourAnalyse;
import com.huiyee.esite.model.ReportGenderAnalyse;
import com.huiyee.esite.model.UserInfo;
import com.huiyee.esite.model.VisitAnalyse;

public interface IDynamicActionRecordDao
{
	public int findDynamicNumBySiteGroup(long sgid,Date date);
	
	public int findDynamicNumBySiteGroupPhone(long sgid) ;
	
	public int findDynamicNumBySiteGroupPC(long sgid) ;
	
	public int findDynamicNumBySiteGroupPad(long sgid) ;
	
	public List<ReportArea> findDymamicAreaBySiteGroup(long sgid);
	
	public String findAreaByIp(String ip);
	
	//得到互动总页数用于分页
	public int findDynamicAreaReportTotal(long sgid);
	
	//查询互动总数
	public int sumHdNum(long sgid);
	
	public List<AreaAnalysis> showDynamicBySiteGroupId(long sgid,int start,int size);
	
	public List<AreaAnalysis> findDynamicAreaReport(long sgid);
	
	public List<ReportSource> findDymamicSourceBySiteGroup(long sgid) ;
	
	public int findDynamicTotalBySiteGroup(long sgid);
	
	public int findDynamicNumBySiteGroupGender(long sgid,String gender) ;
	
	public List<DanymicUserRecord> findDanymicRecordList(long sgid,DanymicUserSiftDto siftDto,int start, int size);
	
	public int findDanymicRecordListTotal(long sgid,DanymicUserSiftDto siftDto);
	
    public int addDynamicActionRecord(long pageid,long wbuid,long action,long entityid,String source,String ip,String terminal);
    
    public UserInfo findUserInofByUid(long uid);
    
	public List<DanymicUserRecord> findDanymicRecordListByNickName(long sgid,String nickname,int start, int size);
	
	public int findDanymicRecordListTotalByNickName(long sgid,String nickname);
	
	public List<DanymicUserDetail> findDanymicRecordDetailList(long sgid,String wbuid,DanymicUserSiftDto siftDto,int start, int size) ;
	
	public int findDanymicRecordDetailTotal(long sgid,String wbuid,DanymicUserSiftDto siftDto);
	
	public List<VisitAnalyse> findVisitAnalyseByDate(long sgid);
	
	public VisitAnalyse findVisitAnalyse(long sgid,String date);
	
	public int findDynamicNumByHour(long sgid, int hour);
	
	public HourAnalyse findHourAnalyse(long sgid,int hour);


	public List<HourAnalyse> findHourAnalyseBySgid(long sgid) ;
	
	public List<DanymicAton> findAtionBySgid(long sgid);
	
	public List<DanymicUserRecord> findDymamicListAreaBySgid(long sgid);
	
	public int updateDynamicActionRecordAreaStatus(long id);
	
	public ReportArea findReportAreaByIp(String ip);
	
	public List<ReportArea> findVisitAreaBySiteGroup(long sgid);
	
	public List<DanymicActionRecord> findNoProcessHourStatusDanymicRecordList(int size);
	
    public int updateDynamicActionRecordHourStatus(long id);
    
    public List<DanymicActionRecord> findNoProcessAreaStatusDanymicRecordList(int size);
    
    public List<DanymicActionRecord> findNoProcessGenderStatusDanymicRecordList(int size) ;
    
    public int updateDynamicActionRecordGenderStatus(long id);
    
    public ReportGenderAnalyse findReportGenderAnalyseByGsid(long sgid) ;
    
    public List<ReportTerminalAnalyse> findReportTerminalAnalyseByGsid(long sgid);
    
    public int findDynamicTerminalReportTotal(long sgid);
    
    public int findDAreaReportTotal(long sgid);
    
    public int findDymamicHdNumTotalBySiteGroup(long sgid);
    
    public List<ReportHdNum> findDymamicHdNumListBySiteGroup(long sgid,int start,int size) ;
    
    public List<ReportHdNum> findDymamicHdNumBySiteGroup(long sgid);
    
    public int findDymamicHdNumTotal(long sgid);
    
    public int findHdPerNumByNum(long sgid,int num);
    
    public List<AreaAnalysis> findHdAreaOwner(long owner, int start, int size);
    
    public int findHdPerNumByNumOwner(long owner,int num);
    
    public int findHdNumTotalOwner(long owner);
    
    public List<ReportTerminalAnalyse> findReportTerminalAnalyseByOwner(long ownerid);
    
    public List<ReportArea> findDymamicAreaBySiteOwner(long owner);
    
    public int findDAreaReportTotalByOwner(long owner);
    
    public int findDynamicTerminalReportTotalByOwner(long owner);
	
}
