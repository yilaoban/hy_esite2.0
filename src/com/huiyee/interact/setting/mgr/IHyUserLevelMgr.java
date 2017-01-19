package com.huiyee.interact.setting.mgr;

import java.util.List;

import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.setting.model.HyUserLevel;

public interface IHyUserLevelMgr {
	
	public List<HyUserLevel> findHyUserLevelByOwner(long owner);
	
	public int saveUserLevel(HyUserLevel level,long owner,int hylevel);
	
	public HyUserLevel findHyUserLevelById(long id,long owner);
	
	public int updateUserLevelById(HyUserLevel level,int hylevel);
	
	public int delUserLevel(long id,long owner);
	
	public int updateHyUserByHyuid(long hyuid,long levelid);
	
	public void updateUserLevel(VisitUser vu);
	
	public void updateUserLevel(VisitUser vu,int rmb);
}
