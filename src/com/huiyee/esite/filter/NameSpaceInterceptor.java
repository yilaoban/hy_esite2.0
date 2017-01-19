package com.huiyee.esite.filter;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.action.HyAbstractAction;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.interact.bbs.action.AbstractBBSUserAction;
import com.huiyee.interact.cb.action.AbstractCbUserAction;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class NameSpaceInterceptor extends AbstractInterceptor
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String intercept(ActionInvocation invocation) throws Exception
	{
		Object act = invocation.getAction();
		String servletPath=invocation.getProxy().getNamespace()+"/"+invocation.getProxy().getActionName();
		if (act instanceof HyAbstractAction)
		{
			HyAbstractAction hyabs=(HyAbstractAction) act;
			String[] strs=servletPath.split("/");
			if(!strs[1].equals("*")&&!strs[1].equals("user")&&!strs[1].equals("page")&&!strs[1].equals("interact")&&!strs[1].equals("weixin"))
			{
			hyabs.setOname(strs[1]);
			}
		}
		if (HyConfig.isRun())
		{
			HttpServletRequest request = (HttpServletRequest) ServletActionContext.getRequest();
			Account account = (Account) request.getSession().getAttribute("account");
			if (account != null)
			{
				if (account.getOwner().getEndtime() == null || account.getOwner().getEndtime().getTime() < new Date().getTime())
				{
					return "outdate";
				}
			}

		}
		else
		{
			Object obj = invocation.getAction();
			if (obj instanceof AbstractAction)
			{
				AbstractAction hd=(AbstractAction) obj;
				hd.beforeHudong();
			}
			if(obj instanceof AbstractBBSUserAction)
			{
				AbstractBBSUserAction bsa=(AbstractBBSUserAction) obj;
				if(!bsa.beforeExcute())
				{
					return "bbserr";
				}
			}
			if(obj instanceof AbstractCbUserAction)
			{
				AbstractCbUserAction bsa=(AbstractCbUserAction) obj;
				if(!bsa.beforeExcute())
				{
					return null;
				}
			}
		}
		return invocation.invoke();

	}

}
