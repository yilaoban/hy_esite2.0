package com.huiyee.interact.research.model;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ResearchQuestionModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5206012798281068883L;
	private long id;
	private long searchid;
	private String type;
	private String title;
	private String pic;
	private int idx;
	private Date createtime;
	private File img;
	private String imgFileName;
	private String imgContentType;
	private String status;
	private String content;
	private String photo;
	private String isreq;
	
	private List<ResearchQuestionOption> options;
	
	
	
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getPhoto()
	{
		return photo;
	}
	public void setPhoto(String photo)
	{
		this.photo = photo;
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
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getPic()
	{
		return pic;
	}
	public void setPic(String pic)
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
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public List<ResearchQuestionOption> getOptions()
	{
		return options;
	}
	public void setOptions(List<ResearchQuestionOption> options)
	{
		this.options = options;
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
