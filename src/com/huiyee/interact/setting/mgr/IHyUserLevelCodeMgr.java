package com.huiyee.interact.setting.mgr;

import java.util.List;

import com.huiyee.interact.setting.model.HyUserLevelCode;

public interface IHyUserLevelCodeMgr {

	public int findCodeCount(HyUserLevelCode code);

	public List<HyUserLevelCode> findCodeList(HyUserLevelCode code, int start, int rows);

	public int[] addCode(long owner, long levelid, List<String> codes);

	public int updateCode(HyUserLevelCode code);

	public int delCode(long id);

}
