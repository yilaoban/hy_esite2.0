
package com.huiyee.esite.mgr.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dao.IContentCategoryDao;
import com.huiyee.esite.dao.IContentProductDao;
import com.huiyee.esite.dao.IEbproductDao;
import com.huiyee.esite.dao.IHyUserDao;
import com.huiyee.esite.dao.ISinaUserDao;
import com.huiyee.esite.dao.ISiteDao;
import com.huiyee.esite.dao.IWxUserDao;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.MarketingEbDto;
import com.huiyee.esite.dto.MarketingOrderDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.mgr.IMarketingEbMgr;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.CategoryTree;
import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.model.MarketingOrder;
import com.huiyee.esite.model.OrderGoods;
import com.huiyee.esite.model.MarketingSet;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.ProductCode;
import com.huiyee.esite.model.SinaUser;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.WxUser;
import com.huiyee.esite.util.DateUtil;

public class MarketingEbMgrImpl implements IMarketingEbMgr
{

	private IEbproductDao ebproductDao;// 电商产品Dao
	private IContentProductDao contentProductDao;
	private IContentCategoryDao contentCategoryDao;
	private IHyUserDao hyUserDao;
	private IWxUserDao wxUserDao;
	private ISinaUserDao sinaUserDao;
	private ISiteDao siteDao;

	
	public void setSiteDao(ISiteDao siteDao)
	{
		this.siteDao = siteDao;
	}

	public void setContentProductDao(IContentProductDao contentProductDao)
	{
		this.contentProductDao = contentProductDao;
	}

	public void setWxUserDao(IWxUserDao wxUserDao)
	{
		this.wxUserDao = wxUserDao;
	}

	public void setSinaUserDao(ISinaUserDao sinaUserDao)
	{
		this.sinaUserDao = sinaUserDao;
	}

	public void setHyUserDao(IHyUserDao hyUserDao)
	{
		this.hyUserDao = hyUserDao;
	}

	public void setContentCategoryDao(IContentCategoryDao contentCategoryDao)
	{
		this.contentCategoryDao = contentCategoryDao;
	}

	public void setEbproductDao(IEbproductDao ebproductDao)
	{
		this.ebproductDao = ebproductDao;
	}

	@Override
	public IDto findProductList(long ccid, long owner, int pageId, String subtype)
	{
		MarketingEbDto dto = new MarketingEbDto();
		List<CategoryTree> tree = new ArrayList<CategoryTree>();
		if (ccid > 0)
		{
			ContentCategory cc = contentCategoryDao.findContentCategoryById(ccid);
			dto.setCurrent(cc);
			subtype = cc.getSubtype();
			tree = findTree(owner, subtype);
		} else if (ccid == 0)
		{
			tree = findTree(owner, subtype);
			if (tree.size() > 0)
			{
				ccid = tree.get(0).getId();
				ContentCategory cc = contentCategoryDao.findContentCategoryById(ccid);
				dto.setCurrent(cc);
			}
		} else if (ccid == -1)
		{
			tree = findTree(owner, subtype);
		}

		if (ccid > 0)
		{
			setOpen(tree, ccid);
			dto.setJsonTree(new Gson().toJson(tree));
			int total = ebproductDao.findListTotal(ccid, owner);
			if (total > 0)
			{
				List<ContentProduct> list = ebproductDao.findListByccid(ccid, owner, (pageId - 1) * IPageConstants.CONTENT_LIMIT, IPageConstants.CONTENT_LIMIT);
				dto.setList(list);
				dto.setPager(new Pager(pageId, total, IPageConstants.CONTENT_LIMIT));
			}
		} else
		{
			dto.setJsonTree(new Gson().toJson(tree));
			ContentCategory c = new ContentCategory();
			c.setType(IPageConstants.CONTENT_PRODUCT);
			c.setSubtype(subtype);
			dto.setCurrent(c);
		}
		return dto;
	}

