package com.huiyee.interact.cb.mgr.impl;

import java.util.List;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.interact.cb.dao.ICbActivityJlDao;
import com.huiyee.interact.cb.dto.CbActivityDto;
import com.huiyee.interact.cb.mgr.ICbActivityJlMgr;
import com.huiyee.interact.cb.model.CbActivityJlRecord;


public class CbActivityJlMgrImpl implements ICbActivityJlMgr
{
	private ICbActivityJlDao cbActivityJlDao;
	
	public void setCbActivityJlDao(ICbActivityJlDao cbActivityJlDao)
	{
		this.cbActivityJlDao = cbActivityJlDao;
	}

	@Override
	public CbActivityDto findCbActivityJlRecordList(long sender,int pageId)
	{
		CbActivityDto dto = new CbActivityDto();
		int total = cbActivityJlDao.findTotalCbActivityJlRecordList(sender);
		if(total > 0){
			int start = IPageConstants.CB_SENDER_RECORD * (pageId - 1);
			List<CbActivityJlRecord> list = cbActivityJlDao.findCbActivityJlRecordList(sender,start,IPageConstants.CB_SENDER_RECORD);
			if(list != null && list.size()>0){
				dto.setJlRecordList(list);
			}else{
				if(pageId -1 > 0){
					pageId = pageId - 1;
					start = IPageConstants.CB_SENDER_RECORD * (pageId - 1);
					list = cbActivityJlDao.findCbActivityJlRecordList(sender,start,IPageConstants.CB_SENDER_RECORD);
					if(list != null && list.size()>0){
						dto.setJlRecordList(list);
					}
				}
			}
		}
		dto.setPageId(pageId);
		return dto;
	}

}
