package com.huiyee.interact.template.model;

import java.util.Date;

import com.huiyee.esite.model.WxUser;

public class WxTemplateJob {

	private long id;
	private long mpid;
	private String type;
	private long entityid;
	private String remark;
	private long msgid;
	private String touser;
	private long wxuid;
	private long hyuid;
	private String template_id;
	private String url;
	private String data;
	private Date createtime;
	private int errcode;
	private String errmsg;
	private String sent;
	private Date sendtime;
	private String opened;
	private Date opentime;

	private WxUser user;

	
	public long getWxuid()
	{
		return wxuid;
	}

	
	public long getHyuid()
	{
		return hyuid;
	}

	
	public void setWxuid(long wxuid)
	{
		this.wxuid = wxuid;
	}

	
	public void setHyuid(long hyuid)
	{
		this.hyuid = hyuid;
	}

	public long getMpid() {
		return mpid;
	}

	public void setMpid(long mpid) {
		this.mpid = mpid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getEntityid() {
		return entityid;
	}

	public void setEntityid(long entityid) {
		this.entityid = entityid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
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

	public String getSent() {
		return sent;
	}

	public void setSent(String sent) {
		this.sent = sent;
	}

	public Date getSendtime() {
		return sendtime;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}

	public String getOpened() {
		return opened;
	}

	public void setOpened(String opened) {
		this.opened = opened;
	}

	public Date getOpentime() {
		return opentime;
	}

	public void setOpentime(Date opentime) {
		this.opentime = opentime;
	}

	public long getMsgid() {
		return msgid;
	}

	public void setMsgid(long msgid) {
		this.msgid = msgid;
	}

	public WxUser getUser() {
		return user;
	}

	public void setUser(WxUser user) {
		this.user = user;
	}

}
