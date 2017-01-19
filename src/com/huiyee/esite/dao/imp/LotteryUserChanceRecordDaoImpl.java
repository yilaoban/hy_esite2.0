package com.huiyee.esite.dao.imp;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.ILotteryUserChanceRecordDao;

public class LotteryUserChanceRecordDaoImpl extends AbstractDao implements ILotteryUserChanceRecordDao
{
	@Override
	public int findUserChanceTotal(long entityid, long featureid, long userid, int utype)
	{
		return getJdbcTemplate().queryForInt("select sum(num) from esite.es_interact_lottery_user_chance_record where entityid=? and featureid=? and userid=? and utype=?", new Object[]
		{ entityid, featureid, userid, utype });
	}

	@Override
	public void addLotteryChanceRecord(long entityid, long featureid, long userid, int utype, int num,long lid)
	{
		getJdbcTemplate().update("insert into esite.es_interact_lottery_user_chance_record (entityid,featureid,userid,utype,createtime,num,lid) values(?,?,?,?,now(),?,?)", new Object[]
		{ entityid, featureid, userid, utype, num ,lid});
	}
}
