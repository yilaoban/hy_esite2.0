package com.huiyee.esite.dto;

import java.io.Serializable;

import com.huiyee.esite.model.SinaShare;

public class Feature112Dto implements IDto, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6664864653844754743L;
	private SinaShare share;
	
	public SinaShare getShare() {
		return share;
	}
	public void setShare(SinaShare share) {
		this.share = share;
	}
	
}
