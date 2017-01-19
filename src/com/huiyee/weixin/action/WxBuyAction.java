package com.huiyee.weixin.action;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.mgr.IAreaManager;
import com.huiyee.esite.mgr.IWeiXinMgr;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.weixin.dto.OrderInfoDto;
import com.huiyee.weixin.mgr.IWxBuyProductMgr;
import com.huiyee.weixin.model.PayOrder;
import com.opensymphony.xwork2.ActionContext;


public class WxBuyAction extends AbstractAction 
{
	private long[] id;//购物车id
	private OrderInfoDto dto;
	private IWxBuyProductMgr wxBuyProductMgr;
	private IWeiXinMgr weiXinMgr;
	private IAreaManager areaMgr;
	private long aid;//收货地址id
	private int usejf;//使用抵扣积分
	private long payOrderid;//订单表的id
	private PayOrder payOrder;
	private String type;//积分J 微商城W
	
	public String getType()
	{
		return type;
	}

	
	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * 订单支付
	 * @return
	 * @throws Exception
	 */
	public String payOrder() throws Exception{
		payOrder = wxBuyProductMgr.payOrderInfo(payOrderid);
		if(payOrder != null){
			if(payOrder.getSubtype() == 0){
				return "jf";
			}else{
				return SUCCESS;
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 积分订单支付扣除积分
	 * @return
	 * @throws Exception
	 */
	public String payMoney() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String ip = ClientUserIp.getIpAddr(request);
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if(vu != null){
			String url = HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl() + "/user/payJfHome.action";
			dto = wxBuyProductMgr.savePayMoney(payOrderid,vu.getHyUserId(),this.getOwner(),vu,url,ip);
		}
		Gson gson = new Gson();
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}
	
	
	public long getAid()
	{
		return aid;
	}
	
	public void setAid(long aid)
	{
		this.aid = aid;
	}
	
	public int getUsejf()
	{
		return usejf;
	}
	
	public void setUsejf(int usejf)
	{
		this.usejf = usejf;
	}

	public long[] getId()
	{
		return id;
	}
	
	public void setId(long[] id)
	{
		this.id = id;
	}
	
	public OrderInfoDto getDto()
	{
		return dto;
	}
	
	public void setDto(OrderInfoDto dto)
	{
		this.dto = dto;
	}

	public void setWeiXinMgr(IWeiXinMgr weiXinMgr)
	{
		this.weiXinMgr = weiXinMgr;
	}

	public void setAreaMgr(IAreaManager areaMgr)
	{
		this.areaMgr = areaMgr;
	}

	public void setWxBuyProductMgr(IWxBuyProductMgr wxBuyProductMgr)
	{
		this.wxBuyProductMgr = wxBuyProductMgr;
	}

	/**
	 * 确认订单
	 */
	@Override
	public String execute() throws Exception{
	
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if(vu != null && id != null && id.length > 0){
			dto = wxBuyProductMgr.findProductAndAddressInfo(id,aid,vu.getHyUserId(),this.getOwner(),vu.getHyUser().getLevelid());
			if(dto ==null || id.length != dto.getShopCartList().size()){
				//购物车数量不对，则认为过期
				return "outtime";
			}
			return SUCCESS;
		}else{
			//参数错误
			return "forbidden";
		}
	}

	
	public void setPayOrderid(long payOrderid)
	{
		this.payOrderid = payOrderid;
	}
	
	/**
	 * 提交订单
	 * @return
	 * @throws Exception
	 */
	public String confirmOrder() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = ClientUserIp.getIpAddr(request);
        VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
        if(vu != null && vu.getHyUserId() > 0 && id != null && id.length > 0){
        	try
			{
        		payOrderid = wxBuyProductMgr.saveOrder(vu, id, aid, usejf,ip,this.getOwner());
				if(payOrderid > 0){
					return SUCCESS;
				}
			} catch (Exception e)
			{
				System.out.println("购买异常：visitUser:"+vu.getHyUserId()+"+Goods："+id.toString());
				e.printStackTrace();
			}
        }
		return "forbidden";
	}
	
	public long getPayOrderid()
	{
		return payOrderid;
	}

	public PayOrder getPayOrder()
	{
		return payOrder;
	}

	public void setPayOrder(PayOrder payOrder)
	{
		this.payOrder = payOrder;
	}

}
