package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

public class ContentPicture implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4642102452771991254L;
	private long id;
	private long ownerid;
	private long catid;
	private String catname;
	private String title;
	private String imgurl;
	private String status ="DSH";
	private Date createtime;
	private Date updatetime;
	private String linkurl;
	private int idx;
	private int length;
	private String tag;
	private String fatie;
	private long topicid;
	private ContentPicture before;
	private long fatherid;
	
	public ContentPicture getBefore()
	{
		return before;
	}

	
	public void setBefore(ContentPicture before)
	{
		this.before = before;
	}

	
	public ContentPicture getNext()
	{
		return next;
	}

	
	public void setNext(ContentPicture next)
	{
		this.next = next;
	}

	private ContentPicture next;
	

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

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
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

	public String getTag()
	{
		return tag;
	}

	public void setTag(String tag)
	{
		this.tag = tag;
	}

	public String getFatie() {
		return fatie;
	}

	public void setFatie(String fatie) {
		this.fatie = fatie;
	}

	public long getTopicid() {
		return topicid;
	}

	public void setTopicid(long topicid) {
		this.topicid = topicid;
	}


	public long getFatherid() {
		return fatherid;
	}


	public void setFatherid(long fatherid) {
		this.fatherid = fatherid;
	}

}
