package com.huiyee.interact.setting.dao;

import java.util.List;

import com.huiyee.interact.setting.model.HyUserLevel;

public interface IHyUserLevelDao {
	public List<HyUserLevel> findHyUserLevelByOwner(long owner);
	
	public HyUserLevel findHyUserLevelByOwnerAndRMB(long owner,int rmb,int hylevel,int totalRmb,int rmbused);
	
	public int saveUserLevel(HyUserLevel level,long owner,int hylevel);
	
	public HyUserLevel findHyUserLevelById(long id, long owner);
	
	public int updateUserLevelById(HyUserLevel level,int hylevel);
	
	public int delUserLevel(long id, long owner);
	
	public int updateHyUserByHyuid(long hyuid, long levelid);

}
