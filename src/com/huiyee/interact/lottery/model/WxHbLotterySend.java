package com.huiyee.interact.lottery.model;

import java.util.Date;

public class WxHbLotterySend {

	private long id;
	private long lid;
	private long lpid;
	private long lurid;
	private String openid;
	private long mpid;
	private String sent;
	private Date createtime;
	private Date senttime;
	private int errcode;
	private String errmsg;

	private String nickname;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getSent() {
		return sent;
	}

	public void setSent(String sent) {
		this.sent = sent;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getSenttime() {
		return senttime;
	}

	public void setSenttime(Date senttime) {
		this.senttime = senttime;
	}

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	
	public long getLid()
	{
		return lid;
	}

	
	public void setLid(long lid)
	{
		this.lid = lid;
	}

	
	public long getLpid()
	{
		return lpid;
	}

	
	public void setLpid(long lpid)
	{
		this.lpid = lpid;
	}

	
	public long getLurid()
	{
		return lurid;
	}

	
	public void setLurid(long lurid)
	{
		this.lurid = lurid;
	}

	
	public long getMpid()
	{
		return mpid;
	}

	
	public void setMpid(long mpid)
	{
		this.mpid = mpid;
	}

}
