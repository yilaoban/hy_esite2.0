package com.huiyee.esite.dto.showdto;

import java.io.Serializable;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.Feature118InteractApt;
import com.huiyee.interact.appointment.model.AppointmentModel;

public class Show118Dto implements IDto, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4432008376697426247L;
	private long fid;
	private Feature118InteractApt apt;
	private AppointmentModel app;
	
	public long getFid() {
		return fid;
	}
	public void setFid(long fid) {
		this.fid = fid;
	}
	public Feature118InteractApt getApt() {
		return apt;
	}
	public void setApt(Feature118InteractApt apt) {
		this.apt = apt;
	}
	public AppointmentModel getApp() {
		return app;
	}
	public void setApp(AppointmentModel app) {
		this.app = app;
	}

}
