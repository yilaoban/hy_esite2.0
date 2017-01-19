
package com.huiyee.interact.servicerpj.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.huiyee.esite.model.WxUser;
import com.huiyee.interact.yuyue.model.YuYueServicer;

public class ServicerPj implements Serializable {
	private static final long serialVersionUID = 6429434562322025164L;

	private long id;
	private long serid;
	private long wxuid;
	private String content;
	private String dzcontent;
	private int level;
	private Date createtime;
	private int type;
	private long sourceid;
	private long enid;
	private List<ServicerPjWd> wdList;

	private WxUser wxUser;
	private YuYueServicer yuYueServicer;
	private long owner;
	private String oname;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getWxuid() {
		return wxuid;
	}

	public void setWxuid(long wxuid) {
		this.wxuid = wxuid;
	}

	public long getSerid() {
		return serid;
	}

	public void setSerid(long serid) {
		this.serid = serid;
	}

	public WxUser getWxUser() {
		return wxUser;
	}

	public void setWxUser(WxUser wxUser) {
		this.wxUser = wxUser;
	}

	public YuYueServicer getYuYueServicer() {
		return yuYueServicer;
	}

	public void setYuYueServicer(YuYueServicer yuYueServicer) {
		this.yuYueServicer = yuYueServicer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDzcontent() {
		return dzcontent;
	}

	public void setDzcontent(String dzcontent) {
		this.dzcontent = dzcontent;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getEnid() {
		return enid;
	}

	public void setEnid(long enid) {
		this.enid = enid;
	}

	public long getOwner() {
		return owner;
	}

	public void setOwner(long owner) {
		this.owner = owner;
	}

	public String getOname() {
		return oname;
	}

	public void setOname(String oname) {
		this.oname = oname;
	}

	public long getSourceid() {
		return sourceid;
	}

	public void setSourceid(long sourceid) {
		this.sourceid = sourceid;
	}

	public List<ServicerPjWd> getWdList() {
		return wdList;
	}

	public void setWdList(List<ServicerPjWd> wdList) {
		this.wdList = wdList;
	}

}
