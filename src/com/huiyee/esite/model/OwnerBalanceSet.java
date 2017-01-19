package com.huiyee.esite.model;

public class OwnerBalanceSet 
{
	private long id;
	private long owner;
	private int sharenum;//分享
	private int ssharenum;//分享带来的分享
	private int sgznum;//分享带来的关注
	private int newnum;//新关注
	private int topicnum;//发表话题
	private int comnum;//评论
	private int topnum;//话题置顶
	private int upnum;//顶
	private int downum;//踩
	private int ocnum;//线下签到
	private int yynum;//微预约
	private int yypjnum;//服务提供者评价获得多少积分
	private long cachetime;//缓存时间
	private int ocxfnum;//线下消费多少块获得1积分
	
	public int getOcxfnum()
	{
		return ocxfnum;
	}
	
	public void setOcxfnum(int ocxfnum)
	{
		this.ocxfnum = ocxfnum;
	}

	public int getYypjnum()
	{
		return yypjnum;
	}
	
	public void setYypjnum(int yypjnum)
	{
		this.yypjnum = yypjnum;
	}


	public int getOcnum()
	{
		return ocnum;
	}

	
	public int getYynum()
	{
		return yynum;
	}

	
	public void setOcnum(int ocnum)
	{
		this.ocnum = ocnum;
	}

	
	public void setYynum(int yynum)
	{
		this.yynum = yynum;
	}

	public long getCachetime()
	{
		return cachetime;
	}
	
	public void setCachetime(long cachetime)
	{
		this.cachetime = cachetime;
	}

	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public long getOwner()
	{
		return owner;
	}
	
	public void setOwner(long owner)
	{
		this.owner = owner;
	}
	
	public int getSharenum()
	{
		return sharenum;
	}
	
	public void setSharenum(int sharenum)
	{
		this.sharenum = sharenum;
	}
	
	public int getSsharenum()
	{
		return ssharenum;
	}
	
	public void setSsharenum(int ssharenum)
	{
		this.ssharenum = ssharenum;
	}
	
	public int getSgznum()
	{
		return sgznum;
	}
	
	public void setSgznum(int sgznum)
	{
		this.sgznum = sgznum;
	}
	
	public int getNewnum()
	{
		return newnum;
	}
	
	public void setNewnum(int newnum)
	{
		this.newnum = newnum;
	}
	
	public int getTopicnum()
	{
		return topicnum;
	}
	
	public void setTopicnum(int topicnum)
	{
		this.topicnum = topicnum;
	}
	
	public int getComnum()
	{
		return comnum;
	}
	
	public void setComnum(int comnum)
	{
		this.comnum = comnum;
	}
	
	public int getTopnum()
	{
		return topnum;
	}
	
	public void setTopnum(int topnum)
	{
		this.topnum = topnum;
	}
	
	public int getUpnum()
	{
		return upnum;
	}
	
	public void setUpnum(int upnum)
	{
		this.upnum = upnum;
	}
	
	public int getDownum()
	{
		return downum;
	}
	
	public void setDownum(int downum)
	{
		this.downum = downum;
	}
	
	
}
