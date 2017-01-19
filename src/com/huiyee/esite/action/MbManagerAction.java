package com.huiyee.esite.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.dto.MbDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.Mb;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.Sitegroup;


public class MbManagerAction extends AbstractAction
{

	private static final long serialVersionUID = 4146550277724405871L;
	private String type;
	private MbDto dto;
	private long tagid;
	private long siteid;
	private List<Page> pages;
	private Mb mb;
	private List<Sitegroup> sitegroup;
	private long mbid;
	private long groupid;
	private String name;
	private String link;
	private String stype;
	
	@Override
	public int getLightType()
	{
		return 2;
	}
	
	public String getLink()
	{
		return link;
	}

	
	public void setLink(String link)
	{
		this.link = link;
	}

	
	public long getCid()
	{
		return cid;
	}

	
	public void setCid(long cid)
	{
		this.cid = cid;
	}


	private long cid;

	@Override
	public String execute() throws Exception
	{
		dto = (MbDto)pageCompose.findMbList(type,tagid);
		if("C".equalsIgnoreCase(type)){
			return "event";
		}
		else{
			return "website";
		}
	}
	
	public String yulan() throws Exception{
		return SUCCESS;
	}
	
	public String addMb() throws Exception{
		pages = pageCompose.findPagesBySiteid(siteid);
		return SUCCESS;
	}
	
	public String addMbSub() throws Exception{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		mb.setOwnerid(account.getOwner().getId());
		int result = pageCompose.saveMb(mb);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}

	public String useMb() throws Exception{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		sitegroup = pageCompose.findSitegroupList(account.getOwner().getId());
		return SUCCESS;
	}
	
	public String useMbSub() throws Exception{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long result = pageCompose.saveUseMb(account.getOwner(), type, mbid, name, groupid, stype);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
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
	
	public MbDto getDto()
	{
		return dto;
	}
	
	public void setDto(MbDto dto)
	{
		this.dto = dto;
	}
	
	public long getTagid()
	{
		return tagid;
	}
	
	public void setTagid(long tagid)
	{
		this.tagid = tagid;
	}
	
	public long getSiteid()
	{
		return siteid;
	}
	
	public void setSiteid(long siteid)
	{
		this.siteid = siteid;
	}
	
	public List<Page> getPages()
	{
		return pages;
	}
	
	public void setPages(List<Page> pages)
	{
		this.pages = pages;
	}

	
	public Mb getMb()
	{
		return mb;
	}
	
	public void setMb(Mb mb)
	{
		this.mb = mb;
	}
	
	public List<Sitegroup> getSitegroup()
	{
		return sitegroup;
	}
	
	public long getMbid()
	{
		return mbid;
	}

	
	public void setMbid(long mbid)
	{
		this.mbid = mbid;
	}

	
	public long getGroupid()
	{
		return groupid;
	}

	
	public void setGroupid(long groupid)
	{
		this.groupid = groupid;
	}

	
	public String getName()
	{
		return name;
	}

	
	public void setName(String name)
	{
		this.name = name;
	}


	
	public String getStype()
	{
		return stype;
	}


	
	public void setStype(String stype)
	{
		this.stype = stype;
	}
	
}
