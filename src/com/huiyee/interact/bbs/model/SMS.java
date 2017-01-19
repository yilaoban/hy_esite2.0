package com.huiyee.interact.bbs.model;

import java.io.Serializable;

public class SMS implements Serializable{
	private static final long serialVersionUID = -4575264014745151216L;
	private long id;
	private long owner;
	private String username;
	private String password;
	private long productid;
	private String hydesc;
	
	public String getHydesc()
	{
		return hydesc;
	}
	
	public void setHydesc(String hydesc)
	{
		this.hydesc = hydesc;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getProductid() {
		return productid;
	}
	public void setProductid(long productid) {
		this.productid = productid;
	}
	
	
}
