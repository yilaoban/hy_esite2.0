package com.huiyee.esite.fdao;

import com.huiyee.esite.model.UserUpload;

public interface IUserUploadDao {

	public UserUpload findUserUpload(long fid);
	
	public long addFeatureUserUpload(long pageid);
	
	public int updateFeatureUserUpload(UserUpload userUpload);
}
