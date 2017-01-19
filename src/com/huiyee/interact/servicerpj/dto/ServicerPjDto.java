
package com.huiyee.interact.servicerpj.dto;

import java.util.List;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.interact.servicerpj.model.ServicerPj;
import com.huiyee.interact.servicerpj.model.ServicerPjWd;
import com.huiyee.interact.setting.model.HyUserDz;
import com.huiyee.interact.yuyue.model.YuYueCatalog;
import com.huiyee.interact.yuyue.model.YuYueServicer;

public class ServicerPjDto implements IDto {

	private List<YuYueServicer> list;
	private Pager pager;
	private List<YuYueCatalog> catalogs;
	private List<ServicerPj> pjList;
	private List<HyUserDz> dzList;
	private List<ServicerPjWd> pjwdList;
	
	public List<ServicerPjWd> getPjwdList() {
		return pjwdList;
	}

	public void setPjwdList(List<ServicerPjWd> pjwdList) {
		this.pjwdList = pjwdList;
	}

	public List<YuYueServicer> getList() {
		return list;
	}

	public void setList(List<YuYueServicer> list) {
		this.list = list;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public List<YuYueCatalog> getCatalogs() {
		return catalogs;
	}

	public void setCatalogs(List<YuYueCatalog> catalogs) {
		this.catalogs = catalogs;
	}

	public List<ServicerPj> getPjList() {
		return pjList;
	}

	public void setPjList(List<ServicerPj> pjList) {
		this.pjList = pjList;
	}

	public List<HyUserDz> getDzList() {
		return dzList;
	}

	public void setDzList(List<HyUserDz> dzList) {
		this.dzList = dzList;
	}

}
