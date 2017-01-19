package com.huiyee.esite.dto.showdto;

import java.io.Serializable;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.SecurityCodeModel;

public class Show142Dto implements IDto, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SecurityCodeModel record;

	public SecurityCodeModel getRecord() {
		return record;
	}

	public void setRecord(SecurityCodeModel record) {
		this.record = record;
	}


	
}
