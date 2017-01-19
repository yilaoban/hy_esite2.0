package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.esite.model.SinaChecklistRecord;
import com.huiyee.esite.model.SinaShareRecordCategory;

public interface ISinaShareRecordCheckListDao {
	
	public long saveSinaShareReocrdCheckList(long pageid);
	
	public List<SinaChecklistRecord> findSinaCheckListRecordByFid(long fid);
	
	public List<SinaShareRecordCategory> findSinaShareRecordCategoryByFid(long fid);
	
	public List<SinaChecklistRecord> findSinaChecklistRecordByCategoryId(long cid);
	
	public int saveSinaShareRecordCheckList(long recordid,long categoryid,int idx);
	
	public int deleteSinaShareRecordChekcList(long fid);
	
}
