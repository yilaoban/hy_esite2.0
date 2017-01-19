
package project.caidan.action;

import java.util.List;

import net.sf.json.JSONArray;

import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.mgr.IUserTagMgr;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.UserPkvTag;
import com.huiyee.esite.model.UserTag;
import com.opensymphony.xwork2.ActionContext;

public class CaiDanTagsAction extends AbstractAction
{

	private IUserTagMgr userTagMgr;
	private List<UserTag> list;
	private UserPkvTag tags;
	private long pid;
	private String type;

	public String execute() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		list = pageCompose.findOwnerUserTag(account.getOwner().getId());
		tags = userTagMgr.findContentTag(type, pid);
		return SUCCESS;
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

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public void setUserTagMgr(IUserTagMgr userTagMgr)
	{
		this.userTagMgr = userTagMgr;
	}

}
