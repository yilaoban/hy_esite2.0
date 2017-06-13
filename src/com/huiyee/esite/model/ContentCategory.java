package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Comparator;

public class ContentCategory implements Comparable<ContentCategory>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5196607884702849145L;
	private long id;
	private long ownerid;
	private String name;
	private String desc;
	private String pic;
	private String type;
	private long fartherCatId;
	private int idx;
	private String use = "N";// 是否为当前目录
	private long typeid;
	private String content;
	private String bread;
	private String subtype="N";//若type为产品 subtype可以用来区分是微商城的还是微积分的
	private String password;
	
	
	public String getPassword()
	{
		return password;
	}


	
	public void setPassword(String password)
	{
		this.password = password;
	}


	public String getBread()
	{
		return bread;
	}

	
	public void setBread(String bread)
	{
		this.bread = bread;
	}

	
	public long getPageid()
	{
		return pageid;
	}

	
	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	private long pageid;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(long ownerid) {
		this.ownerid = ownerid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getFartherCatId() {
		return fartherCatId;
	}

	public void setFartherCatId(long fartherCatId) {
		this.fartherCatId = fartherCatId;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}

	public int hashCode() {
		return Integer.valueOf(id + "");
	}

	public boolean equals(Object obj) {
		ContentCategory c = (ContentCategory) obj;
		if (id == c.getId()) {
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(ContentCategory o) {
		long a=this.getId();
		long b=o.getId();
		if(a>b){
			return 1;
		}else if(a<b){
			return -1;
		}else{
			return 0;
		}
	}

	public long getTypeid()
	{
		return typeid;
	}

	public void setTypeid(long typeid)
	{
		this.typeid = typeid;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
