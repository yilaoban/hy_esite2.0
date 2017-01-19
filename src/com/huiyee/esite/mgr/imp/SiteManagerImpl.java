package com.huiyee.esite.mgr.imp;

import java.util.List;

import com.huiyee.esite.dao.IPageDao;
import com.huiyee.esite.dao.ISinaTokenDao;
import com.huiyee.esite.dao.ISiteDao;
import com.huiyee.esite.dto.SitePage;
import com.huiyee.esite.mgr.ISiteManager;
import com.huiyee.esite.model.Module;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.Sitegroup;
import com.huiyee.weixin.model.WxPageShow;

public class SiteManagerImpl implements ISiteManager {
	
	private ISiteDao siteDao;
	private ISinaTokenDao sinaTokenDao;
	private IPageDao pageDao;

	public void setSinaTokenDao(ISinaTokenDao sinaTokenDao)
	{
		this.sinaTokenDao = sinaTokenDao;
	}

	public void setSiteDao(ISiteDao siteDao) {
		this.siteDao = siteDao;
	}

	@Override
	public List<Site> findSiteList(String type, long groupid) {
		List<Site> list = siteDao.findSiteByGroupId(groupid, type);
		for(Site s : list){
		    Page page=pageDao.findHomePageBySiteid(s.getId());
		    if(page!=null){
		        s.setHomepageId(page.getId());
		    }
			s.setPages(pageDao.findPageBySiteid(s.getId()));
			s.setPageTotal(siteDao.findPageTotalBySite(s.getId()));
			s.setWbnickname(siteDao.findWbNickName(s.getId()));
			s.setSinaToken(sinaTokenDao.findTokenBySiteid(s.getId()));
		}
		return list; 
	}
	
	@Override
    public List<Site> findSiteListAll(String type,long owner) {
	    List<Site> list = siteDao.findSiteListAll(type,owner);
        for(Site s : list){
            s.setPages(pageDao.findPageBySiteid(s.getId()));
            s.setPageTotal(siteDao.findPageTotalBySite(s.getId()));
            s.setWbnickname(siteDao.findWbNickName(s.getId()));
            s.setSinaToken(sinaTokenDao.findTokenBySiteid(s.getId()));
        }
        return list; 
    }

	@Override
	public List<Module> findModules() {
		return siteDao.findModules();
	}

//	@Override
//	public long saveSite(long ownerid, String name, String type,long groupid) {
//		return siteDao.saveSite(ownerid, name, type,groupid);
//	}
	
	@Override
    public int saveLoadPageSite(long ownerid, String name, String type,long appid,List<Long> modules,long groupid) {
	    long sid=siteDao.saveSite(ownerid, name, type,groupid);
	    sinaTokenDao.addSinaToken(sid, appid,ownerid);
//	    if (modules != null && modules.size() > 0)
//        {
//	        siteDao.deleteModuleSite(sid);
//            for (Long m : modules)
//            {
//                siteDao.saveModuleSite(m, sid);
//            }
//        }
        return 1;
    }

	@Override
	public int saveModuleSite(long mmid, long siteid) {
		return siteDao.saveModuleSite(mmid, siteid);
	}

	@Override
	public int deleteModuleSite(long siteid) {
		return siteDao.deleteModuleSite(siteid);
	}

	@Override
	public Site findSiteByID(long siteid) {
		return siteDao.findSiteByID(siteid);
	}

	@Override
	public List<Long> findSiteModule(long siteid) {
		return siteDao.findSiteModule(siteid);
	}

	@Override
	public int updateSite(String name,long siteid,List<Long> modules) {
	    siteDao.updateSite(name, siteid);
//        if (modules != null && modules.size() > 0)
//        {
//            siteDao.deleteModuleSite(siteid);
//            for (Long m : modules)
//            {
//                siteDao.saveModuleSite(m, siteid);
//            }
//        }
		return 1;
	}

	@Override
	public int deleteSite(long sitegroupid) {
		return siteDao.deleteSite(sitegroupid);
	}

	@Override
	public List<Sitegroup> findSitegroupByOwner(long owner,String type ,int start,int size) {
		List<Sitegroup> list = siteDao.findSitegroupByOwner(owner, type,start, size);
		for(Sitegroup sg : list){
			List<Site> site = siteDao.findSiteByGroupId(sg.getId(), type);
			for(Site s : site){
				s.setPageTotal(siteDao.findPageTotalBySite(s.getId()));
				s.setWbnickname(siteDao.findWbNickName(s.getId()));
				s.setSinaToken(sinaTokenDao.findTokenBySiteid(s.getId()));
			}
			sg.setSite(site);
		}
		return list;
	}

