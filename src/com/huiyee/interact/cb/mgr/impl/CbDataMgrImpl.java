
package com.huiyee.interact.cb.mgr.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dao.IMarketActivityDao;
import com.huiyee.esite.dao.IPageShowMaterialDao;
import com.huiyee.esite.dao.IWeiXinDao;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.CbActivity;
import com.huiyee.esite.model.CrmWxHongbaoCheck;
import com.huiyee.esite.solr.HylogSolrServer;
import com.huiyee.esite.util.DateUtil;
import com.huiyee.interact.appointment.dao.IInteractAptDao;
import com.huiyee.interact.appointment.model.AppointmentDataModel;
import com.huiyee.interact.appointment.model.AptMapping;
import com.huiyee.interact.cb.dao.ICbActivityMatterDao;
import com.huiyee.interact.cb.dao.ICbJiDaoImpl;
import com.huiyee.interact.cb.dao.ICbSenderDao;
import com.huiyee.interact.cb.dao.IHbRecordDao;
import com.huiyee.interact.cb.dao.IInteractCbDao;
import com.huiyee.interact.cb.dto.CbDataDto;
import com.huiyee.interact.cb.mgr.ICbDataMgr;
import com.huiyee.interact.cb.model.CbHbRecord;
import com.huiyee.interact.cb.model.CbImpel;
import com.huiyee.interact.cb.model.CbSender;
import com.huiyee.interact.cb.model.HbConfig;
import com.huiyee.interact.cb.model.InteractCb;
import com.huiyee.interact.cb.model.SenderImpel;
import com.huiyee.interact.lottery.dao.IWxHongbaoDao;

public class CbDataMgrImpl implements ICbDataMgr
{

	private ICbSenderDao cbSenderDao;
	private IWeiXinDao weiXinDao;
	private IInteractAptDao interactAptDao;
	private ICbJiDaoImpl cbJiDao;
	private IInteractCbDao interactCbDao;
	private IMarketActivityDao marketActivityDao;
	private ICbActivityMatterDao cbActivityMatterDao;
	private HylogSolrServer hylogSolrServer;
	private IWxHongbaoDao wxHongbaoDao;
	private IHbRecordDao hbRecordDao;
	private IPageShowMaterialDao pageShowMaterialDao;

	public void setPageShowMaterialDao(IPageShowMaterialDao pageShowMaterialDao)
	{
		this.pageShowMaterialDao = pageShowMaterialDao;
	}

	public void setHbRecordDao(IHbRecordDao hbRecordDao)
	{
		this.hbRecordDao = hbRecordDao;
	}

	public void setWxHongbaoDao(IWxHongbaoDao wxHongbaoDao)
	{
		this.wxHongbaoDao = wxHongbaoDao;
	}

	public void setMarketActivityDao(IMarketActivityDao marketActivityDao)
	{
		this.marketActivityDao = marketActivityDao;
	}

	public void setInteractCbDao(IInteractCbDao interactCbDao)
	{
		this.interactCbDao = interactCbDao;
	}

	public void setCbJiDao(ICbJiDaoImpl cbJiDao)
	{
		this.cbJiDao = cbJiDao;
	}

	public void setInteractAptDao(IInteractAptDao interactAptDao)
	{
		this.interactAptDao = interactAptDao;
	}

	public void setWeiXinDao(IWeiXinDao weiXinDao)
	{
		this.weiXinDao = weiXinDao;
	}

	public void setCbSenderDao(ICbSenderDao cbSenderDao)
	{
		this.cbSenderDao = cbSenderDao;
	}

	public void setCbActivityMatterDao(ICbActivityMatterDao cbActivityMatterDao)
	{
		this.cbActivityMatterDao = cbActivityMatterDao;
	}

	public void setHylogSolrServer(HylogSolrServer hylogSolrServer)
	{
		this.hylogSolrServer = hylogSolrServer;
	}

