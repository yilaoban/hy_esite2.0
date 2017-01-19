package com.huiyee.esite.fmgr.imp;

import java.util.List;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dao.IPageDao;
import com.huiyee.esite.dto.Feature110Dto;
import com.huiyee.esite.dto.Feature147Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.fdao.IHd147Dao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.ContentNew;
import com.huiyee.esite.model.FeatureNews;
import com.huiyee.esite.model.FeatureNewslist;
import com.huiyee.esite.model.Page;

public class Feature147ManagerImpl extends AbstractFeatureManager
{
	private IHd147Dao hd147Dao;
	private IPageDao pageDao;

	public void setHd147Dao(IHd147Dao hd147Dao)
	{
		this.hd147Dao = hd147Dao;
	}
	
	@Override
	public long add(long pageid, long featureid,String featurename) {
		long fid = hd147Dao.addNewsList(pageid);
		return getPageFeatureDao().addPageFeature(pageid, featureid, fid,featurename,"N");
	}

	@Override
	public IDto config(long fid) {
		Feature147Dto dto = new Feature147Dto();
		dto.setFid(fid);
		FeatureNewslist news = hd147Dao.findCatidByFid(fid);
		if(news != null){
			dto.setPageid(news.getXpid());			
		}
		dto.setNewList(hd147Dao.findNewsListByFid(fid));
		return dto;
	}

	@Override
	public IDto config(long fid, Account account) {
		Feature147Dto dto = new Feature147Dto();
		dto.setFid(fid);
		FeatureNewslist news = hd147Dao.findCatidByFid(fid);
		if(news != null ){
			dto.setCatid(news.getCategoryid());
		}
		dto.setCatList(hd147Dao.findContentCategoryListByOwner(account.getOwner().getId()));
		Page p = pageDao.findPageById(news.getPageid());
		dto.setPagelist(pageDao.findPageBySiteid(p.getSiteid()));
		return dto;
	}
	
	@Override
	public String configSub(long featureid, IDto dto, Account account) {
		Feature147Dto featuredto = (Feature147Dto) dto;
		List<ContentNew> list = hd147Dao.findNewListByCatid(featuredto.getCatid());
		hd147Dao.updateFeatureNnewslistCatidByFid(featuredto.getFid(),featuredto.getCatid(),featuredto.getPageid());
		hd147Dao.deleteAllRelation(featuredto.getFid());
		if(list != null){
			for (ContentNew fn : list) {
				int idx = hd147Dao.findIdxByfid(featuredto.getFid());
				idx = idx + 1;
				hd147Dao.addReation(featuredto.getFid(),fn.getId(),idx);
			}
		}
		return "Y";
	}

	public void setPageDao(IPageDao pageDao) {
		this.pageDao = pageDao;
	}

	
	
}
