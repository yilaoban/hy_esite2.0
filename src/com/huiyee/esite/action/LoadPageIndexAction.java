package com.huiyee.esite.action;



import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.LoadPageIndexDto;
import com.huiyee.esite.model.Account;

public class LoadPageIndexAction extends AbstractAction
{
	/**
     * 
     */
    private static final long serialVersionUID = 1764931102875552330L;
    private LoadPageIndexDto dto;
    private String type=IPageConstants.SITE_TYPE_C;
    private String sitename;
    private ArrayList<Long> moduleList;
    private String result;
    private long appid;
    private long siteid;
	//site列表
	@Override
	public String execute() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (LoadPageIndexDto) pageCompose.composeLoadPageIndex(account, type);
		return SUCCESS;
	}
	// 修改site
    public String loadSiteUpdate() throws Exception
    {
        dto = (LoadPageIndexDto) pageCompose.composeUpdateLoadSite(siteid);
        if(dto.getSite()!=null){
            sitename = dto.getSite().getName();
        }
        moduleList = (ArrayList<Long>) dto.getModuleArr();
        return SUCCESS;
    }
	// 创建site保存
    public String loadPageSiteSub() throws Exception
    {
        if (StringUtils.isEmpty(sitename))
        {
            result = "微博PAGE名称为空!";
            return SUCCESS;
        }
        if (moduleList == null || moduleList.size() == 0)
        {
            result = "版块未选!";
            return SUCCESS;
        }
        Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
        dto = (LoadPageIndexDto) pageCompose.composeCreateLoadPageSiteSub(account,type,sitename,moduleList,appid);
        result = dto.getResult();
        return SUCCESS;
    }
 // 修改site保存
    public String siteLoadPageUpdateSub() throws Exception
    {
        if (StringUtils.isEmpty(sitename))
        {
            result = "微博PAGE名称为空!";
            return SUCCESS;
        }
        if (moduleList == null || moduleList.size() == 0)
        {
            result = "版块未选!";
            return SUCCESS;
        }
        Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
        dto = (LoadPageIndexDto) pageCompose.composeUpdateLoadPageSiteSub(account, siteid, sitename, moduleList);
        result = dto.getResult();
        return SUCCESS;
    }
    
 // 创建site
    public String addSite() throws Exception
    {
        Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
        int rs = pageCompose.addSite(sitename, account.getOwner().getId(), type,appid);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        out.print(rs);
        out.flush();
        out.close();
        return null;
    }
 // 删除site
    public String del_site() throws Exception
    {
        int rs = pageCompose.composeDelSite(siteid);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        out.print(rs);
        out.flush();
        out.close();
        return null;
    }

    public LoadPageIndexDto getDto() {
        return dto;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSitename() {
        return sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    public ArrayList<Long> getModuleList() {
        return moduleList;
    }

    public void setModuleList(ArrayList<Long> moduleList) {
        this.moduleList = moduleList;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setAppid(long appid) {
        this.appid = appid;
    }
    public void setSiteid(long siteid) {
        this.siteid = siteid;
    }
    public long getSiteid() {
        return siteid;
    }
}
