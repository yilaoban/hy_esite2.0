package com.huiyee.tfmodel;

import java.util.Date;

import net.sf.json.JSONObject;

public class HyWbComment  implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date createdAt;                    //评论时间
	private long id;                           //评论id
	private String mid;						   //评论id
	private String idstr;					   //评论id
	private String text;                       //评论内容
	private String source;                     //内容来源
	private HyWbComment replycomment = null;       //回复的评论内容
	private HyWbUser user = null;                  //User对象
	private HyWbSrc wbsrc = null;              //Status对象

	
	public HyWbComment() {
		
	}

	public HyWbComment(String jsonstr) {
		JSONObject json = JSONObject.fromObject(jsonstr);
		JSONObject createdAtObj = HyJsonUtils.pasJsonobj(json,"createdAt");
		if (!createdAtObj.isEmpty()) {
			this.createdAt = new Date(createdAtObj.getLong("time"));
		}
		this.id = HyJsonUtils.pasLong(json, "id");
		this.mid = HyJsonUtils.pasString(json, "mid");
		this.idstr = HyJsonUtils.pasString(json, "idstr");
		this.text = HyJsonUtils.pasString(json, "text");
		this.source = HyJsonUtils.pasString(json, "source");
		String replycommentStr = HyJsonUtils.pasString(json, "replycomment");
		if (replycommentStr != null && !"null".equals(replycommentStr)) {
			this.replycomment = new HyWbComment(replycommentStr);
		}
		String userStr = HyJsonUtils.pasString(json, "user");
		if (userStr != null && !"null".equals(userStr)) {
			this.user = new HyWbUser(userStr);
		}
		String wbsrcStr = HyJsonUtils.pasString(json, "wbsrc");
		if (wbsrcStr != null && !"null".equals(wbsrcStr)) {
			this.wbsrc = new HyWbSrc(wbsrcStr);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HyWbComment other = (HyWbComment) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comment [createdAt=" + createdAt + ", id=" + id + ", mid="
				+ mid + ", idstr=" + idstr + ", text=" + text + ", source="
				+ source + ", replycomment=" + replycomment + ", user=" + user
				+ ", status=" + wbsrc +"]";
	}

	public Date getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt(Date createdAt)
	{
		this.createdAt = createdAt;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getMid()
	{
		return mid;
	}

	public void setMid(String mid)
	{
		this.mid = mid;
	}

	public String getIdstr()
	{
		return idstr;
	}

	public void setIdstr(String idstr)
	{
		this.idstr = idstr;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public String getSource()
	{
		return source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public HyWbComment getReplycomment()
	{
		return replycomment;
	}

	public void setReplycomment(HyWbComment replycomment)
	{
		this.replycomment = replycomment;
	}

	public HyWbUser getUser()
	{
		return user;
	}

	public void setUser(HyWbUser user)
	{
		this.user = user;
	}

	public HyWbSrc getWbsrc()
	{
		return wbsrc;
	}

	public void setWbsrc(HyWbSrc wbsrc)
	{
		this.wbsrc = wbsrc;
	}

}
