package com.huiyee.esite.mgr;

import java.util.List;
import java.util.Map;

import com.huiyee.esite.constants.IPrivilegeConstants;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.Owner;
import com.huiyee.esite.model.OwnerPrivilege;
import com.huiyee.esite.model.OwnerSetting;

public interface IAccountManager {
	
	public Account addloginLogAccountListByOwner(String ownerName,String accountName,String password,String ip);
	
	public Account addUnifyLoginByOwnerandAccountId(long ownerId,long accountId,String ip);
	
	/**
	 * 统一平台创建账号,返回accountId
	 * @param ownerId
	 * @param accountId
	 * @param accountname
	 * @return
	 */
	public long addAccount(long ownerId,long accountId,String accountname);
	
	public Owner findOwner(long owner);

	public OwnerSetting findOwnerSetting(long owner);

	public int updateOwnerSetting(OwnerSetting o);

	public Map<Integer, String> findHideControl(long ownerId, long accountId);
	
	public OwnerPrivilege findOwnerPrivilege(long ownerid,long appid);
	
	public int saveOwnerPrivilege(long ownerid,long appid,String status,int level);
	
	public List<OwnerPrivilege> findOwnerPrivilege(long ownerid);
	
}
