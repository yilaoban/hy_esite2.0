package com.huiyee.interact.research.model;

import java.io.Serializable;
import java.util.Date;

public class ResearchQuestionOptionVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4923634148065208113L;
	private long id;
	private long questionid;
	private String content;
	private String pic;
	private int idx;
	private int count;
	private Date createtime;
	private String status;
	private String percent;
	private long target;
	private String title;
	public long getId()
	{
		return id;
	}
	public void setId(long id)
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
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
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
	public int getCount()
	{
		return count;
	}
	public void setCount(int count)
	{
		this.count = count;
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
	public String getPercent()
	{
		return percent;
	}
	public void setPercent(String percent)
	{
		this.percent = percent;
	}
	public long getTarget()
	{
		return target;
	}
	public void setTarget(long target)
	{
		this.target = target;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	

}
