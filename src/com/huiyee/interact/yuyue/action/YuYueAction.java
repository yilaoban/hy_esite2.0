package com.huiyee.interact.yuyue.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.constants.IPrivilegeConstants;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.Sitegroup;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.service.Permission;
import com.huiyee.esite.util.DateUtil;
import com.huiyee.interact.appointment.mgr.IInteractAptManager;
import com.huiyee.interact.appointment.model.AppointmentDataModel;
import com.huiyee.interact.appointment.model.AppointmentReflect;
import com.huiyee.interact.appointment.util.ToolUtil;
import com.huiyee.interact.template.mgr.IWxTemplateMgr;
import com.huiyee.interact.template.model.WxTemplate;
import com.huiyee.interact.yuyue.dto.YuYueDto;
import com.huiyee.interact.yuyue.mgr.IYuYueMgr;
import com.huiyee.interact.yuyue.model.YuYue;
import com.huiyee.interact.yuyue.model.YuYueCatalog;
import com.huiyee.interact.yuyue.model.YuYueSS;
import com.huiyee.interact.yuyue.model.YuYueSSTime;
import com.huiyee.interact.yuyue.model.YuYueService;
import com.huiyee.interact.yuyue.model.YuYueServicer;
import com.huiyee.interact.yuyue.model.YuYueTag;
import com.opensymphony.xwork2.ActionContext;

public class YuYueAction extends AbstractAction {
	private static final long serialVersionUID = 8355218749578914852L;
	private IYuYueMgr yuYueMgr;
	private IWxTemplateMgr wxTemplateMgr;
	private List<Sitegroup> list;
	private YuYue yuYue;
	private YuYueDto dto;
	private YuYueCatalog yuYueCatalog;
	private YuYueService yuYueService;
	private YuYueServicer yuYueServicer;
	private YuYueSSTime yuYueSSTime;
	private YuYueTag yuYueTag;
	private WxTemplate wt_u;
	private WxTemplate wt_d;
	private int lightType = 1;
	private int pageId = 1;
	private long catid;
	private long serviceid;
	private long serid;
	private long ssid;
	private long timeid;
	private long tagid;
	private String swtype = "S";// S: 服务 R：服务者
	private int day;
	private int weekday;// 周几
	private int dateday;// 具体日期
	private YuYueSS yuYueSS;
	private String type;
	private long recordid;
	private Date yytime;
	private String yytimeStr;
	private Date startTime;
	private Date endTime;
	private String starttime;
	private String endtime;
	
	private String status = "ALL";
	private long siteid;

	private AppointmentDataModel record;
	private IInteractAptManager interactAptManager;
	private long wxuid;
	private long aptid;

	private long pageid;
	
	public void setYuYueMgr(IYuYueMgr yuYueMgr) {
		this.yuYueMgr = yuYueMgr;
	}

	public void setWxTemplateMgr(IWxTemplateMgr wxTemplateMgr) {
		this.wxTemplateMgr = wxTemplateMgr;
	}

	public int moveUp;// 1上移 -1下移
	private int top;// 1置顶 0不置顶

