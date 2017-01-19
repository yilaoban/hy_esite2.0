package project.caidan.mgr.impl;

import org.apache.commons.lang.StringUtils;

import project.caidan.dao.ICaiDanMedaiDao;
import project.caidan.mgr.ICaiDanMediaMgr;

import com.huiyee.esite.model.Account;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.interact.ad.dao.IAdMediaDao;
import com.huiyee.interact.ad.dao.IAdWayDao;
import com.huiyee.interact.ad.dao.IAdwdDao;
import com.huiyee.interact.ad.model.AdMedia;
import com.huiyee.interact.ad.model.Adwd;


public class CaiDanMediaMgrImpl implements ICaiDanMediaMgr
{
	private ICaiDanMedaiDao cdMediaDao;
	private IAdMediaDao adMediaDao;
	private IAdwdDao adwdDao;
	private IAdWayDao adWayDao;
	
	public void setAdWayDao(IAdWayDao adWayDao)
	{
		this.adWayDao = adWayDao;
	}

	public void setAdwdDao(IAdwdDao adwdDao)
	{
		this.adwdDao = adwdDao;
	}

	public void setAdMediaDao(IAdMediaDao adMediaDao)
	{
		this.adMediaDao = adMediaDao;
	}

	public void setCdMediaDao(ICaiDanMedaiDao cdMediaDao)
	{
		this.cdMediaDao = cdMediaDao;
	}

	@Override
	public long saveAdWay(Account account, String media, String qwd, String wd, long pageid, String url, String fsurl,String type,int num) throws Exception
	{
		long owner = account.getOwner().getId();
		long mediaid = 0;
		long qwdid = 0;
		long wdid = 0;
		if (StringUtils.isEmpty(media))
		{
			Exception e = new RuntimeException("-1");
			throw e;
		} else
		{
			mediaid = getMediaId(owner, media);
		}

		if (StringUtils.isEmpty(qwd))
		{
			Exception e = new RuntimeException("-2");
			throw e;
		} else
		{
			qwdid = getWdId(owner, qwd,Adwd.WD_QHO);
		}

		if (StringUtils.isEmpty(wd))
		{
			Exception e = new RuntimeException("-3");
			throw e;
		} else
		{
			wdid = getWdId(owner, wd,Adwd.WD_BBN);
		}
		if (pageid == 0)
		{
			Exception e = new RuntimeException("-4");
			throw e;
		}
		long wayid = adWayDao.saveAdWay(mediaid, wdid, qwdid, pageid, url,fsurl, owner,type,num);
		if(wayid>0 && StringUtils.isBlank(url)){
			url = HyConfig.getPageyuming(owner)+"/caidan/user/wxshowp/uucd/"+mediaid+"-hy-"+wayid+".html";
			adWayDao.updateAdWay(wayid,mediaid, wdid, qwdid, pageid, url,fsurl, owner,type,num);
		}
		return wayid;
	
	}
	
	private long getMediaId(long owner, String media)
	{
		AdMedia adm = adMediaDao.findMediaByName(media, owner);
		if (adm != null)
		{
			return adm.getId();
		} else
		{
			return adMediaDao.saveMedia(media, owner);
		}
	}
	
	private long getWdId(long owner, String name, String type)
	{
		Adwd wd = adwdDao.findWdByName(owner, name, type);
		if (wd != null)
		{
			return wd.getId();
		} else
		{
			return adwdDao.saveWd(name, owner, type);
		}
	}

	@Override
	public long findEWMPageidByType(String type)
	{
		return cdMediaDao.findEWMPageidByType(type);
	}

	@Override
	public int updateAdWay(Account account, long wayid, String media, String qwd, String wd, long pageid, String url, String fsurl,String type,int num) throws Exception
	{
		long owner = account.getOwner().getId();
		long mediaid = getMediaId(owner, media);
		long qwdid = getWdId(owner, qwd,Adwd.WD_QHO);
		long wdid = getWdId(owner, wd,Adwd.WD_BBN);
		if (pageid == 0)
		{
			Exception e = new RuntimeException("-4");
			throw e;
		}
		return adWayDao.updateAdWay(wayid,mediaid, wdid, qwdid, pageid, url,fsurl, owner,type,num);
	}
	
	
}
