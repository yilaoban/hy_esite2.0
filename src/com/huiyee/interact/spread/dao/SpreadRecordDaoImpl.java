package com.huiyee.interact.spread.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.interact.spread.model.SpreadRecord;

public class SpreadRecordDaoImpl implements ISpreadRecordDao
{
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate()
	{
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	public int findspreadRecordTotal(long spreadid, String begintime, String endtime)
	{
		List<Object> list = new ArrayList<Object>();
		String sql = "select count(*) from es_feature_interact_spread_record  sr   where sr.spreadid=? and sr.type=0";
		list.add(spreadid);
		if (StringUtils.isNotEmpty(begintime))
		{
			sql += " and ?<sr.createtime ";
			list.add(begintime);
		}
		if (StringUtils.isNotEmpty(endtime))
		{
			sql += " and ?>sr.createtime ";
			list.add(endtime);
		}
		return jdbcTemplate.queryForInt(sql, list.toArray());
	}

	@Override
	public List<SpreadRecord> findSpreadrecordList(int start, int size, long spreadid, String begintime, String endtime)
	{
		List<Object> param = new ArrayList<Object>();
		String sql = "select * from es_feature_interact_spread_record sr  where sr.spreadid=? and sr.type=0 ";
		param.add(spreadid);
		if (StringUtils.isNotEmpty(begintime))
		{
			sql += " and ?<sr.createtime ";
			param.add(begintime);
		}
		if (StringUtils.isNotEmpty(endtime))
		{
			sql += " and ?>sr.createtime ";
			param.add(endtime);
		}
		sql += " order by sr.id desc limit ?,? ";
		param.add(start);
		param.add(size);
		List<SpreadRecord> list = jdbcTemplate.query(sql, param.toArray(), new MyRowMapper());
		return list;
	}
	
	@Override
	public List<SpreadRecord> findSpreadrecordListWx(int start, int size, long spreadid, String begintime, String endtime)
	{
		List<Object> param = new ArrayList<Object>();
		String sql = "select * from es_feature_interact_spread_record sr where sr.spreadid=? and sr.type=1 ";
		param.add(spreadid);
		if (StringUtils.isNotEmpty(begintime))
		{
			sql += " and ?<sr.createtime ";
			param.add(begintime);
		}
		if (StringUtils.isNotEmpty(endtime))
		{
			sql += " and ?>sr.createtime ";
			param.add(endtime);
		}
		sql += " order by sr.id desc limit ?,? ";
		param.add(start);
		param.add(size);
		List<SpreadRecord> list = jdbcTemplate.query(sql, param.toArray(), new MyRowMapper());
		return list;
	}
	
	@Override
	public int findspreadRecordTotalWx(long spreadid, String begintime, String endtime)
	{
		List<Object> list = new ArrayList<Object>();
		String sql = "select count(*) from es_feature_interact_spread_record  sr  where sr.spreadid=? and sr.type=1";
		list.add(spreadid);
		if (StringUtils.isNotEmpty(begintime))
		{
			sql += " and ?<sr.createtime ";
			list.add(begintime);
		}
		if (StringUtils.isNotEmpty(endtime))
		{
			sql += " and ?>sr.createtime ";
			list.add(endtime);
		}
		return jdbcTemplate.queryForInt(sql, list.toArray());
	}

	class MyRowMapper implements RowMapper
	{
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			SpreadRecord sr = new SpreadRecord();
			sr.setId(rs.getLong("sr.id"));
			sr.setPageid(rs.getLong("sr.pageid"));
			sr.setWbuid(rs.getLong("wbuid"));
			sr.setCreatetime(rs.getTimestamp("sr.createtime"));
			sr.setPic(rs.getString("sr.pic"));
			sr.setContent(rs.getString("sr.content"));
			sr.setIp(rs.getString("ip"));
			return sr;
		}
	}

}
