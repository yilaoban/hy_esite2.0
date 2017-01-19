
package com.huiyee.weixin.dto;

public class WkqRs
{

	private int status;//-1：店主不存在；-2：卡券不存在；-3：卡券已经使用；-4：不是店主的身份；-5：请不要篡改二维码地址！；1-验证成功；
	private String hydesc;
	private String url;

	public int getStatus()
	{
		return status;
	}

	public String getHydesc()
	{
		return hydesc;
	}

	public String getUrl()
	{
		return url;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public void setHydesc(String hydesc)
	{
		this.hydesc = hydesc;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}
}
