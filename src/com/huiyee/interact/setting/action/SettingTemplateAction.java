package com.huiyee.interact.setting.action;

import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.constants.IPrivilegeConstants;
import com.huiyee.esite.service.Permission;
import com.huiyee.interact.template.mgr.IWxTemplateMgr;
import com.huiyee.interact.template.model.WxTemplate;

public class SettingTemplateAction extends AbstractAction {
	private static final long serialVersionUID = 2182316567995851611L;

	private IWxTemplateMgr wxTemplateMgr;

	private WxTemplate wt_cu;
	private WxTemplate wt_xu;
	private WxTemplate wt_cd;
	private WxTemplate wt_xd;
	private WxTemplate wt_sq;

	@Override
	public int getLightType() {
		return 6;
	}

	@Permission(module = IPrivilegeConstants.MODULE_APP_微积分, privilege = IPrivilegeConstants.PERMISSION_R)
	public String execute() throws Exception {
		// 会员充值成功通知;
		long owner = this.getOwner();
		String type = "CZU";
		long store_id = 5;
		wt_cu = wxTemplateMgr.getTemplate(owner, type);
		if (wt_cu == null) {
			wt_cu = new WxTemplate();
			wt_cu.setType(type);
			wt_cu.setStore_id(store_id);
		}
		// 会员消费提醒
		type = "XFU";
		store_id = 6;
		wt_xu = wxTemplateMgr.getTemplate(owner, type);
		if (wt_xu == null) {
			wt_xu = new WxTemplate();
			wt_xu.setType(type);
			wt_xu.setStore_id(store_id);
		}
		// 充值通知商家
		type = "CZD";
		store_id = 5;
		wt_cd = wxTemplateMgr.getTemplate(owner, type);
		if (wt_cd == null) {
			wt_cd = new WxTemplate();
			wt_cd.setType(type);
			wt_cd.setStore_id(store_id);
		}
		// 消费通知商家
		type = "XFD";
		store_id = 6;
		wt_xd = wxTemplateMgr.getTemplate(owner, type);
		if (wt_xd == null) {
			wt_xd = new WxTemplate();
			wt_xd.setType(type);
			wt_xd.setStore_id(store_id);
		}
		// 消费通知商家
		type = "SQD";
		store_id = 3;
		wt_sq = wxTemplateMgr.getTemplate(owner, type);
		if (wt_sq == null) {
			wt_sq = new WxTemplate();
			wt_sq.setType(type);
			wt_sq.setStore_id(store_id);
		}

		return SUCCESS;

	}

	public void setWxTemplateMgr(IWxTemplateMgr wxTemplateMgr) {
		this.wxTemplateMgr = wxTemplateMgr;
	}

	public WxTemplate getWt_cu() {
		return wt_cu;
	}

	public void setWt_cu(WxTemplate wt_cu) {
		this.wt_cu = wt_cu;
	}

	public WxTemplate getWt_xu() {
		return wt_xu;
	}

	public void setWt_xu(WxTemplate wt_xu) {
		this.wt_xu = wt_xu;
	}

	public WxTemplate getWt_cd() {
		return wt_cd;
	}

	public void setWt_cd(WxTemplate wt_cd) {
		this.wt_cd = wt_cd;
	}

	public WxTemplate getWt_xd() {
		return wt_xd;
	}

	public void setWt_xd(WxTemplate wt_xd) {
		this.wt_xd = wt_xd;
	}

	public WxTemplate getWt_sq() {
		return wt_sq;
	}

	public void setWt_sq(WxTemplate wt_sq) {
		this.wt_sq = wt_sq;
	}

}
