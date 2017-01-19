package com.huiyee.esite.dao;

import java.util.List;
import java.util.Map;

import com.huiyee.esite.model.ContentNew;
import com.huiyee.esite.model.ContentPicture;

public interface IContentNewDao {

	public long saveNew(ContentNew cn);

	public List<ContentNew> findNewByCcid(long ccid, long owner, int size, int size2, String name);
	
	public List<ContentNew> findNewByCcid(long ccid,long ownerid);

	public ContentNew findNewById(long contentId, long owner);
	
	public ContentNew findNewsById(long contentId);
	
	public ContentNew findBeforeNews(ContentNew news);
	
	public ContentNew findNextNews(ContentNew news);
	
	public ContentPicture findBeforePicture(ContentPicture pic);
	
	public ContentPicture findNextPicture(ContentPicture pic);

	public int updateNew(ContentNew cn);

	public int updateNew(long contentId, long owner, String status);

	public int findNewsTotal(long ccid, long owner, String name);

	public List<ContentNew> findNewsByOwner(long owner);
	
	public int findMaxIndx(long catid);
	
	public Map findIndx(long id);
	
	public int deleteIndx(int idx,long catid);
	
	public int updateNewsIdx(ContentNew news);

	public void updateNewsPost(long entityid, long topicid);

	public int updateContentToTop(long contentid, long id, int topType);

	/**
	 * 根据status获取目录中新闻
	 * @param ccid
	 * @param ownerid
	 * @param start
	 * @param size
	 * @param status
	 * @return
	 */
	public List<ContentNew> findNewsByCcid(long ccid, long ownerid, int start, int size, String status);

	public void updateNewsByFatherid(long fatherid, ContentNew cn);

	public long savecdNews(ContentNew news);
	
}
