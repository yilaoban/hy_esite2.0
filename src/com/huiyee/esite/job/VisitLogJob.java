package com.huiyee.esite.job;

import java.util.List;

import com.huiyee.esite.dto.VisitLogDto;
import com.huiyee.esite.dto.VisitPageTime;
import com.huiyee.esite.mgr.IVisitLogMgr;
import com.huiyee.esite.util.CacheUtil;
import com.huiyee.esite.util.HyConfig;

public class VisitLogJob
{
   private IVisitLogMgr visitLogMgr;
	public void execute()
	{
		if (!HyConfig.isRun())
		{
			try
			{
				dowork();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	private void dowork()
	{
		List<VisitLogDto> vls = CacheUtil.findVisitLog();
		if (vls != null && vls.size() > 0)
		{
			visitLogMgr.addLogs(vls);
		}
		List<VisitPageTime> vpts=CacheUtil.findvpt();
		if(vpts!=null&&vpts.size()>0)
		{
			visitLogMgr.addVpts(vpts);
		}
	}

	public void setVisitLogMgr(IVisitLogMgr visitLogMgr)
	{
		this.visitLogMgr = visitLogMgr;
	}

}
