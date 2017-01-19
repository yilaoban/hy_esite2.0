package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.InteractModel;
import com.huiyee.esite.model.OrderMessage;

public class OrderMesDto implements IDto{

	private List<OrderMessage> list;
	private int total;
	private Pager pager;
	private List<InteractModel> iModel;
	
	public List<OrderMessage> getList() {
		return list;
	}
	public void setList(List<OrderMessage> list) {
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
	public List<InteractModel> getIModel() {
		return iModel;
	}
	public void setIModel(List<InteractModel> model) {
		iModel = model;
	}
	
	
}
