package com.huiyee.esite.fmgr.imp;

import java.util.List;

import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.huiyee.esite.dao.ISiteDao;
import com.huiyee.esite.dto.Feature124Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.showdto.Show124Dto;
import com.huiyee.esite.fdao.IHd124Dao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.HdCard;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.esite.model.PageRelation;
import com.huiyee.interact.research.model.ResearchModel;
import com.huiyee.interact.research.model.ResearchQuestionModel;

public class Feature124ManagerImpl extends AbstractFeatureManager{

	private IHd124Dao hd124Dao;
	private ISiteDao siteDao;
	
	public void setSiteDao(ISiteDao siteDao)
	{
		this.siteDao = siteDao;
	}

	public void setHd124Dao(IHd124Dao hd124Dao) {
		this.hd124Dao = hd124Dao;
	}
	
	@Override
	public long add(long pageid, long featureid,String featurename){
		long fid = hd124Dao.saveFeatureInteractResearch(pageid); 
		return getPageFeatureDao().addPageFeature(pageid, featureid, fid,featurename,"N");
	}
	
	@Override
	public IDto config(long fid, Account account) {
		Feature124Dto dto = new Feature124Dto();
		List<ResearchModel> researchList = hd124Dao.findInteractResearchByOwner(account.getOwner().getId());
		dto.setResearchid(hd124Dao.findResearchidByFid(fid).getId());
		dto.setResearchList(researchList);
		return dto;
	}
	
	@Override
	public String configSub(long featureid, IDto dto, Account account) {
		String result = "N";
		Feature124Dto d = (Feature124Dto) dto;
		List<ResearchModel> research = hd124Dao.findInteractResearchByOwner(account.getOwner().getId());
		d.setResearchList(research);
		int res =  hd124Dao.updateFeatureIneractResearch(d.getResearchid(),d.getFid());
		if(res == 1){
			result = "Y";
	     }
	    return result;
	}
	
	@Override
	public IDto config(long fid) {
		Show124Dto d = new Show124Dto();
		ResearchModel rm = hd124Dao.findFeatureInteractResearchById(fid);
		List<ResearchQuestionModel> list = hd124Dao.findQuestionsByResearchid(rm.getId());
		for(ResearchQuestionModel q : list){
			q.setOptions(hd124Dao.findOptionsByQuestionid(q.getId()));
		}
		rm.setQuestions(list);
		d.setResearchModel(rm);
		return d;
	}
	
	 @Override
	public IDto configNew(long fid,Account account,long relationid) {
		 long pageid = hd124Dao.findResearchidByFid(fid).getPageid();
		 long siteid = siteDao.findSiteidByPageid(pageid);
		 List<Page> pageList = siteDao.findPageListBySiteId(siteid);
		 PageBlockRelation pbr = hd124Dao.findPageBlockRelationByRelationid(relationid);
		 String str = pbr.getJson();
		 JSONObject jo = JSONObject.fromObject(str);
		 String hd = jo.get("obj").toString();
		 String type = "N";
		 String url = null;
		 String urlShow = null;
		 String urlPre = null;
		 long toPageid = 0; 
		 try {
			 jo = JSONObject.fromObject(hd);
			 hd = jo.get("redirect").toString();
			 jo = JSONObject.fromObject(hd);
			 type = jo.getString("type");
			 url =jo.getString("url");
			 urlShow = jo.getString("urlShow");
			 urlPre = jo.getString("urlPre");
			 toPageid = jo.getLong("toPageid");
		 }catch (Exception e) {
			//e.printStackTrace();
		}
		 Feature124Dto dto = (Feature124Dto) config(fid, account);
		 dto.setType(type);
		 dto.setUrl(url);
		 dto.setUrlPre(urlPre);
		 dto.setUrlShow(urlShow);
		 dto.setPageList(pageList);
		 dto.setToPageid(toPageid);
		 return dto;
	}
	 
	 @Override
	public String configSubNew(long featureid, IDto dto, Account account) {
		Feature124Dto d = (Feature124Dto) dto;
		configSub(featureid, dto, account);
		HdCard hc = new HdCard();
		hc.setFid(d.getFid());
		hc.setId(d.getResearchid());
		hc.setFeatureid(featureid);
		hc.setRedirect(d.getRedirect());
		Gson gson = new Gson();
		String str1 = gson.toJson(hc);
		PageBlockRelation pbr = hd124Dao.findPageBlockRelationByRelationid(d.getRelationid());
		String str = pbr.getJson();
		JSONObject jo = JSONObject.fromObject(str);
		jo.element("obj", str1);
		hd124Dao.updatePageBlockRelationByRelationid(d.getRelationid(),jo.toString());
		return "Y";
	}
}
