package com.huiyee.esite.action;

import org.apache.commons.lang3.StringUtils;

import com.huiyee.esite.mgr.IHyUtilMgr;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.util.HyConfig;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public abstract class HyAbstractAction extends ActionSupport
{

	/**
	 * 公用action,所有action都必须继承
	 */
	private static final long serialVersionUID = 1L;
	private String oname;
	private long owner;
	private IHyUtilMgr hyUtilMgr;

	public long getOwner()
	{
		if (owner > 0)
		{
			return owner;
		}
		if (HyConfig.isRun())
		{
			Account account = (Account) ActionContext.getContext().getSession().get("account");
			if (account != null && account.getOwner() != null)
			{
				return account.getOwner().getId();
			}
			else
			{
				if (oname == null || oname.equalsIgnoreCase("user") || oname.equalsIgnoreCase("page") || oname.equalsIgnoreCase("loadpage") || oname.equalsIgnoreCase("content") || oname.equalsIgnoreCase("interact") || oname.equalsIgnoreCase("data") || oname.equalsIgnoreCase("template")
						|| oname.equalsIgnoreCase("material") || oname.equalsIgnoreCase("weixin"))
				{
					return 0;
				}
				return hyUtilMgr.findOwnerByName(oname);
			}
		}
		else
		{
			if (oname == null || oname.equalsIgnoreCase("user") || oname.equalsIgnoreCase("page") || oname.equalsIgnoreCase("loadpage") || oname.equalsIgnoreCase("content") || oname.equalsIgnoreCase("interact") || oname.equalsIgnoreCase("data") || oname.equalsIgnoreCase("template")
					|| oname.equalsIgnoreCase("material") || oname.equalsIgnoreCase("weixin"))
			{
				return 0;
			}
			return hyUtilMgr.findOwnerByName(oname);
		}

	}

	public void setOwner(long owner)
	{
		this.owner = owner;
	}

	public void setHyUtilMgr(IHyUtilMgr hyUtilMgr)
	{
		this.hyUtilMgr = hyUtilMgr;
	}

	public String getOname()
	{
		String rs = "";
		if (StringUtils.isNotBlank(oname))
		{
			rs = oname;
		}
		else if (HyConfig.isRun())
		{
			Account account = (Account) ActionContext.getContext().getSession().get("account");
			if (account != null && account.getOwner() != null)
				rs = account.getOwner().getEntity();
		}
		return rs;
	}

	public String getOnameUrl()
	{
		String rs = "";
		if (StringUtils.isNotBlank(oname))
		{
			rs = "/" + oname;
		}
		else if (HyConfig.isRun())
		{
			Account account = (Account) ActionContext.getContext().getSession().get("account");
			if (account != null && account.getOwner() != null)
				rs = "/" + account.getOwner().getEntity();
		}
		return rs;
	}

	public void setOname(String oname)
	{
		this.oname = oname;
	}

}
