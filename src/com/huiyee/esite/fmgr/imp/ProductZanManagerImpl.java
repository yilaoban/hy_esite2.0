package com.huiyee.esite.fmgr.imp;

import com.huiyee.esite.dto.DynamicActionDto;
import com.huiyee.esite.fdao.IProListProductDao;
import com.huiyee.esite.model.UserInfo;


public class ProductZanManagerImpl extends AbstractFeatureManager {

	private IProListProductDao proListProductDao;
	@Override
	public String dynamicAction(long uid, DynamicActionDto dto) {
		if(uid==0){
			return "Y";
		}
		long id = proListProductDao.findProductZan(dto.getFppid(), uid);
		if(id>0){
			return "рятч";			
		}
		long entityid = proListProductDao.addProductZan(dto.getFppid(), uid, dto.getIp(), dto.getTerminal(),dto.getSource(),dto.getPageid());
		proListProductDao.addZan(dto.getFppid());
		return "Y";
	}
	public void setProListProductDao(IProListProductDao proListProductDao) {
		this.proListProductDao = proListProductDao;
	}
	
}
