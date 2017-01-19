package com.huiyee.interact.xc.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import com.huiyee.esite.dao.IWeiXinDao;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.model.WxUser;
import com.huiyee.interact.xc.dto.XcDto;
import com.huiyee.interact.xc.mgr.IXCSendMgr;
import com.huiyee.interact.xc.mgr.IXcMgr;
import com.huiyee.interact.xc.model.LotteryConfig;
import com.huiyee.interact.xc.model.Xc;
import com.huiyee.interact.xc.model.XcRecord;
import com.huiyee.interact.xc.model.XcSendRecord;

public class XcService implements IXcService {
	private IXcMgr xcMgr;
	private IXCSendMgr sendMgr;
	private IWeiXinDao weiXinDao;

	private static boolean stop = true;
	private static Map<Long, XcDto> xcMap = new HashMap<Long, XcDto>();

	@Override
	public int start(long xcid) {
		Xc xc = xcMgr.getXcById(xcid);
		if (xc == null || xc.getLotteryconfig() == null) {
			return -1;
		}
		LotteryConfig lc = xc.getLotteryconfig();
		if (lc.getStarted() == 0 || lc.getStarted() == 2) {
			return lc.getStarted();
		}

		if (stop) {
			XcDto xcDto = new XcDto();
			xcDto.setLc(lc);
			String atype = lc.getAtype();// Y-邀请,P-评论,Q-签到,N-不需要
			if (!"N".equals(atype)) {
				List<Long> uids = xcMgr.getUids(xcid, atype);
				xcDto.setUids(uids);
			}
			XcService.xcMap.put(xcid, xcDto);
			stop = false;
		}
		return 1;
	}

	@Override
	public int process(long xcid, VisitUser vu, String ip, String device) {
		if (vu == null) {
			return -3;
		}
		long uid = vu.getUid();
		int utype = vu.getCd();

		XcDto xcDto = XcService.xcMap.get(xcid);
		if (xcDto == null || xcDto.getLc() == null) {
			return -1;
		}
		LotteryConfig lc = xcDto.getLc();
		if (lc.getStarted() == 0 || lc.getStarted() == 2) {
			return 0 - lc.getStarted();
		}
		if (!"N".equals(lc.getAtype())) {
			List<Long> uids = xcDto.getUids();
			if (!uids.contains(uid)) {
				return -5;
			}
		}

		StringBuffer sb = new StringBuffer();
		sb.append(xcid).append("-");
		sb.append(lc.getStartnum()).append("-");
		sb.append(uid).append("-");
		sb.append(utype);
		String key = sb.toString();

		Map<String, XcRecord> map = xcDto.getMap();
		XcRecord xcr = map.get(key);
		if (xcr == null) {
			xcr = new XcRecord();
			xcr.setXcid(xcid);
			xcr.setStartnum(lc.getStartnum());
			xcr.setUid(uid);
			xcr.setUtype(utype);
			xcr.setJoinnum(1);
			xcr.setTop("N");
			xcr.setCreatetime(new Date());
			xcr.setSource(vu.getSource());
			xcr.setPageid(vu.getPageid());
			xcr.setIp(ip);
			xcr.setTerminal(device);
			map.put(key, xcr);
		} else {
			xcr.setJoinnum(xcr.getJoinnum() + 1);
		}

		List<String> keys = xcDto.getKeys();
		String type = lc.getType();// J-参与抽奖,C-参与并且提高中奖率,P-排序抽奖
		if ("J".equals(type) || "P".equals(type)) {
			if (xcr.getJoinnum() == 1) {
				keys.add(key);
			}
		} else if ("C".equals(type)) {
			keys.add(key);
		}

		return xcr.getJoinnum();
	}

