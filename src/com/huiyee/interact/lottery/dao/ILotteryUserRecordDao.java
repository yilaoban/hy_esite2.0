package com.huiyee.interact.lottery.dao;

import java.util.List;

import com.huiyee.interact.lottery.model.LotteryUserRecord;
import com.huiyee.interact.lottery.model.LotteryUserShow;
import com.huiyee.interact.lottery.model.LotteryWinnerDetail;

public interface ILotteryUserRecordDao
{
	public int findUserZjTotal(long lid,long uid,int type);

	public List<LotteryUserRecord> findRecord(long lid, long wbuid, String type, int media, int start, int size);

	public List<LotteryWinnerDetail> findRecordDetail(long lpid, String nickName, int i, int lotteryUserRecordLimit);

	public int findRecordDetailTotal(long lpid, String nickName);
	
	public List<LotteryWinnerDetail> findLotteryUserRecord(long lpid, String processstatus, int start, int size);
	
	public int findLotteryUserRecordTotal(long lpid, String processstatus);
	
	public long addLotteryCmp(String nickname,Long wbuid, Long lid, Long lpid, Long lpcid,Long pageid, String ip, String terminal,String source,int status,int type);

	public List<LotteryWinnerDetail> findRecordDetail(long lpid);

	public int findRecordTotal(long lid, long wbuid, String type, int media);

	public List<LotteryWinnerDetail> findWxRecordDetail(long lpid);

	public List<LotteryUserRecord> findUserJoin(long lid, long fuid, int utype);

	public List<LotteryUserShow> findRecordToScreent(long lid, long lpid, long start, long end);

	/**
	 * 抽奖当前最大的记录值
	 * @param lid
	 * @return
	 */
	public long findMaxRecordid(long lid);

	/**
	 * 抽奖的参与人数
	 * @param lid
	 * @return
	 */
	public int findLotteryTotal(long lid);

	/**
	 * 抽奖的中间人数
	 * @param lid
	 * @return
	 */
	public int findWinTotal(long lid);

	/**
	 * 清空抽奖数据
	 * @param lid
	 * @return
	 */
	public int updateLotteryClean(long lid);

}
