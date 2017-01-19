package com.huiyee.esite.fmgr.imp;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.huiyee.esite.dto.Feature151Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.showdto.Show151Dto;
import com.huiyee.esite.fdao.IHd151Dao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.BBSForum;
import com.huiyee.esite.model.HD151Model;
import com.huiyee.esite.model.HdCard;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.esite.model.PageRelation;

public class Feature151ManagerImpl extends AbstractFeatureManager{
	private IHd151Dao hd151Dao;

	public void setHd151Dao(IHd151Dao hd151Dao) {
		this.hd151Dao = hd151Dao;
	}

	@Override
	public long add(long pageid, long featureid, String featurename) {
		long fid = hd151Dao.saveFeatureInteract(pageid);
		return getPageFeatureDao().addPageFeature(pageid, featureid, fid,featurename,"N");
	}

	@Override
	public IDto config(long fid, Account account) {
		Feature151Dto dto = new Feature151Dto();
		long ownerid = account.getOwner().getId();
		HD151Model m = hd151Dao.findForumidListByFid(fid);
		List<Long> froumidList = new ArrayList<Long>();
		if(m != null && m.getForumid() != null){
			String forumid = m.getForumid();
			JSONArray ja = JSONArray.fromObject(forumid);
			if(ja != null && ja.size()>0){
				for(int i = 0;i<ja.size();i++){
					froumidList.add(ja.getLong(i));
				}
			}
		}
		dto.setFroumidList(froumidList);
		List<BBSForum> bbsForumList = hd151Dao.findForumListByOwnerid(ownerid,account.getId());
		dto.setBbsForumList(bbsForumList);
        return dto;
	}

	@Override
	public IDto config(long fid) {
		Show151Dto dto = new Show151Dto();
		dto.setFid(fid);
		HD151Model m = hd151Dao.findForumidListByFid(fid);
		List<BBSForum> bbsForumList = new ArrayList<BBSForum>();
		if(m != null && m.getForumid() != null){
			String forumid = m.getForumid();
			JSONArray ja = JSONArray.fromObject(forumid);
			if(ja != null && ja.size()>0){
				for(int i = 0;i<ja.size();i++){
					BBSForum bbsForum = hd151Dao.findBBSForumById(ja.getLong(i));
					if(bbsForum != null){
						bbsForumList.add(bbsForum);
					}
				}
			}
		}
		dto.setList(bbsForumList);
        return dto;
	}

	@Override
	public String configSub(long featureid, IDto dto, Account account) {
		String result = "N";
		Feature151Dto d = (Feature151Dto)dto;
		long ownerid = account.getOwner().getId();
		List<BBSForum> bbsForumList = hd151Dao.findForumListByOwnerid(ownerid,account.getId());
		d.setBbsForumList(bbsForumList);
		int res =  hd151Dao.updateFeatureIneractCommunity(d.getForumid(),d.getFid());
		if(res == 1){
			result = "Y";
	     }
	    return result;
	}
	
	@Override
	public IDto configNew(long fid, Account account, long relationid) {
		 return config(fid, account);
	}


	@Override
	public String configSubNew(long featureid, IDto dto, Account account) {
		Feature151Dto d = (Feature151Dto) dto;
		configSub(featureid, dto, account);
		HdCard hc = new HdCard();
		hc.setFid(d.getFid());
		hc.setFeatureid(featureid);
		hc.setForumid(d.getForumid());
		Gson gson = new Gson();
		String str1 = gson.toJson(hc);
		PageBlockRelation pbr = hd151Dao.findPageBlockRelationByRelationid(d.getRelationid());
		String str = pbr.getJson();
		JSONObject jo = JSONObject.fromObject(str);
		jo.element("obj", str1);
		hd151Dao.updatePageBlockRelationByRelationid(d.getRelationid(),jo.toString());
		return "Y";
	}
}
