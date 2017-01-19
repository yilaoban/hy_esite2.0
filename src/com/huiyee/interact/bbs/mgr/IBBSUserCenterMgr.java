package com.huiyee.interact.bbs.mgr;

import com.huiyee.interact.bbs.dto.IDto;

public interface IBBSUserCenterMgr
{
	public IDto findUserInfoByUserid(long userid);
	
	public IDto findMyTopic(long userid);
	
	public IDto bbsMyReply(long userid);
}
