package com.huiyee.esite.model;

public class MyTempalte {
    
    
    private long id;
    private long ownerid;
    private long tempid;
    private String name;
    private String style;
    
    private String ppath;
    private String cpath;
    private String cimg;
    private String pimg;
    private String type;
    private long categoryid;
    private String categoryName;
    
    private TemplateStyleModel t;
    
    
    public String getCimg()
	{
		return cimg;
	}
	public void setCimg(String cimg)
	{
		this.cimg = cimg;
	}
	public String getPimg()
	{
		return pimg;
	}
	public void setPimg(String pimg)
	{
		this.pimg = pimg;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(long categoryid) {
		this.categoryid = categoryid;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(long ownerid) {
		this.ownerid = ownerid;
	}
	public long getTempid() {
		return tempid;
	}
	public void setTempid(long tempid) {
		this.tempid = tempid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStyle() {
        return style;
    }
    public void setStyle(String style) {
        this.style = style;
    }
	public String getPpath() {
		return ppath;
	}
	public void setPpath(String ppath) {
		this.ppath = ppath;
	}
	public String getCpath() {
		return cpath;
	}
	public void setCpath(String cpath) {
		this.cpath = cpath;
	}
	public TemplateStyleModel getT()
	{
		return t;
	}
	public void setT(TemplateStyleModel t)
	{
		this.t = t;
	}

}
