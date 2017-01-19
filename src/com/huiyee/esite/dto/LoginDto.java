package com.huiyee.esite.dto;

import java.util.Map;

import com.huiyee.esite.model.Account;



public class LoginDto implements IDto{

	
	private Account account;
	private int loginResult;
	private Map<Integer, String> hideControl;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

    public int getLoginResult() {
        return loginResult;
    }

    public void setLoginResult(int loginResult) {
        this.loginResult = loginResult;
    }

	public Map<Integer, String> getHideControl() {
		return hideControl;
	}

	public void setHideControl(Map<Integer, String> hideControl) {
		this.hideControl = hideControl;
	}

	

	
}
