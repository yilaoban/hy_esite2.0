
package com.huiyee.weixin.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.mgr.IAreaManager;
import com.huiyee.esite.mgr.IWeiXinMgr;
import com.huiyee.esite.model.AreaCity;
import com.huiyee.esite.model.AreaProvince;
import com.huiyee.esite.model.MarketingSet;
import com.huiyee.esite.model.OrderGoods;
import com.huiyee.esite.model.PayRecord;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.weixin.dto.OrderInfoDto;
import com.huiyee.weixin.mgr.IWxBuyProductMgr;
import com.huiyee.weixin.model.PayAddress;
import com.huiyee.weixin.model.PayOrder;
import com.huiyee.weixin.model.ShoppingCartRecord;
import com.opensymphony.xwork2.ActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WeiXinBuyProductAction extends AbstractAction
{

	private static final long serialVersionUID = 8186615979778845668L;
	private long productid;
	private int quantity;
	private long aid;
	private long hyuid;
	private IWxBuyProductMgr wxBuyProductMgr;
	private IWeiXinMgr weiXinMgr;
	private IAreaManager areaMgr;
	private OrderInfoDto dto;
	private String redirectUrl;
	private long out_trade_no;
	private String provinceId;
	private long payOrderid;
	private long payrecordid;
	private String str;
	private List<OrderInfoDto> dtoList;
	private int totalPrice;
	private String subject;
	private String status;// 订单状态
	private int pageId = 1;
	private int usejf;// 使用积分
	private String productids;
	private String linkUrl; // 我的订单页面返回的地址
	private PayRecord payRecord;
	private long mpid;
	private long paid; // 个性化 id
	private long shopCartid;
	private String type;
	private long gid;
	
	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
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

	public String buyProduct() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		ShoppingCartRecord record = null;
		if (vu != null)
		{
			hyuid = vu.getHyUserId();
			record = wxBuyProductMgr.addShoppingCart(productid, hyuid, paid, "RTN",this.getOwner());
		}
		Gson gson = new Gson();
		out.print(gson.toJson(record));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 订单详情
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showPayOrder() throws Exception
	{
		dto = wxBuyProductMgr.findPayOrderInfoById(payOrderid);
		return SUCCESS;
	}

	/**
	 * 添加商品至购物车 1
	 */
	public String addShoppingCart() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		long res = 0;
		if (vu != null)
		{
			hyuid = vu.getHyUserId();
			ShoppingCartRecord record = wxBuyProductMgr.addShoppingCart(productid, hyuid, paid, "EDT",this.getOwner());
			if(record != null){
				res = record.getId();
			}
		}
		out.print(res);
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 删除购物车产品 1
	 */
	public String delShopCartProduct() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		int res = 0;
		if (vu != null)
		{
			hyuid = vu.getHyUserId();
			res = wxBuyProductMgr.delShopCartProduct(shopCartid, hyuid);
		}
		out.print(res);
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 查看购物车 1
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showShoppingCart() throws Exception
	{
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if (vu != null)
		{
			hyuid = vu.getHyUserId();
			dto = wxBuyProductMgr.findShoppingCartProductByHyuid(hyuid, aid, this.getOwner(), "W",vu.getHyUser().getLevelid());
		}
		return SUCCESS;
	}

	/**
	 * 积分商城购物车 1
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showJfShoppingCart() throws Exception
	{
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if (vu != null)
		{
			hyuid = vu.getHyUserId();
			dto = wxBuyProductMgr.findShoppingCartProductByHyuid(hyuid, aid, this.getOwner(), "J",vu.getHyUser().getLevelid());
		}
		return SUCCESS;
	}

	/**
	 * 查询购物车数量
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findShoppingCartNum() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("text/html; charset=utf-8");
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		int res = 0;
		if (vu != null)
		{
			hyuid = vu.getHyUserId();
			res = wxBuyProductMgr.findShoppingCartProductNumByHyuid(hyuid);
		}
		PrintWriter pw = response.getWriter();
		pw.print(res);
		pw.flush();
		pw.close();
		return null;
	}


	/**
	 * 修改商品数量 1
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateQuantity() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		int res = 0;
		if (vu != null)
		{
			hyuid = vu.getHyUserId();
			res = wxBuyProductMgr.updateQuantity(shopCartid, hyuid, quantity,productid);
		}
		out.print(res);
		out.flush();
		out.close();
		return null;
	}


	public String showAddress() throws Exception
	{
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if (vu != null)
		{
			hyuid = vu.getHyUserId();
			dto = wxBuyProductMgr.findAddressList(aid, hyuid);
		}
		return SUCCESS;
	}
	
	public String myAddress() throws Exception
	{
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if (vu != null)
		{
			hyuid = vu.getHyUserId();
			dto = wxBuyProductMgr.findAddressList(aid, hyuid);
		}
		return SUCCESS;
	}

	/**
	 * 新增地址
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addPayAddress() throws Exception
	{
		dto = new OrderInfoDto();
		List<AreaProvince> proviceList = areaMgr.findAreaPorvince();
		dto.setProvinceList(proviceList);
		return SUCCESS;
	}

	public String ajax_get_city() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("text/html; charset=utf-8");
		List<AreaCity> cityList = areaMgr.findAreaCity(provinceId);
		JSONArray arr = new JSONArray();
		for (AreaCity a : cityList)
		{
			JSONObject json = new JSONObject();
			json.put("cityId", a.getCityId());
			json.put("city", a.getCity());
			arr.add(json);
		}
		PrintWriter pw = response.getWriter();
		pw.print(arr.toString());
		pw.flush();
		pw.close();
		return null;
	}

	/**
	 * 保存地址
	 * 
	 * @return
	 * @throws Exception
	 */
	public String savePayAddress() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		int res =0;
		if (vu != null)
		{
			hyuid = vu.getHyUserId();
			res = wxBuyProductMgr.savePayAddress(hyuid, dto.getAddress());
		}
		out.print(res);
		out.flush();
		out.close();
		return null;

	}

	/**
	 * 编辑地址
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editPayAddress() throws Exception
	{
		dto = new OrderInfoDto();
		PayAddress address = wxBuyProductMgr.findPayAddressById(aid);
		List<AreaProvince> proviceList = areaMgr.findAreaPorvince();
		dto.setAddress(address);
		dto.setProvinceList(proviceList);
		return SUCCESS;
	}

	/**
	 * 更新地址
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updatePayAddress() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		int res = 0;
		if (vu != null)
		{
			hyuid = vu.getHyUserId();
			res = wxBuyProductMgr.updatePayAddress(aid, hyuid, dto.getAddress());
		}
		out.print(res);
		out.flush();
		out.close();
		return null;
	}

	public String delPayAddress() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int res = wxBuyProductMgr.delPayAddress(aid);
		out.print(res);
		out.flush();
		out.close();
		return null;
	}

	public String wxpayCallBack() throws Exception
	{
		redirectUrl = HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl() + "/user/showOrderList.action";
		wxBuyProductMgr.updatepayOrderStatus(out_trade_no, "DFK", "DQR", 0, null, 0);
		return SUCCESS;
	}
	
	public String callBack() throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = ClientUserIp.getIpAddr(request);
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if(vu != null){
			redirectUrl = HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl() + "/user/showOrderList.action";
			String url = HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl() + "/user/payHome.action";
			PayOrder p = wxBuyProductMgr.findPayOrderById(out_trade_no);
			if(p != null){
				if(p.getGoodscount() > 0){
					wxBuyProductMgr.updatepayOrderStatus(out_trade_no, null, "DFH", this.getOwner(), url, 0);//更新主订单
				}else{
					wxBuyProductMgr.updatepayOrderStatus(out_trade_no, null, "CMP", this.getOwner(), url, 0);//更新主订单
				}
				wxBuyProductMgr.updatePayorderStatusByFid(out_trade_no,"CMP");//更新子订单
				PayRecord payRecord = new PayRecord();
				payRecord.setIp(ip);
				payRecord.setMediaorder("支付0元");
				payRecord.setHyuid(vu.getHyUserId());
				payRecord.setOrderid(out_trade_no);
				wxBuyProductMgr.savePayRecord(payRecord);//保存支付记录
				return SUCCESS;
			}
		}
		return "forbidden";
	}

	public String payHome() throws Exception
	{
		long pid = wxBuyProductMgr.findPayHome(this.getOwner());
		redirectUrl = HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl() + "/user/wxshow/" + pid + "/hom.html";
		return SUCCESS;
	}

	public String payJfHome() throws Exception
	{
		long pid = wxBuyProductMgr.findPayJfHome(this.getOwner());
		redirectUrl = HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl() + "/user/wxshow/" + pid + "/hom.html";
		return SUCCESS;
	}

	public String payJfUser() throws Exception
	{
		long pid = wxBuyProductMgr.findPayJfUserPage(this.getOwner());
		redirectUrl = HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl() + "/user/wxshow/" + pid + "/hom.html";
		return SUCCESS;
	}

	public String payProductList() throws Exception
	{
		MarketingSet set = wxBuyProductMgr.findPayShop(this.getOwner());
		if (set != null)
		{
			long pid = set.getLpage();
			if(pid == 0){
				pid = set.getHomepage();
			}
			redirectUrl = HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl() + "/user/wxshow/" + pid + "/hom.html";
		} else
		{
			redirectUrl = ServletActionContext.getRequest().getHeader("Referer");
		}
		return SUCCESS;
	}

	public String payJfProductList() throws Exception
	{
		MarketingSet set = wxBuyProductMgr.findJfPayShop(this.getOwner());
		if (set != null)
		{
			long pid = set.getLpage();
			if(pid == 0){
				pid = set.getHomepage();
			}
			redirectUrl = HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl() + "/user/wxshow/" + pid + "/hom.html";
		} else
		{
			redirectUrl = ServletActionContext.getRequest().getHeader("Referer");
		}
		return SUCCESS;
	}

	/**
	 * 我的订单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showOrderList() throws Exception
	{
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if (vu != null)
		{
			hyuid = vu.getHyUserId();
			dto = wxBuyProductMgr.findOrderList(hyuid, status, pageId, 1);
		}
		return SUCCESS;
	}
	
	public String ajaxShowOrderList() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("cache-control", "no-cache");
		response.setContentType("application/json; charset=utf-8");
		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if (vu != null)
		{
			hyuid = vu.getHyUserId();
			dto = wxBuyProductMgr.findOrderList(hyuid, status, pageId, 1);
		}
		pw.print(gson.toJson(dto));
		pw.flush();
		pw.close();
		return null;
		
	}
	

	/**
	 * 积分商城订单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showJfOrderList() throws Exception
	{
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if (vu != null)
		{
			hyuid = vu.getHyUserId();
			dto = wxBuyProductMgr.findOrderList(hyuid, status, pageId, 0);
		}
		return SUCCESS;
	}

	public String ajaxShowJfOrderList() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("cache-control", "no-cache");
		response.setContentType("application/json; charset=utf-8");
		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if (vu != null)
		{
			hyuid = vu.getHyUserId();
			dto = wxBuyProductMgr.findOrderList(hyuid, status, pageId, 0);
		}
		pw.print(gson.toJson(dto));
		pw.flush();
		pw.close();
		return null;
		
	}
	
	
	/**
	 * 微商城 卡券订单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showKqOrderList() throws Exception
	{
		type = "W";
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if (vu != null)
		{
			hyuid = vu.getHyUserId();
			dto = wxBuyProductMgr.findKqOrderList(hyuid, "CMP", pageId, 1);
		}
		return SUCCESS;
	}
	
	public String ajaxShowKqOrderList() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("cache-control", "no-cache");
		response.setContentType("application/json; charset=utf-8");
		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		type = "W";
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if (vu != null)
		{
			hyuid = vu.getHyUserId();
			dto = wxBuyProductMgr.findKqOrderList(hyuid, "CMP", pageId, 1);
		}
		pw.print(gson.toJson(dto));
		pw.flush();
		pw.close();
		return null;
		
	}
	
	/**
	 * 积分商城 卡券订单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showJfKQOrderList() throws Exception
	{
		type = "J";
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if (vu != null)
		{
			hyuid = vu.getHyUserId();
			dto = wxBuyProductMgr.findKqOrderList(hyuid, "CMP", pageId, 0);
		}
		return SUCCESS;
	}
	
	public String ajaxShowJfKQOrderList() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("cache-control", "no-cache");
		response.setContentType("application/json; charset=utf-8");
		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		type = "J";
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if (vu != null)
		{
			hyuid = vu.getHyUserId();
			dto = wxBuyProductMgr.findKqOrderList(hyuid, "CMP", pageId, 0);
		}
		pw.print(gson.toJson(dto));
		pw.flush();
		pw.close();
		return null;
		
	}
	
	/**
	 * 所有卡券（包含积分商城微商城）
	 * @throws Exception
	 */
	
	public String showKQList() throws Exception{
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if (vu != null){
			hyuid = vu.getHyUserId();
			dto = wxBuyProductMgr.findKqOrderList(hyuid, "CMP", pageId);
		}
		return SUCCESS;
	}
	
	
	public void ajaxShowKQList() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("cache-control", "no-cache");
		response.setContentType("application/json; charset=utf-8");
		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if (vu != null)
		{
			hyuid = vu.getHyUserId();
			dto = wxBuyProductMgr.findKqOrderList(hyuid, "CMP", pageId);
		}
		pw.print(gson.toJson(dto));
		pw.flush();
		pw.close();
	}
	
	
	public String delPayOrder() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int res = wxBuyProductMgr.delPayOrder(payOrderid);
		out.print(res);
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 取消订单 同时加积分 加库存
	 * 
	 * @return
	 * @throws Exception
	 */
	public String cancelPayOrder() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		int res = 0;
		if (vu != null)
		{
			hyuid = vu.getHyUserId();
			res = wxBuyProductMgr.updatePayOrder(payOrderid, hyuid);
		}
		out.print(res);
		out.flush();
		out.close();
		return null;
	}
	
	public String checkused() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		OrderGoods goods = wxBuyProductMgr.findPayOrderGoodsById(gid);
		if(goods != null){
			out.print(goods.getUsed());
		}else{
			out.print("Y");
		}
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

	public OrderInfoDto getDto()
	{
		return dto;
	}

	public void setDto(OrderInfoDto dto)
	{
		this.dto = dto;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public long getAid()
	{
		return aid;
	}

	public void setAid(long aid)
	{
		this.aid = aid;
	}

	public String getRedirectUrl()
	{
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl)
	{
		this.redirectUrl = redirectUrl;
	}

	public String getProvinceId()
	{
		return provinceId;
	}

	public void setProvinceId(String provinceId)
	{
		this.provinceId = provinceId;
	}

	public long getPayOrderid()
	{
		return payOrderid;
	}

	public void setPayOrderid(long payOrderid)
	{
		this.payOrderid = payOrderid;
	}

	public String getStr()
	{
		return str;
	}

	public void setStr(String str)
	{
		this.str = str;
	}

	public List<OrderInfoDto> getDtoList()
	{
		return dtoList;
	}

	public void setDtoList(List<OrderInfoDto> dtoList)
	{
		this.dtoList = dtoList;
	}

	public long getPayrecordid()
	{
		return payrecordid;
	}

	public void setPayrecordid(long payrecordid)
	{
		this.payrecordid = payrecordid;
	}

	public int getTotalPrice()
	{
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice)
	{
		this.totalPrice = totalPrice;
	}

	public String getSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public int getUsejf()
	{
		return usejf;
	}

	public void setUsejf(int usejf)
	{
		this.usejf = usejf;
	}

	public String getProductids()
	{
		return productids;
	}

	public void setProductids(String productids)
	{
		this.productids = productids;
	}

	public String getLinkUrl()
	{
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl)
	{
		this.linkUrl = linkUrl;
	}

	public long getOut_trade_no()
	{
		return out_trade_no;
	}

	public void setOut_trade_no(long out_trade_no)
	{
		this.out_trade_no = out_trade_no;
	}

	public PayRecord getPayRecord()
	{
		return payRecord;
	}

	public void setPayRecord(PayRecord payRecord)
	{
		this.payRecord = payRecord;
	}

	public long getMpid()
	{
		return mpid;
	}

	public void setMpid(long mpid)
	{
		this.mpid = mpid;
	}

	public long getPaid()
	{
		return paid;
	}

	public void setPaid(long paid)
	{
		this.paid = paid;
	}

	public long getShopCartid()
	{
		return shopCartid;
	}

	public void setShopCartid(long shopCartid)
	{
		this.shopCartid = shopCartid;
	}

	
	public long getGid()
	{
		return gid;
	}

	
	public void setGid(long gid)
	{
		this.gid = gid;
	}

}
