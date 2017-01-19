
package com.huiyee.esite.action;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.constants.IPrivilegeConstants;
import com.huiyee.esite.dto.MarketingOrderDto;
import com.huiyee.esite.mgr.IMarketingEbMgr;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.service.Permission;
import com.opensymphony.xwork2.ActionContext;

public class MarketingOrderAction extends AbstractAction
{

	private IMarketingEbMgr marketingEbMgr;
	private int pageId = 1;
	private MarketingOrderDto dto;
	private JSONObject sift;
	private String subtype;
	private String express;
	private long orderid;
	private Date startTime;
	private Date endTime;
	private String name;
	
	@Permission(module=IPrivilegeConstants.MODULE_APP_илЁг,privilege=IPrivilegeConstants.PERMISSION_R)
	public String execute() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		if (sift == null)
		{
			sift = new JSONObject();
		}
		dto = (MarketingOrderDto) marketingEbMgr.findOrderList(pageId, account, sift, subtype,startTime,endTime);
		this.setLightType(3);
		return SUCCESS;
	}

	public String saveExpress() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int rs = marketingEbMgr.updateOrderExpress(orderid, express);
		Gson gson = new Gson();
		out.print(gson.toJson(rs));
		out.flush();
		out.close();
		return null;
	}
	
	public String exportOrderList() throws Exception{
		
		return null;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public MarketingOrderDto getDto()
	{
		return dto;
	}

	public void setDto(MarketingOrderDto dto)
	{
		this.dto = dto;
	}

	public void setMarketingEbMgr(IMarketingEbMgr marketingEbMgr)
	{
		this.marketingEbMgr = marketingEbMgr;
	}

	public int getTool()
	{
		return 2;
	}

	public JSONObject getSift()
	{
		return sift;
	}

	public void setSift(JSONObject sift)
	{
		this.sift = sift;
	}

	public String getSubtype()
	{
		return subtype;
	}

	public void setSubtype(String subtype)
	{
		this.subtype = subtype;
	}

	public String fenToyuan(int num)
	{
		return String.valueOf((float) num / 100);
	}

	public String getExpress()
	{
		return express;
	}

	public void setExpress(String express)
	{
		this.express = express;
	}

	
	public long getOrderid()
	{
		return orderid;
	}

	
	public void setOrderid(long orderid)
	{
		this.orderid = orderid;
	}

	
	public Date getStartTime()
	{
		return startTime;
	}

	
	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}

	
	public Date getEndTime()
	{
		return endTime;
	}

	
	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}

	
	public String getName()
	{
		return name;
	}

	
	public void setName(String name)
	{
		this.name = name;
	}

}
