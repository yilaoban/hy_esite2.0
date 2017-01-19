package com.huiyee.esite.dto.showdto;

import java.io.Serializable;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.SinaShare;

public class Show112Dto implements IDto , Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -618147907599900986L;
	private SinaShare share;
	
	public SinaShare getShare() {
		return share;
	}
	public void setShare(SinaShare share) {
		this.share = share;
	}

}
