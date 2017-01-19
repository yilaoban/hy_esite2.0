package com.huiyee.interact.ad.dao;

import com.huiyee.esite.model.Page;
import com.huiyee.interact.ad.model.Ad;


public interface IAdDao
{

	public Ad findAdByOwner(long id);

	public int saveAd(long id);

	public Page findPageByApptypeAndGroupId(long gid, String aPPTYPE_ADD);

}
