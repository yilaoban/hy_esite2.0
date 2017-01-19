package com.huiyee.interact.template.mgr.impl;

import java.util.List;

import com.huiyee.interact.template.dao.IWxTemplateJobDao;
import com.huiyee.interact.template.mgr.IWxTemplateJobMgr;
import com.huiyee.interact.template.model.WxTemplateJob;

public class WxTemplateJobMgrImpl implements IWxTemplateJobMgr {

	private IWxTemplateJobDao wxTemplateJobDao;

	public void setWxTemplateJobDao(IWxTemplateJobDao wxTemplateJobDao) {
		this.wxTemplateJobDao = wxTemplateJobDao;
	}

	@Override
	public int getJobCount(long mpid) {
		return wxTemplateJobDao.getJobCount(mpid);
	}

	@Override
	public int getJobCount(long mpid, String type, long entityid) {
		return wxTemplateJobDao.getJobCount(mpid, type, entityid);
	}

	@Override
	public int getJobCount(long mpid, List<String> types, long entityid) {
		return wxTemplateJobDao.getJobCount(mpid, types, entityid);
	}

	@Override
	public List<WxTemplateJob> getJobList(long mpid, int start, int rows) {
		return wxTemplateJobDao.getJobList(mpid, start, rows);
	}

	@Override
	public List<WxTemplateJob> getJobList(long mpid, String type, long entityid, int start, int rows) {
		return wxTemplateJobDao.getJobList(mpid, type, entityid, start, rows);
	}

	@Override
	public List<WxTemplateJob> getJobList(long mpid, List<String> types, long entityid, int start, int rows) {
		return wxTemplateJobDao.getJobList(mpid, types, entityid, start, rows);
	}

}
