package com.huiyee.esite.fmgr.imp;

import java.util.List;

import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.huiyee.esite.dao.ISiteDao;
import com.huiyee.esite.dto.Feature123Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.showdto.Show123Dto;
import com.huiyee.esite.fdao.IHd123Dao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.HdCard;
import com.huiyee.esite.model.Module;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.esite.model.PageRelation;
import com.huiyee.interact.vote.model.InteractVote;

public class Feature123ManagerImpl extends AbstractFeatureManager{
	private IHd123Dao hd123Dao;
	private ISiteDao siteDao;
	
	public ISiteDao getSiteDao()
	{
		return siteDao;
	}

	
	public void setSiteDao(ISiteDao siteDao)
	{
		this.siteDao = siteDao;
	}

	public void setHd123Dao(IHd123Dao hd123Dao) {
		this.hd123Dao = hd123Dao;
	}

	@Override
	public long add(long pageid, long featureid,String featurename) {
		long fid = hd123Dao.saveFeatureInteractVote(pageid);
		return getPageFeatureDao().addPageFeature(pageid, featureid, fid,featurename,"N");
	}
	
	@Override
	public IDto config(long fid, Account account) {
		Feature123Dto dto = new Feature123Dto();
		List<InteractVote> interactVote = hd123Dao.findInteractVoteByOwner(account.getOwner().getId());
		
		/*if(interactVote != null && interactVote.size()>0){
			for(int i=0;i<interactVote.size();i++){
				int totalOption = hd123Dao.findTotalOptionById(interactVote.get(i).getId());
				interactVote.get(i).setTotalOption(totalOption);
			}
		}*/
		Module m = hd123Dao.findVoteidByFid(fid);
		if(m != null){
			dto.setVoteid(m.getId());
			dto.setEnd(m.getEnd());
			dto.setStart(m.getStart());
		}
		dto.setInteractVote(interactVote);
		return dto;
	}
	
	@Override
	public String configSub(long featureid, IDto dto, Account account) {
		String result = "N";
		Feature123Dto d = (Feature123Dto) dto;
		int res = hd123Dao.updateFeatureIneractVote(d.getVoteid(),d.getFid(),d.getStart(),d.getEnd());
		if(res == 1){
			result = "Y";
	     }
	    return result;
	}
	
	@Override
	public IDto config(long fid) {
		Show123Dto d = new Show123Dto();
		InteractVote iv = hd123Dao.findFeatureInteractVoteById(fid);
		Module m = hd123Dao.findVoteidByFid(fid);
		int start = 0;int end =0;
		if(m != null){
			start = m.getStart();
			end = m.getEnd();
			d.setEnd(m.getEnd());
			d.setStart(m.getStart());
		}
		iv.setVoteOption(hd123Dao.findFeatureInteractVoteOptionById(fid,start,end));
		d.setInteractVote(iv);
		return d;
	}
	
	@Override
	public IDto configNew(long fid,Account account,long relationid) {
		Feature123Dto dto = new Feature123Dto();
		List<InteractVote> interactVote = hd123Dao.findInteractVoteByOwner(account.getOwner().getId());
		if(interactVote != null && interactVote.size()>0){
			for(int i=0;i<interactVote.size();i++){
				int totalOption = hd123Dao.findTotalOptionById(interactVote.get(i).getId());
				interactVote.get(i).setTotalOption(totalOption);
			}
		}
		Module m = hd123Dao.findVoteidByFid(fid);
		if(m != null){
			dto.setVoteid(m.getId());
			dto.setEnd(m.getEnd());
			dto.setStart(m.getStart());
		}
		dto.setInteractVote(interactVote);
		
		 long pageid = m.getPageid();
		 long siteid = siteDao.findSiteidByPageid(pageid);
		 List<Page> pageList = siteDao.findPageListBySiteId(siteid);
		 PageBlockRelation pbr = hd123Dao.findPageBlockRelationByRelationid(relationid);
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
		Feature123Dto d = (Feature123Dto)dto;
		configSub(featureid, dto, account);
		HdCard hc = new HdCard();
		hc.setFid(d.getFid());
		hc.setId(d.getVoteid());
		hc.setFeatureid(featureid);
		hc.setStart(d.getStart());
		hc.setEnd(d.getEnd());
		hc.setRedirect(d.getRedirect());
		Gson gson = new Gson();
		String str1 = gson.toJson(hc);
		PageBlockRelation pbr = hd123Dao.findPageBlockRelationByRelationid(d.getRelationid());
		String str = pbr.getJson();
		JSONObject jo = JSONObject.fromObject(str);
		jo.element("obj", str1);
		hd123Dao.updatePageBlockRelationByRelationid(d.getRelationid(),jo.toString());
		return "Y";
	}
}
