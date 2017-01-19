package com.huiyee.esite.fmgr.imp;

import com.huiyee.esite.dto.Feature146Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.showdto.Show146Dto;
import com.huiyee.esite.fdao.IHd146Dao;
import com.huiyee.esite.model.Account;

public class Feature146ManagerImpl extends AbstractFeatureManager {
	
	private IHd146Dao hd146Dao;

	public void setHd146Dao(IHd146Dao hd146Dao) {
		this.hd146Dao = hd146Dao;
	}
	
	@Override
	public long add(long pageid, long featureid, String featurename) {
		long fid = hd146Dao.saveXc(pageid);
		return getPageFeatureDao().addPageFeature(pageid, featureid, fid,featurename,"N");
	}
	
	@Override
	public IDto config(long fid) {
		Show146Dto dto = new Show146Dto();
		dto.setXc(hd146Dao.findXcById(fid));
		return dto;
	}
	
	@Override
	public IDto config(long fid, Account account) {
		Feature146Dto dto = new Feature146Dto();
		dto.setFid(fid);
		dto.setXcid(hd146Dao.findXcidByFid(fid));
		dto.setXclist(hd146Dao.findXcList(account.getOwner().getId()));
		return dto;
	}
	
	@Override
	public String configSub(long featureid, IDto dto, Account account) {
		String result = "N";
		Feature146Dto d = (Feature146Dto) dto;
		int res = hd146Dao.updateXcid(d.getXcid(), d.getFid());
		if(res == 1){
        	result = "Y";
        }
		return result;
	}
	
}
