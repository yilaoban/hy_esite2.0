package com.huiyee.esite.fmgr.imp;

import java.util.List;

import com.huiyee.esite.dto.Feature148Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.fdao.IHd148Dao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.ContentPicture;
import com.huiyee.esite.model.FeaturePiclist;

public class Feature148ManagerImpl extends AbstractFeatureManager
{
	private IHd148Dao hd148Dao;

	public void setHd148Dao(IHd148Dao hd148Dao)
	{
		this.hd148Dao = hd148Dao;
	}

	@Override
	public long add(long pageid, long featureid,String featurename) {
		 long fid = hd148Dao.saveFeturePicture(pageid);
	     return getPageFeatureDao().addPageFeature(pageid, featureid, fid,featurename,"N");
	}

	@Override
	public IDto config(long fid) {
		Feature148Dto dto = new Feature148Dto();
		dto.setFid(fid);
		dto.setPicList(hd148Dao.findPictureById(fid));
		return dto;
	}

	@Override
	public IDto config(long fid, Account account) {
		Feature148Dto dto = new Feature148Dto();
		dto.setFid(fid);
		FeaturePiclist pic = hd148Dao.findCatidByFid(fid);
		if(pic != null ){
			dto.setCatid(pic.getCategoryid());
		}
		dto.setCatList(hd148Dao.findContentCategoryListByOwner(account.getOwner().getId()));
		return dto;
	}
	
	@Override
	public String configSub(long featureid, IDto dto, Account account) {
		Feature148Dto featuredto = (Feature148Dto) dto;
		List<ContentPicture> list = hd148Dao.findPicListByCatid(featuredto.getCatid());
		hd148Dao.updateFeaturePiclistCatidByFid(featuredto.getFid(),featuredto.getCatid());
		hd148Dao.deleteAllRelation(featuredto.getFid());
		if(list != null){
			for (ContentPicture fn : list) {
				int idx = hd148Dao.findIdxByfid(featuredto.getFid());
				idx = idx + 1;
				hd148Dao.addReation(featuredto.getFid(),fn.getId(),idx);
			}
		}
		return "Y";
	}

	
	
}
