
package com.huiyee.interact.servicerpj.mgr;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.dao.ISiteDao;
import com.huiyee.esite.dao.IWxUserDao;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.mgr.imp.AbstractMgr;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.OwnerBalanceSet;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.model.WxUser;
import com.huiyee.esite.util.DateUtil;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.esite.util.JsonStringUtil;
import com.huiyee.interact.servicerpj.dao.IServicerPjPageDao;
import com.huiyee.interact.servicerpj.dao.IYuyueServicePjDao;
import com.huiyee.interact.servicerpj.dao.IYuyueServicerDao;
import com.huiyee.interact.servicerpj.dto.ServicerPjDto;
import com.huiyee.interact.servicerpj.model.ServicerPj;
import com.huiyee.interact.servicerpj.model.ServicerPjPage;
import com.huiyee.interact.servicerpj.model.ServicerPjWd;
import com.huiyee.interact.setting.model.HyUserDz;
import com.huiyee.interact.template.model.WxTemplate;
import com.huiyee.interact.template.model.WxTemplateJob;
import com.huiyee.interact.yuyue.dao.IYuYueDao;
import com.huiyee.interact.yuyue.model.YuYue;
import com.huiyee.interact.yuyue.model.YuYueServicer;
import com.huiyee.interact.yuyue.model.YuYueTag;
import com.huiyee.weixin.model.template.GRXXTZ;

import edu.emory.mathcs.backport.java.util.Collections;

public class ServicerPjMgrImpl extends AbstractMgr implements IServicerPjMgr {

	private IYuyueServicerDao yuyueServicerDao;
	private IYuyueServicePjDao yuyueServicePjDao;
	private IServicerPjPageDao servicerPjPageDao;
	private IYuYueDao yuYueDao;
	private IWxUserDao wxUserDao;
	private ISiteDao siteDao;

	public void setSiteDao(ISiteDao siteDao) {
		this.siteDao = siteDao;
	}

	public void setYuyueServicerDao(IYuyueServicerDao yuyueServicerDao) {
		this.yuyueServicerDao = yuyueServicerDao;
	}

	public void setYuyueServicePjDao(IYuyueServicePjDao yuyueServicePjDao) {
		this.yuyueServicePjDao = yuyueServicePjDao;
	}

	public void setServicerPjPageDao(IServicerPjPageDao servicerPjPageDao) {
		this.servicerPjPageDao = servicerPjPageDao;
	}

	public void setYuYueDao(IYuYueDao yuYueDao) {

		this.yuYueDao = yuYueDao;
	}

	public void setWxUserDao(IWxUserDao wxUserDao) {
		this.wxUserDao = wxUserDao;
	}

	@Override
	public IDto findServicerList(Account account, int pageId) {
		ServicerPjDto dto = new ServicerPjDto();
		long owner = account.getOwner().getId();
		int total = yuyueServicerDao.findServicerTotalByOwner(owner);
		if (total > 0) {
			List<YuYueServicer> list = yuyueServicerDao.findServicers(owner, (pageId - 1) * IInteractConstants.INTERACT_SERVICER_LIMIT, IInteractConstants.INTERACT_SERVICER_LIMIT);
			for (YuYueServicer yuYueServicer : list) {
				List<YuYueTag> tagList = yuYueDao.findYuYueTagListBySerid(yuYueServicer.getId());
				yuYueServicer.setTagList(tagList);
			}
			dto.setList(list);
			dto.setPager(new Pager(pageId, total, IInteractConstants.INTERACT_SERVICER_LIMIT));
		}
		dto.setCatalogs(yuYueDao.findYuYueCatalog(owner));
		return dto;
	}

	@Override
	public long saveYuYueServicer(YuYueServicer yuYueServicer, long owner) {
		yuYueServicer.setOwner(owner);
		yuYueServicer.setType(2);// 服务评价的人
		int maxIdx = yuyueServicerDao.findServicerMaxIdx(owner);
		yuYueServicer.setOidx(maxIdx + 1);
		long serid = yuyueServicerDao.saveYuYueServicer(yuYueServicer);
		if (yuYueServicer.getTagname() != null && yuYueServicer.getTagname().size() > 0) {
			YuYueTag yuYueTag = new YuYueTag();
			YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
			for (int i = 0; i < yuYueServicer.getTagname().size(); i++) {
				yuYueTag.setYyid(yuyue.getId());
				yuYueTag.setName(yuYueServicer.getTagname().get(i));
				long tid = yuYueDao.findYuYueTag(yuYueTag);
				if (tid == 0) {
					tid = yuYueDao.saveYuYueTag(yuYueTag);
				}
				yuYueDao.saveYuYueServicerTag(serid, tid);
			}
		}
		return serid;
	}

