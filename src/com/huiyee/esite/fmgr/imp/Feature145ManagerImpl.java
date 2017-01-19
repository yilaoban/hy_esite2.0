package com.huiyee.esite.fmgr.imp;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.huiyee.esite.dto.Feature145Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.showdto.Show145Dto;
import com.huiyee.esite.fdao.IHd145Dao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.CsFuidDraw;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.cs.model.Cs;

public class Feature145ManagerImpl extends AbstractFeatureManager {
	private IHd145Dao hd145Dao;

	public void setHd145Dao(IHd145Dao hd145Dao)
	{
		this.hd145Dao = hd145Dao;
	}

	@Override
	public long add(long pageid, long featureid, String featurename) {
		long fid = hd145Dao.saveFeatureInteract(pageid);
		return getPageFeatureDao().addPageFeature(pageid, featureid, fid,featurename,"Y");
	}

	@Override
	public IDto config(long fid, Account account)
	{
		Feature145Dto dto = new Feature145Dto();
		dto.setCsid(hd145Dao.findCsidByFid(fid));
	    List<Cs> csList= hd145Dao.findCsByOwner(account.getOwner().getId());
        //dto.setCsList(csList);
        return dto;
	}

	@Override
	public String configSub(long featureid, IDto dto, Account account)
	{
		String result = "N";
		Feature145Dto d = (Feature145Dto) dto;
		List<Cs> csList= hd145Dao.findCsByOwner(account.getOwner().getId());
		//d.setCsList(csList);
        int res = hd145Dao.updateFeatureIneractCs(d.getCsid(),d.getFid());
        if(res == 1){
        	result = "Y";
        }
        return result;
	}
	
	
	@Override
	public IDto config(long fid)
	{
		Show145Dto dto = new Show145Dto();
		Cs cs = hd145Dao.findCsByFid(fid);
		if(cs != null){
			JSONArray ja = JSONArray.fromObject(cs.getJcontent());
			dto.setJa(ja);
		}
		dto.setCs(cs);
		return dto;
	}

	@Override
	public IDto featureUserRecord(VisitUser visit, long fid) {
		Show145Dto dto = new Show145Dto();
		if(visit != null){
			try{
				Long source = Long.parseLong(visit.getSource());
				dto.setSource(source);
				CsFuidDraw csFuidDraw = hd145Dao.findCsFuidDrawByid(source);
				if(csFuidDraw != null){
					JSONObject jb = JSONObject.fromObject(csFuidDraw);
					dto.setJb(jb);
				}
				dto.setCsFuidDraw(csFuidDraw);
			}catch (NumberFormatException e) {
				return dto;
			}
		}
		return dto;
	}

	
}
