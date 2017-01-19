package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Account implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8311164575788692614L;
	private long id;
	private String username;//’À∫≈√˚
	private String password;// √‹¬Î
	private Owner owner = new Owner();
	private List<OwnerPrivilege> privileges;
	
	public List<OwnerPrivilege> getPrivileges()
	{
		return privileges;
	}
	
	public void setPrivileges(List<OwnerPrivilege> privileges)
	{
		this.privileges = privileges;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean permission(String module,int value){
		return false;
	}
}
