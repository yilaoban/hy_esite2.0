package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.MyTempalte;
import com.huiyee.esite.model.TemplateCategory;
import com.huiyee.esite.model.TemplateModel;

public class TemplateDto implements IDto{

	private List<TemplateCategory> categoryList;
	private List<TemplateModel> templateList;
	private List<MyTempalte> myTemplateList;
	private MyTempalte mytemplate;

	public List<TemplateCategory> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<TemplateCategory> categoryList) {
		this.categoryList = categoryList;
	}

	public List<TemplateModel> getTemplateList() {
		return templateList;
	}

	public void setTemplateList(List<TemplateModel> templateList) {
		this.templateList = templateList;
	}

	public List<MyTempalte> getMyTemplateList() {
		return myTemplateList;
	}

	public void setMyTemplateList(List<MyTempalte> myTemplateList) {
		this.myTemplateList = myTemplateList;
	}

	public MyTempalte getMytemplate()
	{
		return mytemplate;
	}

	public void setMytemplate(MyTempalte mytemplate)
	{
		this.mytemplate = mytemplate;
	}
}
