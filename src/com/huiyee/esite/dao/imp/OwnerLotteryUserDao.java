package com.huiyee.esite.dao.imp;

import org.springframework.jdbc.core.JdbcTemplate;

import com.huiyee.esite.dao.IOwnerLotteryUserDao;

public class OwnerLotteryUserDao implements IOwnerLotteryUserDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addLotteryUserTotal(long wbuid,int type, long lid, int num)
	{
		jdbcTemplate.update("insert into es_interact_lottery_user (wbuid,lid,totalnum,type) values (?,?,?,?) on duplicate key update totalnum=totalnum+?", new Object[]
		{ wbuid, lid, num,type, num });
	}
}
