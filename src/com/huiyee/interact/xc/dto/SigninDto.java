package com.huiyee.interact.xc.dto;

import java.util.List;

import com.huiyee.interact.xc.model.XcCheckinRecord;


public class SigninDto
{

	private int count;//签到人数
	private List<XcCheckinRecord> list; //签到记录
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<XcCheckinRecord> getList() {
		return list;
	}
	public void setList(List<XcCheckinRecord> list) {
		this.list = list;
	}
}
