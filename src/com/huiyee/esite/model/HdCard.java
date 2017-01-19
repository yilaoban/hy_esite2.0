package com.huiyee.esite.model;

import java.io.Serializable;

public class HdCard implements Serializable {

	private static final long serialVersionUID = -7184798864302989305L;
	
	private long id;
	private long featureid;
	private long fid;
	private String forumid; //社区
	
	private Redirect redirect;
	private Style_118 style118;
	private int start;//投票开始题数
	private int end;//投票结束题数
	private long pageid;//详情页pageid
	
	public long getFid()
	{
		return fid;
	}

	public void setFid(long fid)
	{
		this.fid = fid;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getFeatureid()
	{
		return featureid;
	}

	public void setFeatureid(long featureid)
	{
		this.featureid = featureid;
	}

	public Redirect getRedirect()
	{
		return redirect;
	}

	public void setRedirect(Redirect redirect)
	{
		this.redirect = redirect;
	}

	public String getForumid() {
		return forumid;
	}

	public void setForumid(String forumid) {
		this.forumid = forumid;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	
	public long getPageid()
	{
		return pageid;
	}

	
	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	
	public Style_118 getStyle118()
	{
		return style118;
	}

	
	public void setStyle118(Style_118 style118)
	{
		this.style118 = style118;
	}
	
}
