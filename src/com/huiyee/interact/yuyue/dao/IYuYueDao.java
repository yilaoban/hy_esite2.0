package com.huiyee.interact.yuyue.dao;

import java.util.Date;
import java.util.List;

import com.huiyee.interact.yuyue.model.YuYue;
import com.huiyee.interact.yuyue.model.YuYueCatalog;
import com.huiyee.interact.yuyue.model.YuYueRecord;
import com.huiyee.interact.yuyue.model.YuYueSS;
import com.huiyee.interact.yuyue.model.YuYueSSTime;
import com.huiyee.interact.yuyue.model.YuYueService;
import com.huiyee.interact.yuyue.model.YuYueServicer;
import com.huiyee.interact.yuyue.model.YuYueTag;


public interface IYuYueDao
{
	public YuYue findYuYueByOwner(long owner);
	
	public long saveYuYue(YuYue yueyue);
	
	public int findTotalYuYueCatalog(long owner);
	
	public List<YuYueCatalog> findYuYueCatalog(long owner,int start,int size);
	
	public long saveYuYueCatalog(YuYueCatalog yuYueCatalog);
	
	public long delYuYueCatalog(long catid,long yyid);
	
	public YuYueCatalog findYuYueCatalogById(long catid,long owner);
	
	public int updateYuYueCatalog(YuYueCatalog yuYueCatalog);
	
	public int findTotalYuYueServiceByCatid(long catid,long yyid);
	
	public List<YuYueService> findYuYueServiceListByCatid(long catid,long yyid,int start,int size);
	
	public List<YuYueService> findYuYueServiceListByCatid(long catid,long yyid);
	
	public long saveYuYueService(YuYueService yuYueService);
	
	public YuYueService findYuYueServiceById(long yyid,long serviceid);
	
	public int updateYuYueService(YuYueService yuYueService);
	
	public long delYuYueService(long serviceid,long yyid);
	
	public int findTotalYuYueServicerByServiceid(long serviceid);
	
	public int findTotalYuYueServiceBySerid(long serid);
	
	public int findTotalYuYueServicerByCatid(long catid,long yyid );
	
	public List<YuYueServicer> findYuYueServicerListByServiceid(long serviceid,int start,int size);
	
	public List<YuYueService> findYuYueServiceListBySerid(long serid,int start,int size);
	
	public List<YuYueServicer> findYuYueServicerListByCatid(long catid,long yyid,int start,int size);
	
	public List<YuYueServicer> findYuYueServicerListByCatid(long catid,long yyid);
	
	public List<YuYueTag> findYuYueTagListBySerid(long serid);
	
	public long saveYuYueServicer(YuYueServicer yuYueServicer);
	
	public long saveYuYueSS(long serid,long sid);
	
	public YuYueServicer findYuYueServicerById(long serid, long yyid, long catid);
	
	public int updateYuYueServicer(YuYueServicer yuYueServicer);
	
	public long delYuYueServicer(long serid, long yyid);
	
	public long delYuYueSS(long serid,long sid);
	
	public int findTotalYuYueSSTimeBySsid(long ssid,long owner);
	
//	public int findTotalYuYueSSTimeByCatid(long catid,long owner);
	
	public List<YuYueSSTime> findYuYueSSTimeBySsid(long ssid,long owner,int start,int size);
	
	public List<YuYueSSTime> findYuYueSSTimeByCatid(long catid,long owner,int weekday);
	
	public List<YuYueSSTime> findYuYueSSTimeByDateday(long catid,long owner,int dateday);
	
	public int findTotalYuYueSSTimeByTime(long catid,long owner);
	
	public List<YuYueSSTime> findYuYueSSTimeByTime(long catid,long owner,int start,int size);
	
	public List<YuYueSSTime> findYuYueSSTimeByDateday(long owner,int dateday,long ssid);
	
	public List<YuYueSSTime> findYuYueSSTimeByWeekday(long owner,int weekday,long ssid);
	
