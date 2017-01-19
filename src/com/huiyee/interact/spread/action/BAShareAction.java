package com.huiyee.interact.spread.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.spread.mgr.IBAShareManager;

public class BAShareAction extends InteractModelAction
{

	private static final long serialVersionUID = 3691192656501652577L;
	private String type;
	private long pageid;
	private String content;
	private String pic;
	private long featureid;
	private long hid;
	private String key;

	private IBAShareManager baShareManager;

	@Override
	public String execute() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();

		HttpServletRequest request = ServletActionContext.getRequest();
		VisitUser user = (VisitUser) request.getSession().getAttribute("visitUser");
		int result = baShareManager.share(user, pageid, content, pic);
		if (result > 0)
		{
			if ("B".equals(type))
			{
				//pageCompose.updateBeforeShare(featureid, hid);
			}
			else
			{
				//pageCompose.updateAfterShare(featureid, hid);
			}
			out.print("Y");
		}
		else
		{
			out.print("N");
		}

		out.flush();
		out.close();
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

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getPic()
	{
		return pic;
	}

	public void setPic(String pic)
	{
		this.pic = pic;
	}

	public long getFeatureid()
	{
		return featureid;
	}

	public void setFeatureid(long featureid)
	{
		this.featureid = featureid;
	}

	public long getHid()
	{
		return hid;
	}

	public void setHid(long hid)
	{
		this.hid = hid;
	}

	public IBAShareManager getBaShareManager()
	{
		return baShareManager;
	}

	public void setBaShareManager(IBAShareManager baShareManager)
	{
		this.baShareManager = baShareManager;
	}

	public void setKey(String key)
	{
		this.key = key;
		if (key != null)
		{
			String[] array = key.split("-");
			if (array.length == 2)
			{
				try
				{
					featureid = Long.parseLong(array[0]);
					hid = Long.parseLong(array[1]);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}

}
