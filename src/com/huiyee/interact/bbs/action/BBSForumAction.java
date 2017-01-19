package com.huiyee.interact.bbs.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.interact.bbs.mgr.IBBSForumMgr;
import com.huiyee.interact.bbs.mgr.IBBSTopicMgr;
import com.huiyee.interact.bbs.model.BBSForum;
import com.huiyee.interact.bbs.model.BBSTopic;

public class BBSForumAction extends AbstractBBSAction {

	private static final long serialVersionUID = 7555808135954148127L;
	private IBBSForumMgr bbsForumMgr;
	private IBBSTopicMgr bbsTopicMgr;

	private int pageId = 1;

	public String showTopics() throws Exception {
		int rows = 10;
		int start = (pageId - 1) * rows;
		List<BBSTopic> topics = new ArrayList<BBSTopic>();
		BBSForum forum = bbsForumMgr.findForumById(this.getForumid());
		if (forum != null) {
			topics = bbsTopicMgr.findTopicsByForumid(forum, start, rows);
			for(int i=0;i<topics.size();i++){
				long uid = topics.get(i).getCREATER_UID();
				String creater = topics.get(i).getCreater();
				if(uid == forum.getForumer()){
					topics.get(i).setCreater(creater + "(¹ÜÀíÔ±)");
				}
			}
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(topics));
		out.flush();
		out.close();
		return null;
	}

	public void setBbsForumMgr(IBBSForumMgr bbsForumMgr) {
		this.bbsForumMgr = bbsForumMgr;
	}

	public void setBbsTopicMgr(IBBSTopicMgr bbsTopicMgr) {
		this.bbsTopicMgr = bbsTopicMgr;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

}
