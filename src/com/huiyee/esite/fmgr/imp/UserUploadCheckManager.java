package com.huiyee.esite.fmgr.imp;

import java.util.List;

import com.huiyee.esite.dto.Feature107Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.fdao.IUserUploadCheckListDao;
import com.huiyee.esite.fdao.IUserUploadRecordDao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.UserUploadCheckList;
import com.huiyee.esite.model.UserUploadRecord;

public class UserUploadCheckManager extends AbstractFeatureManager {
	private IUserUploadCheckListDao userUploadCheckListDao;
	private IUserUploadRecordDao userUploadRecordDao;

	public void setUserUploadCheckListDao(IUserUploadCheckListDao userUploadCheckListDao) {
		this.userUploadCheckListDao = userUploadCheckListDao;
	}

	public void setUserUploadRecordDao(IUserUploadRecordDao userUploadRecordDao) {
		this.userUploadRecordDao = userUploadRecordDao;
	}

	public long add(long pageid, long featureid,String featurename) {
		long fid = userUploadCheckListDao.saveCheckList(pageid);
		return getPageFeatureDao().addPageFeature(pageid, featureid, fid,featurename,"N");
	}

	public IDto config(long fid, Account account) {
		UserUploadCheckList checklist = userUploadCheckListDao.findUploadId(fid);
		Feature107Dto dto = new Feature107Dto();
		dto.setFid(fid);
		dto.setFidList(userUploadCheckListDao.findUserUploadListByPageid(checklist.getPageid()));
		long uploadid = 0;
		if ((uploadid = checklist.getUploadid()) != 0) {
			dto.setRecord(userUploadRecordDao.findAllUploadRecor(uploadid));
			dto.setUploadid(uploadid);
		}
		return dto;

	}

	public IDto config(long fid) {
		Feature107Dto dto = new Feature107Dto();
		UserUploadCheckList checklist = userUploadCheckListDao.findUploadId(fid);
		dto.setRecord(userUploadRecordDao.findUploadRecor(checklist.getUploadid(), "CMP"));
		return dto;
	}

	public String configSub(long featureid, IDto feature107Dto, Account account) {
		Feature107Dto dto = (Feature107Dto) feature107Dto;
		UserUploadCheckList checklist = userUploadCheckListDao.findUploadId(dto.getFid());
		if (dto.getUploadid() != 0) {
			if (dto.getUploadid() == checklist.getUploadid()) {
				List<UserUploadRecord> record = dto.getRecord();
				for (UserUploadRecord r : record) {
					if (r != null && r.getStatus() != "EDT") {
						userUploadRecordDao.updateStatusbyid(r);
					}
				}
			} else {
				userUploadCheckListDao.updateUploadid(dto.getFid(), dto.getUploadid());
				return "P";
			}
		} else {
			return "您还没有选择上传点";
		}
		return "Y";
	}

}
