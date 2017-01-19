package com.huiyee.interact.leader.action;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.constants.IPrivilegeConstants;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.service.Permission;
import com.huiyee.interact.leader.dto.LeaderDto;
import com.huiyee.interact.leader.mgr.ILeaderMgr;

public class LeaderIndexAction extends AbstractAction {

	private static final long serialVersionUID = 5440795842308663440L;
	private ILeaderMgr leaderMgr;
	private LeaderDto dto;
	private int pageId = 1;
	private Date startTime;
	private Date endTime;
	private String status = "ALL";
	private long id;
	private long hyuid;
	private int lightType = 1;

	public void setLeaderMgr(ILeaderMgr leaderMgr) {
		this.leaderMgr = leaderMgr;
	}

	@Override
	@Permission(module=IPrivilegeConstants.MODULE_APP_Ç±¿Í¹ÜÀí,privilege=IPrivilegeConstants.PERMISSION_R)
	public String execute() throws Exception {
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		dto = leaderMgr.findLeaderListByOwnerid(ownerid, pageId, status, startTime, endTime);
		return SUCCESS;
	}

	public String editLeader() throws Exception {
		dto = leaderMgr.findLeaderById(id);
		return SUCCESS;
	}

	public String updateLeader() throws Exception {
		leaderMgr.updateLeader(dto);
		return SUCCESS;
	}

	public String showSource() throws Exception {
		dto = leaderMgr.findLeaderLogListByHyuid(hyuid, pageId);
		return SUCCESS;
	}

	public String delLeader() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String result = "N";
		int res = leaderMgr.delLeader(id);
		if (res > 0) {
			result = "Y";
		}
		out.print(result);
		out.flush();
		out.close();
		return null;
	}

	public LeaderDto getDto() {
		return dto;
	}

	public void setDto(LeaderDto dto) {
		this.dto = dto;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getHyuid() {
		return hyuid;
	}

	public void setHyuid(long hyuid) {
		this.hyuid = hyuid;
	}

	
	public int getLightType()
	{
		return lightType;
	}

}
