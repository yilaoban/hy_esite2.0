
package com.huiyee.esite.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.dto.SiteGroupDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.Sitegroup;

public class CopyPageAction extends AbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5472509522190287618L;

	private SiteGroupDto dto;
	private long siteid;
	private long pageid;
	private String result;
	private List<Site> sitelist;
	private long copysiteid;
	private Map<String, List<Sitegroup>> map;

	public long getCopysiteid()
	{
		return copysiteid;
	}

	public void setCopysiteid(long copysiteid)
	{
		this.copysiteid = copysiteid;
	}

	public List<Site> getSitelist()
	{
		return sitelist;
	}

	public void setSitelist(List<Site> sitelist)
	{
		this.sitelist = sitelist;
	}

	public String execute() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		sitelist = pageCompose.composeSiteGroupList(account);
		if (sitelist.size() > 0)
		{
			Map<Long, List<Site>> sgmap = new HashMap<Long, List<Site>>();
			Map<Long, String> rmap = new HashMap<Long, String>();
			map = new HashMap<String, List<Sitegroup>>();
			for (Site s : sitelist)
			{
				if(s.getGroupStype()==null){
					System.out.println(s.getId()+":"+s.getGroupStype());
				}
				if (sgmap.get(s.getSitegroupid()) == null)
				{
					List<Site> list = new ArrayList<Site>();
					list.add(s);
					sgmap.put(s.getSitegroupid(), list);
				} else
				{
					sgmap.get(s.getSitegroupid()).add(s);
				}

				if (rmap.get(s.getSitegroupid()) == null)
				{
					rmap.put(s.getSitegroupid(), s.getGroupStype());
				}
			}
			for (long groupid : rmap.keySet())
			{
				String stype = rmap.get(groupid);
				Sitegroup sg = new Sitegroup();
				sg.setId(groupid);
				sg.setSite(sgmap.get(groupid));
				sg.setGroupname(sgmap.get(groupid).get(0).getGroupName());
				if (map.get(stype) == null)
				{
					List<Sitegroup> ls = new ArrayList<Sitegroup>();
					ls.add(sg);
					map.put(stype, ls);
				} else
				{
					map.get(stype).add(sg);
				}

			}

		}
		return SUCCESS;
	}

	// ¸´ÖÆ±£´æ
	public String saveNewPage() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("text/html; charset=utf-8");
		int result = pageCompose.saveNewPage(copysiteid, pageid);
		PrintWriter pw = response.getWriter();
		pw.print(result);
		pw.flush();
		pw.close();
		return null;
	}

	public SiteGroupDto getDto()
	{
		return dto;
	}

	public void setDto(SiteGroupDto dto)
	{
		this.dto = dto;
	}

	public long getSiteid()
	{
		return siteid;
	}

	public void setSiteid(long siteid)
	{
		this.siteid = siteid;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}

	
	public Map<String, List<Sitegroup>> getMap()
	{
		return map;
	}

	
	public void setMap(Map<String, List<Sitegroup>> map)
	{
		this.map = map;
	}

}
