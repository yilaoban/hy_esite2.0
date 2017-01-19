package com.huiyee.esite.mgr.imp;

import java.util.Date;
import java.util.List;

import com.huiyee.esite.dao.IDynamicActionRecordDao;
import com.huiyee.esite.dao.IVisitDao;
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
import com.huiyee.esite.mgr.IVisitReportManager;
import com.huiyee.esite.model.AreaAnalysis;
import com.huiyee.esite.model.CustomVisitReport;
import com.huiyee.esite.model.HourAnalyse;
import com.huiyee.esite.model.ReportGenderAnalyse;
import com.huiyee.esite.model.VisitAnalyse;
import com.huiyee.esite.model.VisitLog;
import com.huiyee.esite.model.VisitReportDetail;
public class VisitReportManagerImpl implements IVisitReportManager {
    private IVisitDao visitDao;
    private IDynamicActionRecordDao dynamicActionRecordDao;
    @Override
    public int findDynamicNumBySiteGroup(long sgid, Date date) {
        return dynamicActionRecordDao.findDynamicNumBySiteGroup(sgid, date);
    }

    @Override
    public int findVisitNumBySiteGroup(long sgid, Date date) {
        return visitDao.findVisitNumBySiteGroup(sgid, date);
    }
    
    public void setDynamicActionRecordDao(IDynamicActionRecordDao dynamicActionRecordDao) {
        this.dynamicActionRecordDao = dynamicActionRecordDao;
    }
    public void setVisitDao(IVisitDao visitDao) {
        this.visitDao = visitDao;
    }

    @Override
    public int findDynamicNumBySiteGroupPC(long sgid) {
        return dynamicActionRecordDao.findDynamicNumBySiteGroupPC(sgid);
    }

    @Override
    public int findDynamicNumBySiteGroupPad(long sgid) {
        return dynamicActionRecordDao.findDynamicNumBySiteGroupPad(sgid);
    }

    @Override
    public int findDynamicNumBySiteGroupPhone(long sgid) {
        return dynamicActionRecordDao.findDynamicNumBySiteGroupPhone(sgid);
    }

    @Override
    public List<ReportArea> findDymamicAreaBySiteGroup(long sgid) {
        List<ReportArea> list= dynamicActionRecordDao.findDymamicAreaBySiteGroup(sgid);
        return list;
    }

    @Override
    public String findAreaByIp(String ip) {
        return dynamicActionRecordDao.findAreaByIp(ip);
    }


	@Override
	public List<AreaAnalysis> showDynamicAreaBySiteGroupId(long sgid,int start,int size) {
		
		return dynamicActionRecordDao.showDynamicBySiteGroupId(sgid,start,size);
	}
    
	//查询得到访问表
	public List<AreaAnalysis> showVisiterBySiteGroupId(long sgid,int start,int size){
		
		return visitDao.findVisitArea(sgid, start, size);
	}
	


    @Override
    public int findNoLoginVisitNumBySiteGroup(long sgid, Date date) {
        return visitDao.findNoLoginVisitNumBySiteGroup(sgid, date);
    }



    @Override
    public List<ReportSource> findDymamicSourceBySiteGroup(long sgid) {
        return dynamicActionRecordDao.findDymamicSourceBySiteGroup(sgid);
    }

    @Override
    public int findDynamicTotalBySiteGroup(long sgid) {
        return dynamicActionRecordDao.findDynamicTotalBySiteGroup(sgid);
    }

    @Override
    public int findDynamicNumBySiteGroupGender(long sgid, String gender) {
        return dynamicActionRecordDao.findDynamicNumBySiteGroupGender(sgid, gender);
    }




    @Override
    public int findNoLogVisitBySiteGroupGender(long sgid, String gender) {
        return visitDao.findNoLogVisitBySiteGroupGender(sgid, gender);
    }

    @Override
    public int findVisitBySiteGroupGender(long sgid, String gender) {
        return visitDao.findVisitBySiteGroupGender(sgid, gender);
    }

