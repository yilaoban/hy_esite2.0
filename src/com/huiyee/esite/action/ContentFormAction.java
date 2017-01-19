package com.huiyee.esite.action;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.ContentFormDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.ContentForm;
import com.huiyee.esite.model.ContentFormMapping;
import com.huiyee.esite.model.ContentFormRecord;
import com.opensymphony.xwork2.ActionContext;

/**
 * 内容管理-自定义表单
 * 
 * @author Administrator
 * 
 */
public class ContentFormAction extends AbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String typeName;// 自定义表单名称
	private String result;
	private ContentFormDto dto;
	private long ccid;
	private long typeid;
	private long formid;
	private String x;
	private String y;
	private String tags;
	private int pagesize;
	private ContentForm contentform;
	private List<ContentFormMapping> newList;
	private ContentFormRecord record;
	private long recordid;

	public String addType() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		int rs = pageCompose.addContentType(account, typeName, IPageConstants.CONTENT_FORM);
		result = rs > 0 ? "Y" : "N";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(result));
		out.flush();
		out.close();
		return null;
	}

	public String index()
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (ContentFormDto) pageCompose.composeContentForm(account.getOwner().getId(), ccid, typeid);
		return SUCCESS;
	}

	public String updateIndex()
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (ContentFormDto) pageCompose.composeUpdateContentForm(account.getOwner().getId(), formid);
		return SUCCESS;
	}

	public String updateSub() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		int rs = pageCompose.updateContentFormSub(account, contentform, newList);
		result = rs > 0 ? "Y" : "N";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(result));
		out.flush();
		out.close();
		return null;

	}
	
	public String showForms() throws Exception
	{
		List<Map<String, String>> rs = pageCompose.findRecordByPoint(x, y, tags, pagesize);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(rs));
		out.flush();
		out.close();
		return null;

	}
	
	public String showOneForm() throws Exception
	{
		Map<String, String> rs = pageCompose.findRecordId(recordid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(rs));
		out.flush();
		out.close();
		return null;

	}

	public void setX(String x) {
		this.x = x;
	}

	public void setY(String y) {
		this.y = y;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public String addFormIndex() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (ContentFormDto) pageCompose.addContentFormIndex(account, formid);
		return SUCCESS;
	}

	public String addFormSub() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		int rs = pageCompose.addContentFormSub(record,account);
		return SUCCESS;
	}

	public String delRecord() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		int rs = pageCompose.deletFormRecord(account, formid, recordid);
		result = rs > 0 ? "Y" : "N";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(result));
		out.flush();
		out.close();
		return null;

	}
	
	public String editRecord() throws Exception{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (ContentFormDto) pageCompose.findContentFormRecord(account, formid, recordid);
		return SUCCESS;
	}
	
	public String editRecordSub() throws Exception{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		int rs = pageCompose.editContentFormSub(record,account);
		return SUCCESS;
	}

	public String recordDdetail() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (ContentFormDto) pageCompose.findContentFormRecord(account, formid, recordid);
		return SUCCESS;
	}

	public String getTypeName()
	{
		return typeName;
	}

	public void setTypeName(String typeName)
	{
		this.typeName = typeName;
	}

	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}

	public ContentFormDto getDto()
	{
		return dto;
	}

	public void setDto(ContentFormDto dto)
	{
		this.dto = dto;
	}

	public long getCcid()
	{
		return ccid;
	}

	public void setCcid(long ccid)
	{
		this.ccid = ccid;
	}

	public long getTypeid()
	{
		return typeid;
	}

	public void setTypeid(long typeid)
	{
		this.typeid = typeid;
	}

	public long getFormid()
	{
		return formid;
	}

	public void setFormid(long formid)
	{
		this.formid = formid;
	}

	public ContentForm getContentform()
	{
		return contentform;
	}

	public void setContentform(ContentForm contentform)
	{
		this.contentform = contentform;
	}

	public List<ContentFormMapping> getNewList()
	{
		return newList;
	}

	public void setNewList(List<ContentFormMapping> newList)
	{
		this.newList = newList;
	}

	public ContentFormRecord getRecord()
	{
		return record;
	}

	public void setRecord(ContentFormRecord record)
	{
		this.record = record;
	}

	public long getRecordid()
	{
		return recordid;
	}

	public void setRecordid(long recordid)
	{
		this.recordid = recordid;
	}

}
