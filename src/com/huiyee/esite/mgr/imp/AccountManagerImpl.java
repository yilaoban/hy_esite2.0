package com.huiyee.esite.mgr.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dao.IAccountDao;
import com.huiyee.esite.dao.IAccountHideDao;
import com.huiyee.esite.mgr.IAccountManager;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.Owner;
import com.huiyee.esite.model.OwnerPrivilege;
import com.huiyee.esite.model.OwnerSetting;

public class AccountManagerImpl implements IAccountManager {
	private IAccountDao accountDao;
	public void setAccountHideDao(IAccountHideDao accountHideDao) {
		this.accountHideDao = accountHideDao;
	}
	private IAccountHideDao accountHideDao;
	
	public void setAccountDao(IAccountDao accountDao) {
		this.accountDao = accountDao;
	}

    @Override
	public Account addloginLogAccountListByOwner(String ownerName, String accountName, String password,String ip) {
        Account account = accountDao.findAccountListByOwner(ownerName, accountName, password);
        if (account != null){
            accountDao.addLoginLog(ip, account.getId(), account.getOwner().getId(), IPageConstants.LOGIN_NORMAL);
        }
        return account;
    }
    
    @Override
	public Account addUnifyLoginByOwnerandAccountId(long ownerId,
			long accountId, String ip) {
		 Account account = accountDao.findAccountByOwnerIdandAccountId(ownerId, accountId);
		if (account != null) {
			account.getOwner().setPrivileges(accountDao.findOwnerPrivilege(ownerId));
			accountDao.addLoginLog(ip, account.getId(), account.getOwner()
					.getId(), IPageConstants.LOGIN_UNIFY);
		}
		return account;
	}

	@Override
	public long addAccount(long ownerId, long accountId, String accountname) {
		long id=accountDao.findAccountId(ownerId,accountId,accountname);
		if(id==0){
			return accountDao.addAccount(ownerId, accountId, accountname);
		}
		return id;
	}

	@Override
	public Owner findOwner(long owner) {
		return accountDao.findOwner(owner);
	}
	
	@Override
	public OwnerSetting findOwnerSetting(long owner)
	{
		return accountDao.findOwnerSetting(owner);
	}
	
	@Override
	public int updateOwnerSetting(OwnerSetting set)
	{
		return accountDao.updateOwnerSetting(set);
	}
	@Override
	public Map<Integer, String> findHideControl(long ownerId, long accountId) {
		Map<Integer, String> map=new HashMap<Integer, String>();
		List<Long> ids=accountHideDao.findHidIds(ownerId, accountId, IPageConstants.ACCOUNT_HIDE_LEFT);
		for(int i=1;i<=7;i++){
			Long value=Long.valueOf(i);
			if(ids.contains(value)){
				map.put(i, "N");
			}else{
				map.put(i, "Y");
			}
			
		}
		return map;
	}

	@Override
	public OwnerPrivilege findOwnerPrivilege(long ownerid, long appid)
	{
		return accountDao.findOwnerPrivilege(ownerid, appid);
	}

	@Override
	public int saveOwnerPrivilege(long ownerid, long appid,String status, int level)
	{
		return accountDao.saveOwnerPrivilege(ownerid, appid, status, level);
	}

	@Override
	public List<OwnerPrivilege> findOwnerPrivilege(long ownerid)
	{
		return accountDao.findOwnerPrivilege(ownerid);
	}
}