	@Override
	public int findSitegroupCountByOwner(long owner,String type) {
		return siteDao.findSitegroupCountByOwner(owner,type);
	}

	@Override
	public int addSitegroup(String name, long ownerid,String type) {
		return siteDao.addSitegroup(name, ownerid,type);
	}
	@Override
    public int addSite(String name, long ownerid,String type,long appid) {
	    int res=0;
        long sid= siteDao.saveSite(ownerid, name, type, 0);
        sinaTokenDao.addSinaToken(sid, appid,ownerid);
        if(sid>0){
            res=1;
        }
        return res;
    }
	
	public void setPageDao(IPageDao pageDao) {
		this.pageDao = pageDao;
	}

	@Override
	public Site findSiteById(long id) {
		return siteDao.findSiteByID(id);
	}

    @Override
    public List<Sitegroup> findSitegroupListByOwner(long owner,String type, int start, int size) {
        return siteDao.findSitegroupByOwner(owner,type, start, size);
    }
    
    @Override
    public List<WxPageShow> sitegroupListLink(long sitegroupid) {
        return siteDao.sitegroupListLink(sitegroupid);
    }
    
    @Override
    public Sitegroup sitegroupListbyId(long pgid) {
        return siteDao.sitegroupListbyId(pgid);
    }
    
    @Override
    public int findPageCountBySiteId(long siteid){
    	return siteDao.findPageCountBySiteId(siteid);
    }

    @Override
    public List<Site> findSiteListByGroupId(String type, long groupid) {
        List<Site> sites= siteDao.findSiteByGroupId(groupid, type);
        for(Site s : sites){
            s.setSinaToken(sinaTokenDao.findTokenBySiteid(s.getId()));
        }
        return sites;
    }

    @Override
    public int updateSiteName(String name, long sitegroupid) {
    	Sitegroup sg=siteDao.findSiteGroupById(sitegroupid);
    	String oldGroupName=sg.getGroupname();
    	List<Site> list=findSiteBySiteGroupId(sitegroupid);
    	for (Site site : list) {
			String oldname=site.getName();
			String newName=oldname.replace(oldGroupName, name);
			siteDao.updateSiteName(newName,site.getId());
		}
        return siteDao.updateSite(name, sitegroupid);
    }

    @Override
    public List<Site> findSites(long ownerid, String type, int start, int size) {
        List<Site> sites=siteDao.findSites(ownerid, type, start, size);
        for(Site s : sites){
            s.setSinaToken(sinaTokenDao.findTokenBySiteid(s.getId()));
        }
        return sites;
    }

    @Override
    public int findSiteCountByOwner(long owner, String type) {
        return siteDao.findSiteCountByOwner(owner, type);
    }

	@Override
	public long findSiteidByPageid(long pageid) {
		return siteDao.findSiteidByPageid(pageid);
	}

	@Override
	public List<Site> findSiteNames(long id) {
		List<Site> site = siteDao.findSiteNames(id);
		for(Site s : site){
			s.setWbnickname(siteDao.findWbNickName(s.getId()));
			s.setSinaToken(sinaTokenDao.findTokenBySiteid(s.getId()));
		}
		return site;
	}

    @Override
    public int deleteSiteGroup(long sgid) {
        return siteDao.deleteSiteGroup(sgid);
    }

    @Override
    public int updateSiteGroup(String name, long sgid) {
        return siteDao.updateSiteGroup(name, sgid);
    }
    
    @Override
	public List<Site> findSiteBySiteGroupId(long sitegroupid) {
		List<Site> list=siteDao.findSiteBySiteGroupId(sitegroupid);
		return list;
	}

	@Override
	public List<Site> findSiteIdBySiteGroupId(long sitegroupid) {
		return siteDao.findSiteIdBySiteGroupId(sitegroupid);
	}

    @Override
    public List<Sitegroup> findSitegroupAll(int size) {
        return siteDao.findSitegroupAll(size);
    }

    @Override
    public int updateSiteGroupProcessTime(long sgid) {
        return siteDao.updateSiteGroupProcessTime(sgid);
    }
    
    @Override
    public List<Site> findSiteListByOwner(long owner) {
        return siteDao.findSiteListByOwner(owner);
    }

    @Override
    public Sitegroup findSitegroupByOwner(long owner, long sgid) {
        return siteDao.findSitegroupByOwner(owner, sgid);
    }

	@Override
	public List<Site> findSiteByOwner(long owner) {
		 return siteDao.findSiteByOwner(owner);
	}

