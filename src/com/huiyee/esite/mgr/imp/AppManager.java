package com.huiyee.esite.mgr.imp;

import java.util.Map;

import com.huiyee.esite.mgr.IAppManager;
import com.huiyee.interact.cb.mgr.IInteractCbMgr;
import com.huiyee.interact.cb.model.InteractCb;
import com.huiyee.interact.offcheck.mgr.IOffCheckMgr;
import com.huiyee.interact.offcheck.model.OffCheck;
import com.huiyee.interact.yuyue.mgr.IYuYueMgr;
import com.huiyee.interact.yuyue.model.YuYue;

public class AppManager implements IAppManager {
	
	private IOffCheckMgr offCheckMgr;
	private IYuYueMgr yuYueMgr;
	private IInteractCbMgr iInteractCbMgr;
	
	public IInteractCbMgr getiInteractCbMgr()
	{
		return iInteractCbMgr;
	}

	
	public void setiInteractCbMgr(IInteractCbMgr iInteractCbMgr)
	{
		this.iInteractCbMgr = iInteractCbMgr;
	}

	public void setYuYueMgr(IYuYueMgr yuYueMgr)
	{
		this.yuYueMgr = yuYueMgr;
	}

	public void setOffCheckMgr(IOffCheckMgr offCheckMgr)
	{
		this.offCheckMgr = offCheckMgr;
	}

	@Override
	public Object findAppObjectByAppid(long ownerid, long appid)
	{
		if(appid == 2){
			//合伙人
			InteractCb cb = iInteractCbMgr.findCbbyOwner(ownerid);
			if(cb != null){
				return cb.getApt();
			}
		}
		if(appid == 6){
			//线下签到
			OffCheck oc = offCheckMgr.findStoreCrmByOwner(ownerid);
			if(oc != null){
				return oc.getApt();
			}
		}
		if(appid == 7){
			//微预约
			YuYue yy = yuYueMgr.findYuYueByOwner(ownerid);
			if(yy != null){
				return yy.getApt();
			}
		}
		return null;
	}
	
	@Override
	public long findAppUsedPageid(long ownerid, int appid)
	{
		if(appid == 2){
			//合伙人
			InteractCb cb = iInteractCbMgr.findCbbyOwner(ownerid);
			if(cb != null){
				return cb.getSpageid();
			}
		}
		return 0;
	}
	
	@Override
	public int updateUsedSiteGroup(long owner, int appid, Map<String, Long> map)
	{
		if(appid == 2){
			return iInteractCbMgr.updateUsedSiteGroup(owner,map);
		}
		return 0;
	}
}
