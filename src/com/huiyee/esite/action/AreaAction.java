package com.huiyee.esite.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.dto.AreaDto;

public class AreaAction extends AbstractAction{

	private String provinceId;
	
	@Override
	public String execute() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("application/json; charset=utf-8");
		AreaDto dto = (AreaDto) pageCompose.composeProvince();
		PrintWriter pw = response.getWriter();
		pw.print(new Gson().toJson(dto));
		pw.flush();
		pw.close();
		return null;
	}
	
	public String city() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("application/json; charset=utf-8");
		AreaDto dto = (AreaDto) pageCompose.composeCity(provinceId);
		PrintWriter pw = response.getWriter();
		pw.print(new Gson().toJson(dto));
		pw.flush();
		pw.close();
		return null;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
}
