package com.huiyee.esite.mgr;

import java.util.List;

import com.huiyee.esite.model.OwnerSource;
import com.huiyee.esite.model.Page4Source;
import com.huiyee.esite.model.PageSource;
import com.huiyee.esite.model.SiteSource;
import com.huiyee.esite.model.SiteSourceVm;
public interface ISiteSourceManager {

	public int saveSiteSource(long siteid, long owner, SiteSource sitesource);
	
	public List<SiteSource> findSiteSourceByOwnerid(long ownerid,long siteid,long pageid);
	
	public List<SiteSource> findSiteSourceByPageid(long pageid);
	
	public void updatePageSource(long pageid, List<Long> sources);
	
	public List<String> findPageSourceByPageidInCardAndPage(long pageid);
	
	public List<String> findPageSourceByPageidInCardOrPage(long pageid,String type);
	
	public List<PageSource> findPageSourceListByPageid(long pageid);
	
	public PageSource findPageSourceById(long psid);
	
	public int savePageSourceEdit(long psid, String json);
	
	public int savePageSourceEditC(long psid, String json,String top,String left,String right,String bottom);

	public List<SiteSource> findSiteSource(long siteid);

	public int updateSiteSource(long siteid, long owner, SiteSource sitesource);
	
	public SiteSource findSiteSourceById(long sourceid);
	
	public int findSourcePage(long sourceid);
	
	public int delSiteSource(long sourceid);
	
	public List<OwnerSource> findOwnerSource(long ownerid);
	
	public List<SiteSourceVm> findSiteSourceVm(String type);
	
	public int saveOwnerSiteSource(long ownerid, long vmid, String title);
	
	public OwnerSource findOwnerSourceById(long id);
	
	public int saveEditOwnerSiteSource(OwnerSource os);
	
	public Page4Source findPage4SourceByPageid(long pageid,String type);
	
	public int savePage4Source(long pageid, long osid);
	
	public List<String> findPageSourceByPageidInEdit(long pageid);
	
	public List<String> findPageSourceByPageidInShow(long pageid,int level);
	
	public int saveCancelPage4Source(long ownerid, long pageid, String type);

	public int delOwnerSource(long owner, long osid);

	public int updateOwnerSourceStyle(long id, long owner, String json);
}
