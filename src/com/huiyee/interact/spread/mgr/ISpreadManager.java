package com.huiyee.interact.spread.mgr;

import java.util.List;

import com.huiyee.esite.dto.HdRsDto;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.spread.dto.IDto;
import com.huiyee.interact.spread.dto.SpreadDto;
import com.huiyee.interact.spread.model.SpreadModel;


public interface ISpreadManager {
	
	public int findSpreadListTotal(long ownerid, long omid);
	
	public IDto findSpreadList(long ownerid,int start,int size, long omid);
	
	public List<Lottery> findLotteryByType(long ownerid,String type);
	
	public long saveSpreadDesign(long ownerid,SpreadDto dto, long omid);
	
	public IDto findSpreadModelById(long spreadid,long ownerid);
	
	public Lottery findLotteryById(long lotteryid);
	
	public long updateSpreadDesign(long ownerid,SpreadDto dto,long spreadid);
	
	public int deleteSpread(long id);
	
	public HdRsDto saveSpreadRecord(long pageid,long spreadid,VisitUser u,String content,String pic,long wbid,String ip,String terminal,String source,long oid,String nicknames, SpreadModel spread);
	
	public SpreadModel previewSpread(long spreadid,long ownerid);
	
	public int updateRuletypeByLotteryid(long lotteryid);

	public SpreadModel findSpreadByIdAndPageId(long spreadid, long pageid);
	
	public long addSpread(long ownerid,String title);
}
