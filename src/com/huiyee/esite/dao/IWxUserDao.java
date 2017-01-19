package com.huiyee.esite.dao;

import java.util.List;

import com.huiyee.esite.model.WxUser;

public interface IWxUserDao {

	public WxUser findWxUserByOpenid(String openid);

	public WxUser findWxUserByid(long wxuid);
	
	public WxUser findWxUserByHyuid(long hyuid);
	
	public int findTotalWxUserByOwner(long owner,String nickname);
	
	public List<WxUser> findWxUserByOwner(long owner,String nickname,int start,int size);

}