    @Override
    public List<DanymicUserRecord> findDanymicRecordList(long sgid, DanymicUserSiftDto siftDto, int start, int size) {
        List<DanymicUserRecord> list= dynamicActionRecordDao.findDanymicRecordList(sgid, siftDto, start, size);
        for (DanymicUserRecord danymicUserRecord : list) {
            String ip=danymicUserRecord.getIp();
            if(ip!=null&&!"".equals(ip)){
                String area=dynamicActionRecordDao.findAreaByIp(ip);
                danymicUserRecord.setArea(area);
            }
        }
        return list;
    }

    @Override
    public int findDanymicRecordListTotal(long sgid, DanymicUserSiftDto siftDto) {
        return dynamicActionRecordDao.findDanymicRecordListTotal(sgid, siftDto);
    }


//	@Override
//	public List<CustomVisitReport> findVisitReport(long sgid,int start,int size) {
//		
//		return visitDao.findVisitReport(sgid,start,size);
//	}

//	@Override
//	public List<CustomVisitReport> findReportByForm(int num, String startdate,
//			String enddate) {
//		
//		return visitDao.findReportByForm(num, startdate, enddate);
//	}


    @Override
    public List<DanymicUserRecord> findDanymicRecordListByNickName(long sgid, String nickname, int start, int size) {
        List<DanymicUserRecord> list=dynamicActionRecordDao.findDanymicRecordListByNickName(sgid, nickname, start, size);
        for (DanymicUserRecord danymicUserRecord : list) {
            String ip=danymicUserRecord.getIp();
            if(ip!=null&&!"".equals(ip)){
                String area=dynamicActionRecordDao.findAreaByIp(ip);
                danymicUserRecord.setArea(area);
            }
        }
        return list;
    }

    @Override
    public int findDanymicRecordListTotalByNickName(long sgid, String nickname) {
        return dynamicActionRecordDao.findDanymicRecordListTotalByNickName(sgid, nickname);
    }

    @Override
    public List<DanymicUserDetail> findDanymicRecordDetailList(long sgid, String wbuid, DanymicUserSiftDto siftDto,
            int start, int size) {
        return dynamicActionRecordDao.findDanymicRecordDetailList(sgid, wbuid, siftDto, start, size);
    }

    @Override
    public int findDanymicRecordDetailTotal(long sgid, String wbuid, DanymicUserSiftDto siftDto) {
        return dynamicActionRecordDao.findDanymicRecordDetailTotal(sgid, wbuid, siftDto);
    }
    @Override
    public List<VisitAnalyse> findVisitAnalyseByDate(long sgid) {
        return dynamicActionRecordDao.findVisitAnalyseByDate(sgid);
    }
    
	@Override
	public List<CustomVisitReport> searchVisitReport(long sgid,VisitUserDto visitdto,int start,int size) {
		List<CustomVisitReport> list=visitDao.searchVisitReport(sgid, visitdto, start, size);
		return list;
	}
	@Override
	public int searchVisitReportTotal(long sgid,VisitUserDto visitdto){
		return visitDao.searchVisitReportTotal(sgid, visitdto);
	}


	@Override
	public int findVisitReportTotal(long sgid) {
		
		return visitDao.findVisitReportTotal(sgid);
	}

	@Override
	public int findDynamicAreaReportTotal(long sgid) {
		
		return dynamicActionRecordDao.findDynamicAreaReportTotal(sgid);
	}

	@Override
	public int findVisitAreaReportTotal(long sgid) {
		
		return visitDao.findVisitAreaReportTotal(sgid);
	}

	//查询得到访问表
	@Override
	public List<AreaAnalysis> findVisitAreaReport(long sgid) {
		
		return visitDao.findVisitAreaReport(sgid);
	}

	@Override
	public List<AreaAnalysis> findDynamicAreaReport(long sgid) {
		
		return dynamicActionRecordDao.findDynamicAreaReport(sgid);
	}


