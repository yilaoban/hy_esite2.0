package com.huiyee.interact.lottery.service;

import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.lottery.dto.LotteryRs;
import com.huiyee.interact.lottery.model.Lottery;

public interface ILotteryDrawService
{
	
 public LotteryRs drawOneLottery(VisitUser vu,Lottery lo,long pageid,String ip,String terminal,String source,long relationid,String starturl,boolean isdraw);

}
