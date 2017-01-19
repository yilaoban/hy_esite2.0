package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

public class HdRecord implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5578016368995212787L;
	private long id;
	private long pageid;
	private long wbuid;
	private long action;
	private long entityid;
	private String source;
	private String terminal;
	private String ip;
	private Date createtime;
	private long sitegroupid;
	private long siteid;
	private long cid;//site°ó¶¨¹ÙÎ»µÄÎ¢²©id
	private int fansnum;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPageid() {
		return pageid;
	}
	public void setPageid(long pageid) {
		this.pageid = pageid;
	}
	public long getWbuid() {
		return wbuid;
	}
	public void setWbuid(long wbuid) {
		this.wbuid = wbuid;
	}
	public long getAction() {
		return action;
	}
	public void setAction(long action) {
		this.action = action;
	}
	public long getEntityid() {
		return entityid;
	}
	public void setEntityid(long entityid) {
		this.entityid = entityid;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public long getCid() {
		return cid;
	}
	public void setCid(long cid) {
		this.cid = cid;
	}
    public int getFansnum() {
        return fansnum;
    }
    public void setFansnum(int fansnum) {
        this.fansnum = fansnum;
    }
    public long getSitegroupid() {
        return sitegroupid;
    }
    public void setSitegroupid(long sitegroupid) {
        this.sitegroupid = sitegroupid;
    }
    public long getSiteid() {
        return siteid;
    }
    public void setSiteid(long siteid) {
        this.siteid = siteid;
    }

}
