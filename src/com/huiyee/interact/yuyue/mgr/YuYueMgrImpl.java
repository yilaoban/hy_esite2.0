
package com.huiyee.interact.yuyue.mgr;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dao.IPageDao;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.mgr.imp.AbstractMgr;
import com.huiyee.esite.util.DateUtil;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.esite.util.JsonStringUtil;
import com.huiyee.interact.appointment.dao.IInteractAptDao;
import com.huiyee.interact.appointment.model.AppointmentDataModel;
import com.huiyee.interact.appointment.model.AppointmentModel;
import com.huiyee.interact.template.model.WxTemplate;
import com.huiyee.interact.template.model.WxTemplateJob;
import com.huiyee.interact.yuyue.dao.IYuYueDao;
import com.huiyee.interact.yuyue.dto.IDto;
import com.huiyee.interact.yuyue.dto.YuYueDto;
import com.huiyee.interact.yuyue.model.YuYue;
import com.huiyee.interact.yuyue.model.YuYueCatalog;
import com.huiyee.interact.yuyue.model.YuYueRecord;
import com.huiyee.interact.yuyue.model.YuYueSS;
import com.huiyee.interact.yuyue.model.YuYueSSTime;
import com.huiyee.interact.yuyue.model.YuYueService;
import com.huiyee.interact.yuyue.model.YuYueServicer;
import com.huiyee.interact.yuyue.model.YuYueTag;
import com.huiyee.weixin.model.template.GRXXTZ;

public class YuYueMgrImpl extends AbstractMgr implements IYuYueMgr
{

	private IYuYueDao yuYueDao;
	private IInteractAptDao interactAptDao;
	private IPageDao pageDao;

	public void setPageDao(IPageDao pageDao)
	{
		this.pageDao = pageDao;
	}

	public void setInteractAptDao(IInteractAptDao interactAptDao)
	{
		this.interactAptDao = interactAptDao;
	}

	public void setYuYueDao(IYuYueDao yuYueDao)
	{
		this.yuYueDao = yuYueDao;
	}

	@Override
	public YuYue findYuYueByOwner(long owner)
	{
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		if (yuyue != null)
		{
			AppointmentModel apt = interactAptDao.findOrderMes(yuyue.getAptid());
			if (apt != null)
			{
				apt.setList(interactAptDao.findMappingPre(apt.getId()));
				yuyue.setApt(apt);
			}
		}
		return yuyue;
	}

	@Override
	public long saveYuYue(long owner)
	{
		long aptid = interactAptDao.saveSimpleApt(owner, "预约实例" + owner);
		YuYue yuyue = new YuYue();
		yuyue.setAptid(aptid);
		yuyue.setOwner(owner);
		return yuYueDao.saveYuYue(yuyue);
	}

	@Override
	public IDto findYuYueCatalog(long owner, int pageId)
	{
		YuYueDto dto = new YuYueDto();
		int total = yuYueDao.findTotalYuYueCatalog(owner);
		if (total > 0)
		{
			List<YuYueCatalog> list = yuYueDao.findYuYueCatalog(owner, (pageId - 1) * IPageConstants.YU_YUE_LIMIT, IPageConstants.YU_YUE_LIMIT);
			for (YuYueCatalog yuYueCatalog : list)
			{
				yuYueCatalog.setSpage(pageDao.findPageById(yuYueCatalog.getSpageid()));
				yuYueCatalog.setXpage(pageDao.findPageById(yuYueCatalog.getXpageid()));
			}
			dto.setCatalogList(list);
			dto.setPager(new Pager(pageId, total, IPageConstants.OFF_CHECK_LIMIT));
		}

		return dto;
	}

	@Override
	public long saveYuYueCatalog(long owner, YuYueCatalog yuYueCatalog)
	{
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		yuYueCatalog.setYyid(yuyue.getId());
		int idx = yuYueDao.findCatalogMaxIdx(yuyue.getId()) + 1;
		yuYueCatalog.setIdx(idx);
		yuYueCatalog = getCatalog(yuYueCatalog);
		long caid = yuYueDao.saveYuYueCatalog(yuYueCatalog);
		YuYueServicer servicer = new YuYueServicer();
		servicer.setCaid(caid);
		servicer.setType(1);
		servicer.setName("默认");
		servicer.setYyid(yuyue.getId());
		return yuYueDao.saveYuYueServicer(servicer);
	}