	private List<CategoryTree> findTree(long owner, String subtype)
	{
		List<CategoryTree> list = ebproductDao.findCategory(0, IPageConstants.CONTENT_PRODUCT, subtype, owner);
		List<CategoryTree> rs = findTreeByCatid(list, new ArrayList<CategoryTree>(), owner, subtype);
		return rs;
	}

	private void setOpen(List<CategoryTree> tree, long ccid)
	{
		long catid = ccid;
		List<Long> used = new ArrayList<Long>();
		used.add(catid);
		long id = 0;
		while ((id = contentCategoryDao.findFatherCategoryByCcid(catid)) > 0)
		{
			used.add(id);
			catid = id;
		}

		for (CategoryTree categoryTree : tree)
		{
			if (used.contains(categoryTree.getId()))
			{
				categoryTree.setOpen(true);
			}
			if (ccid == categoryTree.getId())
			{
				Map<String, String> map = new HashMap<String, String>();
				map.put("background", "#FFE6B0");
				categoryTree.setFont(map);
			}
		}

	}

	private List<CategoryTree> findTreeByCatid(List<CategoryTree> list, ArrayList<CategoryTree> arrayList, long ownerid, String subtype)
	{
		arrayList.addAll(list);
		for (CategoryTree categoryTree : list)
		{
			List<CategoryTree> rs = ebproductDao.findCategory(categoryTree.getId(), IPageConstants.CONTENT_PRODUCT, subtype, ownerid);
			if (rs.size() > 0)
			{
				findTreeByCatid(rs, arrayList, ownerid, subtype);
			} else
			{
				continue;
			}
		}
		return arrayList;
	}

	@Override
	public IDto findProductCodeList(long pid, int pageId, String code, String status)
	{
		MarketingEbDto dto = new MarketingEbDto();
		int total = ebproductDao.findCodeTotal(pid, code, status);
		if (total > 0)
		{
			List<ProductCode> list = ebproductDao.findCodeList(pid, code, status, (pageId - 1) * 20, 20);
			dto.setCodeList(list);
			dto.setPager(new Pager(pageId, total, 20));
		}
		return dto;
	}

	@Override
	public int saveProductCode(long pid, List<String> insertList)
	{
		return ebproductDao.saveProductCode(pid, insertList);
	}

	@Override
	public IDto findOrderList(int pageId, Account account, JSONObject sift, String subtype, Date startTime, Date endTime)
	{
		MarketingOrderDto dto = new MarketingOrderDto();
		int total = ebproductDao.findOrderTotal(account.getOwner().getId(), sift, getOrderSubtype(subtype), startTime, endTime);
		if (total > 0)
		{
			dto.setPager(new Pager(pageId, total, IPageConstants.ORDER_LIMIT));
			//只搜实物类的订单
			List<MarketingOrder> orders = ebproductDao.findOrderList(account.getOwner().getId(), sift, getOrderSubtype(subtype), (pageId - 1) * IPageConstants.ORDER_LIMIT,
					IPageConstants.ORDER_LIMIT, startTime, endTime);
			if (orders.size() > 0)
			{
				
				for (MarketingOrder order : orders)
				{
					HyUser hyuser = new HyUser();
					WxUser wxuser = new WxUser();
					SinaUser sinauser = new SinaUser();
					hyuser = hyUserDao.findHyUserById(order.getHyuid());
					if (hyuser != null)
					{
						if (hyuser.getWxuid() != 0)
						{
							wxuser = wxUserDao.findWxUserByid(hyuser.getWxuid());
						}
						if (hyuser.getWbuid() != 0)
						{
							sinauser = sinaUserDao.findSinaUserByid(hyuser.getWbuid());
						}
						//购买者姓名优先级     收货姓名>>注册姓名>>微信昵称>>新浪昵称
						if (order.getAddress() != null && StringUtils.isNotEmpty(order.getAddress().getName()))
						{
							order.setNameTag(4);
						} else if (StringUtils.isNotEmpty(hyuser.getUsername()))
						{
							order.setNameTag(1);
						} else if (wxuser != null && StringUtils.isNotEmpty(wxuser.getNickname()))
						{
							order.setNameTag(2);
						} else if (sinauser != null && StringUtils.isNotEmpty(sinauser.getNickname()))
						{
							order.setNameTag(3);
						}

					}
					List<OrderGoods> list = ebproductDao.findOrderGoodsByOrid(order.getId());
					//将关联的卡券类型的订单商品也搜出来
					List<OrderGoods> kqGoods = ebproductDao.findOrderGoodsByfoid(order.getId());
					for (OrderGoods orderGoods : list)
					{
						long pcid = 0;
						if ((pcid = orderGoods.getPcid()) > 0)
						{
							orderGoods.setPcode(ebproductDao.findCodeById(pcid));
						}
					}
					for (OrderGoods orderGoods : kqGoods)
					{
						long pcid = 0;
						if ((pcid = orderGoods.getPcid()) > 0)
						{
							orderGoods.setPcode(ebproductDao.findCodeById(pcid));
						}
					}
					order.setKqGoods(kqGoods);
					order.setOrderGoods(list);
					order.setHyuser(hyuser);
					order.setWxuser(wxuser);
					order.setSinauser(sinauser);
					
					
					
					
				}
			}
			dto.setList(orders);

		}
		return dto;
	}

