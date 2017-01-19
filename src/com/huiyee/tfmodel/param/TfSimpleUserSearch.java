package com.huiyee.tfmodel.param;

import java.io.Serializable;

import com.huiyee.tfmodel.TfConstants;

public class TfSimpleUserSearch implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int verified = TfConstants.NOT_REQUIRED;
	private int verifiedType = TfConstants.NOT_REQUIRED;
	private int province = TfConstants.NOT_REQUIRED;
	private int city = TfConstants.NOT_REQUIRED;
	private int minF= TfConstants.NOT_REQUIRED;
	private int maxF= TfConstants.NOT_REQUIRED;
	private int gender= TfConstants.NOT_REQUIRED;
	
	public TfSimpleUserSearch() {
		super();
	}
	
	public int getVerified() {
		return verified;
	}
	public int getVerifiedType() {
		return verifiedType;
	}
	public int getProvince() {
		return province;
	}
	public int getCity() {
		return city;
	}
	public int getMinF() {
		return minF;
	}
	public int getMaxF() {
		return maxF;
	}
	public int getGender() {
		return gender;
	}
	public void setVerified(int verified) {
		this.verified = verified;
	}
	public void setVerifiedType(int verifiedType) {
		this.verifiedType = verifiedType;
	}
	public void setProvince(int province) {
		this.province = province;
	}
	public void setCity(int city) {
		this.city = city;
	}
	public void setMinF(int minF) {
		this.minF = minF;
	}
	public void setMaxF(int maxF) {
		this.maxF = maxF;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	
	

}
