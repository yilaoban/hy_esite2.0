
package project.caidan.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import project.caidan.dto.WayDto;
import project.caidan.mgr.IWayMgr;
import project.caidan.model.CDWay;
import project.caidan.model.CDWayCatalog;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.model.Account;
import com.opensymphony.xwork2.ActionContext;

/**
 * ÇþµÀ¹ÜÀí
 * 
 * @author ldw
 * 
 */
public class WayAction extends AbstractAction
{

	private IWayMgr wayMgr;
	private WayDto dto;
	private int pageId = 1;
	private CDWayCatalog catalog;
	private long caid;
	private CDWay way;
	private long wayid;
	private List<Account> accounts;

	@Override
	public String execute() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		String accountType = (String) ActionContext.getContext().getSession().get("accountType");
		dto = (WayDto) wayMgr.findCatalogs(account, pageId);
		return SUCCESS;
	}

	public String addCatalog() throws Exception
	{
		return SUCCESS;
	}

	public String saveCatalog() throws Exception
	{
		if (catalog != null)
		{
			Account account = (Account) ActionContext.getContext().getSession().get("account");
			int rs = wayMgr.saveCatalog(catalog, account);
		}
		return SUCCESS;
	}

	public String editCatalog() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		catalog = wayMgr.findCatalogByid(caid, account);
		return SUCCESS;
	}

	public String editCatalogSave() throws Exception
	{
		if (catalog != null || catalog.getId() > 0)
		{
			Account account = (Account) ActionContext.getContext().getSession().get("account");
			int rs = wayMgr.updateCatalog(catalog, account);
		}
		return SUCCESS;
	}

	public String deleteCatalog() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int result = wayMgr.deleteCatalog(caid, account);
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;

	}

	public String wayIndex() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (WayDto) wayMgr.findWays(account, caid, pageId);
		return SUCCESS;
	}

	public String addWayIndex() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		accounts = wayMgr.findAccountForWay(account.getOwner().getId());
		return SUCCESS;
	}

	public String saveWay() throws Exception
	{
		if (way != null)
		{
			Account account = (Account) ActionContext.getContext().getSession().get("account");
			int rs = wayMgr.saveWay(way, account);
		}
		return SUCCESS;
	}

	public String editWayIndex() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		accounts = wayMgr.findAccountForWay(account.getOwner().getId());
		way = wayMgr.findWayByid(wayid, account);
		return SUCCESS;
	}
	
	public String editWaySub() throws Exception
	{
		if(way!=null&&way.getId()>0){
			Account account = (Account) ActionContext.getContext().getSession().get("account");
			int rs = wayMgr.updateWay(way,account);
		}
		return SUCCESS;
	}
	
	
	public String deleteWay() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int result = wayMgr.deleteWay(wayid, account);
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;

	}

	public WayDto getDto()
	{
		return dto;
	}

	public void setDto(WayDto dto)
	{
		this.dto = dto;
	}

	public void setWayMgr(IWayMgr wayMgr)
	{
		this.wayMgr = wayMgr;
	}

	public CDWayCatalog getCatalog()
	{
		return catalog;
	}

	public void setCatalog(CDWayCatalog catalog)
	{
		this.catalog = catalog;
	}

	public long getCaid()
	{
		return caid;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public void setCaid(long caid)
	{
		this.caid = caid;
	}

	public CDWay getWay()
	{
		return way;
	}

	public void setWay(CDWay way)
	{
		this.way = way;
	}

	public long getWayid()
	{
		return wayid;
	}

	public void setWayid(long wayid)
	{
		this.wayid = wayid;
	}

	public List<Account> getAccounts()
	{
		return accounts;
	}

	public void setAccounts(List<Account> accounts)
	{
		this.accounts = accounts;
	}
}
