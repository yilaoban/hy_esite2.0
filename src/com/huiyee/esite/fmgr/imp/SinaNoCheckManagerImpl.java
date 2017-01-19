package com.huiyee.esite.fmgr.imp;

import com.huiyee.esite.dto.Feature115Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.showdto.Show114Dto;
import com.huiyee.esite.dto.showdto.Show115Dto;
import com.huiyee.esite.fdao.ISinaShareDao;
import com.huiyee.esite.fdao.ISinaShareListNoCheckDao;
import com.huiyee.esite.fdao.ISinaShareListRecordDao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.SinaCheckList;
import com.huiyee.esite.model.SinaNoCheckList;

public class SinaNoCheckManagerImpl extends AbstractFeatureManager {
	private ISinaShareListNoCheckDao sinaShareListNoCheckDao;
	private ISinaShareListRecordDao sinaShareListRecordDao;
	private ISinaShareDao sinaShareDao;

	public void setSinaShareDao(ISinaShareDao sinaShareDao) {
		this.sinaShareDao = sinaShareDao;
	}

	public void setSinaShareListRecordDao(ISinaShareListRecordDao sinaShareListRecordDao) {
		this.sinaShareListRecordDao = sinaShareListRecordDao;
	}

	public void setSinaShareListNoCheckDao(ISinaShareListNoCheckDao sinaShareListNoCheckDao) {
		this.sinaShareListNoCheckDao = sinaShareListNoCheckDao;
	}

	@Override
	public long add(long pageid, long featureid,String featurename) {
		long fid = sinaShareListNoCheckDao.save(pageid);
		return getPageFeatureDao().addPageFeature(pageid, featureid, fid,featurename,"N");
	}

	@Override
	public IDto config(long fid) {

		SinaNoCheckList share = sinaShareListNoCheckDao.findShareid(fid);
		Show115Dto dto = new Show115Dto();
		dto.setFid(fid);
		long shareid = 0;
		if ((shareid = share.getShareid()) != 0) {
			dto.setCheckListRecord(sinaShareListRecordDao.findNoCheckRecordByShareid(shareid,share.getSize()));
		}
		return dto;
	}

	public IDto config(long fid, Account account) {

		Feature115Dto dto = new Feature115Dto();
		SinaNoCheckList share = sinaShareListNoCheckDao.findShareid(fid);
		dto.setSnl(share);
		dto.setFid(fid);
		dto.setChecklist(sinaShareDao.findSinaShareListByPageId(share.getPageid()));
		return dto;
	}

	@Override
	public String configSub(long featureid, IDto feature115Dto, Account account) {

		Feature115Dto dto = (Feature115Dto) feature115Dto;
		if (dto.getSnl().getShareid() != 0) {
			sinaShareListNoCheckDao.updateSnl(dto.getSnl());
		} else {
			return "您还没有选择是哪个分享表单";
		}
		return "Y";
	}
}
