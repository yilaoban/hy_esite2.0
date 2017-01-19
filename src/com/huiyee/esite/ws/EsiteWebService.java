package com.huiyee.esite.ws;

import java.util.HashMap;
import java.util.Map;

import javax.jws.WebService;

@WebService
public interface EsiteWebService {

	/**
	 * 查找账号是否存在 
	 * @param ownerId
	 * @param accountId
	 * @param accountname
	 * @return 1表示存在 0表示不存在
	 */
//	public int findAccount(long ownerId,long accountId,String accountname);
	
	/**
	 * 同一平台创建账号,返回accountId
	 * @param ownerId
	 * @param accountId
	 * @param accountname
	 * @return
	 */
	public long addAccount(long ownerId,long accountId,String accountname,int dbId);
	
	/**
	 * 易邮数据
	 * @param owner
	 * @param dbId
	 * @return
	 */
	public String findEsiteData(long owner,int dbId);
	
	
	/**
	 * 更新易站 es_owner_setting
	 * @param owner
	 * @param odomain
	 * @param wxappid
	 * @param wxsecret
	 * @return
	 */
	public int updateOwnerSetting(String json);
	
	/**
	 * 获取易站owner_setting
	 * @param id
	 * @return json
	 */
	public String findEsiteOwnerSet(long id);
	
	/**
	 * 获取bbs_account信息
	 * @param owner
	 * @return
	 */
	public String findbbsAccount(long owner);
	
	/**
	 * 更新bbs_account
	 * @param json
	 * @return
	 */
	public int updatebbsAccount(String json);
}
