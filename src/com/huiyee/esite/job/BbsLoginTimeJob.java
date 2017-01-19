package com.huiyee.esite.job;

import java.util.List;
import com.huiyee.esite.dto.BbsUserLoginTime;
import com.huiyee.esite.util.CacheUtil;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.interact.bbs.mgr.IBbsUserLoginMgr;

public class BbsLoginTimeJob
{
	private IBbsUserLoginMgr bbsUserLoginMgr;

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
		List<BbsUserLoginTime> vls = CacheUtil.findBbs();
		if (vls != null && vls.size() > 0)
		{
			for (BbsUserLoginTime bbsUserLoginTime : vls)
			{
				bbsUserLoginMgr.updateLoginTime(bbsUserLoginTime);
			}
		}
		CacheUtil.removeBbs(vls);
	}

	public void setBbsUserLoginMgr(IBbsUserLoginMgr bbsUserLoginMgr)
	{
		this.bbsUserLoginMgr = bbsUserLoginMgr;
	}

}
