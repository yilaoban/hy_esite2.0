package com.huiyee.esite.fdao;
import com.huiyee.esite.model.CategoryTree;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.esite.model.Picture;
import java.util.List;

public interface IPictureDao {
	
  public List<Picture> findPictureById(long id);
    
    public long saveFeturePicture(final long pageid);
    
    public List<Picture> findPictureByOwner(long owner);
    
    public int saveFeturePicListPic(final long lvid,final long vid,final long idx);
    
    //public int findFetureVideoListVideo(long lvid,long vid);
    
    public int deleteFeaturePictureList(String ids) ;
    
    public List<CategoryTree> findTreeByType(String type, long ownerid);
	
	public PageBlockRelation findPageBlockRelationByRelationid(long relationid);
	
	 public int updatePageBlockRelationByRelationid(long relationid,String json);
    
}
