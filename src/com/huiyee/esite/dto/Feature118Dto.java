package com.huiyee.esite.dto;

import java.io.Serializable;
import java.util.List;

import com.huiyee.esite.model.Feature118InteractApt;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.Redirect;
import com.huiyee.esite.model.Style_118;

public class Feature118Dto implements IDto, Serializable{
	private static final long serialVersionUID = 2933642094603586037L;
	
	private List<Feature118InteractApt> interactApt;
	private long aptid;
	private long fid;
	private String name;
	private long relationid;
	private String type;
	private long pageid;
	private Redirect redirect;
	private Style_118 style118;
	private String url;
	private String urlShow;
	private String urlPre;
	private List<Page> pageList;
	private long toPageid;
	private String color;
	private long size;
	private long bottom;
	private String btcontent;
	private String btcolor;
	private long btsize;
	private String btbg;
	private String bttm;
	
	public Redirect getRedirect()
	{
		return redirect;
	}
	
	public void setRedirect(Redirect redirect)
	{
		this.redirect = redirect;
	}


	public String getUrl()
	{
		return url;
	}

	
	public void setUrl(String url)
	{
		this.url = url;
	}

	
	public String getUrlShow()
	{
		return urlShow;
	}

	
	public void setUrlShow(String urlShow)
	{
		this.urlShow = urlShow;
	}

	
	public String getUrlPre()
	{
		return urlPre;
	}

	
	public void setUrlPre(String urlPre)
	{
		this.urlPre = urlPre;
	}

	
	public List<Page> getPageList()
	{
		return pageList;
	}

	
	public void setPageList(List<Page> pageList)
	{
		this.pageList = pageList;
	}

	
	public long getToPageid()
	{
		return toPageid;
	}

	
	public void setToPageid(long toPageid)
	{
		this.toPageid = toPageid;
	}

	public long getRelationid()
	{
		return relationid;
	}

	public void setRelationid(long relationid)
	{
		this.relationid = relationid;
	}

	public List<Feature118InteractApt> getInteractApt() {
		return interactApt;
	}

	public void setInteractApt(List<Feature118InteractApt> interactApt) {
		this.interactApt = interactApt;
	}

	public long getAptid() {
		return aptid;
	}

	public void setAptid(long aptid) {
		this.aptid = aptid;
	}

	public long getFid() {
		return fid;
	}

	public void setFid(long fid) {
		this.fid = fid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	
	public Style_118 getStyle118()
	{
		return style118;
	}

	
	public void setStyle118(Style_118 style118)
	{
		this.style118 = style118;
	}

	
	public String getColor()
	{
		return color;
	}

	
	public void setColor(String color)
	{
		this.color = color;
	}

	
	public long getSize()
	{
		return size;
	}

	
	public void setSize(long size)
	{
		this.size = size;
	}

	
	public long getBottom()
	{
		return bottom;
	}

	
	public void setBottom(long bottom)
	{
		this.bottom = bottom;
	}

	
	public String getBtcontent()
	{
		return btcontent;
	}

	
	public void setBtcontent(String btcontent)
	{
		this.btcontent = btcontent;
	}

	
	public String getBtcolor()
	{
		return btcolor;
	}

	
	public void setBtcolor(String btcolor)
	{
		this.btcolor = btcolor;
	}

	
	public long getBtsize()
	{
		return btsize;
	}

	
	public void setBtsize(long btsize)
	{
		this.btsize = btsize;
	}

	
	public String getBtbg()
	{
		return btbg;
	}

	
	public void setBtbg(String btbg)
	{
		this.btbg = btbg;
	}

	
	public String getBttm()
	{
		return bttm;
	}

	
	public void setBttm(String bttm)
	{
		this.bttm = bttm;
	}

}
