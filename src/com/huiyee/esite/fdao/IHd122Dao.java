package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.interact.EmailPeriodical.model.EmailPeriodicalModel;

public interface IHd122Dao {
	
	 public long saveFeatureInteract(final long pageid);
	 
	 public List<EmailPeriodicalModel> findEmailPeriodicalByOwner(long ownerid,long fid);
	 
	 public List<Long> findFeaturePublishByFid(long fid);
	 
	 public int deleteFeaturePublish(long fid);
	 
	 public int saveFeaturePublish(long fid,long pid,String name,long idx);
	 
	 public List<EmailPeriodicalModel> findUrlByFid(long fid);
	 
	 public EmailPeriodicalModel findEmailPeriodicalModelByFid(long fid);
	 
	 public EmailPeriodicalModel findEmailPeriodicalByFid(long pid);
	 
}
