
package com.huiyee.esite.mgr.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dao.IPageDao;
import com.huiyee.esite.dao.ISiteSourceDao;
import com.huiyee.esite.mgr.ISiteSourceManager;
import com.huiyee.esite.model.OwnerSource;
import com.huiyee.esite.model.Page4Source;
import com.huiyee.esite.model.PageSource;
import com.huiyee.esite.model.SiteSource;
import com.huiyee.esite.model.SiteSourceVm;
import com.huiyee.esite.util.JsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SiteSourceManagerImpl implements ISiteSourceManager
{

	private ISiteSourceDao siteSourceDao;
	private VelocityEngine velocityEngine;
	private IPageDao pageDao;
	
	
	public void setPageDao(IPageDao pageDao)
	{
		this.pageDao = pageDao;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine)
	{
		this.velocityEngine = velocityEngine;
	}

	public void setSiteSourceDao(ISiteSourceDao siteSourceDao)
	{
		this.siteSourceDao = siteSourceDao;
	}

	@Override
	public List<SiteSource> findSiteSourceByOwnerid(long ownerid,long siteid,long pageid)
	{
		return siteSourceDao.findSiteSourceByOwnerid(ownerid,siteid,pageid);
	}

	@Override
	public int saveSiteSource(long siteid, long owner, SiteSource sitesource)
	{
		return siteSourceDao.saveSiteSource(siteid, owner, sitesource);
	}

	@Override
	public List<SiteSource> findSiteSourceByPageid(long pageid)
	{
		return siteSourceDao.findSiteSourceByPageid(pageid);
	}

	@Override
	public void updatePageSource(long pageid, List<Long> sources)
	{
		siteSourceDao.deletePageSource(pageid);
		if(sources != null && sources.size() >0){
			for(Long id : sources){
				SiteSource ss = siteSourceDao.findSiteSourceById(id);
				if(ss != null){
					siteSourceDao.updatePageSource(pageid, ss);
				}
			}
		}
	}

	@Override
	public List<String> findPageSourceByPageidInCardAndPage(long pageid)
	{
		List<PageSource> ps = siteSourceDao.findPageSourceListByPageid(pageid);
		List<String> list = new ArrayList<String>();
		for(PageSource p : ps){
			Map map = new HashMap();
			try{
				JSONObject jo = JSONObject.fromObject(p.getVjson());
				map = JsonUtil.parseJSON2Map(jo.toString());
			} catch (Exception e)
			{
				JSONArray ja = JSONArray.fromObject(p.getVjson());
				map.put("list", ja);
			}
			map.put("top", p.getTop());
			map.put("left",p.getLeft());
			map.put("right", p.getRight());
			map.put("bottom", p.getBottom());
			map.put("position", IPageConstants.POSITION_ABSOLUTE);
			map.put("path", p.getSource().getPath());
			String html = processHtml(map);
			list.add(html);
		}
		return list;
//		return siteSourceDao.findPageSourceByPageidInCardAndPage(pageid);
	}
	
	@Override
	public List<String> findPageSourceByPageidInCardOrPage(long pageid,String type)
	{
		return siteSourceDao.findPageSourceByPageidInCardOrPage(pageid,type);
	}

	@Override
	public List<PageSource> findPageSourceListByPageid(long pageid)
	{
		return siteSourceDao.findPageSourceListByPageid(pageid);
	}

	@Override
	public PageSource findPageSourceById(long psid)
	{
		return siteSourceDao.findPageSourceById(psid);
	}

	@Override
	public int savePageSourceEdit(long psid, String json)
	{
		PageSource ps = siteSourceDao.findPageSourceById(psid);
		JSONObject jo = JSONObject.fromObject(json);
		Map map = JsonUtil.parseJSON2Map(json);
		map.put("top", jo.getString("top"));
		map.put("left", jo.getString("left"));
		map.put("right", jo.getString("right"));
		map.put("bottom", jo.getString("bottom"));
		map.put("position", IPageConstants.POSITION_FIXED);
		map.put("path", ps.getSource().getPath());
		String html = processHtml(map);
		return siteSourceDao.savePageSourceEdit(psid, json, html, jo.get("top").toString(), jo.get("left").toString(),jo.get("right").toString(),jo.get("bottom").toString());
	}
	
	@Override
	public int savePageSourceEditC(long psid, String json,String top,String left,String right,String bottom)
	{
		PageSource ps = siteSourceDao.findPageSourceById(psid);
		JSONObject jo = JSONObject.fromObject(json);
		Map map = new HashMap();
		map.put("list", jo.getJSONArray("list"));
		map.put("top", top);
		map.put("left",left);
		map.put("right", right);
		map.put("bottom", bottom);
		map.put("position", IPageConstants.POSITION_FIXED);
		map.put("path", ps.getSource().getPath());
		String html = processHtml(map);
		return siteSourceDao.savePageSourceEdit(psid, jo.getString("list"), html, top, left,right,bottom);
	}
	
	private String processHtml(Map map){
		return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,"/WEB-INF/velocity/source/source.vm","UTF-8", map);
	}
	
	
	@Override
	public List<SiteSource> findSiteSource(long siteid)
	{
		return siteSourceDao.findSiteSource(siteid);
	}
	
	@Override
	public int updateSiteSource(long siteid, long owner, SiteSource sitesource)
	{
		return siteSourceDao.updateSiteSource(siteid, owner, sitesource);
	}

	@Override
	public SiteSource findSiteSourceById(long sourceid)
	{
		return siteSourceDao.findSiteSourceById(sourceid);
	}

	@Override
	public int findSourcePage(long sourceid)
	{
		return siteSourceDao.findSourcePage(sourceid);
	}

	@Override
	public int delSiteSource(long sourceid)
	{
		return siteSourceDao.delSiteSource(sourceid);
	}
	
	@Override
	public int delOwnerSource(long owner, long osid)
	{
		return siteSourceDao.delOwnerSource(owner,osid);
	}

	@Override
	public List<OwnerSource> findOwnerSource(long ownerid)
	{
		List<OwnerSource> list=siteSourceDao.findOwnerSource(ownerid);
		for (OwnerSource ownerSource : list)
		{
			ownerSource.setPageUsed(siteSourceDao.findCountByOsid(ownerSource.getId()));
		}
		return list;
	}

	@Override
	public List<SiteSourceVm> findSiteSourceVm(String type)
	{
		return siteSourceDao.findSiteSourceVm(type);
	}

	@Override
	public int saveOwnerSiteSource(long ownerid, long vmid, String title)
	{
		SiteSourceVm vm = siteSourceDao.findSiteSourceVmById(vmid);
		if(vm == null)
			return 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("json", vm.getJson());
		String html = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,vm.getPath(),"utf-8", map);
		return siteSourceDao.saveOwnerSiteSource(ownerid, vmid, title, html,vm.getLevel(),vm.getJson());
	}

	@Override
	public OwnerSource findOwnerSourceById(long id)
	{
		return siteSourceDao.findOwnerSourceById(id);
	}

	@Override
	public int saveEditOwnerSiteSource(OwnerSource os)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("json", os.getJsonArray());
		map.put("style", os.getStyleJson());
		String html = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,os.getVm().getPath(),"utf-8", map);
		return siteSourceDao.updateOwnerSiteSource(os.getId(), os.getJson(), html);
	}

	@Override
	public Page4Source findPage4SourceByPageid(long pageid,String type)
	{
		Page4Source ps = siteSourceDao.findPage4SourceByPageid(pageid,type);
		if(ps != null ){
			ps.setPage(pageDao.findPageById(ps.getPageid()));
			ps.setOs(siteSourceDao.findOwnerSourceById(ps.getOsid()));
		}
		return ps;
	}

	@Override
	public int savePage4Source(long pageid, long osid)
	{
		OwnerSource os = siteSourceDao.findOwnerSourceById(osid);//选择的资源
		if(os != null){
			return siteSourceDao.savePage4Source(pageid, osid,os.getVm().getType());
		}
		return 0;
	}

	@Override
	public List<String> findPageSourceByPageidInEdit(long pageid)
	{
		return siteSourceDao.findPageSourceByPageidInEdit(pageid);
	}

	@Override
	public List<String> findPageSourceByPageidInShow(long pageid, int level)
	{
		return siteSourceDao.findPageSourceByPageidInShow(pageid, level);
	}

	@Override
	public int saveCancelPage4Source(long ownerid, long pageid, String type)
	{
		Page4Source ps = findPage4SourceByPageid(pageid,type);
		if(ps != null && ownerid == ps.getOs().getOwnerid()){
			return siteSourceDao.deletePage4Source(pageid, type);
		}
		return 0;
	}
	
	@Override
	public int updateOwnerSourceStyle(long id, long owner, String style)
	{
		OwnerSource os = siteSourceDao.findOwnerSourceById(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("json", os.getJsonArray());
		map.put("style", JSONObject.fromObject(style));
		String html = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,os.getVm().getPath(),"utf-8", map);
		return siteSourceDao.updateOwnerSourceStyle(id,owner,style,html);
	}

}
