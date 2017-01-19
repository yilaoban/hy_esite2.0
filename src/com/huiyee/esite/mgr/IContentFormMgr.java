package com.huiyee.esite.mgr;

import java.util.List;
import java.util.Map;

import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.ContentForm;
import com.huiyee.esite.model.ContentFormMapping;
import com.huiyee.esite.model.ContentFormRecord;

public interface IContentFormMgr
{

	public ContentForm findContentFormByCcid(long ccid);

	public ContentForm findContentFormById(long formid);

	public int updateContentFormSub(long id, ContentForm contentform, List<ContentFormMapping> newList);

	public List<ContentFormMapping> findMappingById(long id);

	public int addContentFormSub(ContentFormRecord record, Account account);

	public List<ContentFormRecord> findRecordByFormid(long id);
	
	public List<Map<String, String>> findRecordByPoint(String x,String y,String tags,int pagesize);

	public int deletFormRecord(long recordid);

	public ContentFormRecord findRecordById(long recordid);

	public int updateFormRecord(ContentFormRecord record, Account account);

	public Map<String, String> findRecordId(long recordid);
}
