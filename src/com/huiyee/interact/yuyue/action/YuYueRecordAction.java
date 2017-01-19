package com.huiyee.interact.yuyue.action;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.interact.appointment.model.AppointmentDataModel;
import com.huiyee.interact.yuyue.dto.YuYueRecordDto;
import com.huiyee.interact.yuyue.mgr.IYuYueRecordMgr;
import com.opensymphony.xwork2.ActionContext;


public class YuYueRecordAction extends AbstractAction
{
	private static final long serialVersionUID = -8348722717511449201L;
	private IYuYueRecordMgr yuYueRecordMgr;
	private YuYueRecordDto dto;
	private AppointmentDataModel aptRecord;
	private long catid;
	private long serid;
	private Date yytime;
	private String hydesc;
	private long pageid;
	private long serviceid;
	private String type;//默认：N  已知科室 医生：U   已知科室 服务 ：S
	private String tag1;
	private String tag2;
	
	public void setYuYueRecordMgr(IYuYueRecordMgr yuYueRecordMgr)
	{
		this.yuYueRecordMgr = yuYueRecordMgr;
	}
	
	/**
	 * 预约表单提交  serid：-2 代表随机 -1 代表未选择
	 * 服务serviceid 0：代表未选择
	 */
	@Override
	public String execute() throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String ip = ClientUserIp.getIpAddr(request); 
		VisitUser visit = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if(visit != null && visit.getWxUser() != null){
			dto = (YuYueRecordDto) yuYueRecordMgr.saveYuYueRecord(visit,this.getOwner(),aptRecord,catid,serid,yytime,ip,hydesc,pageid,serviceid,type,this.getOnameUrl(),tag1,tag2);
		}else{
			dto = new YuYueRecordDto();
			dto.setStatus(1);
			dto.setDesc("预约失败，获取用户信息出错！");
		}
		Gson gson = new Gson();
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}

	
	public YuYueRecordDto getDto()
	{
		return dto;
	}

	
	public void setDto(YuYueRecordDto dto)
	{
		this.dto = dto;
	}

	
	public AppointmentDataModel getAptRecord()
	{
		return aptRecord;
	}

	
	public void setAptRecord(AppointmentDataModel aptRecord)
	{
		this.aptRecord = aptRecord;
	}

	
	public long getCatid()
	{
		return catid;
	}

	
	public void setCatid(long catid)
	{
		this.catid = catid;
	}

	
	public long getSerid()
	{
		return serid;
	}

	
	public void setSerid(long serid)
	{
		this.serid = serid;
	}

	
	public Date getYytime()
	{
		return yytime;
	}

	public void setYytime(Date yytime)
	{
		this.yytime = yytime;
	}

	
	public long getPageid()
	{
		return pageid;
	}

	
	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	
	public String getHydesc()
	{
		return hydesc;
	}

	
	public void setHydesc(String hydesc)
	{
		this.hydesc = hydesc;
	}

	
	public long getServiceid()
	{
		return serviceid;
	}

	
	public void setServiceid(long serviceid)
	{
		this.serviceid = serviceid;
	}

	
	public String getType()
	{
		return type;
	}

	
	public void setType(String type)
	{
		this.type = type;
	}

	public String getTag1() {
		return tag1;
	}

	public void setTag1(String tag1) {
		this.tag1 = tag1;
	}

	public String getTag2() {
		return tag2;
	}

	public void setTag2(String tag2) {
		this.tag2 = tag2;
	}

	

	
}
