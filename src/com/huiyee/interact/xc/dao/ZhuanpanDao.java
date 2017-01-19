package com.huiyee.interact.xc.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class ZhuanpanDao implements IZhuanpanDao
{

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}



}
