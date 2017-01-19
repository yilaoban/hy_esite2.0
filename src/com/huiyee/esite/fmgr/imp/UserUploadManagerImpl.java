package com.huiyee.esite.fmgr.imp;

import com.huiyee.esite.dto.DynamicActionDto;
import com.huiyee.esite.dto.Feature105Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.fdao.IUserUploadDao;
import com.huiyee.esite.fdao.IUserUploadRecordDao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.UserInfo;

public class UserUploadManagerImpl extends AbstractFeatureManager {

	private IUserUploadDao userUploadDao;
	private IUserUploadRecordDao userUploadRecordDao;

	@Override
	public IDto config(long fid) {
		Feature105Dto dto = new Feature105Dto();
		dto.setUserUpload(userUploadDao.findUserUpload(fid));
		return dto;
	}

	@Override
	public String configSub(long featureid,IDto dto,Account account) {
		Feature105Dto  d = (Feature105Dto)dto;
		int result = userUploadDao.updateFeatureUserUpload(d.getUserUpload());
		if(result != 1){
			return "配置失败!系统发生错误!";
		}
		return "Y";
	}

	@Override
	public long add(long pageid, long featureid,String featurename) {
		long upid=userUploadDao.addFeatureUserUpload(pageid);
		return getPageFeatureDao().addPageFeature(pageid, featureid, upid,featurename,"N");
	}

	@Override
	public IDto config(long fid, Account account) {
		return config(fid);
	}

	public void setUserUploadDao(IUserUploadDao userUploadDao) {
		this.userUploadDao = userUploadDao;
	}

	@Override
	public String dynamicAction(long uid, DynamicActionDto dto) {
		if(uid==0){
			return "Y";
		}
		long entityid = userUploadRecordDao.addUserUploadRecord(dto.getUserUploadRecord(),dto.getIp(),dto.getTerminal(),dto.getSource(),dto.getPageid());
		return "Y";
	}

	public void setUserUploadRecordDao(IUserUploadRecordDao userUploadRecordDao) {
		this.userUploadRecordDao = userUploadRecordDao;
	}
}
