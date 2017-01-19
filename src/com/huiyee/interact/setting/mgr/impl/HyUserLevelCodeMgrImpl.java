package com.huiyee.interact.setting.mgr.impl;

import java.util.List;

import com.huiyee.interact.setting.dao.IHyUserLevelCodeDao;
import com.huiyee.interact.setting.mgr.IHyUserLevelCodeMgr;
import com.huiyee.interact.setting.model.HyUserLevelCode;

public class HyUserLevelCodeMgrImpl implements IHyUserLevelCodeMgr {

	private IHyUserLevelCodeDao hyUserLevelCodeDao;

	public void setHyUserLevelCodeDao(IHyUserLevelCodeDao hyUserLevelCodeDao) {
		this.hyUserLevelCodeDao = hyUserLevelCodeDao;
	}

	@Override
	public int findCodeCount(HyUserLevelCode code) {
		return hyUserLevelCodeDao.findCodeCount(code);
	}

	@Override
	public List<HyUserLevelCode> findCodeList(HyUserLevelCode code, int start, int rows) {
		return hyUserLevelCodeDao.findCodeList(code, start, rows);
	}

	@Override
	public int[] addCode(long owner, long levelid, List<String> codes) {
		return hyUserLevelCodeDao.addCode(owner, levelid, codes);
	}

	@Override
	public int updateCode(HyUserLevelCode code) {
		return hyUserLevelCodeDao.updateCode(code);
	}

	@Override
	public int delCode(long id) {
		return hyUserLevelCodeDao.delCode(id);
	}

}
