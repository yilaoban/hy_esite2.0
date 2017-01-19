package com.huiyee.tfmodel;

public class HyWbRelation implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstId;
	private String secondId;
	
	private int type;
	
	public HyWbRelation(){
		super();
	}

	public String getFirstId() {
		return firstId;
	}

	public String getSecondId() {
		return secondId;
	}

	public int getType() {
		return type;
	}

	public void setFirstId(String firstId) {
		this.firstId = firstId;
	}

	public void setSecondId(String secondId) {
		this.secondId = secondId;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	

}
