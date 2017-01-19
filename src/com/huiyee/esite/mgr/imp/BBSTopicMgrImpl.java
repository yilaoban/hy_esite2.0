package com.huiyee.esite.mgr.imp;

import java.util.ArrayList;
import java.util.List;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.dao.IBBSTopicDao;
import com.huiyee.esite.dto.CommunityDto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.mgr.IBBSTopicMgr;
import com.huiyee.esite.model.BBSForum;
import com.huiyee.esite.model.BBSJfRule;
import com.huiyee.interact.bbs.model.BBSTopic;
import com.huiyee.interact.bbs.model.BBSTopicText;

public class BBSTopicMgrImpl extends AbstractMgr implements IBBSTopicMgr
{
	private IBBSTopicDao bbsTopicDao;
	
	public void setBbsTopicDao(IBBSTopicDao bbsTopicDao)
	{
		this.bbsTopicDao = bbsTopicDao;
	}

	@Override
	public IDto findTopicListByForumid(long forumid,int pageId,long owner)
	{
		CommunityDto dto = new CommunityDto();
		int total = bbsTopicDao.findTopicCountByForumid(forumid,owner);
		int start = (pageId - 1) * IInteractConstants.INTERACT_BBS_LIMIT;
		if(total > 0){
			List<BBSTopic> bbsTopicList = bbsTopicDao.findTopicListByForumid(forumid,owner,start,IInteractConstants.INTERACT_BBS_LIMIT);
			if(bbsTopicList != null && bbsTopicList.size()>0){
				dto.setBbsTopicList(bbsTopicList);
			}
		}
		BBSForum bbsForum = bbsTopicDao.findBBSForumById(forumid);
		Pager pager = new Pager(pageId, total, IInteractConstants.INTERACT_BBS_LIMIT);
		dto.setPager(pager);
		dto.setBbsForum(bbsForum);
		return dto;
	}
	
	@Override
	public long saveTopic(BBSTopic topic, BBSTopicText topicText,long owner)
	{
		/*BBSJfRule bbsJfRule = bbsTopicDao.findBBSUserJfRuleByAction(owner,1);
		if(bbsJfRule != null){
			bbsTopicDao.updateBBUserJf(bbsJfRule.getJifen(),topic.getCREATER_UID());//增加积分
		}*/
		BBSForum bbsForum = bbsTopicDao.findForumById(topic.getFORUM_ID());
		if(bbsForum != null){
			String topicCheck = bbsForum.getTopicCheck();
			if("N".equals(topicCheck)){
				topic.setChecked("CMP");
			}else{
				topic.setChecked("NOR");
			}
		}
		long topicid = bbsTopicDao.saveBBSTopic(topic);
		bbsTopicDao.updateBBSUsrTopicnum(topic.getCREATER_UID());
//		bbsTopicDao.updateForumTopicnum(topic.getFORUM_ID());
		long ttid = bbsTopicDao.saveBBSTopicText(topicid,topicText);
		return ttid;
	}

	@Override
	public long updateBBSTopicStatus(long id, String checked)
	{
		if ("Y".equals(checked))
		{
			bbsTopicDao.updateBBSTopicStatus(id, "CMP");
		}
		else if ("N".equals(checked))
		{
			bbsTopicDao.updateBBSTopicStatus(id, "FLD");
		}
		return 1;
	}

	@Override
	public int updateBBSTopicStatus(long topicid,long forumid)
	{
		bbsTopicDao.updateBBSForumTopicnum(forumid);
		return bbsTopicDao.updateBBSTopicStatus(topicid);
	}

	@Override
	public IDto findTopicbytopicid(long topicid,int pageId,long owner,long forumid)
	{	
		CommunityDto dto = new CommunityDto();
		BBSForum bbsForum = bbsTopicDao.findForumById(forumid);
		if(bbsForum != null){
			String commentCheck = bbsForum.getCommentCheck();
			dto.setCommentCheck(commentCheck);
		}
		BBSTopic topic = null;
		List<BBSTopic> topicList = new ArrayList<BBSTopic>();
		BBSTopic topic1 = bbsTopicDao.findBBSTopic(topicid);
		if(topic1 != null && topic1.getEntityid() > 0){
			topic = bbsTopicDao.findTopicbytopicidAndOwner(topicid,owner,topic1.getEntype());//找出主题
		}else{
			topic = bbsTopicDao.findTopicbytopicid(topicid,owner);//找出主题
		}
		if(topic != null){
			topicList.add(topic);
		}
		int total = bbsTopicDao.findBBSPostCountbyTopicid(topicid,owner);
		int start = (pageId - 1) * IInteractConstants.INTERACT_BBS_LIMIT;
		if(total > 0){
			List<BBSTopic> list = bbsTopicDao.findBBSPostbyTopicid(topicid,owner,start,IInteractConstants.INTERACT_BBS_LIMIT);
			if(list !=null && list.size()>0){
				topicList.addAll(list);
			}
			Pager pager = new Pager(pageId, total, IInteractConstants.INTERACT_BBS_LIMIT);
			dto.setPager(pager);
		}
		dto.setBbsTopicList(topicList);
		dto.setTopic(topic);
		return dto;
	}
	
