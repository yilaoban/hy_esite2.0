package com.huiyee.esite.dto;

import net.sf.json.JSONObject;

public class WxOaRs
{
private JSONObject object;
private String openid;
private String scope;
public JSONObject getObject()
{
	return object;
}
public void setObject(JSONObject object)
{
	this.object = object;
}
public String getOpenid()
{
	return openid;
}
public void setOpenid(String openid)
{
	this.openid = openid;
}
public String getScope()
{
	return scope;
}
public void setScope(String scope)
{
	this.scope = scope;
}
}
