package com.huiyee.interact.offcheck.dao;

import com.huiyee.interact.offcheck.model.OffCheck;


public interface IOffCheckDao
{

	public OffCheck findStoreCrmByOwner(long owner);
	
	public int findUserAptRecord(long owner,long wxuid);

	public long saveScrm(OffCheck crm);

}
