
package com.huiyee.esite.model;

import java.io.Serializable;

public class ContentTab implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2443420543497524089L;
	private String id;
	private long pid;
	private String title;
	private String content;

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public long getPid()
	{
		return pid;
	}

	public void setPid(long pid)
	{
		this.pid = pid;
	}

}
