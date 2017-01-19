
package com.huiyee.interact.lottery.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.huiyee.esite.mgr.IGzEventMgr;
import com.huiyee.esite.mgr.ITemplateManager;
import com.huiyee.esite.model.BlockContext;
import com.huiyee.esite.model.CrmWxHongbaoCheck;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.esite.model.PageCard;
import com.huiyee.esite.model.TemplateBlock;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.model.WxGzEvent;
import com.huiyee.esite.model.WxUser;
import com.huiyee.interact.lottery.dto.DrawLp;
import com.huiyee.interact.lottery.dto.LotteryRs;
import com.huiyee.interact.lottery.dto.Usertype;
import com.huiyee.interact.lottery.mgr.ILotteryAsignUserMgr;
import com.huiyee.interact.lottery.mgr.ILotteryPrizeCodeMgr;
import com.huiyee.interact.lottery.mgr.ILotteryPrizeMgr;
import com.huiyee.interact.lottery.mgr.ILotteryUserDateMgr;
import com.huiyee.interact.lottery.mgr.ILotteryUserMgr;
import com.huiyee.interact.lottery.mgr.ILotteryUserRecordMgr;
import com.huiyee.interact.lottery.mgr.IWxHongbaoMgr;
import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.lottery.model.LotteryLose;
import com.huiyee.interact.lottery.model.LotteryPrize;
import com.huiyee.interact.lottery.model.LotteryPrizeCode;
import com.huiyee.interact.lottery.model.LotteryUser;
import com.huiyee.interact.lottery.model.LotteryUserDate;
import com.huiyee.interact.lottery.util.JsonStringUtil;
import com.huiyee.weixin.mgr.IWxBuyProductMgr;

public class LotteryDrawService implements ILotteryDrawService
{

	private ILotteryAsignUserMgr lotteryAsignUserMgr;
	private IWxHongbaoMgr wxHongbaoMgr;
	private ILotteryPrizeCodeMgr lotteryPrizeCodeMgr;
	private ILotteryPrizeMgr lotteryPrizeMgr;
	private ILotteryUserDateMgr lotteryUserDateMgr;
	private ILotteryUserMgr lotteryUserMgr;
	private ILotteryUserRecordMgr lotteryUserRecordMgr;
	private ITemplateManager templateManager;
	private IWxBuyProductMgr wxBuyProductMgr;
	private IGzEventMgr gzEventMgr;
	private static Map<String, String> uids = new HashMap<String, String>();
	private static Map<Long, String> lids = new HashMap<Long, String>();

	public void setWxBuyProductMgr(IWxBuyProductMgr wxBuyProductMgr)
	{
		this.wxBuyProductMgr = wxBuyProductMgr;
	}

	public void setGzEventMgr(IGzEventMgr gzEventMgr)
	{
		this.gzEventMgr = gzEventMgr;
	}

	public void setLotteryAsignUserMgr(ILotteryAsignUserMgr lotteryAsignUserMgr)
	{
		this.lotteryAsignUserMgr = lotteryAsignUserMgr;
	}

	public void setTemplateManager(ITemplateManager templateManager)
	{
		this.templateManager = templateManager;
	}

	public void setWxHongbaoMgr(IWxHongbaoMgr wxHongbaoMgr)
	{
		this.wxHongbaoMgr = wxHongbaoMgr;
	}

	public void setLotteryPrizeCodeMgr(ILotteryPrizeCodeMgr lotteryPrizeCodeMgr)
	{
		this.lotteryPrizeCodeMgr = lotteryPrizeCodeMgr;
	}

	public void setLotteryPrizeMgr(ILotteryPrizeMgr lotteryPrizeMgr)
	{
		this.lotteryPrizeMgr = lotteryPrizeMgr;
	}

	public void setLotteryUserDateMgr(ILotteryUserDateMgr lotteryUserDateMgr)
	{
		this.lotteryUserDateMgr = lotteryUserDateMgr;
	}

	public void setLotteryUserMgr(ILotteryUserMgr lotteryUserMgr)
	{
		this.lotteryUserMgr = lotteryUserMgr;
	}

	public void setLotteryUserRecordMgr(ILotteryUserRecordMgr lotteryUserRecordMgr)
	{
		this.lotteryUserRecordMgr = lotteryUserRecordMgr;
	}

