package com.huiyee.esite.dto;

import com.huiyee.esite.model.Site;
import java.util.List;
import net.sf.json.JSONObject;
public class SiteDto implements IDto {
	private List<String> dates;
	private List<JSONObject> data;
	private Site site;
	private String sdata;
	public List<String> getDates()
	{
		return dates;
	}
	public void setDates(List<String> dates)
	{
		this.dates = dates;
	}
	public Site getSite()
	{
		return site;
	}
	public void setSite(Site site)
	{
		this.site = site;
	}
	public String getSdata()
	{
		return sdata;
	}
	public void setSdata(String sdata)
	{
		this.sdata = sdata;
	}
	public List<JSONObject> getData()
	{
		return data;
	}
	public void setData(List<JSONObject> data)
	{
		this.data = data;
	}
	
}
