package com.huiyee.interact.yuyue.dto;

import java.util.List;

import com.huiyee.interact.appointment.model.AppointmentDataModel;
import com.huiyee.interact.yuyue.model.YuYueCatalog;
import com.huiyee.interact.yuyue.model.YuYueService;
import com.huiyee.interact.yuyue.model.YuYueServicer;


public class YuYueFromDto implements IDto
{
	private AppointmentDataModel aptRecord;
	private List<YuYueCatalog> catalogList;
	private List<YuYueServicer> servicerList;
	private YuYueCatalog yuYueCatalog;
	private List<YuYueService> serviceList;
	private YuYueServicer yuYueServicer;
	private YuYueService yuYueService;
	private YuYueCatalog current;
	private int yuYueNum;
	
	public int getYuYueNum()
	{
		return yuYueNum;
	}


	
	public void setYuYueNum(int yuYueNum)
	{
		this.yuYueNum = yuYueNum;
	}


	public YuYueCatalog getCurrent()
	{
		return current;
	}

	
	public void setCurrent(YuYueCatalog current)
	{
		this.current = current;
	}

	public YuYueService getYuYueService()
	{
		return yuYueService;
	}
	
	public void setYuYueService(YuYueService yuYueService)
	{
		this.yuYueService = yuYueService;
	}

	public YuYueServicer getYuYueServicer()
	{
		return yuYueServicer;
	}

	public void setYuYueServicer(YuYueServicer yuYueServicer)
	{
		this.yuYueServicer = yuYueServicer;
	}


	public List<YuYueService> getServiceList()
	{
		return serviceList;
	}


	
	public void setServiceList(List<YuYueService> serviceList)
	{
		this.serviceList = serviceList;
	}


	public YuYueCatalog getYuYueCatalog()
	{
		return yuYueCatalog;
	}

	
	public void setYuYueCatalog(YuYueCatalog yuYueCatalog)
	{
		this.yuYueCatalog = yuYueCatalog;
	}

	public AppointmentDataModel getAptRecord()
	{
		return aptRecord;
	}
	
	public void setAptRecord(AppointmentDataModel aptRecord)
	{
		this.aptRecord = aptRecord;
	}

	public List<YuYueCatalog> getCatalogList()
	{
		return catalogList;
	}
	
	public void setCatalogList(List<YuYueCatalog> catalogList)
	{
		this.catalogList = catalogList;
	}

	
	public List<YuYueServicer> getServicerList()
	{
		return servicerList;
	}

	
	public void setServicerList(List<YuYueServicer> servicerList)
	{
		this.servicerList = servicerList;
	}
	
	
}
