package com.huiyee.esite.mgr.imp;

import java.util.List;

import com.huiyee.esite.dao.IPageDao;
import com.huiyee.esite.dao.IWeiKaQuanDao;
import com.huiyee.esite.dao.IWeiXinDao;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.dto.WeiKaQuanDto;
import com.huiyee.esite.mgr.IWeiKaQuanMgr;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.OrderGoods;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.PayApt;
import com.huiyee.esite.model.WxUser;
import com.huiyee.interact.setting.dto.CashierDto;
import com.huiyee.weixin.model.Wkq;

public class WeiKaQuanMgrImpl implements IWeiKaQuanMgr {

	private IWeiKaQuanDao wkqDao;
	private IWeiXinDao weiXinDao;
	private IPageDao pageDao;

	public void setPageDao(IPageDao pageDao) {
		this.pageDao = pageDao;
	}

	public void setWeiXinDao(IWeiXinDao weiXinDao) {
		this.weiXinDao = weiXinDao;
	}

	public void setWkqDao(IWeiKaQuanDao wkqDao) {
		this.wkqDao = wkqDao;
	}

	@Override
	public int saveWkqShopOwner(long wxuid, long owner) {
		return wkqDao.saveWkqShopOwner(wxuid, owner);
	}

	@Override
	public IDto findContentProductList(long owner, int pageId) {
		WeiKaQuanDto dto = new WeiKaQuanDto();
		int total = wkqDao.findTotalContentProduct(owner);
		int rows = 10;
		int start = (pageId - 1) * 10;
		if (total > 0) {
			List<ContentProduct> productList = wkqDao.findContentProductList(owner, start, rows);
			dto.setProductList(productList);
		}
		Pager pager = new Pager(pageId, total, rows);
		dto.setPager(pager);
		return dto;
	}

	@Override
	public IDto findPayOrderListByProductid(long productid, int pageId) {
		WeiKaQuanDto dto = new WeiKaQuanDto();
		int total = wkqDao.findTotalPayOrderListByProductid(productid);
		int rows = 10;
		int start = (pageId - 1) * 10;
		if (total > 0) {
			List<OrderGoods> orderGoodsList = wkqDao.findPayOrderListByProductid(productid, start, rows);
			dto.setOrderGoodsList(orderGoodsList);
		}
		Pager pager = new Pager(pageId, total, rows);
		dto.setPager(pager);
		return dto;
	}

	@Override
	public IDto findPayOrderGoods(long id) {
		CashierDto dto = new CashierDto();
		OrderGoods og = wkqDao.findPayOrderGoods(id);
		if (og != null) {
			dto.setOg(og);

			PayApt pa = og.getPayApt();
			if (pa != null) {
				WxUser wxUser = weiXinDao.getWxUserById(pa.getWxuid());
				dto.setWxUser(wxUser);
			}
		}
		return dto;
	}

	@Override
	public int saveWKQ(long pageid, long owner) {
		return wkqDao.saveWKQ(pageid, owner);
	}

	@Override
	public IDto findWKQShopOwnerByOwner(long owner, int pageId) {
		WeiKaQuanDto dto = new WeiKaQuanDto();
		int total = wkqDao.findTotalWKQShopOwnerByOwner(owner);
		int rows = 10;
		int start = (pageId - 1) * 10;
		if (total > 0) {
			List<WxUser> userList = wkqDao.findWKQShopOwnerByOwner(owner, start, rows);
			dto.setUserList(userList);
		}
		Pager pager = new Pager(pageId, total, rows);
		dto.setPager(pager);
		return dto;
	}

	@Override
	public int delShopOwner(long wxuid, long owner) {
		return wkqDao.delShopOwner(wxuid, owner);
	}

	@Override
	public Page findWKQByOwner(long owner) {
		long yzpage = 0;
		Wkq wkq = wkqDao.findWKQByOwner(owner);
		if (wkq != null) {
			yzpage = wkq.getYzpage();
		}
		return pageDao.findPageById(yzpage);
	}

}
