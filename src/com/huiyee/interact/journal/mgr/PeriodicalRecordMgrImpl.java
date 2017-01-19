package com.huiyee.interact.journal.mgr;

import java.util.List;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.interact.journal.dao.IPeriodicalRecordDao;
import com.huiyee.interact.journal.dto.IDto;
import com.huiyee.interact.journal.dto.PeriodicalRecordDto;
import com.huiyee.interact.journal.model.JournalContent;
import com.huiyee.interact.journal.model.JournalShareRecord;
import com.huiyee.interact.journal.model.Pager;



public class PeriodicalRecordMgrImpl implements IPeriodicalRecordMgr{
	
	private IPeriodicalRecordDao periodicalRecordDao;

	public void setPeriodicalRecordDao(IPeriodicalRecordDao periodicalRecordDao)
	{
		this.periodicalRecordDao = periodicalRecordDao;
	}

	@Override
	public IDto findJournalContentByJ(long jid, int pageid)
	{
		PeriodicalRecordDto prdto = new PeriodicalRecordDto();
		int total = periodicalRecordDao.findPeriodicalRecordTotal(jid);
		if (total > 0)
		{
			List<JournalContent> list = periodicalRecordDao.findJournalContentByJ((pageid - 1) * IInteractConstants.JOURNALRECORD_LIMIT, IInteractConstants.JOURNALRECORD_LIMIT, jid);
			prdto.setJournalContent(list);
			prdto.setPager(new Pager(pageid, total, IInteractConstants.JOURNALRECORD_LIMIT));
		}
		return prdto;
	}

	@Override
	public IDto findJournalSR(long contentid, int pageid)
	{
		PeriodicalRecordDto prdto = new PeriodicalRecordDto();
		List<JournalShareRecord> list2 = periodicalRecordDao.bindsource();
		prdto.setBindsource(list2);
		int total = periodicalRecordDao.findJournalSRTotal(contentid);
		if (total > 0)
		{
			List<JournalShareRecord> list = periodicalRecordDao.findJournalSR((pageid - 1) * IInteractConstants.JOURNALRECORD_LIMIT, IInteractConstants.JOURNALRECORD_LIMIT, contentid);
			prdto.setJournalShareRecord(list);
			prdto.setPager(new Pager(pageid, total, IInteractConstants.JOURNALRECORD_LIMIT));
		}
		return prdto;
	}

	@Override
	public IDto searchnickname(String nickname, long contentid, int pageid)
	{
		PeriodicalRecordDto prdto = new PeriodicalRecordDto();
		List<JournalShareRecord> list2 = periodicalRecordDao.bindsource();
		prdto.setBindsource(list2);
		int total = periodicalRecordDao.searchnicknameTotal(nickname,contentid);
		if (total > 0)
		{
			List<JournalShareRecord> list = periodicalRecordDao.searchnickname((pageid - 1) * IInteractConstants.JOURNALRECORD_LIMIT, IInteractConstants.JOURNALRECORD_LIMIT, nickname,contentid);
			prdto.setJournalShareRecord(list);
			prdto.setPager(new Pager(pageid, total, IInteractConstants.JOURNALRECORD_LIMIT));
		}
		return prdto;
	}

	@Override
	public IDto searchtimeorts(String begintime, String endtime, String terminal, String source, long contentid, int pageid)
	{
		PeriodicalRecordDto prdto = new PeriodicalRecordDto();
		List<JournalShareRecord> list2 = periodicalRecordDao.bindsource();
		prdto.setBindsource(list2);
		int total = periodicalRecordDao.searchtimeortsTotal(begintime,endtime,terminal,source,contentid);
		if (total > 0)
		{
			List<JournalShareRecord> list = periodicalRecordDao.searchtimeorts((pageid - 1) * IInteractConstants.JOURNALRECORD_LIMIT, IInteractConstants.JOURNALRECORD_LIMIT,begintime,endtime,terminal,source,contentid);
			prdto.setJournalShareRecord(list);
			prdto.setPager(new Pager(pageid, total, IInteractConstants.JOURNALRECORD_LIMIT));
		}
		return prdto;
	}
	

}
