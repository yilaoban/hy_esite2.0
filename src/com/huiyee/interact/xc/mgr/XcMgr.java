
package com.huiyee.interact.xc.mgr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.dao.IFeatureDao;
import com.huiyee.esite.dao.IPageDao;
import com.huiyee.esite.dao.IPageFeatureDao;
import com.huiyee.esite.dao.ISiteDao;
import com.huiyee.esite.dao.ITemplateDao;
import com.huiyee.esite.dto.Feature123Dto;
import com.huiyee.esite.dto.Feature125Dto;
import com.huiyee.esite.dto.Feature136Dto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.mgr.IPageManager;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.Feature;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.esite.model.PageCard;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.EmojiFilter;
import com.huiyee.esite.util.HttpRequestDeviceUtils;
import com.huiyee.interact.appointment.dao.IInteractAptDao;
import com.huiyee.interact.lottery.dao.IlotteryDao;
import com.huiyee.interact.vote.dao.IVoteDao;
import com.huiyee.interact.xc.dao.IXcDao;
import com.huiyee.interact.xc.dao.IXcLotteryDao;
import com.huiyee.interact.xc.dao.IXcMcDao;
import com.huiyee.interact.xc.dao.IXcSendRecordDao;
import com.huiyee.interact.xc.dao.IXcfeatureDao;
import com.huiyee.interact.xc.dto.IDto;
import com.huiyee.interact.xc.dto.XcSendRecordDto;
import com.huiyee.interact.xc.dto.XcSiftDto;
import com.huiyee.interact.xc.dto.XchdDto;
import com.huiyee.interact.xc.model.InviteConfig;
import com.huiyee.interact.xc.model.Xc;
import com.huiyee.interact.xc.model.XcInfo;
import com.huiyee.interact.xc.model.XcRecord;
import com.huiyee.interact.xc.model.XcSendRecord;

public class XcMgr implements IXcMgr
{

	private IXcDao xcDao;
	private IXcMcDao xcMcDao;
	private IXcSendRecordDao xcSendRecordDao;
	private IVoteDao voteDao;
	private IlotteryDao lotteryDao;
	private IXcfeatureDao xcfeatureDao;
	private ISiteDao siteDao;
	private IPageDao pageDao;
	private IFeatureDao featureDao;
	private ITemplateDao templateDao;
	private IPageManager pageManager;
	private IPageFeatureDao pageFeatureDao;
	private Map<Long, com.huiyee.esite.fmgr.IFeatureManager> managers;
	private IXcLotteryDao xcLotteryDao;
	private IInteractAptDao interactAptDao;

	
	
	public void setInteractAptDao(IInteractAptDao interactAptDao)
	{
		this.interactAptDao = interactAptDao;
	}

	public void setXcLotteryDao(IXcLotteryDao xcLotteryDao)
	{
		this.xcLotteryDao = xcLotteryDao;
	}

	public void setManagers(Map<Long, com.huiyee.esite.fmgr.IFeatureManager> managers)
	{
		this.managers = managers;
	}

	public void setXcSendRecordDao(IXcSendRecordDao xcSendRecordDao)
	{
		this.xcSendRecordDao = xcSendRecordDao;
	}

	public void setXcDao(IXcDao xcDao)
	{
		this.xcDao = xcDao;
	}

	@Override
	public Xc getXcById(long xcid)
	{
		return xcDao.getXcById(xcid);
	}

	@Override
	public void saveRecords(List<XcRecord> records)
	{
		xcDao.saveRecords(records);
	}

	@Override
	public void updateLConfig(long xcid)
	{
		xcDao.updateLConfig(xcid);
	}

	@Override
	public List<Long> getUids(long xcid, String atype)
	{
		return xcDao.getUids(xcid, atype);
	}

	@Override
	public int getYRecord(String key)
	{
		String[] user = key.split("-");
		long xcid = Long.parseLong(user[0]);
		long uid = Long.parseLong(user[2]);
		int utype = Integer.parseInt(user[3]);
		return xcDao.getYCount(xcid, uid, utype);
	}

	@Override
	public List<XcRecord> getLastResult(long xcid, int startnum)
	{
		return xcDao.getLastResult(xcid, startnum);
	}

