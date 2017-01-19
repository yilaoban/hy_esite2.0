package com.huiyee.interact.lottery.mgr;

import java.util.List;

import com.huiyee.esite.model.CrmWxHongbaoCheck;
import com.huiyee.esite.model.WxHbCheckLog;
import com.huiyee.interact.lottery.dao.IWxHongbaoDao;

public class WxHongbaoMgrImpl implements IWxHongbaoMgr{
	private IWxHongbaoDao wxHongbaoDao;
	
	public void setWxHongbaoDao(IWxHongbaoDao wxHongbaoDao)
	{
		this.wxHongbaoDao = wxHongbaoDao;
	}

	@Override
	public int saveHongbaoCheck(long lid,int total,String reason,long account,int type,String accountname,String ip,long ckid,long mpid)
	{
		if(ckid == 0){
			ckid = wxHongbaoDao.saveCrmWxHongbaoCheck(type,lid,mpid);
		}else{
			wxHongbaoDao.updateCrmWxHongbaoCheck(ckid,type,lid,mpid);
		}
		return wxHongbaoDao.saveCrmWxHongbaoCheckLog(total, ckid,account,accountname,reason,ip);
	}

	@Override
	public CrmWxHongbaoCheck findCrmWxHongbaoCheckByLid(long lid,int type)
	{
		return wxHongbaoDao.findCrmWxHongbaoCheckByLid(lid,type);
	}

	@Override
	public CrmWxHongbaoCheck findWxHongbaoCheckByLid(long lid)
	{
		return wxHongbaoDao.findWxHongbaoCheckByLid(lid);
	}

	@Override
	public void saveWxHongbaoLotterySend(long lid,long lpid, long lurid, String openid, long mpid)
	{
		wxHongbaoDao.saveWxHongbaoLotterySend(lid,lpid, lurid, openid, mpid);
	}

	@Override
	public List<WxHbCheckLog> findWxHbCheckLogListBylid(long lid, int start, int rows)
	{
		return wxHongbaoDao.findWxHbCheckLogListBylid(lid, start, rows);
	}

	@Override
	public void saveSysKeywords(long lid,long mpid,String openid, String keywords, int hbgz, long lpid, long rid)
	{
		wxHongbaoDao.saveSysKeywords(lid,mpid,openid, keywords, hbgz, lpid, rid);
	}
}
