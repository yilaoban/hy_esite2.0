
package project.caidan.mgr.impl;

import project.caidan.dao.IKqDao;
import project.caidan.dto.DailiCpz;
import project.caidan.dto.ProductRMB;
import project.caidan.mgr.IKqMgr;

import com.huiyee.esite.mgr.imp.AbstractMgr;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.weixin.dao.IWxBuyProductDao;
import com.huiyee.weixin.dto.WkqCmp;
import com.huiyee.weixin.mgr.IWxBuyProductMgr;

public class KqMgr extends AbstractMgr implements IKqMgr
{
	private IKqDao kqDao;
	private IWxBuyProductDao wxBuyProductDao;
	private IWxBuyProductMgr wxBuyProductMgr;
	
	
	public void setWxBuyProductMgr(IWxBuyProductMgr wxBuyProductMgr)
	{
		this.wxBuyProductMgr = wxBuyProductMgr;
	}

	public void setWxBuyProductDao(IWxBuyProductDao wxBuyProductDao)
	{
		this.wxBuyProductDao = wxBuyProductDao;
	}

	public void setKqDao(IKqDao kqDao)
	{
		this.kqDao = kqDao;
	}

	@Override
	public DailiCpz findChannelByWxuid(long wxuid)
	{
		return kqDao.findChannelByWxuid(wxuid);
	}

	@Override
	public int findProductChannel(long pid, long clid)
	{
		return kqDao.findProductChannel(pid, clid);
	}

	@Override
	public void updateRMB(VisitUser vu,DailiCpz dc,long orderid,long pogid,String productname,ProductRMB pr, String uuid, String onameurl,int ptype,long owner)
	{
		wxBuyProductMgr.updateWkqYzStatus(vu.getWxuid(),uuid, pogid, orderid, "Y", onameurl, ptype, owner);
		String nickname="";
		if(vu.getWxUser()!=null&&vu.getWxUser().getNickname()!=null)
		{
			nickname=vu.getWxUser().getNickname();
		}
		int total=kqDao.findRMB(dc.getWxuid());
		if(pr.getRmb()>0){
			WkqCmp wc=wxBuyProductDao.findWkqCmp(orderid);
			kqDao.updateRMB(dc.getWxuid(), pr.getRmb());
			kqDao.updateRMBRecord(dc.getWxuid(), pr.getRmb(), productname, total+pr.getRmb(), wc.getNickname());
		}
		if(pr.getZdrmb()>0){
			kqDao.updateRMB(dc.getWxuid(), pr.getZdrmb());
			kqDao.updateRMBRecord(dc.getWxuid(), pr.getZdrmb(), "返还", total+pr.getRmb()+pr.getZdrmb(),"系统");
		}
		if(pr.getXjrmb()>0){
			int fatotal=kqDao.findRMB(dc.getFawxuid());
			kqDao.updateRMB(dc.getFawxuid(), pr.getXjrmb());
			kqDao.updateRMBRecord(dc.getFawxuid(), pr.getXjrmb(), "返还", fatotal+pr.getXjrmb(),nickname+",店铺返点");
		}
	}

	@Override
	public ProductRMB findPRMB(long pid)
	{
		return kqDao.findPRMB(pid);
	}

}
