package com.huiyee.esite.dto;

import com.huiyee.weixin.model.PayOrder;

public class OwnerBanlanceDto implements IDto{
	
	private int rmb;
	private BalancePageDto pagedto;
	private int realprice;
	public int getRealprice() {
		return realprice;
	}

	public void setRealprice(int realprice) {
		this.realprice = realprice;
	}

	public BalancePageDto getPagedto() {
		return pagedto;
	}

	public void setPagedto(BalancePageDto pagedto) {
		this.pagedto = pagedto;
	}

	public int getRmb() {
		return rmb;
	}

	public void setRmb(int rmb) {
		this.rmb = rmb;
	}

	
}
