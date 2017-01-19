package com.huiyee.esite.action;

import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.dto.EditPGDto;

public class EditGPAction extends AbstractAction {
	
	private long pageid;
	private EditPGDto dto;
	private String json;
	
	@Override
	public String execute() throws Exception {
		dto = (EditPGDto) pageCompose.composeEditPg(pageid);
		return SUCCESS;
	}
	
	public String save() throws Exception{
		json = json.replaceAll ("\\\\r\\\\n", "");;
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("text/html; charset=utf-8");
		int result = pageCompose.composeSavePg(pageid, json);
		PrintWriter pw = response.getWriter();
		pw.print(result);
		pw.flush();
		pw.close();
		return null;
	}

	public long getPageid() {
		return pageid;
	}

	public void setPageid(long pageid) {
		this.pageid = pageid;
	}

	public EditPGDto getDto() {
		return dto;
	}

	public void setDto(EditPGDto dto) {
		this.dto = dto;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}
}
