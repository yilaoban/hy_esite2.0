package com.huiyee.pay.action;

import java.util.HashMap;
import java.util.Map;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.pay.config.AlipayConfig;
import com.huiyee.pay.mgr.IOwnerPayMgr;
import com.huiyee.pay.model.AliPay;
import com.huiyee.pay.util.AlipaySubmit;
import com.opensymphony.xwork2.ActionSupport;

public class AliPayAction extends InteractModelAction
{

	private static final long serialVersionUID = 3887688790629684079L;
	//支付类型
	private	String payment_type = "1";
	//必填，不能修改
	
	//服务器异步通知页面路径
	private	String notify_url =  HyConfig.getPageyuming()+"/hy/pay/alps.action";
	//需http://格式的完整路径，不能加?id=123这类自定义参数

	//页面跳转同步通知页面路径
	private	String return_url = HyConfig.getPageyuming()+"/hy/pay/alcb.action";
	//需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/

	//商户订单号
	private	String out_trade_no ;
	//商户网站订单系统中唯一订单号，必填

	//订单名称
	private	String subject ;
	//必填

	//付款金额
	private String total_fee ;
	//必填

	//订单描述

	private String body ;
	//商品展示地址
	private String show_url ;
	//需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html

	//防钓鱼时间戳
	private String anti_phishing_key = "";
	//若要使用请调用类文件submit中的query_timestamp函数

	//客户端的IP地址
	private String exter_invoke_ip = "121.238.118.82";
	//非局域网的外网IP地址，如：221.0.0.1
	
	
	private String ff;
	private IOwnerPayMgr ownerPayMgr; 
	
	public String execute() throws Exception
	{
		return ActionSupport.SUCCESS;
	}
	
	public String asub() throws Exception
	{
		AliPay aliPay = ownerPayMgr.findAliPay("Z");
		if(aliPay != null){
			HyConfig.setAlipartner(aliPay.getAlipartner());
			HyConfig.setAliseller_email(aliPay.getAliseller_email());
			HyConfig.setAlikey(aliPay.getAlikey());
		}
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "create_direct_pay_by_user");
        sParaTemp.put("partner", HyConfig.alipartner);
        sParaTemp.put("seller_email", HyConfig.aliseller_email);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", payment_type);
		notify_url = HyConfig.getPageyuming()+"/" + this.getOname() +"/pay/alps.action";
		return_url = HyConfig.getPageyuming()+"/" + this.getOname() +"/pay/alcb.action";
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("return_url", return_url);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);
		sParaTemp.put("show_url", show_url);
		sParaTemp.put("anti_phishing_key", anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
		
		//建立请求
		ff = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
	
		return ActionSupport.SUCCESS;
	}

	public void setPayment_type(String payment_type)
	{
		this.payment_type = payment_type;
	}

	public void setOut_trade_no(String out_trade_no)
	{
		this.out_trade_no = out_trade_no;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public void setTotal_fee(String total_fee)
	{
		this.total_fee = total_fee;
	}

	public void setBody(String body)
	{
		this.body = body;
	}

	public void setAnti_phishing_key(String anti_phishing_key)
	{
		this.anti_phishing_key = anti_phishing_key;
	}

	public void setExter_invoke_ip(String exter_invoke_ip)
	{
		this.exter_invoke_ip = exter_invoke_ip;
	}

	public void setShow_url(String show_url)
	{
		this.show_url = show_url;
	}

	public String getFf()
	{
		return ff;
	}

	public void setReturn_url(String return_url)
	{
		this.return_url = return_url;
	}

	public void setOwnerPayMgr(IOwnerPayMgr ownerPayMgr)
	{
		this.ownerPayMgr = ownerPayMgr;
	}

	
}
