
package project.caidan.mgr.impl;

import java.util.List;

import project.caidan.dao.IAccountTypeDao;
import project.caidan.dto.AccountDto;
import project.caidan.dto.LoginDto;
import project.caidan.mgr.IAccountManager;
import project.caidan.model.CDAccountCpz;
import project.caidan.model.CDAccountDl;
import project.caidan.model.CDAccoutType;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dao.IAccountDao;
import com.huiyee.esite.dao.IWeiXinDao;
import com.huiyee.esite.dao.IWxUserDao;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.util.ToolUtils;

public class AccountManagerImpl implements IAccountManager
{

	private IAccountDao accountDao;
	private IAccountTypeDao accountTypeDao;
	private IWxUserDao wxUserDao;

	public void setAccountDao(IAccountDao accountDao)
	{
		this.accountDao = accountDao;
	}

	public void setAccountTypeDao(IAccountTypeDao accountTypeDao)
	{
		this.accountTypeDao = accountTypeDao;
	}

	public void setWxUserDao(IWxUserDao wxUserDao)
	{
		this.wxUserDao = wxUserDao;
	}

	@Override
	public IDto login(String ownername, String accountname, String password, String ip)
	{
		LoginDto dto = new LoginDto();
		Account account = accountDao.findAccountListByOwner(ownername, accountname, password);
		if (account != null)
		{
			dto.setAccount(account);
			account.getOwner().setPrivileges(accountDao.findOwnerPrivilege(account.getOwner().getId()));
			dto.setType(accountTypeDao.findAccountType(account.getId()));
			accountDao.addLoginLog(ip, account.getId(), account.getOwner().getId(), IPageConstants.LOGIN_NORMAL);
		}
		return dto;
	}

	@Override
	public AccountDto findAccountsByType(Account account, String type, int pageId)
	{
		AccountDto dto = new AccountDto();
		int total = accountTypeDao.findAccountTotal(account.getOwner().getId(), type);
		if (total > 0)
		{
			List<CDAccoutType> list = accountTypeDao.findAccountsByType(account.getOwner().getId(), type, (pageId - 1) * 20, 20);
			for (CDAccoutType t : list)
			{
				if (t.getWxuid() > 0)
				{
					t.setWxUser(wxUserDao.findWxUserByid(t.getWxuid()));
				}
			}
			dto.setList(list);
			dto.setPager(new Pager(pageId, total, 20));
		}
		return dto;
	}

	@Override
	public long saveAccount(Account account, String type, CDAccoutType act)
	{
		int exist = accountTypeDao.findAccountByName(account.getOwner().getId(), act.getUsername());
		if (exist == 0)
		{
			long acid = accountTypeDao.saveAccount(account.getOwner().getId(), act, ToolUtils.getMD5Str(act.getPassword()));
			accountTypeDao.saveAccountType(acid, type);
			return acid;
		} else
		{
			return -1;
		}
	}

	@Override
	public int updateAccountWxuid(long wxuid, long acid, long owner)
	{
		CDAccoutType at = accountTypeDao.findByAcid(acid, owner);
		if (at == null)
		{
			return -1;
		} else if (at.getWxuid() > 0)
		{
			return -2;
		} else
		{
			return accountTypeDao.updateAccountWxuid(acid, wxuid);
		}
	}

	@Override
	public int removeAccType(long acid, Account account)
	{
		CDAccoutType at = accountTypeDao.findByAcid(acid, account.getOwner().getId());
		if (at != null)
		{
			return accountTypeDao.updateAtWxuid(acid);
		}
		return 0;
	}

	@Override
	public int removeAccount(long acid, Account account)
	{
		CDAccoutType at = accountTypeDao.findByAcid(acid, account.getOwner().getId());
		if (at != null)
		{
			int rs = accountTypeDao.removeAccount(acid);
			int rs2 = accountTypeDao.removeAccType(acid);
			return rs + rs2;
		}
		return 0;
	}

	@Override
	public int updatePwd(long acid, Account account, String password)
	{
		CDAccoutType at = accountTypeDao.findByAcid(acid, account.getOwner().getId());
		if (at != null)
		{
			return accountTypeDao.updatePwd(acid, ToolUtils.getMD5Str(password));
		}
		return 0;
	}

	@Override
	public String findAccountTypeByWxuid(long wxuid)
	{
		CDAccoutType sj = accountTypeDao.findAtByWxuid(wxuid);
		CDAccountDl xj = accountTypeDao.findDlByWxuid(wxuid, "Y");
		CDAccountCpz cpz = accountTypeDao.findCpzByWxuid(wxuid, "Y");
		if (sj != null && sj.getType() != "YYR")
		{
			return "SJ";
		} else if (xj != null)
		{
			return "XJ";
		} else if (cpz != null)
		{
			return "CPZ";
		}
		return null;
	}

	@Override
	public AccountDto findAccountById(long acid, Account account)
	{
		AccountDto dto = new AccountDto();
		dto.setAct(accountTypeDao.findByAcid(acid, account.getOwner().getId()));
		return dto;
	}

	@Override
	public long updateAccount(Account account, CDAccoutType act)
	{
		CDAccoutType old = accountTypeDao.findByAcid(act.getId(), account.getOwner().getId());
		if (old.getUsername().equals(act.getUsername()))
		{
			return accountTypeDao.upadte(account.getOwner().getId(), act);
		} else
		{
			int exist = accountTypeDao.findAccountByName(account.getOwner().getId(), act.getUsername());
			if (exist == 0)
			{
				return accountTypeDao.upadte(account.getOwner().getId(), act);
			} else
			{
				return -1;
			}
		}

	}
}
