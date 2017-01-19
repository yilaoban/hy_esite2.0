package com.huiyee.esite.dto.showdto;

import java.io.Serializable;

import com.huiyee.esite.dto.IDto;
import com.huiyee.interact.spread.model.SpreadModel;

public class Show134Dto implements IDto, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7085898025222256115L;
	private SpreadModel spread;
	private long fid;
	
	public SpreadModel getSpread() {
		return spread;
	}
	public void setSpread(SpreadModel spread) {
		this.spread = spread;
	}
	public long getFid() {
		return fid;
	}
	public void setFid(long fid) {
		this.fid = fid;
	}
	
}
