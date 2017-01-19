package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.esite.model.JournalModel;
import com.huiyee.esite.model.Module;

public interface IHd140Dao {
	
	public long saveFeatureInteractJournal(final long pageid);
	
	public List<JournalModel> findInteractJournalByOwner(long ownerid);
	
	public Module findJidByFid(long fid);
	
	public int updateFeatureIneractJournal(long jid,long fid);
	
	public JournalModel findFeatureInteractJournalById(long fid);
}