	public static String getUids(String uid)
	{
		String str = uids.get(uid);
		if (str == null)
		{
			str = new String("");
			uids.put(uid, str);
		}
		return str;

	}

	public static String getLids(long lid)
	{
		String str = lids.get(lid);
		if (str == null)
		{
			str = new String("");
			lids.put(lid, str);
		}
		return str;

	}

	private int getStatus(LotteryPrize lp)
	{
		if (lp.getType().equals("J"))
		{
			return 1;
		} else if (lp.getType().equals("C"))
		{
			return 2;
		} else if (lp.getType().equals("S"))
		{
			return 3;
		} else if (lp.getType().equals("H"))
		{
			return 4;
		} else if (lp.getType().equals("M"))
		{
			return 5;
		} else if (lp.getType().equals("Z"))
		{
			return 6;
		} else
		{
			return 7;
		}
	}

	private LotteryRs drawNlottery(Lottery lo, VisitUser vu, LotteryLose ll)// 抽奖非默认奖品
	{
		LotteryRs rs = new LotteryRs();
		List<LotteryPrize> lps = lotteryPrizeMgr.findLotteryNPrize(lo.getId());
		if (lps != null && lps.size() > 0)
		{
			int offset = 0;
			List<DrawLp> dps = new ArrayList<DrawLp>();
			for (LotteryPrize lp : lps)
			{
				DrawLp dp = new DrawLp();
				dp.setMin(offset);
				offset = offset + (lp.getTotal() - lp.getUsed());
				dp.setMax(offset);
				dp.setLp(lp);
				dps.add(dp);
			}
			LotteryPrize lp = null;
			int rnmn = (int) (Math.random() * offset);
			for (DrawLp dp : dps)
			{
				if (rnmn >= dp.getMin() && rnmn < dp.getMax())
				{
					lp = dp.getLp();
					
				}
			}
			rs.setHydesc("恭喜中奖");
			rs.setStatus(getStatus(lp));
			rs.setLotteryPrize(lp);
			if (lo.getDrawnum() > 0 && ll != null)
			{
				lotteryPrizeMgr.updateWinLose(ll.getId());
			}
			if (lp.getType().equals("C"))
			{
				// int rnmnc = (int) (Math.random() * (lp.getTotal() -
				// lp.getUsed()));//取消随机去除券号
				LotteryPrizeCode lpc = lotteryPrizeCodeMgr.findOneCode(lp.getId(), 0);
				if (lpc != null)
				{
					rs.setLotteryPrizeCode(lpc);
					lotteryPrizeCodeMgr.updateOneCode(lpc.getId());
				} else
				{
					rs = drawYlottery(lo, vu, ll);
				}
			} else if (lp.getType().equals("H") || lp.getType().equals("M"))
			{
				// 红包条件不够也是抽不了奖品的。
				// select hongbao_check where enid=? and type=1
				// ,状态是审核通过，total>used可以抽中，else 去抽默认奖品
				CrmWxHongbaoCheck ck = wxHongbaoMgr.findWxHongbaoCheckByLid(lo.getId());
				if (ck != null && ck.getStatus() == 1 && ck.getTotal() > ck.getUsed() && vu.getWxUser() != null && vu.getWxUser().getOpenid() != null
						&& vu.getWxUser().getMpid() == ck.getMpid())
				{
					// 抽中红包
					System.out.println(lp.getType() + "抽中紅包！");
					float a = lp.getHprice();
					float b = a / 100;
					if (lp.getHbgz() == 1 && vu.getWxUser().getSubscribe() == 0)
					{
						rs.setHydesc("恭喜您抽中了" + b + "元红包，请关注'" + vu.getWxUser().getAppnickname() + "'直接领取红包！");
					} else
					{
						rs.setHydesc("恭喜您抽中了" + b + "元红包，请在'" + vu.getWxUser().getAppnickname() + "'公众号上输入“" + lp.getHbkey() + "”口令领取红包！");
					}
				} else
				{
					System.out.println(lp.getType() + "紅包条件不满足");
					rs = drawYlottery(lo, vu, ll);
				}
			}
		} else
		{
			rs = drawYlottery(lo, vu, ll);
		}

		return rs;
	}

