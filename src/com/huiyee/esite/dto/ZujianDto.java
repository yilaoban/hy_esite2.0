package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.AreaCity;
import com.huiyee.esite.model.AreaProvince;
import com.huiyee.esite.model.TemplateBlock;

public class ZujianDto implements IDto{
	
	private List<TemplateBlock> zjList;

	public List<TemplateBlock> getZjList() {
		return zjList;
	}

	public void setZjList(List<TemplateBlock> zjList) {
		this.zjList = zjList;
	}
	
}
