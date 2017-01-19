package com.huiyee.interact.spread.mgr;

import java.util.List;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.dao.ISinaUserDao;
import com.huiyee.esite.dao.IWeiXinDao;
import com.huiyee.interact.spread.dao.ISpreadRecordDao;
import com.huiyee.interact.spread.dto.IDto;
import com.huiyee.interact.spread.dto.SpreadRecordDto;
import com.huiyee.interact.spread.model.Pager;
import com.huiyee.interact.spread.model.SpreadRecord;

public class SpreadRecordMgrImpl implements ISpreadRecordMgr
{
	private ISpreadRecordDao spreadRecordDao;
	private ISinaUserDao sinaUserDao;
	private IWeiXinDao weiXinDao;
	
	public void setSpreadRecordDao(ISpreadRecordDao spreadRecordDao)
	{
		this.spreadRecordDao = spreadRecordDao;
	}

	public void setSinaUserDao(ISinaUserDao sinaUserDao)
	{
		this.sinaUserDao = sinaUserDao;
	}

	public void setWeiXinDao(IWeiXinDao weiXinDao)
	{
		this.weiXinDao = weiXinDao;
	}

	@Override
	public IDto findSpreadrecordList(int pageid, long spreadid, String begintime, String endtime, long type)
	{
		SpreadRecordDto sr = new SpreadRecordDto();
		if(type==0){
			int total = spreadRecordDao.findspreadRecordTotal(spreadid, begintime, endtime);
			if (total > 0)
			{
				List<SpreadRecord> list = spreadRecordDao.findSpreadrecordList((pageid - 1) * IInteractConstants.SPREADRECORD_LIMIT, IInteractConstants.SPREADRECORD_LIMIT, spreadid, begintime, endtime);
				for (SpreadRecord spreadRecord : list)
				{
					spreadRecord.setNickname(sinaUserDao.findNickNameById(spreadRecord.getWbuid()));
				}
				sr.setSpreadRecord(list);
			}
			sr.setPager(new Pager(pageid, total, IInteractConstants.SPREADRECORD_LIMIT));
		}else{
			int total = spreadRecordDao.findspreadRecordTotalWx(spreadid, begintime, endtime);
			if (total > 0)
			{
				List<SpreadRecord> list = spreadRecordDao.findSpreadrecordListWx((pageid - 1) * IInteractConstants.SPREADRECORD_LIMIT, IInteractConstants.SPREADRECORD_LIMIT, spreadid, begintime, endtime);
				for (SpreadRecord spreadRecord : list)
				{
					spreadRecord.setNickname(weiXinDao.findNickNameById(spreadRecord.getWbuid()));
				}
				sr.setSpreadRecord(list);
			}
			sr.setPager(new Pager(pageid, total, IInteractConstants.SPREADRECORD_LIMIT));
		}
		return sr;
	}

}
