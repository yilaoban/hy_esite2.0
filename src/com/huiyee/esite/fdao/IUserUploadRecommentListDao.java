package com.huiyee.esite.fdao;

import com.huiyee.esite.model.FeatureUploadRecommentList;
import com.huiyee.esite.model.UserUpload;
import com.huiyee.esite.model.UserUploadRecord;
import java.util.List;
public interface IUserUploadRecommentListDao {
	
    public long saveFetureUserUploadRecommentList(final FeatureUploadRecommentList recommentList);
    
    public List<UserUpload> findUserUploadList();
    
    public int updateFeatureUserUploadRecomment(long fid,long uploadid) ;
    
    public FeatureUploadRecommentList findUploadRecommentList(long fid);
    
    public UserUpload findUserUploadListByFid(long fid);
    
    public List<Long> findRecordids(long fid);
    
    public List<UserUploadRecord> findUploadRecordCheckRecomment(long fid,long uploadid);
    
    public int deleteUpLoadListRecord(String ids,long fid) ;
    
    public int saveFeatureUserUploadlistRecord(final long listid,final long recordid,final int idx);
    
    public List<UserUploadRecord> findUploadRecordByFid(long fid);
    
    
    
}
