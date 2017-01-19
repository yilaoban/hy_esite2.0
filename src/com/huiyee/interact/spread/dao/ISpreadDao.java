package com.huiyee.interact.spread.dao;

import java.util.List;

import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.spread.dto.SpreadDto;
import com.huiyee.interact.spread.model.SpreadModel;
import com.huiyee.interact.spread.model.SpreadOption;

public interface ISpreadDao {
	
	public int findSpreadListTotal(long ownerid, long omid);
	
	public List<SpreadModel> findSpreadList(long ownerid,int start,int size, long omid);
	
	public List<Lottery> findLotteryByType(long ownerid,String type);
	
	public long saveSpreadDesign(long ownerid,SpreadDto dto, long omid);
	
	public SpreadModel findSpreadModelById(long spreadid,long ownerid);
	
	public Lottery findLotteryById(long lotteryid);
	
	public long updateSpreadDesign(long ownerid,SpreadDto dto,long spreadid);
	
	public int deleteSpread(long id);
	
	public List<SpreadOption> findOptionsBySpreadid(long spreadid);
	
	public String findTokenByPageidAndWbuid(long wbuid,long pageid);
	
	public int saveSpreadRecord(long pageid,long wbuid,long spreadid,String content,String pic,String fartherwbid,String ip,String terminal,String source,long oid,String nicknames);
	
	public int saveWxSpreadRecord(long pageid,long spreadid,String content,String ip,String terminal,String source,long oid,long wxuid,String nicknames);
	
	public int findSpreadRecordCountByWbuid(long spreadid,long wbuid);
	
	public int findSpreadRecordCountByWxuid(long spreadid,long wxuid);
	
	public SpreadOption findSpreadOptionByOid(long oid);
	
	public int updateRuletypeByLotteryid(long lotteryid);
	
	public List<SpreadOption> findSpreadOptionsBySpreadid(long spreadid);
	
	public long addSpread(long ownerid,String title);
}
