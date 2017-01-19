
package com.huiyee.esite.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.dto.PageShowMaterialDto;
import com.huiyee.esite.mgr.IPageShowMaterialManager;
import com.huiyee.esite.mgr.ISiteManager;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.ContentNew;
import com.huiyee.esite.model.Sitegroup;
import com.opensymphony.xwork2.ActionContext;

public class PageShowMaterialAction extends AbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9042575753888188406L;
	private PageShowMaterialDto dto;
	private IPageShowMaterialManager pageShowMaterialManager;
	private ISiteManager siteManager;
	private int pageId = 1;
	private long aid;
	private long cbid;

	private long id;// es_wx_page_show 表的id
	private String actioned;
	private long pageid;
	private long amid;
	private int lightType = 5;
	private String title;

	private long ccid;// content_category id
	private long newsid;// 新闻ID

	public void setSiteManager(ISiteManager siteManager)
	{
		this.siteManager = siteManager;
	}

	public void setPageShowMaterialManager(IPageShowMaterialManager pageShowMaterialManager)
	{
		this.pageShowMaterialManager = pageShowMaterialManager;
	}

	/**
	 * 素材列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findMaterial() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		long ownerid = account.getOwner().getId();
		dto = (PageShowMaterialDto) pageCompose.findPageShowMaterialByOwnerid(ownerid,pageId,title);
		return SUCCESS;
	}

	/**
	 * 新闻物料
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findNewsMaterial() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		long ownerid = account.getOwner().getId();
		dto = (PageShowMaterialDto) pageCompose.findNewsMaterial(ownerid, ccid, pageId);
		return SUCCESS;
	}

	/**
	 * 设置素材
	 * 
	 * @return
	 * @throws Exception
	 */
	public String setMaterial() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String result = "N";
		// int res = pageCompose.updatePageShowActioned(id,actioned);
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		long ownerid = account.getOwner().getId();
		Sitegroup sg = siteManager.findSitegroupByPageid(pageid);
		if (sg != null)
		{
			int res = pageShowMaterialManager.saveCbActivityMatter(cbid, aid, pageid, id, ownerid, sg.getStype());
			if (res > 0)
			{
				result = "Y";
			}
		}
		out.print(result);
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 设置新闻素材
	 * 
	 * @return
	 * @throws Exception
	 */
	public String setNewsMaterial() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String result = "N";
		// int res = pageCompose.updatePageShowActioned(id,actioned);
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		long ownerid = account.getOwner().getId();
		if (newsid > 0)
		{
			int res = pageShowMaterialManager.saveNewsMatter(cbid, aid,newsid, ownerid);
			if (res > 0)
			{
				result = "Y";
			}
		}
		out.print(result);
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 活动素材
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showCbActivityDetail() throws Exception
	{
		dto = (PageShowMaterialDto) pageCompose.findWxPageShowListByAid(cbid, aid, pageId);
		return SUCCESS;
	}

	/**
	 * 删除素材 （关系表）
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delCbActivityMatter() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String result = "N";
		int res = pageShowMaterialManager.updateInteractCbActivityMatterById(amid);
		if (res > 0)
		{
			result = "Y";
		}
		out.print(result);
		out.flush();
		out.close();
		return null;

	}

	public PageShowMaterialDto getDto()
	{
		return dto;
	}

	public void setDto(PageShowMaterialDto dto)
	{
		this.dto = dto;
	}

	public long getAid()
	{
		return aid;
	}

	public void setAid(long aid)
	{
		this.aid = aid;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public long getCbid()
	{
		return cbid;
	}

	public void setCbid(long cbid)
	{
		this.cbid = cbid;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getActioned()
	{
		return actioned;
	}

	public void setActioned(String actioned)
	{
		this.actioned = actioned;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public long getAmid()
	{
		return amid;
	}

	public void setAmid(long amid)
	{
		this.amid = amid;
	}

	public int getLightType()
	{
		return lightType;
	}

	public void setLightType(int lightType)
	{
		this.lightType = lightType;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public long getCcid()
	{
		return ccid;
	}

	public void setCcid(long ccid)
	{
		this.ccid = ccid;
	}

	public long getNewsid()
	{
		return newsid;
	}

	public void setNewsid(long newsid)
	{
		this.newsid = newsid;
	}

}
