package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONObject;

public class TemplateBlock implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -484913584789157346L;
	private long id;
	private String name;
	private String json;//block 定义属性  例如 text1:S
	private Date createtime;
	private String type;
	private long relationid;
	private String ujson;//属性的值 例如 text1:123
	private String display;
	private long featureid;
	
	private String vjson;//组件默认值
	private String alone;//是否是组件
	private String img;
	private String desc;
	
	private long fid;//互动类型
	public long getId()
	{
		return id;
	}
	
	public long getFeatureid()
	{
		return featureid;
	}
	
	public void setFeatureid(long featureid)
	{
		this.featureid = featureid;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getJson()
	{
		return json;
	}
	public void setJson(String json)
	{
		this.json = json;
	}
	public Date getCreatetime()
	{
		return createtime;
	}
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public JSONObject getAttr(){
		if(!StringUtils.isEmpty(json)){
			return JSONObject.fromObject(json);
		}
		return null;
	}
	public JSONObject getValue(){
		if(!StringUtils.isEmpty(ujson)){
			return JSONObject.fromObject(ujson);
		}
		return null;
	}
	public String getUjson() {
		return ujson;
	}
	public void setUjson(String ujson) {
		this.ujson = ujson;
	}
	public long getRelationid() {
		return relationid;
	}
	public void setRelationid(long relationid) {
		this.relationid = relationid;
	}
	public long getFid() {
		return fid;
	}
	public void setFid(long fid) {
		this.fid = fid;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getVjson() {
		return vjson;
	}
	public void setVjson(String vjson) {
		this.vjson = vjson;
	}
	public String getAlone() {
		return alone;
	}
	public void setAlone(String alone) {
		this.alone = alone;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
