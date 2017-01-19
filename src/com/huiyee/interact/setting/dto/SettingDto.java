package com.huiyee.interact.setting.dto;

import java.util.List;

import org.apache.solr.common.SolrDocument;

import com.huiyee.esite.dto.Pager;
import com.huiyee.interact.setting.model.HyUserDz;
import com.huiyee.interact.setting.model.HyUserLevel;
import com.huiyee.interact.setting.model.HyUserLevelCode;
import com.huiyee.interact.setting.model.HyUserXfDesc;

public class SettingDto {

	private List<HyUserDz> dzList;
	private List<SolrDocument> docList;
	private List<HyUserXfDesc> xdList;
	private List<HyUserLevel> levelList;
	private List<HyUserLevelCode> codeList;
	private Pager pager;

	public List<HyUserLevel> getLevelList() {
		return levelList;
	}

	public void setLevelList(List<HyUserLevel> levelList) {
		this.levelList = levelList;
	}

	public List<HyUserDz> getDzList() {
		return dzList;
	}

	public void setDzList(List<HyUserDz> dzList) {
		this.dzList = dzList;
	}

	public List<SolrDocument> getDocList() {
		return docList;
	}

	public void setDocList(List<SolrDocument> docList) {
		this.docList = docList;
	}

	public List<HyUserXfDesc> getXdList() {
		return xdList;
	}

	public void setXdList(List<HyUserXfDesc> xdList) {
		this.xdList = xdList;
	}

	public List<HyUserLevelCode> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<HyUserLevelCode> codeList) {
		this.codeList = codeList;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

}
