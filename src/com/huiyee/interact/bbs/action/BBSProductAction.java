
package com.huiyee.interact.bbs.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.model.Account;
import com.huiyee.interact.bbs.mgr.IBBSMgr;
import com.huiyee.interact.bbs.model.BBSContent;
import com.huiyee.interact.bbs.model.BBSForum;
import com.opensymphony.xwork2.ActionContext;

public class BBSProductAction extends AbstractAction
{

	private List<BBSForum> list;
	private IBBSMgr bbsMgr;
	private long forumid;
	private long entityId;
	private String entityType;
	private String entityName;
	private List<String> pids;
	private String bathPid;
	private String fromTips;

	public String getForumList() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		list = bbsMgr.findListByOwner(account.getOwner().getId(), account.getId(), entityType);
		if (bathPid != null && bathPid.trim().length() != 0)
		{
			return "switchBatch";
		}
		return SUCCESS;
	}

	public String subTopic() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		int rs = 0;
		if (entityId > 0 && StringUtils.isNotEmpty(entityName) && StringUtils.isNotEmpty(entityType))
		{
			int a = needTips();
			if (a > 0)
			{
				rs = bbsMgr.saveTopicForContent(entityId, entityType, entityName, account);
			} else
			{
				rs = -2;
			}

		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(rs));
		out.flush();
		out.close();
		return null;

	}

	public String bathSubTopic() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		int rs = 0;
		String msg = "N";
		if (needTips() < 0)
		{
			msg = "T";
		} else
		{
			list = bbsMgr.findListByOwner(account.getOwner().getId(), account.getId(), entityType);
			if (list != null && list.size() > 1 && forumid == 0)
			{
				msg = "N";
			} else if (bathPid != null && bathPid.trim().length() > 0)
			{
				rs = bbsMgr.saveBathTopic(bathPid, entityType, forumid, account);
				if (rs > 0)
				{
					msg = "Y";
				} else
				{
					msg = "开通社区失败,相关内容已开通社区.";
				}
			} else
			{
				msg = "没有选择内容!";
			}
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(msg));
		out.flush();
		out.close();
		return null;

	}

	public String subTopicWithForum() throws Exception
	{

		Account account = (Account) ActionContext.getContext().getSession().get("account");
		int rs = 0;
		if (forumid > 0 && entityId > 0 && StringUtils.isNotEmpty(entityName) && StringUtils.isNotEmpty(entityType))
		{
			rs = bbsMgr.saveTopicForContent(entityId, entityType, entityName, forumid, account);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(rs));
		out.flush();
		out.close();
		return null;

	}

	/**
	 * ajax获取踩顶评论
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getBBSData() throws Exception
	{
		List<BBSContent> list = bbsMgr.findData(pids, entityType);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(list));
		out.flush();
		out.close();
		return null;
	}

	private int needTips()
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		list = bbsMgr.findListByOwner(account.getOwner().getId(), account.getId(), entityType);
		if (list == null && fromTips == null)
		{
			return -1;
		}
		return 1;
	}

	public List<BBSForum> getList()
	{
		return list;
	}

	public void setList(List<BBSForum> list)
	{
		this.list = list;
	}

	public IBBSMgr getBbsMgr()
	{
		return bbsMgr;
	}

	public void setBbsMgr(IBBSMgr bbsMgr)
	{
		this.bbsMgr = bbsMgr;
	}

	public long getForumid()
	{
		return forumid;
	}

	public void setForumid(long forumid)
	{
		this.forumid = forumid;
	}

	public long getEntityId()
	{
		return entityId;
	}

	public void setEntityId(long entityId)
	{
		this.entityId = entityId;
	}

	public String getEntityType()
	{
		return entityType;
	}

	public void setEntityType(String entityType)
	{
		this.entityType = entityType;
	}

	public String getEntityName()
	{
		return entityName;
	}

	public void setEntityName(String entityName)
	{
		this.entityName = entityName;
	}

	public List<String> getPids()
	{
		return pids;
	}

	public void setPids(List<String> pids)
	{
		this.pids = pids;
	}

	public String getBathPid()
	{
		return bathPid;
	}

	public void setBathPid(String bathPid)
	{
		this.bathPid = bathPid;
	}

	public String getFromTips()
	{
		return fromTips;
	}

	public void setFromTips(String fromTips)
	{
		this.fromTips = fromTips;
	}
}
