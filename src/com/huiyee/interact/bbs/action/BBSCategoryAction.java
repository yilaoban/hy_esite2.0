package com.huiyee.interact.bbs.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.interact.bbs.dto.BBSForumDto;
import com.huiyee.interact.bbs.mgr.IBBSForumMgr;
import com.huiyee.interact.bbs.mgr.IBBSTopicMgr;
import com.huiyee.interact.bbs.model.BBSForum;
import com.huiyee.interact.bbs.model.BBSTopic;

public class BBSCategoryAction extends AbstractBBSAction {

	private static final long serialVersionUID = -5261069963252745352L;
	private IBBSForumMgr bbsForumMgr;
	private IBBSTopicMgr bbsTopicMgr;

	private int pageId = 1;
	private List<BBSForumDto> list;

	public String showForums() throws Exception {
		int rows = 10;
		int start = (pageId - 1) * rows;

		list = new ArrayList<BBSForumDto>();
		List<BBSForum> forums = bbsForumMgr.findForumsByCateid(this.getCategory());
		for (BBSForum forum : forums) {
			List<BBSTopic> topics = bbsTopicMgr.findTopicsByForumid(forum, start, rows);
			BBSForumDto dto = new BBSForumDto();
			dto.setForum(forum);
			dto.setTopics(topics);
			list.add(dto);
		}
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(list));
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

	public List<BBSForumDto> getList() {
		return list;
	}

	public void setList(List<BBSForumDto> list) {
		this.list = list;
	}

}
