package com.huiyee.interact.xc.mgr;

import java.util.List;

import com.huiyee.interact.cs.dto.IDto;
import com.huiyee.interact.xc.dto.XcSiftDto;
import com.huiyee.interact.xc.model.HdEntity;
import com.huiyee.interact.xc.model.Xc;
import com.huiyee.interact.xc.model.XcExport;
import com.huiyee.interact.xc.model.XcInviteRecord;
import com.huiyee.interact.xc.model.XcSendRecord;

public interface IXcLotteryMgr
{
	public IDto findXcList(long ownerid, int pageId);
	
	public IDto findSiteList(long ownerid);
	
	public long saveXcLottery(Xc xc, long ownerid);
	
	public int updateXcName(Xc xc);
	
	public int updateBigScreenSite(long siteid,long id);
	
	public long saveXcSite(long xcid,long siteid,String type);
	
	public int updateXcLottery(long id,String lotteryconfig);
	
	public int updateXcLottery(long id,Xc xc);
	
	public Xc findXcById(long id);
	
	public int delXcLottery(long id);
	
	public IDto findInviteRecordList(long id,int utype,String nickname, XcSiftDto siftDto);
	
	public IDto findCheckinRecordList(long id,int utype,int pageId);
	
	public IDto findCommentRecordList(long id,int utype,int pageId);
	
	public IDto findAuditList(long id,int utype,int pageId);
	
	public IDto findLotteryRecordList(long id,int utype,String nickname, String top, int pageId);
	
	public long updateCommentRecordStatus(long id,String checked);

	public int updateXcLotteryAndUpload(long id, Xc xc, List<XcSendRecord> list, String ip, String mediaDevice);

	public List<XcExport> findWinner(long id, long owner);

	public long findXcFidByPage(long l);

	public List<HdEntity> findXcEntitys(long xcid);
	
	public Xc getXcById(long xcid);

}
