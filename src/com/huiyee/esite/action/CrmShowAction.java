package com.huiyee.esite.action;

import com.huiyee.esite.mgr.ICrmMgr;
import com.huiyee.weixin.model.WxMediaNews;

public class CrmShowAction extends AbstractAction {
	/**
	 * 用来跳转微信关键字触发的链接
	 */
	private static final long serialVersionUID = 1L;
	private ICrmMgr crmMgr;

	private long id;
	private String accout;
	private String openid;// filter 里面已经处理了
	private int type;// 0-url,1-new

	private long mpid;
	private WxMediaNews news;
	private String refurl;

	@Override
	public String execute() throws Exception {
		if (type == 0) {
			refurl = crmMgr.findCrmKey(id);
			return "su2";
		} else {
			mpid = crmMgr.findMpidByAccout(accout);
			news = crmMgr.findNews(id);
			return "su1";
		}
	}

	public void setCrmMgr(ICrmMgr crmMgr) {
		this.crmMgr = crmMgr;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccout() {
		return accout;
	}

	public void setAccout(String accout) {
		this.accout = accout;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getMpid() {
		return mpid;
	}

	public void setMpid(long mpid) {
		this.mpid = mpid;
	}

	public WxMediaNews getNews() {
		return news;
	}

	public void setNews(WxMediaNews news) {
		this.news = news;
	}

	public String getRefurl() {
		return refurl;
	}

	public void setRefurl(String refurl) {
		this.refurl = refurl;
	}

}
