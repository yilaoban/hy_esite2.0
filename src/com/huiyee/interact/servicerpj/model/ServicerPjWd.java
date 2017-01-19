package com.huiyee.interact.servicerpj.model;

public class ServicerPjWd {

	private long id;
	private long owner;
	private String name;
	private String del_tag;

	private int level;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDel_tag() {
		return del_tag;
	}

	public void setDel_tag(String del_tag) {
		this.del_tag = del_tag;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