	public List<YuYueSSTime> findYuYueSSTimeByTimeToTime(long owner,Date yytime,long ssid);
	
	public YuYueSS findYuYueSSBySsid(long ssid);
	
	public int saveYuYueSSTime(YuYueSSTime yuYueSSTime, long owner);
	
	public YuYueSSTime findYuYueSSTimeById(long id, long ssid);
	
	public int updateYuYueSSTime(YuYueSSTime yuYueSSTime);
	
	public int delYuYueSSTime(long id, long ssid);
	
	public int findTotalYuYueTag(long yyid);
	
	public List<YuYueTag> findYuYueTagList(long yyid,int start,int size);
	
	public long findYuYueTag(YuYueTag yuYueTag);
	
	public long saveYuYueTag(YuYueTag yuYueTag);
	
	public long delYuYueTag(long yyid,long id);
	
	public long saveYuYueServicerTag(long serid, long tagid);
	
	public int findTotalYuYueRecord(long owner,String status,Date startTime,Date endTime,Date yytime);
	
	public List<YuYueRecord> findyyRecord(long owner,String status,int start,int size);
	
	public int findTotalYuYueRecord(long owner,String status);
	
	public List<YuYueRecord> findYuYueRecordList(long owner,String status,Date startTime,Date endTime,Date yytime,int start,int size);
	
	public List<YuYueRecord> findYuYueRecordList(long owner,String status,Date yytime);
	
	public YuYueRecord findYuYueRecordById(long id);
	
	public List<YuYueRecord> findYuYueRecordList(long owner,String status,int start,int size);
	
	public int yuyueTotalCatelogRecord(long owner,long catid,int day,String status,Date startTime,Date endTime);
	
	public List<YuYueRecord> yuyueCatelogRecord(long owner,long catid,int day,String status,Date startTime,Date endTime,int start,int size);
	
	public YuYueRecord findYuYueRecordById(long owner,long recordid);
	
	public int delYuYueServicerTag(long serid);
	
	public long findYuYueSS(long serviceid,long serid);
	
	public long findYuYueServicerByCatidAndServicerid(long catid,long serviceid);
	
	public long findYuYueServiceBySerid(long serid);
	
	public int findCatalogMaxIdx(long yyid);
	
	public long findPageIdBySiteid(long siteid,String type);

	public void updateCataLogIdxForDel(long yyid, int idx);

	public YuYueCatalog findFrontCatalog(long catid);

	public int updateCatelogIdx(long id, int idx);

	public YuYueCatalog findNextCatalog(long catid);
	
	public int findServicerMaxIdx(long catid);

	public void updateServicerIdxForDel(long catid, int idx);

	public YuYueServicer findFrontServicer(int idx,int top,long caid,long yyid);

	public int updateServicerIdx(long serid, int idx);

	public YuYueServicer findNextServicer(int idx,int top,long caid,long yyid);

	public YuYueServicer findYuYueServicerById(long serid);
	
	
	public int findServiceMaxIdx(long catid,long yyid);

	public void updateServiceIdxForDel(long catid, int idx,long yyid);

	public int updateServiceIdx(long seid, int idx,long yyid);
	
	public YuYueService findFrontService(long seid,long caid,long yyid);

	public YuYueService findNextService(long seid,long caid,long yyid);

	public int findTotalYuYueNumByServiceid(long serviceid);
	
	public long updateYuYueCatalogType(long catid, String type);
	
	public int findYuYueSSTimeUsedBySSid(long ssid,long owner);
	
	public int updateYYRecord(long recordid, Date yytime);
	
	public int delYYRecord(long recordid);

	public int updateServiceTop(long serid, long owner, int top);
	
	public long findServicerIdByCatid(long catid,long yyid);
	
	public List<YuYueServicer> findYuYueServicerListById(long catid,long serviceid);
	
	public List<YuYueService> findServiceListBySerid(long catid,long serid);

	public List<YuYueCatalog> findYuYueCatalog(long owner);
}
