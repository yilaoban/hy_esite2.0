package com.huiyee.esite.ws.imp;

import java.util.List;

import com.google.gson.Gson;
import com.huiyee.esite.dto.BBSAccount;
import com.huiyee.esite.dto.EsForum;
import com.huiyee.esite.dto.EsbbsDto;
import com.huiyee.esite.mgr.IAccountManager;
import com.huiyee.esite.model.OwnerSetting;
import com.huiyee.esite.ws.EsiteWebService;
import com.huiyee.interact.bbs.mgr.IBBSMgr;


public class EsiteWebServiceImpl implements EsiteWebService{
	
	private IAccountManager accountManager;
	private IBBSMgr bbsMgr;

	@Override
	public long addAccount(long ownerId, long accountId, String accountname,
			int dbId) {
		return accountManager.addAccount(ownerId, accountId, accountname);
	}

	@Override
	public String findEsiteData(long owner, int dbId) {
		return null;
	}

	public void setBbsMgr(IBBSMgr bbsMgr)
	{
		this.bbsMgr = bbsMgr;
	}

	public void setAccountManager(IAccountManager accountManager) {
		this.accountManager = accountManager;
	}
	
	@Override
	public String findbbsAccount(long owner)
	{
		List<BBSAccount> list=bbsMgr.findBBSAccount(owner);
		List<EsForum> forumlist = bbsMgr.findBbsForumByOwner(owner);
		EsbbsDto dto = new EsbbsDto();
		dto.setForumList(forumlist);
		dto.setRelationList(list);
		return new Gson().toJson(dto);
	}
	
	@Override
	public String findEsiteOwnerSet(long owner)
	{
		OwnerSetting dto=accountManager.findOwnerSetting(owner);
		return new Gson().toJson(dto);
	}
	@Override
	public int updatebbsAccount(String json)
	{
		BBSAccount bbsa=new Gson().fromJson(json, BBSAccount.class);
		return bbsMgr.updateBbsa(bbsa);
	}
	
	@Override
	public int updateOwnerSetting(String json)
	{
		OwnerSetting o=new Gson().fromJson(json, OwnerSetting.class);
		return accountManager.updateOwnerSetting(o);
	}
	
}
