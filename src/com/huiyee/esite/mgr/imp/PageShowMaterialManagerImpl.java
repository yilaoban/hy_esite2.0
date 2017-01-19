
package com.huiyee.esite.mgr.imp;

import java.util.List;

import com.huiyee.esite.dao.IContentCategoryDao;
import com.huiyee.esite.dao.IPageShowMaterialDao;
import com.huiyee.esite.dao.ISiteDao;
import com.huiyee.esite.mgr.IPageShowMaterialManager;
import com.huiyee.esite.model.Sitegroup;
import com.huiyee.esite.model.WeiXinPage;
import com.huiyee.interact.cb.model.CbActivityMatter;
import com.huiyee.weixin.model.WxPageShow;

public class PageShowMaterialManagerImpl implements IPageShowMaterialManager
{

	private IPageShowMaterialDao pageShowMaterialDao;
	private IContentCategoryDao categoryDao;
	private ISiteDao siteDao;

	public void setSiteDao(ISiteDao siteDao)
	{
		this.siteDao = siteDao;
	}

	public void setCategoryDao(IContentCategoryDao categoryDao)
	{
		this.categoryDao = categoryDao;
	}

	public void setPageShowMaterialDao(IPageShowMaterialDao pageShowMaterialDao)
	{
		this.pageShowMaterialDao = pageShowMaterialDao;
	}

	@Override
	public int findTotalWxPageShowByAid(long cbid, long aid)
	{
		return pageShowMaterialDao.findTotalWxPageShowByAid(cbid, aid);
	}

	@Override
	public List<CbActivityMatter> findWxPageShowListByAid(long cbid, long aid, int start, int size)
	{
		return pageShowMaterialDao.findWxPageShowListByAid(cbid, aid, start, size);
	}

	@Override
	public int findTotalWxPageShowByOwnerid(long ownerid,String title)
	{
		return pageShowMaterialDao.findTotalWxPageShowByOwnerid(ownerid,title);
	}

	@Override
	public List<WxPageShow> findPageShowMaterialByOwnerid(long ownerid,String title,int start, int size)
	{
		return pageShowMaterialDao.findPageShowMaterialByOwnerid(ownerid,title, start, size);
	}

	@Override
	public int updatePageShowActioned(long id, String actioned)
	{
		return pageShowMaterialDao.updatePageShowActioned(id, actioned);
	}

	@Override
	public int saveCbActivityMatter(long cbid, long aid, long pageid, long id, long ownerid, String stype)
	{
		return pageShowMaterialDao.saveCbActivityMatter(cbid, aid, pageid, id, ownerid, stype);
	}

	@Override
	public int updateInteractCbActivityMatterById(long amid)
	{
		return pageShowMaterialDao.updateInteractCbActivityMatterById(amid);
	}

	@Override
	public int saveNewsMatter(long cbid, long aid, long newsid, long ownerid)
	{
		long pageid = categoryDao.findPageidByNewsid(newsid, ownerid);
		if (pageid > 0)
		{
			Sitegroup sg = siteDao.findSitegroupByPageid(pageid);
			String kv = "cn-hy-" + newsid;
			return pageShowMaterialDao.saveNewsMatter(cbid, aid, pageid, ownerid, sg.getStype(), kv);
		}
		return 0;
	}
}
