
package com.huiyee.esite.mgr.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huiyee.esite.dao.IDynamicActionRecordDao;
import com.huiyee.esite.dao.IHdUserDataDao;
import com.huiyee.esite.dao.ILotteryUserChanceRecordDao;
import com.huiyee.esite.dao.IOwnerBalanceDao;
import com.huiyee.esite.dao.IOwnerBalanceRecordDao;
import com.huiyee.esite.dao.IOwnerBalanceSetDao;
import com.huiyee.esite.dao.IOwnerLotteryUserDao;
import com.huiyee.esite.dao.IPageDao;
import com.huiyee.esite.model.OwnerBalanceSet;
import com.huiyee.esite.model.SuperHdModel;
import com.huiyee.esite.ws.WeiBoService;
import com.huiyee.interact.setting.dao.IHyUserDzDao;
import com.huiyee.interact.setting.model.HyUserDz;
import com.huiyee.interact.template.dao.IWxTemplateDao;
import com.huiyee.interact.template.dao.IWxTemplateJobDao;
import com.huiyee.interact.template.model.WxTemplateJob;
import com.huiyee.interact.template.model.WxTemplate;

public abstract class AbstractMgr {

	protected IOwnerBalanceDao ownerBalanceDao;
	protected IOwnerBalanceRecordDao ownerBalanceRecordDao;
	protected IOwnerBalanceSetDao ownerBalanceSetDao;
	protected IOwnerLotteryUserDao ownerLotteryUserDao;
	protected IPageDao pageDao;
	protected ILotteryUserChanceRecordDao userChanceRecordDao;
	protected IHdUserDataDao hdUserDataDao;
	static Map<Long, OwnerBalanceSet> obss = new HashMap<Long, OwnerBalanceSet>();

	protected WeiBoService longWS;// 3600000
	protected WeiBoService shortWS;// 60000
	protected WeiBoService mustShortWS;// 缓存时间2000
	protected WeiBoService justWS;// 缓存时间0

	protected IWxTemplateJobDao wxTemplateJobDao;
	protected IWxTemplateDao wxTemplateDao;
	protected IDynamicActionRecordDao dynamicActionRecordDao;//搜索ip区域
	protected IHyUserDzDao hyUserDzDao;
	
	public void setHyUserDzDao(IHyUserDzDao hyUserDzDao)
	{
		this.hyUserDzDao = hyUserDzDao;
	}

	public void setDynamicActionRecordDao(IDynamicActionRecordDao dynamicActionRecordDao)
	{
		this.dynamicActionRecordDao = dynamicActionRecordDao;
	}

	/**
	 * 查看用户的储值账户余额
	 * @param uid
	 * @return
	 */
	public int findRmb(long uid)
	{
		return ownerBalanceDao.findRmbByUser(uid);
	}
	
	/**
	 * 查看用户的积分
	 * 
	 * @param uid
	 * @return
	 */
	public int findJFen(long uid) 
	{
		return ownerBalanceDao.findJFByUser(uid);
	}
	
	/**
	 * 搜索ip区域
	 * @param ip
	 * @return
	 */
	public String findAreaByIp(String ip)
	{
		return dynamicActionRecordDao.findAreaByIp(ip);
	}
	
	public List<HyUserDz> findDz(long owner,String type)
	{
		return hyUserDzDao.findDzList(owner, type, 0,100);
	}
	
	public boolean isDz(long owner, String type, long hyuid)
	{
		int rs=hyUserDzDao.findDzByHyuid(owner, type, hyuid);
		if(rs>0)
		{
			return true;
		}
		return false;
	}
	public OwnerBalanceSet findOBS(long owner) {
		if (owner == 0) {
			return null;
		}
		OwnerBalanceSet obs = obss.get(owner);
		long ctime = System.currentTimeMillis();
		if (obs == null || obs.getCachetime() < ctime - 3600 * 1000) {
			obs = ownerBalanceSetDao.findRuleById(owner);
			if (obs != null) {
				obs.setCachetime(ctime);
				obss.put(owner, obs);
			}
		}
		return obs;
	}

	/**
	 * 统一API,用来处理所有互动跟抽奖的关系
	 * 
	 * @param hd
	 *            互动的model
	 * @param uid
	 *            user的id可以是微博可以是微信
	 * @paramu type 区分微博微信,0-微博,1-微信
	 * @param cid
	 *            如果是微博环境,当时的访问的官微的id
	 */
	public void updateInteractAction(SuperHdModel hd, long uid, int utype) {

		hdUserDataDao.addUserData(uid, utype, hd.getId(), hd.getFeatureid());
		// 获得抽奖机会
		if (hd.getLotterychance() > 0 && hd.getIslottery().equalsIgnoreCase("Y") && hd.getLotteryid() > 0) {
			int chancetotal = this.findUserChanceTotal(hd.getId(), hd.getFeatureid(), uid, utype);// 已经增加的机会
			int maxlotterychance = hd.getMaxlotterychance();
			int addnum = 0;
			if (maxlotterychance - chancetotal > hd.getLotterychance()) {
				addnum = hd.getLotterychance();
			} else {
				addnum = maxlotterychance - chancetotal;
			}
			if (addnum > 0) {
				this.addLotteryChanceRecord(hd.getId(), hd.getFeatureid(), uid, utype, addnum, hd.getLotteryid());
				this.addLotteryUserTotal(uid, utype, hd.getLotteryid(), addnum);
			}
		}
	}

