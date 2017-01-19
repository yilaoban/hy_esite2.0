package com.huiyee.interact.setting.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.constants.IPrivilegeConstants;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.BalanceSet;
import com.huiyee.esite.service.Permission;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.interact.checkin.dto.CheckinDto;
import com.huiyee.interact.checkin.mgr.ICheckinMgr;
import com.huiyee.interact.checkin.model.Checkin;
import com.huiyee.interact.setting.mgr.IUserJfMgr;

/**
 * 用户积分管理
 * @author Administrator
 *
 */
public class UserJfManagerAction extends AbstractAction{
	private static final long serialVersionUID = 3395090364126258925L;
	private IUserJfMgr userJfMgr;
	private ICheckinMgr checkinMgr;
	private BalanceSet balanceSet;
	private Checkin checkin;
	private int lightType = 1; 
	private CheckinDto dto;
	private int pageId = 1;
	
	private int jf;
	private long hyuid;
	
	public void setUserJfMgr(IUserJfMgr userJfMgr) {
		this.userJfMgr = userJfMgr;
	}
	
	public void setCheckinMgr(ICheckinMgr checkinMgr) {
		this.checkinMgr = checkinMgr;
	}

	@Override
	@Permission(module=IPrivilegeConstants.MODULE_APP_微积分,privilege=IPrivilegeConstants.PERMISSION_R)
	public String execute() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long owner = account.getOwner().getId();
		balanceSet = userJfMgr.findBalanceSetByOwner(owner);
		checkin = checkinMgr.findCheckRuleByOwner(owner);
		return SUCCESS;
	}
	
	public String findBalanceSetByOwner() throws Exception{
		lightType = 2;
		balanceSet = userJfMgr.findBalanceSetByOwner(this.getOwner());
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
			int res = userJfMgr.savezfjfDesign(owner,balanceSet);
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
			int res = userJfMgr.saveqdjfDesign(owner,checkin);
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
			int res = userJfMgr.savesqjfDesign(owner,balanceSet);
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
			int res = userJfMgr.savezsjfDesign(owner,balanceSet);
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
			int res = userJfMgr.savexqjfDesign(owner,balanceSet);
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
			int res = userJfMgr.saveyyjfDesign(owner,balanceSet);
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
			int res = userJfMgr.savepjjfDesign(owner,balanceSet);
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
			int res = userJfMgr.saveczDesign(owner,balanceSet);
			out.print(res);
			out.flush();
			out.close();
			return null;
		}
		
		public String updateRmbRule() throws Exception{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
			long owner = account.getOwner().getId();
			int res = userJfMgr.updateRmbRule(owner,balanceSet);
			out.print(res);
			out.flush();
			out.close();
			return null;
		}
		
		public String findCheckinRecordList() throws Exception{
			Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
			long owner = account.getOwner().getId();
			dto = (CheckinDto) checkinMgr.findCheckinRecordList(pageId,owner);
			return SUCCESS;
		}
		
		public String addUserJf() throws Exception{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			int res = userJfMgr.addUserJf(hyuid,jf);
			out.print(res);
			out.flush();
			out.close();
			return null;
		}
		
		public String addUserRmb() throws Exception{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			HttpServletRequest request = ServletActionContext.getRequest();
			String ip = ClientUserIp.getIpAddr(request);
			long res = userJfMgr.addUserRmb(hyuid,jf,ip,this.getOwner());
			out.print(res);
			out.flush();
			out.close();
			return null;
		}
		
		/**
		 * 线下消费多少块获得1积分
		 * @return
		 * @throws Exception
		 */
		public String saveOcxfnum() throws Exception{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			int res = userJfMgr.saveOcxfnum(this.getOwner(),balanceSet);
			out.print(res);
			out.flush();
			out.close();
			return null;
		}
	
		
	public BalanceSet getBalanceSet() {
		return balanceSet;
	}

	public void setBalanceSet(BalanceSet balanceSet) {
		this.balanceSet = balanceSet;
	}

	public Checkin getCheckin() {
		return checkin;
	}

	public void setCheckin(Checkin checkin) {
		this.checkin = checkin;
	}

	public int getLightType() {
		return lightType;
	}

	public void setLightType(int lightType) {
		this.lightType = lightType;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public CheckinDto getDto() {
		return dto;
	}

	public void setDto(CheckinDto dto) {
		this.dto = dto;
	}

	public int getJf() {
		return jf;
	}

	public void setJf(int jf) {
		this.jf = jf;
	}

	public long getHyuid() {
		return hyuid;
	}

	public void setHyuid(long hyuid) {
		this.hyuid = hyuid;
	}

	
	
	

}
