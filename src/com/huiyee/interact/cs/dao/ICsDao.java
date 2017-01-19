package com.huiyee.interact.cs.dao;

import java.util.List;

import com.huiyee.interact.cs.model.ChuanSan;

public interface ICsDao
{
	public int findTotalByOwnerId(long ownerid, long omid);

	public long save(ChuanSan cs, long ownerid, long omid);

	public int update(ChuanSan rq, long id, long ownerid);

	public List<ChuanSan> findListByOwner(long ownerid, int i, int interactCsLimit, long omid);

	public ChuanSan findById(long id, long ownerid);

	public int updateCsJcontent(long id, long owner, String jsonStr);

}
