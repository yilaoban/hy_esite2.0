package com.huiyee.interact.vote.model;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

public class VoteOption implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7419223652167939953L;
	private long id;
	private long voteid;
	private String content;//选项标题
	private String description;//选项描述
	private String pic;
	private String img;
	private String vediourl;
	private int count;
	private Date createtime;
	private String percent;
	private int idx;
	private String tags;
	private int userCount;//当前用户的对此选项的投票次数
	private String linked;
	private String linkurl;
    
	public String getLinked()
	{
		return linked;
	}

	
	public void setLinked(String linked)
	{
		this.linked = linked;
	}

	
	public String getLinkurl()
	{
		return linkurl;
	}

	
	public void setLinkurl(String linkurl)
	{
		this.linkurl = linkurl;
	}

	public JSONArray getTagsJson(){
    	if(tags != null){
    		return JSONArray.fromObject(tags);
    	}else{
    		return new JSONArray();
    	}
    }
	
	public String getTags()
	{
		return tags;
	}

	
	public void setTags(String tags)
	{
		this.tags = tags;
	}

	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getVediourl()
	{
		return vediourl;
	}
	public void setVediourl(String vediourl)
	{
		this.vediourl = vediourl;
	}
	public String getPercent() {
        return percent;
    }
    public void setPercent(String percent) {
        this.percent = percent;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getVoteid() {
		return voteid;
	}
	public void setVoteid(long voteid) {
		this.voteid = voteid;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}

	
	public int getUserCount()
	{
		return userCount;
	}

	
	public void setUserCount(int userCount)
	{
		this.userCount = userCount;
	}
	
}
