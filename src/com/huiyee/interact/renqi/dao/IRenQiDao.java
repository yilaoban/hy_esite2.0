package com.huiyee.interact.renqi.dao;

import java.util.List;

import com.huiyee.interact.renqi.model.RenQi;

public interface IRenQiDao
{
	public RenQi findRenQiById(long id);

	public int findTotalByOwnerId(long ownerid);

	public List<RenQi> findRenQiListByOwner(long ownerid, int start, int size);

	public long saveRenQi(RenQi rq, long ownerid);

	public int updateRenQi(RenQi rq, long id, long ownerid);

	public int updateStatus(long id, String status, long owner);

}