	@Override
	public IDto findSendDetail(long id, String username, int pageId, XcSiftDto siftDto)
	{
		XcSendRecordDto dto = new XcSendRecordDto();
		int total = xcSendRecordDao.findTotal(id, username, siftDto);
		if (total > 0)
		{
			List<XcSendRecord> list = xcSendRecordDao.findList(id, username, siftDto, (pageId - 1) * IInteractConstants.INTERACT_XC_RECORD_LIMIT,
					IInteractConstants.INTERACT_XC_RECORD_LIMIT);
			
			Map<Long, Long> map=new HashMap<Long, Long>();
			Xc xc=xcLotteryDao.findXcById(id);
			for (XcSendRecord xcSendRecord : list)
			{
				long aptrecordid=interactAptDao.findRecordIdByUid(xc.getAptid(),xcSendRecord.getUid());
				if(aptrecordid>0)
					map.put(xcSendRecord.getId(), aptrecordid);
			}
			dto.setList(list);
			dto.setRelationMap(map);
		}
		dto.setPager(new Pager(pageId, total, IInteractConstants.INTERACT_XC_RECORD_LIMIT));
		return dto;
	}

	@Override
	public String addSendRecordSub(long id, List<XcSendRecord> list, String content, String ip, String terminal)
	{
		if (StringUtils.isNotEmpty(content))
		{
			String[] arr = content.split("\r\n");
			if (arr.length > 0)
			{
				for (String string : arr)
				{
					String[] value = string.split(";");
					if (value.length == 2)
					{
						XcSendRecord xc = new XcSendRecord();
						xc.setUsername(value[0]);
						xc.setNickname(value[1]);
						list.add(xc);
					}
				}
			}
		}
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
		return result + "";
	}

	@Override
	public int updateTeyueCheck(VisitUser vu, int hypage, long xcid, String terminal, String ip, Xc xc)
	{
		int rs = 1;
		long uid = vu.getUid();
		int type = vu.getCd();
		String cg = "Y";
		String source = vu.getSource();
		String nickname = "";

		InviteConfig inviteConfig = xc.getInviteconfig();
		if (inviteConfig != null && "S".equals(inviteConfig.getAtype()))
		{
			if (nickname != null)
			{
				XcSendRecord record = xcSendRecordDao.findRecordByNickname(nickname, xcid);
				if (record == null)
				{
					String second = EmojiFilter.getCutString(nickname);
					if (second != null && !second.equals(nickname))
					{
						record = xcSendRecordDao.findRecordByNickname(second, xcid);
					}
				}
				if (record != null)
				{
					xcSendRecordDao.updateSendRecordInvited(record.getId(), uid, type, source, hypage);
				} else
				{
					rs = -10006;// 非特邀嘉宾
					cg = "N";
				}
			}
		}
		xcSendRecordDao.saveInviteRecord(xcid, uid, type, source, hypage, terminal, ip, cg);
		return rs;
	}

	@Override
	public int updateNickname(long recordId, String nickname, long owner)
	{
		XcSendRecord record = xcSendRecordDao.findRecordById(recordId, owner);
		if (record != null)
		{
			return xcSendRecordDao.updateNickname(recordId, nickname);
		}
		return 0;
	}

	@Override
	public int deleteSdRecord(long recordId, long owner)
	{
		XcSendRecord record = xcSendRecordDao.findRecordById(recordId, owner);
		if (record != null)
		{
			return xcSendRecordDao.deleteSdRecord(recordId);
		}
		return 0;
	}