	@Override
	public List<Sitegroup> findSitegroupByOwner(long owner)
	{
		return siteDao.findSitegroupByOwner(owner);
	}

	@Override
	public List<SitePage> findSitePageByOwner(long owner)
	{
		return siteDao.findSitePageByOwner(owner);
	}

	@Override
	public List<SitePage> findPageByOwner(long owner,int start,int size)
	{
		return siteDao.findPageByOwner(owner,start,size);
	}

	@Override
	public List<SitePage> findPageBySiteId(long siteid)
	{
		return siteDao.findPageBySiteId(siteid);
	}

	@Override
	public SitePage findPageById(long pageid)
	{
		return siteDao.findPageById(pageid);
	}

	@Override
	public int findPageWolCountByOwner(long owner)
	{
		return siteDao.findPageWolCountByOwner(owner);
	}

	@Override
	public long findXcIdBySiteid(long siteid)
	{
		return siteDao.findXcIdBySiteid(siteid);
	}
	
	@Override
	public long addSiteWithGroup(String name, long ownerid, String siteTypePhone,long sitegroupid)
	{
		return siteDao.saveSite(ownerid, name, siteTypePhone, sitegroupid);
	}
	
	@Override
	public int updateSiteGroupIsTemplateSetY(long sitegroupid)
	{
		return siteDao.updateSiteGroupIsTemplateSetY(sitegroupid);
	}

	@Override
	public List<Page> findPageListBySiteId(long siteid)
	{
		return siteDao.findPageListBySiteId(siteid);
	}

	@Override
	public int updateSiteGroupIsTemplateSetN(long sitegroupid)
	{
		return siteDao.updateSiteGroupIsTemplateSetN(sitegroupid);
	}
	
	@Override
	public void updateSiteGroupEntity(long sitegroupid, long entity)
	{
		siteDao.updateSiteGroupEntity(sitegroupid,entity);
	}
	
	@Override
	public List<Sitegroup> findAllTempSiteGroup(String type)
	{
		return siteDao.findAllTempSiteGroup(type);
	}

	@Override
	public int updateSiteIsWhole(long siteid) {
		return siteDao.updateSiteIsWhole(siteid);
	}
	
	@Override
	public int updateNamebypageid(long pageid,String name) {
		return siteDao.updateNamebypageid(pageid,name);
	}

	@Override
	public int findSitegroupCountByGrp(String copyType, long groupid)
	{
		return siteDao.findSitegroupCountByGrp(groupid,copyType);
	}

	@Override
	public List<Site> findSiteListByOnwer(long owner)
	{
		return siteDao.findSiteList(owner);
	}

	@Override
	public Sitegroup findSitegroupByPageid(long pageid)
	{
		return siteDao.findSitegroupByPageid(pageid);
	}

	@Override
	public int findCbCount(long ownerid, String type)
	{
		return siteDao.findCbCount(ownerid, type);
	}

	@Override
	public int findPubCount(long ownerid, String type)
	{
		return siteDao.findPubCount(ownerid, type);
	}

	@Override
	public int updateSiteGroupTime(long sgid)
	{
		return siteDao.updateSiteGroupTime(sgid);
	}
	@Override
	public Sitegroup findSitegroupByid(long sitegroupid)
	{
		return siteDao.findSiteGroupById(sitegroupid);
	}
	
	@Override
	public List<Sitegroup> findXcTemplate()
	{
		return siteDao.findXcTemplate();
	}
	
	@Override
	public long findSiteGroupByXcid(long xcid)
	{
		return siteDao.findSiteGroupbyXcid(xcid);
	}
	
	@Override
	public Site findSiteWithGrpById(long siteid)
	{
		return siteDao.findSiteWithGrpById(siteid);
	}
	
	@Override
	public long findxcidBysitegroup(long sitegroupid)
	{
		return siteDao.findxcidBysitegroup(sitegroupid);
	}

	@Override
	public List<Site> findSiteListByOwnerid(long owner)
	{
		return siteDao.findSiteListByOwnerid(owner);
	}
	
	@Override
	public List<Sitegroup> findGroupByType(long owner, String grouptype, int start, int size)
	{
		return siteDao.findGroupByType(owner, grouptype, start, size);
	}
	
	@Override
	public int findGroupTotalByType(long owner, String grouptype)
	{
		return siteDao.findGroupTotalByType(owner,grouptype);
	}
	
	@Override
	public List<Sitegroup> findGroupByType(long owner, String grouptype)
	{
		return siteDao.findGroupByType(owner, grouptype);
	}
}
