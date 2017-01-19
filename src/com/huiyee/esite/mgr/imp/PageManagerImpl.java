package com.huiyee.esite.mgr.imp;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.huiyee.esite.dao.IFeatureDao;
import com.huiyee.esite.dao.IPageDao;
import com.huiyee.esite.dao.IPageFeatureDao;
import com.huiyee.esite.dao.ISiteSourceDao;
import com.huiyee.esite.dao.ITemplateDao;
import com.huiyee.esite.dao.IWeiXinDao;
import com.huiyee.esite.mgr.IPageManager;
import com.huiyee.esite.model.Activity;
import com.huiyee.esite.model.Feature;
import com.huiyee.esite.model.MyTempalte;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.PageAddress;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.esite.model.PageCard;
import com.huiyee.esite.model.PageFeature;
import com.huiyee.esite.model.PageRelation;
import com.huiyee.esite.model.PageTemplate;
import com.huiyee.esite.model.TemplateFeature;
import com.huiyee.esite.model.TemplateModel;

public class PageManagerImpl implements IPageManager {
	private IPageDao pageDao;
	private IFeatureDao featureDao;
	private IPageFeatureDao pageFeatureDao;
	private IWeiXinDao weiXinDao;
	private ITemplateDao templateDao;
	private Map<Long, com.huiyee.esite.fmgr.IFeatureManager> managers;
	private ISiteSourceDao siteSourceDao;
	
	public ISiteSourceDao getSiteSourceDao()
	{
		return siteSourceDao;
	}
	
	public void setSiteSourceDao(ISiteSourceDao siteSourceDao)
	{
		this.siteSourceDao = siteSourceDao;
	}

	public void setManagers(Map<Long, com.huiyee.esite.fmgr.IFeatureManager> managers)
	{
		this.managers = managers;
	}

	public void setTemplateDao(ITemplateDao templateDao)
	{
		this.templateDao = templateDao;
	}

	public void setPageDao(IPageDao pageDao) {
		this.pageDao = pageDao;
	}

	@Override
	public List<Page> findPageBySiteid(long siteid) {
		List<Page> page=pageDao.findPageBySiteid(siteid);
		if(page != null){
			for(Page p : page){
				p.setList(pageDao.findWeiXinPageList(p.getId()));
			}		
		}
		return page;
	}

	@Override
	public long savePage(String pagename, String jspname, long siteid,String stype) {
		return pageDao.savePage(pagename, jspname, siteid,stype);
	}

	@Override
	public Page findPageById(long pageid) {
		return pageDao.findPageById(pageid);
	}

	@Override
	public int updatePage(String pagename, String jspname, long pageid,String istemplate,String ownertempid) {
		return pageDao.updatePage(pagename, jspname, pageid,istemplate,ownertempid);
	}

	@Override
	public int deletePage(long pageid) {
		return pageDao.deletePage(pageid);
	}

	@Override
	public List<Feature> findFeaturesByPageid(long pageid) {
		return featureDao.findFeatureByPageId(pageid);
	}

	public void setFeatureDao(IFeatureDao featureDao) {
		this.featureDao = featureDao;
	}

	@Override
	public List<Feature> findModuleFeaturesByPageid(long pageid) {
		return featureDao.findModuleFeaturesByPageid(pageid);
	}

	@Override
	public int deletePageFeature(long pfid) {
		return pageFeatureDao.deletePageFeature(pfid);
	}

	@Override
	public int updateUpPageFeature(long pfid) {
		return pageFeatureDao.updateUpPageFeature(pfid);
	}

	@Override
	public int updateDownPageFeature(long pfid) {
		return pageFeatureDao.updateDownPageFeature(pfid);
	}

	@Override
	public Page findHomePageBySiteid(long siteid)
	{
		return pageDao.findHomePageBySiteid(siteid);
	}

	public void setPageFeatureDao(IPageFeatureDao pageFeatureDao) {
		this.pageFeatureDao = pageFeatureDao;
	}

	@Override
	public long findOwneridByPageidAndFid(long pageid,long fid) {
		return pageFeatureDao.findOwneridByPageidAndFid(pageid, fid);
	}

	@Override
	public int updateHome(long siteid,long pageid) {
		return pageDao.updateHome(siteid,pageid);
	}

