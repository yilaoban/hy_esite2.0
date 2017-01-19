package com.huiyee.esite.dao;

import java.util.List;
import java.util.Map;

import com.huiyee.esite.model.ContentPicture;

public interface IContentPictureDao {

	public long savePicture(ContentPicture picture);

	public List<ContentPicture> findPictureByCcid(long ccid, long ownerid, int start, int size, String name);

	public ContentPicture findPictureById(long contentId, long id);
	
	public ContentPicture findPictureById(long contentId);

	public int updatePicture(ContentPicture picture);

	public int updatePicture(long contentId, long owner, String contentDel);

	public int findPictureTotal(long ccid, long ownerid, String name);
	
	public int findMaxIndx(long catid);
	
	public Map findIndx(long id);
	
	public int deleteIndx(int idx,long catid);
	
	public int updatePicIdx(ContentPicture cp);
	
	public List<ContentPicture> findPictureByCcid(long ccid,long ownerid);

	public void updatePicturePost(long entityid, long topicid);

	public void updatePictureByFatherid(long fatherid, ContentPicture picture);

}
