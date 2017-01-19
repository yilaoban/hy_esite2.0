package com.huiyee.interact.template.dao;

import java.util.List;

import com.huiyee.interact.template.model.WxTemplateIndustry;

public interface IWxTemplateIndustryDao {

	public List<WxTemplateIndustry> getFirst_class();

	public List<WxTemplateIndustry> getSecond_class(String first_class);

}
