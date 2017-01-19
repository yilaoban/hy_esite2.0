package project.chuanmc.mgr.impl;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dao.IAccountDao;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.Account;

import project.chuanmc.dao.IAccountTypeDao;
import project.chuanmc.dto.LoginDto;
import project.chuanmc.mgr.IAccountManager;


public class AccountManagerImpl implements IAccountManager{
	
	private IAccountDao accountDao;
	
	public void setAccountDao(IAccountDao accountDao)
	{
		this.accountDao = accountDao;
	}

	@Override
	public IDto login(String ownername, String accountname, String password, String ip)
	{
		LoginDto dto = new LoginDto();
		Account account = accountDao.findAccountListByOwner(ownername, accountname, password);
		if(account != null){
			dto.setAccount(account);
			account.getOwner().setPrivileges(accountDao.findOwnerPrivilege(account.getOwner().getId()));
			accountDao.addLoginLog(ip, account.getId(), account.getOwner()
					.getId(), IPageConstants.LOGIN_NORMAL);
		}
		return dto;
	}
	
}