	public YuYueCatalog getCatalog(YuYueCatalog yuYueCatalog){
//		YYS-预约成功页;YYX-预约详情页; YYD-预约设置店主页;YYQ-店主确定预约页面;YYZ-预约者确认页面;FWL-服务列表；FZL-服务者列表；FWX-服务详情；FZX-服务者详情
//		long spageid = yuYueDao.findPageIdBySiteid(yuYueCatalog.getSiteid(),"YYS"); YYS YYD 已经删除
		long spageid = yuYueDao.findPageIdBySiteid(yuYueCatalog.getSiteid(),"YYZ");
		long xpageid = yuYueDao.findPageIdBySiteid(yuYueCatalog.getSiteid(),"YYX");
		long dzpageid = yuYueDao.findPageIdBySiteid(yuYueCatalog.getSiteid(),"YYQ");
		long yzpageid = yuYueDao.findPageIdBySiteid(yuYueCatalog.getSiteid(),"YYZ");
		long fwxpageid = yuYueDao.findPageIdBySiteid(yuYueCatalog.getSiteid(),"FWX");
		long fwzxpageid = yuYueDao.findPageIdBySiteid(yuYueCatalog.getSiteid(),"FZX");
		long fwpageid = yuYueDao.findPageIdBySiteid(yuYueCatalog.getSiteid(),"FWL");
		long fwzpageid = yuYueDao.findPageIdBySiteid(yuYueCatalog.getSiteid(),"FZL");
		yuYueCatalog.setSpageid(spageid);
		yuYueCatalog.setXpageid(xpageid);
		yuYueCatalog.setDzpageid(dzpageid);
		yuYueCatalog.setYzpageid(yzpageid);
		yuYueCatalog.setFwpageid(fwpageid);
		yuYueCatalog.setFwxpageid(fwxpageid);
		yuYueCatalog.setFwzpageid(fwzpageid);
		yuYueCatalog.setFwzxpageid(fwzxpageid);
		return yuYueCatalog;
	}
	
	
	@Override
	public long delYuYueCatalog(long catid, long owner)
	{
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		YuYueCatalog yy = yuYueDao.findYuYueCatalogById(catid, owner);
		yuYueDao.updateCataLogIdxForDel(yuyue.getId(), yy.getIdx());
		return yuYueDao.delYuYueCatalog(catid, yuyue.getId());
	}

	@Override
	public YuYueCatalog findYuYueCatalogById(long catid, long owner)
	{
		return yuYueDao.findYuYueCatalogById(catid, owner);
	}

	@Override
	public int updateYuYueCatalog(long owner, YuYueCatalog yuYueCatalog)
	{
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		yuYueCatalog.setYyid(yuyue.getId());
		yuYueCatalog = getCatalog(yuYueCatalog);
		return yuYueDao.updateYuYueCatalog(yuYueCatalog);
	}

	@Override
	public IDto findYuYueServiceListByCatid(long catid, long owner, int pageId)
	{
		YuYueDto dto = new YuYueDto();
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		YuYueCatalog catalog = yuYueDao.findYuYueCatalogById(catid, owner);
		int total = yuYueDao.findTotalYuYueServiceByCatid(catid, yuyue.getId());
		if (total > 0)
		{
			List<YuYueService> serviceList = yuYueDao.findYuYueServiceListByCatid(catid, yuyue.getId(), (pageId - 1) * IPageConstants.YU_YUE_LIMIT, IPageConstants.YU_YUE_LIMIT);
			dto.setServiceList(serviceList);
			dto.setPager(new Pager(pageId, total, IPageConstants.OFF_CHECK_LIMIT));
		}
		dto.setCatalog(catalog);
		return dto;
	}

	@Override
	public long saveYuYueService(long owner, YuYueService yuYueService)
	{
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		yuYueService.setYyid(yuyue.getId());
		int idx=yuYueDao.findServiceMaxIdx(yuYueService.getCaid(), yuyue.getId());
		yuYueService.setIdx(idx+1);
		return yuYueDao.saveYuYueService(yuYueService);
	}

	@Override
	public YuYueService findYuYueServiceById(long serviceid, long owner)
	{
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		return yuYueDao.findYuYueServiceById(yuyue.getId(), serviceid);
	}

	@Override
	public int updateYuYueService(long owner, YuYueService yuYueService)
	{
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		yuYueService.setYyid(yuyue.getId());
		return yuYueDao.updateYuYueService(yuYueService);
	}

	@Override
	public long delYuYueService(long serviceid, long owner)
	{
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		YuYueService yys=yuYueDao.findYuYueServiceById(yuyue.getId(), serviceid);
		yuYueDao.updateServiceIdxForDel(yys.getCaid(), yys.getIdx(), yuyue.getId());
		return yuYueDao.delYuYueService(serviceid, yuyue.getId());
	}

