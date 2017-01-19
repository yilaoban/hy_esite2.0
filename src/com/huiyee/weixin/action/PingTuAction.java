package com.huiyee.weixin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.model.PayApt;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.weixin.mgr.IWxBuyProductMgr;
import com.opensymphony.xwork2.ActionContext;


public class PingTuAction extends AbstractAction 
{
	private IWxBuyProductMgr wxBuyProductMgr;
	private long hyuid;
	private long productid;
	private PayApt apt;
	
	public void setWxBuyProductMgr(IWxBuyProductMgr wxBuyProductMgr)
	{
		this.wxBuyProductMgr = wxBuyProductMgr;
	}

	public String addToShoppingCart() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		long res = 0;
		if(vu != null && vu.getWxUser() != null && apt != null){
			hyuid = vu.getHyUserId();
			apt.setHyuid(hyuid);apt.setWxuid(vu.getWxUser().getId());
			res = wxBuyProductMgr.addToShoppingCart(productid,hyuid,"EDT",apt);
		}
		out.print(res);
		out.flush();
		out.close();
		return null;
	}

	
	public long getHyuid()
	{
		return hyuid;
	}

	
	public void setHyuid(long hyuid)
	{
		this.hyuid = hyuid;
	}

	
	public long getProductid()
	{
		return productid;
	}

	
	public void setProductid(long productid)
	{
		this.productid = productid;
	}

	
	public PayApt getApt()
	{
		return apt;
	}

	
	public void setApt(PayApt apt)
	{
		this.apt = apt;
	}
	
	
}
