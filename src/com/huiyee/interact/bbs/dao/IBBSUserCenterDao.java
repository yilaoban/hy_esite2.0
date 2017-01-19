package com.huiyee.interact.bbs.dao;

import java.util.List;

import com.huiyee.interact.bbs.model.BBSTopic;
import com.huiyee.interact.bbs.model.BBSUser;

public interface IBBSUserCenterDao
{
	public BBSUser findUserInfoByUserid(long userid);
	
	public List<BBSTopic> findMyTopic(long userid);
	
	public List<BBSTopic> bbsMyReply(long userid);
}
