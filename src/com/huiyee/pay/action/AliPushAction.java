package com.huiyee.pay.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.pay.util.AlipayNotify;
import com.opensymphony.xwork2.ActionSupport;

public class AliPushAction extends ActionSupport
{

	private static final long serialVersionUID = 3887688790629684079L;

    private String out_trade_no;//�̻�������
    private String trade_no;//֧�������׺�
    private String trade_status;//����״̬
    
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

		
		//��ȡ֧����POST����������Ϣ
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
			//����������δ����ڳ�������ʱʹ�á����mysign��sign�����Ҳ����ʹ����δ���ת��
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}

		if(AlipayNotify.verify(params)){//��֤�ɹ�
			//////////////////////////////////////////////////////////////////////////////////////////
			//������������̻���ҵ���߼��������

			//�������������ҵ���߼�����д�������´�������ο�������
			
			if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
				//�жϸñʶ����Ƿ����̻���վ���Ѿ���������
					//���û���������������ݶ����ţ�out_trade_no�����̻���վ�Ķ���ϵͳ�в鵽�ñʶ�������ϸ����ִ���̻���ҵ�����
					//�����������������ִ���̻���ҵ�����
				
			}
			

			//�������������ҵ���߼�����д�������ϴ�������ο�������
			out.println("success");	//�벻Ҫ�޸Ļ�ɾ��

			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//��֤ʧ��
			out.println("fail");
		}
		
		out.print("");
		out.flush();
		out.close();
		return null;
	}

	public void setOut_trade_no(String out_trade_no)
	{
		this.out_trade_no = out_trade_no;
	}

	public void setTrade_no(String trade_no)
	{
		this.trade_no = trade_no;
	}

	public void setTrade_status(String trade_status)
	{
		this.trade_status = trade_status;
	}
	
}