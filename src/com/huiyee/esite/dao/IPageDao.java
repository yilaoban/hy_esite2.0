package com.huiyee.esite.dao;

import java.util.List;

import net.sf.json.JSONObject;

import com.huiyee.esite.model.Activity;
import com.huiyee.esite.model.MyTempalte;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.PageAddress;
import com.huiyee.esite.model.PageRelation;
import com.huiyee.esite.model.PageTemplate;
import com.huiyee.esite.model.TemplateFeature;
import com.huiyee.esite.model.TemplateModel;
import com.huiyee.esite.model.WeiXinPage;

public interface IPageDao {

	public List<Page> findPageBySiteid(long siteid);	
	
	public Page findHomePageBySiteid(long siteid);
	
	public long savePage(String pagename, String jspname, long siteid,String stype);
	
	public Page findPageById(long pageid);
	
	public String findHtmlByPage(long pageid,String type);
	
	public int updatePage(String pagename, String jspname, long pageid,String istemplate,String ownertempid);
	
	public int deletePage(long pageid);
	
	public int updateHome(long siteid,long pageid);
	
	public long findOwnerByPageid(long pageid);
	
	public List<Activity> findActivityByPageid(long pageid);
	
	public List<TemplateModel> findPageTemplate(long ownerid);
	
	public String findTokenByPageidAndWbuid(long pageid,long wbuid);
	
	public MyTempalte findMyTemplateByPageid(long pageid);
	
	public List<TemplateFeature> findTemplateFeaturesByOTid(long otid);
	
	public int findPageCountBySiteId(long siteid);
	
	public long saveNewPage (Page page);
	
	public List<Page> findPageByRelationid(long relationid);
	
	public long saveNewPage (String pagename, String jspname, long siteid,String stype,String type);
	
	public int updateOnline(long pageid);
	
	public int updateOffline(long pageid);
	
	public int savePageOnlineLog(long pageid,String status);
	
	public int updatePageName(long pageid,String name);
	
	public List<Page> findSitePagesByOnePageOfSite(long pageid);
	
	public List<Page> findSinaPublishPageBySiteid(long siteid);
	
	public List<Page> findQQPublishPageBySiteid(long siteid);
	
	public int savePageBg(long pageid,String json);
	
	public String findPageBg(long pageid);
	
	public int savePageHtml(long pageid,String html,String js,String css);
	
	public List<PageAddress> findAddressList(long pageid);
	
	public int savePageAddress(PageAddress pa);
	
	public int findSourceExit(long pageid,String source);
	
	public int updatehiddenBlk(long relationid);
	
	public long findCbidByRelationid(long relationid);
	
	public int deleteRelation(long relationid);
	
	public int deleteCbid(long cbid);
	
	public int updateshowBlk(long pcid);
	
	public PageAddress findPageAddress(long pageid);
	
	public int updateisshowMenu(long pcid,String isshow);
	
	public String findJspstyleByPcid(long pcid);
	
	public int updatePageAddressStatus(long pageid);
	
	public Page findPageSubweixin(long pageid);
	
	public String findPageName(long pageid);
	
	public List<WeiXinPage> findWeiXinPageList(long pageid);

	public int updateHtml(long pageid, String type,String html);
	
	public int savePageTemplate(PageTemplate pt);

	public List<Long> findPageIdBySiteid(long site);
	
	public List<PageRelation> findPageRelationListBySiteid(long siteid);
	
	public List<Long> findPageidByFapageid(long fapageid);
	
	public List<Page> findOnlinePageByOwnerid(long ownerid, String start, String end);
	
	public Page findPageByPageid(long pageid);
	
	public String findPageParam(String jspname);
	
	public int updatePageParams(long pageid, String json);
	
	public int savePageParam(String jspname,String param);
	
	public JSONObject findJspname(long siteid);
	
	public List<Page> findPagesByName(long ownerid, String name, int start, int rows);
	
	public List<Long> findPagesByMbid(long mbid);
	
	public Page findPageBySiteidAndApptype(long siteid,String apptype);
	
	public long findFatherPageid(long childPageid);
	
	public List<Long> findPagesByCcid(long ccid);
	
	public List<Long> findNewsPagesByCcid(long ccid);
	
	public List<Long> findPicturePagesByCcid(long ccid);
	
	public List<Long> findProductPagesByCcid(long ccid);
	
	public List<Long> findVedioPagesByCcid(long ccid);
}
