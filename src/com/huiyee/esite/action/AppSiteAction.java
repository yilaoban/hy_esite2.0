
package com.huiyee.esite.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.constants.IPrivilegeConstants;
import com.huiyee.esite.dto.SiteGroupDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.Sitegroup;
import com.huiyee.esite.service.Permission;
import com.huiyee.interact.cb.mgr.IInteractCbMgr;
import com.opensymphony.xwork2.ActionContext;

public class AppSiteAction extends AbstractAction
{

	private SiteGroupDto dto;
	private int pageId = 1;
	private String grouptype;
	private String subtype;// 微商城/积分商城 传递参数
	private int appid;
	private long sitegroupid;

	@Permission(module = IPrivilegeConstants.MODULE_APP_微社区, privilege = IPrivilegeConstants.PERMISSION_R)
	public String bbs() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (SiteGroupDto) pageCompose.composeSiteGroupByType(account, pageId, grouptype);
		return SUCCESS;
	}

	@Permission(module = IPrivilegeConstants.MODULE_APP_合伙人, privilege = IPrivilegeConstants.PERMISSION_R)
	public String hhr() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (SiteGroupDto) pageCompose.composeSiteGroupByType(account, pageId, grouptype);
		return SUCCESS;
	}

	@Permission(module = IPrivilegeConstants.MODULE_APP_微积分, privilege = IPrivilegeConstants.PERMISSION_R)
	public String jifen() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (SiteGroupDto) pageCompose.composeSiteGroupByType(account, pageId, grouptype);
		return SUCCESS;
	}

	@Permission(module = IPrivilegeConstants.MODULE_APP_商城, privilege = IPrivilegeConstants.PERMISSION_R)
	public String shop() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (SiteGroupDto) pageCompose.composeSiteGroupByType(account, pageId, grouptype);
		return SUCCESS;
	}

	@Permission(module = IPrivilegeConstants.MODULE_APP_线下签到, privilege = IPrivilegeConstants.PERMISSION_R)
	public String offcheck() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (SiteGroupDto) pageCompose.composeSiteGroupByType(account, pageId, grouptype);
		return SUCCESS;
	}

	@Permission(module = IPrivilegeConstants.MODULE_APP_微预约, privilege = IPrivilegeConstants.PERMISSION_R)
	public String yuyue() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (SiteGroupDto) pageCompose.composeSiteGroupByType(account, pageId, grouptype);
		return SUCCESS;
	}

	@Permission(module = IPrivilegeConstants.MODULE_APP_潜客管理, privilege = IPrivilegeConstants.PERMISSION_R)
	public String qianke() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (SiteGroupDto) pageCompose.composeSiteGroupByType(account, pageId, grouptype);
		return SUCCESS;
	}

	@Permission(module = IPrivilegeConstants.MODULE_APP_用户分析, privilege = IPrivilegeConstants.PERMISSION_R)
	public String user() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (SiteGroupDto) pageCompose.composeSiteGroupByType(account, pageId, grouptype);
		return SUCCESS;
	}

	@Permission(module = IPrivilegeConstants.MODULE_APP_站内搜索, privilege = IPrivilegeConstants.PERMISSION_R)
	public String search() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (SiteGroupDto) pageCompose.composeSiteGroupByType(account, pageId, grouptype);
		return SUCCESS;
	}

	@Permission(module = IPrivilegeConstants.MODULE_APP_微投放, privilege = IPrivilegeConstants.PERMISSION_R)
	public String ad() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (SiteGroupDto) pageCompose.composeSiteGroupByType(account, pageId, grouptype);
		return SUCCESS;
	}

	@Permission(module = IPrivilegeConstants.MODULE_APP_服务评价, privilege = IPrivilegeConstants.PERMISSION_R)
	public String servicerpj() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (SiteGroupDto) pageCompose.composeSiteGroupByType(account, pageId, grouptype);
		return SUCCESS;
	}

	public String findUsedSiteGroup() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		long sgid = pageCompose.findAppUsedSiteGroup(account.getOwner().getId(), appid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(sgid);
		out.flush();
		out.close();
		return null;
	}

	public String updateUsedSiteGroup() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		int rs = pageCompose.updateUsedSiteGroup(account.getOwner().getId(), sitegroupid,appid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(rs);
		out.flush();
		out.close();
		return null;
	}

	public SiteGroupDto getDto()
	{
		return dto;
	}

	public void setDto(SiteGroupDto dto)
	{
		this.dto = dto;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public String getGrouptype()
	{
		return grouptype;
	}

	public void setGrouptype(String grouptype)
	{
		this.grouptype = grouptype;
	}

	public String getSubtype()
	{
		return subtype;
	}

	public void setSubtype(String subtype)
	{
		this.subtype = subtype;
	}

	public int getAppid()
	{
		return appid;
	}

	public void setAppid(int appid)
	{
		this.appid = appid;
	}

	public long getSitegroupid()
	{
		return sitegroupid;
	}

	public void setSitegroupid(long sitegroupid)
	{
		this.sitegroupid = sitegroupid;
	}

}