	@Override
	public IDto findYuYueServicerListByServiceid(long serviceid, long catid, long owner, int pageId)
	{
		YuYueDto dto = new YuYueDto();
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		int total = yuYueDao.findTotalYuYueServicerByCatid(catid, yuyue.getId());
		if (total > 0)
		{
			List<YuYueServicer> servicerList = yuYueDao.findYuYueServicerListByCatid(catid, yuyue.getId(), (pageId - 1) * IPageConstants.YU_YUE_LIMIT, IPageConstants.YU_YUE_LIMIT);
			dto.setServicerList(servicerList);
			dto.setPager(new Pager(pageId, total, IPageConstants.OFF_CHECK_LIMIT));
		}
		int total1 = yuYueDao.findTotalYuYueServicerByServiceid(serviceid);
		if (total1 > 0)
		{
			List<YuYueServicer> showServicerList = yuYueDao.findYuYueServicerListByServiceid(serviceid, (pageId - 1) * IPageConstants.YU_YUE_LIMIT, IPageConstants.YU_YUE_LIMIT);
			dto.setShowServicerList(showServicerList);// 关系表里面的提供者
		}
		return dto;
	}

	@Override
	public IDto findYuYueServiceListBySerid(long serid, long catid, long owner, int pageId)
	{
		YuYueDto dto = new YuYueDto();
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		int total = yuYueDao.findTotalYuYueServiceByCatid(catid, yuyue.getId());
		if (total > 0)
		{
			List<YuYueService> serviceList = yuYueDao.findYuYueServiceListByCatid(catid, yuyue.getId(), (pageId - 1) * IPageConstants.YU_YUE_LIMIT, IPageConstants.YU_YUE_LIMIT);
			dto.setServiceList(serviceList);
			dto.setPager(new Pager(pageId, total, IPageConstants.OFF_CHECK_LIMIT));
		}
		int total1 = yuYueDao.findTotalYuYueServiceBySerid(serid);
		if (total1 > 0)
		{
			List<YuYueService> showServiceList = yuYueDao.findYuYueServiceListBySerid(serid, (pageId - 1) * IPageConstants.YU_YUE_LIMIT, IPageConstants.YU_YUE_LIMIT);
			dto.setShowServiceList(showServiceList);// 关系表里面的服务项目
		}
		return dto;
	}

	@Override
	public IDto findYuYueServicerListByServiceid(long catid, long owner, int pageId)
	{
		YuYueDto dto = new YuYueDto();
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		YuYueCatalog catalog = yuYueDao.findYuYueCatalogById(catid, owner);
		int total = yuYueDao.findTotalYuYueServicerByCatid(catid, yuyue.getId());
		if (total > 0)
		{
			List<YuYueServicer> servicerList = yuYueDao.findYuYueServicerListByCatid(catid, yuyue.getId(), (pageId - 1) * IPageConstants.YU_YUE_LIMIT, IPageConstants.YU_YUE_LIMIT);
			for (int i = 0; i < servicerList.size(); i++)
			{
				List<YuYueTag> tagList = yuYueDao.findYuYueTagListBySerid(servicerList.get(i).getId());
				servicerList.get(i).setTagList(tagList);
			}
			dto.setServicerList(servicerList);
			dto.setPager(new Pager(pageId, total, IPageConstants.OFF_CHECK_LIMIT));
		}
		dto.setCatalog(catalog);
		return dto;
	}

	@Override
	public long saveYuYueServicer(YuYueServicer yuYueServicer, long owner)
	{
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		yuYueServicer.setYyid(yuyue.getId());
		int maxIdx = yuYueDao.findServicerMaxIdx(yuYueServicer.getCaid());
		yuYueServicer.setIdx(maxIdx + 1);
		long serid = yuYueDao.saveYuYueServicer(yuYueServicer);
		if (yuYueServicer.getTagname() != null && yuYueServicer.getTagname().size() > 0)
		{
			YuYueTag yuYueTag = new YuYueTag();
			for (int i = 0; i < yuYueServicer.getTagname().size(); i++)
			{
				yuYueTag.setName(yuYueServicer.getTagname().get(i));
				yuYueTag.setYyid(yuyue.getId());
				long tid = yuYueDao.findYuYueTag(yuYueTag);
				if (tid == 0)
				{
					tid = yuYueDao.saveYuYueTag(yuYueTag);
				}
				yuYueDao.saveYuYueServicerTag(serid, tid);
			}
		}
		return serid;
		// return yuYueDao.saveYuYueSS(serid,sid);
	}

	@Override
	public YuYueServicer findYuYueServicerById(long serid, long owner, long catid)
	{
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		YuYueServicer ser = yuYueDao.findYuYueServicerById(serid, yuyue.getId(), catid);
		List<YuYueTag> tagList = yuYueDao.findYuYueTagListBySerid(serid);
		ser.setTagList(tagList);
		return ser;
	}

	@Override
	public int updateYuYueServicer(YuYueServicer yuYueServicer, long owner)
	{
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		yuYueServicer.setYyid(yuyue.getId());
		yuYueDao.delYuYueServicerTag(yuYueServicer.getId());// 删除关系表
		if (yuYueServicer.getTagname().size() > 0)
		{
			YuYueTag yuYueTag = new YuYueTag();
			for (int i = 0; i < yuYueServicer.getTagname().size(); i++)
			{
				yuYueTag.setName(yuYueServicer.getTagname().get(i));
				yuYueTag.setYyid(yuyue.getId());
				long tid = yuYueDao.findYuYueTag(yuYueTag);
				if (tid == 0)
				{
					tid = yuYueDao.saveYuYueTag(yuYueTag);
				}
				yuYueDao.saveYuYueServicerTag(yuYueServicer.getId(), tid);
			}
		}
		return yuYueDao.updateYuYueServicer(yuYueServicer);
	}

