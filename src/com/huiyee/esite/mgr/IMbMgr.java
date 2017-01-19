package com.huiyee.esite.mgr;

import java.util.List;

import com.huiyee.esite.model.Mb;
import com.huiyee.esite.model.Tag;


public interface IMbMgr
{
	public List<Mb> findMbList(String type, long tagid);
	
	public List<Tag> findTagList(String type);
	
	public int saveMb(Mb mb);
	
	public int findTotalMb(String type);
}
