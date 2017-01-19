package com.huiyee.esite.mgr;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.WxUser;

public interface IWxUserMgr {

	public WxUser findWxUserByOpenid(String openid);
	
	public IDto findWxUserByOwner(long owner,int pageId,String nickname);
}
