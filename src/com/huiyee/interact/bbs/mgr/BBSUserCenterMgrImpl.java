package com.huiyee.interact.bbs.mgr;

import java.util.List;

import com.huiyee.interact.bbs.dao.IBBSUserCenterDao;
import com.huiyee.interact.bbs.dto.BBSUserCenterDto;
import com.huiyee.interact.bbs.dto.IDto;
import com.huiyee.interact.bbs.model.BBSTopic;
import com.huiyee.interact.bbs.model.BBSUser;

public class BBSUserCenterMgrImpl implements IBBSUserCenterMgr
{
	private IBBSUserCenterDao bbsUserCenterDao;

	public void setBbsUserCenterDao(IBBSUserCenterDao bbsUserCenterDao)
	{
		this.bbsUserCenterDao = bbsUserCenterDao;
	}

	@Override
	public IDto findUserInfoByUserid(long userid)
	{
		BBSUserCenterDto dto = new BBSUserCenterDto();
		BBSUser bbsUser = bbsUserCenterDao.findUserInfoByUserid(userid);
		dto.setBbsUser(bbsUser);
		return dto;
	}

	@Override
	public IDto findMyTopic(long userid)
	{
		BBSUserCenterDto dto = new BBSUserCenterDto();
		List<BBSTopic> topicList = bbsUserCenterDao.findMyTopic(userid);
		if(topicList != null && topicList.size()>0){
			dto.setTopicList(topicList);
		}
		return dto;
	}

	@Override
	public IDto bbsMyReply(long userid)
	{
		BBSUserCenterDto dto = new BBSUserCenterDto();
		List<BBSTopic> topicList = bbsUserCenterDao.bbsMyReply(userid);
		if(topicList != null && topicList.size()>0){
			dto.setTopicList(topicList);
		}
		return dto;
	}
	
	
}
