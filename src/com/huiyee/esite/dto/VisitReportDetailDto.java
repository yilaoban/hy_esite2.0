package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.UserInfo;
import com.huiyee.esite.model.VisitReportDetail;

public class VisitReportDetailDto implements IDto {

	private List<VisitReportDetail> list;
	private int total;
	private Pager pager;
	private UserInfo user;
	private int num;

	public List<VisitReportDetail> getList() {
		return list;
	}

	public void setList(List<VisitReportDetail> list) {
		this.list = list;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	
	
}
