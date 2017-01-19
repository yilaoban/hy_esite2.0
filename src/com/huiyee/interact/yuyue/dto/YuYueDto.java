package com.huiyee.interact.yuyue.dto;

import java.util.List;

import com.huiyee.esite.dto.Pager;
import com.huiyee.interact.appointment.model.AppointmentDataModel;
import com.huiyee.interact.setting.model.HyUserDz;
import com.huiyee.interact.yuyue.model.YuYue;
import com.huiyee.interact.yuyue.model.YuYueCatalog;
import com.huiyee.interact.yuyue.model.YuYueRecord;
import com.huiyee.interact.yuyue.model.YuYueSSTime;
import com.huiyee.interact.yuyue.model.YuYueService;
import com.huiyee.interact.yuyue.model.YuYueServicer;
import com.huiyee.interact.yuyue.model.YuYueTag;

public class YuYueDto implements IDto {
	public List<YuYueCatalog> catalogList;
	public List<YuYueService> serviceList;
	public List<YuYueServicer> servicerList;
	public List<YuYueSSTime> ssTimeList;
	public List<YuYueTag> tagList;
	public List<YuYueRecord> recordList;
	private List<HyUserDz> dzList;
	public Pager pager;
	public YuYueCatalog catalog;
	public YuYueService service;
	public YuYueServicer servicer;

	public List<YuYueServicer> showServicerList;
	public List<YuYueService> showServiceList;
	public List<YuYueSSTime> showTimeList;

	public int dateday;
	public YuYue yuyue;
	public YuYueRecord record;
	private AppointmentDataModel aptrecord;
	private boolean bl;//用于判断是否能查看预约收到提醒
	
	public boolean isBl() {
		return bl;
	}

	public void setBl(boolean bl) {
		this.bl = bl;
	}

	public AppointmentDataModel getAptrecord() {
		return aptrecord;
	}

	public void setAptrecord(AppointmentDataModel aptrecord) {
		this.aptrecord = aptrecord;
	}

	public YuYueRecord getRecord() {
		return record;
	}

	public void setRecord(YuYueRecord record) {
		this.record = record;
	}

	public YuYue getYuyue() {
		return yuyue;
	}

	public void setYuyue(YuYue yuyue) {
		this.yuyue = yuyue;
	}

	public int getDateday() {
		return dateday;
	}

	public void setDateday(int dateday) {
		this.dateday = dateday;
	}

	public List<YuYueService> getShowServiceList() {
		return showServiceList;
	}

	public void setShowServiceList(List<YuYueService> showServiceList) {
		this.showServiceList = showServiceList;
	}

	public List<YuYueServicer> getShowServicerList() {
		return showServicerList;
	}

	public void setShowServicerList(List<YuYueServicer> showServicerList) {
		this.showServicerList = showServicerList;
	}

	public List<YuYueRecord> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<YuYueRecord> recordList) {
		this.recordList = recordList;
	}

	public List<YuYueTag> getTagList() {
		return tagList;
	}

	public void setTagList(List<YuYueTag> tagList) {
		this.tagList = tagList;
	}

	public YuYueServicer getServicer() {
		return servicer;
	}

	public void setServicer(YuYueServicer servicer) {
		this.servicer = servicer;
	}

	public List<YuYueSSTime> getSsTimeList() {
		return ssTimeList;
	}

	public void setSsTimeList(List<YuYueSSTime> ssTimeList) {
		this.ssTimeList = ssTimeList;
	}

	public YuYueService getService() {
		return service;
	}

	public void setService(YuYueService service) {
		this.service = service;
	}

	public List<YuYueServicer> getServicerList() {
		return servicerList;
	}

	public void setServicerList(List<YuYueServicer> servicerList) {
		this.servicerList = servicerList;
	}

	public List<YuYueService> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<YuYueService> serviceList) {
		this.serviceList = serviceList;
	}

	public List<YuYueCatalog> getCatalogList() {
		return catalogList;
	}

	public void setCatalogList(List<YuYueCatalog> catalogList) {
		this.catalogList = catalogList;
	}

	public List<HyUserDz> getDzList() {
		return dzList;
	}

	public void setDzList(List<HyUserDz> dzList) {
		this.dzList = dzList;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public YuYueCatalog getCatalog() {
		return catalog;
	}

	public void setCatalog(YuYueCatalog catalog) {
		this.catalog = catalog;
	}

	public List<YuYueSSTime> getShowTimeList() {
		return showTimeList;
	}

	public void setShowTimeList(List<YuYueSSTime> showTimeList) {
		this.showTimeList = showTimeList;
	}

}
