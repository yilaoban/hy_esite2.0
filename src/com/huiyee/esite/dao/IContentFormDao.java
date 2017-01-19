package com.huiyee.esite.dao;

import java.util.List;

import com.huiyee.esite.model.ContentForm;
import com.huiyee.esite.model.ContentFormMapping;
import com.huiyee.esite.model.ContentFormRecord;

public interface IContentFormDao
{
	public long addForm(long owner, String title, long catid);

	public long addFormMapping(long formid);

	public ContentForm findContentFormByCcid(long ccid);

	public ContentForm findContentFormById(long formid);

	public List<ContentFormMapping> findMappingById(long formid);

	public int updateMappingClean(long formid);

	public int addFormMapping(long formid, List<ContentFormMapping> list);

	public List<ContentFormMapping> findMappingOrderByRow(long formid);

	public long addContentFormSub(ContentFormRecord record);

	public List<ContentFormRecord> findRecordByFormid(long formid);

	public int deletFormRecord(long recordid);

	public ContentFormRecord findRecordById(long recordid);

	public int updateFormRecord(ContentFormRecord record);

	public void updateLbsid(long lbsid, long recordid);

}
