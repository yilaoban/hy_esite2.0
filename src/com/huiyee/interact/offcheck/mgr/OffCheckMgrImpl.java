
package com.huiyee.interact.offcheck.mgr;

import java.util.List;

import net.sf.json.JSONObject;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dao.IGzEventDao;
import com.huiyee.esite.dao.IPageDao;
import com.huiyee.esite.dao.IWxUserDao;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.mgr.imp.AbstractMgr;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.model.WxGzEvent;
import com.huiyee.esite.model.WxUser;
import com.huiyee.esite.util.JsonStringUtil;
import com.huiyee.interact.appointment.dao.IInteractAptDao;
import com.huiyee.interact.appointment.model.AppointmentModel;
import com.huiyee.interact.offcheck.dao.IOffCheckDao;
import com.huiyee.interact.offcheck.dao.IOffCheckDzWayDao;
import com.huiyee.interact.offcheck.dao.IOffCheckRecordDao;
import com.huiyee.interact.offcheck.dao.IOffCheckSourceDao;
import com.huiyee.interact.offcheck.dto.CheckRs;
import com.huiyee.interact.offcheck.dto.OffCheckDto;
import com.huiyee.interact.offcheck.model.OffCheck;
import com.huiyee.interact.offcheck.model.OffCheckDzWay;
import com.huiyee.interact.offcheck.model.OffCheckLog;
import com.huiyee.interact.offcheck.model.OffCheckRecord;
import com.huiyee.interact.offcheck.model.OffCheckSource;
import com.huiyee.interact.template.model.WxTemplate;
import com.huiyee.interact.template.model.WxTemplateJob;
import com.huiyee.weixin.model.template.JFBDTZ;

public class OffCheckMgrImpl extends AbstractMgr implements IOffCheckMgr
{

	private IOffCheckDao offCheckDao;
	private IOffCheckRecordDao offCheckRecordDao;
	private IOffCheckSourceDao offCheckSourceDao;
	private IInteractAptDao interactAptDao;
	private IPageDao pageDao;
	private IWxUserDao wxUserDao;
	private IGzEventDao gzEventDao;
	private IOffCheckDzWayDao offCheckDzWayDao;

	public void setOffCheckDzWayDao(IOffCheckDzWayDao offCheckDzWayDao)
	{
		this.offCheckDzWayDao = offCheckDzWayDao;
	}

	public void setGzEventDao(IGzEventDao gzEventDao)
	{
		this.gzEventDao = gzEventDao;
	}

	@Override
	public OffCheck findStoreCrmByOwner(long owner)
	{
		OffCheck c = offCheckDao.findStoreCrmByOwner(owner);
		if (c != null)
		{
			AppointmentModel apt = interactAptDao.findOrderMes(c.getAptid());
			if (apt != null)
			{
				apt.setList(interactAptDao.findMappingPre(apt.getId()));
				c.setApt(apt);
			}
		}

		return c;
	}

	@Override
	public long saveStoreCrm(Account account)
	{
		long ownerid = account.getOwner().getId();
		long aptid = interactAptDao.saveSimpleApt(ownerid, "门店Crm_" + ownerid);
		OffCheck crm = new OffCheck();
		crm.setAptid(aptid);
		crm.setOwner(ownerid);
		return offCheckDao.saveScrm(crm);
	}

	@Override
	public IDto findOffCheckSource(Account account, int pageId)
	{
		OffCheckDto dto = new OffCheckDto();
		long ownerid = account.getOwner().getId();
		int total = offCheckSourceDao.findSourceTotalByOwner(ownerid);
		if (total > 0)
		{
			List<OffCheckSource> list = offCheckSourceDao.findSourceByOwner(ownerid, (pageId - 1) * IPageConstants.OFF_CHECK_LIMIT, IPageConstants.OFF_CHECK_LIMIT);
			for (OffCheckSource offCheckSource : list)
			{
				offCheckSource.setSpage(pageDao.findPageById(offCheckSource.getSpageid()));
				offCheckSource.setFpage(pageDao.findPageById(offCheckSource.getFpageid()));
				offCheckSource.setApage(pageDao.findPageById(offCheckSource.getApageid()));
			}
			dto.setSource(list);
			dto.setPager(new Pager(pageId, total, IPageConstants.OFF_CHECK_LIMIT));
		}
		return dto;
	}