	/**
	 * 微预约首页 已开通时显示 未开通是显示开通按钮
	 */
	@Permission(module = IPrivilegeConstants.MODULE_APP_微预约, privilege = IPrivilegeConstants.PERMISSION_R)
	public String index() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		yuYue = yuYueMgr.findYuYueByOwner(account.getOwner().getId());
		if (yuYue != null) {
			return "toSource";
		} else {
			return SUCCESS;
		}
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String open() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		yuYueMgr.saveYuYue(account.getOwner().getId());
		return SUCCESS;
	}

	/**
	 * 科室列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@Permission(module = IPrivilegeConstants.MODULE_APP_微预约, privilege = IPrivilegeConstants.PERMISSION_R)
	public String yuyueCatalog() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (YuYueDto) yuYueMgr.findYuYueCatalog(account.getOwner().getId(), pageId);
		return SUCCESS;
	}

	/**
	 * 新增科室
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addYuYueCatalog() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		list = pageCompose.findPageTofs(account, "Y");
		return SUCCESS;
	}

	public String saveYuYueCatalog() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		yuYueMgr.saveYuYueCatalog(account.getOwner().getId(), yuYueCatalog);
		return SUCCESS;
	}

	public String editYuYueCatalog() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		list = pageCompose.findPageTofs(account, "Y");
		yuYueCatalog = yuYueMgr.findYuYueCatalogById(catid, account.getOwner().getId());
		Page page = pageCompose.findPageByid(yuYueCatalog.getSpageid());
		if (page != null) {
			siteid = page.getSiteid();
		}
		return SUCCESS;
	}

	public String updateYuYueCatalog() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		yuYueMgr.updateYuYueCatalog(account.getOwner().getId(), yuYueCatalog);
		return SUCCESS;
	}

	/**
	 * 删除科室
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delYuYueCatalog() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		long result = yuYueMgr.delYuYueCatalog(catid, account.getOwner().getId());
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	public String updateYuYueCatalogType() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		long result = yuYueMgr.updateYuYueCatalogType(catid, type);
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 服务列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String yuyueService() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (YuYueDto) yuYueMgr.findYuYueServiceListByCatid(catid, account.getOwner().getId(), pageId);
		return SUCCESS;
	}

	/**
	 * 新增服务
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addYuYueService() throws Exception {
		return SUCCESS;
	}

	public String saveYuYueService() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		yuYueMgr.saveYuYueService(account.getOwner().getId(), yuYueService);
		return SUCCESS;
	}

	public String editYuYueService() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		yuYueService = yuYueMgr.findYuYueServiceById(serviceid, account.getOwner().getId());
		return SUCCESS;
	}

	public String updateYuYueService() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		yuYueMgr.updateYuYueService(account.getOwner().getId(), yuYueService);
		return SUCCESS;
	}

	public String delYuYueService() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		long result = yuYueMgr.delYuYueService(serviceid, account.getOwner().getId());
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 人员列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String yuYueServicer() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (YuYueDto) yuYueMgr.findYuYueServicerListByServiceid(catid, account.getOwner().getId(), pageId);
		return SUCCESS;
	}

	/**
	 * 服务列表配置人员
	 * 
	 * @return
	 * @throws Exception
	 */
	public String yuYueServiceSet() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (YuYueDto) yuYueMgr.findYuYueServicerListByServiceid(serviceid, catid, account.getOwner().getId(), pageId);
		return SUCCESS;
	}

	/**
	 * 人员列表配置服务
	 * 
	 * @return
	 * @throws Exception
	 */
	public String yuYueServicerSet() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (YuYueDto) yuYueMgr.findYuYueServiceListBySerid(serid, catid, account.getOwner().getId(), pageId);
		return SUCCESS;
	}

	/**
	 * 配置关系表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String yuYueSet() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		long result = yuYueMgr.saveYuYueSS(serviceid, serid);
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	public String addYuYueServicer() throws Exception {
		return SUCCESS;
	}

	public String saveYuYueServicer() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		yuYueMgr.saveYuYueServicer(yuYueServicer, account.getOwner().getId());
		return SUCCESS;
	}

	public String editYuYueServicer() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		yuYueServicer = yuYueMgr.findYuYueServicerById(serid, account.getOwner().getId(), catid);
		return SUCCESS;
	}

	public String updateYuYueServicer() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		yuYueMgr.updateYuYueServicer(yuYueServicer, account.getOwner().getId());
		return SUCCESS;
	}

	public String delYuYueServicer() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		long result = yuYueMgr.delYuYueServicer(serid, account.getOwner().getId(), serviceid);
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 时间配置列表 修改为预约配置
	 * 
	 * @return
	 * @throws Exception
	 */
	public String yuYueSSTime() throws Exception {
		dto = (YuYueDto) yuYueMgr.findYuYueSSTimeByCatid(catid, this.getOwner(), weekday);
		return SUCCESS;
	}

	/**
	 * 按日
	 * 
	 * @return
	 * @throws Exception
	 */
	public String yuYueDaySSTime() throws Exception {
		dto = (YuYueDto) yuYueMgr.findYuYueSSTimeByDateday(catid, this.getOwner(), dateday);
		return SUCCESS;
	}

	/**
	 * 按时间段
	 * 
	 * @return
	 * @throws Exception
	 */
	public String yuYueSSTimeToTime() throws Exception {
		dto = (YuYueDto) yuYueMgr.findYuYueSSTimeByTime(catid, this.getOwner(), pageId);
		return SUCCESS;
	}

	/**
	 * 添加配置时间
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addYuYueSSTime() throws Exception {
		dto = (YuYueDto) yuYueMgr.saveYuYueServiceAndServicer(catid, this.getOwner(), weekday);
		return SUCCESS;
	}

	/**
	 * 保存按周时间配置
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveYuYueSSTime() throws Exception {
		int res = yuYueMgr.saveYuYueSSTime(yuYueSSTime, this.getOwner(), yuYueService, yuYueServicer, catid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(res));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 按日
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveYuYueDaySSTime() throws Exception {
		int res = yuYueMgr.saveYuYueDaySSTime(yuYueSSTime, this.getOwner(), yuYueService, yuYueServicer, catid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(res));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 按时间段
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveYuYueTime() throws Exception {
		int res = yuYueMgr.saveYuYueTime(yuYueSSTime, this.getOwner(), yuYueService, yuYueServicer, catid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(res));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 编辑时间配置
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editYuYueSSTime() throws Exception {
		yuYueSSTime = yuYueMgr.findYuYueSSTimeById(timeid, ssid);
		yuYueSS = yuYueMgr.findYuYueSSBySsid(ssid, catid, this.getOwner());
		dto = (YuYueDto) yuYueMgr.saveYuYueServiceAndServicer(catid, this.getOwner(), weekday);
		return SUCCESS;
	}

	/**
	 * 更新时间配置
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateYuYueSSTime() throws Exception {
		yuYueSSTime.setOwner(this.getOwner());
		int res = yuYueMgr.updateYuYueSSTimeById(yuYueSSTime, yuYueService, yuYueServicer, catid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(res));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 删除时间配置
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delYuYueSSTime() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		long result = yuYueMgr.delYuYueSSTime(timeid, ssid);
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 标签列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addYuYueTag() throws Exception {
		dto = (YuYueDto) yuYueMgr.findYuYueTagList(this.getOwner(), pageId);
		return SUCCESS;
	}

	/**
	 * 保存标签
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveYuYueTag() throws Exception {
		yuYueMgr.saveYuYueTag(this.getOwner(), yuYueTag);
		return SUCCESS;
	}

	/**
	 * 删除标签
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delYuYueTag() throws Exception {
		yuYueMgr.delYuYueTag(this.getOwner(), tagid);
		return SUCCESS;
	}

	/**
	 * 标签设置
	 * 
	 * @return
	 * @throws Exception
	 */
	public String yuYueTagSet() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		long result = yuYueMgr.saveYuYueServicerTag(serid, tagid);
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 预约记录（所有的记录）
	 * 
	 * @return
	 * @throws Exception
	 */
	@Permission(module = IPrivilegeConstants.MODULE_APP_微预约, privilege = IPrivilegeConstants.PERMISSION_R)
	public String yuyueRecord() throws Exception {
		setLightType(2);
		if(StringUtils.isNotBlank(starttime)){
			startTime = DateUtil.getDateTime(starttime);
		}
		if(StringUtils.isNotBlank(endtime)){
			endTime = DateUtil.getDateTime(endtime); 
		}
		if(StringUtils.isNotBlank(yytimeStr)){
			yytime = DateUtil.getDateTime(yytimeStr);
		}
		dto = (YuYueDto) yuYueMgr.findYuYueRecordList(this.getOwner(), pageId, status, startTime, endTime, yytime);
		return SUCCESS;
	}

	/**
	 * 科室预约记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String yuyueCatelogRecord() throws Exception {
		if(StringUtils.isNotBlank(starttime)){
			startTime = DateUtil.getDateTime(starttime);
		}
		if(StringUtils.isNotBlank(endtime)){
			endTime = DateUtil.getDateTime(endtime); 
		}
		dto = (YuYueDto) yuYueMgr.yuyueCatelogRecord(this.getOwner(), catid, pageId, day, status, startTime, endTime);
		return SUCCESS;
	}

	public String yuyueRecordShow() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		if(StringUtils.isNotBlank(starttime)){
			startTime = DateUtil.getDateTime(starttime);
		}
		if(StringUtils.isNotBlank(endtime)){
			endTime = DateUtil.getDateTime(endtime); 
		}
		if(StringUtils.isNotBlank(yytimeStr)){
			yytime = DateUtil.getDateTime(yytimeStr);
		}
		dto = (YuYueDto) yuYueMgr.findYuYueRecordList(this.getOwner(), pageId, status, startTime, endTime, yytime);
		Gson gson = new Gson();
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;

	}
	
	public String yyRecord() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser visit = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if(visit != null){
			dto = (YuYueDto) yuYueMgr.findyyRecord(this.getOwner(),pageId,status,visit.getHyUserId());
		}
		Gson gson = new Gson();
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}
	
	
	/**
	 * 过期记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String yuyueRecordShowByNow() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		dto = (YuYueDto) yuYueMgr.findYuYueRecordList(this.getOwner(), pageId, status);
		Gson gson = new Gson();
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}

	public String delYYRecord() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		long result = yuYueMgr.delYYRecord(recordid);
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	@Permission(module = IPrivilegeConstants.MODULE_APP_微预约, privilege = IPrivilegeConstants.PERMISSION_R)
	public String editYYRecord() throws Exception {
		dto = (YuYueDto) yuYueMgr.findYuYueRecordById(this.getOwner(), recordid);
		return SUCCESS;
	}

	public String updateYYRecord() throws Exception {
		if(StringUtils.isNotBlank(yytimeStr)){
			yytime = DateUtil.getDateTime(yytimeStr);
		}
		int res = yuYueMgr.updateYYRecord(recordid, yytime, this.getOwner(), this.getOnameUrl());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(res));
		out.flush();
		out.close();
		return null;
	}

	@Permission(module = IPrivilegeConstants.MODULE_APP_微预约, privilege = IPrivilegeConstants.PERMISSION_R)
	public String aptIndex() throws Exception {
		setLightType(4);
		yuYue = yuYueMgr.findYuYueByOwner(this.getOwner());
		return SUCCESS;
	}

	/**
	 * 服务项目配置提供者
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveYuYueSS() throws Exception {
		serid = yuYueMgr.saveYuYueServicer(yuYueServicer, this.getOwner());
		long result = yuYueMgr.saveYuYueSS(serviceid, serid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 提供者，配置服务
	 */
	public String saveYuYueSService() throws Exception {
		serviceid = yuYueMgr.saveYuYueService(this.getOwner(), yuYueService);
		long result = yuYueMgr.saveYuYueSS(serviceid, serid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 科室排序上移
	 * 
	 * @return
	 * @throws Exception
	 */
	public String catalogUp() throws Exception {
		long result = yuYueMgr.updateCatalogUp(catid, this.getOwner());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 科室排序上移
	 * 
	 * @return
	 * @throws Exception
	 */
	public String catalogDown() throws Exception {
		long result = yuYueMgr.updateCatalogDown(catid, this.getOwner());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 提供者上移/下移
	 * 
	 * @return
	 * @throws Exception
	 */
	public String servicerIdx() throws Exception {
		long result = yuYueMgr.updateServicerIdx(serid, this.getOwner(), moveUp);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 服务上移/下移
	 * 
	 * @return
	 * @throws Exception
	 */
	public String serviceIdx() throws Exception {
		long result = yuYueMgr.updateServiceIdx(serviceid, this.getOwner(), moveUp);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	public String setServicerTop() throws Exception {
		int result = yuYueMgr.updateServiceTop(serid, this.getOwner(), top);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 详情
	 * 
	 * @return
	 * @throws Exception
	 */
	public String aptDetailEdit() throws Exception {
		record = interactAptManager.findAppointmentDataByWxuid(wxuid, aptid, this.getOwner());
		if (record == null) {
			record = interactAptManager.findAppointMaps(aptid);
		}
		record.setValues(ToolUtil.testReflect(record, new AppointmentReflect()));
		dto = (YuYueDto) yuYueMgr.findYuYueRecordById(this.getOwner(), recordid);
		dto.setAptrecord(record);
		return SUCCESS;
	}

	public String updateAptRecord() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		if(StringUtils.isNotBlank(yytimeStr)){
			yytime = DateUtil.getDateTime(yytimeStr);
		}
		int	res = yuYueMgr.updateYYRecord(recordid, yytime, this.getOwner(), this.getOnameUrl(),record);
		out.print(res);
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 模板消息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Permission(module = IPrivilegeConstants.MODULE_APP_微预约, privilege = IPrivilegeConstants.PERMISSION_R)
	public String template() throws Exception {
		long owner = this.getOwner();
		// 通知用户
		String type = "YYU";
		long store_id = 3;
		wt_u = wxTemplateMgr.getTemplate(owner, type);
		if (wt_u == null) {
			wt_u = new WxTemplate();
			wt_u.setType(type);
			wt_u.setStore_id(store_id);
		}
		// 通知店主
		type = "YYD";
		wt_d = wxTemplateMgr.getTemplate(owner, type);
		if (wt_d == null) {
			wt_d = new WxTemplate();
			wt_d.setType(type);
			wt_d.setStore_id(store_id);
		}

		setLightType(5);
		return SUCCESS;
	}

	/**
	 * 预约成功详情页
	 */
	public void findYuYueRecordById() throws Exception{
		dto = (YuYueDto) yuYueMgr.findYuYueRecordById(recordid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
	}
	
	public YuYueDto getDto() {
		return dto;
	}

	public void setDto(YuYueDto dto) {
		this.dto = dto;
	}

	public int getLightType() {
		return lightType;
	}

	public void setLightType(int lightType) {
		this.lightType = lightType;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public List<Sitegroup> getList() {
		return list;
	}

	public void setList(List<Sitegroup> list) {
		this.list = list;
	}

	public YuYue getYuYue() {
		return yuYue;
	}

	public void setYuYue(YuYue yuYue) {
		this.yuYue = yuYue;
	}

	public YuYueCatalog getYuYueCatalog() {
		return yuYueCatalog;
	}

	public void setYuYueCatalog(YuYueCatalog yuYueCatalog) {
		this.yuYueCatalog = yuYueCatalog;
	}

	public long getCatid() {
		return catid;
	}

	public void setCatid(long catid) {
		this.catid = catid;
	}

	public YuYueService getYuYueService() {
		return yuYueService;
	}

	public void setYuYueService(YuYueService yuYueService) {
		this.yuYueService = yuYueService;
	}

	public long getServiceid() {
		return serviceid;
	}

	public void setServiceid(long serviceid) {
		this.serviceid = serviceid;
	}

	public YuYueServicer getYuYueServicer() {
		return yuYueServicer;
	}

	public void setYuYueServicer(YuYueServicer yuYueServicer) {
		this.yuYueServicer = yuYueServicer;
	}

	public long getSerid() {
		return serid;
	}

	public void setSerid(long serid) {
		this.serid = serid;
	}

	public long getSsid() {
		return ssid;
	}

	public void setSsid(long ssid) {
		this.ssid = ssid;
	}

	public YuYueSSTime getYuYueSSTime() {
		return yuYueSSTime;
	}

	public void setYuYueSSTime(YuYueSSTime yuYueSSTime) {
		this.yuYueSSTime = yuYueSSTime;
	}

	public long getTimeid() {
		return timeid;
	}

	public void setTimeid(long timeid) {
		this.timeid = timeid;
	}

	public YuYueTag getYuYueTag() {
		return yuYueTag;
	}

	public void setYuYueTag(YuYueTag yuYueTag) {
		this.yuYueTag = yuYueTag;
	}

	public long getTagid() {
		return tagid;
	}

	public void setTagid(long tagid) {
		this.tagid = tagid;
	}

	public String getSwtype() {
		return swtype;
	}

	public void setSwtype(String swtype) {
		this.swtype = swtype;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getWeekday() {
		return weekday;
	}

	public void setWeekday(int weekday) {
		this.weekday = weekday;
	}

	public int getDateday() {
		return dateday;
	}

	public void setDateday(int dateday) {
		this.dateday = dateday;
	}

	public YuYueSS getYuYueSS() {
		return yuYueSS;
	}

	public void setYuYueSS(YuYueSS yuYueSS) {
		this.yuYueSS = yuYueSS;
	}

	public int getMoveUp() {
		return moveUp;
	}

	public void setMoveUp(int moveUp) {
		this.moveUp = moveUp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getRecordid() {
		return recordid;
	}

	public void setRecordid(long recordid) {
		this.recordid = recordid;
	}

	public Date getYytime() {
		return yytime;
	}

	public void setYytime(Date yytime) {
		this.yytime = yytime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	
	public String getStarttime()
	{
		return starttime;
	}

	
	public void setStarttime(String starttime)
	{
		this.starttime = starttime;
	}

	
	public String getEndtime()
	{
		return endtime;
	}

	
	public void setEndtime(String endtime)
	{
		this.endtime = endtime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getSiteid() {
		return siteid;
	}

	public void setSiteid(long siteid) {
		this.siteid = siteid;
	}

	public AppointmentDataModel getRecord() {
		return record;
	}

	public long getPageid() {
		return pageid;
	}

	public void setPageid(long pageid) {
		this.pageid = pageid;
	}

	public WxTemplate getWt_u() {
		return wt_u;
	}

	public void setWt_u(WxTemplate wt_u) {
		this.wt_u = wt_u;
	}

	public WxTemplate getWt_d() {
		return wt_d;
	}

	public void setWt_d(WxTemplate wt_d) {
		this.wt_d = wt_d;
	}

	public void setRecord(AppointmentDataModel record) {
		this.record = record;
	}

	public long getWxuid() {
		return wxuid;
	}

	public void setWxuid(long wxuid) {
		this.wxuid = wxuid;
	}

	public long getAptid() {
		return aptid;
	}

	public void setAptid(long aptid) {
		this.aptid = aptid;
	}

	public void setInteractAptManager(IInteractAptManager interactAptManager) {
		this.interactAptManager = interactAptManager;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	
	public String getYytimeStr()
	{
		return yytimeStr;
	}

	
	public void setYytimeStr(String yytimeStr)
	{
		this.yytimeStr = yytimeStr;
	}

}
