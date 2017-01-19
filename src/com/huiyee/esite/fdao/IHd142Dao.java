package com.huiyee.esite.fdao;

import java.util.Date;
import java.util.List;

import com.huiyee.esite.model.SecurityCodeModel;

public interface IHd142Dao{

	public long saveSecurityCodeCheck(long pageid);
	
	public List<SecurityCodeModel> findSecurityCodeCheckedList(long fid);
	
	public SecurityCodeModel findSecurityCodeRecord(long pageid,long entityid,int type);

	public List<SecurityCodeModel> findSecurityCodeRecord(long pageId, String startTime, String endTime);
}
