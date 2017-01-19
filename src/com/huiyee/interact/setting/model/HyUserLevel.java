package com.huiyee.interact.setting.model;

public class HyUserLevel {
	private long id;
	private String name;
	private String img;
	private String hydesc;
	private long owner;
	private int dannum;
	private int totalnum;
	private int usednum;
	
	private HyUserXfZk hyUserXfZk;
	
	public HyUserXfZk getHyUserXfZk() {
		return hyUserXfZk;
	}
	public void setHyUserXfZk(HyUserXfZk hyUserXfZk) {
		this.hyUserXfZk = hyUserXfZk;
	}
	public int getUsednum() {
		return usednum;
	}
	public void setUsednum(int usednum) {
		this.usednum = usednum;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getHydesc() {
		return hydesc;
	}
	public void setHydesc(String hydesc) {
		this.hydesc = hydesc;
	}
	public long getOwner() {
		return owner;
	}
	public void setOwner(long owner) {
		this.owner = owner;
	}
	public int getDannum() {
		return dannum;
	}
	public void setDannum(int dannum) {
		this.dannum = dannum;
	}
	public int getTotalnum() {
		return totalnum;
	}
	public void setTotalnum(int totalnum) {
		this.totalnum = totalnum;
	}
	
	
}
