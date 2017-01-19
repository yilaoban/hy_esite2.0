package com.huiyee.interact.lottery.mgr;

/**
 * 抽奖指定用户统一处理的mgr
 * @author hzm
 *
 */
public interface ILotteryAsignUserMgr
{
   public int findXcCheckIn(long xcid,long uid,int utype);
   
}