    @Override
    public void insertVisitAnalyse(long sgid, int visitTotalNum, int visitNum, int hdnum, int addVisitNum, String date) {
        visitDao.insertVisitAnalyse(sgid, visitTotalNum, visitNum, hdnum, addVisitNum, date);
    }

    @Override
    public VisitAnalyse findVisitAnalyse(long sgid, String date) {
        return dynamicActionRecordDao.findVisitAnalyse(sgid, date);
    }

    @Override
    public int findVisitNumBeforeDate(long sgid, String date) {
        return visitDao.findVisitNumBeforeDate(sgid, date);
    }

    @Override
    public int findDynamicNumByHour(long sgid, int hour) {
        return dynamicActionRecordDao.findDynamicNumByHour(sgid, hour);
    }

    @Override
    public int findVisitNumByHour(long sgid, int hour) {
        return visitDao.findVisitNumByHour(sgid, hour);
    }

    @Override
    public HourAnalyse findHourAnalyse(long sgid, int hour) {
        return dynamicActionRecordDao.findHourAnalyse(sgid, hour);
    }

    @Override
    public List<HourAnalyse> findHourAnalyseBySgid(long sgid) {
        return dynamicActionRecordDao.findHourAnalyseBySgid(sgid);
    }

	@Override
	public int sumHdNum(long sgid) {
		
		return dynamicActionRecordDao.sumHdNum(sgid);
	}

	@Override
	public int sumVisitNum(long sgid) {
		
		return visitDao.sumVisitNum(sgid);
	}


    @Override
    public List<DanymicAton> findAtionBySgid(long sgid) {
        return dynamicActionRecordDao.findAtionBySgid(sgid);
    }


    @Override
    public List<DanymicUserRecord> findDymamicListAreaBySgid(long sgid) {
        return dynamicActionRecordDao.findDymamicListAreaBySgid(sgid);
    }

    @Override
    public ReportArea findReportAreaByIp(String ip) {
        return dynamicActionRecordDao.findReportAreaByIp(ip);
    }

    @Override
    public void updateAreaAnalyse(long id,long sgid, long aid, String area, int vnum, int hnum,Date lasttime) {
        visitDao.insertAreaAnalyse(sgid, aid, area, vnum, hnum,lasttime);
        if(vnum==1){
            visitDao.updateVisitLogAreaStatus(id);
        }
        if(hnum==1){
            dynamicActionRecordDao.updateDynamicActionRecordAreaStatus(id);
        }
    }
    
    @Override
    public void updateGenderAnalyse(long id,long sgid, int vnum, int hnum,String gender) {
        visitDao.insertGenderAnalyse(sgid,vnum, hnum,gender);
        if(vnum==1){
            visitDao.updateVisitLogGenderStatus(id);
        }
        if(hnum==1){
            dynamicActionRecordDao.updateDynamicActionRecordGenderStatus(id);
        }
    }

    @Override
    public List<VisitLog> findVisitLogListAreaBySgid(long sgid) {
        return visitDao.findVisitLogListAreaBySgid(sgid);
    }

    @Override
    public List<ReportArea> findVisitAreaBySiteGroup(long sgid) {
        return dynamicActionRecordDao.findVisitAreaBySiteGroup(sgid);
    }

	@Override
	public List<VisitReportDetail> findVisitReportDetail(long sgid,
			String wbuid,  int start, int size) {
		return visitDao.findVisitReportDetail(sgid, wbuid,  start, size);
	}

	@Override
	public int findVisitReportDetailTotal(long sgid, String wbuid) {
		return visitDao.findVisitReportDetailTotal(sgid, wbuid);
	}

	@Override
	public int countVisitNum(long sgid, String wbuid) {
		return visitDao.countVisitNum(sgid, wbuid);
	}

    @Override
    public List<DanymicActionRecord> findNoProcessHourStatusDanymicRecordList(int size) {
        return dynamicActionRecordDao.findNoProcessHourStatusDanymicRecordList(size);
    }