    @Override
    public int updateName(long pfid, String name) {
        return pageFeatureDao.updateName(pfid, name);
    }

	@Override
	public long findOwnerByPageid(long pageid) {
		return pageDao.findOwnerByPageid(pageid);
	}

	@Override
	public List<Activity> findActivityByPageid(long pageid) {
		return pageDao.findActivityByPageid(pageid);
	}

	@Override
	public List<TemplateModel> findPageTemplate(long ownerid) {
		return pageDao.findPageTemplate(ownerid);
	}

	@Override
	public MyTempalte findMyTempldateByPageid(long pageid) {
		return pageDao.findMyTemplateByPageid(pageid);
	}

	@Override
	public List<TemplateFeature> findTemplateFeaturesByOTid(long otid) {
		return pageDao.findTemplateFeaturesByOTid(otid);
	}

	@Override
	public int findPageCountBySiteId(long siteid) {
		return pageDao.findPageCountBySiteId(siteid);
	}

	@Override
	public List<Feature> findFeaturesByModuleid(long mouleid) {
		return featureDao.findFeaturesByModuleid(mouleid);
	}

	@Override
	public long saveNewPage(Page page)
	{
		return pageDao.saveNewPage(page);
	}

	@Override
	public List<Page> findPageByRelationid (long relationid)
	{
		return pageDao.findPageByRelationid(relationid);
	}

	@Override
	public int savePageOnlineLog(long pageid, String status)
	{
		return pageDao.savePageOnlineLog(pageid, status);
	}

	@Override
	public int updateOffline(long pageid)
	{
		return pageDao.updateOffline(pageid);
	}

	@Override
	public int updateOnline(long pageid)
	{
		return pageDao.updateOnline(pageid);
	}

	@Override
	public int updatePageName(long pageid, String name)
	{
		return pageDao.updatePageName(pageid, name);
	}

	@Override
	public List<Page> findSitePagesByOnePageOfSite(long pageid) {
		return pageDao.findSitePagesByOnePageOfSite(pageid);
	}

	@Override
	public String findHtmlByPage(long pageid, String type)
	{
		return pageDao.findHtmlByPage(pageid, type);
	}

	@Override
	public List<Page> findSinaPublishPageBySiteid(long siteid)
	{
		return pageDao.findSinaPublishPageBySiteid(siteid);
	}
	
	@Override
	public List<Page> findQQPublishPageBySiteid(long siteid)
	{
		return pageDao.findQQPublishPageBySiteid(siteid);
	}

	@Override
	public int savePageBg(long pageid, String json) {
		return pageDao.savePageBg(pageid, json);
	}

	@Override
	public String findPageBg(long pageid) {
		return pageDao.findPageBg(pageid);
	}

	@Override
	public int saveUploadPage(long siteid, String pagename, String stype,
			String html,String js,String css) {
		long pageid = pageDao.saveNewPage(pagename, "template_blank0.jsp", siteid, stype, "N");
		return pageDao.savePageHtml(pageid, html,js,css);
	}
	
	@Override
	public long saveUploadPageHtml(long siteid, String pagename,String jspname, String stype,
			String js,String css) {
		return pageDao.saveNewPage(pagename, jspname, siteid, stype, "G");
	}

	@Override
	public List<PageAddress> findAddressList(long pageid)
	{
		return pageDao.findAddressList(pageid);
	}

	@Override
	public int findSourceExit(long pageid, String source)
	{
		return pageDao.findSourceExit(pageid, source);
	}

	@Override
	public int savePageAddress(PageAddress pa)
	{
		return pageDao.savePageAddress(pa);
	}

	@Override
	public int updatehiddenBlk(long relationid)
	{
		int result = 0;
		long total = pageDao.updatehiddenBlk(relationid);
		if (total > 0)
		{
			result = 1;
		}
		return result;
	}
	
	@Override
	public int deleteBlk(long relationid)
	{
		return pageDao.deleteRelation(relationid);
	}
	
	@Override
	public int updateshowBlk(long pcid)
	{
		int result = 0;
		long total = pageDao.updateshowBlk(pcid);
		if (total > 0)
		{
			result = 1;
		}
		return result;
	}

