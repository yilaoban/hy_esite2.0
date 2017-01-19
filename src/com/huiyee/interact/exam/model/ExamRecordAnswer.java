package com.huiyee.interact.exam.model;

import java.io.Serializable;
import java.util.Date;

public class ExamRecordAnswer implements Serializable{
	private static final long serialVersionUID = -6750609432880284902L;
	private long id;
	private long recordid;
	private long questionid;
	private long optionid;
	private String answer;
	private String percent;
	private Date createtime;
	private int idx;
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public long getRecordid()
	{
		return recordid;
	}
	public void setRecordid(long recordid)
	{
		this.recordid = recordid;
	}
	public long getQuestionid()
	{
		return questionid;
	}
	public void setQuestionid(long questionid)
	{
		this.questionid = questionid;
	}
	public long getOptionid()
	{
		return optionid;
	}
	public void setOptionid(long optionid)
	{
		this.optionid = optionid;
	}
	public String getAnswer()
	{
		return answer;
	}
	public void setAnswer(String answer)
	{
		this.answer = answer;
	}
	public Date getCreatetime()
	{
		return createtime;
	}
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}
	public int getIdx()
	{
		return idx;
	}
	public void setIdx(int idx)
	{
		this.idx = idx;
	}
	public String getPercent()
	{
		return percent;
	}
	public void setPercent(String percent)
	{
		this.percent = percent;
	}
	
	
	
}
