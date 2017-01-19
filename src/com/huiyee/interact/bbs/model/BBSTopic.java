package com.huiyee.interact.bbs.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class BBSTopic implements Serializable {

	private static final long serialVersionUID = 6269851642504770848L;
	private long id;
	private long FORUM_ID;// 板块
	private long LAST_POST_ID;// 最后帖
	private long FIRST_POST_ID;// 主题帖
	private long CREATER_UID;// 发帖会员
	private long REPLYER_UID;// 最后回帖会员
	private String TITLE;// 标题
	private Date CREATE_TIME;// 创建时间
	private Date LAST_TIME;// 最后回帖时间
	private long VIEW_COUNT;// 浏览次数
	private int REPLY_COUNT;// 回复次数
	private int STATUS;// 状态
	private String HAVA_REPLY;// 回复列表,格式为,1,2,3
	private int moderator_reply;// 版主是否回复0-是未回复,1-是回复
	private int TYPE_ID;// 主题分类id
	private int ALLAY_REPLY;// 主题是否允许回复
	private int views_day;// 日访问量
	private int views_week;// 周访问量
	private int views_month;// 月访问量
	private int replycount_day;// 日回复量
	private int INDEX_COUNT;
	private String checked;
	private int up;
	private int down;
	private int top;
	private long entityid;
	private int entype;

	private String creater;// 发帖者
	private String creater_img;// 发帖者头像
	private String replyer;// 最后回复者
	private String content;
	private String pic;
	private List<String> pic_list;
	private boolean liked;
	private Date createtime;// user表创建时间
	private String forumname;// 板块
	private long online_total;
	private long minute;// 在线时长 （分钟）
	private int topicnum;
	private int replynum;
	private String level_name;
	private long postid;

	private String islink;
	private String linkurl;
	private String img;
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getIslink() {
		return islink;
	}

	public void setIslink(String islink) {
		this.islink = islink;
	}

	public String getLinkurl() {
		return linkurl;
	}

	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getFORUM_ID() {
		return FORUM_ID;
	}

	public void setFORUM_ID(long forum_id) {
		FORUM_ID = forum_id;
	}

	public long getLAST_POST_ID() {
		return LAST_POST_ID;
	}

	public void setLAST_POST_ID(long last_post_id) {
		LAST_POST_ID = last_post_id;
	}

	public long getFIRST_POST_ID() {
		return FIRST_POST_ID;
	}

	public void setFIRST_POST_ID(long first_post_id) {
		FIRST_POST_ID = first_post_id;
	}

	public long getCREATER_UID() {
		return CREATER_UID;
	}

	public void setCREATER_UID(long creater_uid) {
		CREATER_UID = creater_uid;
	}

	public long getREPLYER_UID() {
		return REPLYER_UID;
	}

	public void setREPLYER_UID(long replyer_uid) {
		REPLYER_UID = replyer_uid;
	}

	public String getTITLE() {
		return TITLE;
	}

	public void setTITLE(String title) {
		TITLE = title;
	}

	public Date getCREATE_TIME() {
		return CREATE_TIME;
	}

	public void setCREATE_TIME(Date create_time) {
		CREATE_TIME = create_time;
	}

	public Date getLAST_TIME() {
		return LAST_TIME;
	}

	public void setLAST_TIME(Date last_time) {
		LAST_TIME = last_time;
	}

	public long getVIEW_COUNT() {
		return VIEW_COUNT;
	}

	public void setVIEW_COUNT(long view_count) {
		VIEW_COUNT = view_count;
	}

	public int getREPLY_COUNT() {
		return REPLY_COUNT;
	}

	public void setREPLY_COUNT(int reply_count) {
		REPLY_COUNT = reply_count;
	}

	public int getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(int status) {
		STATUS = status;
	}

	public String getHAVA_REPLY() {
		return HAVA_REPLY;
	}

	public void setHAVA_REPLY(String hava_reply) {
		HAVA_REPLY = hava_reply;
	}

	public int getModerator_reply() {
		return moderator_reply;
	}

	public void setModerator_reply(int moderator_reply) {
		this.moderator_reply = moderator_reply;
	}

	public int getTYPE_ID() {
		return TYPE_ID;
	}

	public void setTYPE_ID(int type_id) {
		TYPE_ID = type_id;
	}

	public int getALLAY_REPLY() {
		return ALLAY_REPLY;
	}

	public void setALLAY_REPLY(int allay_reply) {
		ALLAY_REPLY = allay_reply;
	}

	public int getViews_day() {
		return views_day;
	}

	public void setViews_day(int views_day) {
		this.views_day = views_day;
	}

	public int getViews_week() {
		return views_week;
	}

	public void setViews_week(int views_week) {
		this.views_week = views_week;
	}

	public int getViews_month() {
		return views_month;
	}

	public void setViews_month(int views_month) {
		this.views_month = views_month;
	}

	public int getReplycount_day() {
		return replycount_day;
	}

	public void setReplycount_day(int replycount_day) {
		this.replycount_day = replycount_day;
	}

	public int getINDEX_COUNT() {
		return INDEX_COUNT;
	}

	public void setINDEX_COUNT(int index_count) {
		INDEX_COUNT = index_count;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public int getUp() {
		return up;
	}

	public void setUp(int up) {
		this.up = up;
	}

	public int getDown() {
		return down;
	}

	public void setDown(int down) {
		this.down = down;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getCreater_img() {
		return creater_img;
	}

	public void setCreater_img(String creater_img) {
		this.creater_img = creater_img;
	}

	public String getReplyer() {
		return replyer;
	}

	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isLiked() {
		return liked;
	}

	public void setLiked(boolean liked) {
		this.liked = liked;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getForumname() {
		return forumname;
	}

	public void setForumname(String forumname) {
		this.forumname = forumname;
	}

	public long getOnline_total() {
		return online_total;
	}

	public void setOnline_total(long online_total) {
		this.online_total = online_total;
	}

	public long getMinute() {
		return minute;
	}

	public void setMinute(long minute) {
		this.minute = minute;
	}

	public int getTopicnum() {
		return topicnum;
	}

	public void setTopicnum(int topicnum) {
		this.topicnum = topicnum;
	}

	public int getReplynum() {
		return replynum;
	}

	public void setReplynum(int replynum) {
		this.replynum = replynum;
	}

	public String getLevel_name() {
		return level_name;
	}

	public void setLevel_name(String level_name) {
		this.level_name = level_name;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public long getPostid() {
		return postid;
	}

	public void setPostid(long postid) {
		this.postid = postid;
	}

	public long getEntityid() {
		return entityid;
	}

	public void setEntityid(long entityid) {
		this.entityid = entityid;
	}

	public int getEntype() {
		return entype;
	}

	public void setEntype(int entype) {
		this.entype = entype;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public List<String> getPic_list() {
		return pic_list;
	}

	public void setPic_list(List<String> pic_list) {
		this.pic_list = pic_list;
	}

}
