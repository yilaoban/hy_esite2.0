
package com.huiyee.esite.action;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.OwnerSourceDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.DHMenu;
import com.huiyee.esite.model.OwnerSource;

public class OwnerSourceAction extends AbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2656190775434190086L;
	private OwnerSourceDto dto;
	private long id;
	private long vmid;
	private String title;
	private DHMenu menu;
	private int idx1 = 0;
	private int idx2 = -1;
	private long pageid;
	private Map<String, String> map;

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public int getIdx1()
	{
		return idx1;
	}

	public void setIdx1(int idx1)
	{
		this.idx1 = idx1;
	}

	public int getIdx2()
	{
		return idx2;
	}

	public void setIdx2(int idx2)
	{
		this.idx2 = idx2;
	}

	public DHMenu getMenu()
	{
		return menu;
	}

	public void setMenu(DHMenu menu)
	{
		this.menu = menu;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getVmid()
	{
		return vmid;
	}

	public void setVmid(long vmid)
	{
		this.vmid = vmid;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public OwnerSourceDto getDto()
	{
		return dto;
	}

	@Override
	public String execute() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (OwnerSourceDto) pageCompose.findOwnerSiteSourceByOwnerid(account.getOwner().getId());
		return SUCCESS;
	}

	public String saveStyle() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		int rs = pageCompose.updateOwnerSourceStyle(id, account, map);
		PrintWriter out = response.getWriter();
		out.print(rs);
		out.flush();
		out.close();
		return null;
	}

	public String delSource() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		int result = pageCompose.deleteOwnerSource(account.getOwner().getId(), id);
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}

	public int getLightType()
	{
		return 3;
	}

	public String add_source() throws Exception
	{
		dto = (OwnerSourceDto) pageCompose.findSiteSourceVm(IPageConstants.SITE_SOURCE_TYPE_DH);
		return SUCCESS;
	}

	public void save_source() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		if (StringUtils.isEmpty(title) || vmid == 0)
		{
			out.print(-1);
		} else
		{
			Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
			int result = pageCompose.saveOwnerSiteSource(account.getOwner().getId(), vmid, title);
			out.print(result);
		}
		out.flush();
		out.close();
	}

	public String edit_source() throws Exception
	{
		dto = (OwnerSourceDto) pageCompose.editOwnerSiteSource(id);
		return SUCCESS;
	}

	public void save_edit_source() throws Exception
	{
		dto = (OwnerSourceDto) pageCompose.editOwnerSiteSource(id);
		int result = 0;
		if (dto.getOs() != null)
		{
			OwnerSource os = dto.getOs();
			JSONArray ja = os.getJsonArray();
			JSONObject jo;
			if (idx2 >= 0)
			{
				// 新增、更新二级目录
				JSONArray list = ((JSONArray) ((JSONObject) ja.get(idx1)).get("list"));
				if (list != null)
				{
					if (idx2 >= list.size())
					{
						// 新增二级目录
						list.add(JSONObject.fromObject(menu));
					} else
					{
						// 更新二级目录
						jo = (JSONObject) list.get(idx2);
						if (jo != null)
						{
							jo.element("name", menu.getName());
							jo.element("link", menu.getLink());
							jo.element("ico", menu.getIco());
						}
					}
				}
			} else
			{
				// 一级目录
				if (idx1 >= ja.size())
				{
					// 新增一级目录
					ja.add(JSONObject.fromObject(menu));
				} else
				{
					// 更新一级目录
					jo = (JSONObject) ((JSONObject) ja.get(idx1));
					if (jo != null)
					{
						jo.element("name", menu.getName());
						jo.element("link", menu.getLink());
						jo.element("ico", menu.getIco());
					}
				}
			}
			os.setJson(ja.toString());
			result = pageCompose.saveEditOwnerSiteSource(os);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
	}

	public String set_source_to_page() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (OwnerSourceDto) pageCompose.findPage4Source(account.getOwner().getId(), pageid);
		return SUCCESS;
	}

	public void save_source_to_page() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		int result = pageCompose.savePage4Source(account.getOwner().getId(), pageid, id);
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
	}

	// 取消导航
	public void cancel_source_to_page() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		int result = pageCompose.saveCancelPage4Source(account.getOwner().getId(), pageid, IPageConstants.SITE_SOURCE_TYPE_DH);
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
	}

	public Map<String, String> getMap()
	{
		return map;
	}

	public void setMap(Map<String, String> map)
	{
		this.map = map;
	}
	
	public String outOfTime() throws Exception{
		return SUCCESS;
	}
	
	public void remove_daohang() throws Exception{
		dto = (OwnerSourceDto) pageCompose.editOwnerSiteSource(id);
		int result = 0;
		if (dto.getOs() != null)
		{
			OwnerSource os = dto.getOs();
			JSONArray ja = os.getJsonArray();
			if (idx2 >= 0)
			{
				// 新增、更新二级目录
				JSONArray list = ((JSONArray) ((JSONObject) ja.get(idx1)).get("list"));
				if (list != null)
				{
					if (list.size() >= idx2)
					{
						list.remove(idx2);
					}
				}
			} else
			{
				// 一级目录
				if (ja.size() >= idx1 )
				{
					ja.remove(idx1);
				}
			}
			os.setJson(ja.toString());
			result = pageCompose.saveEditOwnerSiteSource(os);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
	}

}