	/**
	 * 评论和分享的主动类型的积分
	 * 全局设置的积分，通过此方法直接增加减少积分
	 * @param hyuid
	 * @param stype
	 * @param owner
	 */
	public OwnerBalanceSet updateHyUserBalance(long hyuid, String stype, long owner, long enid) {
		OwnerBalanceSet obs = findOBS(owner);
		if (obs == null) {
			return null;
		}
		if (stype.equals("TPC")) {
			updateBalance(hyuid, obs.getTopicnum(), "发表话题", "BBS", stype, enid);
		} else if (stype.equals("CMT")) {
			updateBalance(hyuid, obs.getComnum(), "发表评论", "BBS", stype, enid);
		} else if (stype.equals("TOP")) {
			updateBalance(hyuid, obs.getTopnum(), "话题被置顶", "BBS", stype, enid);
		} else if (stype.equals("UPP")) {
			updateBalance(hyuid, obs.getUpnum(), "论坛顶", "BBS", stype, enid);
		} else if (stype.equals("DOW")) {
			updateBalance(hyuid, obs.getDownum(), "论坛踩", "BBS", stype, enid);
		}else if (stype.equals("OFC")) {
			updateBalance(hyuid, obs.getOcnum(), "线下签到", "OFC", stype, enid);
		} else if (stype.equals("YYU")) {
			updateBalance(hyuid, obs.getYynum(), "微预约", "YYU", stype, enid);
		}else if (stype.equals("FWP")) {
			updateBalance(hyuid, obs.getYypjnum(), "服务评价", "FWP", stype, enid);
		}
		return obs;
	}

	/**
	 * 积分操作,客户操作都会有积分加减
	 * 
	 * @param hyuid
	 *            用户hyuser 的id
	 * @param balance
	 *            积分 正数为加积分 负数为减积分
	 * @param desc
	 *            描述
	 * @param type
	 *            大的分类:SHR-分享类，BBS-社区类，HUD-互动类，NEW-赠送类，CHB-合伙人，CHR-合伙人RMB, JFS-积分商城,JFD-积分抵扣,OFC-线下签到,YYU-预约,TAK-任务
	 *            RMB-储值账户
	 *            项目：
	 *            彩蛋：CDD-彩蛋项目
	 * @param stype
	 *            互动类：APT-申请；LOT-抽奖；RSH-调研；SPR-传散；VOT-投票
	 *            分享类：SRR-主动分享；SSH-分享带来的分享；SGZ-分享带来的关注；SCL-分享带来的点击
	 *            社区类：TPC-发表话题；CMT-发表评论；TOP-话题置顶；UPP-顶；DOW-踩
	 *            赠送类：NGZ-新关注；CHK-签到;CHZ-充值赠送
	 *            合伙人：SHA-转发；CLK-点击；GZH-关注；HUD-互动；WBU-外部 
	 *            积分商城：DHS-兑换商品，FHS-订单过期返还
	 *            积分抵扣：DKP-抵扣现金
	 *            微预约：YYU-预约成功获得积分；FWP-服务评价
	 *            线下签到：OFC-线下签到获得积分；XFJ-消费积分
	 *            储值账户：RCG-充值；XXX-线下消费；XSX-线上消费;SMQ-扫描枪消费
	 *            项目：
	 *            彩蛋：CDD-金币兑换  CDP-手机绑定
	 */
	public void updateBalance(long hyuid, int balance, String desc, String type, String stype, long enid) {
		if (hyuid > 0) {
			if (balance > 0) 
			{
				ownerBalanceDao.addMoreBalance(hyuid, balance);
				ownerBalanceRecordDao.addRecord(hyuid, balance, desc, type, stype, enid);
			} else if (balance < 0) 
			{
				ownerBalanceDao.addLessBalance(hyuid, -balance);
				ownerBalanceRecordDao.addRecord(hyuid, balance, desc, type, stype, enid);
			}
		}
	}
	public long updateRmbBalance(long hyuid, int balance, String desc, String stype, long enid) {
		long rs=0;
		if (hyuid > 0) {
			if (balance > 0) 
			{
				ownerBalanceDao.addMoreRmbBalance(hyuid, balance);
			rs=	ownerBalanceRecordDao.addRmbRecord(hyuid, balance, desc, "RMB", stype, enid);
			} else if (balance < 0) 
			{
				ownerBalanceDao.addLessRmbBalance(hyuid, -balance);
			rs=	ownerBalanceRecordDao.addRmbRecord(hyuid, balance, desc, "RMB", stype, enid);
			}
		}
		return rs;
	}


