
package project.caidan.mgr.impl;

import java.util.List;

import project.caidan.dao.ICaiDanRmbDao;
import project.caidan.dto.CaiDanRmbDto;
import project.caidan.mgr.ICaiDanRmbMgr;
import project.caidan.model.CDRmb;
import project.caidan.model.CDRmbGet;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.Pager;

public class CaiDanRmbMgrImpl implements ICaiDanRmbMgr
{

	private ICaiDanRmbDao cdRmbDao;

	public void setCdRmbDao(ICaiDanRmbDao cdRmbDao)
	{
		this.cdRmbDao = cdRmbDao;
	}

	@Override
	public IDto findRmbGetList(int pageId,String starttime,String endtime)
	{
		CaiDanRmbDto dto = new CaiDanRmbDto();
		int total = cdRmbDao.findTotalRmbGet(starttime,endtime);
		int size = 10;
		int start = (pageId - 1) * size;
		if (total > 0)
		{
			List<CDRmbGet> list = cdRmbDao.findRmbGetList(starttime,endtime,start, size);
			if (list != null && list.size() > 0)
			{
				dto.setRmbGetList(list);
			}
		}
		dto.setPager(new Pager(pageId, total, size));
		return dto;
	}

	@Override
	public int updateRmbGetById(CDRmbGet rmbGet)
	{
		return cdRmbDao.updateRmbGetById(rmbGet);
	}

	@Override
	public CDRmb findRmbByWxuid(long wxuid)
	{
		return cdRmbDao.findCDRmbByWxuid(wxuid);
	}

	@Override
	public int addRmbGet(long wxuid, float rmb)
	{
		CDRmb cdr=cdRmbDao.findCDRmbByWxuid(wxuid);
		if(cdr!=null){
			if(cdr.getTotal()-cdr.getUsed()>=rmb*100){
				cdRmbDao.updateRmbUsed(wxuid, (int)rmb*100);
				return cdRmbDao.addRmbGet(wxuid, rmb);
			}else{
				return -1;
			}
		}
		return 0;
	}

	@Override
	public List<CDRmbGet> findRmbGetList(long wxuid, int pageId, int size)
	{
		return cdRmbDao.findRmbGetList(wxuid, (pageId - 1) * size, size);
	}

	@Override
	public int updateRmbGetStatusById(CDRmbGet rmbGet)
	{
		CDRmbGet rmb = cdRmbDao.findCDRmbGetById(rmbGet.getId());
		if(rmb != null){
			cdRmbDao.updateRmbUsed(rmb.getWxuid(), -rmb.getRmb());
		}
		return cdRmbDao.updateRmbGetStatusById(rmbGet);
	}
	
	@Override
	public List<CDRmbGet> findRmbGetList(String starttime, String endtime)
	{
		return cdRmbDao.findRmbGetList(starttime,endtime);
	}
}
