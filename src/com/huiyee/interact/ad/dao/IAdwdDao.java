package com.huiyee.interact.ad.dao;

import java.util.List;

import com.huiyee.interact.ad.model.Adwd;


public interface IAdwdDao
{

	public List<Adwd> findListByOwner(long owner, String wd, String type);

	public Adwd findWdByName(long owner, String name, String type);

	public long saveWd(String name, long owner, String type);

}
