package com.huiyee.interact.appointment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.model.Account;
import com.huiyee.interact.appointment.dto.AddOrderDto;
import com.huiyee.interact.appointment.dto.AppointmentDataDto;
import com.huiyee.interact.appointment.dto.OrderMesDto;
import com.huiyee.interact.appointment.mgr.IInteractAptManager;
import com.huiyee.interact.appointment.model.AppointmentDataModel;
import com.huiyee.interact.appointment.model.AppointmentReflect;
import com.huiyee.interact.appointment.model.Pager;
import com.huiyee.interact.appointment.util.ToolUtil;

public class OrderMesAction extends InteractModelAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2395743727039516708L;
	private int pageId = 1;
	private long ownerid;
	private long id;
	private long aptid;
	private OrderMesDto dto;
	private AddOrderDto adto;
	private AppointmentDataDto addto;
	private AppointmentDataDto apdto;
	private long rid;
	private long wxuid;
	private AppointmentDataModel record;
	private long mid = 10000;

	private IInteractAptManager interactAptManager;

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public long getOwnerid()
	{
		return ownerid;
	}

	public void setOwnerid(long ownerid)
	{
		this.ownerid = ownerid;
	}

	public OrderMesDto getDto()
	{
		return dto;
	}

	public void setDto(OrderMesDto dto)
	{
		this.dto = dto;
	}

	public String execute() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		dto = new OrderMesDto();
		int start = (pageId - 1) * IInteractConstants.APT_LIMIT;
		int total = interactAptManager.findOrderMesTotal(ownerid, IPageConstants.OWNER_INTERACT_SHOW);
		dto.setTotal(total);
		dto.setList(interactAptManager.findOrderMesById(ownerid, IPageConstants.OWNER_INTERACT_SHOW, start, IInteractConstants.APT_LIMIT));
		Pager pager = new Pager(pageId, total, IInteractConstants.APT_LIMIT);
		dto.setPager(pager);
		return SUCCESS;
	}

	public String previewOrder() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		adto = new AddOrderDto();
		adto.setAm(interactAptManager.findOrderMes(ownerid, id));
		return SUCCESS;
	}

	public String selectedData() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		apdto = (AppointmentDataDto) interactAptManager.checkOrderRecord(id, ownerid, 0, pageId);
		return SUCCESS;
	}

	public void setInteractAptManager(IInteractAptManager interactAptManager)
	{
		this.interactAptManager = interactAptManager;
	}

	public String aptDetail() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		record = interactAptManager.findAppointmentDataModelById(rid, ownerid);
		record.setValues(ToolUtil.testReflect(record, new AppointmentReflect()));
		return SUCCESS;
	}
	
	
	public String aptDetailEdit() throws Exception
	{
 		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		record = interactAptManager.findAppointmentDataByWxuid(wxuid,aptid, ownerid);
		if(record==null){
			record=interactAptManager.findAppointMaps(aptid);
		}
		record.setValues(ToolUtil.testReflect(record, new AppointmentReflect()));
			
		return SUCCESS;
	}
	
	public String updateAptRecord() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int res = interactAptManager.updateAptRecord(record);
		out.print(res);
		out.flush();
		out.close();
		return null;
	}

	public String delAptRecord() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/text;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String result = "N";
		int res = interactAptManager.delAptRecord(rid);
		if(res > 0){
			result = "Y";
		}
		out.print(result);
		out.flush();
		out.close();
		return null;
	}
	
	public AddOrderDto getAdto()
	{
		return adto;
	}

	public void setAdto(AddOrderDto adto)
	{
		this.adto = adto;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public AppointmentDataDto getAddto()
	{
		return addto;
	}

	public void setAddto(AppointmentDataDto addto)
	{
		this.addto = addto;
	}

	public long getAptid()
	{
		return aptid;
	}

	public void setAptid(long aptid)
	{
		this.aptid = aptid;
	}

	public AppointmentDataDto getApdto()
	{
		return apdto;
	}

	public void setApdto(AppointmentDataDto apdto)
	{
		this.apdto = apdto;
	}

	public long getRid()
	{
		return rid;
	}

	public void setRid(long rid)
	{
		this.rid = rid;
	}

	public AppointmentDataModel getRecord()
	{
		return record;
	}

	public long getMid()
	{
		return mid;
	}

	public void setMid(long mid)
	{
		this.mid = mid;
	}

	
	public long getWxuid()
	{
		return wxuid;
	}

	
	public void setWxuid(long wxuid)
	{
		this.wxuid = wxuid;
	}

	
	public void setRecord(AppointmentDataModel record)
	{
		this.record = record;
	}
}
