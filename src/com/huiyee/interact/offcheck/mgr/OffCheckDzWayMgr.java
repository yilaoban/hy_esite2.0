package com.huiyee.interact.offcheck.mgr;

import com.huiyee.esite.mgr.imp.AbstractMgr;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.offcheck.dao.IOffCheckDzWayDao;
import com.huiyee.interact.offcheck.dto.CheckRs;


public class OffCheckDzWayMgr extends AbstractMgr implements IOffCheckDzWayMgr
{
	private IOffCheckDzWayDao offCheckDzWayDao;
	
	public void setOffCheckDzWayDao(IOffCheckDzWayDao offCheckDzWayDao)
	{
		this.offCheckDzWayDao = offCheckDzWayDao;
	}

	@Override
	public CheckRs updateDzWay(VisitUser vu, int rmb, long owner, long csid)
	{
		CheckRs rs=new CheckRs();
		if(vu==null||vu.getWxUser()==null){
			rs.setStatus(-1);
			rs.setHydesc("用户不存在！");
			return rs;	
		}
		if(!this.isDz(owner, "t1", vu.getHyUserId()))
		{
			rs.setStatus(-10005);
			rs.setHydesc("没有收营员权限！");
			return rs;	
		}
		long id=offCheckDzWayDao.findDzWayId(vu.getWxuid(), csid, rmb);
		if(id==0)
		{
			offCheckDzWayDao.addDzWay(vu.getWxuid(), csid, rmb);
		}
		id=offCheckDzWayDao.findDzWayId(vu.getWxuid(), csid, rmb);
		rs.setStatus(1);
		rs.setDzway(id);
		return rs;
	}
}
