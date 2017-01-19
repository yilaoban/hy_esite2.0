package com.huiyee.tfmodel.param;

public class TfCommentDto implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String wbid;
	private String token;
	private String sendtime;// yyyy-MM-dd HH:mm:ss
	private String content;
	private String typekey;
	public String getWbid()
	{
		return wbid;
	}
	public void setWbid(String wbid)
	{
		this.wbid = wbid;
	}
	public String getToken()
	{
		return token;
	}
	public void setToken(String token)
	{
		this.token = token;
	}
	public String getSendtime()
	{
		return sendtime;
	}
	public void setSendtime(String sendtime)
	{
		this.sendtime = sendtime;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getTypekey()
	{
		return typekey;
	}
	public void setTypekey(String typekey)
	{
		this.typekey = typekey;
	}
}
