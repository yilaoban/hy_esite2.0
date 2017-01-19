package com.huiyee.esite.model;

import java.util.Date;

public class HD154Model {

	private long id;
	private long pageid;
	private String ids;
	private String type;
	private Date createtime;
	private long topage;
	
	public String getIds()
	{
		return ids;
	}
	
	public void setIds(String ids)
	{
		this.ids = ids;
	}

	public long getTopage()
	{
		return topage;
	}
	
	public void setTopage(long topage)
	{
		this.topage = topage;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPageid() {
		return pageid;
	}
	public void setPageid(long pageid) {
		this.pageid = pageid;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	
}