	@Override
	public long saveOffCheckSource(Account account, OffCheckSource ofcSource)
	{
		long owner = account.getOwner().getId();
		OffCheck c = offCheckDao.findStoreCrmByOwner(owner);
		ofcSource.setOcid(c.getId());
		ofcSource.setOwner(owner);
		return offCheckSourceDao.saveOffCheckSource(ofcSource);
	}

	@Override
	public long updateOffCheckSource(Account account, OffCheckSource ofcSource)
	{
		long owner = account.getOwner().getId();
		OffCheck c = offCheckDao.findStoreCrmByOwner(owner);
		ofcSource.setOcid(c.getId());
		ofcSource.setOwner(owner);
		 offCheckSourceDao.updateOffCheckSource(ofcSource);
		return ofcSource.getId();
	}

	@Override
	public long delSource(long sourceid, long owner)
	{
		return offCheckSourceDao.delSource(sourceid, owner);
	}

	@Override
	public OffCheckSource findOffCheckSourceById(long sourceid, long owner)
	{
		return offCheckSourceDao.findOffCheckSourceById(sourceid, owner);
	}

	@Override
	public IDto findRecordList(Account account, int pageId, JSONObject sift)
	{
		OffCheckDto dto = new OffCheckDto();
		long owner = account.getOwner().getId();
		OffCheck c = offCheckDao.findStoreCrmByOwner(owner);
		dto.setOfc(c);
		dto.setSource(offCheckSourceDao.findSourceByOwner(owner));
		int total = offCheckRecordDao.findRecordTotal(owner, sift);
		if (total > 0)
		{
			List<OffCheckRecord> list = offCheckRecordDao.findRecordList(owner, sift, (pageId - 1) * IPageConstants.OFF_CHECK_LIMIT, IPageConstants.OFF_CHECK_LIMIT);
			for (OffCheckRecord record : list)
			{
				record.setWxUser(wxUserDao.findWxUserByid(record.getWxuid()));
			}
			dto.setPager(new Pager(pageId, total, IPageConstants.OFF_CHECK_LIMIT));
			dto.setRecords(list);
		}
		return dto;
	}

	@Override
	public IDto findRecordList(Account account, long sourceid, long wxuid, int pageId)
	{
		OffCheckDto dto = new OffCheckDto();
		long owner = account.getOwner().getId();
		WxUser wxUser = wxUserDao.findWxUserByid(wxuid);
		dto.setWxUser(wxUser);
		OffCheckSource source = offCheckSourceDao.findOffCheckSourceById(sourceid, owner);
		dto.setOcs(source);
		int total = offCheckRecordDao.findLogTotal(owner, sourceid, wxuid);
		if (total > 0)
		{
			List<OffCheckLog> logs = offCheckRecordDao.findLogs(owner, sourceid, wxuid, (pageId - 1) * IPageConstants.OFF_CHECK_LIMIT, IPageConstants.OFF_CHECK_LIMIT);
			dto.setLogs(logs);
			dto.setPager(new Pager(pageId, total, IPageConstants.OFF_CHECK_LIMIT));
		}
		return dto;
	}
	
