package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.interact.appointment.model.AppointmentModel;

public class AddOrderDto implements IDto {

	private List<AppointmentModel> list;
	private AppointmentModel am;
	

	public List<AppointmentModel> getList() {
		return list;
	}

	public void setList(List<AppointmentModel> list) {
		this.list = list;
	}

	public AppointmentModel getAm() {
		return am;
	}

	public void setAm(AppointmentModel am) {
		this.am = am;
	}



}
