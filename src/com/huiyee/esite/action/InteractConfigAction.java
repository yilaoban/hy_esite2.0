package com.huiyee.esite.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.InteractModel;
import com.opensymphony.xwork2.ActionContext;

/**
 * ≈‰÷√ª•∂Ø
 * 
 * @author Administrator
 * 
 */
public class InteractConfigAction extends AbstractAction
{
	private List<InteractModel> list;
	private List<InteractModel> omlist;
	private String lids;
	private String title;
	private long mid;
	private long lid;
	private String status;

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	@Override
	public String execute() throws Exception
	{
		omlist = pageCompose.composeFindInteractModel();
		list = pageCompose.compostInteractModel();
		return SUCCESS;
	}

	public String index() throws Exception
	{
		omlist = pageCompose.composeFindInteractModel();
		list = pageCompose.compostInteractModel();
		return SUCCESS;
	}

	public String submit() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		int rs = 0;
		String result = "";
		if ("DEL".equals(status) && lid != 0)
		{
			rs = pageCompose.deleteOwnerInteract(lid, account.getOwner().getId());
			result = rs > 0 ? "Y" : "N";
		}else if("RENAME".equals(status) && lid != 0){
			rs = pageCompose.updateOwnerInteractTitle(lid, account.getOwner().getId(),title);
			switch (rs) {
				case 1:result="Y";break;
				case 0:result="N";break;
				case 2:result="D";break;
			}
		}else if("SHOW".equals(status) && lid != 0){
			rs = pageCompose.updateOwnerInteractStatus(lid, account.getOwner().getId(),"CMP");
			result = rs > 0 ? "Y" : "N";
		}else if("HIDE".equals(status) && lid != 0){
			rs = pageCompose.updateOwnerInteractStatus(lid, account.getOwner().getId(),"REG");
			result = rs > 0 ? "Y" : "N";
		}
		

		out.print(new Gson().toJson(result));
		out.flush();
		out.close();
		return null;
	}

	public String addOwnerInteract() throws Exception
	{

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		String result = pageCompose.addOwnerInteract(title, mid, account.getOwner().getId());
		out.print(new Gson().toJson(result));
		out.flush();
		out.close();
		return null;

	}

	public List<InteractModel> getList()
	{
		return list;
	}

	public void setList(List<InteractModel> list)
	{
		this.list = list;
	}

	public String getLids()
	{
		return lids;
	}

	public void setLids(String lids)
	{
		this.lids = lids;
	}

	public List<InteractModel> getOmlist()
	{
		return omlist;
	}

	public void setOmlist(List<InteractModel> omlist)
	{
		this.omlist = omlist;
	}

	public long getMid()
	{
		return mid;
	}

	public void setMid(long mid)
	{
		this.mid = mid;
	}

	public long getLid()
	{
		return lid;
	}

	public void setLid(long lid)
	{
		this.lid = lid;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

}
