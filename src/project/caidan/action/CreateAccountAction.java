
package project.caidan.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import project.caidan.constants.ICaiDanConstants;
import project.caidan.dto.AccountDto;
import project.caidan.mgr.IAccountManager;
import project.caidan.model.CDAccoutType;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.VisitUser;
import com.opensymphony.xwork2.ActionContext;

public class CreateAccountAction extends AbstractAction
{

	private IAccountManager accountManager;
	/*
	 * ALZ-渠道总监；PRZ-渠道经理；ADM-系统管理员;YYR-运维经理
	 */
	private String type = "ALZ";
	private List<CDAccoutType> accounts;
	private int pageId = 1;
	private String username;
	private String password1;
	private String password2;
	private AccountDto dto;
	private long acid;
	private CDAccoutType act;

	@Override
	public String execute() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		String accountType = (String) ActionContext.getContext().getSession().get("accountType");
		if (ICaiDanConstants.ACCOUNT_TYPE_系统管理员.equals(accountType))
		{
			dto = (AccountDto) accountManager.findAccountsByType(account, type, pageId);
			return SUCCESS;
		} else
		{
			return ERROR;
		}
	}

	public String addAccount() throws Exception
	{
		String accountType = (String) ActionContext.getContext().getSession().get("accountType");
		if (ICaiDanConstants.ACCOUNT_TYPE_系统管理员.equals(accountType))
		{
			return SUCCESS;
		} else
		{
			return ERROR;
		}
	}

	public String saveAccount() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		String accountType = (String) ActionContext.getContext().getSession().get("accountType");
		if (ICaiDanConstants.ACCOUNT_TYPE_系统管理员.equals(accountType))
		{
			if (act == null)
			{
				this.addActionError("参数错误!");
			} else if (StringUtils.isEmpty(act.getUsername()))
			{
				this.addActionError("帐号不能为空!");
			} else if (StringUtils.isEmpty(password1))
			{
				this.addActionError("密码不能为空!");
			} else if (!password1.equals(password2))
			{
				this.addActionError("确认密码不能为空!");
			} else
			{
				act.setPassword(password1);
				long rs = accountManager.saveAccount(account, type, act);
				if (rs > 0)
				{
					return SUCCESS;
				} else
				{
					this.addActionError("帐号名重复!");
				}
			}
			return INPUT;
		} else
		{
			return ERROR;
		}
	}

	public String editAct() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		String accountType = (String) ActionContext.getContext().getSession().get("accountType");
		if (ICaiDanConstants.ACCOUNT_TYPE_系统管理员.equals(accountType))
		{
			dto = (AccountDto) accountManager.findAccountById(acid, account);
		} else
		{
			return ERROR;
		}
		return SUCCESS;
	}

	public String editActSub() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		String accountType = (String) ActionContext.getContext().getSession().get("accountType");
		if (ICaiDanConstants.ACCOUNT_TYPE_系统管理员.equals(accountType))
		{

			if (StringUtils.isEmpty(act.getUsername()))
			{
				this.addActionError("帐号不能为空!");
			} else
			{
				long rs = accountManager.updateAccount(account, act);
				if (rs > 0)
				{
					return SUCCESS;
				} else
				{
					this.addActionError("帐号名重复!");
				}
			}
			return INPUT;
		} else
		{
			dto = (AccountDto) accountManager.findAccountById(acid, account);
			return ERROR;
		}
	}

	public String bindWxuid() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser vu = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
		int result = 0;
		long wxuid = 0;
		if (vu != null && vu.getCd() == 1)
		{
			wxuid = vu.getWxuid();
			result = accountManager.updateAccountWxuid(wxuid, acid, this.getOwner());
		}
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	public String jiechubangding() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		String accountType = (String) ActionContext.getContext().getSession().get("accountType");
		int result = 0;
		if (ICaiDanConstants.ACCOUNT_TYPE_系统管理员.equals(accountType))
		{
			result = accountManager.removeAccType(acid, account);
		} else
		{
			result = -1;
		}
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	public String removeAccount() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		String accountType = (String) ActionContext.getContext().getSession().get("accountType");
		int result = 0;
		if (ICaiDanConstants.ACCOUNT_TYPE_系统管理员.equals(accountType))
		{
			result = accountManager.removeAccount(acid, account);
		} else
		{
			result = -1;
		}
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	public String updatePwd() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		String accountType = (String) ActionContext.getContext().getSession().get("accountType");
		int result = 0;
		if (ICaiDanConstants.ACCOUNT_TYPE_系统管理员.equals(accountType))
		{
			result = accountManager.updatePwd(acid, account, password1);
		} else
		{
			result = -1;
		}
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;

	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public List<CDAccoutType> getAccounts()
	{
		return accounts;
	}

	public void setAccounts(List<CDAccoutType> accounts)
	{
		this.accounts = accounts;
	}

	public IAccountManager getAccountManager()
	{
		return accountManager;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public void setAccountManager(IAccountManager accountManager)
	{
		this.accountManager = accountManager;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword1()
	{
		return password1;
	}

	public void setPassword1(String password1)
	{
		this.password1 = password1;
	}

	public String getPassword2()
	{
		return password2;
	}

	public void setPassword2(String password2)
	{
		this.password2 = password2;
	}

	public AccountDto getDto()
	{
		return dto;
	}

	public void setDto(AccountDto dto)
	{
		this.dto = dto;
	}

	public long getAcid()
	{
		return acid;
	}

	public void setAcid(long acid)
	{
		this.acid = acid;
	}

	public CDAccoutType getAct()
	{
		return act;
	}

	public void setAct(CDAccoutType act)
	{
		this.act = act;
	}

}
