
package project.caidan.mgr.impl;

import java.util.List;

import com.huiyee.esite.dao.IWxUserDao;

import project.caidan.dao.ICdRmbRecordDao;
import project.caidan.mgr.ICdRmbRecordMgr;
import project.caidan.model.CdRmbRecord;

public class CdRmbRecordMgrImpl implements ICdRmbRecordMgr
{

	private ICdRmbRecordDao rmbRecordDao;
	private IWxUserDao wxUserDao;

	@Override
	public List<CdRmbRecord> findListForPage(long wxuid, int pageId, int size)
	{
		List<CdRmbRecord> list = rmbRecordDao.findList(wxuid, (pageId - 1) * size, size);
		for (CdRmbRecord cdRmbRecord : list)
		{
			cdRmbRecord.setWxuser(wxUserDao.findWxUserByid(cdRmbRecord.getWxuid()));
		}
		return list;
	}

	public void setRmbRecordDao(ICdRmbRecordDao rmbRecordDao)
	{
		this.rmbRecordDao = rmbRecordDao;
	}

	public void setWxUserDao(IWxUserDao wxUserDao)
	{
		this.wxUserDao = wxUserDao;
	}
}
