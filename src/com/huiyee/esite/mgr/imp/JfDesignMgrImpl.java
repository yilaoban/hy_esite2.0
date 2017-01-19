
package com.huiyee.esite.mgr.imp;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.huiyee.esite.dao.IJfDesignDao;
import com.huiyee.esite.dao.ISiteDao;
import com.huiyee.esite.dto.BalancePageDto;
import com.huiyee.esite.mgr.IJfDesignMgr;
import com.huiyee.esite.model.BalanceSet;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.UserTag;
import com.huiyee.interact.checkin.model.Checkin;

public class JfDesignMgrImpl implements IJfDesignMgr
{

	private IJfDesignDao jfDesignDao;
	private ISiteDao siteDao;

	public void setSiteDao(ISiteDao siteDao)
	{
		this.siteDao = siteDao;
	}

	public void setJfDesignDao(IJfDesignDao jfDesignDao)
	{
		this.jfDesignDao = jfDesignDao;
	}

	@Override
	public int savezfjfDesign(long owner, BalanceSet balanceSet)
	{
		return jfDesignDao.savezfjfDesign(owner, balanceSet);
	}

	@Override
	public int savesqjfDesign(long owner, BalanceSet balanceSet)
	{
		return jfDesignDao.savesqjfDesign(owner, balanceSet);
	}

	@Override
	public int savezsjfDesign(long owner, BalanceSet balanceSet)
	{
		return jfDesignDao.savezsjfDesign(owner, balanceSet);
	}

	@Override
	public int saveqdjfDesign(long owner, Checkin checkin)
	{
		return jfDesignDao.saveqdjfDesign(owner, checkin);
	}

	@Override
	public BalanceSet findBalanceSetByOwner(long owner)
	{
		return jfDesignDao.findBalanceSetByOwner(owner);
	}

	@Override
	public int savexqjfDesign(long owner, BalanceSet balanceSet)
	{
		return jfDesignDao.savexqjfDesign(owner, balanceSet);
	}

	@Override
	public int saveyyjfDesign(long owner, BalanceSet balanceSet)
	{
		return jfDesignDao.saveyyjfDesign(owner, balanceSet);
	}

	@Override
	public int savepjjfDesign(long owner, BalanceSet balanceSet)
	{
		return jfDesignDao.savepjjfDesign(owner, balanceSet);
	}

	@Override
	public int saveczDesign(long owner, BalanceSet balanceSet)
	{
		return jfDesignDao.saveczDesign(owner, balanceSet);
	}

	@Override
	public int saveJfPageSet(long owner, long sitegroupid)
	{
		if(sitegroupid>0){
			List<Site> list = siteDao.findSiteBySiteGroupId(sitegroupid);
			BalancePageDto bpd = new BalancePageDto();
			bpd.setSitegroupid(sitegroupid);
			for (Site site : list)
			{
				List<Page> pages = siteDao.findPageListBySiteId(site.getId());
				for (Page page : pages)
				{
					if (!"NOR".equals(page.getApptype()))
					{
						String apptype = page.getApptype();
						if (apptype != null && apptype.trim().length() > 0)
						{
							try
							{
								long pageid = page.getId();
								StringBuilder sb = new StringBuilder(apptype.toLowerCase() + "id");
								sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
								Method method = bpd.getClass().getMethod("set" + sb.toString(), long.class);
								// 调用set方法
								method.invoke(bpd, pageid);
							} catch (Exception e)
							{
								System.out.println("error jf pageset=====apptype:"+apptype);
							}
						}
					}
				}
			}
			String json=new Gson().toJson(bpd);
			if(StringUtils.isNotEmpty(json))
				return jfDesignDao.updatePageSet(json,owner);
		}
		return 0;
	}

}
