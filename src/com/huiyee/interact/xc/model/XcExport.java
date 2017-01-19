package com.huiyee.interact.xc.model;

import java.util.Date;

import com.huiyee.esite.util.DateUtil;

public class XcExport
{
	private long xcid;
	private String name;
	private String nickname;
	private long uid;
	private int utype;
	private String top;
	private Date createtime;
	private int startnum;
	private int joinnum;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public long getUid()
	{
		return uid;
	}

	public void setUid(long uid)
	{
		this.uid = uid;
	}

	public int getUtype()
	{
		return utype;
	}

	public void setUtype(int utype)
	{
		this.utype = utype;
	}

	public String getTop()
	{
		return top;
	}

	public void setTop(String top)
	{
		this.top = top;
	}

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

	public int getStartnum()
	{
		return startnum;
	}

	public void setStartnum(int startnum)
	{
		this.startnum = startnum;
	}

	public int getJoinnum()
	{
		return joinnum;
	}

	public void setJoinnum(int joinnum)
	{
		this.joinnum = joinnum;
	}

	public long getXcid()
	{
		return xcid;
	}

	public void setXcid(long xcid)
	{
		this.xcid = xcid;
	}

	@Override
	public String toString()
	{
		// 嘉宾姓名,嘉宾昵称,第几次抽奖,摇多少次
		return startnum + "," + cvsStr(name) + "," + cvsStr(nickname) + "," + joinnum + "," + cvsStr(DateUtil.getDateTimeString(createtime));
	}

	private String cvsStr(String str)
	{
		if (str != null)
		{
			if (str.indexOf(",") != -1)
			{
				return "\"" + str + "\"";
			}
			else
			{
				return str;
			}
		}
		else
		{
			return "";
		}
	}

}
