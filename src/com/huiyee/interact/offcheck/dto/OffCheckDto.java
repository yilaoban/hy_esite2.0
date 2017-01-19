
package com.huiyee.interact.offcheck.dto;

import java.util.List;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.model.WxUser;
import com.huiyee.interact.offcheck.model.OffCheck;
import com.huiyee.interact.offcheck.model.OffCheckLog;
import com.huiyee.interact.offcheck.model.OffCheckRecord;
import com.huiyee.interact.offcheck.model.OffCheckSource;
import com.huiyee.interact.setting.model.HyUserDz;
import com.huiyee.interact.template.model.WxTemplate;

public class OffCheckDto implements IDto {

	private List<OffCheckSource> source;
	private Pager pager;
	private List<OffCheckRecord> records;
	private List<OffCheckLog> logs;
	private List<HyUserDz> dzList;
	private List<WxTemplate> templates;
	private WxUser wxUser;
	private OffCheckSource ocs;
	private OffCheck ofc;

	public List<OffCheckSource> getSource() {
		return source;
	}

	public void setSource(List<OffCheckSource> source) {
		this.source = source;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public List<OffCheckRecord> getRecords() {
		return records;
	}

	public void setRecords(List<OffCheckRecord> records) {
		this.records = records;
	}

	public List<OffCheckLog> getLogs() {
		return logs;
	}

	public void setLogs(List<OffCheckLog> logs) {
		this.logs = logs;
	}

	public List<HyUserDz> getDzList() {
		return dzList;
	}

	public void setDzList(List<HyUserDz> dzList) {
		this.dzList = dzList;
	}

	public List<WxTemplate> getTemplates() {
		return templates;
	}

	public void setTemplates(List<WxTemplate> templates) {
		this.templates = templates;
	}

	public WxUser getWxUser() {
		return wxUser;
	}

	public void setWxUser(WxUser wxUser) {
		this.wxUser = wxUser;
	}

	public OffCheckSource getOcs() {
		return ocs;
	}

	public void setOcs(OffCheckSource ocs) {
		this.ocs = ocs;
	}

	public OffCheck getOfc() {
		return ofc;
	}

	public void setOfc(OffCheck ofc) {
		this.ofc = ofc;
	}

}
