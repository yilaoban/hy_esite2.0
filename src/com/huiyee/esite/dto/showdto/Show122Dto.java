package com.huiyee.esite.dto.showdto;

import java.io.Serializable;
import java.util.List;

import com.huiyee.esite.dto.IDto;
import com.huiyee.interact.EmailPeriodical.model.EmailPeriodicalModel;

public class Show122Dto implements IDto, Serializable{
	private static final long serialVersionUID = -1644926242864757309L;
	
	private List<EmailPeriodicalModel> list;
	private EmailPeriodicalModel emaliPeriodical;
	
	public List<EmailPeriodicalModel> getList() {
		return list;
	}
	public void setList(List<EmailPeriodicalModel> list) {
		this.list = list;
	}
	public EmailPeriodicalModel getEmaliPeriodical() {
		return emaliPeriodical;
	}
	public void setEmaliPeriodical(EmailPeriodicalModel emaliPeriodical) {
		this.emaliPeriodical = emaliPeriodical;
	}
}