	@Override
	public long delYuYueServicer(long serid, long owner, long sid)
	{
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		// yuYueDao.delYuYueSS(serid, sid);
		YuYueServicer s = yuYueDao.findYuYueServicerById(serid);
		yuYueDao.updateServicerIdxForDel(s.getCaid(), s.getIdx());
		return yuYueDao.delYuYueServicer(serid, yuyue.getId());
	}

	@Override
	public IDto findYuYueSSTimeBySsid(long ssid, long owner, int pageId)
	{
		YuYueDto dto = new YuYueDto();
		int total = yuYueDao.findTotalYuYueSSTimeBySsid(ssid, owner);
		if (total > 0)
		{
			List<YuYueSSTime> ssTimeList = yuYueDao.findYuYueSSTimeBySsid(ssid, owner, (pageId - 1) * IPageConstants.YU_YUE_LIMIT, IPageConstants.YU_YUE_LIMIT);
			dto.setSsTimeList(ssTimeList);
			dto.setPager(new Pager(pageId, total, IPageConstants.OFF_CHECK_LIMIT));
		}
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		YuYueSS ss = yuYueDao.findYuYueSSBySsid(ssid);
		long serid = ss.getSerid();
		if (ss != null)
		{
			YuYueService service = yuYueDao.findYuYueServiceById(yuyue.getId(), ss.getSid());
			YuYueCatalog catalog = yuYueDao.findYuYueCatalogById(ss.getCaid(), owner);
			YuYueServicer servicer = yuYueDao.findYuYueServicerById(serid, yuyue.getId(), ss.getCaid());
			dto.setCatalog(catalog);
			dto.setService(service);
			dto.setServicer(servicer);
		}
		return dto;
	}

	@Override
	public IDto findYuYueSSTimeByCatid(long catid, long owner, int weekday)
	{
		YuYueDto dto = new YuYueDto();
		List<YuYueSSTime> ssTimeList = yuYueDao.findYuYueSSTimeByCatid(catid, owner, weekday);
		YuYueCatalog catalog = yuYueDao.findYuYueCatalogById(catid, owner);
		dto.setSsTimeList(ssTimeList);
		dto.setCatalog(catalog);
		return dto;
	}

	@Override
	public IDto findYuYueSSTimeByDateday(long catid, long owner, int dateday)
	{
		YuYueDto dto = new YuYueDto();
		if (dateday == 0)
		{
			Date date = new Date();
			dateday = Integer.parseInt(DateUtil.getDate5String(date));
		}
		if (dateday > 0)
		{
			List<YuYueSSTime> list = yuYueDao.findYuYueSSTimeByDateday(catid, owner, dateday);
			dto.setSsTimeList(list);
		}
		YuYueCatalog catalog = yuYueDao.findYuYueCatalogById(catid, owner);
		dto.setCatalog(catalog);
		dto.setDateday(dateday);
		return dto;
	}

