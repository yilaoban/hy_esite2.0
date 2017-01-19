package com.huiyee.esite.fmgr.imp;

import java.util.List;

import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.huiyee.esite.dao.ISiteDao;
import com.huiyee.esite.dto.Feature118Dto;
import com.huiyee.esite.dto.Feature125Dto;
import com.huiyee.esite.dto.Feature136Dto;
import com.huiyee.esite.dto.Feature138Dto;
import com.huiyee.esite.dto.Feature143Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.showdto.Show125Dto;
import com.huiyee.esite.fdao.IHd125Dao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.HdCard;
import com.huiyee.esite.model.Module;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.esite.model.PageRelation;
import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.lottery.model.LotteryPrize;

public class Feature125ManagerImpl extends AbstractFeatureManager{
	
	private IHd125Dao hd125Dao;
	private ISiteDao siteDao;
	
	public void setHd125Dao(IHd125Dao hd125Dao){
		this.hd125Dao = hd125Dao;
	}
	
	public void setSiteDao(ISiteDao siteDao)
	{
		this.siteDao = siteDao;
	}

	@Override
	public long add(long pageid, long featureid,String featurename) {
		String type = "";
		if(featureid == 125){
			type = "L";
		}else if (featureid == 136) {
			type = "Z";
		}else if (featureid == 138) {
			type = "G";
		}else if(featureid == 143){
			type = "Y";
		}
		long fid = hd125Dao.saveFeatureInteractLottery(pageid,type);
		return getPageFeatureDao().addPageFeature(pageid, featureid, fid,featurename,"N");
	}
	
	@Override
	public IDto config(long fid, Account account) {
		Feature125Dto dto = new Feature125Dto();
		Module m = hd125Dao.findLotteryTypeByFid(fid);
		if(m != null ){
			List<Lottery> lotteryList = hd125Dao.findInteractLotteryByOwner(account.getOwner().getId(),m.getType());
			dto.setLotteryList(lotteryList);
		}
		dto.setLotteryid(hd125Dao.findLotteryidByFid(fid).getId());
		return dto;
	}
	
	@Override
	public String configSub(long featureid, IDto dto, Account account) {
		String result = "N";
		String type = "";
		int res = 0;
		if(featureid == 125){
			type = "L";
			Feature125Dto d = (Feature125Dto) dto;
			List<Lottery> lotteryList = hd125Dao.findInteractLotteryByOwner(account.getOwner().getId(),type);
			d.setLotteryList(lotteryList);
			if(d.getLotteryid()!=0){
				res = hd125Dao.updateFeatureIneractLottery(d.getLotteryid(),d.getFid());
			}else{
				result = "noone";
			}
		}else if (featureid == 136) {
			type = "Z";
			Feature136Dto d = (Feature136Dto) dto;
			List<Lottery> lotteryList = hd125Dao.findInteractLotteryByOwner(account.getOwner().getId(),type);
			d.setLotteryList(lotteryList);
			if(d.getLotteryid()!=0){
				res = hd125Dao.updateFeatureIneractLottery(d.getLotteryid(),d.getFid());
			}else{
				result = "noone";
			}
		}else if (featureid == 138) {
			type = "G";
			Feature138Dto d = (Feature138Dto) dto;
			List<Lottery> lotteryList = hd125Dao.findInteractLotteryByOwner(account.getOwner().getId(),type);
			d.setLotteryList(lotteryList);
			if(d.getLotteryid()!=0){
				res = hd125Dao.updateFeatureIneractLottery(d.getLotteryid(),d.getFid());
			}else{
				result = "noone";
			}
		}else if(featureid == 143){
			type = "Y";
			Feature143Dto d = (Feature143Dto)dto;
			List<Lottery> lotteryList = hd125Dao.findInteractLotteryByOwner(account.getOwner().getId(),type);
			d.setLotteryList(lotteryList);
			if(d.getLotteryid()!=0){
				res = hd125Dao.updateFeatureIneractLottery(d.getLotteryid(),d.getFid());
			}else{
				result = "noone";
			}
		}
		if(res == 1){
			result = "Y";
	     } 
	    return result;
	}
	
	@Override
	public IDto config(long fid) {
		Show125Dto d = new Show125Dto();
		Lottery l = hd125Dao.findFeatureInteractLotteryById(fid);
		if(l != null){
			List<LotteryPrize> lp = hd125Dao.findLotteryPriceByLotteryid(l.getId());
			l.setLotteryPrizes(lp);
		}
		d.setLottery(l);
		return d;
	}
	
