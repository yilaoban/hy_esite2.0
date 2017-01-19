package com.huiyee.esite.fmgr.imp;

import java.util.List;

import com.huiyee.esite.dto.Feature114Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.showdto.Show114Dto;
import com.huiyee.esite.fdao.ISinaShareDao;
import com.huiyee.esite.fdao.ISinaShareListCheckDao;
import com.huiyee.esite.fdao.ISinaShareListRecordDao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.SinaCheckList;
import com.huiyee.esite.model.SinaChecklistRecord;

public class SinaCheckManagerImpl extends AbstractFeatureManager {
	private ISinaShareListCheckDao sinaShareListCheckDao;
	private ISinaShareListRecordDao sinaShareListRecordDao;
	private ISinaShareDao sinaShareDao;

	public void setSinaShareDao(ISinaShareDao sinaShareDao) {
		this.sinaShareDao = sinaShareDao;
	}

	public void setSinaShareListRecordDao(ISinaShareListRecordDao sinaShareListRecordDao) {
		this.sinaShareListRecordDao = sinaShareListRecordDao;
	}

	public void setSinaShareListCheckDao(ISinaShareListCheckDao sinaShareListCheckDao) {
		this.sinaShareListCheckDao = sinaShareListCheckDao;
	}

	@Override
	public long add(long pageid, long featureid,String featurename) {
		long fid = sinaShareListCheckDao.saveCheckList(pageid);
		return getPageFeatureDao().addPageFeature(pageid, featureid, fid,featurename,"N");
	}

	@Override
	public IDto config(long fid) {
		SinaCheckList share = sinaShareListCheckDao.findShareid(fid);
		Show114Dto dto = new Show114Dto();
		long shareid = 0;
		if ((shareid = share.getShareid()) != 0) {
			dto.setCheckListRecord(sinaShareListRecordDao.findCMPRecordByShareId(shareid));
		}
		return dto;
	}

	public IDto config(long fid, Account account) {
		SinaCheckList share = sinaShareListCheckDao.findShareid(fid);
		Feature114Dto dto = new Feature114Dto();
		dto.setFid(fid);
		dto.setChecklist(sinaShareDao.findSinaShareListByPageId(share.getPageid()));
		long shareid = 0;
		if ((shareid = share.getShareid()) != 0) {
			dto.setCheckListRecord(sinaShareListRecordDao.findRecordByShareId(shareid));
			dto.setShareid(shareid);
		}
		return dto;
	}

	@Override
	public String configSub(long featureid, IDto feature114Dto, Account account) {
		Feature114Dto dto = (Feature114Dto) feature114Dto;
		SinaCheckList share = sinaShareListCheckDao.findShareid(dto.getFid());
		if (dto.getShareid() != 0) {
			if (dto.getShareid() == share.getShareid()) {
				List<SinaChecklistRecord> record = dto.getCheckListRecord();
				for (SinaChecklistRecord r : record) {
					if (r != null && r.getStatus() != "EDT") {
						sinaShareListRecordDao.updateStatusbyid(r);
					}
				}
			} else {
				sinaShareListCheckDao.updateShareId(dto.getFid(), dto.getShareid());
				return "P";
			}
		} else {
			return "您还没有选择是哪个分享表单";
		}
		return "Y";
	}

}
