package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TemplateCard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1341861961663624610L;

	private long id;
	private String name;
	private Date createtime;
	private String path;
	private String css;
	private String js;
	private long category;
	private String type;
	private String bimg;
	private String simg;
	private String url;
	private String desc;
	private List<TemplateBlock> blocks;
	private List<PageBlockRelation> relations;

	private long pageid;
	private int position;
	private String bg;
	private String subtype;
	
	public long getPageid() {
		return pageid;
	}
	public void setPageid(long pageid) {
		this.pageid = pageid;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
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
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getCss() {
		return css;
	}
	public void setCss(String css) {
		this.css = css;
	}
	public String getJs() {
		return js;
	}
	public void setJs(String js) {
		this.js = js;
	}
	public long getCategory() {
		return category;
	}
	public void setCategory(long category) {
		this.category = category;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBimg() {
		return bimg;
	}
	public void setBimg(String bimg) {
		this.bimg = bimg;
	}
	public String getSimg() {
		return simg;
	}
	public void setSimg(String simg) {
		this.simg = simg;
	}
	public List<TemplateBlock> getBlocks() {
		return blocks;
	}
	public void setBlocks(List<TemplateBlock> blocks) {
		this.blocks = blocks;
	}
	public List<PageBlockRelation> getRelations() {
		return relations;
	}
	public void setRelations(List<PageBlockRelation> relations) {
		this.relations = relations;
	}
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	public String getDesc()
	{
		return desc;
	}
	public void setDesc(String desc)
	{
		this.desc = desc;
	}
	public String getBg() {
		return bg;
	}
	public void setBg(String bg) {
		this.bg = bg;
	}
	public String getSubtype()
	{
		return subtype;
	}
	public void setSubtype(String subtype)
	{
		this.subtype = subtype;
	}

}
