package com.huiyee.interact.pay.dto;

import java.util.List;

import com.huiyee.esite.dto.Pager;
import com.huiyee.interact.setting.model.HyUserDz;

public class PayDto {

	private List<HyUserDz> dzList;
	private Pager pager;

	public List<HyUserDz> getDzList() {
		return dzList;
	}

	public void setDzList(List<HyUserDz> dzList) {
		this.dzList = dzList;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

}
