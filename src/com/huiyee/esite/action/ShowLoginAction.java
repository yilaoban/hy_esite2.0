package com.huiyee.esite.action;

import com.opensymphony.xwork2.Action;

public class ShowLoginAction extends AbstractAction
{
	/**
	 * 
	 */
	private long appid = 1;// 申请的app在本地数据库的id
	private static final long serialVersionUID = 1L;
	//以下是暂时用不到的属性
	private long cid;
	private String url;
	private String key;
	private long sub_appkey;
	private String viewer;
	private String tokenString;
	private String ifmID;
	private String iniframe;
	


	@Override
	public String execute() throws Exception
	{
		
		return Action.SUCCESS;
	}

	public long getCid()
	{
		return cid;
	}

	public void setAppid(long appid)
	{
		this.appid = appid;
	}

	public void setCid(long cid)
	{
		this.cid = cid;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	public void setSub_appkey(long sub_appkey)
	{
		this.sub_appkey = sub_appkey;
	}

	public void setViewer(String viewer)
	{
		this.viewer = viewer;
	}

	public void setTokenString(String tokenString)
	{
		this.tokenString = tokenString;
	}

	public void setIfmID(String ifmID)
	{
		this.ifmID = ifmID;
	}

	public void setIniframe(String iniframe)
	{
		this.iniframe = iniframe;
	}
}
