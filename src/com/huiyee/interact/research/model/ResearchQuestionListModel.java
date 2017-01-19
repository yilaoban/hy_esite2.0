package com.huiyee.interact.research.model;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ResearchQuestionListModel implements Serializable
{

	private static final long serialVersionUID = 7608329842744680642L;
	
	private long id;
	private long searchid;
	private String type;
	private String isreq;
	private List<String> title;
	private List<String> pic;
	private int idx;
	private Date createtime;
	private List<File> img;
	private List<String> imgFileName;
	private List<String> imgContentType;
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public long getSearchid()
	{
		return searchid;
	}
	public void setSearchid(long searchid)
	{
		this.searchid = searchid;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public List<String> getTitle()
	{
		return title;
	}
	public void setTitle(List<String> title)
	{
		this.title = title;
	}
	public List<String> getPic()
	{
		return pic;
	}
	public void setPic(List<String> pic)
	{
		this.pic = pic;
	}
	public int getIdx()
	{
		return idx;
	}
	public void setIdx(int idx)
	{
		this.idx = idx;
	}
	public Date getCreatetime()
	{
		return createtime;
	}
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}
	public List<File> getImg()
	{
		return img;
	}
	public void setImg(List<File> img)
	{
		this.img = img;
	}
	public List<String> getImgFileName()
	{
		return imgFileName;
	}
	public void setImgFileName(List<String> imgFileName)
	{
		this.imgFileName = imgFileName;
	}
	public List<String> getImgContentType()
	{
		return imgContentType;
	}
	public void setImgContentType(List<String> imgContentType)
	{
		this.imgContentType = imgContentType;
	}
	public String getIsreq()
	{
		return isreq;
	}
	public void setIsreq(String isreq)
	{
		this.isreq = isreq;
	}
	
}
