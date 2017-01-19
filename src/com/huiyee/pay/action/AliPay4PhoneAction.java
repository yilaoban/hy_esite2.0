package com.huiyee.pay.action;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.pay.config.AlipayConfig4Phone;
import com.huiyee.pay.mgr.IOwnerPayMgr;
import com.huiyee.pay.model.AliPay;
import com.huiyee.pay.util.AlipaySubmit4phone;
import com.huiyee.pay.util.UtilDate;
import com.opensymphony.xwork2.ActionSupport;

public class AliPay4PhoneAction extends InteractModelAction
{

	private static final long serialVersionUID = 3887688790629684079L;
	//支付宝网关地址
	private String ALIPAY_GATEWAY_NEW = "http://wappaygw.alipay.com/service/rest.htm?";

	////////////////////////////////////调用授权接口alipay.wap.trade.create.direct获取授权码token//////////////////////////////////////
	
	//返回格式
	private String format = "xml";
	//必填，不需要修改
	
	//返回格式
	private String v = "2.0";
	//必填，不需要修改
	
	//请求号
	private String req_id = UtilDate.getOrderNum();
	//必填，须保证每次请求都是唯一
	
	//req_data详细信息
	
	//服务器异步通知页面路径
	private String notify_url =  HyConfig.getPageyuming()+"/hy/pay/alps4p.action";
	//需http://格式的完整路径，不能加?id=123这类自定义参数

	//页面跳转同步通知页面路径
	private String call_back_url = HyConfig.getPageyuming()+"/hy/pay/alcb4p";
	//需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/

	//操作中断返回地址
	private String merchant_url = HyConfig.getPageyuming()+"/hy/pay/almc4p.action";
	//用户付款中途退出返回商户的地址。需http://格式的完整路径，不允许加?id=123这类自定义参数

	//商户订单号
	private String out_trade_no ;
	//商户网站订单系统中唯一订单号，必填

	//订单名称
	private String subject  ;
	//必填

	//付款金额
	private String total_fee ;
	//必填
	
	private String ff;
	private IOwnerPayMgr ownerPayMgr; 
	
	public String execute() throws Exception
	{
		return ActionSupport.SUCCESS;
	}
	
	public String asub() throws Exception
	{
		notify_url = HyConfig.getPageyuming()+"/" + this.getOname() +"/pay/alps4p.action";
		call_back_url = HyConfig.getPageyuming()+"/" + this.getOname() + "/pay/alcb4p";
		merchant_url = HyConfig.getPageyuming()+"/" + this.getOname() +"/pay/almc4p.action";
		AliPay aliPay = ownerPayMgr.findAliPay("Z");
		if(aliPay != null){
			HyConfig.setAlipartner(aliPay.getAlipartner());
			HyConfig.setAliseller_email(aliPay.getAliseller_email());
			HyConfig.setAlikey(aliPay.getAlikey());
		}
		String orderNo = String.valueOf(new Date().getTime());
		String req_dataToken = "<direct_trade_create_req><notify_url>" + notify_url + "</notify_url><call_back_url>" + call_back_url +out_trade_no+ ".action</call_back_url><seller_account_name>" + HyConfig.aliseller_email + "</seller_account_name><out_trade_no>" + orderNo + "</out_trade_no><subject>" + subject + "</subject><total_fee>" + total_fee + "</total_fee><merchant_url>" + merchant_url + "</merchant_url></direct_trade_create_req>";
	
		//把请求参数打包成数组
		Map<String, String> sParaTempToken = new HashMap<String, String>();
		sParaTempToken.put("service", "alipay.wap.trade.create.direct");
		sParaTempToken.put("partner",HyConfig.alipartner);
		sParaTempToken.put("_input_charset", AlipayConfig4Phone.input_charset);
		sParaTempToken.put("sec_id", AlipayConfig4Phone.sign_type);
		sParaTempToken.put("format", format);
		sParaTempToken.put("v", v);
		sParaTempToken.put("req_id", req_id);
		sParaTempToken.put("req_data", req_dataToken);
		
		//建立请求
		String sHtmlTextToken = AlipaySubmit4phone.buildRequest(ALIPAY_GATEWAY_NEW,"", "",sParaTempToken);
		//URLDECODE返回的信息
		sHtmlTextToken = URLDecoder.decode(sHtmlTextToken,AlipayConfig4Phone.input_charset);
		//获取token
		String request_token = AlipaySubmit4phone.getRequestToken(sHtmlTextToken);
		
		
		////////////////////////////////////根据授权码token调用交易接口alipay.wap.auth.authAndExecute//////////////////////////////////////
		
		//业务详细
		String req_data = "<auth_and_execute_req><request_token>" + request_token + "</request_token></auth_and_execute_req>";
		//必填
		
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "alipay.wap.auth.authAndExecute");
		sParaTemp.put("partner", HyConfig.alipartner);
		sParaTemp.put("_input_charset", AlipayConfig4Phone.input_charset);
		sParaTemp.put("sec_id", AlipayConfig4Phone.sign_type);
		sParaTemp.put("format", format);
		sParaTemp.put("v", v);
		sParaTemp.put("req_data", req_data);
		
		//建立请求
		ff = AlipaySubmit4phone.buildRequest(ALIPAY_GATEWAY_NEW, sParaTemp, "get", "确认");
		return ActionSupport.SUCCESS;
	}

	public String getFf()
	{
		return ff;
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

	
	public void setOwnerPayMgr(IOwnerPayMgr ownerPayMgr)
	{
		this.ownerPayMgr = ownerPayMgr;
	}

	
}
