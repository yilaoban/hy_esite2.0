package com.huiyee.esite.dto;

import java.io.Serializable;
import java.util.List;

import com.huiyee.esite.model.BBSForum;

public class Feature151Dto implements IDto, Serializable{

	private static final long serialVersionUID = 3776477610824949130L;
	
	private long fid;
	private long relationid;
	private String type;
	private List<BBSForum> bbsForumList;
	private List<Long> froumidList;
	private String forumid;
	private long pageid;
	private List<BBSForum> list;
	
	public String getForumid() {
		return forumid;
	}
	public void setForumid(String forumid) {
		this.forumid = forumid;
	}
	public long getFid() {
		return fid;
	}
	public void setFid(long fid) {
		this.fid = fid;
	}
	public long getRelationid() {
		return relationid;
	}
	public void setRelationid(long relationid) {
		this.relationid = relationid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<BBSForum> getBbsForumList() {
		return bbsForumList;
	}
	public void setBbsForumList(List<BBSForum> bbsForumList) {
		this.bbsForumList = bbsForumList;
	}
	public List<Long> getFroumidList() {
		return froumidList;
	}
	public void setFroumidList(List<Long> froumidList) {
		this.froumidList = froumidList;
	}
	public long getPageid() {
		return pageid;
	}
	public void setPageid(long pageid) {
		this.pageid = pageid;
	}
	public List<BBSForum> getList() {
		return list;
	}
	public void setList(List<BBSForum> list) {
		this.list = list;
	}
	
	
}
