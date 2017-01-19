
package com.huiyee.weixin.dto;

public class WkqGetRs
{

	private int status;//-1：没有配置验证的页面；-2：订单不存在；-3：卡券还没有支付成功；1：获取成功
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
