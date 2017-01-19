
package com.huiyee.esite.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.model.InteractModel;
import com.huiyee.esite.util.HyConfig;
import com.opensymphony.xwork2.ActionContext;

public class InteractModelAction extends AbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -557562472103623457L;
	protected long mid = 0;
	protected List<InteractModel> ms;
	private int count;
	protected long redirectXc;// 若此字段不为空,表示 是有现场配置跳转过来的
	protected long redirectCb;// 若此字段不为空,表示 是从传播配置跳转过来的

	@Override
	public String execute() throws Exception
	{
		ServletActionContext.getRequest().getSession().setAttribute("positionId", IPageConstants.POSITIONID_4);
		// ms = pageCompose.composeFindInteractModel();
		if (ms != null && ms.size() > 0)
		{
			if (mid == 0)
			{
				InteractModel i = ms.get(0);
				mid = i.getId();
			}
			return SUCCESS;
		}
		return "failed";
	}

	public String base() throws Exception
	{
		ServletActionContext.getRequest().getSession().setAttribute("positionId", IPageConstants.POSITIONID_4);
		return SUCCESS;
	}

	public void init() throws Exception
	{
		if (HyConfig.isRun())
		{
			this.ms = pageCompose.compostInteractModel();
			count = pageCompose.findPageFeatureFid();
		}
	}

	public long getMid()
	{
		return mid;
	}

	public void setMid(long mid)
	{
		this.mid = mid;
	}

	public List<InteractModel> getMs()
	{
		return ms;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public long getRedirectXc()
	{
		return redirectXc;
	}

	public void setRedirectXc(long redirectXc)
	{
		this.redirectXc = redirectXc;
	}

	public long getRedirectCb()
	{
		return redirectCb;
	}

	public void setRedirectCb(long redirectCb)
	{
		this.redirectCb = redirectCb;
	}

}
