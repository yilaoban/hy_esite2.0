package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TemplateModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 650659799534193083L;
	private long id;
	private long tempid;
	private String name;
	private String style;
	private Date createtime;
	private long categoryid;
	private String cpath;
	private String cimg;
	private String ppath;
	private String pimg;
	private String type;
	private String status;
	private String css;
	private List<TemplateCard> cards = new ArrayList<TemplateCard>();
	
	
	public long getCategoryid()
	{
		return categoryid;
	}
	public void setCategoryid(long categoryid)
	{
		this.categoryid = categoryid;
	}
	public String getCpath()
	{
		return cpath;
	}
	public void setCpath(String cpath)
	{
		this.cpath = cpath;
	}
	public String getCimg()
	{
		return cimg;
	}
	public void setCimg(String cimg)
	{
		this.cimg = cimg;
	}
	public String getPpath()
	{
		return ppath;
	}
	public void setPpath(String ppath)
	{
		this.ppath = ppath;
	}
	public String getPimg()
	{
		return pimg;
	}
	public void setPimg(String pimg)
	{
		this.pimg = pimg;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public Date getCreatetime()
	{
		return createtime;
	}
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}
	
	public long getTempid()
	{
		return tempid;
	}
	public void setTempid(long tempid)
	{
		this.tempid = tempid;
	}
	public String getStyle()
	{
		return style;
	}
	public void setStyle(String style)
	{
		this.style = style;
	}
	public String getCss()
	{
		return css;
	}
	public void setCss(String css)
	{
		this.css = css;
	}
	public List<TemplateCard> getCards()
	{
		return cards;
	}
	public void setCards(List<TemplateCard> cards)
	{
		this.cards = cards;
	}
	
	
	
}
