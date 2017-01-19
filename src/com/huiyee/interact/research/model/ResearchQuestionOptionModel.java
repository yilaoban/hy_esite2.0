package com.huiyee.interact.research.model;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ResearchQuestionOptionModel implements Serializable
{
	private static final long serialVersionUID = 3209146281603407977L;
	
	private List<Long> id;
	private long questionid;
	private List<String> content;
	private List<String> pic;
	private Date createtime;
	private List<File> img;
	private List<String> imgFileName;
	private List<String> imgContentType;
	
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
	
	public List<Long> getId()
	{
		return id;
	}
	public void setId(List<Long> id)
	{
		this.id = id;
	}
	public long getQuestionid()
	{
		return questionid;
	}
	public void setQuestionid(long questionid)
	{
		this.questionid = questionid;
	}
	public List<String> getContent()
	{
		return content;
	}
	public void setContent(List<String> content)
	{
		this.content = content;
	}
	public List<String> getPic()
	{
		return pic;
	}
	public void setPic(List<String> pic)
	{
		this.pic = pic;
	}
	public Date getCreatetime()
	{
		return createtime;
	}
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}
	
	

}
