
package project.caidan.mgr;

import java.util.List;

import project.caidan.model.CDWay;
import project.caidan.model.CDWayCatalog;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.Account;

public interface IWayMgr
{

	public IDto findCatalogs(Account account, int pageId);

	public int saveCatalog(CDWayCatalog catalog, Account account);

	public CDWayCatalog findCatalogByid(long caid, Account account);

	public int updateCatalog(CDWayCatalog catalog, Account account);

	public int deleteCatalog(long caid, Account account);

	public IDto findWays(Account account, long caid, int pageId);

	public List<Account> findAccountForWay(long owner);

	public int saveWay(CDWay way, Account account);

	public CDWay findWayByid(long wayid, Account account);

	public int updateWay(CDWay way, Account account);

	public int deleteWay(long wayid, Account account);

}
