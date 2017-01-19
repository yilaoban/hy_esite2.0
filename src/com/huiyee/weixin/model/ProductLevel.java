package com.huiyee.weixin.model;

import java.math.BigDecimal;

public class ProductLevel {
	
	private long id;
	private long productid;
	private long levelid;
	private int price;
	private String type;
	private int discount;
	private String name; //会员等级名称
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getProductid() {
		return productid;
	}
	public void setProductid(long productid) {
		this.productid = productid;
	}
	public long getLevelid() {
		return levelid;
	}
	public void setLevelid(long levelid) {
		this.levelid = levelid;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
}