    @Override
    public List<VisitLog> findNoProcessHourStatusVisitLogList(int size) {
        return visitDao.findNoProcessHourStatusVisitLogList(size);
    }

    @Override
    public void insertHourAnalyse(long id,long sgid, String hour, int vnum, int hnum) {
        visitDao.insertHourAnalyse(sgid, hour, vnum, hnum);
        if(vnum==1){
            visitDao.updateVisitLogHourStatus(id);
        }
        if(hnum==1){
            dynamicActionRecordDao.updateDynamicActionRecordHourStatus(id);
        }
    }

    @Override
    public List<DanymicActionRecord> findNoProcessAreaStatusDanymicRecordList(int size) {
        return dynamicActionRecordDao.findNoProcessAreaStatusDanymicRecordList(size);
    }

    @Override
    public List<VisitLog> findNoProcessAreaStatusVisitLogList(int size) {
        return visitDao.findNoProcessAreaStatusVisitLogList(size);
    }

    @Override
    public List<VisitLog> findNoProcessGenderStatusVisitLogList(int size) {
        return visitDao.findNoProcessGenderStatusVisitLogList(size);
    }

    @Override
    public List<DanymicActionRecord> findNoProcessGenderStatusDanymicRecordList(int size) {
        return dynamicActionRecordDao.findNoProcessGenderStatusDanymicRecordList(size);
    }

    @Override
    public ReportGenderAnalyse findReportGenderAnalyseByGsid(long sgid) {
        return dynamicActionRecordDao.findReportGenderAnalyseByGsid(sgid);
    }
    @Override
    public List<CustomVisitReport> findVisitRecordListByNickName(long sgid, String nickname, int start, int size) {
        List<CustomVisitReport> list=visitDao.findVisitRecordListByNickName(sgid, nickname, start, size);
        for (CustomVisitReport customVisitReport : list) {
            String ip=customVisitReport.getIp();
            if(ip!=null&&!"".equals(ip)){
                String area=dynamicActionRecordDao.findAreaByIp(ip);
                customVisitReport.setArea(area);
            }
        }
        return list;
    }
    @Override
    public int findVisitRecordListTotalByNickName(long sgid, String nickname) {
        return visitDao.findVisitRecordListTotalByNickName(sgid, nickname);
    }

    @Override
    public List<CustomVisitReport> findVisitLogUnknow(long sgid, int start, int size) {
        return visitDao.findVisitLogUnknow(sgid, start, size);
    }

    @Override
    public int findLoginVisitLogTotalNum(long sgid) {
        return visitDao.findLoginVisitLogTotalNum(sgid);
    }

    @Override
    public int findVisitLogTotalNum(long sgid) {
        return visitDao.findVisitLogTotalNum(sgid);
    }

    @Override
    public int findTodayDddNum(long sgid) {
        return visitDao.findTodayDddNum(sgid);
    }

    @Override
    public int findHdTotalNum(long sgid) {
        return visitDao.findHdTotalNum(sgid);
    }

    @Override
    public int findJoinNum(long sgid) {
        return visitDao.findJoinNum(sgid);
    }

    @Override
    public int findJoinSucNum(long sgid) {
        return visitDao.findJoinSucNum(sgid);
    }

	@Override
	public List<AreaAnalysis> findVisitAreaOwner(long owner, int start, int size)
	{
		return visitDao.findVisitAreaOwner(owner, start, size);
	}

	@Override
	public List<ReportTerminalAnalyse> findReportTerminalAnalyseByGsid(long sgid)
	{
		return dynamicActionRecordDao.findReportTerminalAnalyseByGsid(sgid);
	}

	@Override
	public int findDynamicTerminalReportTotal(long sgid)
	{
		return dynamicActionRecordDao.findDynamicTerminalReportTotal(sgid);
	}

	@Override
	public int findVisitTerminalReportTotal(long sgid)
	{
		return visitDao.findVisitTerminalReportTotal(sgid);
	}

