
package project.caidan.mgr.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.huiyee.esite.dao.IWxUserDao;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.model.WxUser;

import project.caidan.constants.ICaiDanConstants;
import project.caidan.dao.IAccountTypeDao;
import project.caidan.dao.ICaiDanProductDao;
import project.caidan.dao.ICaiDanTeamDao;
import project.caidan.dao.ICdWayDao;
import project.caidan.dao.ITaskDao;
import project.caidan.dto.CdReportSift;
import project.caidan.dto.ReportDto;
import project.caidan.mgr.ICaiDanReportMgr;
import project.caidan.model.CDAccountCpz;
import project.caidan.model.CDAccountDl;
import project.caidan.model.CDAccoutType;
import project.caidan.model.CDProductRmb;
import project.caidan.model.CdWkqRmb;
import project.caidan.model.Task;
import project.caidan.model.WayReport;

public class CaiDanReportMgrImpl implements ICaiDanReportMgr
{

	private ICdWayDao wayDao;
	private IAccountTypeDao accountTypeDao;
	private IWxUserDao wxUserDao;
	private ICaiDanTeamDao cdTeamDao;
	private ICaiDanProductDao cdProductDao;
	private ITaskDao taskDao;

	@Override
	public ReportDto findWayReport(long owner, int pageid, String starttime, String endtime)
	{
		ReportDto dto = new ReportDto();
		int total = wayDao.findWaysTotal(owner);
		if (total > 0)
		{
			List<WayReport> list = wayDao.findWayReport(owner, (pageid - 1) * ICaiDanConstants.REPORT_LIMIT, ICaiDanConstants.REPORT_LIMIT);
			for (WayReport rep : list)
			{
				long acid = rep.getWay().getAcid();
				if (acid > 0)
				{
					CDAccoutType at = accountTypeDao.findByAcid(acid, owner);
					if (at != null && at.getWxuid() > 0)
					{
						List<CDAccountDl> dls = cdTeamDao.findDlByFaWxuid(at.getWxuid(), rep.getWay().getId(), ICaiDanConstants.ACCOUNT_DL_SUCCESS, starttime, endtime);
						for (CDAccountDl dl : dls)
						{
							dl.setCpzList(cdTeamDao.findAccountCpzByWxuid(dl.getWxuid(), ICaiDanConstants.ACCOUNT_DL_SUCCESS));
							if (StringUtils.isEmpty(dl.getName()))
							{
								dl.setName(wxUserDao.findWxUserByid(dl.getWxuid()).getNickname());
							}
						}
						rep.setList(dls);
					} else
					{
						rep.setList(new ArrayList<CDAccountDl>());
					}
				}

			}
			dto.setList(list);
			dto.setPager(new Pager(pageid, total, ICaiDanConstants.REPORT_LIMIT));
		}
		return dto;
	}

	@Override
	public IDto findCpzs(long wxuid, long wayid, int pageId, String starttime, String endtime)
	{
		ReportDto dto = new ReportDto();
		dto.setDl(cdTeamDao.findAccountDlByWxuid(wxuid));
		int total = cdTeamDao.findCpzTotalByFather(wxuid, wayid, starttime, endtime);
		if (total > 0)
		{
			List<CDAccountCpz> list = cdTeamDao.findCpzByFather(wxuid, wayid, starttime, endtime, (pageId - 1) * ICaiDanConstants.REPORT_CPZ_LIMIT,
					ICaiDanConstants.REPORT_CPZ_LIMIT);
			for (CDAccountCpz cpz : list)
			{
				if (StringUtils.isEmpty(cpz.getName()))
				{
					cpz.setName(wxUserDao.findWxUserByid(cpz.getWxuid()).getNickname());
				}
			}
			dto.setCpzs(list);
			dto.setPager(new Pager(pageId, total, ICaiDanConstants.REPORT_CPZ_LIMIT));
		}
		return dto;
	}

	/**
	 * ÇþµÀÒµ¼¨
	 */
	@Override
	public IDto findChannelPerformance(long owner, int pageId, CdReportSift sift)
	{
		ReportDto dto = new ReportDto();
		int total = cdProductDao.findChannelPerformanceTotal(owner, sift);
		Map<Long, WxUser> map = new HashMap<Long, WxUser>();
		Map<Long, CDAccountCpz> cpzs = new HashMap<Long, CDAccountCpz>();
		Map<Long, CDAccountDl> dls = new HashMap<Long, CDAccountDl>();
		if (total > 0)
		{
			List<CdWkqRmb> list = cdProductDao.findChannelPerformance(owner, sift, (pageId - 1) * ICaiDanConstants.REPORT_ChANNEL_PERFORMANCE,
					ICaiDanConstants.REPORT_ChANNEL_PERFORMANCE);
			for (CdWkqRmb r : list)
			{
				CDProductRmb rmb = null;
				long wxuid = 0;
				WxUser buyer = null;
				CDAccountCpz cpz = null;
				CDAccountDl dl = null;
				CDAccoutType at = null;
				WxUser sj = null;
				try
				{
					rmb = cdProductDao.findProductByPid(r.getPid());
					wxuid = cdProductDao.findWxuidByPogid(r.getPogid());
					buyer = getWxuser(wxuid, map);
					cpz = getCpz(r.getCpzid(), cpzs);
					dl = getDl(cpz.getFawxuid(), dls);
					at = accountTypeDao.findByClid(r.getWayid(), owner);
					sj = getWxuser(at.getWxuid(), map);
				} catch (Exception e)
				{
					System.out.println("====================");
					System.out.println("CdWkqRmb:"+new Gson().toJson(r));
					System.out.println("CDProductRmb:"+new Gson().toJson(rmb));
					System.out.println("wxuid:"+new Gson().toJson(wxuid));
					System.out.println("buyer:"+new Gson().toJson(buyer));
					System.out.println("cpz:"+new Gson().toJson(cpz));
					System.out.println("dl:"+new Gson().toJson(dl));
					System.out.println("at:"+new Gson().toJson(at));
					System.out.println("sj:"+new Gson().toJson(sj));
					System.out.println("====================");
				}finally{
					r.setCpz(cpz);
					r.setDl(dl);
					r.setSj(sj);
					r.setRmb(rmb);
					r.setWxuser(buyer);
				}
				dto.setWkq(list);
				dto.setPager(new Pager(pageId, total, ICaiDanConstants.REPORT_ChANNEL_PERFORMANCE));
			}
		}
		return dto;
	}
	
