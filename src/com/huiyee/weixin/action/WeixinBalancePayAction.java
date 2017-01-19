 package com.huiyee.weixin.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.mgr.IGrCenterMgr;
import com.huiyee.esite.mgr.IWeiXinMgr;
import com.huiyee.esite.model.BalanceRule;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.weixin.mgr.IWxBuyProductMgr;
import com.huiyee.weixin.model.PayOrder;
import com.huiyee.weixin.model.WxMp;
import com.huiyee.weixin.model.WxPayOrder;
import com.huiyee.weixin.utils.HttpsUtil;
import com.huiyee.weixin.utils.XmlUtil;
import com.opensymphony.xwork2.ActionContext;

public class WeixinBalancePayAction extends AbstractAction
{

	private static final long serialVersionUID = 5632090158301994248L;
	private IWeiXinMgr weiXinMgr;
	private IGrCenterMgr grCenterMgr;
	
	private long mpid;
	private String body;
	private long out_trade_no;// 我们本地的订单id
	private int total_fee;
	private String trade_type = "JSAPI";
	private long attach; // 规则id

	public void setWeiXinMgr(IWeiXinMgr weiXinMgr)
	{
		this.weiXinMgr = weiXinMgr;
	}

	public String prePay() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			String ip = ClientUserIp.getIpAddr(request);
			VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
			if (vu == null || vu.getWxUser() == null || vu.getWxUser().getOpenid() == null)
			{
				// 做一些错误处理代码
				return null;
			}
			WxMp mp = weiXinMgr.updateFindWxMp(this.getOwner(), mpid);
			if (mp == null)
			{
				// 做一些错误处理代码
				return null;
			}
			String appid = mp.getAppid();
			String mch_id = mp.getMch_id();
			String key = mp.getKey();

			if (StringUtils.isBlank(mch_id) || StringUtils.isBlank(key))
			{
				// 没设置微信支付，做一些错误处理代码
				return null;
			}
			
			BalanceRule rule = grCenterMgr.findBalanceRuleById(attach,this.getOwner());//查询支付金额
			if(rule == null){
				// 充值规则不正确
				return null;
			}
			
			String openid = vu.getWxUser().getOpenid();
			String nonce_str = nonceStr(32);
			String notify_url = HyConfig.getPageyuming() + "/" + this.getOname() + "/user/wxBalancePayResult.action";
			// attach = attach + "," + vu.getHyUserId();
			Map<String, String> map = new HashMap<String, String>();
			map.put("appid", appid);
			map.put("mch_id", mch_id);
			map.put("nonce_str", nonce_str);
			map.put("body", body);
			map.put("attach", attach+"");
			map.put("out_trade_no", "rmb" + out_trade_no);
			map.put("total_fee", rule.getRmb() + "");
			map.put("spbill_create_ip", ip);
			map.put("notify_url", notify_url);
			map.put("trade_type", trade_type);
			map.put("openid", openid);
			String sign = md5(map, key);

			WxPayOrder order = new WxPayOrder();
			order.setAppid(appid);
			order.setMch_id(mch_id);
			order.setNonce_str(nonce_str);
			order.setBody(body);
			order.setAttach(attach+"");
			order.setOut_trade_no("rmb" + out_trade_no);
			order.setTotal_fee(rule.getRmb());
			order.setSpbill_create_ip(ip);
			order.setNotify_url(notify_url);
			order.setTrade_type(trade_type);
			order.setOpenid(vu.getWxUser().getOpenid());
			order.setSign(sign);

			String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
			String post = XmlUtil.payOrderToXml(order);
			post = post.replaceAll("__", "_");
			Map<String, String> obj = HttpsUtil.httpsRequestMap(url, "POST", post);
			String return_code = obj.get("return_code");
			String return_msg = obj.get("return_msg");

			Map<String, String> payMap = new HashMap<String, String>();
			payMap.put("return_code", return_code);
			payMap.put("return_msg", return_msg);
			System.out.println(return_code + "===" + return_msg);
			if ("SUCCESS".equals(return_code))
			{
				String result_code = obj.get("result_code");
				String err_code = obj.get("err_code");
				String err_code_des = obj.get("err_code_des");
				payMap.put("result_code", result_code);
				payMap.put("err_code", err_code);
				payMap.put("err_code_des", err_code_des);
				if ("SUCCESS".equals(result_code))
				{
					String prepay_id = obj.get("prepay_id");
					Map<String, String> jsmap = new HashMap<String, String>();
					jsmap.put("appId", appid);
					jsmap.put("timeStamp", new Date().getTime() + "");
					jsmap.put("nonceStr", nonce_str);
					jsmap.put("package", "prepay_id=" + prepay_id);
					jsmap.put("signType", "MD5");
					String paySign = md5(jsmap, key);
					jsmap.put("paySign", paySign);

					payMap.put("out_trade_no", out_trade_no+"");
					payMap.putAll(jsmap);
				}
			}

