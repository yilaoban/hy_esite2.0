package com.huiyee.interact.setting.dao;

import java.util.List;

import com.huiyee.interact.setting.model.HyUserLevel;
import com.huiyee.interact.setting.model.HyUserXfDesc;
import com.huiyee.interact.setting.model.HyUserXfZk;

public interface IHyUserXfDescDao {

	public int findXfDescCount(long owner);

	public List<HyUserXfDesc> findXfDescList(long owner, int start, int rows);
	
	public List<HyUserXfDesc> findXfDescList(long owner);

	public int addXfDesc(HyUserXfDesc xd);

	public int updateXfDesc(HyUserXfDesc xd);

	public int deleteXfDesc(long id);
	
	public List<HyUserLevel> findHyUserLevel(long owner);
	
	public HyUserXfZk findXfZkListByXfid(long owner,long levelid,long xfid);
	
	public int savezk(long levelid, long xfid, int zk, long owner);
	
	public HyUserXfDesc findXfdescByXfid(long xfid);

}
