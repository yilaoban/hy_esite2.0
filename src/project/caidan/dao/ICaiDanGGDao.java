package project.caidan.dao;

import java.util.List;

import project.caidan.model.TfGG;

import com.huiyee.interact.ad.model.Adgg;


public interface ICaiDanGGDao
{
	public long findGGByGGid(long ggid,long owner, int type);
	
	public int findGGMaxIdx(long owner, int type);
	
	public long saveTfGG(long ggid, long owner,int idx, int type);
	
	public List<Long> findGGidsByOwner(long owner);
	
	public List<Adgg> findtfGGsByOwner(long owner, int type);
	
	public List<Adgg> findtfGGsByOwner(long owner, int type, int start, int size);
	
	public int findTotalTfGGSByOwenr(long owner, int type);
	
	public TfGG findGGById(long id,long owner);
	
	public TfGG findFrontGG(long owner,long idx, int type);
	
	public TfGG findNextGG(long owner,long idx, int type);
	
	public int updateGGIdx(long id,int idx);
	
	public long delTfGGById(long id, long owner);
	
	public long delTfGGByGGId(long ggid, long owner);
}
