
package com.huiyee.esite.mgr.imp;

import org.apache.commons.lang3.StringUtils;
import com.huiyee.esite.dao.IHyUserDao;
import com.huiyee.esite.dto.HuDetail;
import com.huiyee.esite.mgr.IHyUserMgr;
import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.model.WxUser;
import com.huiyee.esite.util.JsonStringUtil;
import com.huiyee.interact.bbs.dao.IBBSUserDao;
import com.huiyee.interact.bbs.model.BBSUser;
import com.huiyee.interact.bbs.model.SMS;

public class HyUserMgr implements IHyUserMgr
{

	private IHyUserDao hyUserDao;
	private IBBSUserDao bbsUserDao;

	public void setHyUserDao(IHyUserDao hyUserDao)
	{
		this.hyUserDao = hyUserDao;
	}

	public void setBbsUserDao(IBBSUserDao bbsUserDao)
	{
		this.bbsUserDao = bbsUserDao;
	}

	@Override
	public long updateCookieUserByCookie(String cookie, long owner)
	{
		long id = hyUserDao.findIdByCookie(cookie, owner);
		if (id == 0)
		{
			id = hyUserDao.saveCookieUserByCookie(cookie, owner);
		}
		return id;
	}


	@Override
	public HyUser saveHyUser(String username,String password,String telphone,String email,String nickname,HuDetail hudetail,String img,long owner,String ip,VisitUser vu)
	{
		HyUser hu=new HyUser();
		if (vu.getHyUser() != null && vu.getHyUser().getId() > 0)
		{
			if(StringUtils.isBlank(img)){
				img = "http://img.huiyee.com/mr.jpg";
			}
			if (StringUtils.isBlank(nickname))
			{
				nickname = username;
			}
			hyUserDao.updateHyUserByHyuid(username, password,telphone,email,nickname,JsonStringUtil.Obj2String(hudetail),img, vu.getHyUser().getId());

		} else
		{
			hyUserDao.saveHyUserByUP(username, password, owner,telphone,email,nickname,JsonStringUtil.Obj2String(hudetail),img, vu);
		}
		hu = hyUserDao.findHyuserByUP(username, password, owner);
//		if (StringUtils.isBlank(hu.getNickname()))
//		{
//			hu.setNickname(username);
//		}
//		if(StringUtils.isBlank(hu.getImg())){
//			hu.setImg("http://img.huiyee.com/mr.jpg");
//		}

//		bs.setId(bsid);
//		bbsUserDao.saveBBSUserOnline(bsid);
//		bbsUserDao.updateBBSUserbyId(bsid);
//		long logid = bbsUserDao.saveBBSLoginLog(bsid, ip);
//		bs.setLogid(logid);
//		bs.setLogintime(System.currentTimeMillis());
		return hu;
	}

	@Override
	public int findBBSUserByName(String username, long owner)
	{
		return hyUserDao.findBBSUserByName(username, owner);
	}

	@Override
	public long getBbsowner(long forumid)
	{
		return hyUserDao.findOwnerByForumid(forumid);
	}

	@Override
	public HyUser findHyUserById(long hyuserid)
	{
		return hyUserDao.findHyUserById(hyuserid);
	}

	@Override
	public int updateHyUserWbuidById(long wbuid, long hyuserid)
	{
		return hyUserDao.updateHyUserWbuidById(wbuid, hyuserid);
	}

	@Override
	public int updateHyUserWxuidById(long wxuid, long hyuserid)
	{
		return hyUserDao.updateHyUserWxuidById(wxuid, hyuserid);
	}

	@Override
	public HyUser findHyUserBywxuid(long wxuid, long owner)
	{
		return hyUserDao.findHyUserBywxuid(wxuid, owner);
	}

	@Override
	public SMS findPPSmsbyOwner(long owner)
	{
		return hyUserDao.findPPSmsbyOwner(owner);
	}


	@Override
	public HyUser saveHyUserByWxuid(long wxuid, long owner)
	{
		HyUser hu=hyUserDao.findHyUserBywxuid(wxuid, owner);
		if(hu==null)
		{
			 hyUserDao.saveHyUserByWxuid(wxuid, owner);
		}
		    hu=hyUserDao.findHyUserBywxuid(wxuid, owner);
		return hu;
	}

	@Override
	public HyUser login(String username, String password, long owner)
	{
		return hyUserDao.findHyuserByUP(username, password, owner);
	}

	@Override
	public int updateVipInfo(HyUser hyUser)
	{
		if(hyUser.getWxuid()>0)
			return hyUserDao.updateVipInfo(hyUser);
		return 0;
	}
}
