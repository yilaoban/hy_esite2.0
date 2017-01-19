package com.huiyee.interact.lottery.dto;

import com.huiyee.esite.dto.HdRsDto;
import com.huiyee.interact.lottery.model.LotteryPrize;
import com.huiyee.interact.lottery.model.LotteryPrizeCode;

public class LotteryRs extends HdRsDto
{
	//private int status;//-1-日限量达到,-2-没有抽奖机会,-3-没有此抽奖(暂时不用),-4-不在指定用户组,-5-不是微信关注者,-6-用户不存在(暂时用不到),-7-积分不够抽奖,-8-指定用户条件不存在, 0-未中奖,1-中了积分,2-中了优惠劵,3-中了实物
	private long lotteryUserRecordid;
	//private String hydesc;
	private LotteryPrize lotteryPrize;
	private LotteryPrizeCode lotteryPrizeCode;
	private int znum;//还剩几次中奖机会
	
	public int getZnum()
	{
		return znum;
	}
	
	public void setZnum(int znum)
	{
		this.znum = znum;
	}

	public LotteryPrize getLotteryPrize()
	{
		return lotteryPrize;
	}

	public void setLotteryPrize(LotteryPrize lotteryPrize)
	{
		this.lotteryPrize = lotteryPrize;
	}

	public LotteryPrizeCode getLotteryPrizeCode()
	{
		return lotteryPrizeCode;
	}

	public void setLotteryPrizeCode(LotteryPrizeCode lotteryPrizeCode)
	{
		this.lotteryPrizeCode = lotteryPrizeCode;
	}

	public String getResult(){
		switch (this.getStatus()) {
			case 0 :
				return "谢谢参与!";
			case 1 :
				return "中了"+lotteryPrize.getPrice()+"积分";
			case 2 :
				return "中了"+lotteryPrize.getName();
			case 3 :
				return "中了"+lotteryPrize.getName();
			default :
				return "谢谢参与!";
		}
	}

	public long getLotteryUserRecordid()
	{
		return lotteryUserRecordid;
	}

	public void setLotteryUserRecordid(long lotteryUserRecordid)
	{
		this.lotteryUserRecordid = lotteryUserRecordid;
	}
}
