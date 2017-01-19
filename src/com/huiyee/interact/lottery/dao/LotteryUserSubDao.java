package com.huiyee.interact.lottery.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class LotteryUserSubDao implements ILotteryUserSubDao
{
private JdbcTemplate jdbcTemplate;

public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
{
	this.jdbcTemplate = jdbcTemplate;
}
}
