package com.huiyee.esite.fmgr.imp;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dao.IContentCategoryDao;
import com.huiyee.esite.dao.IPageDao;
import com.huiyee.esite.dao.ITemplateDao;
import com.huiyee.esite.dto.Feature155Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.showdto.Show154Dto;
import com.huiyee.esite.fdao.IHd154Dao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.BlockContext;
import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.HD154Model;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.PageBlockRelation;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Feature155ManagerImpl extends AbstractFeatureManager{
	private IHd154Dao hd154Dao;
	private IPageDao pageDao;
	private ITemplateDao templateDao;
	private IContentCategoryDao contentCategoryDao;
	
	
	
	public void setContentCategoryDao(IContentCategoryDao contentCategoryDao)
	{
		this.contentCategoryDao = contentCategoryDao;
	}

	public void setTemplateDao(ITemplateDao templateDao)
	{
		this.templateDao = templateDao;
	}

	public void setPageDao(IPageDao pageDao)
	{
		this.pageDao = pageDao;
	}

	public void setHd154Dao(IHd154Dao hd154Dao)
	{
		this.hd154Dao = hd154Dao;
	}

	@Override
	public long add(long pageid, long featureid, String featurename) {
		long fid = hd154Dao.saveFeatureInteract(pageid);
		return getPageFeatureDao().addPageFeature(pageid, featureid, fid,featurename,"N");
	}

	@Override
	public IDto config(long fid, Account account) {
		Feature155Dto dto = new Feature155Dto();
		HD154Model m = hd154Dao.findForumidListByFid(fid);
		if(m.getIds() != null){
			dto.setIds(JSONArray.fromObject(m.getIds()));
		}else{
			dto.setIds(new JSONArray());
		}
		Page p = pageDao.findPageById(m.getPageid());
		dto.setPagelist(pageDao.findPageBySiteid(p.getSiteid()));
		dto.setPageid(m.getTopage());
		dto.setType(m.getType());
		
		List<ContentCategory> list=hd154Dao.findCategory(account.getOwner().getId(),IPageConstants.CONTENT_PICTURE);
		JSONArray ja1=new JSONArray();
		for (ContentCategory contentCategory : list)
		{
			JSONObject jo=new JSONObject();
			jo.put("id", contentCategory.getId());
			jo.put("name", contentCategory.getName());
			jo.put("pId", contentCategory.getFartherCatId());
			if(dto.getIds().contains(contentCategory.getId())){
				jo.put("checked", true);
			}
			ja1.add(jo);
		}
		dto.setJson1(ja1.toString());
		
        return dto;
	}

	@Override
	public IDto config(long fid) {
		Show154Dto dto = new Show154Dto();
		dto.setFid(fid);
		HD154Model m = hd154Dao.findForumidListByFid(fid);
		JSONArray ja = JSONArray.fromObject(m.getIds());
		List<ContentCategory> list = new ArrayList<ContentCategory>();
		if(ja.size()>0){
			for(int i = 0 ;i<ja.size();i++){
				Long id = ja.getLong(i);
				ContentCategory cc = contentCategoryDao.findContentCategoryById(id);
				if(cc != null){
					list.add(cc);
				}
			}
		}
		dto.setList(list);
        return dto;
	}

	@Override
	public String configSub(long featureid, IDto dto, Account account) {
		Feature155Dto d = (Feature155Dto)dto;
	    try
		{
			if (d.getList()!=null && d.getList().size() > 0)
			{
				String type = d.getType();
				String ids = JSONArray.fromObject(d.getList()).toString();
				hd154Dao.saveFid(d.getFid(),ids , type,d.getPageid());
				for(Long catid : d.getList()){
					hd154Dao.updateContentCategoryPageid(catid, d.getPageid());//记录该目录的最近绑定在哪个page上
				}
			}
			else
			{
				String type = d.getType();
				String ids = new JsonArray().toString();
				hd154Dao.saveFid(d.getFid(),ids , type,d.getPageid());
			}
			return "Y";

		}
		catch (RuntimeException e)
		{
			e.printStackTrace();
			return "配置失败!";
		}
	}
	
	@Override
	public IDto configNew(long fid, Account account, long relationid) {
		 return config(fid, account);
	}


	@Override
	public String configSubNew(long featureid, IDto dto, Account account) {
		Feature155Dto d = (Feature155Dto) dto;
		JSONObject jo = new JSONObject();
		jo.element("fid", d.getFid());
		jo.element("featureid", featureid);
		if(d.getList()!=null && d.getList().size()>0){
			jo.element("ids", JSONArray.fromObject(d.getList()));
		}else{
			jo.element("ids", new JsonArray().toString());
		}
		configSub(featureid, dto, account);
		
		/**
		 * 获取详情页的pageid
		 */
		long relationid=d.getRelationid();
		List<BlockContext> list = templateDao.findesBlockContext(relationid);
		long contextid = 0;
		if (list != null && list.size() > 0) {
			contextid = list.get(0).getId();
		}
		Page page = templateDao.findPageByRelationidAndContextid(relationid, contextid);
		if(page != null){
			jo.element("pageid", page.getId());//详情页id
			if(d.getList()!=null){
				for(Long catid : d.getList()){
					hd154Dao.updateContentCategoryPageid(catid, page.getId());
				}
			}
		}
		
		PageBlockRelation pbr = hd154Dao.findPageBlockRelationByRelationid(d.getRelationid());
		JSONObject jo1 = JSONObject.fromObject(pbr.getJson());
		jo1.element("obj", jo.toString());
		hd154Dao.updatePageBlockRelationByRelationid(d.getRelationid(),jo1.toString());
		return "Y";
	}
}
