package com.huiyee.esite.mgr.imp;

import java.util.List;

import com.huiyee.esite.dao.IVisitLogDao;
import com.huiyee.esite.dto.VisitLogDto;
import com.huiyee.esite.dto.VisitPageTime;
import com.huiyee.esite.mgr.IVisitLogMgr;

public class VisitLogMgr implements IVisitLogMgr
{
	private IVisitLogDao visitLogDao;

	public void setVisitLogDao(IVisitLogDao visitLogDao)
	{
		this.visitLogDao = visitLogDao;
	}

	@Override
	public void addLogs(List<VisitLogDto> vls)
	{
		visitLogDao.addLogs(vls);
	}

	@Override
	public void addVpts(List<VisitPageTime> vls)
	{
		visitLogDao.addVpts(vls);
	}
}
