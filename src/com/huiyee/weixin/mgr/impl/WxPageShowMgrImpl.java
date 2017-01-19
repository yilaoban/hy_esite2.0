package com.huiyee.weixin.mgr.impl;

import java.util.List;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.model.Page;
import com.huiyee.weixin.dao.IWxPageShowDao;
import com.huiyee.weixin.mgr.IWxPageShowMgr;
import com.huiyee.weixin.model.WxPageShow;

public class WxPageShowMgrImpl implements IWxPageShowMgr {

	private IWxPageShowDao wxPageShowDao;

	public void setWxPageShowDao(IWxPageShowDao wxPageShowDao) {
		this.wxPageShowDao = wxPageShowDao;
	}

	@Override
	public WxPageShow getWxPageShow(long pageid) {
		if (pageid == 0) {
			return null;
		}
		WxPageShow wxp = wxPageShowDao.getWxPageShowByPP(pageid);
		if (wxp == null) {
			wxp = wxPageShowDao.getWxPageShowByPS(pageid);
		}
		if (wxp == null) {
			wxp = wxPageShowDao.getWxPageShow(pageid);
		}
		// if (wxp == null) {
		// wxp = wxPageShowDao.getWxPageShowBySite(pageid);
		// }
		return wxp;
	}

	@Override
	public WxPageShow saveWxPageShow(WxPageShow wps, List<Long> pids) {
		WxPageShow wp = wxPageShowDao.saveWxPageShow(wps);
		if (wp != null) {
			wxPageShowDao.deleteWxppByWxpid(wp.getId());
			for (int i = 0; i < pids.size(); i++) {
				wxPageShowDao.saveWxpp(wp.getId(), pids.get(i));
			}
		}
		return wp;
	}

	@Override
	public List<WxPageShow> findWxPageListByGroupid(long sitegroupid) {
		return wxPageShowDao.findWxPageListByGroupid(sitegroupid);
	}

	@Override
	public WxPageShow findWxPageShowById(long id) {
		return wxPageShowDao.findWxPageShowById(id);
	}

	@Override
	public List<Long> findWxPPById(long id) {
		return wxPageShowDao.findWxPPById(id);
	}

	@Override
	public int updateWxPageShow(WxPageShow wps, List<Long> pids, long siteid) {
		if (pids != null && pids.size() > 0) {
			// 网站微信设置
			if (wps.getId() > 0) {
				// 修改快照
				wxPageShowDao.deleteWxpp(wps.getId());
				wxPageShowDao.updateWxPageShow(wps);
			} else {
				// 新建快照
				wps = wxPageShowDao.saveWxPageShow(wps);
			}
			for (Long p : pids) {
				if (p > 0) {
					wxPageShowDao.saveWxpp(wps.getId(), p);
				}
			}
		} else {
			// 活动微信设置
			if (wps.getId() > 0) {
				// 修改快照
				wxPageShowDao.updateWxPageShow(wps);
			} else {
				// 新建快照
				wps = wxPageShowDao.saveWxPageShow(wps);
				wxPageShowDao.saveWxPs(wps.getId(), siteid);
			}
		}
		return wxPageShowDao.updateSitegroupCMP(wps.getSitegroupid());
	}

	@Override
	public List<Page> findPublishPageBySiteid(long siteid) {
		return wxPageShowDao.findPublishPageBySiteid(siteid);
	}

	@Override
	public List<WxPageShow> findWxPageListBySiteid(long siteid) {
		return wxPageShowDao.findWxPageListBySiteid(siteid);
	}

	@Override
	public int deleteWxPageShow(long shareid, String stype, long owner) {
		int rs = 0;
		if (IPageConstants.SITEGROUP_TYPE_EVENT.equals(stype)) {
			rs += wxPageShowDao.deleteWxps(shareid);
			rs += wxPageShowDao.deleteWps(shareid);
		} else if (IPageConstants.SITEGROUP_TYPE_WEBSITE.equals(stype) || IPageConstants.SITEGROUP_TYPE_APP.equals(stype)) {
			rs += wxPageShowDao.deleteWxpp(shareid);
			rs += wxPageShowDao.deleteWps(shareid);
		}
		return rs;
	}
}
