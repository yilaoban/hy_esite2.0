package com.huiyee.esite.dto;

import java.util.List;
import com.huiyee.esite.model.PageAddress;
import com.huiyee.weixin.model.WxMp;

public class PageAddressDto implements IDto {

	private WxMp wxMp;
	
	public WxMp getWxMp()
	{
		return wxMp;
	}

	
	public void setWxMp(WxMp wxMp)
	{
		this.wxMp = wxMp;
	}

	private List<PageAddress> list;

	public List<PageAddress> getList()
	{
		return list;
	}

	public void setList(List<PageAddress> list)
	{
		this.list = list;
	}
	
}