	/**
	 * 加抽奖机会
	 * 
	 * @param entityid
	 *            用户wbuid
	 * @param lid
	 *            抽奖id
	 * @param num
	 *            机会
	 */
	public void addLotteryUserTotal(long entityid, int type, long lid, int num) {
		ownerLotteryUserDao.addLotteryUserTotal(entityid, type, lid, num);
	}

	/**
	 * 
	 * @param entityid
	 *            申请或调研或投票或口碑的id
	 * @param featureid
	 *            118-申请 123-投票 124-调研 134-口碑
	 * @param userid
	 *            wbuid或wxuid
	 * @param utype
	 *            0-sina 1-weixin
	 * @return 获得的抽奖次数
	 */
	public int findUserChanceTotal(long entityid, long featureid, long userid, int utype) {
		return userChanceRecordDao.findUserChanceTotal(entityid, featureid, userid, utype);
	}

	/**
	 * 
	 * @param entityid
	 *            申请或调研或投票或口碑的id
	 * @param featureid
	 *            118-申请 123-投票 124-调研 134-口碑
	 * @param userid
	 *            wbuid或wxuid
	 * @param utype
	 *            0-sina 1-weixin
	 * @param num
	 *            添加抽奖次数
	 * @return
	 */
	public void addLotteryChanceRecord(long entityid, long featureid, long userid, int utype, int num, long lid) {
		if (num > 0) {
			userChanceRecordDao.addLotteryChanceRecord(entityid, featureid, userid, utype, num, lid);
		}
	}

	/**
	 * 
	 * @param uid
	 * @param ownerwbuid
	 * @param utype
	 * @param preused
	 *            可以是负数,负数是解冻 冻结的积分
	 */
	public void updatePreUsedByUser(long uid, long ownerwbuid, int utype, int preused, String desc) {
		// ownerBalanceDao.updatePreUsedByUser(uid, ownerwbuid, utype, preused);
		// ownerBalanceRecordDao.addRecord(ownerwbuid, uid, -preused, desc,
		// utype);
	}

	/**
	 * 发送模板消息（加入发送任务）
	 * 
	 * @param job
	 * @return
	 */
	public int addTmplMsg(WxTemplateJob job) {
		return wxTemplateJobDao.addTemplateJob(job);
	}

	public List<WxTemplate> findWxTemplate(long owner, String type, long entityid) {
		if(entityid>0)
		return wxTemplateDao.getTemplateList(owner, type, entityid);
		else
		return wxTemplateDao.getTemplateList(owner, type);	
	}

	public String findTokenByPageidAndWbuid(long pageid, long wbuid) {
		return pageDao.findTokenByPageidAndWbuid(pageid, wbuid);
	}

	public void setOwnerBalanceSetDao(IOwnerBalanceSetDao ownerBalanceSetDao) {
		this.ownerBalanceSetDao = ownerBalanceSetDao;
	}

	public void setOwnerLotteryUserDao(IOwnerLotteryUserDao ownerLotteryUserDao) {
		this.ownerLotteryUserDao = ownerLotteryUserDao;
	}

	public void setPageDao(IPageDao pageDao) {
		this.pageDao = pageDao;
	}

	public WeiBoService getLongWS() {
		return longWS;
	}

	public void setLongWS(WeiBoService longWS) {
		this.longWS = longWS;
	}

	public WeiBoService getShortWS() {
		return shortWS;
	}

	public void setShortWS(WeiBoService shortWS) {
		this.shortWS = shortWS;
	}

	public WeiBoService getMustShortWS() {
		return mustShortWS;
	}

	public void setMustShortWS(WeiBoService mustShortWS) {
		this.mustShortWS = mustShortWS;
	}

	public WeiBoService getJustWS() {
		return justWS;
	}

	public void setJustWS(WeiBoService justWS) {
		this.justWS = justWS;
	}

	public void setUserChanceRecordDao(ILotteryUserChanceRecordDao userChanceRecordDao) {
		this.userChanceRecordDao = userChanceRecordDao;
	}

	public void setOwnerBalanceDao(IOwnerBalanceDao ownerBalanceDao) {
		this.ownerBalanceDao = ownerBalanceDao;
	}

	public void setOwnerBalanceRecordDao(IOwnerBalanceRecordDao ownerBalanceRecordDao) {
		this.ownerBalanceRecordDao = ownerBalanceRecordDao;
	}

	public void setHdUserDataDao(IHdUserDataDao hdUserDataDao) {
		this.hdUserDataDao = hdUserDataDao;
	}

	public void setWxTemplateJobDao(IWxTemplateJobDao wxTemplateJobDao) {
		this.wxTemplateJobDao = wxTemplateJobDao;
	}

	public void setWxTemplateDao(IWxTemplateDao wxTemplateDao) {
		this.wxTemplateDao = wxTemplateDao;
	}

}
