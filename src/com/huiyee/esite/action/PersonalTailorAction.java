package com.huiyee.esite.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.dto.PersonalTailorDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.InteractModel;

public class PersonalTailorAction extends InteractModelAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5612130510835258229L;
	private int pageId = 1;
	private PersonalTailorDto dto;

	public PersonalTailorDto getDto() {
		return dto;
	}

	public void setDto(PersonalTailorDto dto) {
		this.dto = dto;
	}

	public String execute() throws Exception {
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		dto = (PersonalTailorDto) pageCompose.findPageFeature(ownerid, pageId);
		return SUCCESS;
	}

	public boolean getPersonTail(){
		return true;
	}
}
