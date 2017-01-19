package com.huiyee.interact.cs.mgr;

import java.util.List;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.dao.ISinaUserDao;
import com.huiyee.esite.dao.IWeiXinDao;
import com.huiyee.esite.dto.Pager;
import com.huiyee.interact.cs.dao.ICsRecordDao;
import com.huiyee.interact.cs.dto.CsDataDto;
import com.huiyee.interact.cs.dto.IDto;
import com.huiyee.interact.cs.model.CsData;

public class CsRecordMgr implements ICsRecordMgr
{
	private ICsRecordDao csRecordDao;
	private ISinaUserDao sinaUserDao;
	private IWeiXinDao weiXinDao;

	@Override
	public long addCsFuidDraw(long csid, long fuid, int utype, String jcon, String ip, String terminal, String source)
	{
		return csRecordDao.addCsFuidDraw(csid, fuid, utype, jcon, ip, terminal, source);
	}

	public void setCsRecordDao(ICsRecordDao csRecordDao)
	{
		this.csRecordDao = csRecordDao;
	}

	public IDto findCsRecord(long csid, int type, int pageId)
	{
		CsDataDto dto = new CsDataDto();
		int total = csRecordDao.findTotal(csid, type);
		dto.setPager(new Pager(pageId, total, IInteractConstants.INTERACT_CS_DATA_LIMIT));
		if (total > 0)
		{
			List<CsData> list = csRecordDao.findList(csid, type, (pageId - 1) * IInteractConstants.INTERACT_CS_DATA_LIMIT, IInteractConstants.INTERACT_CS_DATA_LIMIT);
			if (list.size() > 0)
			{
				if (type == 0)
				{
					for (CsData csData : list)
					{
						csData.setNickname(sinaUserDao.findNickNameById(csData.getFuid()));
					}

				}
				else if (type == 1)
				{
					for (CsData csData : list)
					{
						csData.setNickname(weiXinDao.findNickNameById(csData.getFuid()));
					}
				}

			}
			dto.setList(list);
		}
		return dto;

	}

	public void setSinaUserDao(ISinaUserDao sinaUserDao)
	{
		this.sinaUserDao = sinaUserDao;
	}

	public void setWeiXinDao(IWeiXinDao weiXinDao)
	{
		this.weiXinDao = weiXinDao;
	}
}
