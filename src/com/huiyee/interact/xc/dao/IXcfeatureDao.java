package com.huiyee.interact.xc.dao;

import java.util.List;

import com.huiyee.esite.model.Sitegroup;
import com.huiyee.interact.xc.model.HdEntity;

public interface IXcfeatureDao
{
	public long addXcfeature(long xcid, int featureid, long entityid, String pages);

	public List<HdEntity> findXcEntitys(long xcid);

	public int deletfeature(int featureid,long entityid);

	public HdEntity findVoteValue(long entityid);

	public HdEntity findLotteryValue(long entityid);

	public List<Sitegroup> findGroupByType();

	public int updatehdstart(long hdid, long ownerid);
	
	public String findXcfeaturePages(long xcid, int interactid, long hdid);

	public int updatehdEnd(long lid, long id);

	public int updateLotteryClean(long lid);

	public int updateVoteClean(long lid);

}
