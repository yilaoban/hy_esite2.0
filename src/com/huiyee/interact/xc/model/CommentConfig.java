package com.huiyee.interact.xc.model;

import java.io.Serializable;

public class CommentConfig implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3507332163630957433L;
	private String note;
	private String atype = "N";// 是否限定评论人 Q:限定仅签到者可评论 N:不限定
	private int maxnum;//每人最多评论的次数
	private String checked = "N";// 是否审核 Y:是 N:否

	public String getChecked()
	{
		return checked;
	}

	public void setChecked(String checked)
	{
		this.checked = checked;
	}

	public String getAtype()
	{
		return atype;
	}

	public void setAtype(String atype)
	{
		this.atype = atype;
	}

	public String getNote()
	{
		return note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}

	public int getMaxnum()
	{
		return maxnum;
	}

	public void setMaxnum(int maxnum)
	{
		this.maxnum = maxnum;
	}
}
