package com.huiyee.esite.fmgr.imp;

import java.util.List;

import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.huiyee.esite.dto.Feature118Dto;
import com.huiyee.esite.dto.Feature134Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.showdto.Show134Dto;
import com.huiyee.esite.fdao.IInteractSpreadDao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.HdCard;
import com.huiyee.esite.model.InteractSpread;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.esite.model.PageRelation;
import com.huiyee.interact.spread.model.SpreadModel;

public class Feature134ManagerImpl extends AbstractFeatureManager
{

	private IInteractSpreadDao interactSpreadDao;

	@Override
	public long add(long pageid, long featureid,String featurename)
	{
		long fid = interactSpreadDao.savePageId(pageid);
		return getPageFeatureDao().addPageFeature(pageid, featureid, fid,featurename,"N");
	}

	@Override
	public IDto config(long fid)
	{
		Show134Dto dto = new Show134Dto();
		SpreadModel spread = interactSpreadDao.findSpreadModelByFId(fid);
		if(spread != null ){
			//spread.setOptions(interactSpreadDao.findOptionsBySpreadid(spread.getId()));			
		}
		dto.setSpread(spread);
		dto.setFid(fid);
		return dto;
	}

	@Override
	public IDto config(long fid, Account account)
	{
		Feature134Dto dto = new Feature134Dto();
		InteractSpread is = interactSpreadDao.findInteractSpread(fid);
		dto.setFid(fid);
		dto.setList(interactSpreadDao.findInteractSpreadList(account.getOwner().getId()));
		dto.setSpreadid(is.getSpreadid());
		return dto;
	}

	@Override
	public String configSub(long featureid, IDto dto, Account account)
	{
		Feature134Dto d = (Feature134Dto) dto;
		long spreadid = d.getSpreadid();
		long fid = d.getFid();
		if (spreadid != 0)
		{
			interactSpreadDao.updateSpreadid(spreadid, fid);
		}
		return "Y";
	}

	public void setInteractSpreadDao(IInteractSpreadDao interactSpreadDao)
	{
		this.interactSpreadDao = interactSpreadDao;
	}

	 @Override
	public IDto configNew(long fid,Account account,long relationid) {
        return config(fid, account);
	}

	@Override
	public String configSubNew(long featureid, IDto dto, Account account) {
		Feature134Dto d = (Feature134Dto) dto;
		configSub(featureid, dto, account);
		HdCard hc = new HdCard();
		hc.setFid(d.getFid());
		hc.setId(d.getSpreadid());
		hc.setFeatureid(featureid);
		Gson gson = new Gson();
		String str1 = gson.toJson(hc);
		PageBlockRelation pbr = interactSpreadDao.findPageBlockRelationByRelationid(d.getRelationid());
		String str = pbr.getJson();
		JSONObject jo = JSONObject.fromObject(str);
		jo.element("obj", str1);
		interactSpreadDao.updatePageBlockRelationByRelationid(d.getRelationid(),jo.toString());
		return "Y";
	}
}
