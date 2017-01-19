package com.huiyee.interact.lottery.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class LotteryAsignUserDao implements ILotteryAsignUserDao
{

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int findXcCheckIn(long xcid, long uid, int utype)
	{
		return jdbcTemplate.queryForInt("select count(id) from esite.es_interact_xc_checkin_record where uid=? and utype=? and xcid=? and status=1",new Object[]{uid,utype,xcid});
	}
}
