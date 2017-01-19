package com.huiyee.esite.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.IOwnerBalanceRecordDao;

public class OwnerBalanceRecordDao implements IOwnerBalanceRecordDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addRecord(long hyuid, int balance, String desc,String type,String stype,long enid)
	{
		jdbcTemplate.update("insert into es_hy_user_balance_record (hyuid,actionnum,hydesc,createtime,type,stype,enid) values (?,?,?,now(),?,?,?)", new Object[]
		{ hyuid, balance, desc ,type,stype,enid});
	}

	@Override
	public long addRmbRecord(long hyuid, int balance, String desc,  String type,  String stype, long enid)
	{
		final String sql = "insert into es_hy_user_balance_record (hyuid,actionnum,hydesc,createtime,type,stype,enid) values ("+hyuid+","+balance+",'"+desc+"',now(),'"+type+"','"+stype+"',"+enid+")";
		KeyHolder keyholder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
				return ps;
			}

		}, keyholder);
		return keyholder.getKey().longValue();
	}

}