	@Override
	public long saveSimpleHd(int interactid, Account account, long xcid, long groupid)
	{
		long entityid = 0;
		String title = null;
		int featureid = 0;// featureid是es_feature的ID
		// interactid是es_interact_model的ID

		if (interactid == 10002)
		{
			featureid = IInteractConstants.INTERACT_VOTE;
			title = "投票";
			entityid = voteDao.addVote(account.getOwner().getId(), title);
		} else if (interactid == 10004)
		{

			featureid = IInteractConstants.INTERACT_LLOTTERY;
			title = "砸金蛋";
			entityid = lotteryDao.addLottery(account.getOwner().getId(), "L", title);

		} else if (interactid == 10003)
		{

			featureid = IInteractConstants.INTERACT_ZLOTTERY;
			title = "大转盘";
			entityid = lotteryDao.addLottery(account.getOwner().getId(), "Z", title);

		} else
		{
			System.out.println("待完成");
		}
		if (entityid > 0)
		{
			List<Site> siteArr = siteDao.findCopySiteByOwner(groupid);
			JSONArray jo = new JSONArray();
			for (Site site : siteArr)
			{
				List<Long[]> list = new ArrayList<Long[]>();
				List<Long> pageList = pageDao.findPageIdBySiteid(site.getId());
				Collections.sort(pageList);
				for (Long pageId : pageList)
				{
					long targetSite = siteDao.findSiteidByXcandPageId(xcid, pageId);
					Long[] arr =
					{ targetSite, pageId };
					list.add(arr);
				}
				// 复制站点下页面
				Map<Long, Long> remark = new HashMap<Long, Long>();// key:新pageId
				// value:原pageId
				for (Long[] arr : list)
				{
					Page p = pageManager.findPageById(arr[1]);
					long newPageId = pageManager.addcopyPage(arr[0], p);
					remark.put(newPageId, arr[0]);
				}

				for (long np : remark.keySet())
				{
					jo.add(np);
					// long fpid = managers.get((long) featureid).add(np,
					// featureid, null);
					List<Long> pfList = pageFeatureDao.findPfidByPageId(np, featureid);
					if (pfList == null || pfList.size() == 0)
					{
						continue;
					}
					for (Long pfid : pfList)
					{
						Feature f = featureDao.findPageFeatureById(pfid);
						com.huiyee.esite.dto.IDto dto = getDto(f, entityid);
						managers.get((long) featureid).configSub(featureid, dto, account);
					}
				}
			}
			xcfeatureDao.addXcfeature(xcid, interactid, entityid, jo.toString());
		}
		return entityid;
	}

	private com.huiyee.esite.dto.IDto getDto(Feature f, long entityid)
	{
		if (f.getId() == IInteractConstants.INTERACT_VOTE)
		{
			Feature123Dto dto = new Feature123Dto();
			dto.setVoteid(entityid);
			dto.setFid(f.getFid());
			return dto;
		} else if (f.getId() == IInteractConstants.INTERACT_LLOTTERY)
		{
			Feature125Dto dto = new Feature125Dto();
			dto.setLotteryid(entityid);
			dto.setFid(f.getFid());
			return dto;
		} else if (f.getId() == IInteractConstants.INTERACT_ZLOTTERY)
		{
			Feature136Dto dto = new Feature136Dto();
			dto.setLotteryid(entityid);
			dto.setFid(f.getFid());
			return dto;
		}
		return null;
	}

	private Long copyPage(long siteid, long pageid, Set<Long> ridSet)
	{
		Page page = pageDao.findPageById(pageid);
		String copyname = page.getName();
		if (!page.getName().contains("(复制)"))
		{
			copyname = copyname + "(复制)";
		}
		page.setName(copyname);
		page.setSiteid(siteid);
		long pageid_copy = pageDao.saveNewPage(page);

		List<PageCard> list = templateDao.findTemplateCardByPageid(pageid);
		for (PageCard pagecard : list)
		{
			long pcid = pagecard.getId();
			pagecard.setPageid(pageid_copy);
			long pcid_copy = templateDao.saveNewPageCard(pagecard);

			List<PageBlockRelation> block = templateDao.findPageBlockRelationBycardid1(pcid);
			for (PageBlockRelation pbr : block)
			{
				long relationid = pbr.getId();
				long cbid = pbr.getCbid();
				String json = pbr.getJson();
				long pfid = pbr.getPfid();
				long relationid_copy = templateDao.savePageBlockRelation(pcid_copy, cbid, json, pfid,pbr.getDisplay());
				if (ridSet != null && json.contains("/user/show/"))
				{
					ridSet.add(relationid_copy);
				}

				List<Page> subpages = pageDao.findPageByRelationid(relationid);
				for (Page subpage : subpages)
				{
					subpage.setSiteid(siteid);
					subpage.setRelationid(relationid_copy);
					pageDao.saveNewPage(subpage);
				}
			}
		}
		return pageid_copy;
	}

