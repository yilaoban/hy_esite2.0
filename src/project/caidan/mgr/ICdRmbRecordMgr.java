package project.caidan.mgr;

import java.util.List;

import project.caidan.model.CdRmbRecord;


public interface ICdRmbRecordMgr
{
	public List<CdRmbRecord> findListForPage(long wxuid,int pageId,int size);
}
