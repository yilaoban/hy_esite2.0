
package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

import com.huiyee.esite.util.DateUtil;

import net.sf.json.JSONObject;

public class ContentNew implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4961673020192400706L;
	private long id;
	private long ownerid;
	private long catid;
	private String catname;
	private String title;
	private String content;
	private String status;
	private Date createtime;
	private Date updatetime;
	private String simgurl;
	private String bimgurl;
	private String linkurl;

	private String hyType;
	private long ccid;
	private String shortdesc;
	private long featureid;
	private String createtimeStr;
	private int idx;
	private String islink;
	private int length;
	private String fatie;
	private long topicid;
	private Date startTime;
	private Date endTime;
	private ContentNew before;
	private ContentNew next;
	private JSONObject bread;
	private long readNum;

	private String author;// 作者
	private String source;// 来源
	private Date publishtime;// 发布时间
	private int top;// 0:未置顶 1:置顶

	private int initialZan;// 初始点赞量
	private int initialRead;// 初始阅读量
	/**
	 * 记录初始值,当发生修改时相同初始值的内容同步修改
	 */
	private long fatherid;

	public int getInitialZan()
	{
		return initialZan;
	}

	public void setInitialZan(int initialZan)
	{
		this.initialZan = initialZan;
	}

	public int getInitialRead()
	{
		return initialRead;
	}

	public void setInitialRead(int initialRead)
	{
		this.initialRead = initialRead;
	}

	public int getTop()
	{
		return top;
	}

	public void setTop(int top)
	{
		this.top = top;
	}

	public Date getPublishtime()
	{
		return publishtime;
	}

	public void setPublishtime(Date publishtime)
	{
		this.publishtime = publishtime;
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public String getSource()
	{
		return source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public long getReadNum()
	{
		return readNum;
	}

	public void setReadNum(long readNum)
	{
		this.readNum = readNum;
	}

	public JSONObject getBread()
	{
		return bread;
	}

	public void setBread(JSONObject bread)
	{
		this.bread = bread;
	}

	public long getFeatureid()
	{
		return featureid;
	}

	public void setFeatureid(long featureid)
	{
		this.featureid = featureid;
	}

	public String getHyType()
	{
		return hyType;
	}

	public void setHyType(String hyType)
	{
		this.hyType = hyType;
	}

	public long getCcid()
	{
		return ccid;
	}

	public void setCcid(long ccid)
	{
		this.ccid = ccid;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getOwnerid()
	{
		return ownerid;
	}

	public void setOwnerid(long ownerid)
	{
		this.ownerid = ownerid;
	}

	public long getCatid()
	{
		return catid;
	}

	public void setCatid(long catid)
	{
		this.catid = catid;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

	public Date getUpdatetime()
	{
		return updatetime;
	}

	public void setUpdatetime(Date updatetime)
	{
		this.updatetime = updatetime;
	}

	public String getCatname()
	{
		return catname;
	}

	public void setCatname(String catname)
	{
		this.catname = catname;
	}

	public String getSimgurl()
	{
		return simgurl;
	}

	public void setSimgurl(String simgurl)
	{
		this.simgurl = simgurl;
	}

	public String getBimgurl()
	{
		return bimgurl;
	}

	public void setBimgurl(String bimgurl)
	{
		this.bimgurl = bimgurl;
	}

	public String getLinkurl()
	{
		return linkurl;
	}

	public void setLinkurl(String linkurl)
	{
		this.linkurl = linkurl;
	}

	public String getShortdesc()
	{
		return shortdesc;
	}

	public void setShortdesc(String shortdesc)
	{
		this.shortdesc = shortdesc;
	}

	public String getCreatetimeStr()
	{
		return createtimeStr;
	}

	public void setCreatetimeStr(String createtimeStr)
	{
		this.createtimeStr = createtimeStr;
	}

	public int getIdx()
	{
		return idx;
	}

	public void setIdx(int idx)
	{
		this.idx = idx;
	}

	public String getIslink()
	{
		return islink;
	}

	public void setIslink(String islink)
	{
		this.islink = islink;
	}

	public int getLength()
	{
		return length;
	}

	public void setLength(int length)
	{
		this.length = length;
	}

	public String getFatie()
	{
		return fatie;
	}

	public void setFatie(String fatie)
	{
		this.fatie = fatie;
	}

	public long getTopicid()
	{
		return topicid;
	}

	public void setTopicid(long topicid)
	{
		this.topicid = topicid;
	}

	public Date getStartTime()
	{
		return startTime;
	}

	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}

	public Date getEndTime()
	{
		return endTime;
	}

	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}

	public ContentNew getBefore()
	{
		return before;
	}

	public void setBefore(ContentNew before)
	{
		this.before = before;
	}

	public ContentNew getNext()
	{
		return next;
	}

	public void setNext(ContentNew next)
	{
		this.next = next;
	}

	public long getFatherid()
	{
		return fatherid;
	}

	public void setFatherid(long fatherid)
	{
		this.fatherid = fatherid;
	}

}
