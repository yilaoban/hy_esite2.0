
package project.caidan.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import project.caidan.dto.DailiCpz;
import project.caidan.dto.ProductRMB;
import project.caidan.mgr.IKqMgr;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.model.OrderGoods;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.weixin.dto.WkqRs;
import com.huiyee.weixin.mgr.IPayShopOwnerMgr;
import com.huiyee.weixin.mgr.IWxBuyProductMgr;
import com.huiyee.weixin.model.Wkq;
import com.opensymphony.xwork2.ActionContext;

public class CaiDanKqAction extends AbstractAction
{

	private static final long serialVersionUID = 1L;
	private IPayShopOwnerMgr payShopOwnerMgr;
	private IWxBuyProductMgr wxBuyProductMgr;
	private IKqMgr kqMgr;
	
	
	public void setKqMgr(IKqMgr kqMgr)
	{
		this.kqMgr = kqMgr;
	}

	public void setWxBuyProductMgr(IWxBuyProductMgr wxBuyProductMgr)
	{
		this.wxBuyProductMgr = wxBuyProductMgr;
	}

	public void setPayShopOwnerMgr(IPayShopOwnerMgr payShopOwnerMgr)
	{
		this.payShopOwnerMgr = payShopOwnerMgr;
	}

	public String asub() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		WkqRs rs = new WkqRs();
		if (vu != null && vu.getKv() != null)
		{
			String[] strs = vu.getKv().split("-");
			if (strs.length == 2)
			{
				OrderGoods po=wxBuyProductMgr.findPayOrderGoodsById(Long.valueOf(strs[1]));
				if(po==null){
					rs.setStatus(-20);
					rs.setHydesc("订单不存在！");
					Gson gson = new Gson();
					out.print(gson.toJson(rs));
					out.flush();
					out.close();
					return null;
				}
				Wkq wk = payShopOwnerMgr.findWkq(this.getOwner(),po.getType());
				if(wk==null){
					rs.setStatus(-10);
					rs.setHydesc("没有配置应用站点！");
					Gson gson = new Gson();
					out.print(gson.toJson(rs));
					out.flush();
					out.close();
					return null;
				}
				rs.setUrl(HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl() + "/user/wxshow/" + wk.getFpage() + "/" + vu.getSource() + ".html");
				DailiCpz dc=kqMgr.findChannelByWxuid(vu.getWxUser().getId());
				if(dc==null)
				{
					rs.setStatus(-4);
					rs.setHydesc("不是店主身份！");
					Gson gson = new Gson();
					out.print(gson.toJson(rs));
					out.flush();
					out.close();
					return null;
				}
				int ss=kqMgr.findProductChannel(po.getPid(), dc.getClid());
				if(ss==0)
				{
						rs.setStatus(-6);
						rs.setHydesc("店主渠道不匹配！");
						Gson gson = new Gson();
						out.print(gson.toJson(rs));
						out.flush();
						out.close();
						return null;
				}
				 ProductRMB pr=kqMgr.findPRMB(po.getPid());
				 if(pr.getEndtime().getTime()<System.currentTimeMillis())
				 {
					    rs.setStatus(-7);
						rs.setHydesc("卡券已经过期！");
						Gson gson = new Gson();
						out.print(gson.toJson(rs));
						out.flush();
						out.close();
						return null; 
				 }
				
				String st = wxBuyProductMgr.findWkqOrder(strs[0], Long.valueOf(strs[1]));
					if (st == null)
					{
						rs.setStatus(-2);
						rs.setHydesc("卡券不存在！");
					} else if (st.equals("Y"))
					{
						rs.setStatus(-3);
						rs.setHydesc("卡券已经验证过！");
					} else
					{
						//wxBuyProductMgr.updateWkqYzStatus(vu.getWxuid(),strs[0], Long.valueOf(strs[1]),po.getOrderid(), "Y",this.getOnameUrl(),po.getType(),this.getOwner());
						//事务问题  把上一条语句执行放到kqmgr里
						kqMgr.updateRMB(vu, dc, po.getOrderid(), Long.valueOf(strs[1]), po.getProductname(),pr,strs[0],this.getOnameUrl(),po.getType(),this.getOwner());
						rs.setStatus(1);
						rs.setHydesc("验证成功！");
						rs.setUrl(HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl() + "/user/wxshow/" + wk.getSpage() + "/" + vu.getSource() + ".html");
					}
			} else
			{
				rs.setStatus(-5);
				rs.setHydesc("请不要篡改二维码地址！");
			}
		} else
		{
			rs.setStatus(-1);
			rs.setHydesc("用户不存在！");
		}
		Gson gson = new Gson();
		out.print(gson.toJson(rs));
		out.flush();
		out.close();
		return null;
	}

}
