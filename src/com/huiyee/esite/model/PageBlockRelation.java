package com.huiyee.esite.model;

import java.io.Serializable;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

public class PageBlockRelation implements Serializable{

	private static final long serialVersionUID = 6188902680370582364L;
	private long id;
	private long pcid;
	private long cbid;
	private String json;
	private long cid;
	private long pfid;
	private long fid;
	private String display;
	private String alone;//是否是组建
	
	
	public String getAlone()
	{
		return alone;
	}
	
	public void setAlone(String alone)
	{
		this.alone = alone;
	}
	public long getFid()
	{
		return fid;
	}
	public void setFid(long fid)
	{
		this.fid = fid;
	}
	public long getPfid()
	{
		return pfid;
	}
	public void setPfid(long pfid)
	{
		this.pfid = pfid;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCbid() {
		return cbid;
	}
	public void setCbid(long cbid) {
		this.cbid = cbid;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public JSONObject getJsonObject(){
		if(!StringUtils.isEmpty(json)){
			return JSONObject.fromObject(json);
		}
		return null;
	}
	public long getCid() {
		return cid;
	}
	public void setCid(long cid) {
		this.cid = cid;
	}
	public long getPcid() {
		return pcid;
	}
	public void setPcid(long pcid) {
		this.pcid = pcid;
	}
	public String getDisplay()
	{
		return display;
	}
	public void setDisplay(String display)
	{
		this.display = display;
	}

	
}
