package com.huiyee.interact.xc.mgr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.dao.ISinaUserDao;
import com.huiyee.esite.dao.IWeiXinDao;
import com.huiyee.esite.model.Site;
import com.huiyee.interact.appointment.dao.IInteractAptDao;
import com.huiyee.interact.auction.model.Pager;
import com.huiyee.interact.cs.dto.IDto;
import com.huiyee.interact.xc.dao.IXcLotteryDao;
import com.huiyee.interact.xc.dao.IXcSendRecordDao;
import com.huiyee.interact.xc.dao.IXcfeatureDao;
import com.huiyee.interact.xc.dto.XcLotteryDto;
import com.huiyee.interact.xc.dto.XcSiftDto;
import com.huiyee.interact.xc.model.HdEntity;
import com.huiyee.interact.xc.model.Xc;
import com.huiyee.interact.xc.model.XcCheckinRecord;
import com.huiyee.interact.xc.model.XcCjRecord;
import com.huiyee.interact.xc.model.XcCommentRecord;
import com.huiyee.interact.xc.model.XcExport;
import com.huiyee.interact.xc.model.XcInviteRecord;
import com.huiyee.interact.xc.model.XcSendRecord;

public class XcLotteryMgr implements IXcLotteryMgr
{
	private IXcLotteryDao xcLotteryDao;
	private IXcSendRecordDao xcSendRecordDao;
	private ISinaUserDao sinaUserDao;
	private IWeiXinDao weiXinDao;
	private IXcfeatureDao xcfeatureDao;
	private IInteractAptDao interactAptDao;

	
	public void setInteractAptDao(IInteractAptDao interactAptDao)
	{
		this.interactAptDao = interactAptDao;
	}

	public void setSinaUserDao(ISinaUserDao sinaUserDao)
	{
		this.sinaUserDao = sinaUserDao;
	}

	public void setWeiXinDao(IWeiXinDao weiXinDao)
	{
		this.weiXinDao = weiXinDao;
	}

	public void setXcLotteryDao(IXcLotteryDao xcLotteryDao)
	{
		this.xcLotteryDao = xcLotteryDao;
	}

	public void setXcSendRecordDao(IXcSendRecordDao xcSendRecordDao)
	{
		this.xcSendRecordDao = xcSendRecordDao;
	}

	@Override
	public IDto findXcList(long ownerid, int pageId)
	{
		XcLotteryDto dto = new XcLotteryDto();
		int total = xcLotteryDao.findTotalByOwnerId(ownerid);
		if (total > 0)
		{
			List<Xc> xcList = xcLotteryDao.findXcList(ownerid, (pageId - 1) * IInteractConstants.INTERACT_XC_LIMIT, IInteractConstants.INTERACT_XC_LIMIT);
			if (xcList != null && xcList.size() > 0)
			{
//				for (int i = 0; i < xcList.size(); i++)
//				{
//					List<XcSite> xcSiteList = xcLotteryDao.findXcSite(xcList.get(i).getId());
//					if (xcSiteList != null && xcSiteList.size() > 0)
//					{
//						xcList.get(i).setXcSiteList(xcSiteList);
//					}
//				}
			}
			dto.setXcList(xcList);
		}
		dto.setPager(new Pager(pageId, total, IInteractConstants.INTERACT_XC_LIMIT));
		return dto;
	}

	@Override
	public long saveXcLottery(Xc xc, long ownerid)
	{
		return xcLotteryDao.saveXcLottery(xc, ownerid);
	}

	@Override
	public int updateXcLottery(long id, String lotteryconfig)
	{
		return xcLotteryDao.updateXcLottery(id, lotteryconfig);
	}

	@Override
	public Xc findXcById(long id)
	{
		return xcLotteryDao.findXcById(id);
	}

	@Override
	public int updateXcLottery(long id, Xc xc)
	{
		return xcLotteryDao.updateXcLottery(id, xc);
	}