	@Override
	public PageAddress findPageAddress(long pageid)
	{
		return pageDao.findPageAddress(pageid);
	}

	@Override
	public int updateisshowMenu(long pcid,String isshow)
	{
		int result = 0;
		long total = pageDao.updateisshowMenu(pcid,isshow);
		if (total > 0)
		{
			result = 1;
		}
		return result;
	}

	@Override
	public String findJspstyleByPcid(long pcid) {
		return pageDao.findJspstyleByPcid(pcid);
	}

	@Override
	public int updatePageAddressStatus(long pageid)
	{
		return pageDao.updatePageAddressStatus(pageid);
	}

	@Override
	public List<Feature> findFeaturesByModuleidAndOwnerid(long mouleid,
			long ownerid) {
		return featureDao.findFeaturesByModuleidAndOwnerid(mouleid, ownerid);
	}

	@Override
	public Page findPageSubweixin(long pageid)
	{
		return pageDao.findPageSubweixin(pageid);
	}

	@Override
	public String findPageName(long pageid)
	{
		return pageDao.findPageName(pageid);
	}

	public IWeiXinDao getWeiXinDao()
	{
		return weiXinDao;
	}

	public void setWeiXinDao(IWeiXinDao weiXinDao)
	{
		this.weiXinDao = weiXinDao;
	}

	@Override
	public int updateHtml(long pageid, String type,String html)
	{
		return pageDao.updateHtml(pageid,type,html);
	}

	@Override
	public int savePageTemplate(PageTemplate pt)
	{
		return pageDao.savePageTemplate(pt);
	}
	
	@Override
	public List<Long> findPageIdBySiteid(long site)
	{
		return pageDao.findPageIdBySiteid(site);
	}

	@Override
	public List<PageRelation> findPageRelationListBySiteid(long siteid)
	{
		return pageDao.findPageRelationListBySiteid(siteid);
	}

	@Override
	public List<Long> findPageidByFapageid(long fapageid) {
		return pageDao.findPageidByFapageid(fapageid);
	}

	@Override
	public List<Page> findOnlinePageByOwnerid(long ownerid, String start, String end) {
		return pageDao.findOnlinePageByOwnerid(ownerid, start, end);
	}

	@Override
	public Page findPageByPageid(long pageid) {
		return pageDao.findPageByPageid(pageid);
	}

	@Override
	public String findPageParams(String jspname) {
		return pageDao.findPageParam(jspname);
	}

	@Override
	public int updatePageParams(long pageid, String json) {
		return pageDao.updatePageParams(pageid, json);
	}

	@Override
	public int savePageParam(String jspname, String param) {
		return pageDao.savePageParam(jspname, param);
	}

	@Override
	public JSONObject findJspname(long siteid) {
		return pageDao.findJspname(siteid);
	}
	
	@Override
	public Long addcopyPage(long siteid, Page page) {
		page.setSiteid(siteid);
		long pageid_copy = saveNewPage(page);//复制page表，拿到新pageid
		List<PageCard> list = templateDao.findTemplateCardByPageid(page.getId());//找到需要复制的所有卡片
		for (PageCard pagecard : list) {
			long pcid = pagecard.getId();
			pagecard.setPageid(pageid_copy);
			long pcid_copy = templateDao.saveNewPageCard(pagecard);//复制pcid，拿到新的pcid

			List<PageBlockRelation> block = templateDao.findPageBlockRelationBycardid1(pcid);//找到所有relationid
			for (PageBlockRelation pbr : block) {
				long relationid = pbr.getId();
				long pfid = updateCopyPfid(pbr.getPfid(),pageid_copy);
				String json = pbr.getJson();
				long relationid_copy = 0 ;
				if("N".equals(pbr.getAlone()) ){
					//非组件
					long cbid = pbr.getCbid();
					if(pfid > 0 ){
						//互动
						JSONObject jo = JSONObject.fromObject(json);
						if(jo.get("obj")!=null){
							//删除其余的json 如果json中含有页面关联则保留,在最后changelink替换pageid ,oname
							JSONObject oldjo=(JSONObject)jo.get("obj");
//							JSONObject newjo=new JSONObject();
//							if(oldjo.has("redirect")){
//								newjo.element("redirect", oldjo.get("redirect"));
//								jo.element("obj", newjo);
//							}else{
//								jo.element("obj", "{}");
//							}
							oldjo.remove("id");
							oldjo.remove("fid");
							jo.element("obj", oldjo);
						}
						if(jo.get("list") != null){
							jo.element("list", "[]");							
						}
						json = jo.toString();
					}
					relationid_copy = templateDao.savePageBlockRelation(pcid_copy, cbid, json, pfid,pbr.getDisplay());
				}else{
					//组件
//					long cbid_copy = templateDao.addCopyCbid(pbr.getCbid());
					if(pfid > 0 ){
						//互动
						JSONObject jo = JSONObject.fromObject(json);
						jo.element("obj", "");
						jo.element("list", "");
					}
					relationid_copy = templateDao.savePageBlockRelation(pcid_copy, pbr.getCbid(), json, pfid,pbr.getDisplay());
				}
				List<Page> subpages = findPageByRelationid(relationid);
				for (Page subpage : subpages) {
					subpage.setSiteid(siteid);
					subpage.setRelationid(relationid_copy);
					addcopyPage(siteid, subpage);
				}
			}
		}
		siteSourceDao.saveCopyPageSource(page.getId(), pageid_copy);
		return pageid_copy;
	}
	
