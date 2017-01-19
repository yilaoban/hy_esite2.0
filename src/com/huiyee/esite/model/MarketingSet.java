package com.huiyee.esite.model;

import java.io.Serializable;

public class MarketingSet implements Serializable {
	private static final long serialVersionUID = 4194739526496325632L;

	private long id;
	private long owner;
	private long dzpage;
	private long homepage;
	private long userpage;
	private long yzpage;
	private long spage;
	private long fpage;
	private long lpage;//商城列表页
	private int bili;

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

	public long getDzpage() {
		return dzpage;
	}

	public void setDzpage(long dzpage) {
		this.dzpage = dzpage;
	}

	public long getHomepage() {
		return homepage;
	}

	public void setHomepage(long homepage) {
		this.homepage = homepage;
	}

	public long getUserpage() {
		return userpage;
	}

	public void setUserpage(long userpage) {
		this.userpage = userpage;
	}

	public long getYzpage() {
		return yzpage;
	}

	public void setYzpage(long yzpage) {
		this.yzpage = yzpage;
	}

	public long getSpage() {
		return spage;
	}

	public void setSpage(long spage) {
		this.spage = spage;
	}

	public long getFpage() {
		return fpage;
	}

	public void setFpage(long fpage) {
		this.fpage = fpage;
	}

	
	public long getLpage()
	{
		return lpage;
	}

	
	public void setLpage(long lpage)
	{
		this.lpage = lpage;
	}

	public String getPages() {
		return dzpage + "-" + homepage + "-" + userpage + "-" + yzpage + "-" + spage + "-" + fpage + "-" + lpage;
	}

	
	public int getBili()
	{
		return bili;
	}

	
	public void setBili(int bili)
	{
		this.bili = bili;
	}

}