	@Override
	public IDto findSenderRecordList(long cbid, int sendType, long owner, int pageid)
	{
		CbDataDto dto = new CbDataDto();
		dto.setCb(interactCbDao.findCbById(cbid));
		int total = cbSenderDao.findSenderRecordTotal(cbid, sendType, owner);
		if (total > 0)
		{
			List<CbSender> list = cbSenderDao.findSenderRecord(cbid, sendType, owner, IPageConstants.CB_SENDER_LIMIT * (pageid - 1), IPageConstants.CB_SENDER_LIMIT);
			if (list.size() > 0)
			{
				for (CbSender cbSender : list)
				{
					long wxuid = cbSender.getRecord().getWbuid();
					String nickname = weiXinDao.findNickNameById(wxuid);
					cbSender.getRecord().setNickname(nickname);
				}
			}
			dto.setList(list);
			dto.setPager(new Pager(pageid, total, IPageConstants.CB_SENDER_LIMIT));
		}
		return dto;
	}

	@Override
	public IDto findSenderRecordList(long cbid, long rid, long owner)
	{
		CbDataDto dto = new CbDataDto();
		CbSender sender = cbSenderDao.findRecordById(cbid, rid);
		if (sender == null)
			return null;
		AppointmentDataModel aptRecord = sender.getRecord();
		List<AptMapping> cloum = interactAptDao.findAllColum(aptRecord.getAptid());
		aptRecord.setMaps(cloum);
		aptRecord.composeList(cloum);
		sender.setOwner(owner);
		sender.setRecord(aptRecord);
		dto.setSender(sender);
		return dto;
	}

	@Override
	public int updateCbSenderSub(long cbid, long rid, String status, String reason, Account account)
	{
		CbSender sender = cbSenderDao.findRecordById(cbid, rid);
		if (sender != null && "1".equals(sender.getRecord().getType()))
		{
			return cbSenderDao.updateCbSenderSub(sender, status, reason, account.getOwner().getId());
		} else
		{
			System.out.println("微传播的申请记录不是不是微信?");
		}
		return 0;
	}

	@Override
	public IDto findJiliRecord(Account account, long cbid, int pageId, String name, String starttime, String endtime, String status)
	{
		return null;
	}

	@Override
	public IDto findSenderDetail(Account account, long cbid, long senderid, int pageId, String starttime, String endtime)
	{
		InteractCb cb = interactCbDao.findCbById(cbid);
		if (cb != null)
		{
			CbDataDto dto = new CbDataDto();
			int total = cbJiDao.findSenderJiTotal(senderid, starttime, endtime);
			if (total > 0)
			{
				List<CbImpel> list = cbJiDao.findSenderImpelDetail(senderid, starttime, endtime, (pageId - 1) * IPageConstants.CB_IMPEL_LIMIT, IPageConstants.CB_IMPEL_LIMIT);
				for (CbImpel s : list)
				{
					CbSender sender = cbSenderDao.findRecordById(senderid);
					if (sender != null)
					{
						s.setName(sender.getRecord().getName());
					}
				}
				dto.setImplList(list);
				dto.setPager(new Pager(pageId, total, IPageConstants.CB_IMPEL_LIMIT));
			}
			return dto;
		}
		return null;
	}