	@Override
	public int updateServicerByCaid(Account account, long caid) {
		long owner = account.getOwner().getId();
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		List<YuYueServicer> list = yuyueServicerDao.findServicerByCaid(yuyue.getId(), caid, 0);
		Collections.reverse(list);
		int maxIdx = yuyueServicerDao.findServicerMaxIdx(owner) + 1;
		for (YuYueServicer yuYueServicer : list) {
			yuYueServicer.setOwner(owner);
			yuYueServicer.setOidx(maxIdx++);
		}
		return yuyueServicerDao.updateServicerToPj(list);
	}

	@Override
	public int delServicer(Account account, long serid) {
		/**
		 * 有caid的是从微预约导入的 删除时只要修改type 没有caid的是在服务评价新增的可以直接删除
		 */
		long owner = account.getOwner().getId();
		YuYueServicer servicer = yuyueServicerDao.findServicerByid(serid);
		if (servicer == null) {
			return 0;
		} else {
			if (servicer.getCaid() > 0) {
				return yuyueServicerDao.updateServicerBack(owner, servicer.getId());
			} else {
				int rs = yuyueServicerDao.delServicer(owner, servicer.getId());
				if (rs > 0)
					yuYueDao.delYuYueServicerTag(serid);
				return rs;
			}
		}
	}

	@Override
	public int updateServicerOidx(Account account, long serid, int moveUp) {
		long owner = account.getOwner().getId();
		YuYueServicer servicer = yuyueServicerDao.findServicerByid(serid);
		if (servicer != null) {
			YuYueServicer other = null;
			if (moveUp > 0) {
				other = yuyueServicerDao.findFrontServicer(owner, servicer.getOidx(), servicer.getOtop());
			} else {
				other = yuyueServicerDao.findNextServicer(owner, servicer.getOidx(), servicer.getOtop());
			}
			if (other != null) {
				int swapIdx = servicer.getOidx();
				servicer.setOidx(other.getOidx());
				other.setOidx(swapIdx);
				int rs1 = yuyueServicerDao.updateServicerOidx(servicer);
				int rs2 = yuyueServicerDao.updateServicerOidx(other);
				return rs1 + rs2;
			}

		}
		return -1;
	}

	@Override
	public YuYueServicer findServicerById(Account account, long serid) {
		YuYueServicer yy = yuyueServicerDao.findServicerByid(serid);
		if (yy != null)
			yy.setTagList(yuYueDao.findYuYueTagListBySerid(yy.getId()));
		return yy;
	}

	@Override
	public int updateServicer(YuYueServicer yuYueServicer, long owner) {
		yuYueDao.delYuYueServicerTag(yuYueServicer.getId());// 删除关系表
		if (yuYueServicer.getTagname().size() > 0) {
			YuYueTag yuYueTag = new YuYueTag();
			for (int i = 0; i < yuYueServicer.getTagname().size(); i++) {
				YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
				yuYueTag.setName(yuYueServicer.getTagname().get(i));
				yuYueTag.setYyid(yuyue.getId());
				long tid = yuYueDao.findYuYueTag(yuYueTag);
				if (tid == 0) {
					tid = yuYueDao.saveYuYueTag(yuYueTag);
				}
				yuYueDao.saveYuYueServicerTag(yuYueServicer.getId(), tid);
			}
		}
		return yuyueServicerDao.updateYuYueServicer(yuYueServicer);
	}

	@Override
	public IDto findServicerPjList(Account account, long serid, int pageId) {
		ServicerPjDto dto = new ServicerPjDto();
		long owner = account.getOwner().getId();
		dto.setList(yuyueServicerDao.findServicers(owner));
		int total = yuyueServicePjDao.findServicerPjTotal(owner, serid);
		if (total > 0) {
			dto.setPager(new Pager(pageId, total, IInteractConstants.INTERACT_SERVICER_PJ_LIMIT));
			List<ServicerPj> pjList = yuyueServicePjDao.findServicerPjList(owner, serid, (pageId - 1) * IInteractConstants.INTERACT_SERVICER_PJ_LIMIT, IInteractConstants.INTERACT_SERVICER_PJ_LIMIT);
			Map<Long, YuYueServicer> map = new HashMap<Long, YuYueServicer>();
			for (ServicerPj pj : pjList) {
				pj.setWdList(yuyueServicePjDao.findServicerPjWdListByPjid(pj.getId()));
				pj.setWxUser(wxUserDao.findWxUserByid(pj.getWxuid()));
				if (map.get(pj.getSerid()) == null) {
					YuYueServicer ser = yuyueServicerDao.findServicerByid(pj.getSerid());
					pj.setYuYueServicer(ser);
					map.put(ser.getId(), ser);
				} else {
					pj.setYuYueServicer(map.get(pj.getSerid()));
				}
			}
			dto.setPjList(pjList);
		}
		return dto;
	}

