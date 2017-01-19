package com.huiyee.esite.mgr;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.bbs.model.BBSUser;

public interface ICommunityUserMgr
{
	public IDto findBBSUserJfRuleList(long owner);
	
	public int saveBBSUserJfRule(int action,int jifen,long owner);
	
	public IDto findBBSUSErJfRuleById(long ruleid);
	
	public int updateJfRuleById(long ruleid,int jifen);
	
	public int deleteJfRule(long ruleid);
	
	public IDto findBBSUserJflevelList(long owner);
	
	public int saveBBSUserJfLevel(String level_name,long require_jf,long owner);
	
	public IDto findBBSUserJfLevelById(long levelid);
	
	public int updateBBSUserJfLevel(String level_name,long require_jf,long levelid,long owner);
	
	public int deleteJfLevel(long levelid);
	
	public IDto findBBSUserInfoList(long owner,int pageId);
	
	public IDto findBBSUserBalck(long owner,int pageId);
	
	public IDto findBBSUserInfoListByNicknameAndLevelid(long owner,int pageId,String nickname,long levelid);
	
	public int addUserBalck(long uid,String isbalck);
	
	public int saveBBSUser(String username,String password,BBSUser bbsUser,long owner,String ip);
}