	@Override
	public int updateXcLotteryAndUpload(long id, Xc xc, List<XcSendRecord> list, String ip, String terminal)
	{
		List<XcSendRecord> needInsert = new ArrayList<XcSendRecord>();
		for (XcSendRecord xcSendRecord : list)
		{
			int exist = xcSendRecordDao.findExist(id, xcSendRecord.getUsername(), xcSendRecord.getNickname());
			if (exist == 0)
			{
				needInsert.add(xcSendRecord);
			}
		}
		int result = xcSendRecordDao.addSendRecordSub(needInsert, id, ip, terminal);
		xcLotteryDao.updateXcLottery(id, xc);
		return result;
	}

	@Override
	public int delXcLottery(long id)
	{
		return xcLotteryDao.delXcLottery(id);
	}

	@Override
	public IDto findInviteRecordList(long id,int pageId,String nickname, XcSiftDto siftDto)
	{
		XcLotteryDto dto = new XcLotteryDto();
		int total = xcLotteryDao.findTotalInviteRecord(id,nickname,siftDto);
		if (total > 0)
		{
			List<XcInviteRecord> inviteRecordList = xcLotteryDao.findInviteRecordList(id, (pageId - 1) * IInteractConstants.INTERACT_XC_LIMIT, IInteractConstants.INTERACT_XC_LIMIT,nickname,siftDto);
			dto.setInviteRecordList(inviteRecordList);
			
			Map<Long, Long> map=new HashMap<Long, Long>();
			Xc xc=xcLotteryDao.findXcById(id);
			for (XcInviteRecord xcSendRecord : inviteRecordList)
			{
				long aptrecordid=interactAptDao.findRecordIdByUid(xc.getAptid(),xcSendRecord.getUid());
				if(aptrecordid>0)
					map.put(xcSendRecord.getId(), aptrecordid);
			}
			dto.setRelationMap(map);
		}
		dto.setPager(new Pager(pageId, total, IInteractConstants.INTERACT_XC_LIMIT));
		return dto;
	}

	@Override
	public IDto findCheckinRecordList(long id, int utype, int pageId)
	{
		XcLotteryDto dto = new XcLotteryDto();
		int total = xcLotteryDao.findTotalCheckinRecord(id, utype);
		if (total > 0)
		{
			List<XcCheckinRecord> checkinRecordList = xcLotteryDao.findCheckinRecordList(id, utype, (pageId - 1) * IInteractConstants.INTERACT_XC_LIMIT, IInteractConstants.INTERACT_XC_LIMIT);
			dto.setCheckinRecordList(checkinRecordList);
		}
		dto.setPager(new Pager(pageId, total, IInteractConstants.INTERACT_XC_LIMIT));
		return dto;
	}

	@Override
	public IDto findCommentRecordList(long id, int utype, int pageId)
	{
		XcLotteryDto dto = new XcLotteryDto();
		int total = xcLotteryDao.findTotalCommentRecord(id, utype);
		if (total > 0)
		{
			List<XcCommentRecord> commentRecordList = xcLotteryDao.findCommentRecordList(id, utype, (pageId - 1) * IInteractConstants.INTERACT_XC_LIMIT, IInteractConstants.INTERACT_XC_LIMIT);
			dto.setCommentRecordList(commentRecordList);
		}
		dto.setPager(new Pager(pageId, total, IInteractConstants.INTERACT_XC_LIMIT));
		return dto;
	}

	@Override
	public IDto findLotteryRecordList(long id, int utype, String nickname, String top, int pageId)
	{
		XcLotteryDto dto = new XcLotteryDto();
		int total = xcLotteryDao.findTotalLotteryRecord(id, utype, nickname, top);
		if (total > 0)
		{
			List<XcCjRecord> cjRecordList = xcLotteryDao.findLotteryRecordList(id, utype, (pageId - 1) * IInteractConstants.INTERACT_XC_LIMIT, IInteractConstants.INTERACT_XC_LIMIT, nickname, top);
			dto.setCjRecordList(cjRecordList);
		}
		dto.setPager(new Pager(pageId, total, IInteractConstants.INTERACT_XC_LIMIT));
		return dto;
	}

