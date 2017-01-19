
package project.caidan.mgr.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import project.caidan.dao.ICdWayDao;
import project.caidan.dao.ICouponDao;
import project.caidan.dto.CouponDto;
import project.caidan.mgr.ICouponMgr;
import project.caidan.model.CDWay;
import project.caidan.model.CDWayCatalog;
import project.caidan.model.CDWkqRecord;
import project.caidan.model.Coupon;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dao.IContentCategoryDao;
import com.huiyee.esite.dao.IContentProductDao;
import com.huiyee.esite.dao.IHyUserDao;
import com.huiyee.esite.dao.IUserTagDao;
import com.huiyee.esite.dao.IWxUserDao;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.MarketingEbDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.model.ProductCode;
import com.huiyee.esite.model.UserPkvTag;
import com.huiyee.interact.lottery.model.LotteryPrizeCode;

public class CouponMgrImpl implements ICouponMgr
{

	private ICouponDao couponDao;
	private IContentCategoryDao contentCategoryDao;
	private ICdWayDao wayDao;
	private IContentProductDao contentProductDao;
	private IUserTagDao userTagDao;
	private IWxUserDao wxUserDao;
	private IHyUserDao hyUserDao;

	@Override
	public IDto findCouponList(Account account, int pageid)
	{
		long ownerid = account.getOwner().getId();
		CouponDto dto = new CouponDto();
		int total = couponDao.findTotal(ownerid);
		if (total > 0)
		{
			List<Coupon> list = couponDao.findList(ownerid, (pageid - 1) * 20, 20);
			for (Coupon coupon : list)
			{
				coupon.setWays(wayDao.findWaysByPid(coupon.getPid()));
			}
			dto.setList(list);
			dto.setPager(new Pager(pageid, total, 20));
		}
		return dto;
	}

	@Override
	public long saveCoupon(Account account, Coupon coupon, ContentProduct product, JSONObject tags)
	{
		long catid = getOwnerCatid(account.getOwner().getId());
		product.setCatid(catid);
		long pid = contentProductDao.saveProduct(product);
		coupon.setPid(pid);
		couponDao.saveCoupon(coupon);
		if (coupon.getWayid() != null && coupon.getWayid().size() > 0)
		{
			List<JSONObject> insertWays = new ArrayList<JSONObject>();
			for (long wayid : coupon.getWayid())
			{
				if (wayid > 0)
				{
					JSONObject jo=new  JSONObject();
					jo.put("wayid", wayid);
					Map map=wayDao.findWayCatByid(wayid);
					jo.put("cname", String.valueOf(map.get("cname")));
					jo.put("ccname", String.valueOf(map.get("ccname")));
					insertWays.add(jo);
				}
			}
			wayDao.updateProductWay(pid, insertWays);
		}
		updateTags(account.getOwner().getId(), pid, tags);
		return pid;
	}

	@Override
	public List<CDWay> findWayByCatalog(Account account, long catalog)
	{
		return wayDao.findWays(account.getOwner().getId(), catalog);
	}

	private long getOwnerCatid(long owner)
	{
		ContentCategory cc = contentCategoryDao.findByOwner(owner, IPageConstants.CONTENT_NEW);
		if (cc == null)
		{
			ContentCategory simple = new ContentCategory();
			simple.setOwnerid(owner);
			simple.setName("�һ�ȯĿ¼");
			simple.setType(IPageConstants.CONTENT_NEW);
			long catid = contentCategoryDao.addCategory(owner, simple);
			return catid;
		} else
		{
			return cc.getId();
		}
	}

	@Override
	public IDto findCouponByid(long pid, Account account)
	{
		CouponDto dto = new CouponDto();
		long owner = account.getOwner().getId();
		Coupon c = couponDao.findCouponByPid(pid, owner);
		if (c != null)
		{
			c.setWays(wayDao.findWaysByPid(c.getPid()));
		}
		dto.setCoupon(c);
		List<CDWayCatalog> list = wayDao.findCatalogs(owner);
		dto.setCatalogs(list);
		return dto;
	}

	private void updateTags(long owner, long pid, JSONObject tags)
	{
		UserPkvTag t = new UserPkvTag();
		String k_s = "ctt";

		t.setK_s(k_s);
		t.setV_s(pid + "");

		if (tags.has("tag1"))
		{
			t.setTg1(updateTg(owner, tags.getJSONArray("tag1")));
		}

		if (tags.has("tag2"))
		{
			t.setTg2(updateTg(owner, tags.getJSONArray("tag2")));
		}

		if (tags.has("tag3"))
		{
			t.setTg3(updateTg(owner, tags.getJSONArray("tag3")));
		}

		if (tags.has("tag4"))
		{
			t.setTg4(updateTg(owner, tags.getJSONArray("tag4")));
		}
		if (tags.has("tag5"))
		{
			t.setTg5(updateTg(owner, tags.getJSONArray("tag5")));
		}
		userTagDao.savePkvTag(t);

	}

