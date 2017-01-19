package com.huiyee.esite.dto.showdto;

import java.io.Serializable;

import com.huiyee.esite.dto.IDto;
import com.huiyee.interact.xc.model.Xc;

public class Show146Dto implements IDto, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2230712066615737679L;
	private Xc xc;
	public Xc getXc() {
		return xc;
	}
	public void setXc(Xc xc) {
		this.xc = xc;
	}
	
}
