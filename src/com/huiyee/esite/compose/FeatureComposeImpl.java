package com.huiyee.esite.compose;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.dto.DynamicActionDto;
import com.huiyee.esite.dto.ExportDto;
import com.huiyee.esite.dto.FeatureMgrDto;
import com.huiyee.esite.dto.HdDetailDto;
import com.huiyee.esite.dto.HdReportDto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.fmgr.IFeatureManager;
import com.huiyee.esite.fmgr.IHdFeatureManager;
import com.huiyee.esite.mgr.IActivityManager;
import com.huiyee.esite.mgr.IContentManager;
import com.huiyee.esite.mgr.IHdRecordManager;
import com.huiyee.esite.mgr.IPageCacheManager;
import com.huiyee.esite.mgr.IPageManager;
import com.huiyee.esite.mgr.ISiteManager;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.Activity;
import com.huiyee.esite.model.ContentNew;
import com.huiyee.esite.model.Feature;
import com.huiyee.esite.model.HdModel;
import com.huiyee.esite.model.Module;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.TemplateFeature;
import com.huiyee.esite.model.VisitUser;

public class FeatureComposeImpl implements IFeatureCompose {
	private IPageManager pageManager;
	private ISiteManager siteManager;
	private IPageCacheManager pageCacheManager; 
	private IHdRecordManager hdRecordManager;
	private IActivityManager activityManager;
	private Map<Long, IFeatureManager> managers;
	private Map<Long,IHdFeatureManager> hdManagers;
	private IContentManager contentManager;
	
	public void setManagers(Map<Long, IFeatureManager> managers) {
		this.managers = managers;
	}

	@Override
	public IDto config(long featureid, long fid,Account account) {
		return managers.get(featureid).config(fid, account);
	}
	
	@Override
	public IDto configNew(long featureid, long fid,Account account,long relationid) {
		return managers.get(featureid).configNew(fid, account,relationid);
	}

	@Override
	public String configSub(long featureid, IDto dto,Account account) {
		return managers.get(featureid).configSub(featureid, dto, account);
	}
	
	@Override
	public String configSubNew(long featureid, IDto dto,Account account) {
		return managers.get(featureid).configSubNew(featureid, dto, account);
	}
	
	@Override
	public int pub(long pageid) {
		List<Feature> list = pageManager.findFeaturesByPageid(pageid);//order by idx (1~~)
		Map<String, Object> map = new HashMap<String, Object>();
		for(int idx = 1;idx <= list.size();idx++){
			Feature feature = list.get(idx - 1);
			map.put(idx+"", managers.get(feature.getId()).config(feature.getFid()));
		}
		return pageCacheManager.saveMap(pageid, map);
	}
	@Override
    public int pubBySiteId(long siteid) {
	    List<Page> pages=pageManager.findPageBySiteid(siteid);
	    int res=0;
	    Map<Long,Map<String, Object> > m = new HashMap<Long, Map<String,Object>>();
	    for (Page page : pages) {
	        long pageid=page.getId();
	        List<Feature> list = pageManager.findFeaturesByPageid(pageid);//order by idx (1~~)
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("page", pageManager.findPageById(pageid));
	        for(int idx = 1;idx <= list.size();idx++){
	            Feature feature = list.get(idx - 1);
	            map.put(idx+"", managers.get(feature.getId()).config(feature.getFid()));
	        }
	        m.put(pageid, map);
        }
        res=pageCacheManager.savePagesMap(m);
        return res;
    }


	public void setPageManager(IPageManager pageManager) {
		this.pageManager = pageManager;
	}

	@Override
	public IDto add(long pageid,long ownerid) {
		FeatureMgrDto dto = new FeatureMgrDto();
		dto.setPage(pageManager.findPageById(pageid));
		List<Module> modules = new ArrayList<Module>();
		for(Module m : siteManager.findModules()){
			List<Feature> f = null;
			if("N".equals(m.getIsown())){
				f = pageManager.findFeaturesByModuleid(m.getId());
			}else{
				f = pageManager.findFeaturesByModuleidAndOwnerid(m.getId(),ownerid);
			}
			if(f.size()>0){
				m.setFeatures(f);
				modules.add(m);
			}
		}
		dto.setModules(modules);
		return dto;
	}

	@Override
	public int addSub(long pageid,FeatureMgrDto dto) {
		ArrayList<Long> features = dto.getFeatureids();
		if(features != null && features.size()>0){
			for(Long f : features){
				managers.get(f).add(pageid,f,null);
			}
		}
		return 0;
	}

	@Override
	public int delete(long pfid) {
		return pageManager.deletePageFeature(pfid);
	}

	@Override
	public int up(long pfid) {
		return pageManager.updateUpPageFeature(pfid);
	}

	@Override
	public int down(long pfid) {
		return pageManager.updateDownPageFeature(pfid);
	}