	@Override
	public List<CdWkqRmb> findExport(long owner, CdReportSift sift)
	{
		Map<Long, WxUser> map = new HashMap<Long, WxUser>();
		Map<Long, CDAccountCpz> cpzs = new HashMap<Long, CDAccountCpz>();
		Map<Long, CDAccountDl> dls = new HashMap<Long, CDAccountDl>();
		List<CdWkqRmb> list = cdProductDao.findChannelPerformance(sift);
		for (CdWkqRmb r : list)
		{
			CDProductRmb rmb = null;
			long wxuid = 0;
			WxUser buyer = null;
			CDAccountCpz cpz = null;
			CDAccountDl dl = null;
			CDAccoutType at = null;
			WxUser sj = null;
			try
			{
				rmb = cdProductDao.findProductByPid(r.getPid());
				wxuid = cdProductDao.findWxuidByPogid(r.getPogid());
				buyer = getWxuser(wxuid, map);
				cpz = getCpz(r.getCpzid(), cpzs);
				dl = getDl(cpz.getFawxuid(), dls);
				at = accountTypeDao.findByClid(r.getWayid(), owner);
				sj = getWxuser(at.getWxuid(), map);
			} catch (Exception e)
			{
				System.out.println("====================");
				System.out.println("CdWkqRmb:"+new Gson().toJson(r));
				System.out.println("CDProductRmb:"+new Gson().toJson(rmb));
				System.out.println("wxuid:"+new Gson().toJson(wxuid));
				System.out.println("buyer:"+new Gson().toJson(buyer));
				System.out.println("cpz:"+new Gson().toJson(cpz));
				System.out.println("dl:"+new Gson().toJson(dl));
				System.out.println("at:"+new Gson().toJson(at));
				System.out.println("sj:"+new Gson().toJson(sj));
				System.out.println("====================");
			}finally{
				r.setCpz(cpz);
				r.setDl(dl);
				r.setSj(sj);
				r.setRmb(rmb);
				r.setWxuser(buyer);
			}
		}
		return list;
	}

	@Override
	public WxUser findWxUserByHyuid(long hyuid) {
		return wxUserDao.findWxUserByHyuid(hyuid);
	}

	@Override
	public Task findTask(long id) {
		return taskDao.getTask(id);
	}

	private WxUser getWxuser(long wxuid, Map<Long, WxUser> map)
	{
		if (map.get(wxuid) != null)
		{
			return map.get(wxuid);
		} else
		{
			WxUser user = wxUserDao.findWxUserByid(wxuid);
			map.put(wxuid, user);
			return user;
		}
	}

	private CDAccountDl getDl(long wxuid, Map<Long, CDAccountDl> map)
	{
		if (map.get(wxuid) != null)
		{
			return map.get(wxuid);
		} else
		{
			CDAccountDl user = accountTypeDao.findDlByWxuid(wxuid, "Y");
			map.put(wxuid, user);
			return user;
		}
	}

	private CDAccountCpz getCpz(long wxuid, Map<Long, CDAccountCpz> map)
	{
		if (map.get(wxuid) != null)
		{
			return map.get(wxuid);
		} else
		{
			CDAccountCpz user = accountTypeDao.findCpzByWxuid(wxuid, "Y");
			map.put(wxuid, user);
			return user;
		}
	}

	public void setWayDao(ICdWayDao wayDao)
	{
		this.wayDao = wayDao;
	}

	public void setCdTeamDao(ICaiDanTeamDao cdTeamDao)
	{
		this.cdTeamDao = cdTeamDao;
	}

	public void setAccountTypeDao(IAccountTypeDao accountTypeDao)
	{
		this.accountTypeDao = accountTypeDao;
	}

	public void setWxUserDao(IWxUserDao wxUserDao)
	{
		this.wxUserDao = wxUserDao;
	}

	public void setCdProductDao(ICaiDanProductDao cdProductDao)
	{
		this.cdProductDao = cdProductDao;
	}

	public void setTaskDao(ITaskDao taskDao) {
		this.taskDao = taskDao;
	}

}
