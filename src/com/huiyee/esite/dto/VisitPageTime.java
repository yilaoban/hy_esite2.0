
package com.huiyee.esite.dto;

public class VisitPageTime
{

	private long pageid;
	private long tid;
	private long cutid;
	private String cookie;

	public String getCookie()
	{
		return cookie;
	}
	
	public void setCookie(String cookie)
	{
		this.cookie = cookie;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public long getTid()
	{
		return tid;
	}

	public void setTid(long tid)
	{
		this.tid = tid;
	}

	public long getCutid()
	{
		return cutid;
	}

	public void setCutid(long cutid)
	{
		this.cutid = cutid;
	}

}
