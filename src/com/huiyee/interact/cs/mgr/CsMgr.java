package com.huiyee.interact.cs.mgr;

import java.util.ArrayList;
import java.util.List;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.interact.cs.dao.ICsDao;
import com.huiyee.interact.cs.dto.CsDto;
import com.huiyee.interact.cs.dto.IDto;
import com.huiyee.interact.cs.dto.JContent;
import com.huiyee.interact.cs.model.ChuanSan;
import com.huiyee.interact.cs.util.JsonStringUtil;

public class CsMgr implements ICsMgr
{
	private ICsDao csDao;

	@Override
	public ChuanSan findCsById(long id, long ownerid)
	{
		return csDao.findById(id, ownerid);
	}

	@Override
	public IDto findList(long ownerid, int pageId, long omid)
	{
		CsDto dto = new CsDto();
		int total = csDao.findTotalByOwnerId(ownerid, omid);
		if (total > 0)
		{
			List<ChuanSan> list = csDao.findListByOwner(ownerid, (pageId - 1) * IInteractConstants.INTERACT_CS_LIMIT, IInteractConstants.INTERACT_CS_LIMIT, omid);
			dto.setList(list);
		}
		return dto;
	}

	@Override
	public long saveChuanSan(ChuanSan cs, long ownerid, long omid)
	{
		return csDao.save(cs, ownerid, omid);
	}

	@Override
	public int updateCs(ChuanSan rq, long id, long ownerid)
	{
		return csDao.update(rq, id, ownerid);
	}

	@Override
	public int updateCsJcontent(long id, long owner, String jsonStr)
	{
		return csDao.updateCsJcontent(id, owner, jsonStr);
	}

	public void setCsDao(ICsDao csDao)
	{
		this.csDao = csDao;
	}

	@Override
	public int deleteContent(long id, long jid, long owner)
	{
		ChuanSan cs = csDao.findById(id,owner);
		List<JContent> old = JsonStringUtil.String2List(cs.getJcontent(), JContent.class);
		List<JContent> list = new ArrayList<JContent>();
		if (old != null)
		{
			for (JContent content : old)
			{
				if (content.getJid() != jid)
				{
					list.add(content);
				}
			}
		}
		String jsonStr = JsonStringUtil.Obj2String(list);
		return csDao.updateCsJcontent(id, owner, jsonStr);
	}
}
