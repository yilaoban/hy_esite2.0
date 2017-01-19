package com.huiyee.tfmodel;

import java.util.Date;

import net.sf.json.JSONObject;

public class HyWbSrc implements java.io.Serializable
{

	private static final long serialVersionUID = 1L;

	private HyWbUser user = null; // 作者信息
	private Date createdAt; // status创建时间
	private String id; // status id
	private String mid; // 微博MID
	private String text; // 微博内容
	private HyWbSource source; // 微博来源
	private boolean favorited; // 是否已收藏
	private boolean truncated;
	private long inReplyToStatusId; // 回复ID
	private long inReplyToUserId; // 回复人ID
	private String inReplyToScreenName; // 回复人昵称
	private String thumbnailPic; // 微博内容中的图片的缩略地址
	private String bmiddlePic; // 中型图片
	private String originalPic; // 原始图片
	private HyWbSrc reweibo = null; // 转发的博文，内容为status，如果不是转发，则没有此字段
	private String geo; // 地理信息，保存经纬度，没有时不返回此字段
	private double latitude = -1; // 纬度
	private double longitude = -1; // 经度
	private int repostsCount; // 转发数
	private int commentsCount; // 评论数
	private String annotations; // 元数据，没有时不返回此字段
	private String pid;//如果是转发微博,这个id是父类微博的id
	private int mlevel;
	private HyWbVisible visible;

	public HyWbSrc()
	{
		super();
	}

	public HyWbSrc(String jsonstr) {
		JSONObject json = JSONObject.fromObject(jsonstr);
		String userStr = HyJsonUtils.pasString(json, "user");
		if (userStr != null && !"null".equals(userStr)) {
			this.user = new HyWbUser(userStr);
		}
		JSONObject createdAtObj = HyJsonUtils.pasJsonobj(json,"createdAt");
		if (!createdAtObj.isEmpty()) {
			this.createdAt = new Date(createdAtObj.getLong("time"));
		}
		this.id = HyJsonUtils.pasString(json, "id");
		this.mid = HyJsonUtils.pasString(json, "mid");
		this.text = HyJsonUtils.pasString(json, "text");
		String sourceStr = HyJsonUtils.pasString(json, "source");
		if (sourceStr != null && !"null".equals(sourceStr)) {
			this.source = new HyWbSource(sourceStr);
		}
		this.favorited = HyJsonUtils.pasBoolean(json,"favorited");
		this.truncated = HyJsonUtils.pasBoolean(json,"truncated");
		this.inReplyToStatusId = HyJsonUtils.pasLong(json, "inReplyToStatusId");
		this.inReplyToUserId = HyJsonUtils.pasLong(json, "inReplyToUserId");
		this.inReplyToScreenName = HyJsonUtils.pasString(json, "inReplyToScreenName");
		this.thumbnailPic = HyJsonUtils.pasString(json, "thumbnailPic");
		this.bmiddlePic = HyJsonUtils.pasString(json, "bmiddlePic");
		this.originalPic = HyJsonUtils.pasString(json, "originalPic");
		String reweiboStr = HyJsonUtils.pasString(json, "reweibo");
		if (reweiboStr != null && !"null".equals(reweiboStr)) {
			this.reweibo = new HyWbSrc(reweiboStr);
		}
		this.geo = HyJsonUtils.pasString(json, "geo");
		this.latitude = HyJsonUtils.pasDouble(json,"latitude");
		this.longitude = HyJsonUtils.pasDouble(json,"longitude");
		this.repostsCount = HyJsonUtils.pasInt(json, "repostsCount");
		this.commentsCount = HyJsonUtils.pasInt(json, "commentsCount");
		this.annotations = HyJsonUtils.pasString(json, "annotations");
		this.pid = HyJsonUtils.pasString(json, "pid");
		this.mlevel = HyJsonUtils.pasInt(json,"mlevel");
		String visibleStr = HyJsonUtils.pasString(json, "visible");
		if (visibleStr != null && !"null".equals(visibleStr)) {
			this.visible = new HyWbVisible(visibleStr);
		}
	}

	public HyWbUser getUser()
	{
		return user;
	}

