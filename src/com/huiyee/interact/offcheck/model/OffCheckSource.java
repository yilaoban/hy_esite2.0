
package com.huiyee.interact.offcheck.model;

import java.util.Date;

import com.huiyee.esite.model.Page;

public class OffCheckSource {

	private long id;
	private long owner;
	private long ocid;
	private long fpageid;
	private long spageid;
	private long apageid;
	private String fensi;
	private String name;
	private Date createtime;
	private long gzeid;
	private int type;
	private long dzpageid;
	private long dtpageid;
	private long jfpageid;
	private int rmbjf;

	private Page fpage;
	private Page spage;
	private Page apage;

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

	public long getOcid() {
		return ocid;
	}

	public void setOcid(long ocid) {
		this.ocid = ocid;
	}

	public long getFpageid() {
		return fpageid;
	}

	public void setFpageid(long fpageid) {
		this.fpageid = fpageid;
	}

	public long getSpageid() {
		return spageid;
	}

	public void setSpageid(long spageid) {
		this.spageid = spageid;
	}

	public String getFensi() {
		return fensi;
	}

	public void setFensi(String fensi) {
		this.fensi = fensi;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public long getGzeid() {
		return gzeid;
	}

	public void setGzeid(long gzeid) {
		this.gzeid = gzeid;
	}

	public Page getFpage() {
		return fpage;
	}

	public void setFpage(Page fpage) {
		this.fpage = fpage;
	}

	public Page getSpage() {
		return spage;
	}

	public void setSpage(Page spage) {
		this.spage = spage;
	}

	public long getApageid() {
		return apageid;
	}

	public void setApageid(long apageid) {
		this.apageid = apageid;
	}

	public Page getApage() {
		return apage;
	}

	public void setApage(Page apage) {
		this.apage = apage;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getDzpageid() {
		return dzpageid;
	}

	public void setDzpageid(long dzpageid) {
		this.dzpageid = dzpageid;
	}

	public long getDtpageid() {
		return dtpageid;
	}

	public void setDtpageid(long dtpageid) {
		this.dtpageid = dtpageid;
	}

	public long getJfpageid() {
		return jfpageid;
	}

	public void setJfpageid(long jfpageid) {
		this.jfpageid = jfpageid;
	}

	public int getRmbjf() {
		return rmbjf;
	}

	public void setRmbjf(int rmbjf) {
		this.rmbjf = rmbjf;
	}

}
