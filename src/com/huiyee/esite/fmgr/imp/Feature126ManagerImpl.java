package com.huiyee.esite.fmgr.imp;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.huiyee.esite.dto.Feature118Dto;
import com.huiyee.esite.dto.Feature126Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.showdto.Show126Dto;
import com.huiyee.esite.fdao.IHd126Dao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.HdCard;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.esite.model.PageRelation;
import com.huiyee.interact.auction.model.Auction;


public class Feature126ManagerImpl extends AbstractFeatureManager {
	
	private IHd126Dao hd126Dao;

	public void setHd126Dao(IHd126Dao hd126Dao){
		this.hd126Dao = hd126Dao;
	}
	
	@Override
	public long add(long pageid, long featureid,String featurename) {
		long fid = hd126Dao.saveFeatureInteractAuction(pageid);
		return getPageFeatureDao().addPageFeature(pageid, featureid, fid,featurename,"N");
	}
	
	@Override
	public IDto config(long fid, Account account) {
		Feature126Dto dto = new Feature126Dto();
		List<Auction> auctionList = hd126Dao.findInteractAuctionByOwner(account.getOwner().getId());
		dto.setAuctionList(auctionList);
		dto.setAuctionid(hd126Dao.findAuctionidByFid(fid).getId());
		return dto;
	}
	
	@Override
	public String configSub(long featureid, IDto dto, Account account) {
		String result = "N";
		Feature126Dto d = (Feature126Dto) dto;
		List<Auction> auctionList = hd126Dao.findInteractAuctionByOwner(account.getOwner().getId());
		d.setAuctionList(auctionList);
		int res = hd126Dao.updateFeatureIneractAuction(d.getAuctionid(),d.getFid());
		if(res == 1){
			result = "Y";
	     }
	    return result;
	}
	
	@Override
	public IDto config(long fid) {
		Show126Dto d = new Show126Dto();
		Auction auction=hd126Dao.findFeatureInteractAuctionById(fid);
		Date date=auction.getProendTime()!=null?auction.getProendTime():auction.getEndtime();
		if(date.before(new Date())){
			List<Long> result=hd126Dao.findAuctionWinner(auction.getId());
			if(result!=null&&result.size()==3){
				int hasSub=hd126Dao.findAuctionSub(result.get(0));
				if(hasSub==0){
//					auction.setEntityid(result.get(1));
//					auction.setType(result.get(2));
				}
			}
		}
		d.setAuction(auction);
		return d;
	}
	
	 @Override
		public IDto configNew(long fid,Account account,long relationid) {
	        return config(fid, account);
		}
		 
		 @Override
		public String configSubNew(long featureid, IDto dto, Account account) {
			Feature126Dto d = (Feature126Dto) dto;
			configSub(featureid, dto, account);
			HdCard hc = new HdCard();
			hc.setFid(d.getFid());
			hc.setId(d.getAuctionid());
			hc.setFeatureid(featureid);
			Gson gson = new Gson();
			String str1 = gson.toJson(hc);
			PageBlockRelation pbr = hd126Dao.findPageBlockRelationByRelationid(d.getRelationid());
			String str = pbr.getJson();
			JSONObject jo = JSONObject.fromObject(str);
			jo.element("obj", str1);
			hd126Dao.updatePageBlockRelationByRelationid(d.getRelationid(),jo.toString());
			return "Y";
		}
}