	private LotteryRs drawYlottery(Lottery lo, VisitUser vu, LotteryLose ll)// 抽奖默认奖品,默认奖品中奖率都是一样的
	{
		LotteryRs rs = new LotteryRs();
		List<LotteryPrize> lps = lotteryPrizeMgr.findLotteryYPrize(lo.getId());
		if (lps != null && lps.size() > 0)
		{
			int rnmn = (int) (Math.random() * lps.size());
			LotteryPrize lp = lps.get(rnmn);
			rs.setHydesc("抽中默认奖品");
			rs.setStatus(getStatus(lp));
			rs.setLotteryPrize(lp);
			if (lp.getType().equals("C"))
			{
				// int rnmnc = (int) (Math.random() * (lp.getTotal() -
				// lp.getUsed()));
				LotteryPrizeCode lpc = lotteryPrizeCodeMgr.findOneCode(lp.getId(), 0);
				if (lpc != null)
				{
					rs.setLotteryPrizeCode(lpc);
					lotteryPrizeCodeMgr.updateOneCode(lpc.getId());
				} else
				{
					rs.setStatus(0);
					rs.setHydesc("未中奖");
				}
			} else if (lp.getType().equals("H") || lp.getType().equals("M"))
			{
				// 红包条件不够也是抽不了奖品的。
				// select hongbao_check where enid=? and type=1
				// ,状态是审核通过，total>used可以抽中，else 去抽不中
				CrmWxHongbaoCheck ck = wxHongbaoMgr.findWxHongbaoCheckByLid(lo.getId());
				if (ck != null && ck.getStatus() == 1 && ck.getTotal() > ck.getUsed() && vu.getWxUser() != null && vu.getWxUser().getOpenid() != null
						&& vu.getWxUser().getMpid() == ck.getMpid())
				{
					// 安慰奖抽中红包
					System.out.println(lp.getType() + "安慰奖抽中紅包！");
					float a = lp.getHprice();
					float b = a / 100;
					if (lp.getHbgz() == 1 && vu.getWxUser().getSubscribe() == 0)
					{
						rs.setHydesc("恭喜您抽中了" + b + "元红包，请关注'" + vu.getWxUser().getAppnickname() + "'直接领取红包！或者在'" + vu.getWxUser().getAppnickname() + "'公众号上输入“" + lp.getHbkey()
								+ "”口令领取红包！");
					} else
					{
						rs.setHydesc("恭喜您抽中了" + b + "元红包，请在'" + vu.getWxUser().getAppnickname() + "'公众号上输入“" + lp.getHbkey() + "”口令领取红包！");
					}
				} else
				{
					System.out.println(lp.getType() + "安慰奖紅包条件不满足！");
					rs.setStatus(0);
					rs.setHydesc("未中奖");
				}
			}
		} else
		{
			rs.setStatus(0);
			rs.setHydesc("未中奖");
		}
		return rs;
	}

