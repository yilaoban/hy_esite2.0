package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

public class BBSForum implements Serializable
{
	private static final long serialVersionUID = -6885493500603942084L;
	private long id;
	private long cateid;
	private long lastpostid;
	private long lastposter;
	private String title;
	private String hydesc;
	private String hyrule;
	private int rank;
	private int topicnum;
	private int postnum;
	private int posttodaynum;
	private Date lasttime;
	private String groupviews;
	private String grouptopics;
	private String grouppost;
	private int visittype;
	private int ovisittype;
	private long forumer;
	private Date createtime;
	private String forumname;
	private String topicCheck;
	private String commentCheck;
	private String nickname;
	
	private String catname;
	private long registerpageid;
	private long loginpageid;
	private String img;
	
	public int getOvisittype() {
		return ovisittype;
	}
	public void setOvisittype(int ovisittype) {
		this.ovisittype = ovisittype;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getTopicCheck()
	{
		return topicCheck;
	}
	public void setTopicCheck(String topicCheck)
	{
		this.topicCheck = topicCheck;
	}
	public String getCommentCheck()
	{
		return commentCheck;
	}
	public void setCommentCheck(String commentCheck)
	{
		this.commentCheck = commentCheck;
	}
	public String getForumname()
	{
		return forumname;
	}
	public void setForumname(String forumname)
	{
		this.forumname = forumname;
	}
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public long getCateid()
	{
		return cateid;
	}
	public void setCateid(long cateid)
	{
		this.cateid = cateid;
	}
	public long getLastpostid()
	{
		return lastpostid;
	}
	public void setLastpostid(long lastpostid)
	{
		this.lastpostid = lastpostid;
	}
	public long getLastposter()
	{
		return lastposter;
	}
	public void setLastposter(long lastposter)
	{
		this.lastposter = lastposter;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getHydesc()
	{
		return hydesc;
	}
	public void setHydesc(String hydesc)
	{
		this.hydesc = hydesc;
	}
	public String getHyrule()
	{
		return hyrule;
	}
	public void setHyrule(String hyrule)
	{
		this.hyrule = hyrule;
	}
	public int getRank()
	{
		return rank;
	}
	public void setRank(int rank)
	{
		this.rank = rank;
	}
	public int getTopicnum()
	{
		return topicnum;
	}
	public void setTopicnum(int topicnum)
	{
		this.topicnum = topicnum;
	}
	public int getPostnum()
	{
		return postnum;
	}
	public void setPostnum(int postnum)
	{
		this.postnum = postnum;
	}
	public int getPosttodaynum()
	{
		return posttodaynum;
	}
	public void setPosttodaynum(int posttodaynum)
	{
		this.posttodaynum = posttodaynum;
	}
	public Date getLasttime()
	{
		return lasttime;
	}
	public void setLasttime(Date lasttime)
	{
		this.lasttime = lasttime;
	}
	public String getGroupviews()
	{
		return groupviews;
	}
	public void setGroupviews(String groupviews)
	{
		this.groupviews = groupviews;
	}
	public String getGrouptopics()
	{
		return grouptopics;
	}
	public void setGrouptopics(String grouptopics)
	{
		this.grouptopics = grouptopics;
	}
	public String getGrouppost()
	{
		return grouppost;
	}
	public void setGrouppost(String grouppost)
	{
		this.grouppost = grouppost;
	}
	public int getVisittype()
	{
		return visittype;
	}
	public void setVisittype(int visittype)
	{
		this.visittype = visittype;
	}
	public Date getCreatetime()
	{
		return createtime;
	}
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}
	public long getForumer()
	{
		return forumer;
	}
	public void setForumer(long forumer)
	{
		this.forumer = forumer;
	}
	public String getNickname()
	{
		return nickname;
	}
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}
	public String getCatname()
	{
		return catname;
	}
	public void setCatname(String catname)
	{
		this.catname = catname;
	}
	public long getRegisterpageid() {
		return registerpageid;
	}
	public void setRegisterpageid(long registerpageid) {
		this.registerpageid = registerpageid;
	}
	public long getLoginpageid() {
		return loginpageid;
	}
	public void setLoginpageid(long loginpageid) {
		this.loginpageid = loginpageid;
	}

}
