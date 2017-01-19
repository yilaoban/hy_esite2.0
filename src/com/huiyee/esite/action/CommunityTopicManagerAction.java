package com.huiyee.esite.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.dto.CommunityDto;
import com.huiyee.esite.mgr.IBBSTopicMgr;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.interact.bbs.action.AbstractBBSAction;
import com.huiyee.interact.bbs.mgr.IBBSLikeMgr;
import com.huiyee.interact.bbs.model.BBSLike;
import com.huiyee.interact.bbs.model.BBSTopic;
import com.huiyee.interact.bbs.model.BBSTopicText;
import com.opensymphony.xwork2.ActionContext;

public class CommunityTopicManagerAction extends AbstractBBSAction
{
	private static final long serialVersionUID = -2716772019784194256L;
	private CommunityDto dto;
	private int lightType = 1;
	private int type = 2;
	private IBBSTopicMgr bbsTopicMgr;
	private IBBSLikeMgr bbsLikeMgr;
	private String entity;
	private long forumer;
	private BBSTopic topic;
	private BBSTopicText topicText;
	private String ids;
	private String checked;// 审核通过"Y" 不通过 "N"
	private int pageId = 1;
	private long topicid;
	private int indexCount;
	private int top;
	private int index;
	private long postid;
	
	public void setBbsLikeMgr(IBBSLikeMgr bbsLikeMgr)
	{
		this.bbsLikeMgr = bbsLikeMgr;
	}

	public void setBbsTopicMgr(IBBSTopicMgr bbsTopicMgr)
	{
		this.bbsTopicMgr = bbsTopicMgr;
	}

	@Override
	public String execute() throws Exception
	{	Account account = (Account) ActionContext.getContext().getSession().get("account");
		entity = account.getOwner().getEntity();
		long owner = account.getOwner().getId();
		if(owner != this.getBbsowner()){
			return "fail";
		}
		dto = (CommunityDto) bbsTopicMgr.findTopicListByForumid(this.getForumid(),pageId,owner);
		return SUCCESS;
	}

	/**
	 * 排序查询话题列表
	 */
	public String findTopicListSort() throws Exception{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		entity = account.getOwner().getEntity();
		long owner = account.getOwner().getId();
		if(owner != this.getBbsowner()){
			return "fail";
		}
		dto = (CommunityDto) bbsTopicMgr.findTopicListSort(this.getForumid(),pageId,index,owner);
		return SUCCESS;
	}
	
	/**
	 * 创建话题
	 * @return
	 * @throws Exception
	 */
	public String addTopic() throws Exception{
		return SUCCESS;
	}
	
	/**
	 * 保存话题
	 * @return
	 * @throws Exception
	 */
	public String saveTopic() throws Exception{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		entity = account.getOwner().getEntity();
		long owner = account.getOwner().getId();
		long res = bbsTopicMgr.saveTopic(topic,topicText,owner);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(res));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 编辑话题
	 */
	public String editbbsTopic() throws Exception{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		entity = account.getOwner().getEntity();
		long owner = account.getOwner().getId();
		if(owner != this.getBbsowner()){
			return "fail";
		}
		dto = (CommunityDto) bbsTopicMgr.findTopicbytopicid(topicid,owner);
		return SUCCESS;
	}
	