	public void setVoteDao(IVoteDao voteDao)
	{
		this.voteDao = voteDao;
	}

	public void setXcfeatureDao(IXcfeatureDao xcfeatureDao)
	{
		this.xcfeatureDao = xcfeatureDao;
	}

	@Override
	public IDto findAppendHd()
	{
		XchdDto dto = new XchdDto();
		dto.setList(xcfeatureDao.findGroupByType());
		return dto;
	}

	public void setSiteDao(ISiteDao siteDao)
	{
		this.siteDao = siteDao;
	}

	public void setPageDao(IPageDao pageDao)
	{
		this.pageDao = pageDao;
	}

	public void setFeatureDao(IFeatureDao featureDao)
	{
		this.featureDao = featureDao;
	}

	public void setTemplateDao(ITemplateDao templateDao)
	{
		this.templateDao = templateDao;
	}

	@Override
	public Xc getXcByIdMc(long xcid)
	{
		return xcMcDao.getXcByIdMc(xcid);
	}

	public void setXcMcDao(IXcMcDao xcMcDao)
	{
		this.xcMcDao = xcMcDao;
	}

	public void setLotteryDao(IlotteryDao lotteryDao)
	{
		this.lotteryDao = lotteryDao;
	}

	public void setPageManager(IPageManager pageManager)
	{
		this.pageManager = pageManager;
	}

	public void setPageFeatureDao(IPageFeatureDao pageFeatureDao)
	{
		this.pageFeatureDao = pageFeatureDao;
	}

	@Override
	public int updateAcceptInvite(VisitUser vu, long xcid)
	{
		long uid = vu.getUid();
		int type = vu.getCd();
		xcDao.updateSdAcceptInvite(uid, type, xcid);
		return xcDao.updateAcceptInvite(uid, type, xcid);
	}

	@Override
	public int updatehdstart(int interactid, long hdid, long ownerid)
	{
		if (interactid == IInteractConstants.INTERACT_MODEL_ZHUANPAN || interactid == IInteractConstants.INTERACT_MODEL_ZHUANPAN)
		{
			return xcfeatureDao.updatehdstart(hdid, ownerid);
		}
		return 0;
	}
	
	@Override
	public int deleteXchd(int interactid, Account account, long xcid, long hdid)
	{
		//假删除互动
		switch (interactid)
		{
			case 10002:voteDao.deleteVote(hdid);break;
			case 10003:			
			case 10004:lotteryDao.updateStatus(hdid, "D", account.getOwner().getId());break;
		}
		//假删除互动页面
		String pages=xcfeatureDao.findXcfeaturePages(xcid,interactid,hdid);
		if(pages!=null){
			JSONArray ja=JSONArray.fromObject(pages);
			for (int i = 0; i < ja.size(); i++)
			{
				long pageid=ja.getLong(i);
				if(pageid>0)
					pageDao.deletePage(pageid);
			}
		}
		return xcfeatureDao.deletfeature(interactid,hdid);
	}
	
	
	@Override
	public XcInfo findXcInfoByXcid(long xcid)
	{
		XcInfo xc=new XcInfo();
		xc.setInvitedNum(xcDao.findInviteNum(xcid));
		xc.setCheckedNum(xcDao.findCheckedNum(xcid));
		return xc;
	}

	public Xc findXcById(long xcid)
	{
		return xcLotteryDao.findXcById(xcid);
	}
	
	@Override
	public void saveSdRecord(long xcid, List<XcSendRecord> list, String ip, String terminal)
	{
		List<XcSendRecord> needInsert = new ArrayList<XcSendRecord>();
		for (XcSendRecord xcSendRecord : list)
		{
			int exist = xcSendRecordDao.findExist(xcid, xcSendRecord.getUsername(), xcSendRecord.getNickname());
			if (exist == 0)
			{
				needInsert.add(xcSendRecord);
			}
		}
		xcSendRecordDao.addSendRecordSub(needInsert, xcid, ip, terminal);		
	}
	
	@Override
	public int updateInviteConfig(long xcid, String needInvite, InviteConfig inviteConfig)
	{
		return xcDao.updateInviteConfig(xcid,needInvite,inviteConfig);
	}
}