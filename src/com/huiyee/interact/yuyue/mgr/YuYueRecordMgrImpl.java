package com.huiyee.interact.yuyue.mgr;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.huiyee.esite.mgr.imp.AbstractMgr;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.DateUtil;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.esite.util.JsonStringUtil;
import com.huiyee.interact.appointment.model.AppointmentDataModel;
import com.huiyee.interact.setting.dao.IHyUserDzDao;
import com.huiyee.interact.setting.model.HyUserDz;
import com.huiyee.interact.template.model.WxTemplate;
import com.huiyee.interact.template.model.WxTemplateJob;
import com.huiyee.interact.yuyue.dao.IYuYueDao;
import com.huiyee.interact.yuyue.dao.IYuYueFormDao;
import com.huiyee.interact.yuyue.dao.IYuYueRecordDao;
import com.huiyee.interact.yuyue.dto.IDto;
import com.huiyee.interact.yuyue.dto.YuYueRecordDto;
import com.huiyee.interact.yuyue.model.YuYue;
import com.huiyee.interact.yuyue.model.YuYueCatalog;
import com.huiyee.interact.yuyue.model.YuYueSSTime;
import com.huiyee.interact.yuyue.model.YuYueSSTimeUsed;
import com.huiyee.interact.yuyue.model.YuYueService;
import com.huiyee.interact.yuyue.model.YuYueServicer;
import com.huiyee.weixin.model.template.GRXXTZ;

public class YuYueRecordMgrImpl extends AbstractMgr implements IYuYueRecordMgr {
	private IYuYueRecordDao yuYueRecordDao;
	private IYuYueFormDao yuYueFormDao;
	private IYuYueDao yuYueDao;
	private IHyUserDzDao hyUserDzDao;

	public void setYuYueRecordDao(IYuYueRecordDao yuYueRecordDao) {
		this.yuYueRecordDao = yuYueRecordDao;
	}

	public void setYuYueFormDao(IYuYueFormDao yuYueFormDao) {
		this.yuYueFormDao = yuYueFormDao;
	}

	public void setYuYueDao(IYuYueDao yuYueDao) {
		this.yuYueDao = yuYueDao;
	}

	public void setHyUserDzDao(IHyUserDzDao hyUserDzDao) {
		this.hyUserDzDao = hyUserDzDao;
	}

