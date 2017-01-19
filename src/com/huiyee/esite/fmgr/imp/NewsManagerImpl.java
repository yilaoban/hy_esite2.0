package com.huiyee.esite.fmgr.imp;

import java.util.List;

import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.huiyee.esite.dao.IPageDao;
import com.huiyee.esite.dao.ITemplateDao;
import com.huiyee.esite.dto.Feature110Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.fdao.INewsListDao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.BlockContext;
import com.huiyee.esite.model.CategoryTree;
import com.huiyee.esite.model.Content;
import com.huiyee.esite.model.FeatureNews;
import com.huiyee.esite.model.FeatureNewslist;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.esite.model.PageRelation;

public class NewsManagerImpl extends AbstractFeatureManager {
	private INewsListDao newsListDao;
	private IPageDao pageDao;
	private ITemplateDao templateDao;

	
	public void setTemplateDao(ITemplateDao templateDao)
	{
		this.templateDao = templateDao;
	}

	public void setNewsListDao(INewsListDao newsListDao) {
		this.newsListDao = newsListDao;
	}
	
	@Override
	public long add(long pageid, long featureid,String featurename) {
		long fid = newsListDao.addNewsList(pageid);
		return getPageFeatureDao().addPageFeature(pageid, featureid, fid,featurename,"N");
	}

	@Override
	public IDto config(long fid) {
		Feature110Dto dto = new Feature110Dto();
		dto.setFid(fid);
		dto.setList(newsListDao.findNewsListByFid(fid));
		FeatureNewslist news = newsListDao.findCatidByFid(fid);
		dto.setPageid(news.getXpid());
		return dto;
	}

	@Override
	public String configSub(long featureid, IDto dto, Account account) {
		Feature110Dto featuredto = (Feature110Dto) dto;
		List<FeatureNews> list = featuredto.getList();
		newsListDao.deleteAllRelation(featuredto.getFid());
		newsListDao.updateFeatureNnewslistXpidByFid(featuredto.getFid(), featuredto.getPageid());
		if(list != null){
			for (FeatureNews fn : list) {
				if (fn != null) {
					if (fn.getId() < 0 && fn.getIdx() <= 0) {
						int idx = newsListDao.findIdxByfid(featuredto.getFid());
						fn.setIdx(idx + 1);
					}
					newsListDao.addReation(fn);
				}
			}			
		}
		return "Y";
	}

	@Override
	public IDto config(long fid, Account account) {
		Feature110Dto dto = new Feature110Dto();
		dto.setFid(fid);
		dto.setList(newsListDao.findNewsListByFid(fid));
		FeatureNewslist news = newsListDao.findCatidByFid(fid);
		Page p = pageDao.findPageById(news.getPageid());
		dto.setPagelist(pageDao.findPageBySiteid(p.getSiteid()));
		return dto;
	}
	
	@Override
	public IDto configNew(long fid, Account account, long relationid) {
		Feature110Dto dto = new Feature110Dto();
		dto.setFid(fid);
		List<CategoryTree> categoryTreeList = newsListDao.findTreeByType("N", account.getOwner().getId());
		dto.setCategoryTreeList(categoryTreeList);
		PageBlockRelation pbr = newsListDao.findPageBlockRelationByRelationid(relationid);
		if(pbr != null){
			JSONObject jb = JSONObject.fromObject(pbr.getJson());
			String hd = jb.get("obj").toString();
			jb = JSONObject.fromObject(hd);
			Object obj = jb.get("ccid");
			if(obj != null){
				long ccid = jb.getLong("ccid");
				dto.setCcid(ccid);
			}
		}
		return dto;
	}
	
	@Override
	public String configSubNew(long featureid, IDto dto, Account account) {
		Feature110Dto d = (Feature110Dto) dto;
		Content content = new Content();
		content.setCcid(d.getCcid());
		content.setFeatureid(featureid);
		content.setHyType("N");
		long ownerid = account.getOwner().getId();
		content.setOwnerid(ownerid);
		
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
		content.setPageid(page.getId());//详情页id
		newsListDao.updateContentCategoryPageid(d.getCcid(), page.getId());
		
		Gson gson = new Gson();
		String str1 = gson.toJson(content);
		PageBlockRelation pbr = newsListDao.findPageBlockRelationByRelationid(d.getRelationid());
		String str = pbr.getJson();
		JSONObject jo = JSONObject.fromObject(str);
		jo.element("obj", str1);
		newsListDao.updatePageBlockRelationByRelationid(d.getRelationid(),jo.toString());
		return "Y";
	}

	public void setPageDao(IPageDao pageDao) {
		this.pageDao = pageDao;
	}
	
	
}
