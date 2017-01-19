package com.huiyee.interact.setting.mgr;

import java.util.List;

import com.huiyee.interact.setting.dto.SettingDto;
import com.huiyee.interact.setting.model.HyUserXfDesc;

public interface IHyUserXfDescMgr {

	public int findXfDescCount(long owner);

	public List<HyUserXfDesc> findXfDescList(long owner, int start, int rows);
	
	public List<HyUserXfDesc> findXfDescList(long owner);

	public int addXfDesc(HyUserXfDesc xd);

	public int updateXfDesc(HyUserXfDesc xd);

	public int deleteXfDesc(long id);
	
	public SettingDto findXfZkListByXfid(long owner,long xfid);
	
	public int savezk(long levelid,long xfid,int zk,long owner);
	
	public HyUserXfDesc findXfdescByXfid(long xfid);
	
	public int findzkrmbByXfidAndLevelid(long owner,long xfid,long levelid,int rmb);

}
