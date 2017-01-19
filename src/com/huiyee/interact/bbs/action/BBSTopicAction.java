package com.huiyee.interact.bbs.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.mgr.IContentManager;
import com.huiyee.esite.model.ContentNew;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.model.WxUser;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.interact.bbs.dto.BBSCommentDto;
import com.huiyee.interact.bbs.mgr.IBBSCommentMgr;
import com.huiyee.interact.bbs.mgr.IBBSForumMgr;
import com.huiyee.interact.bbs.mgr.IBBSTopicMgr;
import com.huiyee.interact.bbs.model.BBSComment;
import com.huiyee.interact.bbs.model.BBSForum;
import com.huiyee.interact.bbs.model.BBSTopic;
import com.huiyee.interact.bbs.model.BBSUser;
import com.opensymphony.xwork2.ActionContext;

public class BBSTopicAction extends AbstractBBSAction
{

	private static final long serialVersionUID = 638825227570928848L;
	private IBBSTopicMgr bbsTopicMgr;
	private IBBSCommentMgr bbsCommentMgr;
	private IContentManager contentManager;
	private IBBSForumMgr bbsForumMgr;
	private int pageId = 1;
	private BBSCommentDto dto;

	public String showComments() throws Exception
	{
		int rows = 10;
		int start = (pageId - 1) * rows;

		dto = new BBSCommentDto();
		BBSTopic topic = bbsTopicMgr.findTopicById(this.getTopicid());
		if (this.getCategory() == 0 && topic != null)
		{
			if (topic.getEntype() == 0)
			{// 图文

			}
			else if (topic.getEntype() == 1)
			{// 产品
				ContentProduct product = contentManager.findProductById(topic.getEntityid());
				if (product != null)
				{
					String img = "<img src=\"" + HyConfig.getImgDomain() + product.getSimgurl() + "\" width=\"100%\"/><br>";
					topic.setContent(img + product.getName());
				}
			}
			else if (topic.getEntype() == 2)
			{// 新闻
				ContentNew news = contentManager.findNewsById(topic.getEntityid());
				if (news != null)
				{
					String img = "<img src=\"" + HyConfig.getImgDomain() + news.getSimgurl() + "\" width=\"100%\"/><br>";
					topic.setContent(img + news.getContent());
				}
			}
			else if (topic.getEntype() == 3)
			{// 视频

			}

		}
		dto.setTopic(topic);

		if (topic != null)
		{	
			BBSForum forum = bbsForumMgr.findForumById(this.getForumid());
			if (forum != null) {
				List<BBSComment> comments = bbsCommentMgr.findCommentsByTopicid(topic.getId(),forum.getCommentCheck(), start, rows);
				for(int i=0;i<comments.size();i++){
					long uid = comments.get(i).getCREATER_ID();
					String creater = comments.get(i).getCreater();
					if(uid == forum.getForumer()){
						comments.get(i).setCreater(creater + "(管理员)");
					}
				}
				dto.setComments(comments);
			}
		}

		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if (vu != null)
		{
			BBSUser bu = vu.getBbsUser();
			WxUser wu = vu.getWxUser();
			if (bu != null)
			{
				dto.setHead_img(bu.getImg());
			}
			else if (wu != null)
			{
				dto.setHead_img(wu.getHeadimgurl());
			}
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(dto));
		out.flush();
		out.close();
		return null;
	}

	public void setBbsTopicMgr(IBBSTopicMgr bbsTopicMgr)
	{
		this.bbsTopicMgr = bbsTopicMgr;
	}

	public void setBbsCommentMgr(IBBSCommentMgr bbsCommentMgr)
	{
		this.bbsCommentMgr = bbsCommentMgr;
	}

	public void setContentManager(IContentManager contentManager)
	{
		this.contentManager = contentManager;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public BBSCommentDto getDto()
	{
		return dto;
	}

	public void setDto(BBSCommentDto dto)
	{
		this.dto = dto;
	}

	
	public void setBbsForumMgr(IBBSForumMgr bbsForumMgr)
	{
		this.bbsForumMgr = bbsForumMgr;
	}

}