	@Override
	public IDto saveYuYueRecord(VisitUser visit, long owner, AppointmentDataModel aptRecord, long catid, long serid, Date yytime, String ip, String hydesc, long pageid, long serviceid, String type,
			String onameurl,String tag1,String tag2) {
		long wxuid = visit.getWxUser().getId();
		YuYueRecordDto dto = new YuYueRecordDto();
		YuYue yuyue = yuYueDao.findYuYueByOwner(owner);
		if (yuyue == null) {
			dto.setStatus(1);
			dto.setDesc("预约失败，还未开通预约！");
			return dto;
		}
		aptRecord.setAptid(yuyue.getAptid());
		AppointmentDataModel model = yuYueFormDao.findAptRecordByWxuid(yuyue.getAptid(), wxuid, 1);
		if (model != null) {
			yuYueRecordDao.updateInteractAptRecord(wxuid, aptRecord, 1, model.getId());
		} else {
			yuYueRecordDao.saveInteractAptRecord(wxuid, aptRecord, 1, pageid);
		}
		if ("S".equals(type) && serviceid > 0 && serid == -1) {// 根据科室 服务
																// 查出医生（唯一）
			serid = yuYueDao.findYuYueServicerByCatidAndServicerid(catid, serviceid);
		}
		if ("U".equals(type) && serid > 0 && serviceid == 0) {// 根据科室 医生 查出
																// 服务（唯一）
			serviceid = yuYueDao.findYuYueServiceBySerid(serid);
		}

		if (serviceid == 0) {//未选择
			List<YuYueService> serviceList = yuYueDao.findYuYueServiceListByCatid(catid, yuyue.getId());// 搜科室下的服务
			if (serviceList == null) {
				dto.setStatus(1);
				dto.setDesc("预约失败，还没有服务！");
				return dto;
			}
			serviceid = serviceList.get(0).getId();// 服务的id
		}
		if (serid == -2) {// 默认
			serid = yuYueDao.findServicerIdByCatid(catid, yuyue.getId());
		}
		if (serid == -1) {//未选择
			List<YuYueServicer> servicerList = yuYueDao.findYuYueServicerListByCatid(catid, yuyue.getId());
			if (servicerList == null) {
				dto.setStatus(1);
				dto.setDesc("预约失败，还没有提供者！");
				return dto;
			}
			serid = servicerList.get(0).getId();
		}

		String servicename = null;
		String sername = null;
		String catname = null;
		long ssid = yuYueDao.findYuYueSS(serviceid, serid);// 查询服务、提供者的关系表
		if (ssid == 0) {
			dto.setStatus(1);
			dto.setDesc("预约失败,此提供者不提供此服务！");
			return dto;
		}else{
			YuYueService service =  yuYueDao.findYuYueServiceById(yuyue.getId(), serviceid);
			if(service != null){
				servicename = service.getName();
			}
			YuYueServicer servicer = yuYueDao.findYuYueServicerById(serid);
			if(servicer != null){
				sername = servicer.getName();
			}
		}
		int dateday = Integer.parseInt(DateUtil.getDate5String(yytime));
		int weekday = getWeekDay(yytime);// 查询星期几
		List<YuYueSSTime> ssTimeList = yuYueDao.findYuYueSSTimeByDateday(owner, dateday, ssid);// 查询具体日期该ssid的时间配置
		if (ssTimeList == null) {
			ssTimeList = yuYueDao.findYuYueSSTimeByWeekday(owner, weekday, ssid);// 查询星期几的时间配置
			if (ssTimeList == null) {
				ssTimeList = yuYueDao.findYuYueSSTimeByTimeToTime(owner, yytime, ssid);
				if (ssTimeList == null) {
					dto.setStatus(1);
					dto.setDesc("预约失败，该时间不能预约！");// 没配置规则
					return dto;
				}
			}
		}
		long stid = ssTimeList.get(0).getId();
		int total = ssTimeList.get(0).getTotal();
		YuYueSSTimeUsed tiemUsed = yuYueRecordDao.findYuYueTimeUsed(dateday, stid, ssid, owner);
		if (tiemUsed != null) {
			if (total != 0 && tiemUsed.getUsed() >= total) {
				dto.setStatus(1);
				dto.setDesc("预约失败，预约已经满额了！");
				return dto;
			}
			yuYueRecordDao.updateYuYueTimeUsedById(tiemUsed.getId());
		} else {
			yuYueRecordDao.saveYuYueTimeUsed(dateday, stid, ssid, owner);
		}
		
		YuYueCatalog yyc = yuYueDao.findYuYueCatalogById(catid, owner);
		if(yyc != null){
			catname = yyc.getName();
		}
		yuYueRecordDao.saveYuYueRecord(wxuid, ip, stid, yytime, hydesc,servicename,sername,serid,serviceid,catid,catname,tag1,tag2);
		dto.setDesc("预约成功");
		this.updateHyUserBalance(visit.getHyUserId(), "YYU", owner, stid);

		if (owner > 0) {
			List<WxTemplate> wts = this.findWxTemplate(owner, "YYD", 0);
			if (wts != null && wts.size() > 0) {
				String date = DateUtil.getDateTimeString(yytime);
				String nickname = visit.getWxUser().getNickname();
				if (nickname == null) {
					nickname = "";
				}
				for (WxTemplate wt : wts) {
					GRXXTZ dd = (GRXXTZ) JsonStringUtil.String2Obj(wt.getData(), GRXXTZ.class);
					dd.setFirst(dd.getFirst().replace("${nickname}", nickname).replace("${time}", date));
					dd.setKeyword1(dd.getKeyword1().replace("${nickname}", nickname).replace("${time}", date));
					dd.setKeyword2(date);
					dd.setKeyword3(dd.getKeyword3().replace("${nickname}", nickname).replace("${time}", date));
					dd.setRemark(dd.getRemark().replace("${nickname}", nickname).replace("${time}", date));

					List<HyUserDz> dzs = hyUserDzDao.findDzList(owner, "t2", 0, 100);
					for (HyUserDz dz : dzs) {
						WxTemplateJob wj = new WxTemplateJob();
						wj.setMpid(wt.getMpid());
						wj.setType(wt.getType());
						wj.setEntityid(wt.getEntityid());
						wj.setRemark(wt.getRemark());
						wj.setTemplate_id(wt.getTemplate_id());
						wj.setData(dd.toData());
						if (yyc.getDzpageid() > 0)
							wj.setUrl(HyConfig.getPageyuming(owner) + onameurl + "/user/wxshow/" + yyc.getDzpageid() + "/dzx.html");
						wj.setTouser(dz.getOpenid());
						this.addTmplMsg(wj);
					}
				}
			}
		}
		return dto;
	}

	public int getWeekDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int weekday = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (weekday == 0) {
			weekday = 7;
		}
		return weekday;
	}

}
