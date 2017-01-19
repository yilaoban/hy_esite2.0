package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.HD149Model;
import com.huiyee.interact.bbs.model.BBSContent;


public interface IHd149Dao
{

	public long saveFid(long pageid);

	public List<ContentCategory> findCategory(long id);

	public String findContentType(long catid);

	public int saveFid(long fid, long catid, String type, long topage);

	public List<BBSContent> findBBSProduct(long catid, int entype);

	public List<BBSContent> findBBSPicture(long catid, int entype);

	public List<BBSContent> findBBSVideo(long catid, int entype);

	public List<BBSContent> findBBSNews(long catid, int entype);
	
	public HD149Model findFid(long fid);

	public List<BBSContent> findBBSForm(long id, int typeCode);
	
	public int updateContentCategoryPageid(long cateid,long pageid);

}
