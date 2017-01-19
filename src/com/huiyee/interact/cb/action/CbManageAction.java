
package com.huiyee.interact.cb.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.constants.IPrivilegeConstants;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.service.Permission;
import com.huiyee.interact.cb.dto.CbDataDto;
import com.huiyee.interact.cb.mgr.ICbDataMgr;
import com.huiyee.interact.cb.model.HbConfig;

/**
 * 微传播管理
 * 
 * @author ldw
 * 
 */
public class CbManageAction extends AbstractAction
{

	private int sendType;// 0-全部 1-新增 2-已批准 3-已拒绝
	private CbDataDto dto;
	private ICbDataMgr cbDataMgr;
	private int pageId = 1;
	private long rid;// 申请record id;

	// =====批准/拒绝成为传播者
	private String status;
	private String reason;
	private int lightType = 1;

	// 激励
	private long wxuid;
	private int subPrice;
	private int impelId;

	private String name;
	private String starttime;
	private String endtime;

	private long sender;
	private String ord = "c";// 效果排行排序
	private long cbaid;// activityid;
	private long pageid;// 活动的页面的id

	private HbConfig hbc;
	private long cbSenderid;
	private int stat;
	@Override
	@Permission(module=IPrivilegeConstants.MODULE_APP_合伙人,privilege=IPrivilegeConstants.PERMISSION_R)
	public String execute() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		int exist = cbDataMgr.findCbExist(account);
		if (exist == 0)
		{
			return INPUT;
		} else
		{
			return SUCCESS;
		}
	}

	public String cbCreate() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		int rs = cbDataMgr.addInteractCb(account);
		return SUCCESS;
	}

	@Permission(module=IPrivilegeConstants.MODULE_APP_合伙人,privilege=IPrivilegeConstants.PERMISSION_R)
	public String CbSender() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (CbDataDto) cbDataMgr.findSenderRecordList(account.getOwner().getId(), sendType, account.getOwner().getId(), pageId);
		return SUCCESS;
	}

	/*
	 * 批准/拒绝申请
	 */
	public String CbSenderCheck() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (CbDataDto) cbDataMgr.findSenderRecordList(account.getOwner().getId(), rid, account.getOwner().getId());
		return SUCCESS;
	}

	public String cbSenderSub() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int rs = cbDataMgr.updateCbSenderSub(account.getOwner().getId(), rid, status, reason, account);
		out.print(rs);
		out.flush();
		out.close();
		return null;

	}

	/**
	 * 活动效果
	 * 
	 * @return
	 * @throws Exception
	 */
	public String senderEffectData() throws Exception
	{
		dto = (CbDataDto) cbDataMgr.findSenderEffect(this.getOwner(), cbaid, pageId, starttime, endtime, ord);
		lightType = 5;
		return SUCCESS;
	}

	/**
	 * 激励记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String jiliRecord() throws Exception
	{
		lightType = 3;
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (CbDataDto) cbDataMgr.findJiliRecord(account, account.getOwner().getId(), pageId, name, starttime, endtime, status);
		return SUCCESS;
	}

	public String senderDetail() throws Exception
	{
		lightType = 3;
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (CbDataDto) cbDataMgr.findSenderDetail(account, account.getOwner().getId(), sender, pageId, starttime, endtime);
		return SUCCESS;

	}

	/**
	 * 激励确认
	 * 
	 * @return
	 * @throws Exception
	 */
	public String jiliConfirm() throws Exception
	{
		lightType = 2;
		if (StringUtils.isEmpty(status))
		{
			status = "0";
		}
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (CbDataDto) cbDataMgr.findJiliList(cbaid, account, name, starttime, endtime, pageId, status);
		return SUCCESS;
	}

	/**
	 * 确认,给红包
	 * 
	 * @return
	 * @throws Exception
	 */
	public String jiliSub() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int rs = cbDataMgr.updateJiliSub(impelId, subPrice);
		out.print(rs);
		out.flush();
		out.close();
		return null;
	}

	@Permission(module=IPrivilegeConstants.MODULE_APP_合伙人,privilege=IPrivilegeConstants.PERMISSION_R)
	public String cbActivity() throws Exception
	{
		lightType = 2;
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (CbDataDto) cbDataMgr.findCbActivity(account, pageId);
		return SUCCESS;
	}

	@Permission(module=IPrivilegeConstants.MODULE_APP_合伙人,privilege=IPrivilegeConstants.PERMISSION_R)
	public String hbConfig() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		hbc = cbDataMgr.findHbConfig(account);
		lightType = 6;
		return SUCCESS;
	}

	public String configSub() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int rs = cbDataMgr.updateHbConfig(account, hbc);
		out.print(new Gson().toJson(rs));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 提现记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String fundsRecord() throws Exception
	{
		lightType = 6;
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (CbDataDto) cbDataMgr.findFundsRecord(account, sender, name, starttime, endtime, pageId);
		return SUCCESS;
	}

	/**
	 * 合伙人资产
	 * 
	 * @return
	 * @throws Exception
	 */
	public String senderFunds() throws Exception
	{
		lightType = 6;
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (CbDataDto) cbDataMgr.findCbSender(account, name, pageId);
		return SUCCESS;
	}

	public String cbHongBaoSendCheck() throws Exception{
		return SUCCESS;
	}
	
	public String updateCbSendCheck() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int rs = cbDataMgr.updateCbSendCheck(cbSenderid,stat);
		out.print(new Gson().toJson(rs));
		out.flush();
		out.close();
		return null;
	}
	
	public int getSendType()
	{
		return sendType;
	}

	public void setSendType(int sendType)
	{
		this.sendType = sendType;
	}

	public CbDataDto getDto()
	{
		return dto;
	}

	public void setDto(CbDataDto dto)
	{
		this.dto = dto;
	}

	public void setCbDataMgr(ICbDataMgr cbDataMgr)
	{
		this.cbDataMgr = cbDataMgr;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public long getRid()
	{
		return rid;
	}

	public void setRid(long rid)
	{
		this.rid = rid;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getReason()
	{
		return reason;
	}

	public void setReason(String reason)
	{
		this.reason = reason;
	}

	public int getLightType()
	{
		return lightType;
	}

	public void setLightType(int lightType)
	{
		this.lightType = lightType;
	}

	public long getWxuid()
	{
		return wxuid;
	}

	public void setWxuid(long wxuid)
	{
		this.wxuid = wxuid;
	}

	public String getEndtime()
	{
		return endtime;
	}

	public void setEndtime(String endtime)
	{
		this.endtime = endtime;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getStarttime()
	{
		return starttime;
	}

	public void setStarttime(String starttime)
	{
		this.starttime = starttime;
	}

	public long getSender()
	{
		return sender;
	}

	public void setSender(long sender)
	{
		this.sender = sender;
	}

	public String fenToyuan(int num)
	{
		return String.valueOf((float) num / 100);
	}

	public String getOrd()
	{
		return ord;
	}

	public void setOrd(String ord)
	{
		this.ord = ord;
	}

	public long getCbaid()
	{
		return cbaid;
	}

	public void setCbaid(long cbaid)
	{
		this.cbaid = cbaid;
	}

	public int getSubPrice()
	{
		return subPrice;
	}

	public void setSubPrice(int subPrice)
	{
		this.subPrice = subPrice;
	}

	public int getImpelId()
	{
		return impelId;
	}

	public void setImpelId(int impelId)
	{
		this.impelId = impelId;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public HbConfig getHbc()
	{
		return hbc;
	}

	public void setHbc(HbConfig hbc)
	{
		this.hbc = hbc;
	}

	public long getCbSenderid() {
		return cbSenderid;
	}

	public void setCbSenderid(long cbSenderid) {
		this.cbSenderid = cbSenderid;
	}

	public int getStat() {
		return stat;
	}

	public void setStat(int stat) {
		this.stat = stat;
	}
	
}
