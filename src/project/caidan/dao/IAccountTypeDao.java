package project.caidan.dao;

import java.util.List;

import project.caidan.model.CDAccountCpz;
import project.caidan.model.CDAccountDl;
import project.caidan.model.CDAccoutType;

import com.huiyee.esite.model.Account;

public interface IAccountTypeDao {
	
	public String findAccountType(long accountid);
	
	public List<Account> findAccountForWay(long owner);

	public List<CDAccoutType> findAccountsByType(long owner, String type, int start, int size);

	public long saveAccount(long id, CDAccoutType act, String password);

	public void saveAccountType(long acid, String type);

	public int findAccountByName(long id, String username);

	public int findAccountTotal(long id, String type);

	public int updateAccountWxuid(long accountid, long wxuid);

	public CDAccoutType findByAcid(long accountid, long owner);

	public int updateAtWxuid(long acid);

	public int removeAccount(long acid);

	public int removeAccType(long acid);

	public CDAccoutType findAtByWxuid(long wxuid);

	public CDAccountDl findDlByWxuid(long wxuid, String status);

	public CDAccountCpz findCpzByWxuid(long wxuid, String status);

	public int updatePwd(long acid, String md5Str);

	public long upadte(long id, CDAccoutType act);

	public List<CDAccountCpz> findCpzsByPid(long pid, int start, int size);

	public CDAccoutType findByClid(long wayid, long owner);

}
