package com.huiyee.esite.dao;

import java.util.List;
import java.util.Map;

import com.huiyee.esite.model.ContentVideo;

public interface IContentVideoDao {

	public long saveVideo(ContentVideo video);

	public List<ContentVideo> findVideoByCcid(long ccid, long owner, int start, int size, String name);

	public ContentVideo findVideoById(long contentId, long owner);
	
	public ContentVideo findVideoById(long contentId);
	
	public ContentVideo findBeforeVideoById(ContentVideo video);

	public ContentVideo findNextVideoById(ContentVideo video);

	public int updateVideo(ContentVideo video);

	public int updateVideo(long contentId, String status, long owner);

	public int findVideoTotal(long ccid, long owner, String name);
	
	public int findMaxIndx(long catid);
	
	public Map findIndx(long id);
	
	public int deleteIndx(int idx,long catid);
	
	public int updateVideoIdx(ContentVideo video);

	public void updateVideoPost(long entityid, long topicid);

	public void updateVideoByFatherid(long fatherid, ContentVideo video);
	
}
