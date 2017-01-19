package com.huiyee.esite.dao;

import java.util.List;
import java.util.Map;

import com.huiyee.esite.dto.SitePage;
import com.huiyee.esite.model.Module;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.Sitegroup;
import com.huiyee.weixin.model.WxPageShow;

public interface ISiteDao {

	public int findPageTotalBySite(long siteId);
	
	public Sitegroup findSitegroupByPageid(long pageid);
	
	public long findXcIdBySiteid(long siteid);
	
	public String findWbNickName(long siteId);
	
	public List<Module> findModules();
	
	public long saveSite(long ownerid, String name, String type,long groupid);
	
	public int deleteModuleSite(long siteid);
	
	public int saveModuleSite(long mmid,long siteid);
	
	public Site findSiteByID(long siteid);
	
	public List<Long> findSiteModule(long siteid);
	
	public int updateSite(String name,long sitegroupid);
	
	public int deleteSite(long sitegroupid);
	
	public int findSitegroupCountByOwner(long owner,String type);
	
	public List<Sitegroup> findSitegroupByOwner(long owner,String type,int start,int size);
	
	public List<WxPageShow> sitegroupListLink(long sitegroupid);
	
	public Sitegroup sitegroupListbyId(long pgid);
	
	public int findPageCountBySiteId(long siteid);
	
	public List<Sitegroup> findSitegroupListByOwner(long owner);
	
	public List<Site> findSiteListByOwner(long owner);
	
	public List<Site> findSiteByOwner(long owner);
	
	public List<Site> findSiteByGroupId(long groupid,String type);
	
	public void updateSiteAction(long siteid,String type);
	
	public int addSitegroup(String name, long ownerid,String type);
	
	public List<Site> findSiteListAll(String type,long owner);
	
	public List<Site> findSites(long ownerid,String type,int start, int size);
	
	public int findSiteCountByOwner(long owner,String type);
	
	public long findSiteidByPageid(long pageid);
	
	public List<Site> findSiteNames(long id);
	
	public int deleteSiteGroup(long sgid);
	
	public int updateSiteGroup(String name,long sgid);
	
	public List<Site> findSiteBySiteGroupId(long sitegroupid);
	
	public List<Site> findSiteIdBySiteGroupId(long sitegroupid);
	
	public List<Sitegroup> findSitegroupAll(int size);
	
	public int updateSiteGroupProcessTime(long sgid);
	
	public Sitegroup findSitegroupByOwner(long owner,long sgid);
	
	public List<Site> findSiteList(long owner);
	
	public List<Sitegroup> findSitegroupByOwner(long owner);
	
	public List<SitePage> findSitePageByOwner(long owner) ;
	
	public List<SitePage> findPageByOwner(long owner,int start,int size);
	
	public List<SitePage> findPageBySiteId(long siteid);
	
	public SitePage findPageById(long pageid);
	
	public int findPageWolCountByOwner(long owner);

	/**
	 * 创建sitegroup
	 * @param sitegroupname
	 * @param type
	 * @param ownerid 
	 * @return
	 */
	public long saveSiteGroup(String sitegroupname, String type, long ownerid ,String stype);

	/**
	 * 查找需要复制的站点
	 * @param owner 
	 * @return
	 */
	public List<Site> findCopySiteByOwner(long groupid);
	
	public int updateSiteGroupIsTemplateSetY(long sitegroupid);
	
	public List<Page> findPageListBySiteId(long siteid);
	
	public int updateSiteGroupIsTemplateSetN(long sitegroupid);

	public void updateSiteGroupEntity(long sitegroupid, long entity);

	public List<Sitegroup> findAllTempSiteGroup(String type);

	public long findSiteidByXcandType(long xcid, String type);

	public long findSiteidByXcandPageId(long xcid, Long pageId);
	
	public int updateSiteIsWhole(long siteid);
	
	public int updateNamebypageid(long pageid,String name);

	public Sitegroup findSiteGroupById(long sitegroupid);

	public void updateSiteName(String newName, long id);
	
	public int findSameNameCount(long ownerid,String sitename, String type);
	
	public List<Page> findOnlinePageListBySiteId(long siteid);

	public String findOnameBySg(long groupid);

	public int findSitegroupCountByGrp(long groupid, String copyType);

	/**
	 * sitegroup 的loginpage,registpage
	 * @param oldsitegroupid
	 * @return
	 */
	public Map<String, Long> findLogRegById(long oldsitegroupid);

	public void updateSglr(long newsitegroupid, Long nl, Long nr);
	
	public int findCbCount(long ownerid, String type);
	
	public int findPubCount(long ownerid, String type);
	
	public int updateSiteGroupTime(long sgid);

	public long saveSameSite(long ownerid, long sitegroupid, long id);

	/**
	 * 复制sitegroup
	 * @param ownerid
	 * @param sitename
	 * @param groupid
	 * @return
	 */
	public long saveSameSiteGroup(long ownerid, String sitename, long groupid);

	public List<Sitegroup> findXcTemplate();

	public long findSiteGroupbyXcid(long xcid);

	public String findGroupTypeByMbid(long mbid);

	/**
	 * 根据siteid查找site信息 包含sitegroupname, sitegroupid, sitegroup_stype
	 * @param siteid
	 * @return
	 */
	public Site findSiteWithGrpById(long siteid);

	public long findxcidBysitegroup(long sitegroupid);

	/**
	 * 查找sitegroup 信息包含entityid
	 * @param groupid
	 * @return
	 */
	public Sitegroup findSiteGroup(long groupid);
	
	public List<Site> findSiteListByOwnerid(long owner);

	public List<Sitegroup> findGroupByType(long owner, String grouptype, int start, int size);

	public int findGroupTotalByType(long owner, String grouptype);

	public List<Sitegroup> findGroupByType(long owner, String grouptype);
}
