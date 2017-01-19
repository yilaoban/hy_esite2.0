package com.huiyee.esite.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.dto.SitePage;
import com.huiyee.esite.dto.WeiKaQuanDto;
import com.huiyee.esite.mgr.ISiteManager;
import com.huiyee.esite.mgr.IWeiKaQuanMgr;
import com.huiyee.esite.mgr.IWxUserMgr;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.Site;
import com.huiyee.weixin.model.Wkq;


public class WeiKaQuanSetAction extends AbstractAction
{
	private IWeiKaQuanMgr wkqMgr;
	private IWxUserMgr wxUserMgr;
	private ISiteManager siteManager;
	private WeiKaQuanDto dto;
	private int tool = 1;
	private int pageId = 1;
	private String nickname;
	private long wxuid;
	private long productid;
	private List<Site> siteList;
	private long siteid;
	private long pageid;
	private Page page;
	public void setSiteManager(ISiteManager siteManager)
	{
		this.siteManager = siteManager;
	}

	public void setWxUserMgr(IWxUserMgr wxUserMgr)
	{
		this.wxUserMgr = wxUserMgr;
	}

	public void setWkqMgr(IWeiKaQuanMgr wkqMgr)
	{
		this.wkqMgr = wkqMgr;
	}
	
	@Override
	public String execute() throws Exception
	{
		dto = (WeiKaQuanDto) wkqMgr.findWKQShopOwnerByOwner(this.getOwner(),pageId);
		return SUCCESS;
	}
	
	public String wxUser() throws Exception{
		dto = (WeiKaQuanDto) wxUserMgr.findWxUserByOwner(this.getOwner(),pageId,nickname);
		return SUCCESS;
	}
	
	
	public String setShopOwner() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int res = wkqMgr.saveWkqShopOwner(wxuid,this.getOwner());
		out.print(res);
		out.flush();
		out.close();
		return null;
	}
	
	public String delShopOwner() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int res = wkqMgr.delShopOwner(wxuid,this.getOwner());
		out.print(res);
		out.flush();
		out.close();
		return null;
	}
	
	
	public String weiKaQuanList() throws Exception{
		dto = (WeiKaQuanDto)wkqMgr.findContentProductList(this.getOwner(),pageId);
		return SUCCESS;
	}
	
	public String payOrderList() throws Exception{
		dto = (WeiKaQuanDto)wkqMgr.findPayOrderListByProductid(productid,pageId);
		return SUCCESS;
	}
	
	public String findSiteListByOwner() throws Exception{
		siteList = siteManager.findSiteListByOwnerid(this.getOwner());
		page = wkqMgr.findWKQByOwner(this.getOwner());
		return SUCCESS;
	}
	
	public String ajax_get_page() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("text/html; charset=utf-8");
		List<SitePage> pageList = siteManager.findPageBySiteId(siteid);
		JSONArray arr = new JSONArray();
		for(SitePage a : pageList){
			JSONObject json = new JSONObject();
			json.put("pageid", a.getPageid());
			json.put("name", a.getPagename());
			arr.add(json);
		}
		PrintWriter pw = response.getWriter();
		pw.print(arr.toString());
		pw.flush();
		pw.close();
		return null;
	}
	
	public String saveWKQ() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int res = wkqMgr.saveWKQ(pageid,this.getOwner());
		out.print(res);
		out.flush();
		out.close();
		return null;
	}
	
	public WeiKaQuanDto getDto()
	{
		return dto;
	}

	
	public void setDto(WeiKaQuanDto dto)
	{
		this.dto = dto;
	}

	
	public int getTool()
	{
		return tool;
	}

	
	public void setTool(int tool)
	{
		this.tool = tool;
	}

	
	public int getPageId()
	{
		return pageId;
	}

	
	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	
	public String getNickname()
	{
		return nickname;
	}

	
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	
	public long getWxuid()
	{
		return wxuid;
	}

	
	public void setWxuid(long wxuid)
	{
		this.wxuid = wxuid;
	}

	
	public long getProductid()
	{
		return productid;
	}

	
	public void setProductid(long productid)
	{
		this.productid = productid;
	}

	
	public List<Site> getSiteList()
	{
		return siteList;
	}

	
	public void setSiteList(List<Site> siteList)
	{
		this.siteList = siteList;
	}

	
	public long getSiteid()
	{
		return siteid;
	}

	
	public void setSiteid(long siteid)
	{
		this.siteid = siteid;
	}

	
	public long getPageid()
	{
		return pageid;
	}

	
	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	
	public Page getPage()
	{
		return page;
	}

	
	public void setPage(Page page)
	{
		this.page = page;
	}

	
	
}
