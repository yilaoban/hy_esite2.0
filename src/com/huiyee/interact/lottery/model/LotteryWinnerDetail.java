package com.huiyee.interact.lottery.model;

import org.apache.commons.lang.StringUtils;

import com.huiyee.esite.util.DateUtil;

public class LotteryWinnerDetail
{
	private LotteryUserRecord record;
	private LotteryUserSub sub;
	private int type;

	public LotteryUserRecord getRecord()
	{
		return record;
	}

	public void setRecord(LotteryUserRecord record)
	{
		this.record = record;
	}

	public LotteryUserSub getSub()
	{
		return sub;
	}

	public void setSub(LotteryUserSub sub)
	{
		this.sub = sub;
	}

	@Override
	public String toString()
	{
		return record.getWbuid() + "," + cvsStr(record.getNickName()) + "," + cvsStr(record.getCreatetime()) + "," + cvsStr(sub.getUsername()) + "," + cvsStr(sub.getUserphone()) + "," + cvsStr(sub.getUseremail()) + "," + cvsStr(sub.getUserlocation());
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

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}
}
