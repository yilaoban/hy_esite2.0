package com.huiyee.interact.xc.model;

import java.io.Serializable;
import java.util.Date;

public class Xc implements Serializable
{
	/**
	 * 现场抽奖
	 */
	private static final long serialVersionUID = 2731809257615457312L;
	private long id;
	private String title;
	private long owner;
	private Date createtime;
	private String needinvite="Y";
	private String needcheckin="Y";
	private String needcomment="Y";
	private String needlottery="Y";
	private InviteConfig inviteconfig = new InviteConfig();// 邀请设置
	private CheckinConfig checkinconfig = new CheckinConfig();// 签到设置
	private CommentConfig commentconfig = new CommentConfig();// 评论设置
	private LotteryConfig lotteryconfig = new LotteryConfig();// 抽奖设置
	
	private long aptid;//微现场的申请

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public long getOwner()
	{
		return owner;
	}

	public void setOwner(long owner)
	{
		this.owner = owner;
	}

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

	public String getNeedinvite()
	{
		return needinvite;
	}

	public void setNeedinvite(String needinvite)
	{
		this.needinvite = needinvite;
	}

	public String getNeedcheckin()
	{
		return needcheckin;
	}

	public void setNeedcheckin(String needcheckin)
	{
		this.needcheckin = needcheckin;
	}

	public String getNeedcomment()
	{
		return needcomment;
	}

	public void setNeedcomment(String needcomment)
	{
		this.needcomment = needcomment;
	}

	public String getNeedlottery()
	{
		return needlottery;
	}

	public void setNeedlottery(String needlottery)
	{
		this.needlottery = needlottery;
	}

	public InviteConfig getInviteconfig()
	{
		return inviteconfig;
	}

	public void setInviteconfig(InviteConfig inviteconfig)
	{
		this.inviteconfig = inviteconfig;
	}

	public CheckinConfig getCheckinconfig()
	{
		return checkinconfig;
	}

	public void setCheckinconfig(CheckinConfig checkinconfig)
	{
		this.checkinconfig = checkinconfig;
	}

	public CommentConfig getCommentconfig()
	{
		return commentconfig;
	}

	public void setCommentconfig(CommentConfig commentconfig)
	{
		this.commentconfig = commentconfig;
	}

	public LotteryConfig getLotteryconfig()
	{
		return lotteryconfig;
	}

	public void setLotteryconfig(LotteryConfig lotteryconfig)
	{
		this.lotteryconfig = lotteryconfig;
	}

	
	public long getAptid()
	{
		return aptid;
	}

	
	public void setAptid(long aptid)
	{
		this.aptid = aptid;
	}

}
