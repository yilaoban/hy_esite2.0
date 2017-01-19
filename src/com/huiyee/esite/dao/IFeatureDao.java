package com.huiyee.esite.dao;

import java.util.List;
import java.util.Map;

import com.huiyee.esite.dto.HdType;
import com.huiyee.esite.dto.QueryDto;
import com.huiyee.esite.model.Feature;
import com.huiyee.esite.model.PageFeature;
import com.huiyee.esite.model.SinaChecklistRecord;
import com.huiyee.esite.model.SinaUserApp;
import com.huiyee.esite.model.UploadDetail;
import com.huiyee.esite.model.ZanDetail;
import com.huiyee.interact.cb.model.InteractCb;
import com.huiyee.interact.renqi.model.RenQi;

public interface IFeatureDao {

	public List<Feature> findFeatureByPageId(long pageid);
	
	public List<Feature> findNeedUserFeatureByPageId(long pageid);
	
	public List<Feature> findModuleFeaturesByPageid(long pageid);
	
	/**
	 * 产品列表赞
	 * @param sitegroupid
	 * @return
	 */
	public Map<String,Object> findZanBySitegroupid(long sitegroupid);
	
	public List<Feature> findDynameicFeature(long sitegroupid);
	
	public List<Feature> findDynameicFeature();
	
	/**
	 * 产品列表赞
	 * @param sitegroupid
	 * @return
	 */
	public Map<String,Object> findUploadBySitegroupid(long sitegroupid);
	
	public List<ZanDetail> findZanDetailBySitegroupid(long sitegroupid);
	
	public List<UploadDetail> findUploadDetailBySitegroupid(long sitegroupid);
	
	public Map<String, Object> findUploadBySiteId(long siteid);
	
	public Map<String,Object> findZanBySiteId(long siteid) ;
	
	public List<UploadDetail> findUploadDetailBySiteId(long siteid);
	
	public List<ZanDetail> findZanDetailBySiteId(long siteid) ;
	
	public Map<String, Object> findZanTotal(long pfid);

	/**
	 * 新浪授权的跟新时间和互动总数
	 * @param sitegroupid
	 * @return
	 */
	public Map<String, Object> findSinaAccreditBySitegroupid(long sitegroupid);

	/**
	 * 新浪分享得更新时间和互动总数
	 * @param sitegroupid
	 * @return
	 */
	public Map<String, Object> findSinaShareBySitegroupid(long sitegroupid);

	public List<SinaUserApp> findSinaUsersDetail(long sitegroupid, QueryDto siftDto, int start, int size);
	
	public List<Feature> findFeaturesBySiteId(long siteid);

	public int findSinaUsersDetailTotal(long sitegroupid, QueryDto siftDto);

	public List<SinaChecklistRecord> findSinaShare(long sitegroupid, QueryDto siftDto, int start, int size);

	public int findSinaShareTotal(long sitegroupid, QueryDto siftDto);
	
	public List<Feature> findFeaturesByModuleid(long mouleid);
	
	public Feature findFeaturesId(long id);
	
	public HdType findHdType(long id);
	
	public List<Feature> findFeaturesByModuleidAndOwnerid(long mouleid,long ownerid);
	
	public List<PageFeature> findPageFeature(long ownerid,int start,int size);
	
	public int findPageFeatureFid(long ownerid);

	public Feature findPageFeatureByFid(long fid);

	public long savePageFeature(long pageid, long fid, int idx, int featureid);

	public long saveSimpleRq(RenQi rq, long owner);

	public long findRqFidByPage(long l);

	public long saveRQFid(long pageId, long rqid);
	
	public long saveXcFid(long pageId, long xcid);

	public long saveVotefid(long pageid, long entityId);

	public int findMaxIdxByPageId(long pageid);

	public long findEntityId(long id, long fid);

	public long saveLotteryFid(long pageid, long entityId, String type);

	public Feature findPageFeatureById(long pfid);

}
