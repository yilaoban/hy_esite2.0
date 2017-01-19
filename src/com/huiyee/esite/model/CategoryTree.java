package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Map;

public class CategoryTree implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7475260740989799575L;

	private long id;
	private long pId;
	private String name;
	private boolean open;
	private Map<String, String> font;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPId() {
		return pId;
	}

	public void setPId(long id) {
		pId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public Map<String, String> getFont()
	{
		return font;
	}

	public void setFont(Map<String, String> font)
	{
		this.font = font;
	}
}
