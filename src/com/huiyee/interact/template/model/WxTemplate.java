package com.huiyee.interact.template.model;

import java.util.Date;

/**
 * @author xn
 *
 */
public class WxTemplate {

	private long id;
	private long owner;

	// WDS-微电商购买成功,WDC-微电商交易完成
	// JFS-积分商城购买成功,JFC-积分商城交易完成
	// OCF-线下签到服务评价通知;OCT-线下签到到店提醒通知;XFJ-消费获得积分
	// YYU-预约(用户);YYD-预约(店主)
	// FWP-服务评价通知;FWR-店主回复通知
	// CZU-会员充值成功通知;CZD-充值通知商家;XFU-会员消费提醒;XFD-消费通知商家;SQD-申请管理员
	private String type;
	private long entityid;
	private String remark;

	private long store_id;
	private long mpid;
	private String template_id;
	private String data;
	private String url;
	private Integer delay;
	private String sendtime;
	private Date updatetime;

	private WxTemplateStore store;
	private boolean showRemark;
	private boolean showUrl;
	private boolean showSend;
	private int dtype;
	
	public int getDtype() {
		return dtype;
	}

	public void setDtype(int dtype) {
		this.dtype = dtype;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOwner() {
		return owner;
	}

	public void setOwner(long owner) {
		this.owner = owner;
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

	public long getStore_id() {
		return store_id;
	}

	public void setStore_id(long store_id) {
		this.store_id = store_id;
	}

	public long getMpid() {
		return mpid;
	}

	public void setMpid(long mpid) {
		this.mpid = mpid;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getDelay() {
		return delay;
	}

	public void setDelay(Integer delay) {
		this.delay = delay;
	}

	public String getSendtime() {
		return sendtime;
	}

	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public WxTemplateStore getStore() {
		return store;
	}

	public void setStore(WxTemplateStore store) {
		this.store = store;
	}

	public boolean isShowRemark() {
		return showRemark;
	}

	public void setShowRemark(boolean showRemark) {
		this.showRemark = showRemark;
	}

	public boolean isShowUrl() {
		return showUrl;
	}

	public void setShowUrl(boolean showUrl) {
		this.showUrl = showUrl;
	}

	public boolean isShowSend() {
		return showSend;
	}

	public void setShowSend(boolean showSend) {
		this.showSend = showSend;
	}

}