	@Override
	public int findDAreaReportTotal(long sgid)
	{
		return dynamicActionRecordDao.findDAreaReportTotal(sgid);
	}

	@Override
	public List<ReportHdNum> findDymamicHdNumBySiteGroup(long sgid)
	{
		return dynamicActionRecordDao.findDymamicHdNumBySiteGroup(sgid);
	}

	@Override
	public List<ReportHdNum> findDymamicHdNumListBySiteGroup(long sgid, int start, int size)
	{
		return dynamicActionRecordDao.findDymamicHdNumListBySiteGroup(sgid, start, size);
	}

	@Override
	public int findDymamicHdNumTotalBySiteGroup(long sgid)
	{
		return dynamicActionRecordDao.findDymamicHdNumTotalBySiteGroup(sgid);
	}

	@Override
	public int findDymamicHdNumTotal(long sgid)
	{
		return dynamicActionRecordDao.findDymamicHdNumTotal(sgid);
	}

	@Override
	public int findHdPerNumByNum(long sgid, int num)
	{
		return dynamicActionRecordDao.findHdPerNumByNum(sgid, num);
	}

	@Override
	public List<AreaAnalysis> findHdAreaOwner(long owner, int start, int size){
		return dynamicActionRecordDao.findHdAreaOwner(owner, start, size);
	}

	@Override
	public int findVisitNumBySgid(long sgid)
	{
		return visitDao.findVisitNumBySgid(sgid);
	}

	@Override
	public int findVisitPerNumBySgid(long sgid)
	{
		return visitDao.findVisitPerNumBySgid(sgid);
	}

	@Override
	public List<VisitNum> findHdNumListByOwner(long ownerid)
	{
		return visitDao.findHdNumListByOwner(ownerid);
	}

	@Override
	public List<VisitNum> findHdNumListBySgid(long sgid)
	{
		return visitDao.findHdNumListBySgid(sgid);
	}

	@Override
	public List<VisitNum> findVisitNumListByOwner(long ownerid)
	{
		return visitDao.findVisitNumListByOwner(ownerid);
	}

	@Override
	public List<VisitNum> findVisitNumListBySgid(long sgid)
	{
		return visitDao.findVisitNumListBySgid(sgid);
	}

	@Override
	public int findHdPerNumByNumOwner(long owner, int num)
	{
		return dynamicActionRecordDao.findHdPerNumByNumOwner(owner, num);
	}

	@Override
	public int findVisitPerNumByNum(long sgid, int num)
	{
		return visitDao.findVisitPerNumByNum(sgid, num);
	}

	@Override
	public int findVisitPerNumByNumOwner(long owner, int num)
	{
		return visitDao.findVisitPerNumByNumOwner(owner, num);
	}

	@Override
	public int findVisitNumTotalOwner(long owner)
	{
		return visitDao.findVisitNumTotalOwner(owner);
	}

	@Override
	public int findHdNumTotalOwner(long owner)
	{
		return dynamicActionRecordDao.findHdNumTotalOwner(owner);
	}

	@Override
	public List<ReportTerminalAnalyse> findReportTerminalAnalyseByOwner(long ownerid)
	{
		return dynamicActionRecordDao.findReportTerminalAnalyseByOwner(ownerid);
	}

	@Override
	public List<ReportArea> findDymamicAreaBySiteOwner(long owner)
	{
		return dynamicActionRecordDao.findDymamicAreaBySiteOwner(owner);
	}

	@Override
	public int findDAreaReportTotalByOwner(long owner)
	{
		return dynamicActionRecordDao.findDAreaReportTotalByOwner(owner);
	}

	@Override
	public int findDynamicTerminalReportTotalByOwner(long owner)
	{
		return dynamicActionRecordDao.findDynamicTerminalReportTotalByOwner(owner);
	}

	@Override
	public int findNoLoginVisitTotalBySiteGroup(long sgid)
	{
		return visitDao.findNoLoginVisitTotalBySiteGroup(sgid);
	}    

}
