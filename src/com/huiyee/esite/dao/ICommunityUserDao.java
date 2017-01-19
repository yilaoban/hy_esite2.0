package com.huiyee.esite.dao;

import java.util.List;

import com.huiyee.esite.model.BBSJfLevel;
import com.huiyee.esite.model.BBSJfRule;
import com.huiyee.interact.bbs.model.BBSTopic;
import com.huiyee.interact.bbs.model.BBSUser;

public interface ICommunityUserDao
{
	public int saveBBSUserJfRule(int action, int jifen,long owner);
	
	public List<BBSJfRule> findBBSUserJfRuleList(long owner);
	
	public BBSJfRule findBBSUSErJfRuleByAction(int action,long owner);
	
	public BBSJfRule findBBSUSErJfRuleById(long ruleid);
	
	public int updateJfRuleById(long ruleid, int jifen);
	
	public int deleteJfRule(long ruleid);
	
	public List<BBSJfLevel> findBBSUserJflevelList(long owner);
	
	public int saveBBSUserJfLevel(String level_name, long require_jf, long owner,int level_id);
	
	public BBSJfLevel findBBSUserJfLevelById(long levelid);
	
	public int updateBBSUserJfLevel(String level_name, long require_jf, long levelid);
	
	public int deleteJfLevel(long levelid);
	
	public List<BBSUser> findBBSUserInfoList(long owner,int start,int size);
	
	public List<BBSUser> findBBSUserBalckList(long owner,int start,int size);
	
	public int findBBSUserInfoTotal(long owner);
	
	public int findBBSUserBalck(long owner);
	
	public List<BBSTopic> fingBBSTopicByUid(long uid);
	
	public int findTopTotalByUid(long uid);
	
	public int findBBSUserInfoListByNicknameAndLevelidTotal(long owner,String nickname,long levelid);
	
	public List<BBSUser> findBBSUserInfoListByNicknameAndLevelidTotal(long owner,String nickname,long levelid,int start,int size);
	
	public int addUserBalck(long uid,String isbalck);
	
	public int findCountByUid(long uid,int atype);
}
