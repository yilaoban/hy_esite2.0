package com.huiyee.esite.fmgr.imp;

import java.util.List;

import com.huiyee.esite.dto.Feature144Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.showdto.Show144Dto;
import com.huiyee.esite.fdao.IHd144Dao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.renqi.model.RenQi;

public class Feature144ManagerImpl extends AbstractFeatureManager {
	private IHd144Dao  hd144Dao;

	public void setHd144Dao(IHd144Dao hd144Dao)
	{
		this.hd144Dao = hd144Dao;
	}

	@Override
	public long add(long pageid, long featureid, String featurename) {
		long fid = hd144Dao.saveFeatureInteract(pageid);
		return getPageFeatureDao().addPageFeature(pageid, featureid, fid,featurename,"Y");
	}

	@Override
	public IDto config(long fid, Account account)
	{
		Feature144Dto dto = new Feature144Dto();
        dto.setRqid(hd144Dao.findRqidByFid(fid));
        List<RenQi> renQiList= hd144Dao.findRenQiByOwner(account.getOwner().getId());
     //   dto.setRenQiList(renQiList);
        return dto;
	}

	@Override
	public String configSub(long featureid, IDto dto, Account account)
	{
		String result = "N";
		Feature144Dto d = (Feature144Dto) dto;
        List<RenQi> renQiList= hd144Dao.findRenQiByOwner(account.getOwner().getId());
       // d.setRenQiList(renQiList);
        int res = hd144Dao.updateFeatureIneractRq(d.getRqid(),d.getFid());
        if(res == 1){
        	result = "Y";
        }
        return result;
	}
	
	@Override
	public IDto config(long fid)
	{
		Show144Dto dto = new Show144Dto();
		RenQi rq = hd144Dao.findRenQiByFId(fid);
		dto.setRq(rq);
		return dto;
	}

	@Override
	public IDto featureUserRecord(VisitUser visit,long fid) {
		Show144Dto dto = new Show144Dto();
		if(visit != null){
			try {
				Long source = Long.parseLong(visit.getSource());
				dto.setSource(source);
				int type=visit.getCd();
				dto.setVisit(visit.getUid());
					
			
				RenQi rq = hd144Dao.findRenQiByFId(fid);
				if(rq == null){
					return null;
				}
				dto.setRq(rq);
				dto.setRecord(hd144Dao.findRenQiRecord(rq.getId(),dto.getSource(),type,5));
				dto.setIsJoin(hd144Dao.findIsJoinByVisit(rq.getId(), dto.getSource(),dto.getVisit()));//访问者是否参与过
				dto.setIsSend(hd144Dao.findIsSendByVisit(rq.getId(), dto.getVisit()));//访问者是否发起过
				dto.setTotal(hd144Dao.findTotalByFuid(rq.getId(), dto.getSource()));
				
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return dto;
	}
	
}
