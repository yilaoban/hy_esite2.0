
package com.huiyee.interact.cb.mgr.impl;

import java.util.Map;

import com.huiyee.esite.dto.HdRsDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.AppointmentRecord;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.appointment.dao.IInteractAptDao;
import com.huiyee.interact.appointment.model.AppointmentModel;
import com.huiyee.interact.cb.dao.ICbSenderDao;
import com.huiyee.interact.cb.dao.IInteractCbDao;
import com.huiyee.interact.cb.mgr.IInteractCbMgr;
import com.huiyee.interact.cb.model.CbSender;
import com.huiyee.interact.cb.model.InteractCb;

public class InteractCbMgrImpl implements IInteractCbMgr
{

	private IInteractCbDao interactCbDao;
	private IInteractAptDao interactAptDao;
	private ICbSenderDao cbSenderDao;

	public void setCbSenderDao(ICbSenderDao cbSenderDao)
	{
		this.cbSenderDao = cbSenderDao;
	}

	public void setInteractCbDao(IInteractCbDao interactCbDao)
	{
		this.interactCbDao = interactCbDao;
	}

	public void setInteractAptDao(IInteractAptDao interactAptDao)
	{
		this.interactAptDao = interactAptDao;
	}

	@Override
	public InteractCb findCbbyOwner(long owner)
	{
		InteractCb cb = interactCbDao.findCbByOwner(owner);
		if (cb != null && cb.getAptid() != 0)
		{
			AppointmentModel apt = interactAptDao.findOrderMes(cb.getAptid());
			if (apt != null)
			{
				apt.setList(interactAptDao.findMappingPre(apt.getId()));
				cb.setApt(apt);
			}
		}
		return cb;
	}
	
	@Override
	public InteractCb findCbbyid(Account account, long cbid)
	{
		InteractCb cb = interactCbDao.findCbById(cbid);
		if (cb != null && cb.getAptid() != 0)
		{
			AppointmentModel apt = interactAptDao.findOrderMes(cb.getAptid());
			if (apt != null)
			{
				apt.setList(interactAptDao.findMappingPre(apt.getId()));
				cb.setApt(apt);
			}
		}
		return cb;
	}

	@Override
	public InteractCb findOnlyCbbyid(long cbid)
	{
		return interactCbDao.findCbById(cbid);
	}

	@Override
	public AppointmentModel findAppointById(long aptid)
	{
		return interactAptDao.findOrderMes(aptid);
	}

	@Override
	public HdRsDto saveCustomCommentRepotr(VisitUser vu, AppointmentRecord aptc, String ip, String terminal, String source, long relationid, AppointmentModel apt, long owner)
	{
		HdRsDto rs = new HdRsDto();
		rs.setStatus(1);
		rs.setHydesc("提交成功！");
		long uid = vu.getUid();
		int type = vu.getCd();

		/*
		 * 查看次uid是否已经成为合伙人 或已 取消合伙人资格
		 */
		long cbid = owner;
		CbSender sender = cbSenderDao.findSenderByUId(owner, uid);
		if(sender==null||"ERR".equals(sender.getStatus())){
			interactAptDao.delAptRecordByuid(apt.getId(), uid);
			cbSenderDao.delSender(owner, uid);
		}else if ("CMP".equals(sender.getStatus()))
		{
			rs.setStatus(-1);
			rs.setHydesc("已经是合伙人！");
		} else if ("FAL".equals(sender.getStatus()))
		{
			rs.setStatus(-2);
			rs.setHydesc("已经取消合伙人资格！");
		}
		
		if(rs.getStatus()==1){
			rs.setId(interactCbDao.findCbStatusPageid(aptc.getPageid()));
			interactAptDao.saveCustomCommentRepotr(uid, aptc, ip, terminal, source, type);
			InteractCb cb=interactCbDao.findCbById(cbid);
			if(cb.getSenderAccept()==1){
				sender = cbSenderDao.findRecordByUid(cbid, uid);
				cbSenderDao.updateCbSenderSub(sender, "CMP", "", owner);
			}
		}
		return rs;
	}
	
	@Override
	public int updateAptAcpt(Account account, int status)
	{
		long cbid=account.getOwner().getId();
		return interactCbDao.updateAptAcpt(cbid,status);
	}
	
	@Override
	public int updateUsedSiteGroup(long owner, Map<String, Long> map)
	{
		return interactCbDao.updateUsedSiteGroup(owner,map);
	}
}