			out.print(new Gson().toJson(payMap));
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			out.flush();
			out.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public String payResult() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			String postdata = readStreamParameter(request.getInputStream());
			if (StringUtils.isEmpty(postdata))
			{
				return null;
			}
			Document document = DocumentHelper.parseText(postdata);
			Element root = document.getRootElement();
			List<Element> elementList = root.elements();

			Map<String, String> messageMap = new HashMap<String, String>();
			for (Element e : elementList)
			{
				messageMap.put(e.getName(), new String(e.getTextTrim().getBytes(), "UTF-8"));
			}

			String return_code = messageMap.get("return_code");
			if ("SUCCESS".equals(return_code))
			{
				String result_code = messageMap.get("result_code");
				if ("SUCCESS".equals(result_code))
				{
					String out_trade_no = messageMap.get("out_trade_no");
					String transaction_id = messageMap.get("transaction_id");// 微信订单号
					String total_fee = messageMap.get("total_fee");
					String ruleid = messageMap.get("attach");// 返回回来处理参数
					System.out.println("transaction_id=" + transaction_id);
					System.out.println("total_fee=" + total_fee);
					System.out.println("out_trade_no ==" + out_trade_no);
					grCenterMgr.saveBalanceRecord(Long.parseLong(ruleid), total_fee, transaction_id, Long.parseLong(out_trade_no.split("rmb")[1]));
					out.print("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
					return null;
				}
			}

			System.out.println("payResult---" + new Gson().toJson(messageMap));
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			out.flush();
			out.close();
		}
		return null;
	}

	private static String readStreamParameter(ServletInputStream in)
	{
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while ((line = reader.readLine()) != null)
			{
				buffer.append(line);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			if (null != reader)
			{
				try
				{
					reader.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return buffer.toString();
	}

	public static String md5(Map<String, String> map, String key)
	{
		if (map == null || map.size() == 0)
		{
			return null;
		}
		try
		{
			Set<String> set = map.keySet();
			int size = set.size();
			String[] arr = new String[size];
			int index = 0;
			for (String s : set)
			{
				arr[index++] = s;
			}
			Arrays.sort(arr);

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < arr.length; i++)
			{
				String k = arr[i];
				String v = map.get(k);
				sb.append(k).append("=").append(v).append("&");
			}
			sb.append("key=").append(key);

			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(sb.toString().getBytes());
			String signature = byteToString(digest);

			return signature.toUpperCase();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	private static String byteToString(byte[] bytes)
	{
		StringBuffer sb = new StringBuffer();
		for (byte b : bytes)
		{
			String str = Integer.toHexString(b & 0xff);
			if (str.length() == 1)
			{
				sb.append("0");
			}
			sb.append(str);
		}
		return sb.toString();
	}

	private static String nonceStr(int size)
	{
		if (size < 1)
		{
			return "";
		}
		String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++)
		{
			int index = (int) (Math.random() * s.length());
			sb.append(s.charAt(index));
		}
		return sb.toString();
	}

	public long getMpid()
	{
		return mpid;
	}

	public void setMpid(long mpid)
	{
		this.mpid = mpid;
	}

	public String getBody()
	{
		return body;
	}

	public void setBody(String body)
	{
		this.body = body;
	}

	public long getOut_trade_no()
	{
		return out_trade_no;
	}

	public void setOut_trade_no(long out_trade_no)
	{
		this.out_trade_no = out_trade_no;
	}

	public int getTotal_fee()
	{
		return total_fee;
	}

	public void setTotal_fee(int total_fee)
	{
		this.total_fee = total_fee;
	}

	public String getTrade_type()
	{
		return trade_type;
	}

	public void setTrade_type(String trade_type)
	{
		this.trade_type = trade_type;
	}
	
	public void setGrCenterMgr(IGrCenterMgr grCenterMgr)
	{
		this.grCenterMgr = grCenterMgr;
	}

	public long getAttach() {
		return attach;
	}

	public void setAttach(long attach) {
		this.attach = attach;
	}

}
