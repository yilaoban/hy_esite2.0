package project.caidan.mgr;

import java.util.List;

import com.huiyee.interact.ad.dto.IDto;


public interface ICaiDanGGMgr
{
	public long saveTfGG(String ids,long owner, int type);
	
	public List<Long> findGGidsByOwner(long owner);
	
	public IDto findtfGGsByOwner(long owner,int pageid, int type);
	
	public IDto findtfGGsByOwner(long owner, int type);
	
	public long updateGGUp(long id,long owner);
	
	public long updateGGDown(long id,long owenr);
	
	public long delTfGGById(long id,long owenr);
	
	public long delTfGGByGGid(long ggid,long owner, int type);
}
