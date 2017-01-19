package com.huiyee.interact.xc.mgr;

import java.util.List;

import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.xc.dto.IDto;
import com.huiyee.interact.xc.dto.XcSiftDto;
import com.huiyee.interact.xc.model.InviteConfig;
import com.huiyee.interact.xc.model.Xc;
import com.huiyee.interact.xc.model.XcInfo;
import com.huiyee.interact.xc.model.XcRecord;
import com.huiyee.interact.xc.model.XcSendRecord;

public interface IXcMgr {

	public Xc getXcById(long xcid);
	
	public Xc getXcByIdMc(long xcid);

	public void saveRecords(List<XcRecord> records);

	public List<Long> getUids(long xcid, String atype);

	public int getYRecord(String key);

	public void updateLConfig(long xcid);

	public List<XcRecord> getLastResult(long xcid, int startnum);

	public IDto findSendDetail(long id, String username, int pageId, XcSiftDto siftDto);

	public String addSendRecordSub(long id, List<XcSendRecord> list, String content, String ip, String terminal);

	public int updateTeyueCheck(VisitUser vu, int hypage, long xcid, String userAgent, String ip,Xc xc);

	public int updateNickname(long recordId, String nickname, long owner);

	public int deleteSdRecord(long recordId, long owner);

	public long saveSimpleHd(int interactid, Account account, long xcid, long groupid);

	public IDto findAppendHd();

	public int updateAcceptInvite(VisitUser vu, long xcid);

	public int updatehdstart(int interactid, long hdid, long ownerid);

	public int deleteXchd(int interactid, Account account, long xcid, long hdid);

	public XcInfo findXcInfoByXcid(long xcid);

	public Xc findXcById(long xcid);

	public void saveSdRecord(long xcid, List<XcSendRecord> list, String ip, String terminal);

	public int updateInviteConfig(long xcid, String needInvite, InviteConfig inviteConfig);
}
