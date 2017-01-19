
package project.caidan.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import project.caidan.mgr.IAccountManager;
import project.caidan.mgr.ICaiDanNewsMgr;
import project.caidan.model.CDNews;
import project.caidan.model.CdRmbRecord;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.VisitUser;
import com.opensymphony.xwork2.ActionContext;

/**
 * 彩蛋新闻
 * 
 * @author ldw
 * 
 */
public class NewsForPageAction extends AbstractAction
{

	private ICaiDanNewsMgr newsMgr;
	private IAccountManager accountManager;
	private int pageid = 1;
	private int size = 5;

	@Override
	public String execute() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		List<CDNews> list = newsMgr.findListForCaiMin(this.getOwner(), pageid, size);
		Gson gson = new Gson();
		out.print(gson.toJson(list));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 投注站看到的新闻
	 * 
	 * @return
	 * @throws Exception
	 */
	public String touzhuzhan() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser vu = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
		long wxuid = 0;
		JSONObject jo = new JSONObject();
		List<CDNews> list = new ArrayList<CDNews>();
		if (vu != null && vu.getCd() == 1)
		{
			wxuid = vu.getWxuid();
			String accountLevel = accountManager.findAccountTypeByWxuid(wxuid);
			if (StringUtils.isNotEmpty(accountLevel))
			{
				jo.put("accountLevel", accountLevel);
				ActionContext.getContext().getSession().put("accountLevel", accountLevel);
				list = newsMgr.findListForStation(this.getOwner(), accountLevel, pageid, size);
			}
		}
		jo.put("list", list);
		out.print(jo);
		out.flush();
		out.close();
		return null;
	}

	public int getPageid()
	{
		return pageid;
	}

	public void setPageid(int pageid)
	{
		this.pageid = pageid;
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}

	public void setNewsMgr(ICaiDanNewsMgr newsMgr)
	{
		this.newsMgr = newsMgr;
	}

	public void setAccountManager(IAccountManager accountManager)
	{
		this.accountManager = accountManager;
	}
}