	@Override
	public IDto findTopicbytopicid(long topicid,long owner){
		CommunityDto dto = new CommunityDto();
		BBSTopic topic = null;
		BBSTopic topic1 = bbsTopicDao.findBBSTopic(topicid);
		if(topic1 != null && topic1.getEntityid() > 0){
			topic = bbsTopicDao.findTopicbytopicidAndOwner(topicid,owner,topic1.getEntype());//找出主题
		}else{
			topic = bbsTopicDao.findTopicbytopicid(topicid,owner);//找出主题
		}
		dto.setTopic(topic);
		return dto;
	}
	

	@Override
	public long saveReplyInfo(BBSTopic topic, String ip,long owner)
	{
		/*BBSJfRule bbsJfRule = bbsTopicDao.findBBSUserJfRuleByAction(owner,2);
		if(bbsJfRule != null){
			bbsTopicDao.updateBBUserJf(bbsJfRule.getJifen(),topic.getCREATER_UID());
		}*/
		BBSForum bbsForum = bbsTopicDao.findForumById(topic.getFORUM_ID());
		if(bbsForum != null){
			String commentCheck = bbsForum.getCommentCheck();
			if("N".equals(commentCheck)){
				topic.setChecked("CMP");
			}else{
				topic.setChecked("NOR");
			}
		}
		long postid = bbsTopicDao.saveBBSPost(topic,ip);
		long id = bbsTopicDao.saveBBSPostext(postid,topic);
		bbsTopicDao.updateBBSTopic(topic,postid);
		bbsTopicDao.updateForumpostnum(topic.getFORUM_ID());
		bbsTopicDao.updateBBSUserReplynum(topic.getCREATER_UID());
		return id;
	}

	@Override
	public int updateBBSTopicTop(long topicid, int top,long owner,long forumer)
	{
//		if(top == 1){
//			BBSJfRule bbsJfRule = bbsTopicDao.findBBSUserJfRuleByAction(owner,5);
//			if(bbsJfRule != null){
//				bbsTopicDao.updateBBUserJf(bbsJfRule.getJifen(),forumer);
//			}
//		}
		BBSTopic bt=bbsTopicDao.findBBSTopic(topicid);
		this.updateHyUserBalance(bt.getCREATER_UID(), "TOP", owner, topicid);
		return bbsTopicDao.updateBBSTopicTop(topicid,top);
	}

	@Override
	public int updateBBSTopicDown(long topicid,long owner,long forumer)
	{
		/*BBSJfRule bbsJfRule = bbsTopicDao.findBBSUserJfRuleByAction(owner,4);
		if(bbsJfRule != null){
			bbsTopicDao.updateBBUserJf(bbsJfRule.getJifen(),forumer);
		}*/
		return bbsTopicDao.updateBBSTopicDown(topicid);
	}

	@Override
	public int updateBBSTopicUp(long topicid,long owner,long forumer)
	{//1:发表主题 2:发表回复3:顶4:踩5:主题被置顶
		/*BBSJfRule bbsJfRule = bbsTopicDao.findBBSUserJfRuleByAction(owner,3);
		if(bbsJfRule != null){
			bbsTopicDao.updateBBUserJf(bbsJfRule.getJifen(),forumer);
		}*/
		return bbsTopicDao.updateBBSTopicUp(topicid);
	}

	@Override
	public IDto findTopicListSort(long forumid, int pageId, int index,long owner)
	{
		CommunityDto dto = new CommunityDto();
		int total = bbsTopicDao.findTopicCountByForumid(forumid,index,owner);
		int start = (pageId - 1) * IInteractConstants.INTERACT_BBS_LIMIT;
		if(total > 0){
			List<BBSTopic> bbsTopicList = bbsTopicDao.findTopicListByForumid(forumid,index,owner,start,IInteractConstants.INTERACT_BBS_LIMIT);
			if(bbsTopicList != null && bbsTopicList.size()>0){
				dto.setBbsTopicList(bbsTopicList);
			}
		}
		BBSForum bbsForum = bbsTopicDao.findBBSForumById(forumid);
		Pager pager = new Pager(pageId, total, IInteractConstants.INTERACT_BBS_LIMIT);
		dto.setPager(pager);
		dto.setBbsForum(bbsForum);
		return dto;
	}

	@Override
	public int updateBBSTopic(BBSTopic topic, BBSTopicText topicText) {
		bbsTopicDao.updateBBSTopicById(topic);
		int res = bbsTopicDao.updateBBSTopicContentById(topic.getId(), topicText);
		return res;
	}

	@Override
	public int delReplyTopic(long postid,long topicid) {
		bbsTopicDao.updateBBSTopicReplyCount(topicid);
		return bbsTopicDao.delReplyTopic(postid);
	}

	@Override
	public int updateBBSPostChecked(long postid, String checked)
	{
		return bbsTopicDao.updateBBSPostChecked(postid,checked);
	}
	
}
