package com.huiyee.interact.appointment.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.dao.IContentCategoryDao;
import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.ContentFormRecord;
import com.huiyee.interact.appointment.mgr.IInteractAptManager;

/**
 * 预约需要的自定义表单数据
 * 
 * @author ldw
 *
 */
public class AptAction extends AbstractAction {
	private long catid;
	private IInteractAptManager aptManager;

	public String getCategoryList() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		List<ContentCategory> list = null;
		if (catid != 0) {
			System.out.println(catid+"--"+this.getOwner());
			list = aptManager.findNextCategoryByCcid(catid, this.getOwner());
		}
		out.print(new Gson().toJson(list));
		out.flush();
		out.close();
		return null;
	}

	public String getFormRecord() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		List<ContentFormRecord> list = null;
		if (catid != 0) {
			list = aptManager.findFormRecordByCatid(catid);
		}
		out.print(new Gson().toJson(list));
		out.flush();
		out.close();
		return null;

	}

	public long getCatid() {
		return catid;
	}

	public void setCatid(long catid) {
		this.catid = catid;
	}

	public void setAptManager(IInteractAptManager aptManager) {
		this.aptManager = aptManager;
	}
}
