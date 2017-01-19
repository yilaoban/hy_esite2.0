package com.huiyee.interact.bbs.mgr;

import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.WxUser;
import com.huiyee.interact.bbs.model.BBSUser;
import com.huiyee.interact.bbs.model.BBSUserActiveLevel;
import com.huiyee.interact.bbs.model.BBSUserOnline;

public interface IBBSUserMgr
{
	public int updateBBSUserbyId(long id);
	
	public long saveBBSLoginLog(long userid,String ip);
	
	public BBSUserActiveLevel findBBSUseActiveLevel(long userid);
	
	public BBSUserOnline findBBSUserOnlineById(long userid);
	
	public int updateBBSUserLevlebyId(long id);
	
	public Page findJspnameByForumid(long forumid);
	
	public Page findRegJspnameByForumid(long forumid);
	
	public int updateOperateNum(String column, long id);
	
	public BBSUser findBBSUserByHyuid(long hyuid);
	
	public BBSUser saveBBSUser(long hyuid,long owner,String ip);
}
