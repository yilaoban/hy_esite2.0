package com.huiyee.esite.fmgr.imp;

import java.util.List;

import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.huiyee.esite.dao.ISiteDao;
import com.huiyee.esite.dto.Feature118Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.showdto.Show118Dto;
import com.huiyee.esite.fdao.IHd118Dao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.Feature118InteractApt;
import com.huiyee.esite.model.HdCard;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.esite.model.PageRelation;
import com.huiyee.interact.appointment.model.AppointmentModel;
import com.huiyee.interact.appointment.model.OrderMappingModel;

public class Feature118ManagerImpl extends AbstractFeatureManager {
	private IHd118Dao hd118Dao;
	private ISiteDao siteDao;
	
	public void setSiteDao(ISiteDao siteDao)
	{
		this.siteDao = siteDao;
	}

	public void setHd118Dao(IHd118Dao hd118Dao) {
		this.hd118Dao = hd118Dao;
	}

	@Override
	public long add(long pageid, long featureid,String featurename) {
		long fid = hd118Dao.saveFeatureInteract(pageid);
		return getPageFeatureDao().addPageFeature(pageid, featureid, fid,featurename,"N");
	}
	
	@Override
    public IDto config(long fid, Account account) {
        Feature118Dto dto = new Feature118Dto();
        dto.setAptid(hd118Dao.findAptidByFid(fid).getId());
        dto.setName(hd118Dao.findAptidByFid(fid).getName());
        List<Feature118InteractApt> interactApt = hd118Dao.findApponitmentsByOwner(account.getOwner().getId()); 
        dto.setInteractApt(interactApt);
        return dto;
    }
	
	 @Override
	    public String configSub(long featureid, IDto dto, Account account) {
	        String result = "N";
	        Feature118Dto d = (Feature118Dto) dto;
	        List<Feature118InteractApt> interactApt = hd118Dao.findApponitmentsByOwner(account.getOwner().getId()); 
	        d.setInteractApt(interactApt);
	        int res = hd118Dao.updateFeatureIneractApt(d.getAptid(),d.getFid());
	        if(d.getName() != null && !d.getName().equals(hd118Dao.findAptidByFid(d.getFid()).getName())){
	        	hd118Dao.updateupdateFeatureIneractAptName(d.getFid(),d.getName());
	        }
	        if(res == 1){
	        	result = "Y";
	        }
	        return result;
	    }
	 
	 @Override
	    public IDto config(long fid) {
		 	Show118Dto d = new Show118Dto();
	        AppointmentModel apt = hd118Dao.findFeatureInteractAptById(fid);
			List<OrderMappingModel> list = hd118Dao.findInteractAptMappingById(apt.getId());
			if(list.size()> 0 ){
		 		apt.setList(list);
		 	}
			d.setApp(apt);
			return d;
	    }
	 
	 @Override
	public IDto configNew(long fid,Account account,long relationid) {
		 long pageid = hd118Dao.findAptidByFid(fid).getPageid();
		 long siteid = siteDao.findSiteidByPageid(pageid);
		 List<Page> pageList = siteDao.findPageListBySiteId(siteid);
		 PageBlockRelation pbr = hd118Dao.findPageBlockRelationByRelationid(relationid);
		 String str = pbr.getJson();
		 JSONObject jo = JSONObject.fromObject(str);
		 String hd = jo.get("obj").toString();
		 String hd2 = jo.get("obj").toString();
		 String type = "N";
		 String url = null;
		 String urlShow = null;
		 String urlPre = null;
		 String color = null;
		 String btcontent = null;
		 String btcolor = null;
		 long size = 0;
		 long btsize = 0;
		 long bottom = 0;
		 long toPageid = 0; 
		 String btbg = null;
		 String bttm = null;
		 try {
			 jo = JSONObject.fromObject(hd2);
			 hd2 = jo.get("style118").toString();
			 jo = JSONObject.fromObject(hd2);
			 color = jo.getString("color");
			 size = jo.getLong("size");
			 bottom = jo.getLong("bottom");
			 btcontent = jo.getString("btcontent");
			 btcolor = jo.getString("btcolor");
			 btsize = jo.getLong("btsize");
			 btbg = jo.getString("btbg");
			 bttm = jo.getString("bttm");
			 
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
		 Feature118Dto dto = (Feature118Dto) config(fid, account);
		 dto.setType(type);
		 dto.setUrl(url);
		 dto.setUrlPre(urlPre);
		 dto.setUrlShow(urlShow);
		 dto.setPageList(pageList);
		 dto.setToPageid(toPageid);
		 dto.setColor(color);
		 dto.setSize(size);
		 dto.setBottom(bottom);
		 dto.setBtcontent(btcontent);
		 dto.setBtcolor(btcolor);
		 dto.setBtsize(btsize);
		 dto.setBtbg(btbg);
		 dto.setBttm(bttm);
        return dto;
	}
	 
	 @Override
	public String configSubNew(long featureid, IDto dto, Account account) {
		Feature118Dto d = (Feature118Dto) dto;
		configSub(featureid, dto, account);
		HdCard hc = new HdCard();
		hc.setFid(d.getFid());
		hc.setId(d.getAptid());
		hc.setFeatureid(featureid);
		hc.setRedirect(d.getRedirect());
		hc.setStyle118(d.getStyle118());
		Gson gson = new Gson();
		String str1 = gson.toJson(hc);
		PageBlockRelation pbr = hd118Dao.findPageBlockRelationByRelationid(d.getRelationid());
		String str = pbr.getJson();
		JSONObject jo = JSONObject.fromObject(str);
		jo.element("obj", str1);
		hd118Dao.updatePageBlockRelationByRelationid(d.getRelationid(),jo.toString());
		return "Y";
	}
	 
}
