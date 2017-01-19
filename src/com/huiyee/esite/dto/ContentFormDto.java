package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.ContentForm;
import com.huiyee.esite.model.ContentFormMapping;
import com.huiyee.esite.model.ContentFormRecord;
import com.huiyee.esite.model.OwnerContentType;

public class ContentFormDto implements IDto
{
	private List<OwnerContentType> typeList;
	private Pager pager;
	private String tree;
	private ContentForm contentform;
	private List<ContentFormRecord> record;
	private List<ContentFormMapping> colum;
	private ContentCategory current;

	public List<OwnerContentType> getTypeList()
	{
		return typeList;
	}

	public void setTypeList(List<OwnerContentType> typeList)
	{
		this.typeList = typeList;
	}

	public String getTree()
	{
		return tree;
	}

	public void setTree(String tree)
	{
		this.tree = tree;
	}


	public ContentCategory getCurrent()
	{
		return current;
	}

	public void setCurrent(ContentCategory current)
	{
		this.current = current;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	public ContentForm getContentform()
	{
		return contentform;
	}

	public void setContentform(ContentForm contentform)
	{
		this.contentform = contentform;
	}

	public List<ContentFormRecord> getRecord()
	{
		return record;
	}

	public void setRecord(List<ContentFormRecord> record)
	{
		this.record = record;
	}

	public List<ContentFormMapping> getColum()
	{
		return colum;
	}

	public void setColum(List<ContentFormMapping> colum)
	{
		this.colum = colum;
	}

}
