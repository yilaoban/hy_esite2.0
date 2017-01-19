package com.huiyee.interact.ad.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.constants.IPrivilegeConstants;
import com.huiyee.esite.service.Permission;
import com.huiyee.interact.ad.dto.AdGGDto;
import com.huiyee.interact.ad.mgr.IAdGGMgr;
import com.huiyee.interact.ad.model.Adgg;


public class AdGGAction extends AbstractAction
{
	private static final long serialVersionUID = -9211368138073008413L;
	private IAdGGMgr adGGMgr;
	private AdGGDto dto;
	private int pageId = 1;
	private int lightType = 2;
	private long ggid;
	private Adgg adgg;
	
	public void setAdGGMgr(IAdGGMgr adGGMgr)
	{
		this.adGGMgr = adGGMgr;
	}
	
	@Override
	@Permission(module=IPrivilegeConstants.MODULE_APP_Î¢Í¶·Å,privilege=IPrivilegeConstants.PERMISSION_R)
	public String execute() throws Exception
	{
		dto = (AdGGDto) adGGMgr.findAdGGListByOwner(this.getOwner(),pageId);
		return SUCCESS;
	}
	
	public String addGG() throws Exception{
		return SUCCESS;
	}
	
	public String saveGG() throws Exception{
		adgg.setOwner(this.getOwner());
		adGGMgr.saveGG(adgg);
		return SUCCESS;
	}
	
	public String editGG() throws Exception{
		adgg = adGGMgr.findadGGById(ggid);
		return SUCCESS;
	}
	
	public String updateGG() throws Exception{
		adgg.setOwner(this.getOwner());
		adGGMgr.updateGG(adgg);
		return SUCCESS;
	}
	
	public String delGG() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		long result = adGGMgr.delGGById(ggid,this.getOwner());
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}
	
	public AdGGDto getDto()
	{
		return dto;
	}
	
	public void setDto(AdGGDto dto)
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

	public int getLightType()
	{
		return lightType;
	}

	
	public void setLightType(int lightType)
	{
		this.lightType = lightType;
	}

	
	public long getGgid()
	{
		return ggid;
	}

	
	public void setGgid(long ggid)
	{
		this.ggid = ggid;
	}

	
	public Adgg getAdgg()
	{
		return adgg;
	}

	
	public void setAdgg(Adgg adgg)
	{
		this.adgg = adgg;
	}
	
	
}
