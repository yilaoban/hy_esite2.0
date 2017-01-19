package com.huiyee.interact.template.model;

public class WxTemplateIndustry implements Cloneable {

	private long id;
	private String first_class;
	private boolean first_selected;
	private String second_class;
	private boolean second_selected;

	@Override
	public WxTemplateIndustry clone() {
		try {
			return (WxTemplateIndustry) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirst_class() {
		return first_class;
	}

	public void setFirst_class(String first_class) {
		this.first_class = first_class;
	}

	public boolean isFirst_selected() {
		return first_selected;
	}

	public void setFirst_selected(boolean first_selected) {
		this.first_selected = first_selected;
	}

	public String getSecond_class() {
		return second_class;
	}

	public void setSecond_class(String second_class) {
		this.second_class = second_class;
	}

	public boolean isSecond_selected() {
		return second_selected;
	}

	public void setSecond_selected(boolean second_selected) {
		this.second_selected = second_selected;
	}

}
