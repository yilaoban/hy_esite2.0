
package project.caidan.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import project.caidan.constants.ICaiDanConstants;
import project.caidan.dto.CDProductDto;
import project.caidan.mgr.ICaiDanProductMgr;
import project.caidan.model.CDAccountCpz;
import project.caidan.model.CDProductRmb;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.model.WxUser;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.weixin.mgr.IWxBuyProductMgr;
import com.huiyee.weixin.model.ShoppingCartRecord;
import com.opensymphony.xwork2.ActionContext;

public class CaiDanProductAction extends AbstractAction
{

	private static final long serialVersionUID = -2873043881866694028L;
	private ICaiDanProductMgr cdProductMgr;
	private IWxBuyProductMgr wxBuyProductMgr;
	private CDProductDto dto;
	private int size;
	private long id;
	private WxUser wxUser;
	private long productid;
	private float rmb;
	private int pageId=1;
	
	public void setWxBuyProductMgr(IWxBuyProductMgr wxBuyProductMgr)
	{
		this.wxBuyProductMgr = wxBuyProductMgr;
	}

	public void setCdProductMgr(ICaiDanProductMgr cdProductMgr)
	{
		this.cdProductMgr = cdProductMgr;
	}

	/**
	 * 金币兑换
	 */
	@Override
	public String execute() throws Exception
	{
		VisitUser vu = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
		long hyuid = 0;
		if (vu != null)
		{
			hyuid = vu.getHyUserId();
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		dto = (CDProductDto) cdProductMgr.findProductList(this.getOwner(), hyuid, size);
		Gson gson = new Gson();
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 金币兑换详情页
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findCdProductById() throws Exception
	{
		VisitUser vu = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
		long hyuid = 0;
		if (vu != null)
		{
			hyuid = vu.getHyUserId();
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		dto = (CDProductDto) cdProductMgr.findCdProductById(id, hyuid);
		Gson gson = new Gson();
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 钱包个人信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findWxUser() throws Exception
	{
		VisitUser vu = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
		long wxuid = 0;
		if (vu != null && vu.getCd() == 1)
		{
			wxUser = vu.getWxUser();
			wxuid = vu.getWxUser().getId();
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		dto = (CDProductDto) cdProductMgr.findWxUser(wxuid);
		dto.setWxUser(wxUser);
		Gson gson = new Gson();
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}

	public String findCdQbUser() throws Exception
	{
		VisitUser vu = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
		long hyuid = 0;
		if (vu != null)
		{
			wxUser = vu.getWxUser();
			hyuid = vu.getHyUserId();
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		dto = (CDProductDto) cdProductMgr.findCdWxUser(hyuid);
		dto.setWxUser(wxUser);
		Gson gson = new Gson();
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}
	
	
	public String saveCdRmbget() throws Exception
	{
		VisitUser vu = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int res = 0;
		if (vu != null && vu.getWxUser() != null)
		{
			res = cdProductMgr.saveCdRmbget(vu.getWxUser().getId(),vu.getHyUserId(),rmb);
		}
		Gson gson = new Gson();
		out.print(gson.toJson(res));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 积分商城订单(兑换券列表)
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showCdOrderList() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if (vu != null)
		{
			long hyuid = vu.getHyUserId();
			dto = (CDProductDto) cdProductMgr.findOrderList(hyuid, "CMP", size, 0);
		}
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}

	public String findCdOrderById() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		dto = new CDProductDto();
		if (vu != null)
		{
			long hyuid = vu.getHyUserId();
			dto = (CDProductDto) cdProductMgr.findCdOrderById(hyuid, null, "J",id);
		}
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 兑换券的使用范围
	 * @return
	 * @throws Exception
	 */
	public String findCouponArea() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		dto = new CDProductDto();
		if (vu != null)
		{
			CDProductRmb pr=cdProductMgr.findProductById(id);
			if( pr!=null&& pr.getProduct()!=null){
				List<CDAccountCpz> list=cdProductMgr.findCouponArea(pr.getProduct().getId(),pageId,size);
				dto.setCdProduct(pr);
				dto.setCpz(list);
			}
			
		}
		out.print(new Gson().toJson(dto));
		out.flush();
		out.close();
		return null;
	}
	
	public String cdConfirmOrder() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		long shopCartid = 0;
		dto = new CDProductDto();
		if(vu != null){ //加入购物车
			
			/**
			 * 当产品是外部券时,判断是否有券可用
			 */
			CDProductRmb cdp=cdProductMgr.findProductByProductid(productid);
			if(cdp!=null&&cdp.getProduct()!=null&&IPageConstants.PRODUCT_SUBTYPE_COUPON.equals(cdp.getProduct().getSubtype())){
				int num=cdProductMgr.findProductCodeLess(productid);
				if(num==0){
					dto.setHydesc("该商品已售完");
					Gson gson = new Gson();
					out.print(gson.toJson(dto));
					out.flush();
					out.close();
					return null;
				} 
			}
			if(cdp!=null&&cdp.getProduct()!=null){
				Gson gson = new Gson();
				int remian = cdp.getProduct().getTotal() - cdp.getProduct().getUsed();
				if(remian < 1){
					dto.setStatus(-1);
					dto.setHydesc("库存不足");
				}
				int re = cdProductMgr.findRemianJf(vu.getHyUserId(),cdp.getProduct().getSalesjifen());
				if(re == -1){
					dto.setStatus(-3);
					out.print(gson.toJson(dto));
					out.flush();
					out.close();
					return null;
				}
			}
			ShoppingCartRecord record = wxBuyProductMgr.addShoppingCart(productid, vu.getHyUserId(), 0,"RTN",this.getOwner());
			if(record != null){
				shopCartid = record.getId(); 
				long[] id = {shopCartid} ;
				String ip = getIp();
				 //生成订单
				try{
					long payOrderid = wxBuyProductMgr.saveOrder(vu, id, 0, 0,ip,this.getOwner());
					if(payOrderid > 0){ //支付
						String url = HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl() + "/user/showJfOrderList.action";
						dto = (CDProductDto) cdProductMgr.savePayMoney(payOrderid,vu.getHyUserId(),this.getOwner(),vu,url,ip);
						if(dto.getStatus()==1&&IPageConstants.PRODUCT_SUBTYPE_COUPON.equals(cdp.getProduct().getSubtype())){
							dto.setCode(cdProductMgr.findProductCode(payOrderid));
						}
					}else{
						dto.setStatus(-2);
						dto.setHydesc("购买失败！");
					}
				} catch (Exception e) {
					System.out.println("购买异常：visitUser:"+vu.getHyUserId()+"+Goods："+id.toString());
					e.printStackTrace();
				}
			}else{
				dto.setHydesc("该商品不存在");
			}
		}
		Gson gson = new Gson();
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
        
	}
	
	
	public String getIp() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = ClientUserIp.getIpAddr(request);
        return ip;
	}
	
	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}

	public CDProductDto getDto()
	{
		return dto;
	}

	public void setDto(CDProductDto dto)
	{
		this.dto = dto;
	}

	public WxUser getWxUser()
	{
		return wxUser;
	}

	public void setWxUser(WxUser wxUser)
	{
		this.wxUser = wxUser;
	}

	
	public long getProductid()
	{
		return productid;
	}

	
	public void setProductid(long productid)
	{
		this.productid = productid;
	}

	
	public float getRmb()
	{
		return rmb;
	}

	
	public void setRmb(float rmb)
	{
		this.rmb = rmb;
	}

	
	public int getPageId()
	{
		return pageId;
	}

	
	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

}
