package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.HdModel;
import com.huiyee.esite.model.Site;

public class HdReportDto implements IDto{
	
	private Site site;
	private List<HdModel> models;
	private String data;



	public Site getSite()
	{
		return site;
	}

	public void setSite(Site site)
	{
		this.site = site;
	}

	public List<HdModel> getModels() {
		return models;
	}

	public void setModels(List<HdModel> models) {
		this.models = models;
	}

	public String getData()
	{
		return data;
	}

	public void setData(String data)
	{
		this.data = data;
	}

	

}
