package com.huiyee.esite.mgr;

import java.util.List;

import com.huiyee.esite.dto.SitePage;
import com.huiyee.esite.model.Module;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.Sitegroup;
import com.huiyee.weixin.model.WxPageShow;

public interface ISiteManager {
	
	public List<Site> findSiteList(String type, long groupid);
	
	public Sitegroup findSitegroupByPageid(long pageid);
	
	public List<Module> findModules();
	
	public long findXcIdBySiteid(long siteid);
	
	//public long saveSite(long ownerid,String name,String type,long groupid);
	
	public int deleteModuleSite(long siteid);
	
	public int saveModuleSite(long mmid,long siteid);
	
	public Site findSiteByID(long siteid);
	
	public List<Long> findSiteModule(long siteid);
	
	public int updateSite(String name,long siteid,List<Long> modules);
	
	public int deleteSite(long sitegroupid);
	
	public int findSitegroupCountByOwner(long owner,String type);
	
	public List<Sitegroup> findSitegroupByOwner(long owner,String type,int start,int size);
	
	public int addSitegroup(String name,long ownerid,String type);
	
	public Site findSiteById(long id);
	
    public List<Site> findSiteListAll(String type,long owner) ;
    
    public int saveLoadPageSite(long ownerid, String name, String type,long appid,List<Long> modules,long groupid) ;
    
    public int addSite(String name, long ownerid,String type,long appid);
    
    public List<Sitegroup> findSitegroupListByOwner(long owner,String type,int start,int size);
    
    public List<WxPageShow> sitegroupListLink(long sitegroupid);
    
    public Sitegroup sitegroupListbyId(long pgid);
    
    public int findPageCountBySiteId(long siteid);
    
    public List<Site> findSiteListByGroupId(String type, long groupid);
    
    public int updateSiteName(String name,long sitegroupid);
    
    public List<Site> findSites(long ownerid,String type,int start, int size);
    
    public int findSiteCountByOwner(long owner,String type);
    
    public long findSiteidByPageid(long pageid);
    
    public List<Site> findSiteNames(long id);
    
    public int deleteSiteGroup(long sgid);
    
    public int updateSiteGroup(String name,long sgid);
    
    public int updateSiteGroupTime(long sgid);
    
	public List<Site> findSiteBySiteGroupId(long sitegroupid);
	
	public List<Site> findSiteIdBySiteGroupId(long sitegroupid);
	
	public List<Sitegroup> findSitegroupAll(int size);
	
	public int updateSiteGroupProcessTime(long sgid);
	
	public List<Site> findSiteListByOwner(long owner);
	
	public Sitegroup findSitegroupByOwner(long owner,long sgid);
	
	public List<Site> findSiteByOwner(long owner);
	
	public List<Site> findSiteListByOwnerid(long owner);
	
	public List<Sitegroup> findSitegroupByOwner(long owner);
	
	public List<Site> findSiteListByOnwer(long owner);
	
	public List<SitePage> findSitePageByOwner(long owner);
	
	public List<SitePage> findPageByOwner(long owner,int start,int size);
	
	public List<SitePage> findPageBySiteId(long siteid);
	
	public SitePage findPageById(long pageid);
	
	public int findPageWolCountByOwner(long owner);

	/**
	 * 添加站点
	 * @param string
	 * @param ownerid
	 * @param type W-微现场  J-集人气  N-普通
	 * @param sitegroupid 
	 * @return 所添加的站点ID
	 */
	public long addSiteWithGroup(String string, long ownerid, String type, long sitegroupid);

	public int updateSiteGroupIsTemplateSetY(long sitegroupid);
	
	public int updateSiteGroupIsTemplateSetN(long sitegroupid);
	
	public List<Page> findPageListBySiteId(long siteid);

	public void updateSiteGroupEntity(long sitegroupid, long entity);

	public List<Sitegroup> findAllTempSiteGroup(String type);
	
	public int updateSiteIsWhole(long siteid);
	
	public int updateNamebypageid(long pageid,String name);

	public int findSitegroupCountByGrp(String copyType, long groupid);
	
	public int findCbCount(long ownerid,String type);
	
	public int findPubCount(long ownerid,String type);

	public Sitegroup findSitegroupByid(long sitegroupid);

	public List<Sitegroup> findXcTemplate();

	public long findSiteGroupByXcid(long xcid);

	/**
	 * 根据siteid查找site信息 包含sitegroupname, sitegroupid, sitegroup_stype
	 * @param siteid
	 * @return
	 */
	public Site findSiteWithGrpById(long siteid);

	public long findxcidBysitegroup(long sitegroupid);

	public int findGroupTotalByType(long id, String grouptype);

	public List<Sitegroup> findGroupByType(long id, String grouptype, int start, int siteLimit);

	public List<Sitegroup> findGroupByType(long owner, String grouptype);

}
