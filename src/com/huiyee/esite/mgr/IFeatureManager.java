package com.huiyee.esite.mgr;

import java.util.List;
import java.util.Map;

import com.huiyee.esite.dto.HdType;
import com.huiyee.esite.dto.QueryDto;
import com.huiyee.esite.model.Feature;
import com.huiyee.esite.model.Owner;
import com.huiyee.esite.model.PageFeature;
import com.huiyee.esite.model.SinaChecklistRecord;
import com.huiyee.esite.model.SinaUserApp;
import com.huiyee.esite.model.UploadDetail;
import com.huiyee.esite.model.ZanDetail;
import com.huiyee.interact.renqi.model.RenQi;

public interface IFeatureManager {
	
	public List<Feature> findFeatureByPageId(long pageid);
	
	public List<Feature> findNeedUserFeatureByPageId(long pageid);
	
	public List<Feature> findFeatureBySitegroupid(long sitegroupid);
	
	public List<ZanDetail> findZanDetailBySitegroupid(long sitegroupid);
	
	public List<UploadDetail> findUploadDetailBySitegroupid(long sitegroupid);
	
	public List<Feature> findFeatureBySiteId(long siteid);
	
	public List<ZanDetail> findZanDetailBySiteId(long siteid);
	
	public List<UploadDetail> findUploadDetailBySiteId(long siteid);
	
	public Map<Long,Integer> findZanTotal(String[] pfid);
	
	public List<Feature> findFeaturesBySiteId(long siteid);

	public List<SinaUserApp> findSinaUsersDetail(long sitegroupid, QueryDto siftDto);

	public int findSinaUsersDetailTotal(long sitegroupid, QueryDto siftDto);

	public List<SinaChecklistRecord> findSinaShare(long sitegroupid, QueryDto siftDto);

	public int findSinaShareTotal(long sitegroupid, QueryDto siftDto);
	
	public Feature findFeaturesId(long id);
	
	public HdType findHdType(long id);
	
	public List<PageFeature> findPageFeature(long ownerid,int start,int size);
	
	public int findPageFeatureFid(long ownerid);

	public long savePageFeature(long pageid, long fid, int idx, int featureid);

	public long saveSimpleRq(RenQi rq, long ownerid);

	public long findRqFidByPage(long l);

	public long saveRQFid(long longValue, long resultid);

	public long addSiteCopy(Owner owner, String sitename, String copyType, long groupid);

	/**
	 * 创建普通站点
	 * @param owner
	 * @param sitename
	 * @param copyType
	 * @param groupid
	 * @return
	 */
	public long addNormalSitegroup(Owner owner, String sitename, String copyType, long groupid);

	/**
	 * 创建传播
	 * @param owner
	 * @param sitename
	 * @param copyType
	 * @param groupid
	 * @return
	 */
	public long addCbSite(Owner owner, String sitename, String copyType, long groupid);
	
	public long saveUseMb(Owner owner, String type, long mbid, String name, long groupid, String stype);

	/**
	 * 复制微现场
	 * @param owner
	 * @param sitename
	 * @param copyType
	 * @param groupid
	 * @return
	 */
	public long addXcSite(Owner owner, String sitename, String copyType, long groupid);

	public long addOffCheckSite(Owner owner, String sitename, String copyType, long groupid);

}
