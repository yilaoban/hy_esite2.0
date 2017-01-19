package com.huiyee.weixin.model;

public class WxPageShow {

	private long id;
	private long sitegroupid;
	private long pageid;
	private String infoed; // Y 拉取用户信息, N 不拉取
	private String pic;
	private String title;
	private String description;
	private int updateseconds;
	private long ownerid;
	private long wxshareid;
	private String kv;
	private long cachetime;
	private long spageid;
	private long mpid;

	public long getCachetime() {
		return cachetime;
	}

	public void setCachetime(long cachetime) {
		this.cachetime = cachetime;
	}

	public long getWxshareid() {
		return wxshareid;
	}

	public void setWxshareid(long wxshareid) {
		this.wxshareid = wxshareid;
	}

	public String getKv() {
		return kv;
	}

	public void setKv(String kv) {
		this.kv = kv;
	}

	public long getSitegroupid() {
		return sitegroupid;
	}

	public void setSitegroupid(long sitegroupid) {
		this.sitegroupid = sitegroupid;
	}

	public long getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(long ownerid) {
		this.ownerid = ownerid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPageid() {
		return pageid;
	}

	public void setPageid(long pageid) {
		this.pageid = pageid;
	}

	public String getInfoed() {
		return infoed;
	}

	public void setInfoed(String infoed) {
		this.infoed = infoed;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getUpdateseconds() {
		return updateseconds;
	}

	public void setUpdateseconds(int updateseconds) {
		this.updateseconds = updateseconds;
	}

	public long getSpageid() {
		return spageid;
	}

	public void setSpageid(long spageid) {
		this.spageid = spageid;
	}

	public long getMpid() {
		return mpid;
	}

	public void setMpid(long mpid) {
		this.mpid = mpid;
	}

}
