package com.huiyee.esite.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.huiyee.interact.EmailPeriodical.model.EmailPeriodicalModel;

public class Feature122Dto implements IDto, Serializable{

	private static final long serialVersionUID = 29172812979093240L;
	
	private List<EmailPeriodicalModel> emailPeriodical;
	private long fid;
	private ArrayList<Long> moduleList;
	private List<String> list;
	
	public List<EmailPeriodicalModel> getEmailPeriodical() {
		return emailPeriodical;
	}
	public void setEmailPeriodical(List<EmailPeriodicalModel> emailPeriodical) {
		this.emailPeriodical = emailPeriodical;
	}
	public long getFid() {
		return fid;
	}
	public void setFid(long fid) {
		this.fid = fid;
	}
	public ArrayList<Long> getModuleList() {
		return moduleList;
	}
	public void setModuleList(ArrayList<Long> moduleList) {
		this.moduleList = moduleList;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
}