	@Override
	public int updateServicerTop(Account account, long serid, int top) {
		return yuyueServicerDao.updateServicerTop(account.getOwner().getId(), serid, top);
	}

	@Override
	public int findServicerPjTotal(long owner, long srid) {
		return yuyueServicePjDao.findServicerPjTotal(owner, srid);
	}

	@Override
	public List<ServicerPj> findServicerPjList(long owner, long srid, int start, int rows) {
		List<ServicerPj> pjList = yuyueServicePjDao.findServicerPjList(owner, srid, start, rows);
		for (ServicerPj pj : pjList) {
			pj.setWdList(yuyueServicePjDao.findServicerPjWdListByPjid(pj.getId()));
			pj.setWxUser(wxUserDao.findWxUserByid(pj.getWxuid()));
			pj.setYuYueServicer(yuyueServicerDao.findServicerByid(pj.getSerid()));
		}
		return pjList;
	}

	@Override
	public int addServicerPjInvite(ServicerPj pj) {
		WxUser user = wxUserDao.findWxUserByid(pj.getWxuid());
		if (user == null) {
			return -1;
		}

		if (pj.getSourceid() == 0) {
			return -2;
		}

		List<WxTemplate> wtList = this.findWxTemplate(pj.getOwner(), "OCF", pj.getSourceid());
		for (WxTemplate wt : wtList) {
			GRXXTZ dd = (GRXXTZ) JsonStringUtil.String2Obj(wt.getData(), GRXXTZ.class);
			String nickname = user.getNickname();
			dd.setFirst(dd.getFirst().replace("${nickname}", nickname));
			dd.setKeyword1(dd.getKeyword1().replace("${nickname}", nickname));
			dd.setKeyword2(DateUtil.getDateTimeString(new Date()));
			dd.setKeyword3(dd.getKeyword3().replace("${nickname}", nickname));
			dd.setRemark(dd.getRemark().replace("${nickname}", nickname));

			String kv = pj.getType() + "-" + pj.getSourceid() + "-" + pj.getEnid();
			String url = HyConfig.getPageyuming() + "/" + pj.getOname() + "/user/wxshowp/uipj/" + kv + ".html";
			WxTemplateJob wj = new WxTemplateJob();
			wj.setMpid(wt.getMpid());
			wj.setType(wt.getType());
			wj.setEntityid(wt.getEntityid());
			wj.setTouser(user.getOpenid());
			wj.setRemark(wt.getRemark());
			wj.setTemplate_id(wt.getTemplate_id());
			wj.setData(dd.toData());
			wj.setUrl(url);
			this.addTmplMsg(wj);
		}
		return wtList.size();
	}

	@Override
	public ServicerPj findServicerPj(int type, long enid) {
		ServicerPj pj = yuyueServicePjDao.findServicerPj(type, enid);
		if (pj != null) {
			pj.setWdList(yuyueServicePjDao.findServicerPjWdListByPjid(pj.getId()));
			pj.setWxUser(wxUserDao.findWxUserByid(pj.getWxuid()));
			pj.setYuYueServicer(yuyueServicerDao.findServicerByid(pj.getSerid()));
		}
		return pj;
	}

