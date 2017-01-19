package com.huiyee.interact.servicerpj.mgr;

import java.util.List;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.interact.servicerpj.dao.IServicerPjWdDao;
import com.huiyee.interact.servicerpj.dto.ServicerPjDto;
import com.huiyee.interact.servicerpj.model.ServicerPjWd;

public class ServicerPjWdMgrImpl implements IServicerPjWdMgr {
	private IServicerPjWdDao servicerPjWdDao;

	public void setServicerPjWdDao(IServicerPjWdDao servicerPjWdDao) {
		this.servicerPjWdDao = servicerPjWdDao;
	}

	@Override
	public IDto findServicerPjwdList(long owner,int pageId) {
		ServicerPjDto dto = new ServicerPjDto();
		int total = servicerPjWdDao.findTotalServicerPjwd(owner);
		if(total > 0){
			List<ServicerPjWd> pjwdList = servicerPjWdDao.findServicerPjwdList(owner, (pageId - 1) * IInteractConstants.INTERACT_SERVICER_PJ_LIMIT, IInteractConstants.INTERACT_SERVICER_PJ_LIMIT);
			dto.setPjwdList(pjwdList);
			dto.setPager(new Pager(pageId, total, IInteractConstants.INTERACT_SERVICER_PJ_LIMIT));
		}
		return dto;
	}

	@Override
	public int savepjwd(long owner, String name) {
		return servicerPjWdDao.savepjwd(owner,name);
	}

	@Override
	public ServicerPjWd findPjWdById(long id) {
		return servicerPjWdDao.findPjWdById(id);
	}

	@Override
	public int updatePjWdById(String name, long id) {
		return servicerPjWdDao.updatePjWdById(name,id);
	}

	@Override
	public int delPjWdById(long id) {
		return servicerPjWdDao.delPjWdById(id);
	}
	
}
