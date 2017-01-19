
package com.huiyee.esite.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.mgr.IContentManager;
import com.huiyee.esite.model.Account;
import com.opensymphony.xwork2.ActionContext;

/**
 * 内容的需要复制或者剪切到别的目录下(xj need) 置顶
 * 
 * @author ldw
 * 
 */

public class ContentMoveAction extends AbstractAction
{

	private IContentManager contentManager;
	private String ids;
	private long targetCcid;
	private int copyOrcut;
	private long contentid;
	private int topType;// 0-取消置顶 1-置顶

	@Override
	public String execute() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int rs = 0;
		if (StringUtils.isNotEmpty(ids) && targetCcid != 0)
		{
			rs = contentManager.updateContentMoveOrCut(ids, targetCcid, copyOrcut);
		}
		Gson gson = new Gson();
		out.print(gson.toJson(rs));
		out.flush();
		out.close();
		return null;
	}

	public String batchDel() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int rs = 0;
		if (StringUtils.isNotEmpty(ids))
		{
			rs = contentManager.updateContentBatchDel(ids, targetCcid);
		}
		Gson gson = new Gson();
		out.print(gson.toJson(rs));
		out.flush();
		out.close();
		return null;
	}

	public String contentToTop() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int rs = 0;
		if (contentid>0)
		{
			rs = contentManager.updateContentToTop(account,contentid,topType);
		}
		Gson gson = new Gson();
		out.print(gson.toJson(rs));
		out.flush();
		out.close();
		return null;
	}

	public String getIds()
	{
		return ids;
	}

	public void setIds(String ids)
	{
		this.ids = ids;
	}

	public long getTargetCcid()
	{
		return targetCcid;
	}

	public void setTargetCcid(long targetCcid)
	{
		this.targetCcid = targetCcid;
	}

	public void setContentManager(IContentManager contentManager)
	{
		this.contentManager = contentManager;
	}

	public int getCopyOrcut()
	{
		return copyOrcut;
	}

	public void setCopyOrcut(int copyOrcut)
	{
		this.copyOrcut = copyOrcut;
	}

	
	public long getContentid()
	{
		return contentid;
	}

	
	public void setContentid(long contentid)
	{
		this.contentid = contentid;
	}

	
	public int getTopType()
	{
		return topType;
	}

	
	public void setTopType(int topType)
	{
		this.topType = topType;
	}

}