	@Override
	public IDto configNew(long fid,Account account,long relationid) {
//		return config(fid, account);
		 long pageid = hd125Dao.findLotteryidByFid(fid).getPageid();
		 long siteid = siteDao.findSiteidByPageid(pageid);
		 List<Page> pageList = siteDao.findPageListBySiteId(siteid);
		 PageBlockRelation pbr = hd125Dao.findPageBlockRelationByRelationid(relationid);
		 String str = pbr.getJson();
		 JSONObject jo = JSONObject.fromObject(str);
		 String hd = jo.get("obj").toString();
		 String type = "N";
		 String url = null;
		 String urlShow = null;
		 String urlPre = null;
		 String furl = null;
		 String furlShow = null;
		 long toPageid = 0; 
		 try {
			 jo = JSONObject.fromObject(hd);
			 hd = jo.get("redirect").toString();
			 jo = JSONObject.fromObject(hd);
			 type = jo.getString("type");
			 url =jo.getString("url");
			 urlShow = jo.getString("urlShow");
			 urlPre = jo.getString("urlPre");
			 furl =jo.getString("furl");
			 furlShow = jo.getString("furlShow");
			 toPageid = jo.getLong("toPageid");
		 }catch (Exception e) {
			//e.printStackTrace();
		}
		 Feature125Dto dto = (Feature125Dto) config(fid, account);
		 dto.setType(type);
		 dto.setUrl(url);
		 dto.setUrlPre(urlPre);
		 dto.setUrlShow(urlShow);
		 dto.setFurl(furl);
		 dto.setFurlShow(furlShow);
		 dto.setPageList(pageList);
		 dto.setToPageid(toPageid);
        return dto;
	}
	 
	@Override
	public String configSubNew(long featureid, IDto dto, Account account) {
		if(featureid == 125){
			Feature125Dto d = (Feature125Dto) dto;
			configSub(featureid, dto, account);
			HdCard hc = new HdCard();
			hc.setFid(d.getFid());
			hc.setId(d.getLotteryid());
			hc.setFeatureid(featureid);
			hc.setRedirect(d.getRedirect());
			Gson gson = new Gson();
			String str1 = gson.toJson(hc);
			PageBlockRelation pbr = hd125Dao.findPageBlockRelationByRelationid(d.getRelationid());
			String str = pbr.getJson();
			JSONObject jo = JSONObject.fromObject(str);
			jo.element("obj", str1);
			hd125Dao.updatePageBlockRelationByRelationid(d.getRelationid(),jo.toString());
		}else if(featureid == 136){
			Feature136Dto d = (Feature136Dto) dto;
			configSub(featureid, dto, account);
			HdCard hc = new HdCard();
			hc.setFid(d.getFid());
			hc.setId(d.getLotteryid());
			hc.setFeatureid(featureid);
			hc.setRedirect(d.getRedirect());
			Gson gson = new Gson();
			String str1 = gson.toJson(hc);
			PageBlockRelation pbr = hd125Dao.findPageBlockRelationByRelationid(d.getRelationid());
			String str = pbr.getJson();
			JSONObject jo = JSONObject.fromObject(str);
			jo.element("obj", str1);
			hd125Dao.updatePageBlockRelationByRelationid(d.getRelationid(),jo.toString());
		}else if(featureid == 138){
			Feature138Dto d = (Feature138Dto) dto;
			configSub(featureid, dto, account);
			HdCard hc = new HdCard();
			hc.setFid(d.getFid());
			hc.setId(d.getLotteryid());
			hc.setFeatureid(featureid);
			hc.setRedirect(d.getRedirect());
			Gson gson = new Gson();
			String str1 = gson.toJson(hc);
			PageBlockRelation pbr = hd125Dao.findPageBlockRelationByRelationid(d.getRelationid());
			String str = pbr.getJson();
			JSONObject jo = JSONObject.fromObject(str);
			jo.element("obj", str1);
			hd125Dao.updatePageBlockRelationByRelationid(d.getRelationid(),jo.toString());
		}else if(featureid == 143){
			Feature143Dto d = (Feature143Dto)dto;
			configSub(featureid, dto, account);
			HdCard hc = new HdCard();
			hc.setFid(d.getFid());
			hc.setId(d.getLotteryid());
			hc.setFeatureid(featureid);
			hc.setRedirect(d.getRedirect());
			Gson gson = new Gson();
			String str1 = gson.toJson(hc);
			PageBlockRelation pbr = hd125Dao.findPageBlockRelationByRelationid(d.getRelationid());
			String str = pbr.getJson();
			JSONObject jo = JSONObject.fromObject(str);
			jo.element("obj", str1);
			hd125Dao.updatePageBlockRelationByRelationid(d.getRelationid(),jo.toString());
		}
		return "Y";
	}
}
