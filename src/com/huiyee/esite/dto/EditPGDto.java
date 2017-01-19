package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.Page;

import net.sf.json.JSONObject;
public class EditPGDto implements IDto{
	
	private JSONObject param;
	private JSONObject value;
	private List<Page> pages;
	
	public JSONObject getParam() {
		return param;
	}
	public void setParam(JSONObject param) {
		this.param = param;
	}
	public JSONObject getValue() {
		return value;
	}
	public void setValue(JSONObject value) {
		this.value = value;
	}
	public List<Page> getPages() {
		return pages;
	}
	public void setPages(List<Page> pages) {
		this.pages = pages;
	}
	
}
