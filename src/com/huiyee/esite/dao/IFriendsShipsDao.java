package com.huiyee.esite.dao;

import com.huiyee.esite.model.SinaToken;

public interface IFriendsShipsDao
{

	public SinaToken findToken(long pageid, long wbuid);

	public void addRecord(long pageid, long wbuid, long cid);

}
