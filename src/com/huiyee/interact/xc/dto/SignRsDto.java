
package com.huiyee.interact.xc.dto;

public class SignRsDto
{

	private int status;// 1-成功；0-不是邀请人；-1-不是关注者
	private String url;//
	private String hydesc;//

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getHydesc()
	{
		return hydesc;
	}

	public void setHydesc(String hydesc)
	{
		this.hydesc = hydesc;
	}
}
