package project.caidan.mgr;

import project.caidan.dto.AccountDto;
import project.caidan.model.CDAccoutType;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.Account;

public interface IAccountManager {
	
	public IDto login(String ownername,String accountname,String password,String ip);

	public IDto findAccountsByType(Account account, String type, int pageId);

	public int updateAccountWxuid(long wxuid, long acid, long owner);

	public int removeAccType(long acid, Account account);

	public int removeAccount(long acid, Account account);
	
	public String findAccountTypeByWxuid(long wxuid);

	public int updatePwd(long acid, Account account, String password1);

	public IDto findAccountById(long acid, Account account);

	public long updateAccount(Account account, CDAccoutType act);

	public long saveAccount(Account account, String type, CDAccoutType act);
}
