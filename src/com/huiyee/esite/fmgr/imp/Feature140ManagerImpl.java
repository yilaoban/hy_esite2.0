package com.huiyee.esite.fmgr.imp;


import java.util.List;

import com.huiyee.esite.dto.Feature140Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.showdto.Show140Dto;
import com.huiyee.esite.fdao.IHd140Dao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.JournalModel;


public class Feature140ManagerImpl extends AbstractFeatureManager{
	
	private IHd140Dao hd140Dao;

	public void setHd140Dao(IHd140Dao hd140Dao) {
		this.hd140Dao = hd140Dao;
	}

	@Override
	public long add(long pageid, long featureid,String featurename) {
		long fid = hd140Dao.saveFeatureInteractJournal(pageid);
		return getPageFeatureDao().addPageFeature(pageid, featureid, fid,featurename,"N");
	}
	
	@Override
	public IDto config(long fid, Account account) {
		Feature140Dto dto = new Feature140Dto();
		List<JournalModel> journalList = hd140Dao.findInteractJournalByOwner(account.getOwner().getId());
		dto.setJournalList(journalList);
		dto.setJid(hd140Dao.findJidByFid(fid).getId());
		return dto;
	}
	
	@Override
	public String configSub(long featureid, IDto dto, Account account) {
		String result = "N";
		Feature140Dto d = (Feature140Dto) dto;
		List<JournalModel> journalList = hd140Dao.findInteractJournalByOwner(account.getOwner().getId());
		d.setJournalList(journalList);
		int res = hd140Dao.updateFeatureIneractJournal(d.getJid(), d.getFid());
		if(res == 1){
			result = "Y";
	     }
	    return result;
	}
	
	@Override
	public IDto config(long fid) {
		Show140Dto d = new Show140Dto();
		d.setJournal(hd140Dao.findFeatureInteractJournalById(fid));
		return d;
	}
}
