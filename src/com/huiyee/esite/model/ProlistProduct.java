package com.huiyee.esite.model;

import java.io.Serializable;

public class ProlistProduct implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7564342442092836627L;
	private long id;
	private long prolistid;
	private long productid;
	private int zantotal;
	private String status;
	private ContentProduct product;
	private int idx;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProlistid() {
		return prolistid;
	}

	public void setProlistid(long prolistid) {
		this.prolistid = prolistid;
	}

	public long getProductid() {
		return productid;
	}

	public void setProductid(long productid) {
		this.productid = productid;
	}

	public int getZantotal() {
		return zantotal;
	}

	public void setZantotal(int zantotal) {
		this.zantotal = zantotal;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ContentProduct getProduct() {
		return product;
	}

	public void setProduct(ContentProduct product) {
		this.product = product;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}
}
