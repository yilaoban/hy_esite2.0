package com.huiyee.esite.job;

import java.util.List;
import com.huiyee.esite.mgr.IHyUtilMgr;
import com.huiyee.esite.model.OwnerSetting;
import com.huiyee.esite.util.CacheUtil;
import com.huiyee.weixin.mgr.IWxAppMgr;
import com.huiyee.weixin.model.WxApp;

public class WbCacheJob
{
	private IWxAppMgr wxAppMgr;
	private IHyUtilMgr hyUtilMgr;

	public void setWxAppMgr(IWxAppMgr wxAppMgr)
	{
		this.wxAppMgr = wxAppMgr;
	}

	public void execute()
	{
//		List<WxApp> list = wxAppMgr.getWxapps();
//		if (list != null && list.size() > 0)
//		{
//			for (WxApp app : list)
//			{
//				CacheUtil.third_access_token_map.put(app.getAppid(), app.getAccess_token());
//			}
//		}
//		List<OwnerSetting> ls = hyUtilMgr.findOwnerSetting();
//		if (ls != null && ls.size() > 0)
//		{
//			for (OwnerSetting os : ls)
//			{
//				CacheUtil.oss.put(os.getOwnerid(), os);
//			}
//		}

	}

	public void setHyUtilMgr(IHyUtilMgr hyUtilMgr)
	{
		this.hyUtilMgr = hyUtilMgr;
	}

}
