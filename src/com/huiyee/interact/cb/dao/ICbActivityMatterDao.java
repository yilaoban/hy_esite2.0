package com.huiyee.interact.cb.dao;

import java.util.List;

import com.huiyee.interact.cb.model.CbActivityMatter;

public interface ICbActivityMatterDao {

	public List<CbActivityMatter> findPageidByAid(long aid);
	
	public List<CbActivityMatter> findPageByAid(long aid);

	public List<CbActivityMatter> findWxPageShowByAid(long cbaid);
	
}