	@Override
	public LotteryRs drawOneLottery(VisitUser vu, Lottery lo, long pageid, String ip, String terminal, String source, long relationid, String starturl, boolean isdraw)
	{
		LotteryRs rs = new LotteryRs();
		long sid = 0;
		String url = null;
		String furl = null;
		int num = 0;
		int tnum = 0;
		int znum = 0;
		if (relationid > 0)
		{
			TemplateBlock tb = templateManager.findTemplateBlockByRelationid(relationid);
			if (tb != null)
			{
				PageBlockRelation r = templateManager.findPageBlockRelationByRelationid(relationid);
				String jtype = "N";
				if (r != null)
				{
					String json = r.getJson();
					JSONObject jo = JSONObject.fromObject(json);
					try
					{
						jo = jo.getJSONObject("obj");
						jo = jo.getJSONObject("redirect");
						jtype = jo.getString("type");
						url = jo.getString("url");
						furl = jo.getString("furl");
					} catch (Exception e)
					{
						jtype = "N";
					}
				}
				if ("N".equals(jtype))
				{ // 默认跳转
					BlockContext bc = templateManager.findBlockContextid(tb.getId(), "Y");
					if (bc != null)
					{
						Page page = templateManager.findPageByRelationidAndContextid(relationid, bc.getId());
						if (page != null)
						{
							List<PageCard> cardList = templateManager.findTemplateCardByPageid(page.getId());
							if (cardList != null && cardList.size() > 0)
							{ // 有子页面且页面上有卡片则需要跳转
								sid = page.getId();
								rs.setId(sid);
							}
						}
					}
				}
				rs.setUrl(url);
				rs.setFurl(furl);
			}
		}
		long uid = vu.getUid();
		int type = vu.getCd();
		int xhjf = 0;// 抽奖消耗的积分
		long hyuid = 0;
		if (vu.getHyUser() != null)
		{
			hyuid = vu.getHyUser().getId();
		}
		String nickname = "";
		if (vu.getWxUser() != null)
		{
			nickname = vu.getWxUser().getNickname();
		}
		synchronized (getLids(lo.getId()))
		{
			synchronized (getUids(uid + "," + type))
			{
				LotteryUserDate lud = lotteryUserDateMgr.findUserDate(lo.getId(), uid, type);
				LotteryUser lu = lotteryUserMgr.findLotteryUser(uid, lo.getId(), type);

				if (lo.getRuletype().equals("D"))// 独立抽奖
				{
					// if (lo.getUsertotal() <= 0)设置负数或者0时不限制
					// {
					// rs.setHydesc("没有抽奖机会！");
					// rs.setStatus(-10005);
					// return rs;
					// }
					// if (lo.getUserdaynum() <= 0)
					// {
					// rs.setHydesc("很遗憾，明天再试；网上注册，抽更大奖！");
					// rs.setStatus(-10006);
					// return rs;
					// }
					if (lu == null)
					{
						lotteryUserMgr.addLotteryUser(uid, lo.getId(), lo.getUsertotal(), 0, type);
						lu = new LotteryUser();
						lu.setTotalnum(lo.getUsertotal());
					} else if (lu.getTotalnum() != lo.getUsertotal())
					{
						lotteryUserMgr.updateLotteryUser(uid, lo.getId(), lo.getUsertotal(), type);
						lu.setTotalnum(lo.getUsertotal());
					}
				} else if (lu == null)
				{
					rs.setHydesc("没有抽奖机会！");
					rs.setStatus(-10005);
					return rs;
				}

				if (lo.getUsertotal() > 0 && lu.getTotalnum() <= lu.getUsednum())
				{
					rs.setHydesc("没有抽奖机会！");
					rs.setStatus(-10005);
					return rs;
				}
				num = lu.getTotalnum() - lu.getUsednum();// 总共还剩的抽奖次数
				rs.setNum(num);
				if (lud != null)
				{
					tnum = lo.getUserdaynum() - lud.getNum();// 每天剩余的抽奖次数
					rs.setTnum(tnum);
					if (lo.getUserdaynum() > 0 && lo.getUserdaynum() <= lud.getNum())
					{
						rs.setHydesc("今日抽奖达到上限！");
						rs.setStatus(-10006);
						return rs;
					}
				} else
				{
					tnum = lo.getUserdaynum();// 每天剩余的抽奖次数
					rs.setTnum(tnum);
				}
				boolean mx=true;//让不让中奖
				if (lo.getUserzjtotal() > 0)
				{
					int uzjt = lotteryUserRecordMgr.findUserZjTotal(lo.getId(), uid, type);
					znum = lo.getUserzjtotal() - uzjt;// 还剩几次中奖机会
					rs.setZnum(znum);
					if (lo.getUserzjtotal() > 0 && uzjt >= lo.getUserzjtotal())
					{
						System.out.println("中奖达到上限");
						mx=false;
					}
				}

				if (lo.getUsertype().equalsIgnoreCase("S"))// 微信关注
				{
					WxUser wu = vu.getWxUser();
					if (wu == null)
					{
						rs.setHydesc("请在微信环境抽奖！");
						rs.setStatus(-5);
						return rs;
					}
					if (wu.getSubscribe() == 0)
					{
						rs.setHydesc("关注我们'" + wu.getAppnickname() + "'才有抽奖机会哦!");
						rs.setStatus(-5);
						WxGzEvent g = gzEventMgr.findGzEvent(lo.getGzeid());
						if (g != null)
						{
							rs.setUrl(g.getLink());
							gzEventMgr.addSysKeywords(wu.getOpenid(), g.getMsg().getId(), lo.getId(), 3);
						}
						return rs;
					}

				} else if (lo.getUsertype().equalsIgnoreCase("J"))// 积分类型查看积分是否够
				{
					Usertype ut = (Usertype) JsonStringUtil.String2Obj(lo.getAssignuser(), Usertype.class);
					if (ut == null)
					{
						rs.setHydesc("指定用户条件不存在！");
						rs.setStatus(-8);
						return rs;
					}
					int ojf = lotteryUserMgr.findJFen(hyuid);
					xhjf = ut.getJf();
					if (xhjf > ojf)
					{
						rs.setHydesc("需要" + xhjf + "积分才可以抽奖机会哦!");
						rs.setStatus(-7);
						return rs;
					}

				} else if (lo.getUsertype().equalsIgnoreCase("X"))// 现场签到控制
				{
					Usertype ut = (Usertype) JsonStringUtil.String2Obj(lo.getAssignuser(), Usertype.class);
					if (ut == null)
					{
						rs.setHydesc("指定用户条件不存在！");
						rs.setStatus(-8);
						return rs;
					}
					int cn = lotteryAsignUserMgr.findXcCheckIn(ut.getXcid(), uid, type);
					if (cn == 0)
					{
						rs.setHydesc("请到现场签到！");
						rs.setStatus(-4);
						return rs;
					}
				}
				if (isdraw)
				{
					if (lo.getDrawnum() > 0)
					{
						LotteryLose ll = lotteryPrizeMgr.findLose(vu.getUid(), vu.getCd(), lo.getId());
						if (ll != null)
						{
							if (ll.getNum() >= lo.getDrawnum()-1)
							{
								rs = drawNlottery(lo, vu, ll);
							} else
							{
								int rn = (int) (Math.random() * 10000);
								if (mx&&rn < lo.getZjl())
								{
									rs = drawNlottery(lo, vu, ll);
								} else
								{
									rs = drawYlottery(lo, vu, ll);
									lotteryPrizeMgr.updateLose(ll.getId());
								}
							}
						} else
						{
							int rn = (int) (Math.random() * 10000);
							if (mx&&rn < lo.getZjl())
							{
								rs = drawNlottery(lo, vu, null);
							} else
							{
								rs = drawYlottery(lo, vu, null);
								lotteryPrizeMgr.addLose(uid, type, lo.getId());
							}
						}

					} else
					{
						int rn = (int) (Math.random() * 10000);
						if (mx&&rn < lo.getZjl())
						{
							rs = drawNlottery(lo, vu, null);
						} else
						{
							rs = drawYlottery(lo, vu, null);
						}
					}
				} else
				{
					rs.setStatus(8);
					rs.setUrl(url);
					rs.setFurl(furl);
					rs.setId(sid);
					rs.setTnum(tnum);
					rs.setNum(num);
					rs.setZnum(znum);
					return rs;
				}

			}

			Long lpid = null;
			Long lpcid = null;
			int jf = 0;
			if (rs.getLotteryPrize() != null)
			{
				lpid = rs.getLotteryPrize().getId();
				jf = rs.getLotteryPrize().getJf();
			}
			if (rs.getLotteryPrizeCode() != null)
			{
				lpcid = rs.getLotteryPrizeCode().getId();
			}
			long lotteryUserRecordid = lotteryUserRecordMgr.addLotteryCmp(nickname, xhjf, jf, hyuid, uid, lo.getId(), lpid, lpcid, pageid, ip, terminal, source, rs.getStatus(),
					type,lo.getUserdaynum());
			rs.setLotteryUserRecordid(lotteryUserRecordid);
			if (rs.getStatus() == 4)
			{
				wxHongbaoMgr.saveWxHongbaoLotterySend(lo.getId(), lpid, lotteryUserRecordid, vu.getWxUser().getOpenid(), vu.getWxUser().getMpid());
			} else if (rs.getStatus() == 5)
			{
				wxHongbaoMgr.saveSysKeywords(lo.getId(), vu.getWxUser().getMpid(), vu.getWxUser().getOpenid(), rs.getLotteryPrize().getHbkey(), rs.getLotteryPrize().getHbgz(),
						lpid, lotteryUserRecordid);
			} else if (rs.getStatus() == 6)
			{
				wxBuyProductMgr.updateConfirmFreeOrder(rs.getLotteryPrize().getProductid(), ip, vu, lo.getName() + "抽奖，获取的商品！", lo.getOwner(), starturl);
			}
			rs.setUrl(url);
			rs.setFurl(furl);
			rs.setId(sid);
			rs.setTnum(tnum - 1);
			rs.setNum(num - 1);
			rs.setZnum(znum - 1);
			return rs;
		}
	}

}
