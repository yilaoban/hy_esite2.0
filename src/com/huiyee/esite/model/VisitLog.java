package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

public class VisitLog implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 0L;
	private long id;
	private String uid;
	private String nickname;
	private long siteid;
	private String createtime;
	private String ip;
	private String userAgent;
	private String terminal;
	private String source;
	private Date createTime;
	private long wbuid;
	private long cid;//授权应用的微博id
	private long countSum;
	private long sitegroupid;
	private String hour;
	private String gender;
	private int fansnum;
	
	public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public long getSitegroupid() {
        return sitegroupid;
    }

    public void setSitegroupid(long sitegroupid) {
        this.sitegroupid = sitegroupid;
    }

    public long getCountSum() {
		return countSum;
	}

	public void setCountSum(long countSum) {
		this.countSum = countSum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getUid()
	{
		return uid;
	}

	public void setUid(String uid)
	{
		this.uid = uid;
	}

	public long getSiteid()
	{
		return siteid;
	}

	public void setSiteid(long siteid)
	{
		this.siteid = siteid;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
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

	public String getUserAgent()
	{
		return userAgent;
	}

	public void setUserAgent(String userAgent)
	{
		this.userAgent = userAgent;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public long getWbuid() {
		return wbuid;
	}

	public void setWbuid(long wbuid) {
		this.wbuid = wbuid;
	}

	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getFansnum() {
        return fansnum;
    }

    public void setFansnum(int fansnum) {
        this.fansnum = fansnum;
    }

	
}