	private long updateTg(long owner, JSONArray ja)
	{
		if (ja != null && ja.size() > 0)
		{
			String name = ja.getString(0);
			if (name.trim().length() > 0)
			{
				long id = userTagDao.findTagByName(owner, name);
				if (id == 0)
				{
					id = userTagDao.saveTag(owner, name);
				}
				return id;
			}
		}
		return 0;
	}

	@Override
	public int updateCoupon(Account account, Coupon coupon, ContentProduct product, JSONObject tags)
	{
		long owner = account.getOwner().getId();
		long catid = getOwnerCatid(account.getOwner().getId());
		product.setCatid(catid);
		int rs = contentProductDao.updateProduct(product, owner);
		couponDao.updateCoupon(coupon, product.getId());
		if (coupon.getWayid() != null && coupon.getWayid().size() > 0)
		{
			wayDao.updateProductWayClean(product.getId());
			List<JSONObject> insertWays = new ArrayList<JSONObject>();
			for (long wayid : coupon.getWayid())
			{
				if (wayid > 0)
				{
					JSONObject jo=new  JSONObject();
					jo.put("wayid", wayid);
					Map map=wayDao.findWayCatByid(wayid);
					jo.put("cname", String.valueOf(map.get("cname")));
					jo.put("ccname", String.valueOf(map.get("ccname")));
					insertWays.add(jo);
				}
			}
			wayDao.updateProductWay(product.getId(), insertWays);
		
		}
		updateTags(account.getOwner().getId(), product.getId(), tags);
		return rs;
	}

	@Override
	public List<CDWayCatalog> findWayCatalogs(Account account)
	{
		return wayDao.findCatalogs(account.getOwner().getId());
	}

	@Override
	public List<CDWkqRecord> findWkqRecord(long wxuid, long owner, int pageId, int size)
	{
		List<CDWkqRecord> list = couponDao.findWkqRecord(wxuid, (pageId - 1) * size, size);
		for (CDWkqRecord record : list)
		{
			if (record.getPid() > 0)
			{
				record.setCoupon(couponDao.findCouponByPid(record.getPid(), owner));
			}
			if (record.getHyuid() > 0)
			{
				HyUser hyuser = hyUserDao.findHyUserById(record.getHyuid());
				if (hyuser != null)
					record.setWxUser(wxUserDao.findWxUserByid(hyuser.getWxuid()));
			}
		}
		return list;
	}

	public void setCouponDao(ICouponDao couponDao)
	{
		this.couponDao = couponDao;
	}

	public void setContentCategoryDao(IContentCategoryDao contentCategoryDao)
	{
		this.contentCategoryDao = contentCategoryDao;
	}

	public void setWayDao(ICdWayDao wayDao)
	{
		this.wayDao = wayDao;
	}

	public void setContentProductDao(IContentProductDao contentProductDao)
	{
		this.contentProductDao = contentProductDao;
	}

	public void setUserTagDao(IUserTagDao userTagDao)
	{
		this.userTagDao = userTagDao;
	}

	
	public void setWxUserDao(IWxUserDao wxUserDao)
	{
		this.wxUserDao = wxUserDao;
	}

	
	public void setHyUserDao(IHyUserDao hyUserDao)
	{
		this.hyUserDao = hyUserDao;
	}

	@Override
	public int addPrizeCode(List<ProductCode> list, long id)
	{
		int rs = contentProductDao.addCouponsCode(list,id);
		return rs;
	}

	@Override
	public IDto findProductCodeList(long pid, int pageId, String code)
	{
		CouponDto dto = new CouponDto();
		int total = contentProductDao.findCodeTotal(pid, code);
		if (total > 0)
		{
			List<ProductCode> list = contentProductDao.findCodeList(pid, code, (pageId - 1) * 20, 20);
			dto.setCodeList(list);
			dto.setPager(new Pager(pageId, total, 20));
		}
		return dto;
	}

	@Override
	public int saveCode(long pid,String code,String password,long total)
	{
		return contentProductDao.saveCode(pid,code,password,total);
	}
	
	@Override
	public int delCouponCode(long codeid, long owner)
	{
		ContentProduct product=couponDao.findProductByCodeid(codeid,owner);
		if(product!=null){
			int used=couponDao.findCodeUsed(codeid);
			if(used==0){
				return couponDao.delCouponCode(codeid);
			}
		}
		return 0;
	}
	
	@Override
	public int updateCodeClean(long productid, long owner)
	{
		ContentProduct product=contentProductDao.findProductById(productid);
		if(product!=null&&product.getOwner()==owner){
			return couponDao.updateCodeClean(productid);
		}
		return -1;
	}
}
