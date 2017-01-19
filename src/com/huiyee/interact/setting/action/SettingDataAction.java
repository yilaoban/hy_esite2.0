package com.huiyee.interact.setting.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.response.FieldStatsInfo;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.constants.IPrivilegeConstants;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.dto.SolrDataDto;
import com.huiyee.esite.service.Permission;
import com.huiyee.esite.solr.HyJfSolrServer;
import com.huiyee.interact.setting.dto.SettingDto;

public class SettingDataAction extends AbstractAction {
	private static final long serialVersionUID = -5866733124877768681L;

	private HyJfSolrServer hyJfSolrServer;

	private int pageId = 1;
	private SettingDto dto;
	private String startTime;
	private String endTime;
	private Double sum_add = 0d;
	private Double sum_sub = 0d;

	@Override
	public int getLightType() {
		return 3;
	}

	@Permission(module = IPrivilegeConstants.MODULE_APP_微积分, privilege = IPrivilegeConstants.PERMISSION_R)
	public String rmb() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (startTime == null || endTime == null) {
			startTime = endTime = sdf.format(new Date());
		}

		long owner = this.getOwner();
		String type = "RMB";
		String cday = getCday(startTime, endTime);
		int rows = 10;
		int start = (pageId - 1) * rows;
		SolrDataDto sdd = hyJfSolrServer.findByCday(owner, type, cday, start, rows);
		SolrDocumentList docList = sdd.getDocumentList();
		Calendar cal = Calendar.getInstance();
		for (int i = 0; i < docList.size(); i++) {
			SolrDocument doc = docList.get(i);
			Date created = (Date) doc.get("created");
			cal.setTime(created);
			cal.add(Calendar.HOUR_OF_DAY, -8);
			doc.setField("created", cal.getTime());
		}
		dto = new SettingDto();
		dto.setDocList(docList);
		dto.setPager(new Pager(pageId, sdd.getTotal(), rows));
		// 总增长
		FieldStatsInfo fsi = hyJfSolrServer.findAddByCday(owner, type, cday, start, rows).getFsi();
		if (fsi != null) {
			sum_add = (Double) fsi.getSum();
		}
		// 总减少
		fsi = hyJfSolrServer.findSubByCday(owner, type, cday, start, rows).getFsi();
		if (fsi != null) {
			sum_sub = (Double) fsi.getSum();
		}

		return SUCCESS;
	}

	@Permission(module = IPrivilegeConstants.MODULE_APP_微积分, privilege = IPrivilegeConstants.PERMISSION_R)
	public String jf() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (startTime == null || endTime == null) {
			startTime = endTime = sdf.format(new Date());
		}

		long owner = this.getOwner();
		String type = "JF";
		String cday = getCday(startTime, endTime);
		int rows = 10;
		int start = (pageId - 1) * rows;
		SolrDataDto sdd = hyJfSolrServer.findByCday(owner, type, cday, start, rows);
		SolrDocumentList docList = sdd.getDocumentList();
		Calendar cal = Calendar.getInstance();
		for (int i = 0; i < docList.size(); i++) {
			SolrDocument doc = docList.get(i);
			Date created = (Date) doc.get("created");
			cal.setTime(created);
			cal.add(Calendar.HOUR_OF_DAY, -8);
			doc.setField("created", cal.getTime());
		}
		dto = new SettingDto();
		dto.setDocList(docList);
		dto.setPager(new Pager(pageId, sdd.getTotal(), rows));
		// 总增长
		FieldStatsInfo fsi = hyJfSolrServer.findAddByCday(owner, type, cday, start, rows).getFsi();
		if (fsi != null) {
			sum_add = (Double) fsi.getSum();
		}
		// 总减少
		fsi = hyJfSolrServer.findSubByCday(owner, type, cday, start, rows).getFsi();
		if (fsi != null) {
			sum_sub = (Double) fsi.getSum();
		}

		return SUCCESS;
	}

	private static String getCday(String startTime, String endTime) {
		String sday = "*";
		String eday = "*";
		if (StringUtils.isNotBlank(startTime)) {
			sday = startTime.replaceAll("-", "");
		}
		if (StringUtils.isNotBlank(endTime)) {
			eday = endTime.replaceAll("-", "");
		}

		if (sday.equals(eday)) {
			return sday;
		} else {
			return "[ " + sday + " TO " + eday + " ]";
		}
	}

	public void setHyJfSolrServer(HyJfSolrServer hyJfSolrServer) {
		this.hyJfSolrServer = hyJfSolrServer;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public SettingDto getDto() {
		return dto;
	}

	public void setDto(SettingDto dto) {
		this.dto = dto;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Double getSum_add() {
		return sum_add;
	}

	public void setSum_add(Double sum_add) {
		this.sum_add = sum_add;
	}

	public Double getSum_sub() {
		return sum_sub;
	}

	public void setSum_sub(Double sum_sub) {
		this.sum_sub = sum_sub;
	}

}