	private int getOrderSubtype(String subtype)
	{
		if ("W".equalsIgnoreCase(subtype))
		{
			return 1;
		}
		return 0;
	}

	@Override
	public int updateOrderExpress(long orderid, String express)
	{
		return ebproductDao.updateOrderExpress(orderid, express);
	}

	@Override
	public MarketingSet findSetting(Account account, String subtype)
	{
		long owner = account.getOwner().getId();
		if ("W".equals(subtype))
		{
			return ebproductDao.findwdsSetting(owner);
		} else if ("J".equals(subtype))
		{
			return ebproductDao.findjifenSetting(owner);
		}
		return null;
	}

	@Override
	public int updateHomepage(Account account, long sgid, String subtype)
	{
		List<Site> list=siteDao.findSiteBySiteGroupId(sgid);
		MarketingSet marketingSet=new MarketingSet();
		for (Site site : list)
		{
			List<Page> pages = siteDao.findPageListBySiteId(site.getId());
			for (Page page : pages)
			{
				if ("WSD".equals(page.getApptype())) {
					marketingSet.setDzpage(page.getId());
				} else if ("WSH".equals(page.getApptype())) {
					marketingSet.setHomepage(page.getId());
				} else if ("WSU".equals(page.getApptype())) {
					marketingSet.setUserpage(page.getId());
				} else if ("KYZ".equals(page.getApptype())) {
					marketingSet.setYzpage(page.getId());
				} else if ("KYS".equals(page.getApptype())) {
					marketingSet.setSpage(page.getId());
				} else if ("KYF".equals(page.getApptype())) {
					marketingSet.setFpage(page.getId());
				} else if ("WSL".equals(page.getApptype())) {
					marketingSet.setLpage(page.getId());
				}
			}
		}
		
		if ("W".equals(subtype))
		{
			return ebproductDao.updateHomepage(account.getOwner().getId(), marketingSet);
		} else if ("J".equals(subtype))
		{
			return ebproductDao.updateJifenHomepage(account.getOwner().getId(), marketingSet);
		}
		return 0;
	}
	
	@Override
	public int updateJifenToPrice(Account account, int jftoprice, String subtype)
	{
		return ebproductDao.updateJifenToPrice(account.getOwner().getId(), jftoprice);
	}

	@Override
	public IDto findLevelbyOwner(long owner,long pid)
	{
		MarketingEbDto dto = new MarketingEbDto();
		dto.setUserlevelList(ebproductDao.findLevelNameByowner(owner));
		dto.setUserpriceList(ebproductDao.findpriceByowner(owner,pid));
		dto.setCproduct(ebproductDao.findListBypid(pid));
		return dto;
	}
}
