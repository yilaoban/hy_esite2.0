package com.huiyee.esite.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.constants.IPrivilegeConstants;
import com.huiyee.esite.dto.SiteGroupDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.Sitegroup;
import com.huiyee.esite.service.Permission;

public class SiteGroupIndexAction extends AbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2482243392464883601L;
	private int pageId = 1;
	private SiteGroupDto dto;
	private int sitetype;
	private Sitegroup sitegroup;
	private long sgid;
	private long id;
	private String name;
	private long pgid;
	private long siteid;
	private Site site;
	private String grouptype;
	private String subtype;
	
	@Override
	public int getLightType()
	{
		return 1;
	}
	
	public String getSubtype()
	{
		return subtype;
	}

	
	public void setSubtype(String subtype)
	{
		this.subtype = subtype;
	}

	//site列表
	@Override
	public String execute() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (SiteGroupDto) pageCompose.composeSiteGroupList(account, pageId,IPageConstants.SITEGROUP_TYPE_EVENT);
		ServletActionContext.getRequest().getSession().setAttribute("positionId", IPageConstants.POSITIONID_1);
		return SUCCESS;
	}
	
	public String sitegroupListLink() throws Exception{
		dto = (SiteGroupDto) pageCompose.sitegroupListLink(sgid);
		//sitegroup = pageCompose.sitegroupListbyId(sgid);
		site = pageCompose.findSiteWithGrpById(siteid);
		if(dto.getLinkList().size() == 0){
			pgid = 0;
		}else{
			pgid = dto.getLinkList().get(0).getPageid();
		}
		return SUCCESS;
    }
	
	public String website() throws Exception
	{
		String stp=IPageConstants.SITEGROUP_TYPE_WEBSITE;
		if(subtype!=null){
			stp=subtype;
		}
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (SiteGroupDto) pageCompose.composeSiteGroupList(account, pageId,stp);
		ServletActionContext.getRequest().getSession().setAttribute("positionId", IPageConstants.POSITIONID_2);
		return SUCCESS;
	}
	
	/**
	 * 微应用的展示页面
	 * 根据不同的
	 * @return
	 * @throws Exception
	 */
	public String appSite() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (SiteGroupDto) pageCompose.composeSiteGroupByType(account, pageId,grouptype);
		return SUCCESS;
	}
	
	public String getSiteGroup() throws Exception
    {
        Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
        sitegroup=pageCompose.composeSiteGroup(account, sgid);
        sitetype=1;
        return SUCCESS;
    }
	
	public String deleteSite()throws Exception{
		int len=pageCompose.composeDelSite(id);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.write(len);
		out.flush();
		out.close();
		return null;
	}
	
	public String changeSiteName()throws Exception{
		int result=0;
        if(name!=null&&!"".equals(name)){
            result = pageCompose.updateSiteName(name, id);
        }else{
            result=2;//名称为空
        }
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
        return null;
	}
	
	public String shareGroup()throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        int len=pageCompose.updateSiteGroupIsTemplateSetY(id);
        PrintWriter out = response.getWriter();
        out.print(len);
        out.flush();
        out.close();
        return null;
	}
	
	public String cancelShare()throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        int len=pageCompose.updateSiteGroupIsTemplateSetN(id);
        PrintWriter out = response.getWriter();
        out.print(len);
        out.flush();
        out.close();
        return null;
	}
	
	//微现场leftpage
	public String xcSiteGroup() throws Exception{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (SiteGroupDto) pageCompose.composeSiteGroupList(account, pageId,IPageConstants.SITEGROUP_TYPE_XIANCHANG);
		ServletActionContext.getRequest().getSession().setAttribute("positionId", IPageConstants.POSITIONID_3);
		return SUCCESS;
	}

    public int getPageId() {
        return pageId;
    }


    public void setPageId(int pageId) {
        this.pageId = pageId;
    }


    public long getSgid() {
        return sgid;
    }

    public void setSgid(long sgid) {
        this.sgid = sgid;
    }

    public SiteGroupDto getDto() {
        return dto;
    }


    public void setDto(SiteGroupDto dto) {
        this.dto = dto;
    }
    public int getSitetype() {
        return sitetype;
    }

    public Sitegroup getSitegroup() {
        return sitegroup;
    }

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	
	public long getPgid()
	{
		return pgid;
	}

	
	public void setPgid(long pgid)
	{
		this.pgid = pgid;
	}

	
	public long getSiteid()
	{
		return siteid;
	}

	
	public void setSiteid(long siteid)
	{
		this.siteid = siteid;
	}

	
	public Site getSite()
	{
		return site;
	}

	
	public void setSite(Site site)
	{
		this.site = site;
	}

	
	public String getGrouptype()
	{
		return grouptype;
	}

	
	public void setGrouptype(String grouptype)
	{
		this.grouptype = grouptype;
	}


}
