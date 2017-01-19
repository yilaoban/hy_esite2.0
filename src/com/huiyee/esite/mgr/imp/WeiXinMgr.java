
package com.huiyee.esite.mgr.imp;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import com.huiyee.esite.dao.IWeiXinDao;
import com.huiyee.esite.mgr.IWeiXinMgr;
import com.huiyee.esite.model.WeiXinPage;
import com.huiyee.esite.model.WxOA;
import com.huiyee.esite.model.WxUser;
import com.huiyee.esite.util.CacheUtil;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.weixin.model.WxMp;
import com.huiyee.weixin.model.WxPageShow;

public class WeiXinMgr implements IWeiXinMgr
{

	private IWeiXinDao weiXinDao;

	public void setWeiXinDao(IWeiXinDao weiXinDao)
	{
		this.weiXinDao = weiXinDao;
	}

	@Override
	public WxUser addWXUser(WxUser wp)
	{
		WxUser wu = weiXinDao.findWxUserByOpenid(wp.getOpenid());
		if (wu != null)
		{
			if (StringUtils.isNotBlank(wp.getNickname()))
			{
				wp.setId(wu.getId());
				wp.setUpdatetime(new Date());
				weiXinDao.updateWXUser(wu.getId(), wp);
				return wp;
			} else
			{
				return wu;
			}
		} else
		{
			if (StringUtils.isNotBlank(wp.getNickname()))
			{
				wp.setUpdatetime(new Date());
			}
			weiXinDao.addWXUser(wp);
			long wxuid = weiXinDao.findWXUserId(wp.getOpenid());
			wp.setId(wxuid);
			return wp;
		}

	}

	// @Override
	// public long savePublic2qq(long pageid,long ownerid, String name, String
	// appid,
	// String appsecret, String pic,String infoed) {
	// long result=0;
	// long aid = weiXinDao.findWxAppid(appid, ownerid);
	// if(aid>0){
	// WeiXinPage wxpage=weiXinDao.findWxPageShowid(aid);
	// result=weiXinDao.updateWxApp(name, appid, appsecret, aid);
	// result=weiXinDao.updateWxPageShow(wxpage.getId(), pic, infoed,pageid);
	// weiXinDao.updatePagesubWx(wxpage.getPageid(),"N");
	// weiXinDao.updatePageAddressStatus(wxpage.getPageid());
	// }
	// if(aid == 0){
	// aid = weiXinDao.saveWxApp(ownerid,name, appid, appsecret);
	// result = weiXinDao.saveWxPageShow(pageid, aid,pic,infoed);
	// }
	// weiXinDao.updatePagesubWx(pageid,"Y");
	// return result;
	// }

	@Override
	public String findPagePic(long wxapageid)
	{
		return weiXinDao.findPagePic(wxapageid);
	}

	@Override
	public int findWeixinCount(long pageid)
	{
		return weiXinDao.findWeixinCount(pageid);
	}

	@Override
	public List<WeiXinPage> findWeiXinPageList(long pageid)
	{
		return weiXinDao.findWeiXinPageList(pageid);
	}

	@Override
	public WxUser findWxUserByOpenid(String openid)
	{
		return weiXinDao.findWxUserByOpenid(openid);
	}

	@Override
	public WxUser getWxUserById(long id)
	{
		return weiXinDao.getWxUserById(id);// 1
	}

	@Override
	public WxPageShow findWxPageShowBySitegroupId(long sgid)
	{
		return weiXinDao.findWxPageShowBySitegroupId(sgid);
	}

