package com.huiyee.interact.renqi.dao;

import java.util.List;

import com.huiyee.interact.renqi.model.FUidJf;
import com.huiyee.interact.renqi.model.RenQiData;
import com.huiyee.interact.renqi.model.RenQiDetail;

public interface IRenQiRecordDao
{
	public void addRqDuidDraw(long rqid, long duid, long fuid, int utype, int addjf, String ip, String terminal, String source);

	public void updateRqFuidJfTotal(long rqid, long fuid, int utype, int addjf);

	public void addRqFuidJf(long rqid, long fuid, int utype);

	public void addFuidShare(long rqid, long fuid, int utype, String content, String ip, String terminal, String source);

	public void updateRqFuidJfUsed(long rqid, long fuid, int utype, int addjf);

	public FUidJf findFuidJf(long rqid, long fuid, int utype);

	public void addLotteryUserTotal(long lid, long fuid, int utype, int addjf);

	public int findDuidDraw(long rqid, long duid, long fuid, int utype);

	public int findJoinTotal(long rqid, int utype);

	public List<RenQiData> findRecordList(long rqid, int utype, int start, int size);

	public int findFuidTotal(long fuid, long rqid);

	public List<RenQiDetail> findFuidDetail(long fuid, long rqid, int start, int size);

	public String findFristIp(long rqid, long fuid);
}
