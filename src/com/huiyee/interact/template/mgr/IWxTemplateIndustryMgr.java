package com.huiyee.interact.template.mgr;

import java.util.List;

import com.huiyee.interact.template.model.WxTemplateIndustry;

public interface IWxTemplateIndustryMgr {
	
	public List<WxTemplateIndustry> getFirst_class();

	public List<WxTemplateIndustry> getSecond_class(String first_class);

}
