package com.huiyee.esite.dao;

import java.util.List;

import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.Owner;
import com.huiyee.esite.model.OwnerSetting;
import com.huiyee.esite.model.OwnerPrivilege;

public interface IAccountDao {
	
	public Account findAccountListByOwner(String ownerName, String accountName,
			String password);
	
	 /**
     * 登陆日志
     */
    public long addLoginLog(final String ip,final long accountid,final long ownerid, final String logintype); 
    
    public Account findAccountByOwnerIdandAccountId(long ownerId, long accountId);
    
    public List<OwnerPrivilege> findOwnerPrivilege(long ownerid);
	
	/**
	 * 同一平台创建账号,返回accountId
	 * @param ownerId
	 * @param accountId
	 * @param accountname
	 * @return
	 */
	public long addAccount(long ownerId,long accountId,String accountname);
	
	public int findAccount(long ownerId,long accountId,String accountname);
	
	public Owner findOwner(long owner);

	public long findAccountId(long ownerId, long accountId, String accountname);

	public OwnerSetting findOwnerSetting(long owner);

	public int updateOwnerSetting(OwnerSetting set);
	
	public OwnerPrivilege findOwnerPrivilege(long ownerid, long appid);
	
	public int saveOwnerPrivilege(long ownerid, long appid,String status, int level);

}