	@Override
	public int addServicerPj(VisitUser vu, ServicerPj pj) {
		long owner = pj.getOwner();
		int type = pj.getType();
		long sourceid = pj.getSourceid();
		long enid = pj.getEnid();
		// find pageid
		ServicerPjPage pjp = servicerPjPageDao.findServicerPjPage(owner, type);
		if (pjp == null || pjp.getPjrid() == 0) {
			return -1;
		}

		// 保存评价
		long pjid = yuyueServicePjDao.addServicerPj(pj);
		if (pjid == 0) {
			return -2;
		}

		List<ServicerPjWd> wdList = pj.getWdList();
		if (wdList != null) {
			// 保存评价分数
			yuyueServicePjDao.addServicerPjWdl(pjid, wdList);

			// 更新服务人员评分
			yuyueServicePjDao.updateServicerPt(pj.getSerid(), wdList);
		}

		// 增加积分
		OwnerBalanceSet obs = this.updateHyUserBalance(vu.getHyUserId(), "FWP", owner, pj.getEnid());
		// 发送模板消息给店主
		List<WxTemplate> wtList = this.findWxTemplate(owner, "FWP", 0);
		for (WxTemplate wt : wtList) {
			String nickname = vu.getWxUser().getNickname();
			GRXXTZ dd = (GRXXTZ) JsonStringUtil.String2Obj(wt.getData(), GRXXTZ.class);
			dd.setFirst(dd.getFirst().replace("${nickname}", nickname));
			dd.setKeyword1(dd.getKeyword1().replace("${nickname}", nickname));
			dd.setKeyword2(DateUtil.getDateTimeString(new Date()));
			dd.setKeyword3(dd.getKeyword3().replace("${nickname}", nickname));
			dd.setRemark(dd.getRemark().replace("${nickname}", nickname));

			String kv = type + "-" + sourceid + "-" + enid;
			String url = HyConfig.getPageyuming() + "/" + pj.getOname() + "/user/wxshow/" + pjp.getPjrid() + "/wxn/" + kv + ".html";
			List<HyUserDz> dzs = hyUserDzDao.findDzList(owner, "t3", 0, 100);
			for (HyUserDz dz : dzs) {
				WxTemplateJob wj = new WxTemplateJob();
				wj.setMpid(wt.getMpid());
				wj.setType(wt.getType());
				wj.setEntityid(wt.getEntityid());
				wj.setTouser(dz.getOpenid());
				wj.setRemark(wt.getRemark());
				wj.setTemplate_id(wt.getTemplate_id());
				wj.setData(dd.toData());
				wj.setUrl(url);
				this.addTmplMsg(wj);
			}
		}
		return obs.getYypjnum();
	}

	@Override
	public int updateDzcontent(VisitUser vu, ServicerPj pj) {
		long owner = pj.getOwner();
		int type = pj.getType();
		long sourceid = pj.getSourceid();
		long enid = pj.getEnid();
		// 店主验证
		if (!this.isDz(owner, "t3", vu.getHyUserId())) {
			return -1;
		}
		// find pageid
		ServicerPjPage pjp = servicerPjPageDao.findServicerPjPage(owner, type);
		if (pjp == null || pjp.getPjxid() == 0) {
			return -2;
		}
		// 发送模板消息给用户
		List<WxTemplate> wtList = this.findWxTemplate(owner, "FWR", 0);
		for (WxTemplate wt : wtList) {
			String nickname = vu.getWxUser().getNickname();
			GRXXTZ dd = (GRXXTZ) JsonStringUtil.String2Obj(wt.getData(), GRXXTZ.class);
			dd.setFirst(dd.getFirst().replace("${nickname}", nickname));
			dd.setKeyword1(dd.getKeyword1().replace("${nickname}", nickname));
			dd.setKeyword2(DateUtil.getDateTimeString(new Date()));
			dd.setKeyword3(dd.getKeyword3().replace("${nickname}", nickname));
			dd.setRemark(dd.getRemark().replace("${nickname}", nickname));

			String kv = type + "-" + sourceid + "-" + enid;
			String url = HyConfig.getPageyuming() + "/" + pj.getOname() + "/user/wxshow/" + pjp.getPjxid() + "/wxn/" + kv + ".html";
			WxTemplateJob wj = new WxTemplateJob();
			wj.setMpid(wt.getMpid());
			wj.setType(wt.getType());
			wj.setEntityid(wt.getEntityid());
			wj.setTouser(pj.getWxUser().getOpenid());
			wj.setRemark(wt.getRemark());
			wj.setTemplate_id(wt.getTemplate_id());
			wj.setData(dd.toData());
			wj.setUrl(url);
			this.addTmplMsg(wj);
		}

		return yuyueServicePjDao.updateDzcontent(pj.getId(), pj.getDzcontent());
	}

	@Override
	public int updateServicerPage(Account account, long sgid, int source) {
		long owner = account.getOwner().getId();
		Map<String, Long> map = new HashMap<String, Long>();
		List<Site> sites = siteDao.findSiteBySiteGroupId(sgid);
		for (Site site : sites) {
			List<Page> pages = siteDao.findPageListBySiteId(site.getId());
			for (Page page : pages) {
				// PJT-服务评价提交页;PJX-评价详情页;PJR-店主回复评价页;PJS-服务人员列表页;PJC-评价列表页
				if (!"NOR".equals(page.getApptype())) {
					String apptype = page.getApptype().toLowerCase();
					if (map.get(apptype) != null) {
						System.out.println("duplicate apptype for siteid:" + site.getId() + ";page:" + page.getId() + "==== exitpageid:" + map.get(apptype));
					} else {
						map.put(apptype, page.getId());
					}
				}
			}
		}
		return yuyueServicerDao.updateServicerPage(owner, source, map);
	}

	@Override
	public List<ServicerPjWd> findServicerPjWdList(long owner) {
		return yuyueServicePjDao.findServicerPjWdList(owner);
	}

}
