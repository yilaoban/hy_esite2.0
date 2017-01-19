package project.caidan.mgr.impl;

import project.caidan.dao.ICaiDanUserDao;
import project.caidan.mgr.ICaiDanUserMgr;

import java.util.List;

import com.huiyee.esite.mgr.imp.AbstractMgr;
import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.model.OrderGoods;

public class CaiDanUserMgrImpl extends AbstractMgr implements ICaiDanUserMgr {

	private ICaiDanUserDao cdUserDao;

	public void setCdUserDao(ICaiDanUserDao cdUserDao) {
		this.cdUserDao = cdUserDao;
	}

	@Override
	public int findHyUserCount(long owner, HyUser filter) {
		return cdUserDao.findHyUserCount(owner, filter);
	}

	@Override
	public List<HyUser> findHyUserList(long owner, HyUser filter)
	{
		return cdUserDao.findHyUserList(owner, filter);
	}
	
	@Override
	public List<HyUser> findHyUserList(long owner, HyUser filter, int start, int rows) {
		return cdUserDao.findHyUserList(owner, filter, start, rows);
	}

	@Override
	public int findOrderGoodsCount(long hyuid) {
		return cdUserDao.findOrderGoodsCount(hyuid);
	}

	@Override
	public List<OrderGoods> findOrderGoodsList(long hyuid, int start, int rows) {
		return cdUserDao.findOrderGoodsList(hyuid, start, rows);
	}

	@Override
	public HyUser findHyUser(long owner, long wxuid) {
		return cdUserDao.findHyUser(owner, wxuid);
	}

	@Override
	public int updateTelByWxuid(long owner, long wxuid, String telphone) {
		HyUser user=cdUserDao.findHyUserByWxuid(owner, wxuid);
		int rs=cdUserDao.updateTelByWxuid(owner, wxuid, telphone);
		if(user.getTelphone()==null&&rs==1){
			this.updateBalance(user.getId(), 60, "°ó¶¨ÊÖ»ú", "CDD", "CDP", 0);
		}
		return rs;
	}

}
