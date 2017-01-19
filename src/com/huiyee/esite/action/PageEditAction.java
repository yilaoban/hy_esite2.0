package com.huiyee.esite.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.PageEditDto;
import com.huiyee.esite.dto.PageEditGDto;
import com.huiyee.esite.model.Account;

public class PageEditAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8495876192045841278L;
	private long pageid;
	private IDto dto;
	private String html;
	
	public String editKongBaiPage() throws Exception {
        Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (PageEditDto)pageCompose.composeEditKongBaiPage(pageid,account.getOwner().getId());
		if(dto == null){
			return "error";
		}
		return SUCCESS;
	}
	
	public String editCode() throws Exception{
		html = pageCompose.findHtmlByPage(pageid, "E");
		return SUCCESS;
	}
	
	public String updateCode() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("text/html; charset=utf-8");
		int result = pageCompose.updateCode(pageid,"E",html);
		PrintWriter pw = response.getWriter();
		pw.print(result);
		pw.flush();
		pw.close();
		return null;
	}
	
	public String editPageG() throws Exception{
        Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (PageEditGDto)pageCompose.composeEditPageG(pageid,account.getOwner().getId());
		if(dto == null){
			return "error";
		}
		return SUCCESS;
	}
	
	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public void setDto(PageEditDto dto)
	{
		this.dto = dto;
	}

	public String getHtml()
	{
		return html;
	}

	public void setHtml(String html)
	{
		this.html = html;
	}

	public IDto getDto() {
		return dto;
	}

	public void setDto(IDto dto) {
		this.dto = dto;
	}
}
