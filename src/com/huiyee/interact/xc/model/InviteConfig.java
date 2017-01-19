package com.huiyee.interact.xc.model;

import java.io.Serializable;

public class InviteConfig implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8032100560379291474L;
	private String note;// 邀请成功提示语
	private String failNote;// 非邀请成功提示语
	private String atype = "N";// 是否限定邀请人 S:限定 N:不限定

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

	public String getFailNote()
	{
		return failNote;
	}

	public void setFailNote(String failNote)
	{
		this.failNote = failNote;
	}

}
