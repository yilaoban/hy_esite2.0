package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.PageTemplate;
import com.huiyee.esite.model.TemplateModel;

public class PageTemplateDto implements IDto{

	private List<TemplateModel> list;
	private List<PageTemplate> plist;

	public List<TemplateModel> getList() {
		return list;
	}

	public void setList(List<TemplateModel> list) {
		this.list = list;
	}

	public List<PageTemplate> getPlist()
	{
		return plist;
	}

	public void setPlist(List<PageTemplate> plist)
	{
		this.plist = plist;
	}


}
