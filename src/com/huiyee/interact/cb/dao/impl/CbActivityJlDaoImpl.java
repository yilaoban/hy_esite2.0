package com.huiyee.interact.cb.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.interact.cb.dao.ICbActivityJlDao;
import com.huiyee.interact.cb.model.CbActivityJlRecord;


public class CbActivityJlDaoImpl implements ICbActivityJlDao
{
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int findTotalCbActivityJlRecordList(long sender)
	{
		String sql = "select count(r.id) from esite.es_interact_cb_activity_jl_record r where r.sender = ?";
		Object[] params = {sender};
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public List<CbActivityJlRecord> findCbActivityJlRecordList(long sender, int start, int size)
	{
		String sql = "select sum(zhuanfa) zhuanfatotal,sum(zhuanfanum) zhuanfanumtotal,sum(guanzhu) guanzhutotal,sum(guanzhunum) guanzhunumtotal,sum(hudong) hudongtotal,sum(hudongnum) hudongnumtotal,sum(click) clicktotal,sum(clicknum) clicknumtotal,dataday from es_interact_cb_activity_jl_record r where r.sender = ? group by r.dataday limit ?,?";
		Object[] params = {sender,start,size};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CbActivityJlRecord r = new CbActivityJlRecord();
				r.setClicktotal(rs.getInt("clicktotal"));
				r.setClicknumtotal(rs.getInt("clicknumtotal"));
				r.setZhuanfatotal(rs.getInt("zhuanfatotal"));
				r.setZhuanfanumtotal(rs.getInt("zhuanfanumtotal"));
				r.setGuanzhutotal(rs.getInt("guanzhutotal"));
				r.setGuanzhunumtotal(rs.getInt("guanzhunumtotal"));
				r.setHudongtotal(rs.getInt("hudongtotal"));
				r.setHudongnumtotal(rs.getInt("hudongnumtotal"));
				r.setDataday(rs.getTimestamp("dataday"));
				return r;
			}
			
		});
	}
	
	
}
