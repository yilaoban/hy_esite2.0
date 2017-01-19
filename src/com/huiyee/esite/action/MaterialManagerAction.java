package com.huiyee.esite.action;

import com.google.gson.Gson;
import com.huiyee.esite.dto.MaterialDto;
import com.huiyee.esite.dto.TemplateDto;
import com.huiyee.esite.dto.TemplateSiteDto;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
public class MaterialManagerAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8162606211171895936L;
	/**
	 * 
	 */
	private MaterialDto dto;
	private long fcategoryid = 0;
	private long scategoryid=0;
	private String type;
	private int pageId=1;
	private String key;
	private int count=0;
	@Override
	public String execute() throws Exception {
		dto = (MaterialDto) pageCompose.findMaterialIndex(fcategoryid,scategoryid,pageId);
		fcategoryid=dto.getFid();
		scategoryid=dto.getSid();
		return SUCCESS;
	}
	public String getMaterialPics() throws Exception{
		dto = (MaterialDto) pageCompose.findMaterialIndex(fcategoryid,scategoryid,pageId);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
	    String gsondata = gson.toJson(dto);
		out.print(gsondata);
		out.flush();
		out.close();
		return null;
	}
	
	public String sck() throws Exception{
		dto = (MaterialDto) pageCompose.findMaterialList(fcategoryid,scategoryid,pageId);
		fcategoryid=dto.getFid();
		scategoryid=dto.getSid();
		return SUCCESS;
	}
	
	public String ajax_sck() throws Exception{
		dto = (MaterialDto) pageCompose.findMaterialList(fcategoryid,scategoryid,pageId);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
	    String gsondata = gson.toJson(dto);
		out.print(gsondata);
		out.flush();
		out.close();
		return null;
	}
	
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public int getPageId()
	{
		return pageId;
	}
	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}
	public MaterialDto getDto()
	{
		return dto;
	}
	public long getFcategoryid()
	{
		return fcategoryid;
	}
	public void setFcategoryid(long fcategoryid)
	{
		this.fcategoryid = fcategoryid;
	}
	public long getScategoryid()
	{
		return scategoryid;
	}
	public void setScategoryid(long scategoryid)
	{
		this.scategoryid = scategoryid;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getCount()
	{
		return count;
	}
	public void setCount(int count)
	{
		this.count = count;
	}
}