	@Override
	public IDto findYuYueSSTimeByTime(long catid, long owner,int pageId){
		YuYueDto dto = new YuYueDto();
		int total = yuYueDao.findTotalYuYueSSTimeByTime(catid,owner);
		if(total > 0){
			List<YuYueSSTime> ssTimeList = yuYueDao.findYuYueSSTimeByTime(catid,owner,(pageId - 1) * IPageConstants.YU_YUE_LIMIT, IPageConstants.YU_YUE_LIMIT);
			dto.setSsTimeList(ssTimeList);
		}
		YuYueCatalog catalog = yuYueDao.findYuYueCatalogById(catid, owner);
		dto.setCatalog(catalog);
		dto.setPager(new Pager(pageId, total, IPageConstants.YU_YUE_LIMIT));
		return dto;
	}
	
	
	@Override
	public int saveYuYueSSTime(YuYueSSTime yuYueSSTime, long owner, YuYueService yuYueService, YuYueServicer yuYueServicer,long catid)
	{
//		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
//		long serviceid = yuYueService.getId();
//		long serid = yuYueServicer.getId();
		/*if (serviceid == 0)
		{
			yuYueService.setYyid(yuyue.getId());
			int idx=yuYueDao.findServiceMaxIdx(catid, yuyue.getId());
			yuYueService.setIdx(idx+1);
			yuYueService.setCaid(catid);
			serviceid = yuYueDao.saveYuYueService(yuYueService);
		}
		if(serid == -1){//	全部
			serid = yuYueDao.findServicerIdByCatid(catid,yuyue.getId());//查询默认系统提供者
			if(serid == 0){//旧项目没有添加默认提供者 故此加此代码
				YuYueServicer servicer = new YuYueServicer();
				servicer.setCaid(catid);
				servicer.setType(1);
				servicer.setName("全部");
				servicer.setYyid(yuyue.getId());
				serid = yuYueDao.saveYuYueServicer(servicer);
			}
		}else if (serid == 0)
		{
			yuYueServicer.setYyid(yuyue.getId());
			int maxIdx = yuYueDao.findServicerMaxIdx(catid);
			yuYueServicer.setIdx(maxIdx + 1);
			yuYueServicer.setCaid(catid);
			serid = yuYueDao.saveYuYueServicer(yuYueServicer);
		}*/
		String[] serviceids = yuYueService.getIds();
		String[] serids = yuYueServicer.getIds();
		String[] weekdays = yuYueSSTime.getWeekdays();
		String[] startTimes = yuYueSSTime.getStartTimes();
		String[] endTimes = yuYueSSTime.getEndTimes();
		for(int i=0;i<serviceids.length;i++){
			for(int j=0;j<serids.length;j++){
				long ssid = yuYueDao.findYuYueSS(Long.parseLong(serviceids[i]),Long.parseLong(serids[j]));//查询服务和人员关系表
				if (ssid == 0){//如果不存在 则插一条记录
					ssid = yuYueDao.saveYuYueSS(Long.parseLong(serids[j]), Long.parseLong(serviceids[i]));
				}
				yuYueSSTime.setSsid(ssid);
				for(int k=0;k<weekdays.length;k++){
					yuYueSSTime.setWeekday(Integer.parseInt(weekdays[k]));
					if(startTimes.length == endTimes.length){
						for(int l=0;l<startTimes.length;l++){
							yuYueSSTime.setStartTime(startTimes[l]);
							yuYueSSTime.setEndTime(endTimes[l]);
							yuYueDao.saveYuYueSSTime(yuYueSSTime, owner);
						}
					}
				}
			}
			
		}
		return 1;
	}

	@Override
	public int saveYuYueDaySSTime(YuYueSSTime yuYueSSTime, long owner, YuYueService yuYueService, YuYueServicer yuYueServicer,long catid)
	{
		String[] serviceids = yuYueService.getIds();
		String[] serids = yuYueServicer.getIds();
		String[] datedays = yuYueSSTime.getDatedays();
		String[] startTimes = yuYueSSTime.getStartTimes();
		String[] endTimes = yuYueSSTime.getEndTimes();
		for(int i=0;i<serviceids.length;i++){
			for(int j=0;j<serids.length;j++){
				long ssid = yuYueDao.findYuYueSS(Long.parseLong(serviceids[i]),Long.parseLong(serids[j]));//查询服务和人员关系表
				if (ssid == 0){//如果不存在 则插一条记录
					ssid = yuYueDao.saveYuYueSS(Long.parseLong(serids[j]), Long.parseLong(serviceids[i]));
				}
				yuYueSSTime.setSsid(ssid);
				for(int k=0;k<datedays.length;k++){
					yuYueSSTime.setDateday(Integer.parseInt(datedays[k]));
					if(startTimes.length == endTimes.length){
						for(int l=0;l<startTimes.length;l++){
							yuYueSSTime.setStartTime(startTimes[l]);
							yuYueSSTime.setEndTime(endTimes[l]);
							yuYueDao.saveYuYueSSTime(yuYueSSTime, owner);
						}
					}
				}
			}
		}
		return 1;
	}
	
	@Override
	public int saveYuYueTime(YuYueSSTime yuYueSSTime, long owner, YuYueService yuYueService, YuYueServicer yuYueServicer,long catid)
	{
		String[] serviceids = yuYueService.getIds();
		String[] serids = yuYueServicer.getIds();
		String[] startTimes = yuYueSSTime.getStartTimes();
		String[] endTimes = yuYueSSTime.getEndTimes();
		for(int i=0;i<serviceids.length;i++){
			for(int j=0;j<serids.length;j++){
				long ssid = yuYueDao.findYuYueSS(Long.parseLong(serviceids[i]),Long.parseLong(serids[j]));//查询服务和人员关系表
				if (ssid == 0){//如果不存在 则插一条记录
					ssid = yuYueDao.saveYuYueSS(Long.parseLong(serids[j]), Long.parseLong(serviceids[i]));
				}
				yuYueSSTime.setSsid(ssid);
				if(startTimes.length == endTimes.length){
					for(int l=0;l<startTimes.length;l++){
						yuYueSSTime.setStime(startTimes[l]);
						yuYueSSTime.setEtime(endTimes[l]);
						yuYueDao.saveYuYueSSTime(yuYueSSTime, owner);
					}
				}
			}
		}
		return 1;
	}
	
	
	@Override
	public YuYueSSTime findYuYueSSTimeById(long id, long ssid)
	{
		return yuYueDao.findYuYueSSTimeById(id, ssid);
	}

