package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.Mb;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.Tag;


public class MbDto implements IDto
{
	private long tagid;
	private List<Mb> mbList;
	private List<Tag> tagList;
	private int total;
	
	public List<Mb> getMbList()
	{
		return mbList;
	}
	
	public void setMbList(List<Mb> mbList)
	{
		this.mbList = mbList;
	}

	
	public List<Tag> getTagList()
	{
		return tagList;
	}

	
	public void setTagList(List<Tag> tagList)
	{
		this.tagList = tagList;
	}

	
	public long getTagid()
	{
		return tagid;
	}

	
	public void setTagid(long tagid)
	{
		this.tagid = tagid;
	}

	
	public int getTotal()
	{
		return total;
	}

	
	public void setTotal(int total)
	{
		this.total = total;
	}
	
	
}
