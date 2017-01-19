package com.huiyee.interact.yuyue.mgr;

import java.util.List;

import com.huiyee.interact.appointment.model.AppointmentDataModel;
import com.huiyee.interact.yuyue.dao.IYuYueDao;
import com.huiyee.interact.yuyue.dao.IYuYueFormDao;
import com.huiyee.interact.yuyue.dto.IDto;
import com.huiyee.interact.yuyue.dto.YuYueFromDto;
import com.huiyee.interact.yuyue.model.YuYue;
import com.huiyee.interact.yuyue.model.YuYueCatalog;
import com.huiyee.interact.yuyue.model.YuYueService;
import com.huiyee.interact.yuyue.model.YuYueServicer;
import com.huiyee.interact.yuyue.model.YuYueTag;


public class YuYueFormMgrImpl implements IYuYueFormMgr
{
	private IYuYueDao yuYueDao;
	private IYuYueFormDao yuYueFormDao;
	
	public void setYuYueDao(IYuYueDao yuYueDao)
	{
		this.yuYueDao = yuYueDao;
	}

	public void setYuYueFormDao(IYuYueFormDao yuYueFormDao)
	{
		this.yuYueFormDao = yuYueFormDao;
	}

	@Override
	public IDto findAptRecordByWxuid(long wxuid,long owner)
	{
		YuYueFromDto dto = new YuYueFromDto();
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		if(yuyue != null){
//			wxuid = 28032;
//			yuyue.setAptid(276);
			AppointmentDataModel aptRecord = yuYueFormDao.findAptRecordByWxuid(yuyue.getAptid(),wxuid,1);
			dto.setAptRecord(aptRecord);
		}
		return dto;
	}

	@Override
	public IDto findYuYueCatalog(long owner,String type)
	{
		YuYueFromDto dto = new YuYueFromDto();
//		int total = yuYueDao.findTotalYuYueCatalog(owner);
//		if (total > 0){
			List<YuYueCatalog> list = yuYueFormDao.findYuYueCatalog(owner,type);
			dto.setCatalogList(list);
//		}
		return dto;
	}

	@Override
	public IDto findYuYueServicerListBycatid(long catid, long owner,int pageId,int size)
	{
		YuYueFromDto dto = new YuYueFromDto();
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		YuYueCatalog catalog = yuYueDao.findYuYueCatalogById(catid, owner);
		int total = yuYueDao.findTotalYuYueServicerByCatid(catid,yuyue.getId());
		if (total > 0){
			if(size > 0){
				List<YuYueServicer> servicerList = yuYueDao.findYuYueServicerListByCatid(catid,yuyue.getId(),(pageId - 1) * size,size);
				dto.setServicerList(servicerList);
			}else{
				List<YuYueServicer> servicerList = yuYueDao.findYuYueServicerListByCatid(catid,yuyue.getId());
				dto.setServicerList(servicerList);
			}
		}
		if(dto.getServicerList()!=null){
			for (YuYueServicer yuYueServicer : dto.getServicerList())
			{
				List<YuYueTag> tagList = yuYueDao.findYuYueTagListBySerid(yuYueServicer.getId());
				yuYueServicer.setTagList(tagList);
			}
		}
		dto.setCurrent(catalog);
		return dto;
	}

	@Override
	public IDto findYuYueCatalogById(long catid, long owner)
	{
		YuYueFromDto dto = new YuYueFromDto();
		YuYueCatalog yuYueCatalog = yuYueDao.findYuYueCatalogById(catid,owner);
		dto.setYuYueCatalog(yuYueCatalog);
		return dto;
	}

	@Override
	public IDto findYuYueServiceListBycatid(long catid, long owner,int pageId,int size)
	{
		YuYueFromDto dto = new YuYueFromDto();
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		YuYueCatalog catalog = yuYueDao.findYuYueCatalogById(catid, owner);
		int total = yuYueDao.findTotalYuYueServiceByCatid(catid,yuyue.getId());
		if (total > 0){
			if(size > 0){
				List<YuYueService> serviceList = yuYueDao.findYuYueServiceListByCatid(catid,yuyue.getId(),(pageId - 1) * size,size);
				dto.setServiceList(serviceList);
			}else{
				List<YuYueService> serviceList = yuYueDao.findYuYueServiceListByCatid(catid,yuyue.getId());
				dto.setServiceList(serviceList);
			}
		}
		dto.setCurrent(catalog);
		return dto;
	}

	@Override
	public IDto findYuYueServicerById(long serid, long owner, long catid)
	{
		YuYueFromDto dto = new YuYueFromDto();
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		YuYueCatalog catalog = yuYueDao.findYuYueCatalogById(catid, owner);
		YuYueServicer yuYueServicer = yuYueDao.findYuYueServicerById(serid, yuyue.getId(), catid);
		if(yuYueServicer != null){
			List<YuYueTag> tagList = yuYueDao.findYuYueTagListBySerid(yuYueServicer.getId());
			yuYueServicer.setTagList(tagList);
		}
		dto.setYuYueServicer(yuYueServicer);
		dto.setCurrent(catalog);
		return dto;
	}

	@Override
	public IDto findYuYueServiceById(long catid, long owner, long serviceid)
	{
		YuYueFromDto dto = new YuYueFromDto();
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		YuYueCatalog catalog = yuYueDao.findYuYueCatalogById(catid, owner);
		YuYueService yuYueService = yuYueDao.findYuYueServiceById(yuyue.getId(), serviceid);
//		int num = yuYueDao.findTotalYuYueNumByServiceid(serviceid);
		long serid = yuYueDao.findYuYueServicerByCatidAndServicerid(catid,serviceid);//根据科室 服务 查出医生（唯一）
		long ssid = yuYueDao.findYuYueSS(serviceid, serid);//查询服务、提供者的关系表
		int num = yuYueDao.findYuYueSSTimeUsedBySSid(ssid,owner);
		dto.setYuYueService(yuYueService);
		dto.setCurrent(catalog);
		dto.setYuYueNum(num);
		return dto;
	}

	@Override
	public IDto findServicerListByService(long catid, long owner, long serviceid)
	{
		YuYueFromDto dto = new YuYueFromDto();
		List<YuYueServicer> servicerList = yuYueDao.findYuYueServicerListById(catid,serviceid);
		dto.setServicerList(servicerList);
		return dto;
	}

	@Override
	public IDto findServiceListBySerid(long catid, long serid)
	{
		YuYueFromDto dto = new YuYueFromDto();
		List<YuYueService> serviceList = yuYueDao.findServiceListBySerid(catid,serid);
		dto.setServiceList(serviceList);
		return dto;
	}
}
