package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.HD150Model;

public interface IHd150Dao {

	public long saveFeatureInteract(final long pageid);
	
	public HD150Model findModelByFid(long fid);
	
	public List<ContentCategory> findCategory(long id);
	
	public String findContentType(long catid);
	
	public int saveFid(long fid, long catid, String type, long topage);
	
	public List<ContentCategory> findCategoryByCateid(long catid);
}
