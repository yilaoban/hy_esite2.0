package com.huiyee.interact.research.model;

import java.io.Serializable;

public class ResearchVO implements Serializable{
	
	private static final long serialVersionUID = -6789753548035456542L;
	private long id;
	private long ownerid;
	private String title;
	private String answer;
	private int count;
	private int idx;
	private String status;
	private long questionid;
	private String content;
	private long searchid;
	private String type;
	
	
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public long getOwnerid()
	{
		return ownerid;
	}
	public void setOwnerid(long ownerid)
	{
		this.ownerid = ownerid;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getAnswer()
	{
		return answer;
	}
	public void setAnswer(String answer)
	{
		this.answer = answer;
	}
	public int getCount()
	{
		return count;
	}
	public void setCount(int count)
	{
		this.count = count;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
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
	public int getIdx()
	{
		return idx;
	}
	public void setIdx(int idx)
	{
		this.idx = idx;
	}
	

	

}
