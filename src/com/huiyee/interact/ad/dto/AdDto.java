
package com.huiyee.interact.ad.dto;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.model.AreaProvince;
import com.huiyee.interact.ad.model.AdMedia;
import com.huiyee.interact.ad.model.AdWay;
import com.huiyee.interact.ad.model.AdWaygg;
import com.huiyee.interact.ad.model.Adgg;
import com.huiyee.interact.ad.model.Adwd;

public class AdDto implements IDto
{

	private List<AdWay> list;
	private Pager pager;
	private List<AdWaygg> wgList;
	private List<Adgg> ggList;
	private List<AdMedia> adMediaList; 
	
	public List<AdMedia> getAdMediaList()
	{
		return adMediaList;
	}

	
	public void setAdMediaList(List<AdMedia> adMediaList)
	{
		this.adMediaList = adMediaList;
	}

	public List<AdWay> getList()
	{
		return list;
	}

	public void setList(List<AdWay> list)
	{
		this.list = list;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	
	public List<AdWaygg> getWgList()
	{
		return wgList;
	}

	
	public void setWgList(List<AdWaygg> wgList)
	{
		this.wgList = wgList;
	}

	public List<Adgg> getGgList()
	{
		return ggList;
	}

	public void setGgList(List<Adgg> ggList)
	{
		this.ggList = ggList;
	}
	
	public String getWgIds(){
		String str="";
		if(wgList!=null&&wgList.size()>0){
			for (AdWaygg wg : wgList)
			{
				if(str.length()>0){
					str+=","+wg.getGgid();
				}else{
					str+=wg.getGgid();
				}
			}
		}
		return str;
	}
}
