package com.huiyee.interact.template.mgr.impl;

import java.util.List;

import com.huiyee.interact.template.dao.IWxTemplateIndustryDao;
import com.huiyee.interact.template.mgr.IWxTemplateIndustryMgr;
import com.huiyee.interact.template.model.WxTemplateIndustry;

public class WxTemplateIndustryMgrImpl implements IWxTemplateIndustryMgr {

	private IWxTemplateIndustryDao wxTemplateIndustryDao;

	public void setWxTemplateIndustryDao(IWxTemplateIndustryDao wxTemplateIndustryDao) {
		this.wxTemplateIndustryDao = wxTemplateIndustryDao;
	}

	@Override
	public List<WxTemplateIndustry> getFirst_class() {
		return wxTemplateIndustryDao.getFirst_class();
	}

	@Override
	public List<WxTemplateIndustry> getSecond_class(String first_class) {
		return wxTemplateIndustryDao.getSecond_class(first_class);
	}

}
