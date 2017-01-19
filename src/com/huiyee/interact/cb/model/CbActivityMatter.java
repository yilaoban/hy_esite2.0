package com.huiyee.interact.cb.model;

import java.util.Date;

public class CbActivityMatter {

	private long id;
	private long cbid;
	private long aid;
	private long pageid;
	private long wxshareid;
	private Date updatetime;
	private Date createtime;
	private String del_tag;
	private String pageName;
	private String pic;
	private String title;
	private String description;
	private String kv;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCbid() {
		return cbid;
	}

	public void setCbid(long cbid) {
		this.cbid = cbid;
	}

	public long getAid() {
		return aid;
	}

	public void setAid(long aid) {
		this.aid = aid;
	}

	public long getPageid() {
		return pageid;
	}

	public void setPageid(long pageid) {
		this.pageid = pageid;
	}

	public long getWxshareid() {
		return wxshareid;
	}

	public void setWxshareid(long wxshareid) {
		this.wxshareid = wxshareid;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getDel_tag() {
		return del_tag;
	}

	public void setDel_tag(String del_tag) {
		this.del_tag = del_tag;
	}

	
	public String getPageName()
	{
		return pageName;
	}

	
	public void setPageName(String pageName)
	{
		this.pageName = pageName;
	}

	
	public String getPic()
	{
		return pic;
	}

	
	public void setPic(String pic)
	{
		this.pic = pic;
	}

	
	public String getTitle()
	{
		return title;
	}

	
	public void setTitle(String title)
	{
		this.title = title;
	}

	
	public String getDescription()
	{
		return description;
	}

	
	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getKv()
	{
		return kv;
	}

	public void setKv(String kv)
	{
		this.kv = kv;
	}

}