	@Override
	public IDto findSenderEffect(long owner, long cbaid, int pageId, String starttime, String endtime, String order) {
		long pageid = 0;
		CbActivity ca = marketActivityDao.findCbActivityByAid(cbaid);
		if (ca != null) {
			int type = ca.getType();
			ca.setM_title(marketActivityDao.findM_title(owner, type, ca.getEnid()));
			if(type==0){
				pageid = ca.getEnid();
			}else if(type==1){
				pageid = marketActivityDao.findNpageid(owner);
			}
		}

		List<CbImpel> list = new ArrayList<CbImpel>();
		int rows = 10;
		int total = 0;
		if (pageid > 0) {
			starttime = toSolrDate(starttime);
			endtime = toSolrDate(endtime);
			int start = (pageId - 1) * rows;
			total = hylogSolrServer.cbSenderEffectCount(owner, "wx", pageid, starttime, endtime);
			SolrDocumentList docs = hylogSolrServer.cbSenderEffect(owner, "wx", pageid, order, starttime, endtime, start, rows);
			for (SolrDocument doc : docs) {
				CbImpel impel = new CbImpel();
				long wxuid = Long.parseLong(doc.getFieldValue("t_s") + "");
				int zhuanfa = Integer.parseInt(doc.getFieldValue("s") + "");
				int dianji = Integer.parseInt(doc.getFieldValue("c") + "");
				int guanzhu = Integer.parseInt(doc.getFieldValue("g") + "");
				int hudong = Integer.parseInt(doc.getFieldValue("h") + "");
				int waibu = Integer.parseInt(doc.getFieldValue("w") + "");
				CbSender sender = cbSenderDao.findSenderByUId(owner, wxuid);
				if (sender != null) {
					impel.setName(sender.getRecord().getName());
					impel.setZhuanfa(zhuanfa);
					impel.setDianji(dianji);
					impel.setGuanzhu(guanzhu);
					impel.setHudong(hudong);
					impel.setWaibuxiaoguo(waibu);
					list.add(impel);
				}
			}
		}

		CbDataDto dto = new CbDataDto();
		dto.setCurrent(ca);
		dto.setImplList(list);
		dto.setPager(new Pager(pageId, total, rows));
		return dto;
	}

	private String toSolrDate(String time)
	{
		if (StringUtils.isNotBlank(time))
		{
			String[] arr = time.split(" ");
			if (arr.length == 1)
			{
				return arr[0] + "T00:00:00.000Z";
			} else if (arr.length == 2)
			{
				return arr[0] + "T" + arr[1] + ".000Z";
			}
		}
		return null;
	}

	@Override
	public IDto findJiliList(long cbaid, Account account, String name, String starttime, String endtime, int pageId, String status)
	{
		CbDataDto dto = new CbDataDto();
		CbActivity ca = marketActivityDao.findCbActivityByAid(cbaid);
		dto.setCurrent(ca);
		int total = cbJiDao.findJiliTotal(cbaid, name, starttime, endtime, status);
		if (total > 0)
		{
			dto.setPager(new Pager(pageId, total, IPageConstants.CB_IMPEL_LIMIT));
			dto.setSenderImpel(cbJiDao.findSenderImpel(cbaid, name, starttime, endtime, (pageId - 1) * IPageConstants.CB_IMPEL_LIMIT, IPageConstants.CB_IMPEL_LIMIT, status));
		}
		CrmWxHongbaoCheck hb = wxHongbaoDao.findCheckByTypeAndEnid(ca.getId(), 2);
		if (hb != null)
		{
			dto.setHbtotal(hb.getTotal());
			dto.setHbused(hb.getUsed());
		}
		return dto;
	}

	@Override
	public int findCbExist(Account account)
	{
		long owner = account.getOwner().getId();
		InteractCb cb = interactCbDao.findCbById(owner);
		return cb != null ? 1 : 0;
	}

	@Override
	public IDto findCbActivity(Account account, int pageId)
	{
		CbDataDto dto = new CbDataDto();
		long owner = account.getOwner().getId();
		int total = marketActivityDao.findTotalCbActivityByCbid(owner);
		if (total > 0)
		{
			List<CbActivity> cbActivityList = marketActivityDao.findCbActivityListByCbid(owner, (pageId - 1) * IInteractConstants.INTERACT_CB_ACTIVITY,
					IInteractConstants.INTERACT_CB_ACTIVITY);
	
			dto.setActivity(cbActivityList);
			dto.setPager(new Pager(pageId, total, 20));
		}
		return dto;
	}

