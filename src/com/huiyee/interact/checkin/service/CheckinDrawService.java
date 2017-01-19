package com.huiyee.interact.checkin.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.DateUtil;
import com.huiyee.interact.checkin.dto.CheckinRs;
import com.huiyee.interact.checkin.mgr.ICheckinMgr;
import com.huiyee.interact.checkin.mgr.ICheckinRecordMgr;
import com.huiyee.interact.checkin.model.Checkin;
import com.huiyee.interact.checkin.model.CheckinRecord;

public class CheckinDrawService implements ICheckinDrawService
{
	private ICheckinMgr checkinMgr;
	private ICheckinRecordMgr checkinRecordMgr;
	private static Map<String, String> uids = new HashMap<String, String>();
	private static Map<String, String> ouids = new HashMap<String, String>();

	public static String getUids(String uid)
	{
		String str = uids.get(uid);
		if (str == null)
		{
			str = new String("");
			uids.put(uid, str);
		}
		return str;

	}

	public static String getOuids(String ouid)
	{
		String str = ouids.get(ouid);
		if (str == null)
		{
			str = new String("");
			ouids.put(ouid, str);
		}
		return str;

	}

	@Override
	public CheckinRs checkin(VisitUser vu, long pageid, String ip, String terminal, String source,long owner)
	{
		CheckinRs rs = new CheckinRs();
		if (vu == null)
		{
			rs.setHydesc("用户不存在");
			rs.setStatus(-6);
			return rs;
		}
		long hyuserid = vu.getHyUserId();
		synchronized (getOuids(owner+""))
		{
			Checkin ck = checkinMgr.findCheckRuleByOwner(owner);
			if (ck != null)
			{
				synchronized (getUids(hyuserid+""))
				{
					CheckinRecord cr = checkinRecordMgr.findLastCheckInByHyuid(hyuserid);
					if (cr == null)
					{
						checkinRecordMgr.addCheckIn(hyuserid,ck.getAddnum(),1,ip, terminal, source);
						rs.setHydesc("签到成功");
						rs.setStatus(1);
						rs.setDaynum(1);
						rs.setJf(ck.getAddnum());
					}
					else
					{
						String created=DateUtil.getDateString(cr.getCreatetime());
						if (DateUtil.getDateString(new Date()).equals(created))
						{
							rs.setHydesc("今天已经签到了");
						}
						else
						{
							if (cr.getDaynum() <= ck.getMaxday()&&DateUtil.getDateString(DateUtil.reduceDays(new Date(), 1)).equals(created))
							{
								checkinRecordMgr.addCheckIn(hyuserid,ck.getAddnum()+cr.getDaynum()*ck.getAddaddnum(),cr.getDaynum()+1,ip, terminal, source);
								rs.setHydesc("连续签到成功");
								rs.setStatus(1);
								rs.setDaynum(cr.getDaynum()+1);
								rs.setJf(ck.getAddnum()+cr.getDaynum()*ck.getAddaddnum());
							}
							else
							{
								checkinRecordMgr.addCheckIn(hyuserid,ck.getAddnum(),1,ip, terminal, source);
								rs.setHydesc("签到成功");
								rs.setStatus(1);
								rs.setDaynum(1);
								rs.setJf(ck.getAddnum());
							}
						}
					}

				}
			}
			else
			{
				rs.setHydesc("没有定义签到规则");
			}
		}
		return rs;
	}

	public void setCheckinMgr(ICheckinMgr checkinMgr)
	{
		this.checkinMgr = checkinMgr;
	}

	public void setCheckinRecordMgr(ICheckinRecordMgr checkinRecordMgr)
	{
		this.checkinRecordMgr = checkinRecordMgr;
	}

}
