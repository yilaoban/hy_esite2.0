package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.esite.model.UserUploadRecord;

public interface IUserUploadRecordDao {

	public List<UserUploadRecord> findUploadRecor(long uploadid,String status);
	
	public void updateStatusbyid(UserUploadRecord r);

	public long addUserUploadRecord(UserUploadRecord record,String ip,String terminal,String source,long pageid);

	public List<UserUploadRecord> findAllUploadRecor(long uploadid);

}
