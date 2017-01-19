
package com.huiyee.esite.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.CardDto;
import com.huiyee.esite.dto.ZujianDto;
import com.huiyee.esite.mgr.ISiteSourceManager;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.SiteSource;
import com.huiyee.esite.service.FileUploadService;
import com.huiyee.esite.util.HyConfig;
import com.opensymphony.xwork2.ActionContext;

public class ZujianAction extends AbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1381102533120126723L;
	private ZujianDto dto;
	private long pcid;
	private long bid;// 组件id
	private long siteid;
	private String name;
	private SiteSource sitesource;

	public void setDto(ZujianDto dto)
	{
		this.dto = dto;
	}

	public long getBid()
	{
		return bid;
	}

	public void setBid(long bid)
	{
		this.bid = bid;
	}

	public ZujianDto getDto()
	{
		return dto;
	}

	public long getPcid()
	{
		return pcid;
	}

	public void setPcid(long pcid)
	{
		this.pcid = pcid;
	}

	@Override
	public String execute() throws Exception
	{
		dto = (ZujianDto) pageCompose.findZujian();
		return SUCCESS;
	}

	public String add() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		String html = pageCompose.addZujian(pcid, bid);
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(html);
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 上传组件资源
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addSourceIndex() throws Exception
	{
		return SUCCESS;
	}

	/**
	 * 上传组建资源文件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String uploadZjs() throws Exception
	{

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Access-Control-Allow-Origin", HyConfig.getPageyuming(this.getOwner()));
		response.addHeader("Access-Control-Allow-Methods", "HEAD,GET,POST,PUT,DELETE,OPTIONS");
		response.addHeader("Access-Control-Allow-Headers", "Content-Type,Origin,Accept");
		response.addHeader("Access-Control-Max-Age", "120");
		PrintWriter out = response.getWriter();
		HttpServletRequest request = ServletActionContext.getRequest();
		if ("OPTIONS".equals(request.getMethod()))
		{
			return null;
		}

		String returnPath = "/WEB-INF/velocity/source/" + siteid + "/";
		String path = HyConfig.getRoot() + returnPath;
		String fname = FileUploadService.createFileName(name);
		File dir = new File(path);
		if (!dir.exists())
		{
			dir.mkdirs();
		}
		File file = new File(path, fname);
		FileOutputStream fout = new FileOutputStream(file);
		IOUtils.copy(request.getInputStream(), fout);
		fout.flush();
		fout.close();
		String url = returnPath + fname;
		out.print("{\"status\":1,\"path\":\"" + url + "\",\"name\":\"" + name + "\"}");
		out.flush();
		out.close();
		return null;

	}

	/**
	 * 组建资源表单提交
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveZjs() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		int rs = pageCompose.saveSiteSource(siteid, account.getOwner().getId(), sitesource);
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(rs);
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * update资源表单提交
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateZjsSub() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		int rs = pageCompose.updateSiteSource(siteid, account.getOwner().getId(), sitesource);
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(rs);
		out.flush();
		out.close();
		return null;
	}

	public long getSiteid()
	{
		return siteid;
	}

	public void setSiteid(long siteid)
	{
		this.siteid = siteid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public SiteSource getSitesource()
	{
		return sitesource;
	}

	public void setSitesource(SiteSource sitesource)
	{
		this.sitesource = sitesource;
	}

}
