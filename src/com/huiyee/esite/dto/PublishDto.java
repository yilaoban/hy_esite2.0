package com.huiyee.esite.dto;

import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.SinaApp;
import com.huiyee.esite.model.WeixinApp;

import java.util.List;

public class PublishDto implements IDto {
	private List<Page> plist;
	private List<SinaApp> applist;
	private List<WeixinApp> wxlist;
	
	public List<Page> getPlist()
	{
		return plist;
	}
	public void setPlist(List<Page> plist)
	{
		this.plist = plist;
	}
	public List<SinaApp> getApplist()
	{
		return applist;
	}
	public void setApplist(List<SinaApp> applist)
	{
		this.applist = applist;
	}
	public List<WeixinApp> getWxlist() {
		return wxlist;
	}
	public void setWxlist(List<WeixinApp> wxlist) {
		this.wxlist = wxlist;
	}
} 