	public String updateTopic() throws Exception{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		entity = account.getOwner().getEntity();
		int res = bbsTopicMgr.updateBBSTopic(topic, topicText);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(res));
		out.flush();
		out.close();
		return null;
		
	}
	
	/**
	 * 话题审核
	 * @return
	 * @throws Exception
	 */
	public String bathPass() throws Exception
	{
		String[] allids = ids.split(";");
		for (int i = 0; i < allids.length; i++)
		{
			long crid = Long.parseLong(allids[i]);
			bbsTopicMgr.updateBBSTopicStatus(crid, checked);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String result = "Y";
		out.write(result);
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 批量删除
	 * @return
	 * @throws Exception
	 */
	public String batchDel() throws Exception
	{
		String[] allids = ids.split(";");
		for (int i = 0; i < allids.length; i++)
		{
			long crid = Long.parseLong(allids[i]);
			bbsTopicMgr.updateBBSTopicStatus(crid,this.getForumid());
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String result = "Y";
		out.write(result);
		out.flush();
		out.close();
		return null;
	}
	
	
	/**
	 * 删除话题
	 * @return
	 * @throws Exception
	 */
	public String deletBBSTopic() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String result = "N";
		int res = bbsTopicMgr.updateBBSTopicStatus(topicid,this.getForumid());
		if(res > 0){
			result = "Y";
		}
		out.write(result);
		out.flush();
		out.close();
		return null;
		
	}
	
	/**
	 * 详细信息
	 * @return
	 * @throws Exception
	 */
	public String bbsTopicDetail() throws Exception{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		entity = account.getOwner().getEntity();
		long owner = account.getOwner().getId();
		if(owner != this.getBbsowner()){
			return "fail";
		}
		dto = (CommunityDto) bbsTopicMgr.findTopicbytopicid(topicid,pageId,owner,this.getForumid());
		return SUCCESS;
	}
	
	public String bbsReplyTopic() throws Exception{
		return SUCCESS;
	}
	
	public String saveBBSReply() throws Exception{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		entity = account.getOwner().getEntity();
		long owner = account.getOwner().getId();
		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = ClientUserIp.getIpAddr(request);
		long result = bbsTopicMgr.saveReplyInfo(topic,ip,owner);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 置顶操作
	 * @return
	 */
	public String updateTopicTop() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String result = "N";
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		entity = account.getOwner().getEntity();
		long owner = account.getOwner().getId();
		int res = bbsTopicMgr.updateBBSTopicTop(topicid,top,owner,forumer);
		if(res > 0){
			result = "Y";
		}
		out.write(result);
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 顶
	 * @return
	 */
	public String bbsUp() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String result = "N";int res = 0;
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		entity = account.getOwner().getEntity();
		long owner = account.getOwner().getId();
		
		BBSLike like = new BBSLike();
		like.setEntityid(topicid);
		like.setUserid(forumer);
		like.setType("T");
		like.setAtype(0);
		res = bbsLikeMgr.addLike(like,0,0);
		if (res > 0) {
			int re = bbsTopicMgr.updateBBSTopicUp(topicid,owner,forumer);
			if(re > 0){
				result = "Y";
			}
		}
		out.write(result);
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 踩
	 */
	public String bbsDown() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String result = "N";int res = 0;
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		entity = account.getOwner().getEntity();
		long owner = account.getOwner().getId();
		
		BBSLike like = new BBSLike();
		like.setEntityid(topicid);
		like.setUserid(forumer);
		like.setType("T");
		like.setAtype(1);
		res = bbsLikeMgr.addLike(like,0,0);
		if (res > 0) {
			int re = bbsTopicMgr.updateBBSTopicDown(topicid,owner,forumer);
			if(re > 0){
				result = "Y";
			}
		}
		out.write(result);
		out.flush();
		out.close();
		return null;
	}
	
	
	/**
	 * 删除回复
	 * @return
	 * @throws Exception
	 */
	public String delReplyTopic() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String result = "N";
		int res = bbsTopicMgr.delReplyTopic(postid,topicid);
		if(res > 0){
			result = "Y";
		}
		out.write(result);
		out.flush();
		out.close();
		return null;
		
	}
	
	/**
	 * 评论审核
	 * @return
	 * @throws Exception
	 */
	public String commentCheck() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String result = "N";
		int res = bbsTopicMgr.updateBBSPostChecked(postid,checked);
		if(res > 0){
			result = "Y";
		}
		out.write(result);
		out.flush();
		out.close();
		return null;
		
	}
	public CommunityDto getDto()
	{
		return dto;
	}

	public void setDto(CommunityDto dto)
	{
		this.dto = dto;
	}

	public int getLightType()
	{
		return lightType;
	}

	public void setLightType(int lightType)
	{
		this.lightType = lightType;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public String getEntity()
	{
		return entity;
	}

	public void setEntity(String entity)
	{
		this.entity = entity;
	}

	public long getForumer()
	{
		return forumer;
	}

	public void setForumer(long forumer)
	{
		this.forumer = forumer;
	}

	public BBSTopic getTopic()
	{
		return topic;
	}

	public void setTopic(BBSTopic topic)
	{
		this.topic = topic;
	}

	public BBSTopicText getTopicText()
	{
		return topicText;
	}

	public void setTopicText(BBSTopicText topicText)
	{
		this.topicText = topicText;
	}

	public String getIds()
	{
		return ids;
	}

	public void setIds(String ids)
	{
		this.ids = ids;
	}

	public String getChecked()
	{
		return checked;
	}

	public void setChecked(String checked)
	{
		this.checked = checked;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public long getTopicid()
	{
		return topicid;
	}

	public void setTopicid(long topicid)
	{
		this.topicid = topicid;
	}

	public int getIndexCount()
	{
		return indexCount;
	}

	public void setIndexCount(int indexCount)
	{
		this.indexCount = indexCount;
	}

	public int getTop()
	{
		return top;
	}

	public void setTop(int top)
	{
		this.top = top;
	}

	public int getIndex()
	{
		return index;
	}

	public void setIndex(int index)
	{
		this.index = index;
	}

	public long getPostid() {
		return postid;
	}

	public void setPostid(long postid) {
		this.postid = postid;
	}
	
}
