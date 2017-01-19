package com.huiyee.interact.setting.mgr.impl;

import java.util.List;

import com.huiyee.interact.setting.dao.IHyUserXfDescDao;
import com.huiyee.interact.setting.dto.SettingDto;
import com.huiyee.interact.setting.mgr.IHyUserXfDescMgr;
import com.huiyee.interact.setting.model.HyUserLevel;
import com.huiyee.interact.setting.model.HyUserXfDesc;
import com.huiyee.interact.setting.model.HyUserXfZk;

public class HyUserXfDescMgrImpl implements IHyUserXfDescMgr {

	private IHyUserXfDescDao hyUserXfDescDao;

	public void setHyUserXfDescDao(IHyUserXfDescDao hyUserXfDescDao) {
		this.hyUserXfDescDao = hyUserXfDescDao;
	}

	@Override
	public int findXfDescCount(long owner) {
		return hyUserXfDescDao.findXfDescCount(owner);
	}

	@Override
	public List<HyUserXfDesc> findXfDescList(long owner, int start, int rows) {
		return hyUserXfDescDao.findXfDescList(owner, start, rows);
	}

	@Override
	public int addXfDesc(HyUserXfDesc xd) {
		return hyUserXfDescDao.addXfDesc(xd);
	}

	@Override
	public int updateXfDesc(HyUserXfDesc xd) {
		return hyUserXfDescDao.updateXfDesc(xd);
	}

	@Override
	public int deleteXfDesc(long id) {
		return hyUserXfDescDao.deleteXfDesc(id);
	}

	@Override
	public List<HyUserXfDesc> findXfDescList(long owner) {
		return hyUserXfDescDao.findXfDescList(owner);
	}

	@Override
	public SettingDto findXfZkListByXfid(long owner, long xfid) {
		SettingDto dto = new SettingDto();
		List<HyUserLevel> levelList = hyUserXfDescDao.findHyUserLevel(owner);
		if(levelList.size()>0){
			for(int i=0;i<levelList.size();i++){
				HyUserXfZk hyUserXfZk = hyUserXfDescDao.findXfZkListByXfid(owner,levelList.get(i).getId(),xfid);
				levelList.get(i).setHyUserXfZk(hyUserXfZk);
			}
		}
		dto.setLevelList(levelList);
		return dto;
	}

	@Override
	public int savezk(long levelid, long xfid, int zk, long owner) {
		return hyUserXfDescDao.savezk(levelid, xfid, zk, owner);
	}

	@Override
	public HyUserXfDesc findXfdescByXfid(long xfid) {
		return hyUserXfDescDao.findXfdescByXfid(xfid);
	}

	@Override
	public int findzkrmbByXfidAndLevelid(long owner, long xfid,long levelid, int rmb) {
		HyUserXfZk hyUserXfZk = hyUserXfDescDao.findXfZkListByXfid(owner,levelid,xfid);
		if(hyUserXfZk != null){
			int zk = hyUserXfZk.getZk();
			rmb = rmb * zk / 100;
		}
		return rmb;
	}

}
