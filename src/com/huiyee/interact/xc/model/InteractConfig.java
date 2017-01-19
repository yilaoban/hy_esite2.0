
package com.huiyee.interact.xc.model;

import java.io.Serializable;

public class InteractConfig implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 88555223496207420L;
	private String atype = "Q";// 是否限定签到人 Q:限定仅签到者可评论 C:评论人可参与互动 N:不限定

	public InteractConfig()
	{
		this.atype = "Q";
	}

	public InteractConfig(String str)
	{
		this.atype = str;
	}

	public String getAtype()
	{
		return atype;
	}

	public void setAtype(String atype)
	{
		this.atype = atype;
	}

}
