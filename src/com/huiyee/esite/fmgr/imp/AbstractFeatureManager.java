package com.huiyee.esite.fmgr.imp;

import java.util.List;

import com.huiyee.esite.dao.IDynamicActionRecordDao;
import com.huiyee.esite.dao.IPageFeatureDao;
import com.huiyee.esite.dao.IPageRelationDao;
import com.huiyee.esite.dto.DynamicActionDto;
import com.huiyee.esite.dto.ExportDto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.fmgr.IFeatureManager;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.VisitUser;

public abstract class AbstractFeatureManager implements IFeatureManager {

	protected IPageFeatureDao pageFeatureDao;
	protected IDynamicActionRecordDao dynamicActionRecordDao;
	protected IPageRelationDao pageRelationDao;
	
	public IPageRelationDao getPageRelationDao()
	{
		return pageRelationDao;
	}

	public void setPageRelationDao(IPageRelationDao pageRelationDao)
	{
		this.pageRelationDao = pageRelationDao;
	}

	public void setPageFeatureDao(IPageFeatureDao pageFeatureDao) {
		this.pageFeatureDao = pageFeatureDao;
	}

	public IPageFeatureDao getPageFeatureDao() {
		return pageFeatureDao;
	}

	@Override
	public long add(long pageid, long featureid,String featurename) {
		throw new RuntimeException("No Such Method");
	}
	
	@Override
	public IDto config(long fid, Account account) {
		throw new RuntimeException("No Such Method");
	}

	@Override
	public IDto config(long fid) {
		throw new RuntimeException("No Such Method");
	}

	@Override
	public String configSub(long featureid, IDto dto, Account account) {
		throw new RuntimeException("No Such Method");
	}

	@Override
	public String dynamicAction(long uid, DynamicActionDto dto) {
		throw new RuntimeException("No Such Method");
	}

	@Override
	public List<String> export(long featureid, long sitegroupid, long ownerid,ExportDto dto) {
		throw new RuntimeException("No Such Method");
	}

	public void setDynamicActionRecordDao(
			IDynamicActionRecordDao dynamicActionRecordDao) {
		this.dynamicActionRecordDao = dynamicActionRecordDao;
	}

	public IDynamicActionRecordDao getDynamicActionRecordDao() {
		return dynamicActionRecordDao;
	}
	
	@Override
	public IDto configNew(long fid, Account account,long relationid) {
		throw new RuntimeException("No Such Method");
	}
	
	@Override
	public String configSubNew(long featureid, IDto dto, Account account) {
		throw new RuntimeException("No Such Method");
	}
	
	@Override
	public IDto featureUserRecord(VisitUser visit,long fid) {
		throw new RuntimeException("No Such Method");
	}
	
}
