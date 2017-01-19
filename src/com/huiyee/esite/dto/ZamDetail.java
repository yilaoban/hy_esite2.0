package com.huiyee.esite.dto;

import java.util.Date;

public class ZamDetail {
	private String nickname;
	private Date createtime;
	public String getNickname()
	{
		return nickname;
	}
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}
	public Date getCreatetime()
	{
		return createtime;
	}
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}
}
