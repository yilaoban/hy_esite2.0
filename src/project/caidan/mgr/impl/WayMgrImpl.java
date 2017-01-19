
package project.caidan.mgr.impl;

import java.util.List;

import project.caidan.dao.IAccountTypeDao;
import project.caidan.dao.ICdWayDao;
import project.caidan.dto.WayDto;
import project.caidan.mgr.IWayMgr;
import project.caidan.model.CDAccoutType;
import project.caidan.model.CDWay;
import project.caidan.model.CDWayCatalog;

import com.huiyee.esite.dao.IAccountDao;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.model.Account;

public class WayMgrImpl implements IWayMgr
{

	private ICdWayDao wayDao;
	private IAccountTypeDao accountTypeDao;
	private IAccountDao accountDao;

	@Override
	public IDto findCatalogs(Account account, int pageId)
	{
		long owner = account.getOwner().getId();
		WayDto dto = new WayDto();
		int total = wayDao.findCatalogsTotal(owner);
		if (total > 0)
		{
			dto.setPager(new Pager(pageId, total, 20));
			List<CDWayCatalog> catalogs = wayDao.findCatalogs(owner, (pageId - 1) * 20, 20);
			dto.setCatalogs(catalogs);

		}
		return dto;
	}

	@Override
	public int saveCatalog(CDWayCatalog catalog, Account account)
	{
		catalog.setOwner(account.getOwner().getId());
		return wayDao.saveCatalog(catalog);
	}

	public void setWayDao(ICdWayDao wayDao)
	{
		this.wayDao = wayDao;
	}

	public void setAccountDao(IAccountDao accountDao)
	{
		this.accountDao = accountDao;
	}

	@Override
	public CDWayCatalog findCatalogByid(long caid, Account account)
	{
		return wayDao.findCatalogById(caid, account.getOwner().getId());
	}

	@Override
	public int updateCatalog(CDWayCatalog catalog, Account account)
	{
		return wayDao.updateCatalog(catalog, account.getOwner().getId());
	}

	@Override
	public int deleteCatalog(long caid, Account account)
	{
		return wayDao.deleteCatalog(caid, account.getOwner().getId());
	}

	@Override
	public IDto findWays(Account account, long caid, int pageId)
	{
		long owner = account.getOwner().getId();
		WayDto dto = new WayDto();
		int total = wayDao.findWaysTotal(owner, caid);
		if (total > 0)
		{
			dto.setPager(new Pager(pageId, total, 20));
			List<CDWay> ways = wayDao.findWays(owner, caid, (pageId - 1) * 20, 20);
			CDWayCatalog catalog = wayDao.findCatalogById(caid, account.getOwner().getId());
			for (CDWay cdWay : ways)
			{
				cdWay.setCatalog(catalog);
				CDAccoutType a=accountTypeDao.findByAcid(cdWay.getAcid(), owner);
				cdWay.setAccount(a);
			}
			dto.setWays(ways);

		}
		return dto;
	}

	@Override
	public List<Account> findAccountForWay(long owner)
	{
		return accountTypeDao.findAccountForWay(owner);
	}

	@Override
	public int saveWay(CDWay way, Account account)
	{
		way.setOwner(account.getOwner().getId());
		return wayDao.saveWay(way);
	}

	@Override
	public CDWay findWayByid(long wayid, Account account)
	{
		return wayDao.findWayByid(wayid, account.getOwner().getId());
	}

	@Override
	public int updateWay(CDWay way, Account account)
	{
		way.setOwner(account.getOwner().getId());
		return wayDao.updateWay(way);
	}

	@Override
	public int deleteWay(long wayid, Account account)
	{
		return wayDao.deleteWay(wayid,account.getOwner().getId());
	}

	public void setAccountTypeDao(IAccountTypeDao accountTypeDao)
	{
		this.accountTypeDao = accountTypeDao;
	}
}
