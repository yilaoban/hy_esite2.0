package com.huiyee.interact.template.mgr.impl;

import java.util.List;

import com.huiyee.interact.template.dao.IWxTemplateDao;
import com.huiyee.interact.template.mgr.IWxTemplateMgr;
import com.huiyee.interact.template.model.WxTemplate;

public class WxTemplateMgrImpl implements IWxTemplateMgr {

	private IWxTemplateDao wxTemplateDao;

	public void setWxTemplateDao(IWxTemplateDao wxTemplateDao) {
		this.wxTemplateDao = wxTemplateDao;
	}

	@Override
	public int getTemplateCount(long owner) {
		return wxTemplateDao.getTemplateCount(owner);
	}

	@Override
	public int getTemplateCount(long owner, String type, long entityid) {
		return wxTemplateDao.getTemplateCount(owner, type, entityid);
	}

	@Override
	public int getTemplateCount(long mpid, String template_id) {
		return wxTemplateDao.getTemplateCount(mpid, template_id);
	}

	@Override
	public List<WxTemplate> getTemplateList(long owner, int start, int rows) {
		return wxTemplateDao.getTemplateList(owner, start, rows);
	}

	@Override
	public List<WxTemplate> getTemplateList(long owner, String type, long entityid, int start, int rows) {
		return wxTemplateDao.getTemplateList(owner, type, entityid, start, rows);
	}

	@Override
	public WxTemplate getTemplate(long id) {
		return wxTemplateDao.getTemplate(id);
	}

	@Override
	public WxTemplate getTemplate(long owner, String type) {
		List<WxTemplate> list = wxTemplateDao.getTemplateList(owner, type);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public WxTemplate getTemplate(long owner, String type, long entityid) {
		List<WxTemplate> list = wxTemplateDao.getTemplateList(owner, type, entityid);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public WxTemplate getTemplate(long mpid, long store_id) {
		return wxTemplateDao.getTemplate(mpid, store_id);
	}

	@Override
	public int addTemplate(WxTemplate wt) {
		return wxTemplateDao.addTemplate(wt);
	}

	@Override
	public int[] addTemplate(List<WxTemplate> list) {
		return wxTemplateDao.addTemplate(list);
	}

	@Override
	public int updateTemplate(WxTemplate wt) {
		return wxTemplateDao.updateTemplate(wt);
	}

	@Override
	public int delTemplate(long id) {
		return wxTemplateDao.delTemplate(id);
	}

	@Override
	public int delAllTemplate(long owner) {
		return wxTemplateDao.delAllTemplate(owner);
	}

}
