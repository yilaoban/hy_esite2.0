package com.huiyee.interact.xc.dao;

import java.util.List;

import com.huiyee.interact.xc.model.InviteConfig;
import com.huiyee.interact.xc.model.Xc;
import com.huiyee.interact.xc.model.XcRecord;

public interface IXcDao {

	public Xc getXcById(long xcid);

	public void saveRecords(List<XcRecord> records);

	public List<Long> getUids(long xcid, String atype);

	public int getYCount(long xcid, long uid, int utype);

	public void updateLConfig(long xcid);

	public List<XcRecord> getLastResult(long xcid, int startnum);

	public int updateAcceptInvite(long uid, int type, long xcid);

	public void updateSdAcceptInvite(long uid, int type, long xcid);

	/**
	 * 微现场的成功邀请人数
	 * @param xcid
	 * @return
	 */
	public int findInviteNum(long xcid);

	/**
	 * 微现场的签到人数
	 * @param xcid
	 * @return
	 */
	public int findCheckedNum(long xcid);

	public int updateInviteConfig(long xcid, String needInvite, InviteConfig inviteConfig);

}
