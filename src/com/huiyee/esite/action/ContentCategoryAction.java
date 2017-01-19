
package com.huiyee.esite.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.compose.IPageCompose;
import com.huiyee.esite.dto.ChooseContentDto;
import com.huiyee.esite.dto.ContentDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.ContentCategory;
import com.opensymphony.xwork2.ActionContext;

public class ContentCategoryAction extends AbstractAction
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7298900096238228796L;
	private ChooseContentDto dto ;
	private long ccid;
	private int pageId=1;
	private String inputid;
	private int size;
	
	public int getSize()
	{
		return size;
	}
	
	public void setSize(int size)
	{
		this.size = size;
	}


	public String getInputid()
	{
		return inputid;
	}

	
	public void setInputid(String inputid)
	{
		this.inputid = inputid;
	}

	public int getPageId()
	{
		return pageId;
	}
	
	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public long getCcid()
	{
		return ccid;
	}

	public void setCcid(long ccid)
	{
		this.ccid = ccid;
	}

	public ChooseContentDto getDto()
	{
		return dto;
	}
	
	public void setDto(ChooseContentDto dto)
	{
		this.dto = dto;
	}
	@Override
	public String execute() throws Exception{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (ChooseContentDto) pageCompose.chooseContent(account);
		return SUCCESS;
	}
	
	public String content() throws Exception{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		ContentDto dto = (ContentDto) pageCompose.findContentByCcid(ccid, account.getOwner().getId(),pageId,IPageCompose.CONTENT_MOUNTS);
		Gson gson = new Gson();
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}
	
	public String usercontent() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		ContentDto dto = (ContentDto) pageCompose.findContentByCcid(ccid, this.getOwner(),pageId,size);
		Gson gson = new Gson();
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}
	
	public String getCategoryList() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		List<ContentCategory> list = null;
		if (ccid != 0) {
			list = pageCompose.findCategoryByFatherCcid(ccid, this.getOwner());
		}
		out.print(new Gson().toJson(list));
		out.flush();
		out.close();
		return null;
	}
	
}
