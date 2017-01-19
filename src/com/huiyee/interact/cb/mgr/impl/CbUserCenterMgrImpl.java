package com.huiyee.interact.cb.mgr.impl;

import java.util.List;

import com.huiyee.esite.mgr.imp.AbstractMgr;
import com.huiyee.interact.appointment.model.AppointmentDataModel;
import com.huiyee.interact.cb.dao.ICbUserCenterDao;
import com.huiyee.interact.cb.dto.HbRecordDto;
import com.huiyee.interact.cb.mgr.ICbUserCenterMgr;
import com.huiyee.interact.cb.model.CbHbRecord;
import com.huiyee.interact.cb.model.CbSender;
import com.huiyee.interact.cb.model.InteractCb;

public class CbUserCenterMgrImpl extends AbstractMgr implements ICbUserCenterMgr {
	private ICbUserCenterDao cbUserCenterDao;
	public void setCbUserCenterDao(ICbUserCenterDao cbUserCenterDao) {
		this.cbUserCenterDao = cbUserCenterDao;
	}

	@Override
	public HbRecordDto findCbSenderByHyuid(long hyuid,long owner) {
		HbRecordDto dto = new HbRecordDto();
		InteractCb cb = cbUserCenterDao.findCbByOwner(owner);
		CbSender cbSender = cbUserCenterDao.findCbSenderByHyuid(hyuid);
		dto.setCb(cb);
		dto.setCbSender(cbSender);
		return dto;
	}

	@Override
	public HbRecordDto findHbCbSendByHyuid(long hyuid) {
		HbRecordDto dto = new HbRecordDto();
		List<CbHbRecord> list = cbUserCenterDao.findHbCbSendByHyuid(hyuid);
		if(list != null && list.size()>0){
			for(int i=0;i<list.size();i++){
				List<CbHbRecord> rlist = cbUserCenterDao.findHbCbSendByHyuidAndTime(hyuid,list.get(i).getCreatetime());
				list.get(i).setRlist(rlist);
			}
			dto.setCbHbRecordList(list);
		}
		return dto;
	}

	@Override
	public HbRecordDto findMySender(long hyuid,long pageid,long owner) {
		HbRecordDto dto = new HbRecordDto();
		List<CbSender> list = cbUserCenterDao.findMySender(hyuid);
		if(list.size() > 0){
			dto.setCbSenderList(list);
			for(int i=0;i<list.size();i++){
				int jf = this.findJFen(list.get(i).getHyuid());
				list.get(i).setJf(jf);
			}
		}
		InteractCb cb = cbUserCenterDao.findCbByOwner(owner);
		dto.setCb(cb);
		return dto;
	}

	@Override
	public HbRecordDto findSenderlist(long owner) {
		HbRecordDto dto = new HbRecordDto();
		List<CbSender> list = cbUserCenterDao.findSenderlist(owner);
		if(list.size() > 0){
			dto.setCbSenderList(list);
		}
		return dto;
	}

	@Override
	public int saveCbSenderAndRecord(long hyuid, long owner, long wxuid,AppointmentDataModel aptRecord,long fatherid) {
		CbSender cbSender = cbUserCenterDao.findCbSenderByHyuid(hyuid);
		if(cbSender != null){
			return -1;
		}
		InteractCb cb = cbUserCenterDao.findCbByOwner(owner);
		aptRecord.setAptid(cb.getAptid());
		long recordid = 0;
		AppointmentDataModel model = cbUserCenterDao.findAptRecordByWxuid(cb.getAptid(), wxuid, 1);
		if (model != null) {
			recordid = model.getId();
			cbUserCenterDao.updateInteractAptRecord(wxuid, aptRecord, 1, model.getId());
		} else {
			recordid = cbUserCenterDao.saveInteractAptRecord(wxuid, aptRecord, 1);
		}
		int pizhun = cb.getPizhun();
		String status = "EDT";
		if(pizhun == 1){
			status = "CMP";
		}
		return cbUserCenterDao.saveCbSender(hyuid,wxuid,cb.getAptid(),recordid,status,owner,fatherid);
	}

	@Override
	public int saveCbHongbaoSend(long owner, long hyuid, String openid, long mpid, int rmb) {
		cbUserCenterDao.updateCbUsed(hyuid, rmb);
		return cbUserCenterDao.saveCbHongbaoSend(owner, hyuid, openid, mpid, rmb);
	}

	@Override
	public HbRecordDto findMyHuobi(long hyuid, long owner) {
		HbRecordDto dto = new HbRecordDto();
		CbSender cbSender = cbUserCenterDao.findCbSenderByHyuid(hyuid);
		int jf = this.findJFen(hyuid);
		if(cbSender != null){
			cbSender.setJf(jf);
		}
		dto.setCbSender(cbSender);
		return dto;
	}
	
}
