package com.huiyee.interact.lottery.mgr;

import java.util.List;
import com.huiyee.interact.lottery.dto.IDto;
import com.huiyee.interact.lottery.model.LotteryWinnerDetail;

public interface ILotteryUserRecordMgr
{
    public long addLotteryCmp(String nickname,int xhjf,int jf,long hyuid,Long uid,Long lid,Long lpid,Long lpcid,Long pageid,String ip,String terminal,String source,int status,int type,int userdaynum);
   
	public IDto findRecord(long lid, long wbuid, int pageId, String type, int media);

	public IDto findRecordDetail(long lpid, String nickName, int pageId);
	
	public IDto findLotteryUserRecord(long lpid, String processstatus, int pageId);

	public List<LotteryWinnerDetail> exportWb(long lpid);

	public List<LotteryWinnerDetail> exportWx(long lpid);

	public IDto findRecord(long lid, long maxRecordid);

	public int updateLotteryClean(long lid, long id);
	
	public int findUserZjTotal(long lid,long uid,int type);

}
