package com.huiyee.esite.fmgr.imp;

import java.util.ArrayList;
import java.util.List;

import com.huiyee.esite.dto.Feature122Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.showdto.Show122Dto;
import com.huiyee.esite.fdao.IHd122Dao;
import com.huiyee.esite.model.Account;
import com.huiyee.interact.EmailPeriodical.model.EmailPeriodicalModel;

public class Feature122ManagerImpl extends AbstractFeatureManager {
	private IHd122Dao hd122Dao;
	public void setHd122Dao(IHd122Dao hd122Dao) {
		this.hd122Dao = hd122Dao;
	}

	@Override
	public long add(long pageid, long featureid,String featurename) {
		long fid = hd122Dao.saveFeatureInteract(pageid);
		return getPageFeatureDao().addPageFeature(pageid, featureid, fid,featurename,"N");
	}

	@Override
	public IDto config(long fid, Account account) {
		Feature122Dto dto = new Feature122Dto();
		List<EmailPeriodicalModel> emailPeriodical = hd122Dao.findEmailPeriodicalByOwner(account.getOwner().getId(),fid);
		ArrayList<Long> moduleList = (ArrayList<Long>) hd122Dao.findFeaturePublishByFid(fid);
		dto.setModuleList(moduleList);
		dto.setEmailPeriodical(emailPeriodical);
		return dto;
	}

	@Override
	public String configSub(long featureid, IDto dto, Account account) {
		 String result = "N";
		 Feature122Dto d = (Feature122Dto) dto;
		 List<EmailPeriodicalModel> emailPeriodical = hd122Dao.findEmailPeriodicalByOwner(account.getOwner().getId(),d.getFid());
		 d.setEmailPeriodical(emailPeriodical);
		 if(d.getList() != null && d.getList().size()>0){
			 hd122Dao.deleteFeaturePublish(d.getFid());
			 for(int i=0;i<d.getList().size();i++){
				 String[] arr = d.getList().get(i).split(",");
				 long pid = Long.parseLong(arr[0]);
				 String name = arr[1];
				 long idx = Long.parseLong(arr[2]);
				 hd122Dao.saveFeaturePublish(d.getFid(), pid, name, idx);
			 }
		 }
		 result = "Y";
		 return result;
	}
	
	@Override
	public IDto config(long fid) {
		 Show122Dto d = new Show122Dto();
		 List<EmailPeriodicalModel> list  = hd122Dao.findUrlByFid(fid);
		 EmailPeriodicalModel emaliPeriodical = hd122Dao.findEmailPeriodicalModelByFid(fid);
		 d.setEmaliPeriodical(emaliPeriodical);
		 d.setList(list);
		 return d;
		
	}

}
