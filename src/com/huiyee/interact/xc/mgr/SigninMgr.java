
package com.huiyee.interact.xc.mgr;

import java.util.List;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.dao.IGzEventDao;
import com.huiyee.esite.model.SinaUser;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.model.WxGzEvent;
import com.huiyee.esite.model.WxUser;
import com.huiyee.interact.xc.dao.ISigninDao;
import com.huiyee.interact.xc.dao.IXcDao;
import com.huiyee.interact.xc.model.Xc;
import com.huiyee.interact.xc.model.XcCheckinRecord;
import com.huiyee.interact.xc.model.XcSendRecord;

public class SigninMgr implements ISigninMgr
{

	private ISigninDao signinDao;
	private IGzEventDao gzEventDao;
	private IXcDao xcDao;

	public void setXcDao(IXcDao xcDao)
	{
		this.xcDao = xcDao;
	}

	public void setGzEventDao(IGzEventDao gzEventDao)
	{
		this.gzEventDao = gzEventDao;
	}

	public void setSigninDao(ISigninDao signinDao)
	{
		this.signinDao = signinDao;
	}

	@Override
	public int findUser(long xcid, long uid, int type)
	{
		return signinDao.findUser(xcid, uid, type);
	}

	@Override
	public int saveUser(long uid, int type, long pageid, String source, long xcid, String ip, String terminal)
	{
		int status = 1;// 符合条件可以签到
		if (signinDao.findUser(xcid, uid, type) == 0)
		{
			signinDao.updateInvite(uid, type, xcid);
			signinDao.updateSd(uid, type, xcid);
			signinDao.saveUser(uid, type, pageid, source, xcid, ip, terminal, status);
		}
		return 1;
	}

	@Override
	public List<XcCheckinRecord> findXcCheckinRecord(long xcid, long start, int size)
	{
		List<XcCheckinRecord> list = signinDao.findXcCheckinRecord(xcid, start, size);
		for (XcCheckinRecord r : list)
		{
			XcSendRecord sd = signinDao.findsdRecord(r.getUid(), r.getUtype(), r.getXcid());
			if (r.getUtype() == 1)
			{
				WxUser u = signinDao.findWxUser(r.getUid());
				if (u != null)
				{
					if (sd == null || sd.getUsername() == null || "".equals(sd.getUsername()))
					{
						r.setNickname(u.getNickname());
					} else
					{
						r.setNickname(sd.getUsername());
					}
					r.setHeadimgurl(u.getHeadimgurl());
				}
			}
			if (r.getUtype() == 0)
			{
				SinaUser u = signinDao.findSinaUser(r.getUid());
				if (u != null)
				{
					if (sd == null || sd.getUsername() == null || "".equals(sd.getUsername()))
					{
						r.setNickname(u.getNickname());
					} else
					{
						r.setNickname(sd.getUsername());
					}
					r.setHeadimgurl(u.getImageurl());
				}
			}
		}
		return list;
	}

	@Override
	public int findisInvite(long xcid, long uid, int type)
	{
		return signinDao.findisInvite(xcid, uid, type);
	}

	@Override
	public int updateInvite(long uid, int type, long xcid)
	{
		int result = 0;
		long total = signinDao.updateInvite(uid, type, xcid);
		if (total > 0)
		{
			result = 1;
		}
		return result;
	}

	@Override
	public int updateSd(long uid, int type, long xcid)
	{
		int result = 0;
		long total = signinDao.updateSd(uid, type, xcid);
		if (total > 0)
		{
			result = 1;
		}
		return result;
	}

	@Override
	public int findSignerTotal(long xcid)
	{
		return signinDao.findSignerTotal(xcid);
	}

	@Override
	public int saveUserFail(long uid, int type, long pageid, VisitUser vu, long xcid, String ip, String terminal, int status,long msgid)
	{
		if (signinDao.findUser(xcid, uid, type) != 0)
		{
			return 2;
		} else
		{
			signinDao.saveUser(uid, type, pageid, vu.getSource(), xcid, ip, terminal, status);
		}
		if (status == -1 && msgid>0)
		{
			long record = signinDao.findCheckinRecordId(xcid, uid, type);
			gzEventDao.addSysKeywords(vu.getWxUser().getOpenid(), msgid, record,0);
			
		}
		return 1;
	}

}
