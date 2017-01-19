package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.List;

public class Module implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5003161189237435233L;
	private long id;
	private String name;
	private String type;
	private String isown;//ÊÇ·ñ¶¨ÖÆ
	private List<Feature> features;
	
	private int start;
	private int end;
	private long pageid;
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
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
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public List<Feature> getFeatures() {
		return features;
	}
	public void setFeatures(List<Feature> features) {
		this.features = features;
	}
	public String getIsown() {
		return isown;
	}
	public void setIsown(String isown) {
		this.isown = isown;
	}
	
	public long getPageid()
	{
		return pageid;
	}
	
	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}
	
}
