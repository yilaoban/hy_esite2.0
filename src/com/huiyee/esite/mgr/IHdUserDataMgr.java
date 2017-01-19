package com.huiyee.esite.mgr;

import com.huiyee.esite.model.HdUserData;

public interface IHdUserDataMgr {

	//public HdUserData findUserData(long uid, int utype, long hdid, long featureid);
	
	public int findUserDayNum(long uid, int utype, long hdid, long featureid);
	
	public int findUserTotal(long uid, int utype, long hdid, long featureid);

	public int addUserData(long uid, int utype, long hdid, long featureid);

}