	@Override
	public WeiXinPage findWXPageByPageid(long pageid, long owner)
	{
		WeiXinPage wp = null;
		if (pageid==0)
		{
			
			wp = new WeiXinPage();
			wp.setOwnerid(owner);
			return wp;
		}
		wp = weiXinDao.findWXPageByPP(pageid);
		if (wp == null)
		{
			wp = weiXinDao.findWXPageByPS(pageid);
		}
		if (wp == null)
		{
			wp = weiXinDao.findWXPageByPageid(pageid);
		}
		// if(wp==null)
		// {
		// wp=weiXinDao.findWXPageBySitegroup(pageid);
		// }
		// 如果还没有，可以搜索这个站点下面其他的的快照
		if (wp == null)
		{
			wp = new WeiXinPage();
			wp.setPageid(pageid);
			wp.setOwnerid(owner);
		}
		return wp;
	}

	@Override
	public WeiXinPage findWXPage(long id)
	{
		return weiXinDao.findWXPage(id);
		
	}

	@Override
	public WxPageShow findWxPageShowBySiteId(long siteid)
	{
		return weiXinDao.findWxPageShowBySiteId(siteid);
	}

	@Override
	public WxMp updateFindWxMp(long owner, long id)
	{
		WxMp wm=null;
		if (id > 0)
		{
			wm= CacheUtil.wxmps.get(id);
			if(wm!=null&&wm.isWork()&&!HyConfig.isRun())
			{
				return wm;
			}
			wm = weiXinDao.findWxMpById(id);
			if (wm != null)
			{
				if (StringUtils.isBlank(wm.getAppsecret()))
				{
					WxOA wa = weiXinDao.findWxOA(wm.getId());// 原本的没有access_token，查找授权的表有没有
					wm.setAu_access_token(wa.getAccess_token());
					wm.setAu_expires_in(wa.getExpires_in());
					wm.setAu_refresh_token(wa.getRefresh_token());
					wm.setThird_appid(wa.getThird_appid());
					wm.setThird_access_token(wa.getThird_access_token());
					wm.setThird_expires_in(wa.getThird_expires_in());
				} 
				CacheUtil.wxmps.put(id, wm);
			}
		}
		if (wm == null || !wm.isWork())
		{
			wm= CacheUtil.wxmpso.get(owner);
			if(wm!=null&&wm.isWork()&&!HyConfig.isRun())
			{
				return wm;
			}
			wm = weiXinDao.findWxMpByOwner(owner);
			if (wm != null)
			{
				if (StringUtils.isBlank(wm.getAppsecret()))
				{
					WxOA wa = weiXinDao.findWxOA(wm.getId());// 原本的没有access_token，查找授权的表有没有
					wm.setAu_access_token(wa.getAccess_token());
					wm.setAu_expires_in(wa.getExpires_in());
					wm.setAu_refresh_token(wa.getRefresh_token());
					wm.setThird_appid(wa.getThird_appid());
					wm.setThird_access_token(wa.getThird_access_token());
					wm.setThird_expires_in(wa.getThird_expires_in());
				}
				CacheUtil.wxmpso.put(owner, wm);
			}
		}
		//非认证的服务号，也用adminowner提供的公众号
		if (wm == null || !wm.isWork() || wm.getVerify_type_info() == -1 || wm.getService_type_info() != 2)
		{
			wm= CacheUtil.wxmpso.get(HyConfig.getAdminowner());
			if(wm!=null&&wm.isWork()&&!HyConfig.isRun())
			{
				return wm;
			}
			wm = weiXinDao.findWxMpByOwner(HyConfig.getAdminowner());
			if (wm != null)
			{
				if (StringUtils.isBlank(wm.getAppsecret()))
				{
					WxOA wa = weiXinDao.findWxOA(wm.getId());// 原本的没有access_token，查找授权的表有没有
					wm.setAu_access_token(wa.getAccess_token());
					wm.setAu_expires_in(wa.getExpires_in());
					wm.setAu_refresh_token(wa.getRefresh_token());
					wm.setThird_appid(wa.getThird_appid());
					wm.setThird_access_token(wa.getThird_access_token());
					wm.setThird_expires_in(wa.getThird_expires_in());
				} 
				CacheUtil.wxmpso.put(HyConfig.getAdminowner(), wm);
			}
		}
		return wm;
	}

}
