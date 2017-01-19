package com.huiyee.esite.model;

import java.io.Serializable;

public class Emotions implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2749734930733379030L;
	private long id;
	private long categoryId;
	private String iconurl;
	private String value;
	private String categoryName;
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIconurl() {
		return iconurl;
	}
	public void setIconurl(String iconurl) {
		this.iconurl = iconurl;
	}
	
}
