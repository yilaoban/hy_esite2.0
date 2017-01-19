
package project.caidan.mgr;

import java.util.List;

import project.caidan.dto.CdReportSift;
import project.caidan.model.CdWkqRmb;
import project.caidan.model.Task;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.WxUser;

public interface ICaiDanReportMgr
{

	public IDto findWayReport(long owner, int pageid, String starttime, String endtime);

	public IDto findCpzs(long wxuid, long wayid, int pageId, String starttime, String endtime);

	public IDto findChannelPerformance(long owner, int pageId, CdReportSift sift);

	public List<CdWkqRmb> findExport(long owner, CdReportSift sift);
	
	public WxUser findWxUserByHyuid(long hyuid);
	
	public Task findTask(long id);
	
}
