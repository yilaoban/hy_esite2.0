
package com.huiyee.interact.offcheck.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.constants.IPrivilegeConstants;
import com.huiyee.esite.mgr.IFeatureManager;
import com.huiyee.esite.mgr.IGzEventMgr;
import com.huiyee.esite.mgr.ISiteManager;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.Sitegroup;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.model.WxGzEvent;
import com.huiyee.esite.service.Permission;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.interact.offcheck.dto.CheckRs;
import com.huiyee.interact.offcheck.dto.OffCheckDto;
import com.huiyee.interact.offcheck.mgr.IOffCheckDzWayMgr;
import com.huiyee.interact.offcheck.mgr.IOffCheckMgr;
import com.huiyee.interact.offcheck.model.OffCheck;
import com.huiyee.interact.offcheck.model.OffCheckSource;
import com.huiyee.interact.setting.mgr.IHyUserLevelMgr;
import com.huiyee.interact.template.mgr.IWxTemplateMgr;
import com.huiyee.interact.template.model.WxTemplate;
import com.opensymphony.xwork2.ActionContext;

public class OffCheckAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private IFeatureManager featureManager;
	private ISiteManager siteManager;
	private IOffCheckMgr offCheckMgr;
	private IGzEventMgr gzEventMgr;
	private IWxTemplateMgr wxTemplateMgr;
	private IHyUserLevelMgr hyUserLevelMgr;

	private long scrmid;
	private OffCheck storeCrm;
	private List<Sitegroup> list;
	private OffCheckDto dto;
	private int pageId = 1;
	private long sourceid;
	private OffCheckSource ofcSource;
	private JSONObject sift = new JSONObject();
	private long wxuid;
	private String refurl;
	private String hyerr;
	private WxGzEvent gzEvent;
	private WxTemplate wt_f;
	private WxTemplate wt_t;
	private IOffCheckDzWayMgr offCheckDzWayMgr;
	private int rmb;// 消费的rmb，单位分。

	
	public void setHyUserLevelMgr(IHyUserLevelMgr hyUserLevelMgr)
	{
		this.hyUserLevelMgr = hyUserLevelMgr;
	}

	public void setRmb(int rmb) {
		this.rmb = rmb;
	}

	public void setOffCheckDzWayMgr(IOffCheckDzWayMgr offCheckDzWayMgr) {
		this.offCheckDzWayMgr = offCheckDzWayMgr;
	}

	public void setSiteManager(ISiteManager siteManager) {
		this.siteManager = siteManager;
	}

	public void setFeatureManager(IFeatureManager featureManager) {
		this.featureManager = featureManager;
	}

	public String getHyerr() {
		return hyerr;
	}

	public String getRefurl() {
		return refurl;
	}

	public void setRefurl(String refurl) {
		this.refurl = refurl;
	}

	/**
	 * 门店后台首页 已开通时显示用户管理 未开通是显示开通按钮
	 */
	@Permission(module = IPrivilegeConstants.MODULE_APP_线下签到, privilege = IPrivilegeConstants.PERMISSION_R)
	public String index() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		storeCrm = offCheckMgr.findStoreCrmByOwner(account.getOwner().getId());
		if (storeCrm != null)
			return "toSource";
		else
			return SUCCESS;
	}

	/**
	 * 开通门店CRM 创建门店实例和申请实例
	 * 
	 * @return
	 * @throws Exception
	 */
	public String open() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		long id = offCheckMgr.saveStoreCrm(account);
		if (id > 0) {
			List<Sitegroup> sg = siteManager.findAllTempSiteGroup(IPageConstants.SITE_GROUP_A);
			if (sg.size() > 0) {
				long groupid = featureManager.addOffCheckSite(account.getOwner(), "线下签到", IPageConstants.SITE_GROUP_A, sg.get(0).getId());
				if (groupid > 0)
					pageCompose.updateOnlineByGroup(groupid, IPageConstants.SITE_GROUP_A);
			}
		}
		return SUCCESS;
	}

	/**
	 * 申请配置
	 * 
	 * @return
	 * @throws Exception
	 */
	@Permission(module = IPrivilegeConstants.MODULE_APP_线下签到, privilege = IPrivilegeConstants.PERMISSION_R)
	public String aptIndex() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		storeCrm = offCheckMgr.findStoreCrmByOwner(account.getOwner().getId());
		return SUCCESS;
	}

	/**
	 * 申请配置
	 * 
	 * @return
	 * @throws Exception
	 */
	public String aptSub() throws Exception {
		return SUCCESS;
	}

	/**
	 * 展示页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String baseAppSite() throws Exception {
		return SUCCESS;
	}

	/**
	 * 线下签到来源
	 * 
	 * @return
	 * @throws Exception
	 */
	@Permission(module = IPrivilegeConstants.MODULE_APP_线下签到, privilege = IPrivilegeConstants.PERMISSION_R)
	public String checkSource() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (OffCheckDto) offCheckMgr.findOffCheckSource(account, pageId);
		setLightType(2);
		return SUCCESS;
	}

	public String addSourceIndex() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		list = pageCompose.findPageTofs(account, "A");
		setLightType(2);
		return SUCCESS;
	}

	public String addSourceSub() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		if ("Y".equals(ofcSource.getFensi())) {
			long gzeid = gzEventMgr.updateMsg(0, gzEvent.getLink(), gzEvent.getMsg(), account.getOwner().getId());
			ofcSource.setGzeid(gzeid);
		}
		offCheckMgr.saveOffCheckSource(account, ofcSource);
		return SUCCESS;
	}

	public String editSourceIndex() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		ofcSource = offCheckMgr.findOffCheckSourceById(sourceid, account.getOwner().getId());
		if (ofcSource != null) {
			gzEvent = gzEventMgr.findGzEvent(ofcSource.getGzeid());
		}
		list = pageCompose.findPageTofs(account, "A");
		setLightType(2);
		return SUCCESS;
	}

	public String editSourceSub() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		if ("Y".equals(ofcSource.getFensi())) {
			long gzeid = gzEventMgr.updateMsg(gzEvent.getId(), gzEvent.getLink(), gzEvent.getMsg(), account.getOwner().getId());
			ofcSource.setGzeid(gzeid);
		} else {
			ofcSource.setGzeid(gzEvent.getId());
		}
		offCheckMgr.updateOffCheckSource(account, ofcSource);
		setLightType(2);
		return SUCCESS;
	}

	public String template() throws Exception {
		// 服务评价通知
		long owner = this.getOwner();
		String type = "OCF";
		long store_id = 3;
		wt_f = wxTemplateMgr.getTemplate(owner, type, sourceid);
		if (wt_f == null) {
			wt_f = new WxTemplate();
			wt_f.setType(type);
			wt_f.setStore_id(store_id);
		}

		// 到店提醒通知
		type = "OCT";
		wt_t = wxTemplateMgr.getTemplate(owner, type, sourceid);
		if (wt_t == null) {
			wt_t = new WxTemplate();
			wt_t.setType(type);
			wt_t.setStore_id(store_id);
		}

		setLightType(2);
		return SUCCESS;
	}

	public String delSource() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		long result = offCheckMgr.delSource(sourceid, account.getOwner().getId());
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	@Permission(module = IPrivilegeConstants.MODULE_APP_线下签到, privilege = IPrivilegeConstants.PERMISSION_R)
	public String recordList() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (OffCheckDto) offCheckMgr.findRecordList(account, pageId, sift);
		setLightType(3);
		return SUCCESS;
	}

	@Permission(module = IPrivilegeConstants.MODULE_APP_线下签到, privilege = IPrivilegeConstants.PERMISSION_R)
	public String offCheckLog() throws Exception {

		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (OffCheckDto) offCheckMgr.findRecordList(account, sourceid, wxuid, pageId);
		return SUCCESS;

	}

	@Permission(module = IPrivilegeConstants.MODULE_APP_线下签到, privilege = IPrivilegeConstants.PERMISSION_R)
	public String offCheckLogs() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (OffCheckDto) offCheckMgr.findOffCheckLogs(account, pageId, sift);
		setLightType(5);
		return SUCCESS;
	}

	/**
	 * 普通签到
	 * 
	 * @return
	 * @throws Exception
	 */
	public String checking() throws Exception {
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		if (HyConfig.isRun()) {
			CheckRs rs = new CheckRs();
			rs.setStatus(1);
			Gson gson = new Gson();
			out.print(gson.toJson(rs));
			out.flush();
			out.close();
			return null;
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = ClientUserIp.getIpAddr(request);
		try {
			sourceid = Long.valueOf(vu.getKv());
		} catch (Exception e) {
			CheckRs rs = new CheckRs();
			rs.setStatus(-10000);
			rs.setHydesc("参数不正确！");
			Gson gson = new Gson();
			out.print(gson.toJson(rs));
			out.flush();
			out.close();
			return null;
		}
		CheckRs rs = offCheckMgr.updateChecking(sourceid, vu, ip, this.getOwner());

		Gson gson = new Gson();
		out.print(gson.toJson(rs));
		out.flush();
		out.close();
		return null;

	}

	/**
	 * 动态签到，带有消费积分
	 * 
	 * @return
	 * @throws Exception
	 */
	public String dtchecking() throws Exception {

		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		if (HyConfig.isRun()) {
			CheckRs rs = new CheckRs();
			rs.setStatus(1);
			Gson gson = new Gson();
			out.print(gson.toJson(rs));
			out.flush();
			out.close();
			return null;
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = ClientUserIp.getIpAddr(request);
		long dzway = 0;
		try {
			dzway = Long.valueOf(vu.getKv());
		} catch (Exception e) {
			CheckRs rs = new CheckRs();
			rs.setStatus(-10000);
			rs.setHydesc("参数不正确！");
			Gson gson = new Gson();
			out.print(gson.toJson(rs));
			out.flush();
			out.close();
			return null;
		}
		CheckRs rs = offCheckMgr.updateDtChecking(dzway, vu, ip, this.getOwner());
		hyUserLevelMgr.updateUserLevel(vu,rs.getRmb());//修改用户等级
		Gson gson = new Gson();
		out.print(gson.toJson(rs));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 获取动态二维码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String dzway() throws Exception {
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		if (HyConfig.isRun()) {
			CheckRs rs = new CheckRs();
			rs.setStatus(-10004);
			rs.setHydesc("请用前台地址！");
			Gson gson = new Gson();
			out.print(gson.toJson(rs));
			out.flush();
			out.close();
			return null;
		}
		try {
			sourceid = Long.valueOf(vu.getKv());
		} catch (Exception e) {
			CheckRs rs = new CheckRs();
			rs.setStatus(-10000);
			rs.setHydesc("参数不正确！");
			Gson gson = new Gson();
			out.print(gson.toJson(rs));
			out.flush();
			out.close();
			return null;
		}
		CheckRs rs = offCheckDzWayMgr.updateDzWay(vu, rmb*100, this.getOwner(), sourceid);
		Gson gson = new Gson();
		out.print(gson.toJson(rs));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 消费积分收营员页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String dzsource() throws Exception {
		if (sourceid == 0) {
			return null;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			OffCheckSource ocs = offCheckMgr.findOffCheckSourceById(sourceid, this.getOwner());
			out.print(new Gson().toJson(ocs));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public String offCheckJf() throws Exception {
		long owner = this.getOwner();
		wt_f = wxTemplateMgr.getTemplate(owner, "XFJ", sourceid);
		if (wt_f == null) {
			wt_f = new WxTemplate();
			wt_f.setType("XFJ");
			wt_f.setStore_id(4);
		}

		setLightType(2);
		return SUCCESS;
	}

	public void setOffCheckMgr(IOffCheckMgr offCheckMgr) {
		this.offCheckMgr = offCheckMgr;
	}

	public void setGzEventMgr(IGzEventMgr gzEventMgr) {
		this.gzEventMgr = gzEventMgr;
	}

	public void setWxTemplateMgr(IWxTemplateMgr wxTemplateMgr) {
		this.wxTemplateMgr = wxTemplateMgr;
	}

	public long getScrmid() {
		return scrmid;
	}

	public void setScrmid(long scrmid) {
		this.scrmid = scrmid;
	}

	public OffCheck getStoreCrm() {
		return storeCrm;
	}

	public void setStoreCrm(OffCheck storeCrm) {
		this.storeCrm = storeCrm;
	}

	public List<Sitegroup> getList() {
		return list;
	}

	public void setList(List<Sitegroup> list) {
		this.list = list;
	}

	public OffCheckDto getDto() {
		return dto;
	}

	public void setDto(OffCheckDto dto) {
		this.dto = dto;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public long getSourceid() {
		return sourceid;
	}

	public void setSourceid(long sourceid) {
		this.sourceid = sourceid;
	}

	public OffCheckSource getOfcSource() {
		return ofcSource;
	}

	public void setOfcSource(OffCheckSource ofcSource) {
		this.ofcSource = ofcSource;
	}

	public JSONObject getSift() {
		return sift;
	}

	public void setSift(JSONObject sift) {
		this.sift = sift;
	}

	public long getWxuid() {
		return wxuid;
	}

	public void setWxuid(long wxuid) {
		this.wxuid = wxuid;
	}

	public WxGzEvent getGzEvent() {
		return gzEvent;
	}

	public void setGzEvent(WxGzEvent gzEvent) {
		this.gzEvent = gzEvent;
	}

	public WxTemplate getWt_f() {
		return wt_f;
	}

	public void setWt_f(WxTemplate wt_f) {
		this.wt_f = wt_f;
	}

	public WxTemplate getWt_t() {
		return wt_t;
	}

	public void setWt_t(WxTemplate wt_t) {
		this.wt_t = wt_t;
	}

}
