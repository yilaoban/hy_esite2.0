package com.huiyee.esite.model;

import java.io.Serializable;
public class EmailContract implements Serializable{

	
	/**
     * 
     */
    private static final long serialVersionUID = 6469068812984163937L;
    private long id;
	private String name;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
