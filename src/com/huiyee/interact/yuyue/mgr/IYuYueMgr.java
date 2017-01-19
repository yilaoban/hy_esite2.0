package com.huiyee.interact.yuyue.mgr;

import java.util.Date;

import com.huiyee.interact.appointment.model.AppointmentDataModel;
import com.huiyee.interact.yuyue.dto.IDto;
import com.huiyee.interact.yuyue.model.YuYue;
import com.huiyee.interact.yuyue.model.YuYueCatalog;
import com.huiyee.interact.yuyue.model.YuYueSS;
import com.huiyee.interact.yuyue.model.YuYueSSTime;
import com.huiyee.interact.yuyue.model.YuYueService;
import com.huiyee.interact.yuyue.model.YuYueServicer;
import com.huiyee.interact.yuyue.model.YuYueTag;


public interface IYuYueMgr
{
	public YuYue findYuYueByOwner(long owner);
	
	public long saveYuYue(long owner);
	
	public IDto findYuYueCatalog(long owner,int pageId);
	
	public long saveYuYueCatalog(long owner,YuYueCatalog yuYueCatalog);
	
	public long delYuYueCatalog(long catid,long owner);
	
	public long updateYuYueCatalogType(long catid,String type);
	
	public YuYueCatalog findYuYueCatalogById(long catid,long owner);
	
	public int updateYuYueCatalog(long owner,YuYueCatalog yuYueCatalog);
	
	public IDto findYuYueServiceListByCatid(long catid,long owner,int pageId);
	
	public long saveYuYueService(long owner,YuYueService yuYueService);
	
	public YuYueService findYuYueServiceById(long serviceid,long owner);
	
	public int updateYuYueService(long owner,YuYueService yuYueService);
	
	public long delYuYueService(long serviceid,long owner);
	
	public IDto findYuYueServicerListByServiceid(long serviceid,long catid,long owner,int pageId );
	
	public IDto findYuYueServiceListBySerid(long serid,long catid,long owner,int pageId);
	
	public IDto findYuYueServicerListByServiceid(long catid,long owner,int pageId);
	
	public long saveYuYueServicer(YuYueServicer yuYueServicer,long owner);
	
	public YuYueServicer findYuYueServicerById(long serid,long owner,long catid);
	
	public int updateYuYueServicer(YuYueServicer yuYueServicer,long owner);
	
	public long delYuYueServicer(long serid,long owner,long sid);
	
	public IDto findYuYueSSTimeBySsid(long ssid,long owner,int pageId );
	
	public IDto findYuYueSSTimeByCatid(long catid,long owner,int weekday);
	
	public IDto findYuYueSSTimeByDateday(long catid,long owner,int dateday);
	
	public IDto findYuYueSSTimeByTime(long catid,long owner,int pageId);
	
	public int saveYuYueSSTime(YuYueSSTime yuYueSSTime,long owner,YuYueService yuYueService,YuYueServicer yuYueServicer,long catid);
	
	public int saveYuYueDaySSTime(YuYueSSTime yuYueSSTime,long owner,YuYueService yuYueService,YuYueServicer yuYueServicer,long catid);
	
	public int saveYuYueTime(YuYueSSTime yuYueSSTime,long owner,YuYueService yuYueService,YuYueServicer yuYueServicer,long catid);
	
	public YuYueSSTime findYuYueSSTimeById(long id,long ssid);
	
	public int updateYuYueSSTime(YuYueSSTime yuYueSSTime,YuYueService yuYueService,YuYueServicer yuYueServicer,long catid);
	
	public int updateYuYueSSTimeById(YuYueSSTime yuYueSSTime,YuYueService yuYueService,YuYueServicer yuYueServicer,long catid);
	
	public int delYuYueSSTime(long id,long ssid);
	
	public IDto findYuYueTagList(long owner,int pageId);
	
	public long saveYuYueTag(long owner,YuYueTag yuYueTag);
	
	public long delYuYueTag(long owner,long tagid);
	
	public long saveYuYueServicerTag(long serid,long tagid);
	
	public IDto findYuYueRecordList(long owner,int pageId,String status,Date startTime,Date endTime,Date yytime);
	
	public IDto yuyueCatelogRecord(long owner,long catid,int pageId,int day,String status,Date startTime,Date endTime);
	
	public IDto findyyRecord(long owner,int pageId,String status,long hyuid);
	
	public IDto findYuYueRecordById(long id);
	
	public IDto findYuYueRecordList(long owner,int pageId,String status);
	
	public IDto findYuYueRecordById(long owner,long recordid);
	
	public int updateYYRecord(long recordid,Date yytime,long owner,String onameurl );
	
	public int updateYYRecord(long recordid,Date yytime,long owner,String onameurl,AppointmentDataModel record );
	
	public long saveYuYueSS(long serviceid,long serid);
	
	public IDto saveYuYueServiceAndServicer(long catid,long owner,int weekday);

	public long updateCatalogUp(long catid, long owner);
	
	public long updateCatalogDown(long catid, long owner);

	public int updateServicerIdx(long serid, long owner, int moveUp);
	
	public int updateServiceIdx(long seid, long owner, int moveUp);
	
	public YuYueSS findYuYueSSBySsid(long ssid,long catid,long owner);
	
	public int delYYRecord(long recordid);

	public int updateServiceTop(long serid, long owner, int top);
}
