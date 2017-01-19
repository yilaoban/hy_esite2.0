package com.huiyee.esite.dao;

public interface ILotteryUserChanceRecordDao
{
	/**
	 * 
	 * @param entityid 申请或调研或投票或口碑的id
	 * @param featureid 118-申请 123-投票 124-调研 134-口碑
	 * @param userid wbuid或wxuid
	 * @param utype 0-sina 1-weixin
	 * @return 获得的抽奖次数
	 */
	public int findUserChanceTotal(long entityid,long featureid,long userid,int utype);
	
	/**
	 * 
	 * @param entityid 申请或调研或投票或口碑的id
	 * @param featureid 118-申请 123-投票 124-调研 134-口碑
	 * @param userid wbuid或wxuid
	 * @param utype 0-sina 1-weixin
	 * @param num 添加抽奖次数
	 * @return
	 */
	public void addLotteryChanceRecord(long entityid,long featureid,long userid,int utype,int num,long lid);
}
