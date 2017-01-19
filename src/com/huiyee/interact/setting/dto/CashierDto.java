package com.huiyee.interact.setting.dto;

import java.util.List;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.model.OrderGoods;
import com.huiyee.esite.model.WxUser;
import com.huiyee.interact.setting.model.HyUserLevel;
import com.huiyee.interact.setting.model.HyUserXfDesc;
import com.huiyee.interact.setting.model.UWay;

public class CashierDto implements IDto {

	private WxUser wxUser;
	private int rmb;
	private UWay uw;
	private HyUser hyUser;
	private HyUserLevel hyUserLevel;
	private String vm;
	private List<HyUserXfDesc> xfDescList;
	private OrderGoods og;

	public WxUser getWxUser() {
		return wxUser;
	}

	public void setWxUser(WxUser wxUser) {
		this.wxUser = wxUser;
	}

	public int getRmb() {
		return rmb;
	}

	public void setRmb(int rmb) {
		this.rmb = rmb;
	}

	public UWay getUw() {
		return uw;
	}

	public void setUw(UWay uw) {
		this.uw = uw;
	}

	public HyUser getHyUser() {
		return hyUser;
	}

	public void setHyUser(HyUser hyUser) {
		this.hyUser = hyUser;
	}

	public HyUserLevel getHyUserLevel() {
		return hyUserLevel;
	}

	public void setHyUserLevel(HyUserLevel hyUserLevel) {
		this.hyUserLevel = hyUserLevel;
	}

	public String getVm() {
		return vm;
	}

	public void setVm(String vm) {
		this.vm = vm;
	}

	public List<HyUserXfDesc> getXfDescList() {
		return xfDescList;
	}

	public void setXfDescList(List<HyUserXfDesc> xfDescList) {
		this.xfDescList = xfDescList;
	}

	public OrderGoods getOg() {
		return og;
	}

	public void setOg(OrderGoods og) {
		this.og = og;
	}

}
