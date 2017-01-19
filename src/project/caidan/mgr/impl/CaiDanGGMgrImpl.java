package project.caidan.mgr.impl;

import java.util.List;

import project.caidan.dao.ICaiDanGGDao;
import project.caidan.mgr.ICaiDanGGMgr;
import project.caidan.model.TfGG;

import com.huiyee.esite.dto.Pager;
import com.huiyee.interact.ad.dao.IAdggDao;
import com.huiyee.interact.ad.dto.AdGGDto;
import com.huiyee.interact.ad.dto.IDto;
import com.huiyee.interact.ad.model.Adgg;


public class CaiDanGGMgrImpl implements ICaiDanGGMgr
{
	private ICaiDanGGDao cdGGDao;
	private IAdggDao adGGDao;
	
	public void setAdGGDao(IAdggDao adGGDao)
	{
		this.adGGDao = adGGDao;
	}
	
	public void setCdGGDao(ICaiDanGGDao cdGGDao)
	{
		this.cdGGDao = cdGGDao;
	}

	@Override
	public long saveTfGG(String ids, long owner, int type)
	{
		String[] ggids = ids.split(";");
		for (int i = 0; i < ggids.length; i++)
		{
			long ggid = Long.parseLong(ggids[i]);
			long id = cdGGDao.findGGByGGid(ggid,owner,type);
			if(id == 0){
				int idx = cdGGDao.findGGMaxIdx(owner,type);
				cdGGDao.saveTfGG(ggid,owner,idx + 1,type);
			}
		}
		return 0;
	}

	@Override
	public List<Long> findGGidsByOwner(long owner)
	{
		return cdGGDao.findGGidsByOwner(owner);
	}

	@Override
	public IDto findtfGGsByOwner(long owner,int pageId,int type)
	{
		AdGGDto dto = new AdGGDto();
		int total = cdGGDao.findTotalTfGGSByOwenr(owner,type);
		int size = 20;
		if(total > 0){
			List<Adgg> adGGList = cdGGDao.findtfGGsByOwner(owner,type,(pageId - 1) * size,size);
			if(adGGList.size() > 0){
				dto.setAdGGList(adGGList);
			}
		}
		dto.setPager(new Pager(pageId, total, size));
		return dto;
	}
	
	@Override
	public IDto findtfGGsByOwner(long owner,int type){
		AdGGDto dto = new AdGGDto();
		List<Adgg> adGGList = cdGGDao.findtfGGsByOwner(owner,type);
		dto.setAdGGList(adGGList);
		return dto;
	}

	@Override
	public long updateGGUp(long id, long owner)
	{
		TfGG current = cdGGDao.findGGById(id,owner);
		TfGG front = cdGGDao.findFrontGG(owner,current.getIdx(),current.getType());
		if (current != null && front != null)
		{
			int rs = cdGGDao.updateGGIdx(current.getId(), front.getIdx());
			int rs2 = cdGGDao.updateGGIdx(front.getId(), current.getIdx());
			return rs + rs2;
		}
		return 0;
	}

	@Override
	public long updateGGDown(long id, long owner)
	{
		TfGG current = cdGGDao.findGGById(id,owner);
		TfGG next = cdGGDao.findNextGG(owner,current.getIdx(),current.getType());
		if (current != null && next != null)
		{
			int rs = cdGGDao.updateGGIdx(current.getId(), next.getIdx());
			int rs2 = cdGGDao.updateGGIdx(next.getId(), current.getIdx());
			return rs + rs2;
		}
		return 0;
	}

	@Override
	public long delTfGGById(long id, long owner)
	{
		return cdGGDao.delTfGGById(id,owner);
	}

	@Override
	public long delTfGGByGGid(long ggid, long owner, int type)
	{
		cdGGDao.delTfGGByGGId(ggid, owner);
		return adGGDao.delGGById(ggid,owner);
	}
	
	
}
