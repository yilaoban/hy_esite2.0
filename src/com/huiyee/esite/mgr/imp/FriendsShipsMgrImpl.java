package com.huiyee.esite.mgr.imp;

import org.apache.commons.lang.StringUtils;

import com.huiyee.esite.dao.IFriendsShipsDao;
import com.huiyee.esite.mgr.IFriendsShipsMgr;
import com.huiyee.esite.model.SinaToken;

public class FriendsShipsMgrImpl extends AbstractMgr implements IFriendsShipsMgr
{
	private IFriendsShipsDao friendsShipsDao;

	public void setFriendsShipsDao(IFriendsShipsDao friendsShipsDao)
	{
		this.friendsShipsDao = friendsShipsDao;
	}

	@Override
	public String saveFriendsShips(long pageid, long wbuid, long cid)
	{
		SinaToken tokenModel = friendsShipsDao.findToken(pageid, wbuid);
		if (tokenModel != null && !StringUtils.isEmpty(tokenModel.getToken()))
		{
			try
			{
				this.justWS.guanzhu(tokenModel.getToken(), cid);
				return "Y";
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return "新浪接口错误";
			}
		}
		else
		{
			return "token不存在";
		}
	}
}
