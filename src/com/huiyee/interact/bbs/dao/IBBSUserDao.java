package com.huiyee.interact.bbs.dao;

import com.huiyee.esite.model.Page;
import com.huiyee.interact.bbs.model.BBSUser;
import com.huiyee.interact.bbs.model.BBSUserActiveLevel;
import com.huiyee.interact.bbs.model.BBSUserOnline;

public interface IBBSUserDao
{	
	public BBSUser findBBSUserByHyuid(long hyuid);
	
	public long saveBBSUser(BBSUser bbsUser,String ip);
	
	public int updateBBSUserbyId(long id);
	
	public int saveBBSUserOnline(long id);
	
	public long saveBBSLoginLog(long userid, String ip);
	
	public BBSUserActiveLevel findBBSUseActiveLevel(long userid);
	
	public BBSUserOnline findBBSUserOnlineById(long userid);
	
	public int updateBBSUserLevlebyId(long id);
	
	public Page findJspnameByForumid(long forumid);
	
	public Page findRegJspnameByForumid(long forumid);
	
	public int updateOperateNum(String column, long id);
	
}
