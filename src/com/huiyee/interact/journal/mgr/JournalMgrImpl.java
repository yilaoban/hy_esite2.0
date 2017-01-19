package com.huiyee.interact.journal.mgr;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.mgr.imp.AbstractMgr;
import com.huiyee.interact.journal.dao.IJournalDao;
import com.huiyee.interact.journal.dto.IDto;
import com.huiyee.interact.journal.dto.JournalDto;
import com.huiyee.interact.journal.model.JournalContent;
import com.huiyee.interact.journal.model.JournalModel;
import com.huiyee.interact.journal.model.Pager;
import com.huiyee.tfmodel.HyWbSrc;

public class JournalMgrImpl extends AbstractMgr implements IJournalMgr
{

	private IJournalDao journalDao;
	public IJournalDao getJournalDao()
	{
		return journalDao;
	}

	public void setJournalDao(IJournalDao journalDao)
	{
		this.journalDao = journalDao;
	}
	
	@Override
	public IDto findJournalList(long ownerid, int start, int size)
	{
		JournalDto dto=new JournalDto();
		List<JournalModel> list=journalDao.findJournalList(ownerid, start, size);
		dto.setList(list);
		return dto;
	}

	@Override
	public int findJournalTotal(long ownerid)
	{
		return journalDao.findJournalTotal(ownerid);
	}

	@Override
	public JournalModel findJournalModelById(long id, long ownerid) {
		JournalModel j = journalDao.findJournalModelById(id, ownerid);
		j.setContents(journalDao.findJournalContentByJid(id));
		return j;
	}

	@Override
	public IDto findJournalContentList(long jid,int pageId) {
		JournalDto dto = new JournalDto();
		int start = (pageId - 1) * IInteractConstants.JOURNAL_LIMIT;
		int total = journalDao.findJournalContentTotal(jid);
		if(total > 0){
			List<JournalContent> journalContentList = journalDao.findJournalContentList(jid,start, IInteractConstants.JOURNAL_LIMIT);
			dto.setJournalContentList(journalContentList);
		}
		Pager pager = new Pager(pageId, total, IInteractConstants.JOURNAL_LIMIT);
		dto.setPager(pager);
		return dto;
	}

	@Override
	public long saveJournalContent(long jid,JournalDto dto) {
		return journalDao.saveJournalContent(jid,dto);
	}

	@Override
	public IDto findJournalContentById(long id) {
		JournalDto dto = new JournalDto();
		JournalContent journal = journalDao.findJournalContentById(id);
		dto.setJournal(journal);
		return dto;
	}

	@Override
	public long updateJournalContent(long id, JournalDto dto) {
		return journalDao.updateJournalContent(id,dto);
	}

	@Override
	public long updateJournalShareContent(long id, JournalDto dto) {
		return journalDao.updateJournalShareContent(id,dto);
	}

	@Override
	public int deleteJournal(long id)
	{
		return journalDao.deleteJournal(id);
	}

	@Override
	public JournalModel findOneJournal(long id)
	{
		return journalDao.findOneJournal(id);
	}

	@Override
	public int saveJournal(long ownerid, JournalModel jm)
	{
		String title=jm.getTitle();
		String isshare=jm.getIsshare();
		int balance=jm.getBalance();
		return journalDao.saveJournal(ownerid, title, isshare, balance);
	}

	@Override
	public int updateJournal(long id, JournalModel jm)
	{
		String title=jm.getTitle();
		String isshare=jm.getIsshare();
		int balance=jm.getBalance();
		return journalDao.updateJournal(id, title, isshare, balance);
	}

	@Override
	public int saveJournalRecrod(long pageid, long wbuid, long contentid,
			String content, String pic, String ip, String terminal,
			String source,long cid) {
		if(wbuid == 0){
			return 1;
		}
		String token = this.findTokenByPageidAndWbuid(pageid, wbuid);
		HyWbSrc src = null;
		if(!StringUtils.isEmpty(pic)){
			src = this.justWS.weiboByPic(token, content, pic);
		}else{
			src = this.justWS.weibo(token, content);
		}
		if(src != null){
			journalDao.saveJournalRecord(pageid, wbuid, contentid, src.getId(), content, pic, ip, terminal, source);
			journalDao.updateContentCount(contentid);
		}
		int balance = journalDao.findBalanceByContentId(contentid);
		if(balance != 0){
			//this.updateBalance(cid, wbuid, balance, IInteractConstants.JOURNAL_DESC);暂时不用了
		}
		return 1;
	}
	@Override
	public int deleteJournalContent(long id) {
		journalDao.deleteJournalContent(id);
		return 0;
	}

}
