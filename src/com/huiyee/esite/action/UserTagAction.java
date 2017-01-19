
package com.huiyee.esite.action;

import java.util.List;

import net.sf.json.JSONArray;

import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.UserPkvTag;
import com.huiyee.esite.model.UserTag;
import com.opensymphony.xwork2.ActionContext;

public class UserTagAction extends AbstractAction
{

	private long ccid;
	private long pid;
	private List<UserTag> list;
	private UserPkvTag tags;
	private long pageid;

	public String execute() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		list = pageCompose.findOwnerUserTag(account.getOwner().getId());
		if(ccid!=0&&pid!=0){
			tags = pageCompose.findContentTag(ccid, pid);
		}
		return SUCCESS;
	}

	public String pageTag() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		list = pageCompose.findOwnerUserTag(account.getOwner().getId());
		tags = pageCompose.findPageTag(pageid);
		return SUCCESS;
	}

	public long getCcid()
	{
		return ccid;
	}

	public void setCcid(long ccid)
	{
		this.ccid = ccid;
	}

	public long getPid()
	{
		return pid;
	}

	public void setPid(long pid)
	{
		this.pid = pid;
	}

	public List<UserTag> getList()
	{
		return list;
	}

	public void setList(List<UserTag> list)
	{
		this.list = list;
	}

	public UserPkvTag getTags()
	{
		return tags;
	}

	public void setTags(UserPkvTag tags)
	{
		this.tags = tags;
	}

	public String getTagsjosn()
	{
		if (list != null && list.size() > 0)
		{
			JSONArray jo = new JSONArray();
			for (UserTag t : list)
			{
				jo.add(t.getName());
			}
			return jo.toString();
		}
		return null;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}
}
