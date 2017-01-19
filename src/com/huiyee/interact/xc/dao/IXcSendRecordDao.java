package com.huiyee.interact.xc.dao;

import java.util.List;

import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.xc.dto.XcSiftDto;
import com.huiyee.interact.xc.model.XcSendRecord;

public interface IXcSendRecordDao
{

	public int findTotal(long id, String username, XcSiftDto siftDto);

	public List<XcSendRecord> findList(long id, String username, XcSiftDto siftDto, int i, int interactXcRecordLimit);

	public int findExist(long id, String username, String nickname);

	public int addSendRecordSub(List<XcSendRecord> needInsert, long id, String ip, String terminal);

	public XcSendRecord findRecordByNickname(String nickname, long xcid);

	public void updateSendRecordInvited(long id, long uid, int type, String source, int hypage);

	public void saveInviteRecord(long xcid, long uid, int type, String source, int hypage, String terminal, String ip,String cg);

	public int updateNickname(long recordId, String nickname);

	public int deleteSdRecord(long recordId);

	public XcSendRecord findRecordById(long recordId, long owner);

	public List<String> findNames(long id, long uid, int utype);
	
	public XcSendRecord getSdRecord(long xcid, long uid, int utype);

	public void updateXcClean(long xcid);

	public void updateXcCleanWithOutInvite(long xcid);

}
