package com.huiyee.esite.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.TemplateDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.MyTempalte;
import com.huiyee.esite.model.TemplateStyleModel;
import com.huiyee.esite.service.FileUploadService;

public class TemplateManagerAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7177856731930963950L;
	private TemplateDto dto;
	private long categoryid = 0;
	private String type = "W";
	private List<Long> templates;
	private long id;
	private String name;
	private String result;
	private TemplateStyleModel tsm;
	private String style;
	private File pic;
	private String oldpic;
	private MyTempalte my;
	
	@Override
	public String execute() throws Exception {
		dto = (TemplateDto) pageCompose.composeTemplateIndexAction(categoryid,type);
		if(categoryid == 0){
			categoryid = dto.getCategoryList().get(0).getId();
		}
		return SUCCESS;
	}
	
	public String ljsy() throws Exception{
		long ownerid = ((Account) ServletActionContext.getRequest().getSession().getAttribute("account")).getOwner().getId();
		int result = pageCompose.saveOwnerTemplate(ownerid, templates);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}
	
	public String myTemplate() throws Exception{
		long ownerid = ((Account) ServletActionContext.getRequest().getSession().getAttribute("account")).getOwner().getId();
		dto = (TemplateDto) pageCompose.composeMyTemplateAction(ownerid);
		return SUCCESS;
	}
	
	public String findOneTemplate()throws Exception{
		my= pageCompose.findOneTemplate(id);
		return SUCCESS;
	}
	
	public String deleteTemplate()throws Exception{
		int result=pageCompose.deleteTemplate(id);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}
	
	//ÐÞ¸ÄÃû×Ö
	public String updateNameTemplate()throws Exception{
		int result=0;
		if(name!=null&&!"".equals(name)){
			result=pageCompose.updateNameTemplate(id, name);
		}else{
			result=2;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
        return null;
	}
	
	
	public String updateTemplate()throws Exception{
		int res=pageCompose.updateTemplate(id, style,pic,oldpic);
		if(res>0){
			result="Y";
		}
		return SUCCESS;
	}

	public TemplateDto getDto() {
		return dto;
	}

	public void setDto(TemplateDto dto) {
		this.dto = dto;
	}

	public long getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(long categoryid) {
		this.categoryid = categoryid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setTemplates(List<Long> templates) {
		this.templates = templates;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}

	public TemplateStyleModel getTsm()
	{
		return tsm;
	}

	public void setTsm(TemplateStyleModel tsm)
	{
		this.tsm = tsm;
	}

	public String getStyle()
	{
		return style;
	}

	public void setStyle(String style)
	{
		this.style = style;
	}

	public File getPic()
	{
		return pic;
	}

	public void setPic(File pic)
	{
		this.pic = pic;
	}

	public MyTempalte getMy()
	{
		return my;
	}

	public void setMy(MyTempalte my)
	{
		this.my = my;
	}

	public String getOldpic()
	{
		return oldpic;
	}

	public void setOldpic(String oldpic)
	{
		this.oldpic = oldpic;
	}

	
}