	public void setUser(HyWbUser user)
	{
		this.user = user;
	}

	public Date getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt(Date createdAt)
	{
		this.createdAt = createdAt;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
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

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public HyWbSource getSource()
	{
		return source;
	}

	public void setSource(HyWbSource source)
	{
		this.source = source;
	}

	public boolean isFavorited()
	{
		return favorited;
	}

	public void setFavorited(boolean favorited)
	{
		this.favorited = favorited;
	}

	public boolean isTruncated()
	{
		return truncated;
	}

	public void setTruncated(boolean truncated)
	{
		this.truncated = truncated;
	}

	public long getInReplyToStatusId()
	{
		return inReplyToStatusId;
	}

	public void setInReplyToStatusId(long inReplyToStatusId)
	{
		this.inReplyToStatusId = inReplyToStatusId;
	}

	public long getInReplyToUserId()
	{
		return inReplyToUserId;
	}

	public void setInReplyToUserId(long inReplyToUserId)
	{
		this.inReplyToUserId = inReplyToUserId;
	}

	public String getInReplyToScreenName()
	{
		return inReplyToScreenName;
	}

	public void setInReplyToScreenName(String inReplyToScreenName)
	{
		this.inReplyToScreenName = inReplyToScreenName;
	}

	public String getThumbnailPic()
	{
		return thumbnailPic;
	}

	public void setThumbnailPic(String thumbnailPic)
	{
		this.thumbnailPic = thumbnailPic;
	}

	public String getBmiddlePic()
	{
		return bmiddlePic;
	}

	public void setBmiddlePic(String bmiddlePic)
	{
		this.bmiddlePic = bmiddlePic;
	}

	public String getOriginalPic()
	{
		return originalPic;
	}

	public void setOriginalPic(String originalPic)
	{
		this.originalPic = originalPic;
	}

	public HyWbSrc getReweibo()
	{
		return reweibo;
	}

	public void setReweibo(HyWbSrc reweibo)
	{
		this.reweibo = reweibo;
	}

	public String getGeo()
	{
		return geo;
	}

	public void setGeo(String geo)
	{
		this.geo = geo;
	}

	public double getLatitude()
	{
		return latitude;
	}

	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}

	public double getLongitude()
	{
		return longitude;
	}

	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}

	public int getRepostsCount()
	{
		return repostsCount;
	}

	public void setRepostsCount(int repostsCount)
	{
		this.repostsCount = repostsCount;
	}

	public int getCommentsCount()
	{
		return commentsCount;
	}

	public void setCommentsCount(int commentsCount)
	{
		this.commentsCount = commentsCount;
	}

	public String getAnnotations()
	{
		return annotations;
	}

	public void setAnnotations(String annotations)
	{
		this.annotations = annotations;
	}

	public int getMlevel()
	{
		return mlevel;
	}

	public void setMlevel(int mlevel)
	{
		this.mlevel = mlevel;
	}

	public HyWbVisible getVisible()
	{
		return visible;
	}

	public void setVisible(HyWbVisible visible)
	{
		this.visible = visible;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HyWbSrc other = (HyWbSrc) obj;
		if (id == null)
		{
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public String getPid()
	{
		return pid;
	}
	
	public void setPid(String pid)
	{
		this.pid = pid;
	}

	@Override
	public String toString()
	{
		return "[user=" + user + ", createdAt=" + createdAt + ", id=" + id + ", text=" + text + ", source=" + source + ", favorited=" + favorited + ", truncated=" + truncated + ", inReplyToStatusId=" + inReplyToStatusId + ", inReplyToUserId=" + inReplyToUserId + ", inReplyToScreenName="
				+ inReplyToScreenName + ", thumbnailPic=" + thumbnailPic + ", bmiddlePic=" + bmiddlePic + ", originalPic=" + originalPic + ", reweibo=" + reweibo + ", geo=" + geo + ", latitude=" + latitude + ", longitude=" + longitude + ", repostsCount=" + repostsCount + ", commentsCount="
				+ commentsCount + ", mid=" + mid + ", annotations=" + annotations + ", mlevel=" + mlevel + ", visible=" + visible + ",pid"+pid+"]";
	}


}
