package com.huiyee.esite.dao;

import java.util.List;

import com.huiyee.esite.model.OwnerSource;
import com.huiyee.esite.model.Page4Source;
import com.huiyee.esite.model.PageSource;
import com.huiyee.esite.model.SiteSource;
import com.huiyee.esite.model.SiteSourceVm;

public interface ISiteSourceDao {

	public int saveSiteSource(long siteid, long owner, SiteSource sitesource);
	
	public List<SiteSource> findSiteSourceByOwnerid(long ownerid,long siteid,long pageid);
	
	public List<SiteSource> findSiteSourceByPageid(long pageid);
	
	public SiteSource findSiteSourceById(long sourceid);
	
	public int updatePageSource(long pageid, SiteSource ss);
	
	public int deletePageSource(long pageid);
	
	public List<String> findPageSourceByPageidInCardAndPage(long pageid);
	
	public List<String> findPageSourceByPageidInCardOrPage(long pageid,String type);
	
	public List<PageSource> findPageSourceListByPageid(long pageid);
	
	public PageSource findPageSourceById(long psid);
	
	public int savePageSourceEdit(long psid, String vjson, String html, String top, String left, String right, String bottom);
	
	public int saveCopyPageSource(long pageid,long newpageid);

	public List<SiteSource> findSiteSource(long siteid);

	public int updateSiteSource(long siteid, long owner, SiteSource sitesource);
	
	public int findSourcePage(long sourceid);
	
	public int delSiteSource(long sourceid);
	
	public List<OwnerSource> findOwnerSource(long ownerid);
	
	public List<SiteSourceVm> findSiteSourceVm(String type);
	
	public SiteSourceVm findSiteSourceVmById(long id);
	
	public int saveOwnerSiteSource(long ownerid,long vmid,String title,String html,int level,String json);
	
	public OwnerSource findOwnerSourceById(long id);
	
	public int updateOwnerSiteSource(long id,String json,String html);
	
	public Page4Source findPage4SourceByPageid(long pageid,String type);
	
	public int savePage4Source(long pageid, long osid,String type);
	
	public List<String> findPageSourceByPageidInEdit(long pageid);
	
	public List<String> findPageSourceByPageidInShow(long pageid,int level);

	public int findCountByOsid(long osid);

	public int delOwnerSource(long owner, long osid);
	
	public int deletePage4Source(long pageid,String type);

	public int updateOwnerSourceStyle(long id, long owner, String style,String html);
	
}
