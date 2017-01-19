package com.huiyee.interact.bbs.action;

import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.bbs.dto.BBSUserCenterDto;
import com.huiyee.interact.bbs.mgr.IBBSUserCenterMgr;
import com.opensymphony.xwork2.ActionContext;

public class BBSUserCenterAction extends AbstractBBSAction {

	private static final long serialVersionUID = 6214272644609324233L;
	private BBSUserCenterDto dto;
	private IBBSUserCenterMgr bbsUserCenterMgr;
	private long forumid;
	
	public void setBbsUserCenterMgr(IBBSUserCenterMgr bbsUserCenterMgr)
	{
		this.bbsUserCenterMgr = bbsUserCenterMgr;
	}

	public String bbsUserCenter() throws Exception{
		VisitUser vu = (VisitUser)ActionContext.getContext().getSession().get("visitUser");
		if(vu != null){
			if(vu.getBbsUser() != null){
				long userid = vu.getBbsUser().getId();
				dto = (BBSUserCenterDto)bbsUserCenterMgr.findUserInfoByUserid(userid);
			}
		}
		return SUCCESS;
	}
	
	public String bbsMyTopic() throws Exception{
		VisitUser vu = (VisitUser)ActionContext.getContext().getSession().get("visitUser");
		if(vu != null){
			if(vu.getBbsUser() != null){
				long userid = vu.getBbsUser().getId();
				dto = (BBSUserCenterDto)bbsUserCenterMgr.findMyTopic(userid);
			}
		}
		return SUCCESS;
	}
	
	public String bbsMyReply() throws Exception{
		VisitUser vu = (VisitUser)ActionContext.getContext().getSession().get("visitUser");
		if(vu != null){
			if(vu.getBbsUser() != null){
				long userid = vu.getBbsUser().getId();
				dto = (BBSUserCenterDto)bbsUserCenterMgr.bbsMyReply(userid);
			}
		}
		return SUCCESS;
	}
	public BBSUserCenterDto getDto()
	{
		return dto;
	}

	public void setDto(BBSUserCenterDto dto)
	{
		this.dto = dto;
	}

	public long getForumid()
	{
		return forumid;
	}

	public void setForumid(long forumid)
	{
		this.forumid = forumid;
	}
	
	
}