	@Override
	public int updateYuYueSSTime(YuYueSSTime yuYueSSTime,YuYueService yuYueService,YuYueServicer yuYueServicer,long catid)
	{
		YuYue yuyue = yuYueDao.findYuYueByOwner(yuYueSSTime.getOwner());
		long serviceid = yuYueService.getId();
		long serid = yuYueServicer.getId();
		if (serviceid == 0)
		{
			yuYueService.setYyid(yuyue.getId());
			int idx=yuYueDao.findServiceMaxIdx(catid, yuyue.getId());
			yuYueService.setIdx(idx+1);
			yuYueService.setCaid(catid);
			serviceid = yuYueDao.saveYuYueService(yuYueService);
		}
		if(serid == -1){//	全部
			serid = yuYueDao.findServicerIdByCatid(catid,yuyue.getId());//查询默认系统提供者
			if(serid == 0){//旧项目没有添加默认提供者 故此加此代码
				YuYueServicer servicer = new YuYueServicer();
				servicer.setCaid(catid);
				servicer.setType(1);
				servicer.setName("默认");
				servicer.setYyid(yuyue.getId());
				serid = yuYueDao.saveYuYueServicer(servicer);
			}
		}else if (serid == 0)
		{
			yuYueServicer.setYyid(yuyue.getId());
			int maxIdx = yuYueDao.findServicerMaxIdx(catid);
			yuYueServicer.setIdx(maxIdx + 1);
			yuYueServicer.setCaid(catid);
			serid = yuYueDao.saveYuYueServicer(yuYueServicer);
		}
		long ssid = yuYueDao.findYuYueSS(serviceid, serid);
		if (ssid == 0)
		{
			ssid = yuYueDao.saveYuYueSS(serid, serviceid);
		}
		yuYueSSTime.setSsid(ssid);
		return yuYueDao.updateYuYueSSTime(yuYueSSTime);
	}
	
	@Override
	public int updateYuYueSSTimeById(YuYueSSTime yuYueSSTime,YuYueService yuYueService,YuYueServicer yuYueServicer,long catid)
	{
		long serviceid = yuYueService.getId();
		long serid = yuYueServicer.getId();
		long ssid = yuYueDao.findYuYueSS(serviceid, serid);
		if (ssid == 0)
		{
			ssid = yuYueDao.saveYuYueSS(serid, serviceid);
		}
		yuYueSSTime.setSsid(ssid);
		return yuYueDao.updateYuYueSSTime(yuYueSSTime);
	}

	@Override
	public int delYuYueSSTime(long id, long ssid)
	{
		return yuYueDao.delYuYueSSTime(id, ssid);
	}

	@Override
	public IDto findYuYueTagList(long owner, int pageId)
	{
		YuYueDto dto = new YuYueDto();
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		int total = yuYueDao.findTotalYuYueTag(yuyue.getId());
		if (total > 0)
		{
			List<YuYueTag> tagList = yuYueDao.findYuYueTagList(yuyue.getId(), (pageId - 1) * IPageConstants.YU_YUE_LIMIT, IPageConstants.YU_YUE_LIMIT);
			dto.setTagList(tagList);
			dto.setPager(new Pager(pageId, total, IPageConstants.OFF_CHECK_LIMIT));
		}
		return dto;
	}

	@Override
	public long saveYuYueTag(long owner, YuYueTag yuYueTag)
	{
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		yuYueTag.setYyid(yuyue.getId());
		return yuYueDao.saveYuYueTag(yuYueTag);
	}

	@Override
	public long delYuYueTag(long owner, long tagid)
	{
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		return yuYueDao.delYuYueTag(yuyue.getId(), tagid);
	}

	@Override
	public long saveYuYueServicerTag(long serid, long tagid)
	{
		return yuYueDao.saveYuYueServicerTag(serid, tagid);
	}

	@Override
	public IDto findYuYueRecordList(long owner, int pageId,String status,Date startTime,Date endTime,Date yytime)
	{
		YuYueDto dto = new YuYueDto();
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		int total = yuYueDao.findTotalYuYueRecord(owner,status,startTime,endTime,yytime);
		if (total > 0)
		{
			List<YuYueRecord> recordList = yuYueDao.findYuYueRecordList(owner,status,startTime,endTime,yytime, (pageId - 1) * IPageConstants.YU_YUE_LIMIT, IPageConstants.YU_YUE_LIMIT);
			dto.setRecordList(recordList);
			dto.setPager(new Pager(pageId, total, IPageConstants.OFF_CHECK_LIMIT));
		}
		dto.setYuyue(yuyue);
		return dto;
	}
	
