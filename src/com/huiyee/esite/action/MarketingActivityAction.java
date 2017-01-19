package com.huiyee.esite.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.constants.IPrivilegeConstants;
import com.huiyee.esite.dto.MarketActivityDto;
import com.huiyee.esite.mgr.IMarketActivityMgr;
import com.huiyee.esite.mgr.IWeiXinMgr;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.CbActivity;
import com.huiyee.esite.model.CrmWxHongbaoCheck;
import com.huiyee.esite.service.Permission;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.interact.lottery.mgr.IWxHongbaoMgr;
import com.huiyee.weixin.model.WxMp;

public class MarketingActivityAction extends AbstractAction {
	private static final long serialVersionUID = -1067783017924675352L;
	private MarketActivityDto dto;
	private CbActivity cbActivity;
	private IMarketActivityMgr marketActivityMgr;
	private IWxHongbaoMgr wxHongbaoMgr;
	private IWeiXinMgr weiXinMgr;
	private int pageId = 1;
	private long cbid;
	private long siteid;
	private long aid;
	private CrmWxHongbaoCheck hongbao;
	private int total;
	private float totalshow;
	private String reason;
	private long ckid;
	private String result;
	private int lightType = 5;
	private int status;
	private int type;
	
	@Override
	@Permission(module=IPrivilegeConstants.MODULE_APP_合伙人,privilege=IPrivilegeConstants.PERMISSION_R)
	public String execute() throws Exception {
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		cbid = account.getOwner().getId();
		dto = (MarketActivityDto) pageCompose.findCbActivityListByCbid(cbid, pageId);
		return SUCCESS;
	}

	public String createMarketActivity() throws Exception {
		return SUCCESS;
	}

	public String saveMarketActivity() throws Exception {
		cbActivity.setOwner(this.getOwner());
		int res = marketActivityMgr.saveMarketActivity(cbActivity);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(res));
		out.flush();
		out.close();
		return null;
	}

	public String editMarketActivity() throws Exception {
		cbActivity = marketActivityMgr.findCbActivityByAid(this.getOwner(),aid);
		return SUCCESS;
	}

	public String updateMarketActivity() throws Exception {
		int res = marketActivityMgr.updateMarketActivity(cbActivity);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(res));
		out.flush();
		out.close();
		return null;
	}

	public String delMarketActivity() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String result = "N";
		int res = marketActivityMgr.delMarketActivity(aid);
		if (res > 0) {
			result = "Y";
		}
		out.print(result);
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 发布 取消发布
	 */
	public String cbActivityPublish() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String result = "N";
		int res = marketActivityMgr.updateMarketActivityStatus(aid,status);
		if (res > 0) {
			result = "Y";
		}
		out.print(result);
		out.flush();
		out.close();
		return null;
	}
	
	public String checkMoney() throws Exception {
		hongbao = wxHongbaoMgr.findCrmWxHongbaoCheckByLid(aid, 2);
		if (hongbao != null) {
			total = hongbao.getTotal();
			totalshow = (float)total / 100;
		}
		return SUCCESS;
	}

	/**
	 * 提交审核
	 * 
	 * @return
	 * @throws Exception
	 */
	public String checkMoneySub() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = ClientUserIp.getIpAddr(request);
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long accountid = account.getId();
		String accountname = account.getUsername();
		WxMp woa = weiXinMgr.updateFindWxMp(this.getOwner(), 0);
		if (woa != null)
		{
	        long mpid = woa.getId();
			int type = 2;
			int res = wxHongbaoMgr.saveHongbaoCheck(aid, total, reason, accountid, type, accountname, ip, ckid, mpid);
			if (res == 1)
			{
				result = "Y";
			}
		} else
		{
			result = "N";
		}
		return SUCCESS;
	}

	public void setWeiXinMgr(IWeiXinMgr weiXinMgr)
	{
		this.weiXinMgr = weiXinMgr;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public long getCbid() {
		return cbid;
	}

	public void setCbid(long cbid) {
		this.cbid = cbid;
	}

	public long getSiteid() {
		return siteid;
	}

	public void setSiteid(long siteid) {
		this.siteid = siteid;
	}

	public MarketActivityDto getDto() {
		return dto;
	}

	public void setDto(MarketActivityDto dto) {
		this.dto = dto;
	}

	public CbActivity getCbActivity() {
		return cbActivity;
	}

	public void setCbActivity(CbActivity cbActivity) {
		this.cbActivity = cbActivity;
	}

	public void setMarketActivityMgr(IMarketActivityMgr marketActivityMgr) {
		this.marketActivityMgr = marketActivityMgr;
	}

	public long getAid() {
		return aid;
	}

	public void setAid(long aid) {
		this.aid = aid;
	}

	public CrmWxHongbaoCheck getHongbao() {
		return hongbao;
	}

	public void setHongbao(CrmWxHongbaoCheck hongbao) {
		this.hongbao = hongbao;
	}

	public void setWxHongbaoMgr(IWxHongbaoMgr wxHongbaoMgr) {
		this.wxHongbaoMgr = wxHongbaoMgr;
	}


	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	public long getCkid() {
		return ckid;
	}

	public void setCkid(long ckid) {
		this.ckid = ckid;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getLightType() {
		return lightType;
	}

	public void setLightType(int lightType) {
		this.lightType = lightType;
	}
	
	public int getStatus()
	{
		return status;
	}

	
	public void setStatus(int status)
	{
		this.status = status;
	}

	
	public float getTotalshow()
	{
		return totalshow;
	}

	
	public void setTotalshow(float totalshow)
	{
		this.totalshow = totalshow;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}
