package com.huiyee.interact.offcheck.dao;

import java.util.List;

import com.huiyee.interact.offcheck.model.OffCheckSource;


public interface IOffCheckSourceDao
{

	public int findSourceTotalByOwner(long id);

	public List<OffCheckSource> findSourceByOwner(long id, int i, int offCheckLimit);

	public long saveOffCheckSource(OffCheckSource ofcSource);

	public OffCheckSource findOffCheckSourceById(long sourceid, long owner);

	public int updateOffCheckSource(OffCheckSource ofcSource);

	public long delSource(long sourceid, long owner);

	public List<OffCheckSource> findSourceByOwner(long id);

}