	@Override
	public IDto findyyRecord(long owner,int pageId,String status,long hyuid){
		YuYueDto dto = new YuYueDto();
		boolean b = this.isDz(owner, "t2", hyuid);
		if(b){
			YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
			List<YuYueRecord> list = yuYueDao.findyyRecord(owner,status,(pageId - 1) * 10, 10);
			if(list != null){
				for(int i= 0;i<list.size();i++){
					List<YuYueRecord> recordList = yuYueDao.findYuYueRecordList(owner,status,list.get(i).getYytime());
					list.get(i).setRecordList(recordList);
				}
			}
			dto.setYuyue(yuyue);
			dto.setRecordList(list);
		}
		dto.setBl(b);
		return dto;
	}
	
	@Override
	public IDto findYuYueRecordById(long id){
		YuYueDto dto = new YuYueDto();
		YuYueRecord record = yuYueDao.findYuYueRecordById(id);
		dto.setRecord(record);
		return dto;
	}
	
	@Override
	public IDto findYuYueRecordList(long owner,int pageId,String status){
		YuYueDto dto = new YuYueDto();
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		int total = yuYueDao.findTotalYuYueRecord(owner,status);
		if (total > 0)
		{
			List<YuYueRecord> recordList = yuYueDao.findYuYueRecordList(owner,status,(pageId - 1) * IPageConstants.YU_YUE_LIMIT, IPageConstants.YU_YUE_LIMIT);
			dto.setRecordList(recordList);
			dto.setPager(new Pager(pageId, total, IPageConstants.OFF_CHECK_LIMIT));
		}
		dto.setYuyue(yuyue);
		return dto;
	}

	@Override
	public long saveYuYueSS(long serviceid, long serid)
	{
		return yuYueDao.saveYuYueSS(serid, serviceid);
	}

	@Override
	public IDto yuyueCatelogRecord(long owner, long catid, int pageId, int day,String status,Date startTime,Date endTime)
	{
		YuYueDto dto = new YuYueDto();
		YuYueCatalog catalog = yuYueDao.findYuYueCatalogById(catid, owner);
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		int total = yuYueDao.yuyueTotalCatelogRecord(owner, catid, day,status,startTime,endTime);
		if (total > 0)
		{
			List<YuYueRecord> recordList = yuYueDao.yuyueCatelogRecord(owner, catid, day,status,startTime,endTime, (pageId - 1) * IPageConstants.YU_YUE_LIMIT, IPageConstants.YU_YUE_LIMIT);
			dto.setRecordList(recordList);
			dto.setPager(new Pager(pageId, total, IPageConstants.OFF_CHECK_LIMIT));
		}
		dto.setCatalog(catalog);
		dto.setYuyue(yuyue);
		return dto;
	}
	
	@Override
	public IDto findYuYueRecordById(long owner,long recordid){
		YuYueDto dto = new YuYueDto();
		YuYueRecord record = yuYueDao.findYuYueRecordById(recordid);
		dto.setRecord(record);
		return dto;
	}

	@Override
	public IDto saveYuYueServiceAndServicer(long catid, long owner, int weekday)
	{
		YuYueDto dto = new YuYueDto();
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		List<YuYueServicer> servicerList = new ArrayList<YuYueServicer>();
		long serid = yuYueDao.findServicerIdByCatid(catid,yuyue.getId());//查询默认系统提供者
		YuYueServicer servicer = new YuYueServicer();
		if(serid == 0){//旧项目没有添加默认提供者 故此加此代码
			servicer.setCaid(catid);
			servicer.setType(1);
			servicer.setName("默认");
			servicer.setYyid(yuyue.getId());
			serid = yuYueDao.saveYuYueServicer(servicer);
		}else{
			servicer.setName("默认");
		}
		servicer.setId(serid);
		servicerList.add(servicer);
		List<YuYueServicer> list = yuYueDao.findYuYueServicerListByCatid(catid, yuyue.getId());
		if(list != null){
			servicerList.addAll(list);
		}
		List<YuYueService> serviceList = yuYueDao.findYuYueServiceListByCatid(catid, yuyue.getId());
		dto.setServiceList(serviceList);
		dto.setServicerList(servicerList);
		return dto;
	}

	@Override
	public long updateCatalogUp(long catid, long owner)
	{
		YuYueCatalog current = yuYueDao.findYuYueCatalogById(catid, owner);
		YuYueCatalog front = yuYueDao.findFrontCatalog(catid);
		if (current != null && front != null)
		{
			int rs = yuYueDao.updateCatelogIdx(current.getId(), front.getIdx());
			int rs2 = yuYueDao.updateCatelogIdx(front.getId(), current.getIdx());
			return rs + rs2;
		}
		return 0;
	}

	@Override
	public long updateCatalogDown(long catid, long owner)
	{
		YuYueCatalog current = yuYueDao.findYuYueCatalogById(catid, owner);
		YuYueCatalog next = yuYueDao.findNextCatalog(catid);
		if (current != null && next != null)
		{
			int rs = yuYueDao.updateCatelogIdx(current.getId(), next.getIdx());
			int rs2 = yuYueDao.updateCatelogIdx(next.getId(), current.getIdx());
			return rs + rs2;
		}
		return 0;
	}

