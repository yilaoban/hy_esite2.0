package com.huiyee.interact.ad.dao;

import java.util.List;

import com.huiyee.interact.ad.model.Adgg;


public interface IAdggDao
{
	public int findTotalAdGGByOwner(long owner);
	
	public List<Adgg> findAdGGListByOwner(long owner,int start,int size);
	
	public int saveGG(Adgg adgg);
	
	public Adgg findadGGById(long ggid);
	
	public int updateGG(Adgg adgg);
	
	public int delGGById(long ggid, long owner);

	public List<Adgg> findAdGGListByOwner(long owner);

	public List<Adgg> findAdGGListByOwnerOrderByStart(long owner, int i, int j);
}
