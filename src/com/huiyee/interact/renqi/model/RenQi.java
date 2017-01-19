package com.huiyee.interact.renqi.model;

import java.util.Date;

import com.huiyee.esite.model.SuperHdModel;
import com.huiyee.esite.util.DateUtil;

public class RenQi extends SuperHdModel
{

	private static final long serialVersionUID = 1L;
    private long id;
    private String title;
    private long owner;
    private long lotteryid;
    private int cnum;
    private int utype;
    private String content;
    private String link;
    private String wxtitle;
    private String wximg;
    private String wxdesc;
    private int minjf;
    private int maxjf;
    private int maxlu;
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
	public long getLotteryid()
	{
		return lotteryid;
	}
	public void setLotteryid(long lotteryid)
	{
		this.lotteryid = lotteryid;
	}
	public int getCnum()
	{
		return cnum;
	}
	public void setCnum(int cnum)
	{
		this.cnum = cnum;
	}
	public int getUtype()
	{
		return utype;
	}
	public void setUtype(int utype)
	{
		this.utype = utype;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getLink()
	{
		return link;
	}
	public void setLink(String link)
	{
		this.link = link;
	}
	public String getWxtitle()
	{
		return wxtitle;
	}
	public void setWxtitle(String wxtitle)
	{
		this.wxtitle = wxtitle;
	}
	public String getWximg()
	{
		return wximg;
	}
	public void setWximg(String wximg)
	{
		this.wximg = wximg;
	}
	public String getWxdesc()
	{
		return wxdesc;
	}
	public void setWxdesc(String wxdesc)
	{
		this.wxdesc = wxdesc;
	}
	public int getMinjf()
	{
		return minjf;
	}
	public void setMinjf(int minjf)
	{
		this.minjf = minjf;
	}
	public int getMaxjf()
	{
		return maxjf;
	}
	public void setMaxjf(int maxjf)
	{
		this.maxjf = maxjf;
	}
	public int getMaxlu()
	{
		return maxlu;
	}
	public void setMaxlu(int maxlu)
	{
		this.maxlu = maxlu;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
}