	@Override
	public IDto findOffCheckLogs(Account account, int pageId, JSONObject sift)
	{
		OffCheckDto dto = new OffCheckDto();
		long owner=account.getOwner().getId();
		dto.setSource(offCheckSourceDao.findSourceByOwner(owner));
		int total = offCheckRecordDao.findLogTotal(owner,sift);
		if (total > 0)
		{
			List<OffCheckLog> logs = offCheckRecordDao.findLogs(owner, (pageId - 1) * IPageConstants.OFF_CHECK_LIMIT, IPageConstants.OFF_CHECK_LIMIT,sift);
			if(logs.size()>0){
				for (OffCheckLog offCheckLog : logs)
				{
					offCheckLog.setWxUser(wxUserDao.findWxUserByid(offCheckLog.getWxuid()));
				}
			}
			dto.setLogs(logs);
			dto.setPager(new Pager(pageId, total, IPageConstants.OFF_CHECK_LIMIT));
		}
		return dto;
	}

	public void setOffCheckDao(IOffCheckDao offCheckDao)
	{
		this.offCheckDao = offCheckDao;
	}

	public void setOffCheckRecordDao(IOffCheckRecordDao offCheckRecordDao)
	{
		this.offCheckRecordDao = offCheckRecordDao;
	}

	public void setOffCheckSourceDao(IOffCheckSourceDao offCheckSourceDao)
	{
		this.offCheckSourceDao = offCheckSourceDao;
	}

	public void setInteractAptDao(IInteractAptDao interactAptDao)
	{
		this.interactAptDao = interactAptDao;
	}

	public void setPageDao(IPageDao pageDao)
	{
		this.pageDao = pageDao;
	}

	public void setWxUserDao(IWxUserDao wxUserDao)
	{
		this.wxUserDao = wxUserDao;
	}

	@Override
	public CheckRs updateChecking(long sourceid, VisitUser user,String ip,long owner)
	{
		CheckRs rs=new CheckRs();
		String area=this.findAreaByIp(ip);
		if(user==null||user.getWxUser()==null){
			rs.setStatus(-1);
			rs.setHydesc("用户不存在！");
			return rs;
		}
		OffCheckSource os=offCheckSourceDao.findOffCheckSourceById(sourceid,owner);
		if(os==null)
		{
			rs.setStatus(0);
			rs.setHydesc("来源不存在！");
			return rs;	
		}
//         if(offCheckDao.findUserAptRecord(owner, user.getWxUser().getId())==0)
//         {
//        	rs.setStatus(-3);
// 			rs.setHydesc("还没有注册会员！");
// 			rs.setPageid(os.getApageid());
// 			return rs;	 
//         }
		
		if(os.getFensi().equals("Y")&&user.getWxUser().getSubscribe()==0)
		{
			WxGzEvent gz=gzEventDao.findGzEvent(os.getGzeid());
			if(gz!=null)
			{
			rs.setUrl(gz.getLink());
			gzEventDao.addSysKeywords(user.getWxUser().getOpenid(), gz.getMsg().getId(), sourceid,3);
			}
			rs.setStatus(-2);
			//rs.setPageid(os.getFpageid());
			rs.setHydesc("必须粉丝才可以参加！");
          return rs;			
		}
		long wxuid = user.getWxUser().getId();
		long rid = offCheckRecordDao.findLogCurDate(wxuid, sourceid);
		if (rid > 0)
		{
			offCheckRecordDao.addRecord(wxuid, ip, sourceid, area, 0);
			offCheckRecordDao.updateLogCurDate(rid, area, ip);
		} else
		{
			offCheckRecordDao.addRecord(wxuid, ip, sourceid, area, 1);
			offCheckRecordDao.addRecordLog(wxuid, ip,sourceid, area);
		}
		rs.setStatus(1);
		rs.setPageid(os.getSpageid());
		rs.setHydesc("签到成功！ ");
		this.updateHyUserBalance(user.getHyUserId(), "OFC", owner, sourceid);
		return rs;
	}
	
