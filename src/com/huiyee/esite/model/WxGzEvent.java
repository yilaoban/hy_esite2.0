
package com.huiyee.esite.model;

import java.io.Serializable;

import com.huiyee.interact.xc.model.KeywordMsg;

public class WxGzEvent implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1093056712590182058L;
	private long id;
	private String link;
	private KeywordMsg msg;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public KeywordMsg getMsg()
	{
		return msg;
	}

	public void setMsg(KeywordMsg msg)
	{
		this.msg = msg;
	}

	public String getLink()
	{
		return link;
	}

	public void setLink(String link)
	{
		this.link = link;
	}
}
