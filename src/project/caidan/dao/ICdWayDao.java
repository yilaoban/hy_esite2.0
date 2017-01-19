
package project.caidan.dao;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import project.caidan.model.CDWay;
import project.caidan.model.CDWayCatalog;
import project.caidan.model.WayReport;

public interface ICdWayDao
{

	public List<CDWayCatalog> findCatalogs(long owner);

	public List<CDWay> findWays(long owner, long catid);

	public CDWayCatalog findCatalogById(long id, long owner);

	public CDWay findWayByid(long wayid, long owner);

	public int findCatalogsTotal(long owner);

	public List<CDWayCatalog> findCatalogs(long owner, int start, int size);

	public int saveCatalog(CDWayCatalog catalog);

	public int updateCatalog(CDWayCatalog catalog, long owner);

	public int deleteCatalog(long caid, long owner);

	public int findWaysTotal(long owner, long caid);

	public List<CDWay> findWays(long owner, long caid, int i, int j);

	public int saveWay(CDWay way);

	public int updateWay(CDWay way);

	public int deleteWay(long wayid, long id);

	public List<CDWay> findWaysByPid(long pid);

	public void updateProductWay(long pid, List<JSONObject> insertWays);

	public void updateProductWayClean(long id);

	public int findWaysTotal(long owner);

	public List<WayReport> findWayReport(long owner, int start, int size);

	public Map findWayCatByid(long wayid);
}
