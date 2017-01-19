package com.huiyee.esite.action;

import com.google.gson.Gson;
import com.huiyee.esite.dto.CardDto;
import com.huiyee.esite.dto.PageTemplateDto;
import com.huiyee.esite.dto.TemplateDto;
import com.huiyee.esite.dto.TemplateSiteDto;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
public class TemplateSiteManagerAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8162606211171895936L;
	/**
	 * 
	 */
	private CardDto dto;
	private long categoryid = 0;
	private String type;
	private int pageId=1;
	private PageTemplateDto pdto;
	@Override
	public String execute() throws Exception {
		dto = (CardDto) pageCompose.findTemplateSiteIndex(categoryid,type);
		if(categoryid == 0){
			categoryid = dto.getCategorylist().get(0).getId();
		}
		return SUCCESS;
	}
	/**
	 * 
	 * @return
	 * @throws Exception
	 
	public String getTempleteCards() throws Exception{
		dto = (TemplateSiteDto) pageCompose.findTemplateSiteIndex(categoryid,type,pageId);
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
	}*/
	
	public String findPageTemplate()throws Exception{
		dto = (CardDto) pageCompose.findTemplateSiteIndex(categoryid,type);
		if(categoryid == 0){
			categoryid = dto.getCategorylist().get(0).getId();
		}
		pdto=(PageTemplateDto) pageCompose.findPageTemplate();
		return SUCCESS;
	}
	
	public long getCategoryid()
	{
		return categoryid;
	}
	public void setCategoryid(long categoryid)
	{
		this.categoryid = categoryid;
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
	public CardDto getDto()
	{
		return dto;
	}
	public PageTemplateDto getPdto()
	{
		return pdto;
	}
	public void setPdto(PageTemplateDto pdto)
	{
		this.pdto = pdto;
	}
	
	
	
}
