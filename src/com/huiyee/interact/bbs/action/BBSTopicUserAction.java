package com.huiyee.interact.bbs.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.interact.bbs.mgr.IBBSCommentMgr;
import com.huiyee.interact.bbs.mgr.IBBSLikeMgr;
import com.huiyee.interact.bbs.mgr.IBBSTopicMgr;
import com.huiyee.interact.bbs.mgr.IBBSUserMgr;
import com.huiyee.interact.bbs.model.BBSComment;
import com.huiyee.interact.bbs.model.BBSLike;
import com.huiyee.interact.bbs.model.BBSTopic;
import com.huiyee.interact.bbs.model.BBSUser;
import com.opensymphony.xwork2.ActionContext;

public class BBSTopicUserAction extends AbstractBBSUserAction {

	private static final long serialVersionUID = 638825227570928858L;
	private IBBSTopicMgr bbsTopicMgr;
	private IBBSCommentMgr bbsCommentMgr;
	private IBBSLikeMgr bbsLikeMgr;

	private BBSComment comment;

	public String likeTopic() throws Exception {
		int result = 0;
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		BBSTopic bbsTopic = bbsTopicMgr.findTopicById(this.getTopicid());
		if (vu != null && bbsTopic != null && bbsTopic.getSTATUS()!= 1) {
			BBSUser bu = vu.getBbsUser();
			if (bu != null) {
				BBSLike like = new BBSLike();
				like.setEntityid(this.getTopicid());
				like.setUserid(bu.getId());
				like.setType("T");
				like.setAtype(0);
				result = bbsLikeMgr.addLike(like,vu.getHyUserId(),this.getOwner());
				if (result > 0) {
					bbsTopicMgr.addTopicField(this.getTopicid(), "up");
				}
			}
		}else{
		   result = -1;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}
	
	public String dislikeTopic() throws Exception {
		int result = 0;
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if (vu != null) {
			BBSUser bu = vu.getBbsUser();
			if (bu != null) {
				BBSLike like = new BBSLike();
				like.setEntityid(this.getTopicid());
				like.setUserid(bu.getId());
				like.setType("T");
				like.setAtype(1);
				result = bbsLikeMgr.addLike(like,vu.getHyUserId(),this.getOwner());
				if (result > 0) {
					bbsTopicMgr.addTopicField(this.getTopicid(), "down");
				}
			}
		}

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}
	

	public String addComment() throws Exception {
		int result = 0;
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if (vu != null && comment != null) {
			BBSUser bu = vu.getBbsUser();
			if (bu != null) {
				HttpServletRequest request = ServletActionContext.getRequest();
				String ip = ClientUserIp.getIpAddr(request);
				comment.setCREATER_ID(bu.getId());
				comment.setPOSTER_IP(ip);
				BBSTopic bbsTopic = bbsTopicMgr.findTopicById(this.getTopicid());
				if(bbsTopic != null && bbsTopic.getSTATUS() != 1){
					result = bbsCommentMgr.addComment(this.getTopicid(), comment,this.getForumid(),vu.getHyUserId(),this.getOwner());
					bbsTopicMgr.addTopicField(this.getTopicid(), "REPLY_COUNT");
					bbsUserMgr.updateOperateNum("replynum", bu.getId());
				}else{
					result = -1;
				}
			}
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}

	public String likeComment() throws Exception {
		int result = 0;
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		BBSTopic bbsTopic = bbsTopicMgr.findTopicById(this.getTopicid());
		if (vu != null && bbsTopic != null && bbsTopic.getSTATUS()!= 1) {
			BBSUser bu = vu.getBbsUser();
			if (bu != null) {
				BBSLike like = new BBSLike();
				like.setEntityid(comment.getId());
				like.setUserid(bu.getId());
				like.setType("C");
				result = bbsLikeMgr.addLike(like,vu.getHyUserId(),this.getOwner());
				if (result > 0) {
					bbsCommentMgr.addCommentField(comment.getId(), "ZAN");
				}
			}
		}else{
			result = -1;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}

	public void setBbsTopicMgr(IBBSTopicMgr bbsTopicMgr) {
		this.bbsTopicMgr = bbsTopicMgr;
	}

	public void setBbsCommentMgr(IBBSCommentMgr bbsCommentMgr) {
		this.bbsCommentMgr = bbsCommentMgr;
	}

	public void setBbsLikeMgr(IBBSLikeMgr bbsLikeMgr) {
		this.bbsLikeMgr = bbsLikeMgr;
	}

	public BBSComment getComment() {
		return comment;
	}

	public void setComment(BBSComment comment) {
		this.comment = comment;
	}

}
