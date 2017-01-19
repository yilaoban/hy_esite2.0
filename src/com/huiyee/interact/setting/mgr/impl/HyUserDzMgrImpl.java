package com.huiyee.interact.setting.mgr.impl;

import java.util.Date;
import java.util.List;

import com.huiyee.esite.mgr.imp.AbstractMgr;
import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.DateUtil;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.esite.util.JsonStringUtil;
import com.huiyee.interact.setting.dao.IHyUserDzDao;
import com.huiyee.interact.setting.mgr.IHyUserDzMgr;
import com.huiyee.interact.setting.model.HyUserDz;
import com.huiyee.interact.template.model.WxTemplate;
import com.huiyee.interact.template.model.WxTemplateJob;
import com.huiyee.weixin.model.template.GRXXTZ;

public class HyUserDzMgrImpl extends AbstractMgr implements IHyUserDzMgr {

	private IHyUserDzDao hyUserDzDao;

	public void setHyUserDzDao(IHyUserDzDao hyUserDzDao) {
		this.hyUserDzDao = hyUserDzDao;
	}

	@Override
	public int findDzCount(long owner, String type) {
		return hyUserDzDao.findDzCount(owner, type);
	}

	@Override
	public List<HyUserDz> findDzList(long owner, String type, int start, int rows) {
		return hyUserDzDao.findDzList(owner, type, start, rows);
	}

	@Override
	public int findDzByWxuid(long owner, String type, long wxuid) {
		return hyUserDzDao.findDzByWxuid(owner, type, wxuid);
	}

	@Override
	public HyUserDz findDzByHyuid(long owner, long hyuid) {
		return hyUserDzDao.findDzByHyuid(owner, hyuid);
	}

	@Override
	public int findDzByHyuid(long owner, String type, long hyuid) {
		return hyUserDzDao.findDzByHyuid(owner, type, hyuid);
	}

	@Override
	public int addDz(HyUserDz hud, VisitUser vu) {
		int res = hyUserDzDao.addDz(hud);
		if (res > 0) {
			long owner = hud.getOwner();
			List<WxTemplate> wts = this.findWxTemplate(owner, "SQD", 0);
			for (WxTemplate wt : wts) {
				String nickname = vu.getWxUser().getNickname();
				GRXXTZ dd = (GRXXTZ) JsonStringUtil.String2Obj(wt.getData(), GRXXTZ.class);
				dd.setFirst(dd.getFirst().replace("${nickname}", nickname));
				dd.setKeyword1(dd.getKeyword1().replace("${nickname}", nickname));
				dd.setKeyword2(DateUtil.getDateTimeString(new Date()));
				dd.setKeyword3(dd.getKeyword3().replace("${nickname}", nickname));
				dd.setRemark(dd.getRemark().replace("${nickname}", nickname));

				String url = HyConfig.getPageyuming() + "/" + vu.getOname() + "/user/wxshowp/uidzqx/" + hud.getHyuid() + ".html";
				List<HyUserDz> dzs = hyUserDzDao.findDzList(owner, "t7", 0, 100);
				for (HyUserDz dz : dzs) {
					WxTemplateJob wj = new WxTemplateJob();
					wj.setMpid(wt.getMpid());
					wj.setType(wt.getType());
					wj.setEntityid(wt.getEntityid());
					wj.setTouser(dz.getOpenid());
					wj.setRemark(wt.getRemark());
					wj.setTemplate_id(wt.getTemplate_id());
					wj.setData(dd.toData());
					wj.setUrl(url);
					this.addTmplMsg(wj);
				}
			}
		}
		return res;
	}

	@Override
	public int updateDz(long id, String type, int type_val) {
		return hyUserDzDao.updateDz(id, type, type_val);
	}

	@Override
	public int delDz(long id) {
		return hyUserDzDao.delDz(id);
	}

	@Override
	public HyUser findUser(long id) {
		return hyUserDzDao.findUser(id);
	}

	@Override
	public int updateUser(HyUser user) {
		return hyUserDzDao.updateUser(user);
	}

}
