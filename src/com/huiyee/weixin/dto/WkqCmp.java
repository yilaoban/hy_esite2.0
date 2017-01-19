
package com.huiyee.weixin.dto;

import java.util.Date;

public class WkqCmp
{

	private String nickname;
	private Date time;
	private String openid;

	public String getNickname()
	{
		return nickname;
	}

	public Date getTime()
	{
		return time;
	}

	public String getOpenid()
	{
		return openid;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public void setTime(Date time)
	{
		this.time = time;
	}

	public void setOpenid(String openid)
	{
		this.openid = openid;
	}
}
