package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

public class ContentVideo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1731822037455335939L;
	private long id;
	private long ownerid;
	private long catid;
	private String catname;
	private String title;
	private String picurl;
	private String videourl;
	private String html;
	private String status;
	private Date createtime;
	private Date updatetime;
	private String linkurl;
	private int idx;
	private int length;
	private ContentVideo before;
	private ContentVideo next;
	private long fatherid;
	
	public ContentVideo getBefore()
	{
		return before;
	}
	
	public void setBefore(ContentVideo before)
	{
		this.before = before;
	}

	public ContentVideo getNext()
	{
		return next;
	}
	
	public void setNext(ContentVideo next)
	{
		this.next = next;
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

	public long getCatid() {
		return catid;
	}

	public void setCatid(long catid) {
		this.catid = catid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getVideourl() {
		return videourl;
	}

	public void setVideourl(String videourl) {
		this.videourl = videourl;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getCatname() {
		return catname;
	}

	public void setCatname(String catname) {
		this.catname = catname;
	}

	public String getLinkurl()
	{
		return linkurl;
	}

	public void setLinkurl(String linkurl)
	{
		this.linkurl = linkurl;
	}

	public int getIdx()
	{
		return idx;
	}

	public void setIdx(int idx)
	{
		this.idx = idx;
	}

	public int getLength()
	{
		return length;
	}

	public void setLength(int length)
	{
		this.length = length;
	}

	public long getFatherid() {
		return fatherid;
	}

	public void setFatherid(long fatherid) {
		this.fatherid = fatherid;
	}
}