	@Override
	public List<XcRecord> getResult(long xcid) {
		List<XcRecord> result = new ArrayList<XcRecord>();

		XcDto xcDto = XcService.xcMap.get(xcid);
		if (xcDto == null) {
			return result;
		}
		LotteryConfig lc = xcDto.getLc();
		if (lc.getStarted() == 2) {
			Xc xc_re = xcMgr.getXcById(xcid);
			LotteryConfig lc_re = xc_re.getLotteryconfig();
			return xcMgr.getLastResult(xcid, lc_re.getStartnum());
		}
		Map<String, XcRecord> map = xcDto.getMap();
		List<String> keys = xcDto.getKeys();
		String type = lc.getType();// J-参与抽奖,C-参与并且提高中奖率,P-排序抽奖
		int num = lc.getNum();// 每次启动需要取出的人数
		String unique = lc.getUnique(); // Y-中奖唯一,N-可重复中间

		List<String> selected = new ArrayList<String>();
		if ("J".equals(type) || "C".equals(type)) {
			while (selected.size() < num && keys.size() > 0) {
				int index = (int) Math.round(Math.random() * (keys.size() - 1));
				String key = keys.get(index);
				keys.remove(key);

				if (!selected.contains(key)) {
					if ("Y".equals(unique)) {
						int ycount = xcMgr.getYRecord(key);
						if (ycount == 0) {
							selected.add(key);
						}
					} else {
						selected.add(key);
					}
				}
			}
		} else if ("P".equals(type)) {
			final Map<String, XcRecord> forSort = new HashMap<String, XcRecord>(map);
			Collections.sort(keys, new Comparator<String>() {
				public int compare(String key1, String key2) {
					int joinnum1 = forSort.get(key1).getJoinnum();
					int joinnum2 = forSort.get(key2).getJoinnum();
					return joinnum2 - joinnum1;
				}
			});

			while (selected.size() < num && keys.size() > 0) {
				String key = keys.get(0);
				keys.remove(key);

				if ("Y".equals(unique)) {
					int ycount = xcMgr.getYRecord(key);
					if (ycount == 0) {
						selected.add(key);
					}
				} else {
					selected.add(key);
				}
			}
		}

		for (String key : selected) {
			XcRecord xcr = map.get(key);
			if (xcr != null) {
				xcr.setTop("Y");
				result.add(xcr);
			}
		}

		return result;
	}

	@Override
	public List<WxUser> getWxUser(List<XcRecord> records) {
		List<WxUser> list = new ArrayList<WxUser>();

		for (XcRecord xcr : records) {
			if (xcr.getUtype() == 1) {// 1表示微信用户
				WxUser wxUser = weiXinDao.getWxUserById(xcr.getUid());
				if (wxUser != null) {
					XcSendRecord xsr = sendMgr.getSdRecord(xcr.getXcid(), xcr.getUid(), 1);
					if (xsr != null && StringUtils.isNotBlank(xsr.getUsername())) {
						wxUser.setNickname(xsr.getUsername());
					}

					wxUser.setPrivilege(xcr.getJoinnum() + "");
					list.add(wxUser);
				}
			}
		}
		return list;
	}

	@Override
	public void saveRecords(long xcid) {
		List<XcRecord> records = new ArrayList<XcRecord>();
		XcDto xcDto = XcService.xcMap.get(xcid);
		if (xcDto != null) {
			Map<String, XcRecord> map = xcDto.getMap();
			for (XcRecord xcr : map.values()) {
				records.add(xcr);
			}
		}
		xcMgr.saveRecords(records);
		// set started as 2
		xcMgr.updateLConfig(xcid);
	}

	@Override
	public void removeCache(long xcid) {
		LotteryConfig lc = new LotteryConfig();
		lc.setStarted(2);
		XcDto xcDto = new XcDto();
		xcDto.setLc(lc);
		XcService.xcMap.put(xcid, xcDto);
		stop = true;
	}

	public void setXcMgr(IXcMgr xcMgr) {
		this.xcMgr = xcMgr;
	}

	public void setSendMgr(IXCSendMgr sendMgr)
	{
		this.sendMgr = sendMgr;
	}

	public void setWeiXinDao(IWeiXinDao weiXinDao)
	{
		this.weiXinDao = weiXinDao;
	}

}
