package com.huiyee.pay.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.pay.dao.IOwnerPayDao;
import com.huiyee.pay.model.AliPay;


public class OwnerPayDaoImpl implements IOwnerPayDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public AliPay findAliPay(long ownerid,String type)
	{
		String sql = "select * from esite.es_owner_pay where ownerid = ? and type = ?";
		Object[] params = {ownerid,type};
		List<AliPay> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				AliPay ap = new AliPay();
				ap.setId(rs.getLong("id"));
				ap.setAlikey(rs.getString("alikey"));
				ap.setAlipartner(rs.getString("alipartner"));
				ap.setAliseller_email(rs.getString("aliseller_email"));
				ap.setAppid(rs.getString("appid"));
				ap.setAppsecret(rs.getString("appsecret"));
				ap.setKey(rs.getString("key"));
				ap.setMchid(rs.getString("mchid"));
				return ap;
			}
			
		});
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	
}