	@Override
	public CheckRs updateDtChecking(long id, VisitUser user,String ip,long owner)
	{
		CheckRs rs=new CheckRs();
		String area=this.findAreaByIp(ip);
		if(user==null||user.getWxUser()==null){
			rs.setStatus(-1);
			rs.setHydesc("用户不存在！");
			return rs;
		}
		OffCheckDzWay dzway=offCheckDzWayDao.findDzWay(id);
		if(dzway==null)
		{
			rs.setStatus(-10001);
			rs.setHydesc("动态二维码不存在！");
			return rs;	
		}
		if(dzway.getStatus()==1){
			rs.setStatus(-10002);
			rs.setHydesc("二维码已经使用！");
			return rs;	
		}
        if(dzway.getEndtime().getTime()<System.currentTimeMillis()){
        	rs.setStatus(-10003);
			rs.setHydesc("二维码已经过期！");
			return rs;	
		}
		
		OffCheckSource os=offCheckSourceDao.findOffCheckSourceById(dzway.getCsid(),owner);
		if(os==null)
		{
			rs.setStatus(0);
			rs.setHydesc("来源不存在！");
			return rs;	
		}
		if(os.getFensi().equals("Y")&&user.getWxUser().getSubscribe()==0)
		{
			WxGzEvent gz=gzEventDao.findGzEvent(os.getGzeid());
			if(gz!=null)
			{
			rs.setUrl(gz.getLink());
			gzEventDao.addSysKeywords(user.getWxUser().getOpenid(), gz.getMsg().getId(), id,3);
			}
			rs.setStatus(-2);
			rs.setHydesc("必须粉丝才可以参加！");
          return rs;			
		}
		long wxuid = user.getWxUser().getId();
		long rid = offCheckRecordDao.findLogCurDate(wxuid, dzway.getCsid());
		if (rid > 0)
		{
			offCheckRecordDao.addRecord(wxuid, ip, dzway.getCsid(), area, 0);
			offCheckRecordDao.updateLogCurDate(rid, area, ip);
		} else
		{
			offCheckRecordDao.addRecord(wxuid, ip, dzway.getCsid(), area, 1);
			offCheckRecordDao.addRecordLog(wxuid, ip, dzway.getCsid(), area);
		}
		rs.setStatus(1);
		rs.setHydesc("签到成功！ ");
		rs.setPageid(os.getJfpageid());
		rs.setRmb(dzway.getRmb());
		this.updateHyUserBalance(user.getHyUserId(), "OFC", owner, dzway.getCsid());
		int balance=dzway.getRmb()/(os.getRmbjf()*100);
		offCheckDzWayDao.updateDzWay(dzway.getId(), wxuid, balance);//消亡二维码
		if (owner > 0)
		{
			List<WxTemplate> wts = this.findWxTemplate(owner,"XFJ",dzway.getCsid());	
			if (wts != null&&wts.size()>0&&user!=null&&user.getWxUser()!=null)
			{
				for(WxTemplate wt:wts)
				{
				JFBDTZ dd = (JFBDTZ) JsonStringUtil.String2Obj(wt.getData(), JFBDTZ.class);
				dd.setFieldName("积分账户");
				dd.setAccount(user.getWxUser().getNickname());
				dd.setChange("获得消费");
				dd.setCreditChange(balance+"积分");
				dd.setCreditTotal(this.findJFen(user.getHyUserId())+balance+"积分");
				WxTemplateJob wj = new WxTemplateJob();
				wj.setMpid(wt.getMpid());
				wj.setType("XFJ");
				wj.setEntityid(wt.getEntityid());
				wj.setRemark(wt.getRemark());
				wj.setTemplate_id(wt.getTemplate_id());
				wj.setTouser(user.getWxUser().getOpenid());
				wj.setData(dd.toData());
				wj.setUrl(wt.getUrl());
				this.addTmplMsg(wj);
				}
			}
		}
		this.updateBalance(user.getHyUserId(), balance, "消费"+dzway.getRmb()/100+"元获得"+balance+"积分", "OFC", "XFJ", dzway.getCsid());
		rs.setBalance(balance);
		return rs;
	}

}
