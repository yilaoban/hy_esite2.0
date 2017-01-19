package com.huiyee.interact.bbs.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.bbs.mgr.IBBSTopicMgr;
import com.huiyee.interact.bbs.mgr.IBBSUserMgr;
import com.huiyee.interact.bbs.model.BBSTopic;
import com.huiyee.interact.bbs.model.BBSUser;
import com.opensymphony.xwork2.ActionContext;

public class BBSForumUserAction extends AbstractBBSUserAction {

	private static final long serialVersionUID = -6126998407945331963L;
	private IBBSTopicMgr bbsTopicMgr;
//	private IBBSUserMgr bbsUserMgr;

	private BBSTopic topic;

	public String addTopic() throws Exception {
		long topicid = 0;
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if (vu != null) {
			BBSUser bu = vu.getBbsUser();
			if (bu != null && topic != null) {
				topic.setCREATER_UID(bu.getId());
				topicid = bbsTopicMgr.addTopic(this.getForumid(), topic,vu.getHyUserId(),this.getOwner());
				bbsUserMgr.updateOperateNum("topicnum", bu.getId());
			}
		}

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(topicid);
		out.flush();
		out.close();
		return null;
	}

	public void setBbsTopicMgr(IBBSTopicMgr bbsTopicMgr) {
		this.bbsTopicMgr = bbsTopicMgr;
	}

	public BBSTopic getTopic() {
		return topic;
	}

	public void setTopic(BBSTopic topic) {
		this.topic = topic;
	}

}
