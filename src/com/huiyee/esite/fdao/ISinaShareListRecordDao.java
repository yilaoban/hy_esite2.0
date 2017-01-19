package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.esite.model.SinaChecklistRecord;

public interface ISinaShareListRecordDao {

	public List<SinaChecklistRecord> findRecordByShareId(long shareid);

	public void updateStatusbyid(SinaChecklistRecord r);

	public List<SinaChecklistRecord> findCMPRecordByPageId(long pageid);

	public List<SinaChecklistRecord> findCMPRecordByShareId(long id);

	public List<SinaChecklistRecord> findNoCheckRecordByShareid(long id, int size);


}
