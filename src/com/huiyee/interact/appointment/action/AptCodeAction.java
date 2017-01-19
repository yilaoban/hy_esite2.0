
package com.huiyee.interact.appointment.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.interact.appointment.dto.AptCodeDto;
import com.huiyee.interact.appointment.mgr.IInteractAptManager;

/**
 * …Í«Î-—È÷§¬Î
 * 
 * @author ldw
 * 
 */
public class AptCodeAction extends InteractModelAction
{

	private IInteractAptManager aptManager;
	private AptCodeDto dto;
	private int pageId = 1;
	private String code;
	private long aptid;
	private int status;

	@Override
	public String execute() throws Exception
	{
		dto = (AptCodeDto) aptManager.findAptCodeList(aptid, pageId, code, status);
		return SUCCESS;
	}

	public String aptCodeUpload() throws Exception
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
		List<String> codes = IOUtils.readLines(request.getInputStream());
		if (codes != null && codes.size() > 0)
		{
			List<String> insertList = new ArrayList<String>();
			for (String str : codes)
			{
				if (!insertList.contains(str))
				{
					insertList.add(str.trim());
				}
			}
			int rs = aptManager.saveAptCode(aptid, insertList);
			out.print("{\"status\":1}");
		} else
		{
			out.print("{\"status\":2}");
		}
		out.flush();
		out.close();
		return null;
	}

	public String updateCoded() throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int rs=aptManager.updateAptCode(status,aptid);
		out.print(new Gson().toJson(rs));
		out.flush();
		out.close();
		return null;
	}

	public AptCodeDto getDto()
	{
		return dto;
	}

	public void setDto(AptCodeDto dto)
	{
		this.dto = dto;
	}

	public void setAptManager(IInteractAptManager aptManager)
	{
		this.aptManager = aptManager;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public long getAptid()
	{
		return aptid;
	}

	public void setAptid(long aptid)
	{
		this.aptid = aptid;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}
}
