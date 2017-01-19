package com.huiyee.esite.fmgr.imp;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.huiyee.esite.dao.IPageDao;
import com.huiyee.esite.dto.Feature150Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.fdao.IHd150Dao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.HD150Model;
import com.huiyee.esite.model.Page;

public class Feature150ManagerImpl extends AbstractFeatureManager{

	private IHd150Dao hd150Dao;
	private IPageDao pageDao;
	
	public void setHd150Dao(IHd150Dao hd150Dao) {
		this.hd150Dao = hd150Dao;
	}

	public void setPageDao(IPageDao pageDao) {
		this.pageDao = pageDao;
	}

	@Override
	public long add(long pageid, long featureid,String featurename) {
		long fid = hd150Dao.saveFeatureInteract(pageid);
		return getPageFeatureDao().addPageFeature(pageid, featureid, fid,featurename,"N");
	}
	
	@Override
	public IDto config(long fid)
	{
		Feature150Dto dto = new Feature150Dto();
		dto.setFid(fid);
		HD150Model m=hd150Dao.findModelByFid(fid);
		ContentCategory current = new ContentCategory();
		current.setId(m.getCategoryid());
		current.setType(m.getType());
		dto.setCurrent(current);
		dto.setPageid(m.getTopage());
		List<ContentCategory> list=hd150Dao.findCategoryByCateid(current.getId());
		dto.setCatList(list);
		return dto;
	}
	
	
	@Override
	public IDto config(long fid, Account account)
	{
		Feature150Dto dto = new Feature150Dto();
		dto.setFid(fid);
		HD150Model m=hd150Dao.findModelByFid(fid);
		ContentCategory current = new ContentCategory();
		current.setId(m.getCategoryid());
		current.setType(m.getType());
		dto.setCurrent(current);
		List<ContentCategory> list=hd150Dao.findCategory(account.getOwner().getId());
		JSONArray ja=new JSONArray();
		JSONArray ja1=new JSONArray();
		JSONArray ja2=new JSONArray();
		JSONArray ja3=new JSONArray();
		for (ContentCategory contentCategory : list)
		{
			JSONObject jo=new JSONObject();
			jo.put("id", contentCategory.getId());
			jo.put("name", contentCategory.getName());
			jo.put("pId", contentCategory.getFartherCatId());
			if(contentCategory.getId()==current.getId()){
				jo.put("checked", true);
			}
			if("T".equals(contentCategory.getType())){
				ja.add(jo);
			}else if("P".equals(contentCategory.getType())){
				ja1.add(jo);
			}else if("V".equals(contentCategory.getType())){
				ja2.add(jo);
			}else if("N".equals(contentCategory.getType())){
				ja3.add(jo);
			}
		}
		dto.setJson(ja.toString());
		dto.setJson1(ja1.toString());
		dto.setJson2(ja2.toString());
		dto.setJson3(ja3.toString());
		Page p = pageDao.findPageById(m.getPageid());
		dto.setPagelist(pageDao.findPageBySiteid(p.getSiteid()));
		dto.setPageid(m.getTopage());
		return dto;
	}
	
	@Override
	public String configSub(long featureid, IDto dto, Account account)
	{
		try
		{
			Feature150Dto featuredto = (Feature150Dto) dto;
			if (featuredto.getCurrent() != null && featuredto.getCurrent().getId() != 0)
			{
				long catid = featuredto.getCurrent().getId();
				String type = hd150Dao.findContentType(catid);
				hd150Dao.saveFid(featuredto.getFid(), catid, type,featuredto.getPageid());
				return "Y";
			}
			else
			{
				return "√ª”–≈‰÷√ƒø¬º.";
			}

		}
		catch (RuntimeException e)
		{
			e.printStackTrace();
			return "≈‰÷√ ß∞‹!";
		}
	}
	
}
