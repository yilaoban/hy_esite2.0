
package com.huiyee.interact.xc.action;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.appointment.dto.AddOrderDto;
import com.huiyee.interact.appointment.mgr.IInteractAptManager;
import com.huiyee.interact.appointment.model.AppointmentModel;
import com.huiyee.interact.appointment.model.OrderMappingModel;
import com.huiyee.interact.xc.dto.XchdDto;
import com.huiyee.interact.xc.mgr.IXcMgr;
import com.huiyee.interact.xc.mgr.InteractCommentMgr;
import com.huiyee.interact.xc.model.InteractConfig;

public class XchdAction extends AbstractAction
{

	private int interactid;
	private long xcid;
	private IXcMgr xcMgr;
	private XchdDto dto;
	private long groupid;
	private long hdid;

	private AppointmentModel apt;
	private long aptid;
	private List<OrderMappingModel> subSys;// 固定字段
	private List<OrderMappingModel> subUdf;// 自定义字段

	private IInteractAptManager interactAptManager;
	private int rs;

	public void setInteractAptManager(IInteractAptManager interactAptManager)
	{
		this.interactAptManager = interactAptManager;
	}

	public String index() throws Exception
	{
		dto = (XchdDto) xcMgr.findAppendHd();
		return SUCCESS;
	}

	@Override
	public String execute() throws Exception
	{

		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long id = xcMgr.saveSimpleHd(interactid, account, xcid, groupid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(id);
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 删除现场互动
	 * 
	 * @return
	 * @throws Exception
	 */
	public String rmXchd() throws Exception
	{

		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		int id = xcMgr.deleteXchd(interactid, account, xcid, hdid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(id);
		out.flush();
		out.close();
		return null;
	}

	public String hdstart() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		int rs = xcMgr.updatehdstart(interactid, hdid, ownerid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(rs);
		out.flush();
		out.close();
		return null;
	}

	public String XcAptIndex() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		apt = interactAptManager.findOrderMesNew(ownerid, aptid);
		return SUCCESS;
	}

	public String XcAptSub() throws Exception
	{
		Calendar c = Calendar.getInstance();
		apt.setStarttimeDate(c.getTime());
		c.add(Calendar.YEAR, 1);
		apt.setEndtimeDate(c.getTime());
		apt.setTotallimit(1);
		apt.setDaylimit(1);
		apt.setIslottery("N");
		rs = interactAptManager.updateOrderMes(apt, apt.getId(), subSys, subUdf);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(rs);
		out.flush();
		out.close();
		return null;

	}

	public int getInteractid()
	{
		return interactid;
	}

	public void setInteractid(int interactid)
	{
		this.interactid = interactid;
	}

	public IXcMgr getXcMgr()
	{
		return xcMgr;
	}

	public void setXcMgr(IXcMgr xcMgr)
	{
		this.xcMgr = xcMgr;
	}

	public long getXcid()
	{
		return xcid;
	}

	public void setXcid(long xcid)
	{
		this.xcid = xcid;
	}

	public XchdDto getDto()
	{
		return dto;
	}

	public void setDto(XchdDto dto)
	{
		this.dto = dto;
	}

	public long getGroupid()
	{
		return groupid;
	}

	public void setGroupid(long groupid)
	{
		this.groupid = groupid;
	}

	public void setHdid(long hdid)
	{
		this.hdid = hdid;
	}

	public AppointmentModel getApt()
	{
		return apt;
	}

	public void setApt(AppointmentModel apt)
	{
		this.apt = apt;
	}

	public List<OrderMappingModel> getSubSys()
	{
		return subSys;
	}

	public void setSubSys(List<OrderMappingModel> subSys)
	{
		this.subSys = subSys;
	}

	public List<OrderMappingModel> getSubUdf()
	{
		return subUdf;
	}

	public void setSubUdf(List<OrderMappingModel> subUdf)
	{
		this.subUdf = subUdf;
	}

	public long getHdid()
	{
		return hdid;
	}

	public long getAptid()
	{
		return aptid;
	}

	public void setAptid(long aptid)
	{
		this.aptid = aptid;
	}

	public int getRs()
	{
		return rs;
	}

	public void setRs(int rs)
	{
		this.rs = rs;
	}

}
