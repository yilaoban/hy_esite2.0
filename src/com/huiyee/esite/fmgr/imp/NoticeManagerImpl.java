package com.huiyee.esite.fmgr.imp;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.Feature104Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.fdao.INoticeDao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.service.FileUploadService;

public class NoticeManagerImpl extends AbstractFeatureManager {
	
	private INoticeDao noticeDao;

	@Override
	public IDto config(long fid) {
		Feature104Dto dto = new Feature104Dto();
		if(fid > 0){
			dto.setNotice(noticeDao.findNoticeById(fid));
		}
		return dto;
	}

	public void setNoticeDao(INoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

	@Override
	public String configSub(long featureid,IDto dto,Account account) {
		Feature104Dto  d = (Feature104Dto)dto;
		if(d.getImg() != null){
			String path = FileUploadService.getFilePath(IPageConstants.TYPE_FEATURE, account.getOwner().getId(), featureid+"");
			String name = FileUploadService.createFileName(d.getImgFileName());
			FileUploadService.saveFile(d.getImg(), path , name);
			d.getNotice().setImgurl(path +"/"+name);
		}
		int result =noticeDao.updateNotice(d.getNotice());	
		if(result == 1){
			return IPageConstants.FEATURE_MGR_RESULT_SUCCESS;
		}
		return "cucuo!!!!";
	}

	@Override
	public long add(long pageid, long featureid,String featurename) {
		long fid = noticeDao.addNotice();
		return getPageFeatureDao().addPageFeature(pageid, featureid, fid,featurename,"N");
	}

	@Override
	public IDto config(long fid, Account account) {
		return config(fid);
	}

}
