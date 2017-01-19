package com.huiyee.interact.cb.mgr.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.solr.common.SolrDocument;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dao.IContentNewDao;
import com.huiyee.esite.dao.IPageDao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.CbActivity;
import com.huiyee.esite.model.ContentNew;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.solr.HylogSolrServer;
import com.huiyee.interact.cb.dao.IActivityMatterDao;
import com.huiyee.interact.cb.dao.ICbSenderDao;
import com.huiyee.interact.cb.dto.ActivityMatterDto;
import com.huiyee.interact.cb.mgr.IActivityMatterMgr;
import com.huiyee.interact.cb.model.CbActivityMatter;
import com.huiyee.interact.cb.model.CbSender;
import com.huiyee.weixin.model.WxPageShow;
import com.opensymphony.xwork2.ActionContext;

public class ActivityMatterMgrImpl implements IActivityMatterMgr {
	private IActivityMatterDao activityMatterDao;
	private ICbSenderDao cbSenderDao;
	private IPageDao pageDao;
	private HylogSolrServer hylogSolrServer;
	private IContentNewDao contentNewDao;
	
	public void setContentNewDao(IContentNewDao contentNewDao)
	{
		this.contentNewDao = contentNewDao;
	}

	public void setPageDao(IPageDao pageDao)
	{
		this.pageDao = pageDao;
	}

	public void setHylogSolrServer(HylogSolrServer hylogSolrServer) {
		this.hylogSolrServer = hylogSolrServer;
	}

	public void setCbSenderDao(ICbSenderDao cbSenderDao) {
		this.cbSenderDao = cbSenderDao;
	}

	public void setActivityMatterDao(IActivityMatterDao activityMatterDao) {
		this.activityMatterDao = activityMatterDao;
	}

	@Override
	public ActivityMatterDto findActivityMatterList(long owner, int pageId) {
		ActivityMatterDto dto = new ActivityMatterDto();
		List<CbActivity> cbActivityList = activityMatterDao.findCbActivityList(owner);
		dto.setCbActivityList(cbActivityList);
		return dto;
	}

	@Override
	public ActivityMatterDto findActivityMatterDetail(long owner, long id, long aid, long wxuid) {
		ActivityMatterDto dto = new ActivityMatterDto();
		WxPageShow wxPageShow = activityMatterDao.findActivityMatterDetailById(id,aid);
		if(wxPageShow.getWxshareid()>0){
			WxPageShow s = activityMatterDao.findWxPageShowById(wxPageShow.getWxshareid(),aid);
			wxPageShow.setTitle(s.getTitle());
			wxPageShow.setPic(s.getPic());
			wxPageShow.setDescription(s.getDescription());
		}else if(wxPageShow.getKv()!=null){
			String kv=wxPageShow.getKv();
			Pattern p=Pattern.compile("cn-hy-\\d+");
			Matcher m=p.matcher(kv);
			if(m.matches()){
				String nid=kv.replace("cn-hy-", "");
				ContentNew cn=contentNewDao.findNewById(Long.parseLong(nid),owner);
				wxPageShow.setTitle(cn.getTitle());
				wxPageShow.setPic(cn.getSimgurl());
				wxPageShow.setDescription(cn.getShortdesc());
			}
		}
		long pageid = 0;
		if(wxPageShow != null){
			dto.setWxPageShow(wxPageShow);
			pageid = wxPageShow.getPageid();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		// 昨日
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		String endtime = sdf.format(cal.getTime());
		cal.add(Calendar.DAY_OF_YEAR, -1);
		String starttime = sdf.format(cal.getTime());
		SolrDocument doc = hylogSolrServer.cbSenderEffect(owner, "wx", pageid, wxuid + "", starttime, endtime);
		dto.setClicknum(Integer.parseInt(doc.get("c")+""));
		dto.setZhuanfanum((Integer.parseInt(doc.get("s")+"")));
		dto.setGuanzhunum((Integer.parseInt(doc.get("g")+"")));
		dto.setHudongnum((Integer.parseInt(doc.get("h")+"")));
		// 7天
		cal.add(Calendar.DAY_OF_YEAR, -6);
		starttime = sdf.format(cal.getTime());
		doc = hylogSolrServer.cbSenderEffect(owner, "wx", pageid, wxuid + "", starttime, endtime);
		dto.setSclicknum(Integer.parseInt(doc.get("c")+""));
		dto.setSzhuanfanum((Integer.parseInt(doc.get("s")+"")));
		dto.setSguanzhunum((Integer.parseInt(doc.get("g")+"")));
		dto.setShudongnum((Integer.parseInt(doc.get("h")+"")));
		
		// 总数
		doc = hylogSolrServer.cbSenderEffect(owner, "wx", pageid, wxuid + "", null, null);
		dto.setTclicknum(Integer.parseInt(doc.get("c")+""));
		dto.setTzhuanfanum((Integer.parseInt(doc.get("s")+"")));
		dto.setTguanzhunum((Integer.parseInt(doc.get("g")+"")));
		dto.setThudongnum((Integer.parseInt(doc.get("h")+"")));
		return dto;
	}

	@Override
	public ActivityMatterDto findActivityMatterChildList(long cbid, long aid,long pageid) {
		ActivityMatterDto dto = new ActivityMatterDto();
		int total = activityMatterDao.findTotalActivityMatterList(cbid, aid);
		Page page = pageDao.findPageById(pageid);
		if(page != null){
			page = pageDao.findPageBySiteidAndApptype(page.getSiteid(),"REP");
			if(page != null){
				dto.setPageid(page.getId());
			}
		}
		if (total > 0) {
			List<WxPageShow> list = activityMatterDao.findActivityMatterList(cbid, aid, 0, IPageConstants.CB_SENDER_RECORD);
			if (list != null && list.size() > 0) {
				for (WxPageShow wxPageShow : list)
				{
					if(wxPageShow.getWxshareid()>0){
						WxPageShow s = activityMatterDao.findWxPageShowById(wxPageShow.getWxshareid(),aid);
						wxPageShow.setTitle(s.getTitle());
						wxPageShow.setPic(s.getPic());
						wxPageShow.setDescription(s.getDescription());
					}else if(wxPageShow.getKv()!=null){
						String kv=wxPageShow.getKv();
						Pattern p=Pattern.compile("cn-hy-\\d+");
						Matcher m=p.matcher(kv);
						if(m.matches()){
							String nid=kv.replace("cn-hy-", "");
							ContentNew cn=contentNewDao.findNewById(Long.parseLong(nid),cbid);
							wxPageShow.setTitle(cn.getTitle());
							wxPageShow.setPic(cn.getSimgurl());
							wxPageShow.setDescription(cn.getShortdesc());
						}
						
					}
				}
				
				
				dto.setWxPageShowList(list);
			}
		}
		return dto;
	}

	@Override
	public CbSender findSenderStatus(VisitUser vu, long owner) {
		long cbid = owner;
		List<Long> recordids = activityMatterDao.findCbAptRecord(cbid, vu.getWxuid());
		if (recordids.size() > 0) {
			long rid = recordids.get(0);// 只有一条record申请记录
			CbSender sender = cbSenderDao.findRecordById(cbid, rid);
			return sender;
		}
		return null;
	}
}