	private long updateCopyPfid(long pfid,long pageid_copy){
		if(pfid > 0){
			PageFeature pf = pageFeatureDao.findPageFeature(pfid);
			if(pf != null){
				long featureid = pf.getFeatureid();
				pfid = managers.get(pf.getFeatureid()).add(pageid_copy, featureid, null);//调用相应互动的add();
			}
		}
		return pfid;
	}
	
	@Override
	public long addCopyCard(long pcid){
		PageCard pc = templateDao.findPageBlockById(pcid);
		long pcid_copy = templateDao.saveNewPageCard(pc);//复制pcid，拿到新的pcid
		List<PageBlockRelation> block = templateDao.findPageBlockRelationBycardid1(pcid);//找到所有relationid
		for (PageBlockRelation pbr : block) {
			long relationid = pbr.getId();
			long pfid = updateCopyPfid(pbr.getPfid(),pc.getPageid());
			String json = pbr.getJson();
			long relationid_copy = 0 ;
			if("N".equals(pbr.getAlone()) ){
				//非组件
				long cbid = pbr.getCbid();
				if(pfid > 0 ){
					//互动
					JSONObject jo = JSONObject.fromObject(json);
					if(jo.get("obj")!=null){							
						jo.element("obj", "{}");
					}
					if(jo.get("list") != null){
						jo.element("list", "[]");							
					}
					json = jo.toString();
				}
				relationid_copy = templateDao.savePageBlockRelation(pcid_copy, cbid, json, pfid,pbr.getDisplay());
			}else{
				//组件
				if(pfid > 0 ){
					//互动
					JSONObject jo = JSONObject.fromObject(json);
					jo.element("obj", "");
					jo.element("list", "");
				}
				relationid_copy = templateDao.savePageBlockRelation(pcid_copy, pbr.getCbid(), json, pfid,pbr.getDisplay());
			}
			List<Page> subpages = findPageByRelationid(relationid);
			for (Page subpage : subpages) {
				subpage.setRelationid(relationid_copy);
				addcopyPage(subpage.getSiteid(), subpage);
			}
		}
		return pcid_copy;
	}

	@Override
	public List<Page> findPagesByName(long ownerid, String name, int start, int rows) {
		return pageDao.findPagesByName(ownerid, name, start, rows);
	}

	@Override
	public long findFatherPageid(long childPageid)
	{
		return pageDao.findFatherPageid(childPageid);
	}

	@Override
	public List<Long> findPagesByCcid(long ccid)
	{
		return pageDao.findPagesByCcid(ccid);
	}

	@Override
	public List<Long> findNewsPagesByCcid(long ccid)
	{
		return null;
	}

	@Override
	public List<Long> findPicturePagesByCcid(long ccid)
	{
		return null;
	}

	@Override
	public List<Long> findProductPagesByCcid(long ccid)
	{
		return null;
	}

	@Override
	public List<Long> findVedioPagesByCcid(long ccid)
	{
		return null;
	}

}
