package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.esite.model.UserUpload;
import com.huiyee.esite.model.UserUploadCheckList;

public interface IUserUploadCheckListDao {

	public long saveCheckList(long pageid);

	public UserUploadCheckList findUploadId(long fid);

	public List<UserUpload> findUserUploadListByPageid(long pageid);

	public void updateUploadid(long fid, long uploadid);

}
