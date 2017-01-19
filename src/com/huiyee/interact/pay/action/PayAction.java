package com.huiyee.interact.pay.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.constants.IPrivilegeConstants;
import com.huiyee.esite.mgr.IMarketingEbMgr;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.MarketingSet;
import com.huiyee.esite.model.Sitegroup;
import com.huiyee.esite.service.Permission;
import com.huiyee.interact.pay.dto.PayDto;
import com.huiyee.interact.setting.model.HyUserDz;
import com.huiyee.interact.template.mgr.IWxTemplateMgr;
import com.huiyee.interact.template.model.WxTemplate;
import com.opensymphony.xwork2.ActionContext;

public class PayAction extends AbstractAction {

	private static final long serialVersionUID = 8078140601696061708L;

	private IMarketingEbMgr marketingEbMgr;
	private IWxTemplateMgr wxTemplateMgr;

	private int pageId = 1;
	private PayDto dto;
	private String subtype;
	private MarketingSet marketingSet;
	private long sgid;
	private List<Sitegroup> sgList;
	private HyUserDz hyUserDz;
	private long pageid;
	private WxTemplate wt_s;
	private WxTemplate wt_c;
	private int jftoprice;

	@Permission(module = IPrivilegeConstants.MODULE_APP_商城, privilege = IPrivilegeConstants.PERMISSION_R)
	public String config() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		marketingSet = marketingEbMgr.findSetting(account, subtype);

		if ("W".equals(subtype)) {
			sgList = pageCompose.findPageTofs(account, "S");
		} else if ("J".equals(subtype)) {
			sgList = pageCompose.findPageTofs(account, "F");
		}

		this.setLightType(4);
		return SUCCESS;
	}

	public String findUsedSitegroup() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		marketingSet = marketingEbMgr.findSetting(account, subtype);
		if(marketingSet!=null){
			Sitegroup sg = pageCompose.findSitegroupByPageid(marketingSet.getHomepage());
			if (sg != null)
				sgid = sg.getId();
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(sgid);
		out.flush();
		out.close();
		return null;
	}

	public String saveConfig() throws Exception {
		if (sgid == 0) {
			return null;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			Account account = (Account) ActionContext.getContext().getSession().get("account");
			int res = marketingEbMgr.updateHomepage(account, sgid, subtype);
			out.print(res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public String jifenToPrice() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			Account account = (Account) ActionContext.getContext().getSession().get("account");
			int res = marketingEbMgr.updateJifenToPrice(account, jftoprice, subtype);
			out.print(res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;

	}

	@Permission(module = IPrivilegeConstants.MODULE_APP_商城, privilege = IPrivilegeConstants.PERMISSION_R)
	public String template() throws Exception {
		if ("W".equals(subtype)) {
			wt_s = wxTemplateMgr.getTemplate(this.getOwner(), "WDS");
			if (wt_s == null) {
				wt_s = new WxTemplate();
				wt_s.setType("WDS");
				wt_s.setStore_id(1);
			}
			wt_c = wxTemplateMgr.getTemplate(this.getOwner(), "WDC");
			if (wt_c == null) {
				wt_c = new WxTemplate();
				wt_c.setType("WDC");
				wt_c.setStore_id(3);
			}
		} else if ("J".equals(subtype)) {
			wt_s = wxTemplateMgr.getTemplate(this.getOwner(), "JFS");
			if (wt_s == null) {
				wt_s = new WxTemplate();
				wt_s.setType("JFS");
				wt_s.setStore_id(2);
			}
			wt_c = wxTemplateMgr.getTemplate(this.getOwner(), "JFC");
			if (wt_c == null) {
				wt_c = new WxTemplate();
				wt_c.setType("JFC");
				wt_c.setStore_id(3);
			}
		}

		this.setLightType(5);
		return SUCCESS;
	}

	public void setMarketingEbMgr(IMarketingEbMgr marketingEbMgr) {
		this.marketingEbMgr = marketingEbMgr;
	}

	public void setWxTemplateMgr(IWxTemplateMgr wxTemplateMgr) {
		this.wxTemplateMgr = wxTemplateMgr;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public PayDto getDto() {
		return dto;
	}

	public void setDto(PayDto dto) {
		this.dto = dto;
	}

	public String getSubtype() {
		return subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	public MarketingSet getMarketingSet() {
		return marketingSet;
	}

	public void setMarketingSet(MarketingSet marketingSet) {
		this.marketingSet = marketingSet;
	}

	public List<Sitegroup> getSgList() {
		return sgList;
	}

	public void setSgList(List<Sitegroup> sgList) {
		this.sgList = sgList;
	}

	public HyUserDz getHyUserDz() {
		return hyUserDz;
	}

	public void setHyUserDz(HyUserDz hyUserDz) {
		this.hyUserDz = hyUserDz;
	}

	public long getPageid() {
		return pageid;
	}

	public void setPageid(long pageid) {
		this.pageid = pageid;
	}

	public WxTemplate getWt_s() {
		return wt_s;
	}

	public void setWt_s(WxTemplate wt_s) {
		this.wt_s = wt_s;
	}

	public WxTemplate getWt_c() {
		return wt_c;
	}

	public void setWt_c(WxTemplate wt_c) {
		this.wt_c = wt_c;
	}

	public int getJftoprice() {
		return jftoprice;
	}

	public void setJftoprice(int jftoprice) {
		this.jftoprice = jftoprice;
	}

	public long getSgid() {
		return sgid;
	}

	public void setSgid(long sgid) {
		this.sgid = sgid;
	}

}
