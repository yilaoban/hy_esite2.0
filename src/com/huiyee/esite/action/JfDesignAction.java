package com.huiyee.esite.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.constants.IPrivilegeConstants;
import com.huiyee.esite.dto.BalancePageDto;
import com.huiyee.esite.mgr.IJfDesignMgr;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.BalanceSet;
import com.huiyee.esite.service.Permission;
import com.huiyee.interact.checkin.dto.CheckinDto;
import com.huiyee.interact.checkin.mgr.ICheckinMgr;
import com.huiyee.interact.checkin.model.Checkin;


public class JfDesignAction extends AbstractAction
{
	private static final long serialVersionUID = 8801166034801181497L;
	private IJfDesignMgr jfDesignMgr;
	private ICheckinMgr checkinMgr;
	private BalanceSet balanceSet;
	private Checkin checkin;
	private CheckinDto dto;
	private int pageId = 1;
	private String tab = "S"; //S 设置 U：用户 Q：签到
	private long sitegroupid;
	
	public void setCheckinMgr(ICheckinMgr checkinMgr)
	{
		this.checkinMgr = checkinMgr;
	}

	public void setJfDesignMgr(IJfDesignMgr jfDesignMgr)
	{
		this.jfDesignMgr = jfDesignMgr;
	}

	@Override
	@Permission(module=IPrivilegeConstants.MODULE_APP_微积分,privilege=IPrivilegeConstants.PERMISSION_R)
	public String execute() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long owner = account.getOwner().getId();
		balanceSet = jfDesignMgr.findBalanceSetByOwner(owner);
		checkin = checkinMgr.findCheckRuleByOwner(owner);
		return SUCCESS;
	}
	
	//转发
	public String savezfjfDesign() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long owner = account.getOwner().getId();
		int res = jfDesignMgr.savezfjfDesign(owner,balanceSet);
		out.print(res);
		out.flush();
		out.close();
		return null;
	}
	
	//签到
	public String saveqdjfDesign() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long owner = account.getOwner().getId();
		int res = jfDesignMgr.saveqdjfDesign(owner,checkin);
		out.print(res);
		out.flush();
		out.close();
		return null;
	}
	
	//社区
	public String savesqjfDesign() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long owner = account.getOwner().getId();
		int res = jfDesignMgr.savesqjfDesign(owner,balanceSet);
		out.print(res);
		out.flush();
		out.close();
		return null;
	}
	
	//赠送
	public String savezsjfDesign() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long owner = account.getOwner().getId();
		int res = jfDesignMgr.savezsjfDesign(owner,balanceSet);
		out.print(res);
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 线下签到
	 * @return
	 * @throws Exception
	 */
	public String savexqjfDesign() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long owner = account.getOwner().getId();
		int res = jfDesignMgr.savexqjfDesign(owner,balanceSet);
		out.print(res);
		out.flush();
		out.close();
		return null;
	}
	/**
	 * 预约
	 * @return
	 * @throws Exception
	 */
	public String saveyyjfDesign() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long owner = account.getOwner().getId();
		int res = jfDesignMgr.saveyyjfDesign(owner,balanceSet);
		out.print(res);
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 服务评价
	 * @return
	 * @throws Exception
	 */
	public String savepjjfDesign() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long owner = account.getOwner().getId();
		int res = jfDesignMgr.savepjjfDesign(owner,balanceSet);
		out.print(res);
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 充值描述
	 * @return
	 * @throws Exception
	 */
	public String saveczDesign() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long owner = account.getOwner().getId();
		int res = jfDesignMgr.saveczDesign(owner,balanceSet);
		out.print(res);
		out.flush();
		out.close();
		return null;
	}
	
	@Permission(module=IPrivilegeConstants.MODULE_APP_微积分,privilege=IPrivilegeConstants.PERMISSION_R)
	public String findCheckinRecordList() throws Exception{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long owner = account.getOwner().getId();
		dto = (CheckinDto) checkinMgr.findCheckinRecordList(pageId,owner);
		return SUCCESS;
	}
	
	public void findJfUsedSitegroup() throws Exception{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long owner = account.getOwner().getId();
		BalanceSet balanceSet = jfDesignMgr.findBalanceSetByOwner(owner);
		BalancePageDto dto=new BalancePageDto();
		if(balanceSet!=null&&StringUtils.isNotEmpty(balanceSet.getPageset())){
			dto=new Gson().fromJson(balanceSet.getPageset(), BalancePageDto.class);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("json/application;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(dto));
		out.flush();
		out.close();
	
	}
	
	public void savePageSet() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("json/application;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long owner = account.getOwner().getId();
		int rs=jfDesignMgr.saveJfPageSet(owner,sitegroupid);
		out.print(new Gson().toJson(rs));
		out.flush();
		out.close();
	}
	
	public BalanceSet getBalanceSet()
	{
		return balanceSet;
	}

	
	public void setBalanceSet(BalanceSet balanceSet)
	{
		this.balanceSet = balanceSet;
	}

	
	public Checkin getCheckin()
	{
		return checkin;
	}

	
	public void setCheckin(Checkin checkin)
	{
		this.checkin = checkin;
	}

	
	public int getPageId()
	{
		return pageId;
	}

	
	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	
	public CheckinDto getDto()
	{
		return dto;
	}

	
	public void setDto(CheckinDto dto)
	{
		this.dto = dto;
	}

	
	public String getTab()
	{
		return tab;
	}

	
	public void setTab(String tab)
	{
		this.tab = tab;
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
