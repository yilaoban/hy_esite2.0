package com.huiyee.interact.xc.dto;

import java.util.List;

import com.huiyee.interact.xc.model.XcCommentRecord;

public class XcCommentRecordDto implements IDto
{

	private List<XcCommentRecord> list;
	private String nickname;
	private int count;

	public List<XcCommentRecord> getList()
	{
		return list;
	}

	public void setList(List<XcCommentRecord> list)
	{
		this.list = list;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
