package com.huiyee.esite.model;

import java.io.Serializable;

public class EmotionsCategory implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3584837068276642904L;
	private long id;
	private String name;
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

}
