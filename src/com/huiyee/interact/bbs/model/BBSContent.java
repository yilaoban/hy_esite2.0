package com.huiyee.interact.bbs.model;

import java.io.Serializable;

import com.huiyee.esite.model.ContentFormRecord;
import com.huiyee.esite.model.ContentNew;
import com.huiyee.esite.model.ContentPicture;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.ContentVideo;
import com.huiyee.esite.model.UserPkvTag;

public class BBSContent implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1588280018759422485L;
	private ContentPicture picture;
	private ContentNew news;
	private ContentVideo video;
	private ContentProduct product;
	private ContentFormRecord formRecord;
	private int zan;
	private int cai;
	private int comment;
	private long pid;
	private String type;
	private long topicid;
	private long forumid;
	private UserPkvTag tags;

	public ContentPicture getPicture()
	{
		return picture;
	}

	public void setPicture(ContentPicture picture)
	{
		this.picture = picture;
	}

	public ContentNew getNews()
	{
		return news;
	}

	public void setNews(ContentNew news)
	{
		this.news = news;
	}

	public ContentVideo getVideo()
	{
		return video;
	}

	public void setVideo(ContentVideo video)
	{
		this.video = video;
	}

	public ContentProduct getProduct()
	{
		return product;
	}

	public void setProduct(ContentProduct product)
	{
		this.product = product;
	}

	public int getZan()
	{
		return zan;
	}

	public void setZan(int zan)
	{
		this.zan = zan;
	}

	public int getCai()
	{
		return cai;
	}

	public void setCai(int cai)
	{
		this.cai = cai;
	}

	public int getComment()
	{
		return comment;
	}

	public void setComment(int comment)
	{
		this.comment = comment;
	}

	public long getPid()
	{
		return pid;
	}

	public void setPid(long pid)
	{
		this.pid = pid;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public long getTopicid()
	{
		return topicid;
	}

	public void setTopicid(long topicid)
	{
		this.topicid = topicid;
	}

	public long getForumid()
	{
		return forumid;
	}

	public void setForumid(long forumid)
	{
		this.forumid = forumid;
	}

	public ContentFormRecord getFormRecord() {
		return formRecord;
	}

	public void setFormRecord(ContentFormRecord formRecord) {
		this.formRecord = formRecord;
	}

	
	public UserPkvTag getTags()
	{
		return tags;
	}

	
	public void setTags(UserPkvTag tags)
	{
		this.tags = tags;
	}

}
