package com.huiyee.tfmodel.param;

import java.util.Date;

public class TfPageJoinDto
{
private long owner;
private String src;//s:新浪;q:微信
private Date createtime;//日志创建时间
private String ip;
private String type;//v:visit;j:join
private String nm;//y:是匿名;n:非匿名
private long siteid;
private long pageid;
private long ent;//实体的id
private long act;//功能的id
private String area;//互动的区域
private String term;//终端
private String agent;//

private String wbuid;
private String wnm;//微博nickname
private int wfans;
private String wsex;//1-男;2-女;0-未知

private String qc ;//(country)
private String qa ;//(appid)
private String qo ;//(openid)
private String qh ;//(headimg)
private String qp ;//(privilge)
public long getOwner()
{
	return owner;
}
public void setOwner(long owner)
{
	this.owner = owner;
}
public String getSrc()
{
	return src;
}
public void setSrc(String src)
{
	this.src = src;
}
public Date getCreatetime()
{
	return createtime;
}
public void setCreatetime(Date createtime)
{
	this.createtime = createtime;
}
public String getIp()
{
	return ip;
}
public void setIp(String ip)
{
	this.ip = ip;
}
public String getType()
{
	return type;
}
public void setType(String type)
{
	this.type = type;
}
public String getNm()
{
	return nm;
}
public void setNm(String nm)
{
	this.nm = nm;
}
public long getSiteid()
{
	return siteid;
}
public void setSiteid(long siteid)
{
	this.siteid = siteid;
}
public long getPageid()
{
	return pageid;
}
public void setPageid(long pageid)
{
	this.pageid = pageid;
}
public long getEnt()
{
	return ent;
}
public void setEnt(long ent)
{
	this.ent = ent;
}
public long getAct()
{
	return act;
}
public void setAct(long act)
{
	this.act = act;
}
public String getArea()
{
	return area;
}
public void setArea(String area)
{
	this.area = area;
}

public String getWnm()
{
	return wnm;
}
public void setWnm(String wnm)
{
	this.wnm = wnm;
}
public int getWfans()
{
	return wfans;
}
public void setWfans(int wfans)
{
	this.wfans = wfans;
}

public String getWbuid()
{
	return wbuid;
}
public void setWbuid(String wbuid)
{
	this.wbuid = wbuid;
}
public String getWsex()
{
	return wsex;
}
public void setWsex(String wsex)
{
	this.wsex = wsex;
}
public String getQc()
{
	return qc;
}
public void setQc(String qc)
{
	this.qc = qc;
}
public String getQa()
{
	return qa;
}
public void setQa(String qa)
{
	this.qa = qa;
}
public String getQo()
{
	return qo;
}
public void setQo(String qo)
{
	this.qo = qo;
}
public String getQh()
{
	return qh;
}
public void setQh(String qh)
{
	this.qh = qh;
}
public String getQp()
{
	return qp;
}
public void setQp(String qp)
{
	this.qp = qp;
}
public String getTerm()
{
	return term;
}
public void setTerm(String term)
{
	this.term = term;
}
public String getAgent()
{
	return agent;
}
public void setAgent(String agent)
{
	this.agent = agent;
}
}
