package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

public class CardBolck implements Serializable{
	
	private static final long serialVersionUID = 8583652985087417542L;
	private long id;
	private long cid;
	private long bid;
	private String json;
	private int position;
	private Date createtime;
	private long featureid;
	
	public long getFeatureid()
	{
		return featureid;
	}
	public void setFeatureid(long featureid)
	{
		this.featureid = featureid;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCid() {
		return cid;
	}
	public void setCid(long cid) {
		this.cid = cid;
	}
	public long getBid() {
		return bid;
	}
	public void setBid(long bid) {
		this.bid = bid;
	}

}
