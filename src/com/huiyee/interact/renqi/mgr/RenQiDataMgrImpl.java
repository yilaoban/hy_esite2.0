package com.huiyee.interact.renqi.mgr;

import java.util.List;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.dao.ISinaUserDao;
import com.huiyee.esite.dao.IWeiXinDao;
import com.huiyee.esite.dto.Pager;
import com.huiyee.interact.lottery.dao.ILotteryUserRecordDao;
import com.huiyee.interact.lottery.model.LotteryUserRecord;
import com.huiyee.interact.renqi.dao.IRenQiRecordDao;
import com.huiyee.interact.renqi.dto.IDto;
import com.huiyee.interact.renqi.dto.RenQiDataDto;
import com.huiyee.interact.renqi.model.RenQiData;
import com.huiyee.interact.renqi.model.RenQiDetail;

public class RenQiDataMgrImpl implements IRenQiDataMgr
{
	private IRenQiRecordDao renQiRecordDao;
	private ISinaUserDao sinaUserDao;
	private IWeiXinDao weiXinDao;
	private ILotteryUserRecordDao lotteryUserRecordDao;

	@Override
	public IDto findList(long rqid, int pageId, int utype)
	{
		RenQiDataDto dto = new RenQiDataDto();
		int total = renQiRecordDao.findJoinTotal(rqid, utype);
		dto.setPager(new Pager(pageId, total, IInteractConstants.INTERACT_RENQI_DATA_LIMIT));
		if (total > 0)
		{
			List<RenQiData> list = renQiRecordDao.findRecordList(rqid, utype, (pageId - 1) * IInteractConstants.INTERACT_RENQI_DATA_LIMIT, IInteractConstants.INTERACT_RENQI_DATA_LIMIT);
			if (list.size() > 0)
			{
				if (utype == 0)
				{
					for (RenQiData renQiData : list)
					{
						renQiData.setName(sinaUserDao.findNickNameById(renQiData.getFuid()));
						renQiData.setIp(renQiRecordDao.findFristIp(rqid,renQiData.getFuid()));
						List<LotteryUserRecord> recordList = lotteryUserRecordDao.findUserJoin(renQiData.getLid(), renQiData.getFuid(), renQiData.getUtype());
						if (recordList.size() > 0)
						{
							renQiData.setLrnum(recordList.size());
							int recordStatus = recordList.get(0).getStatus();
							renQiData.setLrStatus(recordStatus);

						}
					}

				}
				else if (utype == 1)
				{
					for (RenQiData renQiData : list)
					{
						renQiData.setName(weiXinDao.findNickNameById(renQiData.getFuid()));
						renQiData.setIp(renQiRecordDao.findFristIp(rqid,renQiData.getFuid()));
						List<LotteryUserRecord> recordList = lotteryUserRecordDao.findUserJoin(renQiData.getLid(), renQiData.getFuid(), renQiData.getUtype());
						if (recordList.size() > 0)
						{
							renQiData.setLrnum(recordList.size());
							int recordStatus = recordList.get(0).getStatus();
							renQiData.setLrStatus(recordStatus);

						}
					}
				}
			}
			dto.setList(list);
		}

		return dto;
	}

	@Override
	public IDto findDetail(long rqid, long fuid, int pageId)
	{
		RenQiDataDto dto = new RenQiDataDto();
		int total = renQiRecordDao.findFuidTotal(fuid, rqid);
		dto.setPager(new Pager(pageId, total, IInteractConstants.INTERACT_RENQI_DETAIL_LIMIT));
		if (total > 0)
		{
			List<RenQiDetail> details = renQiRecordDao.findFuidDetail(fuid, rqid, (pageId - 1) * IInteractConstants.INTERACT_RENQI_DETAIL_LIMIT, IInteractConstants.INTERACT_RENQI_DETAIL_LIMIT);
			if (details.size() > 0)
			{
				for (RenQiDetail d : details)
				{
					if (d.getUtype() == 0)
					{
						d.setName(sinaUserDao.findNickNameById(d.getWbuid()));
					}else if(d.getUtype()==1){
						d.setName(weiXinDao.findNickNameById(d.getWbuid()));
					}
				}
			}
			dto.setDetails(details);
		}
		return dto;
	}

	public void setRenQiRecordDao(IRenQiRecordDao renQiRecordDao)
	{
		this.renQiRecordDao = renQiRecordDao;
	}

	public void setSinaUserDao(ISinaUserDao sinaUserDao)
	{
		this.sinaUserDao = sinaUserDao;
	}

	public void setWeiXinDao(IWeiXinDao weiXinDao)
	{
		this.weiXinDao = weiXinDao;
	}

	public void setLotteryUserRecordDao(ILotteryUserRecordDao lotteryUserRecordDao)
	{
		this.lotteryUserRecordDao = lotteryUserRecordDao;
	}
}
