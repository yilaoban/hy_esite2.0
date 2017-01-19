package com.huiyee.esite.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.dto.OwnerBanlanceDto;
import com.huiyee.esite.mgr.IOwnerBalanceMgr;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.esite.util.HyConfig;
import com.opensymphony.xwork2.ActionContext;

public class OwnerBalanceAction extends AbstractAction{
	private static final long serialVersionUID = -1583806212957413999L;
	private IOwnerBalanceMgr ownerBalanceMgr;
	private OwnerBanlanceDto dto;
	private long id;
	private String type = "S"; //S 商城  H:线下签到页面扫二维码使用会员卡支付
	
	public void setOwnerBalanceMgr(IOwnerBalanceMgr ownerBalanceMgr) {
		this.ownerBalanceMgr = ownerBalanceMgr;
	}

	@Override
	public String execute() throws Exception {
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if(vu != null){
			dto = (OwnerBanlanceDto) ownerBalanceMgr.findPayTypeInfo(vu.getHyUserId(),id,type,this.getOwner());
		}
		return SUCCESS;
	}
	
	/**
	 * 会员卡支付
	 * @throws Exception
	 */
	public void payBalance() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		String ip = ClientUserIp.getIpAddr(request);
		int res = 0;
		if(vu != null){
			String url = HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl() + "/user/payHome.action";
			res = ownerBalanceMgr.updateBanlance(vu.getHyUserId(),this.getOwner(),id,ip,type,url);
		}
		out.print(res);
		out.flush();
		out.close();
	}

	public OwnerBanlanceDto getDto() {
		return dto;
	}

	public void setDto(OwnerBanlanceDto dto) {
		this.dto = dto;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
