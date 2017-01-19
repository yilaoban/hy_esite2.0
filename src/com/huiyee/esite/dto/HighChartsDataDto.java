package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.AreaAnalysis;
import com.huiyee.esite.model.HdModel;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.Sitegroup;

public class HighChartsDataDto implements IDto{

	//地区
	private List<List<Object>>  areas;
	
	//人数
	private int num1;
	
	//地区
	private List<ReportArea> area;
	
	private List<AreaAnalysis> list;
	
	//人数
	private int num2;
	
	private Pager pager;
	
	private Site site;
	
	private String areastr;
	
	private List<HdModel> models;

	public int getNum1() {
		return num1;
	}

	public void setNum1(int num1) {
		this.num1 = num1;
	}

	public int getNum2() {
		return num2;
	}

	public void setNum2(int num2) {
		this.num2 = num2;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}



	public Site getSite()
	{
		return site;
	}

	public void setSite(Site site)
	{
		this.site = site;
	}

	public List<List<Object>> getAreas()
	{
		return areas;
	}

	public void setAreas(List<List<Object>> areas)
	{
		this.areas = areas;
	}

	public List<ReportArea> getArea()
	{
		return area;
	}

	public void setArea(List<ReportArea> area)
	{
		this.area = area;
	}

	public List<AreaAnalysis> getList()
	{
		return list;
	}

	public void setList(List<AreaAnalysis> list)
	{
		this.list = list;
	}

	public String getAreastr()
	{
		return areastr;
	}

	public void setAreastr(String areastr)
	{
		this.areastr = areastr;
	}

	public List<HdModel> getModels()
	{
		return models;
	}

	public void setModels(List<HdModel> models)
	{
		this.models = models;
	}

}
