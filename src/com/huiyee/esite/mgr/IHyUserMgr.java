package com.huiyee.esite.mgr;

import com.huiyee.esite.dto.HuDetail;
import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.bbs.model.SMS;

public interface IHyUserMgr
{
  public long updateCookieUserByCookie(String cookie,long owner);
  
  public HyUser saveHyUser(String username,String password,String telphone,String email,String nickname,HuDetail hudetail,String img,long owner,String ip,VisitUser vu);
  
  public HyUser login(String username,String password,long owner);
  
  public int findBBSUserByName(String username,long owner);
  
  public long getBbsowner(long forumid);
  
  public HyUser findHyUserById(long hyuserid);
  
  public HyUser saveHyUserByWxuid(long wxuid,  long owner);
  
  public int updateHyUserWbuidById(long wbuid,long hyuserid);
  
  public int updateHyUserWxuidById(long wxuid,long hyuserid);
  
  public HyUser findHyUserBywxuid(long wxuid,long owner);
  
  public SMS findPPSmsbyOwner(long owner);

  /**
   * update username,telphone,gender,birthday with hyuser.wxuid 
   * @param hyUser
   * @return
   */
  public int updateVipInfo(HyUser hyUser);
  
}
