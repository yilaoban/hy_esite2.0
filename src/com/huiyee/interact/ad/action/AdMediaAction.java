
package com.huiyee.interact.ad.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.constants.IPrivilegeConstants;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.AreaProvince;
import com.huiyee.esite.model.Sitegroup;
import com.huiyee.esite.service.Permission;
import com.huiyee.interact.ad.dto.AdMediaDto;
import com.huiyee.interact.ad.mgr.IAdMediaMgr;
import com.huiyee.interact.ad.model.AdMedia;
import com.huiyee.interact.ad.model.AdWay;
import com.opensymphony.xwork2.ActionContext;

public class AdMediaAction extends AbstractAction
{

	private static final long serialVersionUID = 9095995876420771999L;
	private IAdMediaMgr adMediaMgr;
	private AdMediaDto dto;
	private int lightType = 3;
	private int pageId = 1;
	private AdMedia media;
	private long mid;
	private long wayid;
	private List<Sitegroup> list;
	private String area;
	private String wd;// 版本
	private String qwd;// 期号
	private AdWay adway;
	private String groupName;

	public void setAdMediaMgr(IAdMediaMgr adMediaMgr)
	{
		this.adMediaMgr = adMediaMgr;
	}

	@Override
	@Permission(module = IPrivilegeConstants.MODULE_APP_微投放, privilege = IPrivilegeConstants.PERMISSION_R)
	public String execute() throws Exception
	{
		dto = (AdMediaDto) adMediaMgr.findAdMediaList(this.getOwner(), pageId);
		return SUCCESS;
	}

	public String addMedia() throws Exception
	{
		return SUCCESS;
	}

	public String savedMedia() throws Exception
	{
		media.setOwner(this.getOwner());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("json/application;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String msg;
		try
		{
			int rs = adMediaMgr.savedMedia(media, area);
			msg = String.valueOf(rs);
		} catch (Exception e)
		{
			msg = e.getMessage();
		}
		out.print(msg);
		out.flush();
		out.close();
		return null;
	}

	public String editMedia() throws Exception
	{
		media = adMediaMgr.findAdMediaById(mid);
		return SUCCESS;
	}

	public String updateMedia() throws Exception
	{
		media.setOwner(this.getOwner());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("json/application;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String msg;
		try
		{
			int rs = adMediaMgr.updateMedia(media, area);
			msg = String.valueOf(rs);
		} catch (Exception e)
		{
			msg = e.getMessage();
		}
		out.print(msg);
		out.flush();
		out.close();
		return null;

	}

	public String delMedia() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		long result = adMediaMgr.delMediaById(mid, this.getOwner());
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	public String mediaWay() throws Exception
	{
		dto = (AdMediaDto) adMediaMgr.findMediaWayList(mid, qwd, wd, pageId);
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		list = pageCompose.findGroupTofs(account, IPageConstants.SITE_GROUP_D);
		return SUCCESS;
	}

	public String delWay() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		long result = adMediaMgr.delWayById(wayid, this.getOwner());
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	public String showAdWay() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		list = pageCompose.findGroupTofs(account, IPageConstants.SITE_GROUP_D);
		adway = adMediaMgr.findAdWayById(wayid, this.getOwner());
		groupName = adMediaMgr.findgroupnameByPageId(adway.getPageid());
		return SUCCESS;
	}

	public String showMediaWay() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		list = pageCompose.findGroupTofs(account, IPageConstants.SITE_GROUP_D);
		adway = adMediaMgr.findAdWayById(wayid, this.getOwner());
		groupName = adMediaMgr.findgroupnameByPageId(adway.getPageid());
		return SUCCESS;
	}

	public int getLightType()
	{
		return lightType;
	}

	public void setLightType(int lightType)
	{
		this.lightType = lightType;
	}

	public AdMediaDto getDto()
	{
		return dto;
	}

	public void setDto(AdMediaDto dto)
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

	public AdMedia getMedia()
	{
		return media;
	}

	public void setMedia(AdMedia media)
	{
		this.media = media;
	}

	public long getMid()
	{
		return mid;
	}

	public void setMid(long mid)
	{
		this.mid = mid;
	}

	public long getWayid()
	{
		return wayid;
	}

	public void setWayid(long wayid)
	{
		this.wayid = wayid;
	}

	public List<Sitegroup> getList()
	{
		return list;
	}

	public void setList(List<Sitegroup> list)
	{
		this.list = list;
	}

	public String getArea()
	{
		return area;
	}

	public void setArea(String area)
	{
		this.area = area;
	}

	public String getWd()
	{
		return wd;
	}

	public void setWd(String wd)
	{
		this.wd = wd;
	}

	public String getQwd()
	{
		return qwd;
	}

	public void setQwd(String qwd)
	{
		this.qwd = qwd;
	}

	public AdWay getAdway()
	{
		return adway;
	}

	public void setAdway(AdWay adway)
	{
		this.adway = adway;
	}

	public String getGroupName()
	{
		return groupName;
	}

	public void setGroupName(String groupName)
	{
		this.groupName = groupName;
	}

}
