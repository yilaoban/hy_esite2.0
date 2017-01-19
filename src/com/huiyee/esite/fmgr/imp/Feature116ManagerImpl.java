package com.huiyee.esite.fmgr.imp;

import java.util.List;

import com.huiyee.esite.dto.Feature116Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.showdto.Show116Dto;
import com.huiyee.esite.fdao.ISinaShareRecordCheckListDao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.SinaChecklistRecord;
import com.huiyee.esite.model.SinaShareRecordCategory;

public class Feature116ManagerImpl extends AbstractFeatureManager {
	
	private ISinaShareRecordCheckListDao checkListDao;
	
	@Override
	public long add(long pageid, long featureid,String featurename) {
		long fid = checkListDao.saveSinaShareReocrdCheckList(pageid);
		return getPageFeatureDao().addPageFeature(pageid, featureid, fid,featurename,"N");
	}
	
	@Override
	public IDto config(long fid) {
		Show116Dto dto = new Show116Dto();
		List<SinaShareRecordCategory> category=checkListDao.findSinaShareRecordCategoryByFid(fid);
		for(SinaShareRecordCategory c : category){
			c.setList(checkListDao.findSinaChecklistRecordByCategoryId(c.getId()));
		}
		dto.setList(category);
		dto.setFid(fid);
		return dto;
	}
	
	@Override
	public IDto config(long fid, Account account) {
		Feature116Dto dto = new Feature116Dto();
		List<SinaChecklistRecord> list = checkListDao.findSinaCheckListRecordByFid(fid);
		dto.setList(list);
		List<SinaShareRecordCategory> category=checkListDao.findSinaShareRecordCategoryByFid(fid);
		for(SinaShareRecordCategory c : category){
			c.setList(checkListDao.findSinaChecklistRecordByCategoryId(c.getId()));
		}
		dto.setCategory(category);
		dto.setFid(fid);
		return dto;
	}
	
	@Override
	public String configSub(long featureid, IDto dto, Account account) {
		Feature116Dto d = (Feature116Dto)dto;
		if(d.getCategoryid().size() == d.getRecordid().size() || d.getRecordid().size() == d.getIdx().size()){
			checkListDao.deleteSinaShareRecordChekcList(d.getFid());
			for (int i = 0; i < d.getCategoryid().size(); i++) {
				if(d.getCategoryid().get(i)!= 0){
					checkListDao.saveSinaShareRecordCheckList(d.getRecordid().get(i), d.getCategoryid().get(i), d.getIdx().get(i));					
				}
			}
		}
		return "Y";
	}

	public void setCheckListDao(ISinaShareRecordCheckListDao checkListDao) {
		this.checkListDao = checkListDao;
	}

}
