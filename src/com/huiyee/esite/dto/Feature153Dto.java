package com.huiyee.esite.dto;

import java.io.Serializable;
import java.util.List;

import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.Redirect;
import com.huiyee.interact.exam.model.ExamModel;

public class Feature153Dto implements IDto , Serializable {

	private static final long serialVersionUID = -8758456039162812799L;
	private List<ExamModel> examList;
	private long examid;
	private long fid;
	
	private long relationid;
	private Redirect redirect;
	private String type;
	private String url;
	private String urlShow;
	private String urlPre;
	private List<Page> pageList;
	
	private long pageid;
	private long toPageid;
	
	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public String getUrlPre()
	{
		return urlPre;
	}

	public void setUrlPre(String urlPre)
	{
		this.urlPre = urlPre;
	}

	public String getUrlShow()
	{
		return urlShow;
	}

	public void setUrlShow(String urlShow)
	{
		this.urlShow = urlShow;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public long getRelationid()
	{
		return relationid;
	}

	public void setRelationid(long relationid)
	{
		this.relationid = relationid;
	}

	public long getFid()
	{
		return fid;
	}

	public void setFid(long fid)
	{
		this.fid = fid;
	}

	public long getExamid()
	{
		return examid;
	}

	public void setExamid(long examid)
	{
		this.examid = examid;
	}

	public List<ExamModel> getExamList()
	{
		return examList;
	}

	public void setExamList(List<ExamModel> examList)
	{
		this.examList = examList;
	}

	public Redirect getRedirect()
	{
		return redirect;
	}

	public void setRedirect(Redirect redirect)
	{
		this.redirect = redirect;
	}

	
	public List<Page> getPageList()
	{
		return pageList;
	}

	
	public void setPageList(List<Page> pageList)
	{
		this.pageList = pageList;
	}

	
	public long getToPageid()
	{
		return toPageid;
	}

	
	public void setToPageid(long toPageid)
	{
		this.toPageid = toPageid;
	}

	
}
