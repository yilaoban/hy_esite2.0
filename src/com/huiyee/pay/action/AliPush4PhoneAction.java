package com.huiyee.pay.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;

import com.huiyee.pay.config.AlipayConfig4Phone;
import com.huiyee.pay.util.AlipayNotify4phone;
import com.opensymphony.xwork2.ActionSupport;

public class AliPush4PhoneAction extends ActionSupport
{

	private static final long serialVersionUID = 3887688790629684079L;
	public String execute() throws Exception
	{
		return ActionSupport.SUCCESS;
	}
	
	public String asub() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();

		
		//获取支付宝POST过来反馈信息
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}

		//RSA签名解密
	   	if(AlipayConfig4Phone.sign_type.equals("0001")) {
	   		params = AlipayNotify4phone.decrypt(params);
	   	}
		//XML解析notify_data数据
		Document doc_notify_data = DocumentHelper.parseText(params.get("notify_data"));
		
		//商户订单号
		String out_trade_no = doc_notify_data.selectSingleNode( "//notify/out_trade_no" ).getText();

		//支付宝交易号
		String trade_no = doc_notify_data.selectSingleNode( "//notify/trade_no" ).getText();

		//交易状态
		String trade_status = doc_notify_data.selectSingleNode( "//notify/trade_status" ).getText();


		if(AlipayNotify4phone.verifyNotify(params)){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码

			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			
			if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
				System.out.println("执行了pushAction");
			}
			

			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				
			out.println("success");	//请不要修改或删除

			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			out.println("fail");
		}
		
		out.print("");
		out.flush();
		out.close();
		return null;
	}

}