	@Override
	public long updateCommentRecordStatus(long id, String checked)
	{
		if ("Y".equals(checked))
		{
			xcLotteryDao.updateCommentRecordStatus(id, "CMP");
		}
		else if ("N".equals(checked))
		{
			xcLotteryDao.updateCommentRecordStatus(id, "FLD");
		}
		return 1;
	}

	@Override
	public IDto findAuditList(long id, int utype, int pageId)
	{
		XcLotteryDto dto = new XcLotteryDto();
		int total = xcLotteryDao.findTotalAudit(id, utype);
		if (total > 0)
		{
			List<XcCommentRecord> commentRecordList = xcLotteryDao.findAuditList(id, utype, (pageId - 1) * IInteractConstants.INTERACT_XC_LIMIT, IInteractConstants.INTERACT_XC_LIMIT);
			dto.setCommentRecordList(commentRecordList);
		}
		dto.setPager(new Pager(pageId, total, IInteractConstants.INTERACT_XC_LIMIT));
		return dto;
	}

	@Override
	public List<XcExport> findWinner(long id, long owner)
	{
		List<XcExport> list = xcLotteryDao.findWinner(id);
		if (list.size() > 0)
		{
			for (XcExport xcExport : list)
			{
				List<String> names = xcSendRecordDao.findNames(id, xcExport.getUid(), xcExport.getUtype());
				if (names != null)
				{
					xcExport.setName(names.get(0));
					xcExport.setNickname(names.get(1));
				}
				else
				{
					long uid = xcExport.getUid();
					if (xcExport.getUtype() == 0)
					{
						xcExport.setNickname(sinaUserDao.findNickNameById(uid));
					}
					else if (xcExport.getUtype() == 1)
					{
						xcExport.setNickname(weiXinDao.findNickNameById(uid));
					}
				}

			}
		}
		return list;
	}

	@Override
	public IDto findSiteList(long ownerid)
	{
		XcLotteryDto dto = new XcLotteryDto();
		List<Site> siteList = xcLotteryDao.findSiteList(ownerid);
		dto.setSiteList(siteList);
		return dto;
	}

	@Override
	public long saveXcSite(long xcid, long siteid, String type)
	{
		return xcLotteryDao.saveXcSite(xcid, siteid, type);
	}

	@Override
	public int updateXcName(Xc xc)
	{
		return xcLotteryDao.updateXcName(xc);
	}

	@Override
	public int updateBigScreenSite(long siteid, long id)
	{
		return xcLotteryDao.updateBigScreenSite(id, siteid);
	}

	@Override
	public long findXcFidByPage(long pageId)
	{
		return xcLotteryDao.findXcFidByPage(pageId);
	}

	@Override
	public List<HdEntity> findXcEntitys(long xcid)
	{
		List<HdEntity> list = xcfeatureDao.findXcEntitys(xcid);
		if (list.size() > 0)
		{
			for (HdEntity hdEntity : list)
			{
				if(hdEntity.getFeatureid()==10002){
					//Í¶Æ±
					HdEntity e=xcfeatureDao.findVoteValue(hdEntity.getEntityid());
					hdEntity.setTitle(e.getTitle());
//					hdEntity.setVotetype(e.getVotetype());
				}if(hdEntity.getFeatureid()==10004||hdEntity.getFeatureid()==10003){
					//ÔÒ½ðµ°
					HdEntity e=xcfeatureDao.findLotteryValue(hdEntity.getEntityid());
					hdEntity.setTitle(e.getTitle());
					hdEntity.setVotetype(e.getVotetype());
				}
			}
		}
		return list;
	}

	public void setXcfeatureDao(IXcfeatureDao xcfeatureDao)
	{
		this.xcfeatureDao = xcfeatureDao;
	}

	@Override
	public Xc getXcById(long xcid)
	{
		return xcLotteryDao.getXcById(xcid);
	}


}
