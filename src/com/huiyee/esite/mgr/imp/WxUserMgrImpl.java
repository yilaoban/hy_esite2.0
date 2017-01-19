package com.huiyee.esite.mgr.imp;

import java.util.List;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.dao.IWxUserDao;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.dto.WeiKaQuanDto;
import com.huiyee.esite.mgr.IWxUserMgr;
import com.huiyee.esite.model.WxUser;

public class WxUserMgrImpl implements IWxUserMgr {

	private IWxUserDao wxUserDao;

	public void setWxUserDao(IWxUserDao wxUserDao) {
		this.wxUserDao = wxUserDao;
	}

	@Override
	public WxUser findWxUserByOpenid(String openid) {
		return wxUserDao.findWxUserByOpenid(openid);
	}

	@Override
	public IDto findWxUserByOwner(long owner,int pageId,String nickname)
	{
		WeiKaQuanDto dto = new WeiKaQuanDto();
		int total = wxUserDao.findTotalWxUserByOwner(owner,nickname);
		int rows = 10;
		int start = (pageId - 1) * 10;
		if(total > 0){
			List<WxUser> userList = wxUserDao.findWxUserByOwner(owner,nickname,start,rows);
			dto.setUserList(userList);
		}
		Pager pager = new Pager(pageId, total, rows);
		dto.setPager(pager);
		return dto;
	}

}
