package com.huiyee.esite.fdao;

import com.huiyee.esite.model.FeatureVideoList;
import com.huiyee.esite.model.Video;
import java.util.List;

public interface IVideoDao {
	
    public List<Video> findVideoById(long id);
    
    public long saveFetureVideo(final FeatureVideoList video);
    
    public List<Video> findVideoByOwner(long owner);
    
    public int saveFetureVideoListVideo(final long lvid,final long vid,int idx);
    
    //public int findFetureVideoListVideo(long lvid,long vid);
    
    public int deleteFeatureVideoList(String ids) ;
    
}
