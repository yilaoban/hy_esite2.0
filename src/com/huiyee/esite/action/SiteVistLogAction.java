package com.huiyee.esite.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.dto.SiteVistLogDto;
import com.huiyee.esite.model.Account;
import com.opensymphony.xwork2.ActionContext;

public class SiteVistLogAction extends AbstractAction
{
	private long sitegroupid;
	private SiteVistLogDto dto;
	private String type;
	private int pageId = 1;

	// 统计site
	@Override
	public String execute() throws Exception
	{
		dto = (SiteVistLogDto) pageCompose.composeVistLogList(sitegroupid, type, pageId);
		return SUCCESS;
	}

	public String export() throws Exception{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		List<String> exportlist = pageCompose.exportSiteCountDetail(sitegroupid, type,account.getOwner().getId());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/csv;charset=GBK");
		response.setHeader("Content-Disposition", "attachment;filename=" + encodingFileName(type) + ".csv");
		PrintWriter out = response.getWriter();
		if (exportlist != null && exportlist.size() != 0) {
			for (String str : exportlist) {
				out.println(str);
			}
		} else {
			out.print("无相关信息");
		}
		return null;
	}
	
	private String encodingFileName(String type) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf.format(new Date());
		String result = null;
		if("Y".equals(type)){
			result=time+"匿名访问明细";
		}else if("N".equals(type)){
			result=time+"非匿名访问明细";
		}
		try {
			return new String(result.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			return time;
		}
	}
	
	
	public void setType(String type)
	{
		this.type = type;
	}


	public SiteVistLogDto getDto()
	{
		return dto;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}



	public long getSitegroupid()
	{
		return sitegroupid;
	}



	public void setSitegroupid(long sitegroupid)
	{
		this.sitegroupid = sitegroupid;
	}

}