	@Override
	public int updateJiliSub(int impelId, int subPrice)
	{
		SenderImpel si = cbJiDao.findJiRecordById(impelId);
		CrmWxHongbaoCheck hbc = wxHongbaoDao.findCheckByTypeAndEnid(si.getCbaid(), 2);
		subPrice = si.getTotal();// 不能修改激励总额所以subprice就是total
		int residue = 0;
		if (hbc != null)
		{
			residue = hbc.getTotal() - hbc.getUsed();
		}

		if (residue < subPrice || residue <= 0)
		{
			return -1;
		} else
		{
			wxHongbaoDao.updateCbActivityUsed(si.getCbaid(), 2, subPrice);
			cbJiDao.updatejISub(impelId, subPrice);
			cbSenderDao.addHbtotal(si, subPrice);
			return 1;
		}
	}

	@Override
	public int addInteractCb(Account account)
	{
		long ownerid = account.getOwner().getId();
		String aptname = "申请-" + DateUtil.getDateString(new Date());
		long aptid = interactAptDao.saveSimpleApt(ownerid, aptname);
		interactAptDao.addName(aptid, "", "f1", "S", "T", "", "N", 0, "N", "N");
		interactAptDao.addName(aptid, "", "f2", "S", "S", "", "N", 0, "N", "N");
		interactAptDao.addName(aptid, "", "f3", "S", "R", "", "N", 0, "N", "N");
		interactAptDao.addName(aptid, "", "f4", "S", "C", "", "N", 0, "N", "N");
		interactAptDao.addName(aptid, "", "f5", "S", "A", "", "N", 0, "N", "N");

		InteractCb cb = new InteractCb();
		cb.setAptid(aptid);
		cb.setTitle("传播" + DateUtil.getDateString(new Date()));
		cb.setOwner(ownerid);
		interactCbDao.saveInteractCb(cb);
		return 1;
	}

	@Override
	public CbSender findSenderByUId(long owner, long wxuid)
	{
		return cbSenderDao.findSenderByUId(owner, wxuid);
	}

	@Override
	public HbConfig findHbConfig(Account account)
	{
		long cbid = account.getOwner().getId();
		HbConfig c = interactCbDao.findCbhbConfig(cbid);
		return c;
	}

	@Override
	public int updateHbConfig(Account account, HbConfig hbc)
	{
		long cbid = account.getOwner().getId();
		HbConfig c = interactCbDao.findCbhbConfig(cbid);
		return interactCbDao.updateHbConfig(cbid, hbc);
	}

	@Override
	public IDto findFundsRecord(Account account, long sender, String name, String starttime, String endtime, int pageId)
	{
		CbDataDto dto = new CbDataDto();
		long owner = account.getOwner().getId();
		int total = hbRecordDao.findTotalByCbid(owner, sender, name, starttime, endtime);
		if (total > 0)
		{
			List<CbHbRecord> list = hbRecordDao.findHbRecord(owner, sender, name, starttime, endtime, (pageId - 1) * 20, 20);
			dto.setHbRecord(list);
			dto.setPager(new Pager(pageId, total, 20));
		}
		return dto;
	}

	@Override
	public IDto findCbSender(Account account, String name, int pageId)
	{
		CbDataDto dto = new CbDataDto();
		long owner = account.getOwner().getId();
		int total = cbSenderDao.findSenderTotal(owner, name);
		if (total > 0)
		{
			List<CbSender> list = cbSenderDao.findSenderByCbid(owner, name, (pageId - 1) * 20, 20);
			dto.setList(list);
			dto.setPager(new Pager(pageId, total, 20));
		}
		return dto;
	}

	@Override
	public int updateCbSendCheck(long id, int status) {
		if(status==-1)
		{
			cbSenderDao.updateCbSenderUsed(id,status);
		}else{
		 hbRecordDao.updateCbSendCheck(id,status);
		}
		return 1;
	}

	@Override
	public int findSender(long hyuid)
	{
		return cbSenderDao.findSender(hyuid);
	}
}
