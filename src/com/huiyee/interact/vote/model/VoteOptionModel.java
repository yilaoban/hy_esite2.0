package com.huiyee.interact.vote.model;

import java.io.File;
import java.util.Date;
import java.util.List;

public class VoteOptionModel {

	private long id;
	private long voteid;
	private String content;//选项标题
	private String description;//选项描述
	private Date createtime;
	private int count;
	private String pic;
	private File img;
	private String imgFileName;
	private String imgContentType;
	private String vediourl;
	private String tag;
	private String linked;
	private String linkurl;
	
	
	public String getLinked()
	{
		return linked;
	}

	
	public void setLinked(String linked)
	{
		this.linked = linked;
	}

	
	public String getLinkurl()
	{
		return linkurl;
	}

	
	public void setLinkurl(String linkurl)
	{
		this.linkurl = linkurl;
	}

	public String getTag()
	{
		return tag;
	}
	
	public void setTag(String tag)
	{
		this.tag = tag;
	}
	public String getVediourl()
	{
		return vediourl;
	}
	public void setVediourl(String vediourl)
	{
		this.vediourl = vediourl;
	}
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public long getVoteid()
	{
		return voteid;
	}
	public void setVoteid(long voteid)
	{
		this.voteid = voteid;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public Date getCreatetime()
	{
		return createtime;
	}
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}
	public int getCount()
	{
		return count;
	}
	public void setCount(int count)
	{
		this.count = count;
	}
	public File getImg()
	{
		return img;
	}
	public void setImg(File img)
	{
		this.img = img;
	}
	public String getImgFileName()
	{
		return imgFileName;
	}
	public void setImgFileName(String imgFileName)
	{
		this.imgFileName = imgFileName;
	}
	public String getImgContentType()
	{
		return imgContentType;
	}
	public void setImgContentType(String imgContentType)
	{
		this.imgContentType = imgContentType;
	}
	public String getPic()
	{
		return pic;
	}
	public void setPic(String pic)
	{
		this.pic = pic;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
}