	@Override
	public int updateServicerIdx(long serid, long owner, int moveUp)
	{
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		YuYueServicer current = yuYueDao.findYuYueServicerById(serid);
		YuYueServicer other = null;
		if (moveUp == 1)
		{
			other = yuYueDao.findFrontServicer(current.getIdx(),current.getTop(),current.getCaid(),yuyue.getId());
		} else
		{
			other = yuYueDao.findNextServicer(current.getIdx(),current.getTop(),current.getCaid(),yuyue.getId());
		}
		if (current != null && other != null)
		{
			int rs = yuYueDao.updateServicerIdx(current.getId(), other.getIdx());
			int rs2 = yuYueDao.updateServicerIdx(other.getId(), current.getIdx());
			return rs + rs2;
		}
		return 0;
	}
	
	@Override
	public int updateServiceIdx(long seid, long owner, int moveUp)
	{
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		YuYueService current = yuYueDao.findYuYueServiceById(yuyue.getId(), seid);
		YuYueService other = null;
		if (moveUp == 1)
		{
			other = yuYueDao.findFrontService(seid,current.getCaid(),yuyue.getId());
		} else
		{
			other = yuYueDao.findNextService(seid,current.getCaid(),yuyue.getId());
		}
		if (current != null && other != null)
		{
			int rs = yuYueDao.updateServiceIdx(current.getId(), other.getIdx(),yuyue.getId());
			int rs2 = yuYueDao.updateServiceIdx(other.getId(), current.getIdx(),yuyue.getId());
			return rs + rs2;
		}
		return 0;
	}

	@Override
	public YuYueSS findYuYueSSBySsid(long ssid,long catid,long owner)
	{
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		long serid = yuYueDao.findServicerIdByCatid(catid, yuyue.getId());
		YuYueSS ss = yuYueDao.findYuYueSSBySsid(ssid);
		if(ss != null && ss.getSerid() == serid){
			ss.setSerid(-1);
		}
		return ss;
	}

	@Override
	public long updateYuYueCatalogType(long catid, String type)
	{
		return yuYueDao.updateYuYueCatalogType(catid,type);
	}

	@Override
	public int updateYYRecord(long recordid, Date yytime,long owner,String onameurl)
	{
		if (owner > 0)
		{
			List<WxTemplate> wts = this.findWxTemplate(owner,"YYU",0);
			if (wts != null&&wts.size()>0)
			{
				String date=DateUtil.getDateTimeString(yytime);
				YuYueRecord yyr=yuYueDao.findYuYueRecordById(owner, recordid);
				String nickname=yyr.getNickname();
				if(nickname==null){
					nickname="";
				}
				for(WxTemplate wt:wts)
				{
				GRXXTZ dd = (GRXXTZ) JsonStringUtil.String2Obj(wt.getData(), GRXXTZ.class);
				dd.setFirst(dd.getFirst().replace("${nickname}", nickname).replace("${time}", date));
				dd.setKeyword1(dd.getKeyword1().replace("${nickname}", nickname).replace("${time}", date));
				dd.setKeyword2(date);
				dd.setKeyword3(dd.getKeyword3().replace("${nickname}", nickname).replace("${time}", date));
				dd.setRemark(dd.getRemark().replace("${nickname}", nickname).replace("${time}", date));
				WxTemplateJob wj = new WxTemplateJob();
				wj.setMpid(wt.getMpid());
				wj.setType(wt.getType());
				wj.setEntityid(wt.getEntityid());
				wj.setRemark(wt.getRemark());
				wj.setTemplate_id(wt.getTemplate_id());
				wj.setTouser(yyr.getOpenid());
				wj.setData(dd.toData());
				if(yyr.getYzpageid()>0)
				wj.setUrl(HyConfig.getPageyuming(owner) + onameurl + "/user/wxshow/" + yyr.getYzpageid() + "/dzx/"+yyr.getId()+".html" );
				this.addTmplMsg(wj);
				}
			}
		}
		
		
		return yuYueDao.updateYYRecord(recordid,yytime);
	}
	
	public int updateYYRecord(long recordid,Date yytime,long owner,String onameurl,AppointmentDataModel record ){
		interactAptDao.updateAptRecord(record);
		return updateYYRecord(recordid, yytime, owner, onameurl);
	}

	@Override
	public int delYYRecord(long recordid)
	{
		return yuYueDao.delYYRecord(recordid);
	}
	
	@Override
	public int updateServiceTop(long serid, long owner, int top) {
		if(top==0||top==1){
			YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
			return yuYueDao.updateServiceTop(serid,yuyue.getId(),top);
		}
		return 0;
	}
	
}
