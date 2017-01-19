package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.Sitegroup;
import com.huiyee.weixin.model.WxPageShow;


public class SiteGroupDto implements IDto{
    private List<Sitegroup> list;
    private List<WxPageShow> linkList;
    private int pubcount;
    private int cbcount; 
    private Pager pager;
    private List<Sitegroup> xcTemplate;//微现场模版
    private List<Sitegroup> taozhuang;//微现场模版
    
	public int getPubcount()
	{
		return pubcount;
	}
	
	public void setPubcount(int pubcount)
	{
		this.pubcount = pubcount;
	}
	
	public int getCbcount()
	{
		return cbcount;
	}
	
	public void setCbcount(int cbcount)
	{
		this.cbcount = cbcount;
	}
    public List<Sitegroup> getList() {
        return list;
    }
    public void setList(List<Sitegroup> list) {
        this.list = list;
    }
    public Pager getPager() {
        return pager;
    }
    public void setPager(Pager pager) {
        this.pager = pager;
    }

	
	public List<Sitegroup> getXcTemplate()
	{
		return xcTemplate;
	}

	
	public void setXcTemplate(List<Sitegroup> xcTemplate)
	{
		this.xcTemplate = xcTemplate;
	}

	
	public List<WxPageShow> getLinkList()
	{
		return linkList;
	}

	
	public void setLinkList(List<WxPageShow> linkList)
	{
		this.linkList = linkList;
	}

	
	public List<Sitegroup> getTaozhuang()
	{
		return taozhuang;
	}

	
	public void setTaozhuang(List<Sitegroup> taozhuang)
	{
		this.taozhuang = taozhuang;
	}

	

	
}