	@Override
	public String dynamicAction(long featureid, long uid, DynamicActionDto dto) {
		return managers.get(featureid).dynamicAction(uid, dto);
	}

	@Override
	public long findOwneridByPageidAndFid(long pageid,long fid) {
		return pageManager.findOwneridByPageidAndFid(pageid, fid);
	}

	public void setPageCacheManager(IPageCacheManager pageCacheManager) {
		this.pageCacheManager = pageCacheManager;
	}

	@Override
	public Map<String, Object> show(long pageid,String type,String source) {
		List<Feature> list = pageManager.findFeaturesByPageid(pageid);//order by idx (1~~)
		Map<String, Object> map = new HashMap<String, Object>();
		for(int idx = 1;idx <= list.size();idx++){
			Feature feature = list.get(idx - 1);
			if("H".equals(type)){
				map.put(feature.getId()+"", managers.get(feature.getId()).config(feature.getFid()));
			}else{
				map.put(idx+"", managers.get(feature.getId()).config(feature.getFid()));
			}
		}

		if(source != null && source.contains("-")){
			String[] s = source.split("-");
			if(s.length > 2){
				if("n".equals(s[0])){
					ContentNew cn = contentManager.findNewsById(Long.parseLong(s[1]));
					map.put("news", cn);
				}
			}
		}
		Page page = pageManager.findPageById(pageid);
		map.put("page", page);
		return map;
	}
	
	@Override
	public List<String> export(long featureid, long sitegroupid, long ownerid,ExportDto exportDto) {
		return managers.get(featureid).export(featureid,sitegroupid,ownerid,exportDto);
	}
	
	@Override
	public IDto composeHdReport(long siteid, long ownerid) {
		HdReportDto dto = new HdReportDto();
		Site sg = siteManager.findSiteById(siteid);
		dto.setSite(sg);
		if(sg==null){
		    return dto;
		}
		if(ownerid != sg.getOwnerId()){
			return dto;
		}
//		List<Long> types = hdRecordManager.findHdTypeBySitegroupid(sitegroupid);
//		List<HdModel> list = new ArrayList<HdModel>();
//		if(types != null){
//			for(Long hdid : types){
//				list.addAll(hdManagers.get(hdid).findHdModelList(sitegroupid));
//			}
//		}
		List<HdModel> list=hdRecordManager.findHdModelListBySgid(siteid);
		DecimalFormat df2 = new DecimalFormat("##.##%");
		int total=hdRecordManager.findHdReportNumTotal(siteid);
		List<List<Object>> arrlist = new ArrayList<List<Object>>();
		for (HdModel hdModel : list)
		{
			int num = hdModel.getTotal();
			double modelnum = 0.0;
			List<Object> listobj = new ArrayList<Object>();
			listobj.add(hdModel.getType());
			listobj.add(num);
			if (total != 0)
			{
				modelnum = (num * 1.00) / (total * 1.00);
				hdModel.setTotalstr((modelnum != 0 ? df2.format(modelnum) : "0%"));
			}
			else
			{
				hdModel.setTotalstr("=0%");
			}
			arrlist.add(listobj);
		}
		dto.setModels(list);
		Gson gson = new Gson();
		dto.setData(gson.toJson(arrlist));
		return dto;
	}

	public void setSiteManager(ISiteManager siteManager) {
		this.siteManager = siteManager;
	}

	public void setHdRecordManager(IHdRecordManager hdRecordManager) {
		this.hdRecordManager = hdRecordManager;
	}

	public void setActivityManager(IActivityManager activityManager) {
		this.activityManager = activityManager;
	}

	public void setHdManagers(Map<Long, IHdFeatureManager> hdManagers) {
		this.hdManagers = hdManagers;
	}

	@Override
	public IDto composeHdReportDetail(long pid,long hdid, long hdfid,int pageId) {
		HdDetailDto  dto = new HdDetailDto();
		hdManagers.get(hdid).findHdDetail(hdfid, pageId,dto);
		//dto.setSite(siteManager.findSiteById(sitegroupid));
		return dto;
	}

	@Override
	public IDto composeQueryHdReportDetail(long sitegroupid, long hdid,
			long hdfid, HdDetailDto dto, int pageId) {
		hdManagers.get(hdid).queryHdDetailList(hdfid, pageId, dto, dto.getQueryObject(hdid));
		//dto.setSite(siteManager.findSiteById(sitegroupid));
		return dto;
	}

	@Override
	public long composeCreatePage(String pagename, String jspname, long siteid,String stype) {
		Site site = siteManager.findSiteById(siteid);
		int pageCount = pageManager.findPageCountBySiteId(siteid);
		pagename="ÐÂ½¨Ò³Ãæ"+(pageCount+1);
		String defaultname="template_blank.jsp";
		long pageid = pageManager.savePage(pagename, defaultname, siteid,site.getType());
		return pageid;
	}

	public void setContentManager(IContentManager contentManager) {
		this.contentManager = contentManager;
	}
}
