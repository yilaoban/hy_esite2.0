package com.huiyee.interact.xc.dao;

import java.util.List;

import com.huiyee.esite.model.Site;
import com.huiyee.interact.xc.dto.XcSiftDto;
import com.huiyee.interact.xc.model.Xc;
import com.huiyee.interact.xc.model.XcCheckinRecord;
import com.huiyee.interact.xc.model.XcCjRecord;
import com.huiyee.interact.xc.model.XcCommentRecord;
import com.huiyee.interact.xc.model.XcExport;
import com.huiyee.interact.xc.model.XcInviteRecord;
import com.huiyee.interact.xc.model.XcSite;

public interface IXcLotteryDao {
	
	public int findTotalByOwnerId(long ownerid);
	
	public List<Xc> findXcList(long ownerid, int start, int size);
	
	public List<XcSite> findXcSite(long xcid);
	
	public List<Site> findSiteList(long ownerid);
	
	public long saveXcSite(long xcid, long siteid, String type);
	
	public int updateXcName(Xc xc);
	
	public int updateBigScreenSite(long id, long siteid);
	
	public long saveXcLottery(Xc xc, long ownerid);
	
	public int updateXcLottery(long id,String lotteryconfig);
	
	public Xc findXcById(long id);
	
	public int updateXcLottery(long id, Xc xc);
	
	public int delXcLottery(long id);
	
	public int findTotalInviteRecord(long id,String nickname, XcSiftDto siftDto);
	
	public List<XcInviteRecord> findInviteRecordList(long id,int start,int size, String nickname, XcSiftDto siftDto);
	
	public int findTotalCheckinRecord(long id,int utype);
	
	public List<XcCheckinRecord> findCheckinRecordList(long id,int utype,int start, int size);
	
	public int findTotalCommentRecord(long id,int utype);
	
	public List<XcCommentRecord> findCommentRecordList(long id,int utype,int start, int size);
	
	public int findTotalAudit(long id,int utype);
	
	public List<XcCommentRecord> findAuditList(long id,int utype,int start, int size);
	
	public int findTotalLotteryRecord(long id,int utype, String nickname, String top);
	
	public List<XcCjRecord> findLotteryRecordList(long id,int utype,int start, int size, String nickname, String top);
	
	public long updateCommentRecordStatus(long id,String status);

	public List<XcExport> findWinner(long id);

	public long findXcFidByPage(long pageId);
	
	public Xc getXcById(long xcid);
	

	/**
	 * 微现场需要的申请ID
	 * @param newXc
	 * @param newApt
	 */
	public void updateXcWithApt(long newXc, long newApt);

}
