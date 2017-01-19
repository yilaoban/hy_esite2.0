package com.huiyee.esite.mgr.imp;

import com.huiyee.esite.fdao.IUserUploadDao;
import com.huiyee.esite.mgr.IUserUploadManager;
import com.huiyee.esite.model.UserUpload;

public class UserUploadManagerImpl implements IUserUploadManager {
	
	private IUserUploadDao userUploadDao;

	public void setUserUploadDao(IUserUploadDao userUploadDao) {
		this.userUploadDao = userUploadDao;
	}

	@Override
	public UserUpload findUserUpload(long id) {
		return userUploadDao.findUserUpload(id);
	}

}
