
package com.huiyee.interact.appointment.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.solr.HylogSolrServer;
import com.huiyee.interact.appointment.dto.AppointmentDataDto;
import com.huiyee.interact.appointment.mgr.IInteractAptManager;
import com.huiyee.interact.appointment.model.AppointmentModel;
import com.opensymphony.xwork2.ActionContext;

public class OrderDataAction extends InteractModelAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long aptid;
	private int data_select = -1;
	private int pageId = 1;
	private AppointmentDataDto dto;
	private IInteractAptManager interactAptManager;
	private List<String> mapping;
	private String starttime;//导出的开始时间
	private String endtime;//导出的结束时间
	private HylogSolrServer hylogSolrServer;
	private int gz_i;
	private List<List<Object>> areaList;
	
	private long xcid;//微现场也有申请
	/**
	 * 新浪申请
	 * 
	 * @return
	 * @throws Exception
	 */
	public String checkRecord() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		// if (data_select == -1)
		// {
		// data_select = 0;
		// }
		dto = (AppointmentDataDto) interactAptManager.checkOrderRecord(aptid, ownerid, data_select, pageId);
		return SUCCESS;
	}

	public String dataShow() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		dto = (AppointmentDataDto) interactAptManager.findUsedMapping(aptid, ownerid);
		return SUCCESS;
	}

	public String submit() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int rs = 0;
		if (mapping.size() > 0)
		{
			rs = interactAptManager.updateDataShow(aptid, mapping);
		}
		String result = rs > 0 ? "Y" : "N";
		out.print(result);
		out.flush();
		out.close();
		return null;
	}

	public String dateClean() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		int rs = 0;
		if (account != null && aptid > 0)
		{
			rs = interactAptManager.updateAptClean(aptid, account.getOwner().getId());
		}
		out.print(rs);
		out.flush();
		out.close();
		return null;
	}

	public String export() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		AppointmentModel apm = interactAptManager.findOrderMes(account.getOwner().getId(), aptid);
		List<String> list = interactAptManager.findExportData(account.getOwner().getId(), data_select, aptid,starttime,endtime);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/csv;charset=GBK");
		response.setHeader("Content-Disposition", "attachment;filename=" + encodingFileName(apm.getTitle()) + ".csv");
		PrintWriter out = response.getWriter();
		if (list != null && list.size() != 0)
		{
			for (String str : list) {
				out.println(str);
			}
		} else
		{
			out.print("无相关信息");
		}
		return null;
	}

	public String appointmentMoveToGroup() throws Exception{
		areaList = hylogSolrServer.findHudongUserArea("wx", "h", "f", aptid, gz_i);
		return SUCCESS;
	}
	
	
	private String encodingFileName(String name)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf.format(new Date());
		String result = name + time;
		try
		{
			return new String(result.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e)
		{
			return time;
		}
	}

	public long getAptid()
	{
		return aptid;
	}

	public void setAptid(long aptid)
	{
		this.aptid = aptid;
	}

	public int getData_select()
	{
		return data_select;
	}

	public void setData_select(int data_select)
	{
		this.data_select = data_select;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public AppointmentDataDto getDto()
	{
		return dto;
	}

	public void setDto(AppointmentDataDto dto)
	{
		this.dto = dto;
	}

	public void setInteractAptManager(IInteractAptManager interactAptManager)
	{
		this.interactAptManager = interactAptManager;
	}

	public List<String> getMapping()
	{
		return mapping;
	}

	public void setMapping(List<String> mapping)
	{
		this.mapping = mapping;
	}
	
	public String getStarttime()
	{
		return starttime;
	}

	
	public void setStarttime(String starttime)
	{
		this.starttime = starttime;
	}

	
	public String getEndtime()
	{
		return endtime;
	}

	
	public void setEndtime(String endtime)
	{
		this.endtime = endtime;
	}
	
	public int getGz_i()
	{
		return gz_i;
	}

	
	public void setGz_i(int gz_i)
	{
		this.gz_i = gz_i;
	}

	
	public List<List<Object>> getAreaList()
	{
		return areaList;
	}

	
	public void setAreaList(List<List<Object>> areaList)
	{
		this.areaList = areaList;
	}

	
	public void setHylogSolrServer(HylogSolrServer hylogSolrServer)
	{
		this.hylogSolrServer = hylogSolrServer;
	}

	
	public long getXcid()
	{
		return xcid;
	}

	
	public void setXcid(long xcid)
	{
		this.xcid = xcid;
	}
	@Override
	public long getMid()
	{
		return 10000;
	}
}
