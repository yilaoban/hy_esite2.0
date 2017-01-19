package com.huiyee.interact.setting.mgr;

import java.util.List;

import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.setting.model.HyUserDz;

public interface IHyUserDzMgr {

	public int findDzCount(long owner, String type);

	public List<HyUserDz> findDzList(long owner, String type, int start, int rows);

	public int findDzByWxuid(long owner, String type, long wxuid);

	public HyUserDz findDzByHyuid(long owner, long hyuid);

	public int findDzByHyuid(long owner, String type, long hyuid);

	public int addDz(HyUserDz dz, VisitUser vu);

	public int updateDz(long id, String type, int type_val);

	public int delDz(long id);

	public HyUser findUser(long id);

	public int updateUser(HyUser user);

}
