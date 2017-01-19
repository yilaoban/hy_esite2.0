package com.huiyee.interact.exam.dto;
public class ExamQuestionDataDto{
	private long id;
	private long searchid;
	private String type;
	private String title;
	private String pic;
	private int idx;
	private ExamOptionDto optionDto;
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
	public ExamOptionDto getOptionDto()
	{
		return optionDto;
	}
	public void setOptionDto(ExamOptionDto optionDto)
	{
		this.optionDto = optionDto;
	}
	
}
