package com.huiyee.interact.setting.dao;

import java.util.List;

import com.huiyee.interact.setting.model.HyUserLevelCode;

public interface IHyUserLevelCodeDao {

	public int findCodeCount(HyUserLevelCode code);

	public List<HyUserLevelCode> findCodeList(HyUserLevelCode code, int start, int rows);

	public HyUserLevelCode findCode(long owner, String code);

	public int[] addCode(long owner, long levelid, List<String> codes);

	public int updateStatus(long id);

	public int updateCode(HyUserLevelCode code);

	public int delCode(long id);

}
