package com.huiyee.esite.dao;

import java.util.List;

import com.huiyee.esite.model.Mb;
import com.huiyee.esite.model.Tag;


public interface IMbDao
{
	public List<Mb> findMbList(String type, long tagid);
	
	public List<Tag> findTagList(String type);
	
	public long saveMb(Mb mb);
	
	public long findTag(String tag,String type);
	
	public long saveTag(String tag,String type);
	
	public int saveMbPage(long mbid,long pageid);
	
	public int saveMbTag(long mbid,long tagid);
	
	public int findTotalMb(String type);
	
	public int findMbTag(long mbid,String tag);
	
}
