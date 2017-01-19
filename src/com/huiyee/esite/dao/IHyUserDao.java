package com.huiyee.esite.dao;

import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.bbs.model.BBSUser;
import com.huiyee.interact.bbs.model.SMS;

public interface IHyUserDao
{
	public long findIdByCookie(String cookie,long owner);
	
	public long saveCookieUserByCookie(String cookie,long owner);
	
	public HyUser findHyuserByUP(String username,String password,long owner);
	
	public long saveHyUserByUP(String username,String password,long owner,String telphone,String email,String nickname,String hudetail,String img,VisitUser vu);
	
	public long saveHyUser(String username,String password,long owner,BBSUser bs);
	
	public void updateHyUserByHyuid(String username,String password,String telphone,String email,String nickname,String hudetail,String img,long hyuid);
	
	public long findIdByWx(long wxuid,long owner);
	
	public void saveHyUserByWxuid(long wxuid,long owner);
	
	public int findBBSUserByName(String username,long owner);
	
	public long findOwnerByForumid(long forumid);
	
	public HyUser findHyUserById(long hyuserid);
	
	public int updateHyUserWbuidById(long wbuid,long hyuserid);
	
	public int updateHyUserWxuidById(long wxuid, long hyuserid);
	
	public HyUser findHyUserBywxuid(long wxuid,long owner);
	
	public SMS findPPSmsbyOwner(long owner);

	public String findNickname(long entityid, long wbuid, long wxuid);

	public int updateVipInfo(HyUser hyUser);
	
	public int updateHyUserLevelidById(long levelid,long hyuid);
}
