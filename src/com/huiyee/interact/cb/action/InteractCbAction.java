
package com.huiyee.interact.cb.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.constants.IPrivilegeConstants;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.service.Permission;
import com.huiyee.interact.appointment.mgr.IInteractAptManager;
import com.huiyee.interact.appointment.model.AppointmentMappingModel;
import com.huiyee.interact.appointment.model.AppointmentModel;
import com.huiyee.interact.appointment.model.OrderMappingModel;
import com.huiyee.interact.cb.mgr.IInteractCbMgr;
import com.huiyee.interact.cb.model.InteractCb;

/**
 * 
 * @author ldw
 * 
 */
public class InteractCbAction extends InteractModelAction
{

	private long cbid;
	private IInteractCbMgr interactCbMgr;
	private InteractCb cb;
	private AppointmentModel apt;
	private AppointmentMappingModel mappingmodel;
	private IInteractAptManager interactAptManager;
	private int needRef;
	private String refUrl;
	
	private List<OrderMappingModel> subSys;// 固定字段
	private List<OrderMappingModel> subUdf;// 自定义字段

	public void setInteractAptManager(IInteractAptManager interactAptManager)
	{
		this.interactAptManager = interactAptManager;
	}

	public AppointmentModel getApt()
	{
		return apt;
	}

	public void setApt(AppointmentModel apt)
	{
		this.apt = apt;
	}

	public AppointmentMappingModel getMappingmodel()
	{
		return mappingmodel;
	}

	public void setMappingmodel(AppointmentMappingModel mappingmodel)
	{
		this.mappingmodel = mappingmodel;
	}

	public void setCbid(long cbid)
	{
		this.cbid = cbid;
	}

	public long getCbid()
	{
		return cbid;
	}

	public void setInteractCbMgr(IInteractCbMgr interactCbMgr)
	{
		this.interactCbMgr = interactCbMgr;
	}

	/**
	 * 传播的申请配置
	 * 
	 * @return
	 * @throws Exception
	 */
	@Permission(module=IPrivilegeConstants.MODULE_APP_合伙人,privilege=IPrivilegeConstants.PERMISSION_R)
	public String editApt() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		cbid = account.getOwner().getId();
		cb = interactCbMgr.findCbbyid(account, cbid);
		if(needRef==1){
			//从站点列表点配置跳转过来的在编辑保存后跳会站点列表
			HttpServletRequest httpRequest = ServletActionContext.getRequest();
			refUrl=httpRequest.getHeader("Referer");
		}
		return SUCCESS;
	}

	/**
	 * 活动中心
	 * 
	 * @return
	 * @throws Exception
	 */
	public String cbactive() throws Exception
	{
		return SUCCESS;
	}

	public InteractCb getCb()
	{
		return cb;
	}

	public void setCb(InteractCb cb)
	{
		this.cb = cb;
	}

	public int getLightType()
	{
		return 4;
	}

	
	public int getNeedRef()
	{
		return needRef;
	}

	
	public void setNeedRef(int needRef)
	{
		this.needRef = needRef;
	}

	public String getRefUrl()
	{
		return refUrl;
	}

	public void setRefUrl(String refUrl)
	{
		this.refUrl = refUrl;
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
}
